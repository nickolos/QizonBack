package com.nickolos.result.service;


import com.nickolos.result.model.*;

public interface ResultService {

    //вернуть результат
    TableResultGame tableresult(Long id_game);

    //вернуть moreрезультат
    ResponseMoreResult moreresult(RequestMoreResult req);

    //сохраняем результат
    ResponseSave saved(RequestAnswer requestAnswer);



}
