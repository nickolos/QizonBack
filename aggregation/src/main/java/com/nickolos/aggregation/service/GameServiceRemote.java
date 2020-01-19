package com.nickolos.aggregation.service;



import com.nickolos.game.model.*;
import com.nickolos.game.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service("remoteGameService")
public class GameServiceRemote implements GameService {

    private static final Logger log = LoggerFactory.getLogger(GameServiceRemote.class);

    private static final String createGame   = "http://localhost:9090/game/create";

    private static final String isStatusGame   = "http://localhost:9090/game/status/{id_game}";

    private static final String changeStatusGame   = "http://localhost:9090/game/changeStatus/{id_game}";

    private static final String listActiveGames   = "http://localhost:9090/game/games/now_active";

    private static final String play   = "http://localhost:9090/game/question/{id_game}/5/{number}";

    private static final String  join   = "http://localhost:9090/game/joinToGame/{id_game}";

    private static final String answer   = "http://localhost:9090/game/answer/{id_game}/5/{number}";

    private RestTemplate rest = new RestTemplate();

    @Override
    public ResponseGameId createdGame(RequestUser user) {

        try {
            return rest.postForObject(createGame,user,ResponseGameId.class);
        } catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }
    }

    @Override
    public ResponseGameId isStatusGame(Long game_id) {
        try {
            return  rest.getForObject(isStatusGame,ResponseGameId.class,game_id);
        }catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }
    }

    @Override
    public ResponseGameId changedStatusGame(Long id_game) {
        try {
         return rest.postForObject(changeStatusGame,null,ResponseGameId.class, id_game);
        }catch (HttpClientErrorException e) {
            log.error("Request error", e);
        } catch (Exception e) {
            log.error("Request error", e);
        }
       return null;
    }

    @Override
    public ListActiveGame activeGames() {
        try {
            return rest.getForObject(listActiveGames,  ListActiveGame.class);
        }catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }

    }

    @Override
    public ResponseQuestionFifthRound question (Long id_game, int number) {
       try{
               return rest.postForObject(play,null,ResponseQuestionFifthRound.class,id_game,number); //reqQuest.getId_game(),reqQuest.getNumber() ,ResponseQuestionFifthRound.class);
        } catch (HttpClientErrorException e) {
        log.error("Request error", e);
        return null;
        } catch (Exception e) {
        log.error("Request error", e);
        return null;
        }
    }

    @Override
    public ResponseGameId joinToGame(Long id_game, RequestUser user) {

        try {
            return rest.postForObject(join,user,ResponseGameId.class,id_game);
        } catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }
    }


    @Override
    public ResponseAnswerFifthRound answer(Long id_game, int number) {
        try{
            return rest.postForObject(answer,null,ResponseAnswerFifthRound.class,id_game,number); //reqQuest.getId_game(),reqQuest.getNumber() ,ResponseQuestionFifthRound.class);
        }catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }
    }
}
