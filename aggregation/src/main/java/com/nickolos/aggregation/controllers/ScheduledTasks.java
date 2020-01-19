package com.nickolos.aggregation.controllers;


import com.nickolos.game.model.ResponseGameId;
import com.nickolos.game.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableAsync
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    @Qualifier("remoteGameService")
    GameService gameService;


    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void reportCurrentTime() {
        List<Long> list = gameService.activeGames().getListActiveGames();
        for (Long aLong : list) {
            Long time = gameService.isStatusGame(aLong).getTimeArrive();

            if (time < 0L) {
                ResponseGameId res = gameService.changedStatusGame(aLong);
                log.info(res.toString());
            }
        }
    }
}
