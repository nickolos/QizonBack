package com.nickolos.game.service;


import com.nickolos.game.entities.Game;
import com.nickolos.game.model.*;
import com.nickolos.game.repository.GameRepository;
import com.nickolos.game.repository.Round5Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.aspectj.runtime.internal.Conversions.intValue;


@Service("localGameService")
@ConditionalOnProperty("game.micro.service")
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private final int MAX_INDEX = 9;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private Round5Repository round5Repository;

    @Transactional
    @Override
    public ResponseGameId createdGame(RequestUser user) {
        Game game = new Game();
        ResponseGameId responseGameId = new ResponseGameId();
        List<Integer> index_for_fifth_round = list_index(MAX_INDEX, 6);
        int[] round5s = new int[index_for_fifth_round.size()];
        for (int i = 0; i < index_for_fifth_round.size(); i++) {
            round5s[i] = intValue(index_for_fifth_round.get(i));
        }
        game.setFifth_round(round5s);
        game.setGame_status(true);
        game.setTime(new Date());
        gameRepository.save(game);
        responseGameId.setId(gameRepository.findById(game.getId()).get().getId());
        responseGameId.setTimeArrive(120000L);
        logger.info("time create:" + gameRepository.findById(responseGameId.getId()).get().getTime().getTime());
        return responseGameId;
    }


    @Transactional
    @Override
    public ResponseGameId isStatusGame(Long game_id) {

        if (gameRepository.findById(game_id).get().isGame_status()) {
            Date fin = new Date();
            Long time = (120000L + gameRepository.findById(game_id).get().getTime().getTime() - fin.getTime());
            return new ResponseGameId(game_id, time);
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseGameId changedStatusGame(Long id_game) {
        Game game = gameRepository.findById(id_game).get();
        game.setGame_status(false);
        gameRepository.save(game);
        logger.info(String.valueOf(gameRepository.findById(id_game).get().isGame_status()));
        return new ResponseGameId(id_game, 0L);
    }

    @Override
    @Transactional
    public ListActiveGame activeGames() {

        return new ListActiveGame(gameRepository.findAllByGame_statusIsTrue());
    }

    @Override
    @Transactional
    public ResponseQuestionFifthRound question(Long id_game, int number) {
        ResponseQuestionFifthRound resp = new ResponseQuestionFifthRound();
        Game game = gameRepository.findById(id_game).get();
        resp.setId_game(id_game);
        resp.setNumber(number);
        int[] index = game.getFifth_round();
        resp.setQuestion(round5Repository.getOne(index[number] + round5Repository.getFirstById()).getQuestion());
        resp.setFile(round5Repository.getOne(index[number] + round5Repository.getFirstById()).getPath_to_image());
        return resp;
    }

    @Override
    public ResponseGameId joinToGame(Long id_game, RequestUser user) {
        ResponseGameId responseGameId = new ResponseGameId();
        if (gameRepository.findById(id_game).get().isGame_status()) {
            responseGameId.setId(id_game);
            Date fin = new Date();
            responseGameId.setTimeArrive(120000L + gameRepository.findById(id_game).get().getTime().getTime() - fin.getTime());
        }
        return responseGameId;
    }

    @Override
    @Transactional
    public ResponseAnswerFifthRound answer(Long id_game, int number) {
        ResponseAnswerFifthRound resp = new ResponseAnswerFifthRound();
        Game game = gameRepository.findById(id_game).get();
        int[] index = game.getFifth_round();
        resp.setId_game(id_game);
        resp.setNumber(number);
        resp.setAnswer(round5Repository.getOne(index[number] + round5Repository.getFirstById()).getAnswer());
        return resp;
    }


    //сделать норм рандом
    List<Integer> list_index(int max, int count_question) {
        Set<Integer> list = new HashSet<>(count_question);
        List<Integer> out = new ArrayList<>(count_question);
        Random random = new Random();
        while (list.size() < count_question) {
            list.add(random.nextInt(max));
        }
        out.addAll(list);
        return out;
    }

}
