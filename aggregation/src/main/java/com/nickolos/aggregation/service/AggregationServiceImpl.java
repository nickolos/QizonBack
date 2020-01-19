package com.nickolos.aggregation.service;


import com.nickolos.aggregation.entities.User;
import com.nickolos.aggregation.model.StatisticRequest;
import com.nickolos.aggregation.model.StatisticResponse;
import com.nickolos.aggregation.repository.UserDetailsRepo;
import com.nickolos.game.model.*;
import com.nickolos.game.service.GameService;

import com.nickolos.result.model.*;
import com.nickolos.result.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AggregationServiceImpl implements AggregationService {
    private static final Logger log = LoggerFactory.getLogger(AggregationServiceImpl.class);

    @Autowired
    @Qualifier("remoteGameService")
    GameService gameService;

    @Autowired
    @Qualifier("remoteResultService")
    ResultService resultService;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    @Transactional
    public ResponseGameId createGame(RequestUser user) {
        return gameService.createdGame(user);
    }

    @Override
    @Transactional
    public ListActiveGame activeGames() {
        return gameService.activeGames();
    }

    @Override
    @Transactional
    public ResponseQuestionFifthRound play(Long id_game, int number) {
        return gameService.question(id_game, number);
    }

    @Override
    @Transactional
    public ResponseAnswerFifthRound answer(Long id_game, int number) {
        return gameService.answer(id_game, number);
    }

    @Override
    @Transactional
    public ResponseSave save(RequestAnswer answer) {
        answer.setAnswer_right(gameService.answer(answer.getId_game(), answer.getNumber()).getAnswer());
        return resultService.saved(answer);
    }

    @Override
    @Transactional
    public TableResultGame tableresult(Long id_game) {
        return resultService.tableresult(id_game);
    }

    @Override
    @Transactional
    public ResponseMoreResult moreresult(RequestMoreResult req) {
        return resultService.moreresult(req);
    }

    @Override
    @Transactional
    public ResponseGameId joinToGame(Long id_game, RequestUser user) {
        return gameService.joinToGame(id_game, user);
    }

    @Override
    @Transactional
    public StatisticResponse statistics(StatisticRequest request) {
        TableResultGame tableResultGame = resultService.tableresult(request.getId_game());
        StatisticResponse response = new StatisticResponse();
        int count_participant = tableResultGame.getTable().size();
        for (int i = 0; i < count_participant; i++) {
            if (request.getName().equals(tableResultGame.getTable().get(i).getName())) {

                User user = userDetailsRepo.findByName(request.getName());
                user.setRating(user.getRating() + tableResultGame.getTable().get(i).getScore() * (i + 1));
                if (i == 0) {
                    user.setFp_count(user.getFp_count() + 1);
                }
                userDetailsRepo.save(user);
                response.setUID(user.getId());
                response.setFp_count(user.getFp_count());
                response.setRating(user.getRating());
            }
        }
        return response;
    }
}
