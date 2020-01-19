package com.nickolos.aggregation.service;


import com.nickolos.aggregation.model.StatisticRequest;
import com.nickolos.aggregation.model.StatisticResponse;
import com.nickolos.game.model.*;
import com.nickolos.result.model.*;

public interface AggregationService {

       //создание новой игры
       ResponseGameId createGame(RequestUser user);

       //список активных игр
       ListActiveGame activeGames();

       //играть
       ResponseQuestionFifthRound play(Long id_game, int number);

       //Запрос на ответ
       ResponseAnswerFifthRound answer(Long id_game, int number);

       //savResponseGameIde result
       ResponseSave save(RequestAnswer answer);

       //returt result
       TableResultGame tableresult(Long id_game);


       //return moreresult
       ResponseMoreResult moreresult(RequestMoreResult req);

       //connect to игры
       ResponseGameId joinToGame(Long id_game, RequestUser user);

       //statistic
       StatisticResponse statistics(StatisticRequest request);

}
