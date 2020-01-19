package com.nickolos.game.controller;


import com.nickolos.game.entities.Round5;
import com.nickolos.game.model.*;
import com.nickolos.game.repository.Round5Repository;
import com.nickolos.game.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@ConditionalOnProperty("game.micro.service")
@RequestMapping("game")
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);


    @Autowired
    @Qualifier("localGameService")
    GameService gameService;

    @Autowired
    private Round5Repository round5Repository;


    @RequestMapping(method = RequestMethod.POST, value = "/init")
    public void json() {
        String data_ans = "";
        ClassPathResource cpr_answ = new ClassPathResource("answer/answer1");

        String data_que = "";
        ClassPathResource cpr_que = new ClassPathResource("question/question1");
        try {
            byte[] bdata_ans = FileCopyUtils.copyToByteArray(cpr_answ.getInputStream());
            byte[] bdata_que = FileCopyUtils.copyToByteArray(cpr_que.getInputStream());
            data_ans = new String(bdata_ans, StandardCharsets.UTF_8);
            data_que = new String(bdata_que, StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.warn("IOException", e);
        }
        List<String> answer = Arrays.asList(data_ans.split("\n"));
        List<String> question = Arrays.asList(data_que.split("question\n"));

        List<Round5> allData = new ArrayList<>();

        for (int i = 0; i < answer.size(); i++) {
            Round5 round5 = new Round5();
            round5.setAnswer(answer.get(i));
            round5.setQuestion(question.get(i));
            int j = i + 1;
            round5.setPath_to_image("img" + j + ".jpg");
            round5.setId(j);
            allData.add(round5);

        }
        logger.info(String.valueOf(allData.size()));
        round5Repository.saveAll(allData);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseGameId createdGame(@RequestBody RequestUser user) {
        logger.info("POST game/create");
        return gameService.createdGame(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/status/{id_game}")
    public ResponseGameId isStatusGame(@PathVariable Long id_game) {
        logger.info("GET /game/status/" + id_game);
        return gameService.isStatusGame(id_game);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, value = "/changeStatus/{id_game}")
    public ResponseGameId changedStatusGame(@PathVariable Long id_game) {
        logger.info("Post /game/status/" + id_game);
        return gameService.changedStatusGame(id_game);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/games/now_active")
    public ListActiveGame activeGames() {
        logger.info("GET /games/now_active");
        return gameService.activeGames();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/question/{id_game}/5/{number}")
    ResponseQuestionFifthRound question(@PathVariable Long id_game, @PathVariable int number) {
        logger.info("POST /game/question/" + id_game + "/5/" + number);
        return gameService.question(id_game, number);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/answer/{id_game}/5/{number}")
    ResponseAnswerFifthRound answer(@PathVariable Long id_game, @PathVariable int number) {
        logger.info("POST /game/answer/" + id_game + "/5/" + number);
        return gameService.answer(id_game, number);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/joinToGame/{id_game}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseGameId joinToGame(@PathVariable Long id_game, @RequestBody RequestUser user) {
        logger.info("POST /game/joinToGame/{id_game}");
        return gameService.joinToGame(id_game, user);

    }


}
