package com.nickolos.game.service;


import com.nickolos.game.model.*;

public interface GameService {
    //создать игру
    ResponseGameId createdGame(RequestUser user);

    //статус игры
    ResponseGameId isStatusGame(Long game_id);

    //изменение статуса
    ResponseGameId changedStatusGame(Long game_id);

    //запрос на свободные игры
    ListActiveGame activeGames();

    //отправляем вопрос
    ResponseQuestionFifthRound question(Long id_game, int number);

    //присоединиться к игре
    ResponseGameId joinToGame(Long id_game, RequestUser user);

    //узнаем ответ вопрос
    ResponseAnswerFifthRound answer(Long id_game, int number);


}
