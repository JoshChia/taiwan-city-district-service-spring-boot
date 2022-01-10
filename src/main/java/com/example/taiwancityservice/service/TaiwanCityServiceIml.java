package com.example.taiwancityservice.service;

import com.example.taiwancityservice.dao.TaiwanCityDao;
import com.example.taiwancityservice.dto.request.TaiwanCityCreateReq;
import com.example.taiwancityservice.dto.request.TaiwanCityDeleteReq;
import com.example.taiwancityservice.dto.request.TaiwanCityQueryReq;
import com.example.taiwancityservice.dto.request.TaiwanCityUpdateReq;
import com.example.taiwancityservice.dto.response.TaiwanCityCreateRes;
import com.example.taiwancityservice.dto.response.TaiwanCityDeleteRes;
import com.example.taiwancityservice.dto.response.TaiwanCityQueryRes;
import com.example.taiwancityservice.dto.response.TaiwanCityUpdateRes;
import com.example.taiwancityservice.entity.TaiwanCityDistrict;
import com.example.taiwancityservice.exception.ECIPException;
import com.example.taiwancityservice.enums.ReturnCode;
import com.example.taiwancityservice.requestscope.RequestScopedContext;
import com.example.taiwancityservice.dto.response.TaiwanCityQueryRes.TaiwanCityDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.taiwancityservice.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TaiwanCityServiceIml implements TaiwanCityService{
    @Autowired
    private TaiwanCityDao taiwanCityDao;

    @Autowired
    private RequestScopedContext requestScopedContext;

    @Override
    public TaiwanCityQueryRes queryTaiwanCity(TaiwanCityQueryReq taiwanCityQueryReq) {



        TaiwanCityDistrict taiwanCityDistrict = new TaiwanCityDistrict();
//        taiwanCityDistrict.setCity(taiwanCityQueryReq.getCity());
//        taiwanCityDistrict.setDistrict(taiwanCityDistrict.getDistrict());

        log.info("taiwanCityQueryRes : {}", taiwanCityQueryReq.getCity() + "" + taiwanCityQueryReq.getDistrict());
        List<TaiwanCityDistrict> taiwanCityDistrictList = taiwanCityDao.selectByCityAndDistrict(
                taiwanCityQueryReq.getCity(),
                taiwanCityQueryReq.getDistrict()
        );
//        List<TaiwanCityDistrict> taiwanCityDistrictList = taiwanCityDao.selectAll();

        // 組回傳資料
        TaiwanCityQueryRes taiwanCityQueryRes = new TaiwanCityQueryRes();
        List<TaiwanCityDTO> taiwanCityDTOList = taiwanCityDistrictList.stream().map(record -> {
            TaiwanCityDTO taiwanCityDTO = new TaiwanCityDTO();
            BeanUtils.copyProperties(record, taiwanCityDTO);
            return taiwanCityDTO;
        }).collect(Collectors.toList());

        taiwanCityQueryRes.setTaiwanCityList(taiwanCityDTOList);
        taiwanCityQueryRes.setTaiwanCityCount(taiwanCityQueryRes.getTaiwanCityList().size());

        return taiwanCityQueryRes;
    }

    @Override
    public TaiwanCityCreateRes createTaiwanCity(TaiwanCityCreateReq taiwanCityCreateReq) {

//        檢查資料是否存在
        TaiwanCityDistrict taiwanCityDistrict = taiwanCityDao.selectByPrimaryKey(taiwanCityCreateReq.getCity(), taiwanCityCreateReq.getDistrict());
        if (taiwanCityDistrict != null){
            throw new ECIPException(ReturnCode.DB_INSERT_ERROR, "資料新增失敗,該筆資料已存在資料庫中");
        }

        taiwanCityDistrict = new TaiwanCityDistrict();

        taiwanCityDistrict.setCity(taiwanCityCreateReq.getCity());
        taiwanCityDistrict.setDistrict(taiwanCityCreateReq.getDistrict());
        taiwanCityDistrict.setSerNo(taiwanCityCreateReq.getSerNo());

        LocalDateTime currentDateTime = LocalDateTime.now();
        taiwanCityDistrict.setDataCreateTime(DateUtil.formatDateTime(DateUtil.dateTimeFormatter, currentDateTime));
        taiwanCityDistrict.setDataUpdateTime(DateUtil.formatDateTime(DateUtil.dateTimeFormatter, currentDateTime));

        taiwanCityDistrict.setDataUpdateUser(requestScopedContext.getRequestMessageTemplate().getRequestMessageHeader().getUserId());

        //        log.info("enterpriseDataList : {}", taiwanCityDistrict.getDistrict());
        //        新增資料至資料庫
        taiwanCityDao.insert(taiwanCityDistrict);

        return new TaiwanCityCreateRes();
    }

    @Override
    public TaiwanCityUpdateRes updateTaiwanCity(TaiwanCityUpdateReq taiwanCityUpdateReq) {

//        判斷搜尋條件是否符合
       TaiwanCityDistrict taiwanCityDistrict = taiwanCityDao.selectByPrimaryKey(taiwanCityUpdateReq.getOldCity(), taiwanCityUpdateReq.getOldDistrict());
       if(taiwanCityDistrict == null){
           throw new ECIPException(ReturnCode.DB_UPDATE_ERROR, "資料更新失敗,該筆資料不存在資料中");
       }

//       判斷更新資料是否存在
       taiwanCityDistrict = taiwanCityDao.selectByPrimaryKey(taiwanCityUpdateReq.getCity(), taiwanCityUpdateReq.getDistrict());
       if(taiwanCityDistrict != null){
           throw new ECIPException(ReturnCode.DB_UPDATE_ERROR, "資料更新失敗,該筆資料已重複");
       }

       taiwanCityDistrict = new TaiwanCityDistrict();

       taiwanCityDistrict.setCity(taiwanCityUpdateReq.getCity());
       taiwanCityDistrict.setDistrict(taiwanCityUpdateReq.getDistrict());

       LocalDateTime currentDateTime = LocalDateTime.now();
       taiwanCityDistrict.setDataUpdateTime(DateUtil.formatDateTime(DateUtil.dateTimeFormatter, currentDateTime));

       taiwanCityDistrict.setDataUpdateUser(requestScopedContext.getRequestMessageTemplate().getRequestMessageHeader().getUserId());

       log.info("taiwanCityUpdateDistrict {}", taiwanCityUpdateReq.getCity() + "diestrict" + taiwanCityUpdateReq.getDistrict());

       taiwanCityDao.updateCityDistrictByPrimaryKey(taiwanCityDistrict, taiwanCityUpdateReq.getOldCity(), taiwanCityUpdateReq.getOldDistrict());

       return new TaiwanCityUpdateRes();
    }

    @Override
    public TaiwanCityDeleteRes deleteTaiwanCity(TaiwanCityDeleteReq taiwanCityDeleteReq) {
        TaiwanCityDistrict taiwanCityDistrict = taiwanCityDao.selectByPrimaryKey(taiwanCityDeleteReq.getCity(), taiwanCityDeleteReq.getDistrict()) ;
        if(taiwanCityDistrict == null){
            throw new ECIPException(ReturnCode.DB_DELETE_ERROR, "資料刪除失敗,該筆資料不存在");
        }
        taiwanCityDistrict = new TaiwanCityDistrict();

        taiwanCityDistrict.setCity(taiwanCityDeleteReq.getCity());
        taiwanCityDistrict.setDistrict(taiwanCityDeleteReq.getDistrict());

        taiwanCityDao.deleteByPrimaryKey(taiwanCityDistrict);
        return new TaiwanCityDeleteRes();
    }

}
