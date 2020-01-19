package com.nickolos.aggregation.controllers;

import com.nickolos.aggregation.model.ResultFromUser;
import com.nickolos.aggregation.model.StatisticRequest;
import com.nickolos.aggregation.model.StatisticResponse;
import com.nickolos.aggregation.service.AggregationService;
import com.nickolos.game.model.*;
import com.nickolos.result.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/quizon")
public class AggregationController {
    private static final Logger logger = LoggerFactory.getLogger(AggregationController.class);

    @Autowired
    private AggregationService aggregationService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/game/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseGameId createGame(@RequestBody RequestUser user) {
        logger.info("POST /quizon/game/create");
        return aggregationService.createGame(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/game/join/{id_game}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseGameId joinToGame(@PathVariable Long id_game, @RequestBody RequestUser user) {
        logger.info("POST /quizon/game/join"+id_game);

        return aggregationService.joinToGame(id_game, user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/play/{id_game}/5/{number}")
    ResponseQuestionFifthRound play(@PathVariable Long id_game, @PathVariable int number) {
        RequestQuestionFifthRound req = new RequestQuestionFifthRound(id_game, number);
        logger.info("POST /game/play/" + id_game + "/5/"+number);
        return aggregationService.play(id_game, number);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/games/now_active")
    public ListActiveGame activeGames() {
        logger.info("GET /games/now_active");
        return aggregationService.activeGames();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/result/save")
    ResponseSave save(@RequestBody ResultFromUser resultFromUser) {
        logger.info("Post  /result/save/");
        RequestAnswer answer = new RequestAnswer();
        answer.setAnswer_user(resultFromUser.getAnswer_user());
        answer.setId_game(resultFromUser.getId_game());
        answer.setName(resultFromUser.getName());
        answer.setNumber(resultFromUser.getNumber());
        answer.setUid(resultFromUser.getUid());
        ResponseAnswerFifthRound answer_right = aggregationService.answer(resultFromUser.getId_game(), resultFromUser.getNumber());
        answer.setAnswer_right(answer_right.getAnswer());
        return aggregationService.save(answer);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/result/table/{id_game}")
    TableResultGame tableresult(@PathVariable Long id_game) {
        logger.info("Post  /result/tablet/"+ id_game);
        return aggregationService.tableresult(id_game);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/result/moreresult")
    ResponseMoreResult moreresult(@RequestBody RequestMoreResult req) {
        logger.info("Post  /result/moreresult/");
        return aggregationService.moreresult(req);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/statistic")
    StatisticResponse statistics(@RequestBody StatisticRequest req) {
        logger.info("Post  /statistic");
        return aggregationService.statistics(req);
    }


}
