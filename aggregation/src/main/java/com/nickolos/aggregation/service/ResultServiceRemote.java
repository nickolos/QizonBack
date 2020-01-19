package com.nickolos.aggregation.service;


import com.nickolos.result.model.*;
import com.nickolos.result.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service("remoteResultService")
public class ResultServiceRemote implements ResultService {

    private static final Logger log = LoggerFactory.getLogger(GameServiceRemote.class);

    private static final String save = "http://localhost:9900/result/save";

    private static final String result = "http://localhost:9900/result/simple/{id_game}";

    private static final String moreresult = "http://localhost:9900/result/moreresult";

    private RestTemplate rest = new RestTemplate();

    @Override
    public ResponseMoreResult moreresult(RequestMoreResult req) {
        try {
            return rest.postForObject(moreresult, req, ResponseMoreResult.class);
        } catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }
    }

    @Override
    public TableResultGame tableresult(Long id_game) {
        try {
            return rest.postForObject(result, null, TableResultGame.class, id_game);
        } catch (HttpClientErrorException e) {
            log.error("Request error", e);
            return null;
        } catch (Exception e) {
            log.error("Request error", e);
            return null;
        }
    }

    @Override
    public ResponseSave saved(RequestAnswer requestAnswer) {
        try {
            return rest.postForObject(save, requestAnswer, ResponseSave.class);
        } catch (HttpClientErrorException e) {
            log.error("Request error", e);
        } catch (Exception e) {
            log.error("Request error", e);
        }
        return null;
    }
}
