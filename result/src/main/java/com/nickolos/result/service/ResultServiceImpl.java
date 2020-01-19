package com.nickolos.result.service;

import com.nickolos.result.entities.FullResult;
import com.nickolos.result.entities.FullResultId;
import com.nickolos.result.model.*;
import com.nickolos.result.repository.FullResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Service("localResultService")
@ConditionalOnProperty("result.micro.service")
public class ResultServiceImpl implements ResultService {

    @Autowired
    private FullResultRepo fullResultRepo;

    @Override
    @Transactional
    public TableResultGame tableresult(Long id_game) {
        TableResultGame tableResultGame = new TableResultGame();
        List<ResponseResult> response = new ArrayList<>();
        List<FullResult> full = fullResultRepo.fullRes(id_game);
        int i = 0;
        for (FullResult res : full) {
            res.setPlace(++i);
        }
        fullResultRepo.saveAll(full);
        full = fullResultRepo.fullRes(id_game);
        for (FullResult res : full) {
            response.add(new ResponseResult(res.getPlace(), res.getScore(), res.getId().getName()));
        }
        tableResultGame.setTable(response);

        return tableResultGame;
    }

    @Override
    @Transactional
    public ResponseMoreResult moreresult(RequestMoreResult req) {
        FullResult result = fullResultRepo.findById_gameAndUid(req.getId_game(), req.getUid());
        return new ResponseMoreResult(result.getScore(), req.getName(), result.getInfo());
    }

    @Transactional
    @Override
    public ResponseSave saved(RequestAnswer requestAnswer) {

        if (fullResultRepo.findById_gameAndUid(requestAnswer.getId_game(), requestAnswer.getUid()) != null) {
            FullResult update = fullResultRepo.findById_gameAndUid(requestAnswer.getId_game(), requestAnswer.getUid());
            String[] mas = new String[update.getAnswer().length + 1];
            List<String> list = new LinkedList<String>(Arrays.asList(update.getAnswer()));
            list.add(requestAnswer.getAnswer_user());
            mas = list.toArray(mas);
            if (requestAnswer.getAnswer_right().equals(requestAnswer.getAnswer_user())) {
                update.setScore(update.getScore() + 1);
                update.setInfo(update.getInfo() + "+");
            } else {
                update.setInfo(update.getInfo() + "-");

            }
            update.setAnswer(mas);
            fullResultRepo.save(update);
        } else {

            FullResult full = new FullResult();
            full.setPlace(1);
            full.setId(new FullResultId(requestAnswer.getId_game(), requestAnswer.getUid(), requestAnswer.getName()));
            String[] mas = new String[1];
            mas[0] = requestAnswer.getAnswer_user();
            full.setAnswer(mas);
            if (requestAnswer.getAnswer_right().equals(requestAnswer.getAnswer_user())) {
                full.setScore(full.getScore() + 1);
                full.setInfo("+");
            } else {
                full.setInfo("-");
                full.setScore(0);
            }
            fullResultRepo.save(full);
        }
        return new ResponseSave();
    }
}
