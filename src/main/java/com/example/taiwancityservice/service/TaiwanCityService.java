package com.example.taiwancityservice.service;

import com.example.taiwancityservice.dto.request.TaiwanCityCreateReq;
import com.example.taiwancityservice.dto.request.TaiwanCityDeleteReq;
import com.example.taiwancityservice.dto.request.TaiwanCityQueryReq;
import com.example.taiwancityservice.dto.request.TaiwanCityUpdateReq;
import com.example.taiwancityservice.dto.response.TaiwanCityCreateRes;
import com.example.taiwancityservice.dto.response.TaiwanCityDeleteRes;
import com.example.taiwancityservice.dto.response.TaiwanCityQueryRes;
import com.example.taiwancityservice.dto.response.TaiwanCityUpdateRes;

public interface TaiwanCityService {
    TaiwanCityQueryRes queryTaiwanCity(TaiwanCityQueryReq taiwanCityQueryReq);

    TaiwanCityCreateRes createTaiwanCity(TaiwanCityCreateReq taiwanCityCreateReq);

    TaiwanCityUpdateRes updateTaiwanCity(TaiwanCityUpdateReq taiwanCityUpdateReq);

    TaiwanCityDeleteRes deleteTaiwanCity(TaiwanCityDeleteReq taiwanCityDeleteReq);
}
