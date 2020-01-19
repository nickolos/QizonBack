package com.nickolos.result.controller;


import com.nickolos.result.model.*;
import com.nickolos.result.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@ConditionalOnProperty("result.micro.service")
@RequestMapping("result")
public class ResultController {

    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    @Qualifier("localResultService")
    ResultService resultService;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSave saved(@RequestBody RequestAnswer answer) {
        logger.info("POST result/save");
        return resultService.saved(answer);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/simple/{id_game}")
    TableResultGame tableresult(@PathVariable Long id_game) throws InterruptedException {
        logger.info("POST /result/simple"+id_game);
        return resultService.tableresult(id_game);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/moreresult")
    ResponseMoreResult moreresult(@RequestBody RequestMoreResult req) {
        logger.info("POST /moreresult");
        return resultService.moreresult(req);
    }

}
