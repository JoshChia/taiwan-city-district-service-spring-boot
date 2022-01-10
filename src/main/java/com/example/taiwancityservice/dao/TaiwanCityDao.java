package com.example.taiwancityservice.dao;

import com.example.taiwancityservice.entity.TaiwanCityDistrict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TaiwanCityDao {
    @Autowired
    private TaiwanCityDistrictMapper taiwanCityDistrictMapper;

    public List<TaiwanCityDistrict> selectByCityAndDistrict(String city, String district){
        log.info("data", city + "district" + district);
        return taiwanCityDistrictMapper.selectByCityAndDistrict(city, district);
    }
    public List<TaiwanCityDistrict> selectAll(){
        return taiwanCityDistrictMapper.selectAll();
    }

    public Integer insert(TaiwanCityDistrict taiwanCityDistrict){
        return taiwanCityDistrictMapper.insert(taiwanCityDistrict);
    }

    public TaiwanCityDistrict selectByPrimaryKey(String city, String district){

        return taiwanCityDistrictMapper.selectByPrimaryKey(city,district);
    }

    public int updateCityDistrictByPrimaryKey(TaiwanCityDistrict taiwanCityDistrict, String oldCity, String oldDistrict){
        log.info("Dao city", taiwanCityDistrict.getCity());
        return taiwanCityDistrictMapper.updateCityDistrictByPrimaryKey(taiwanCityDistrict, oldCity, oldDistrict);
    }

    public int deleteByPrimaryKey(TaiwanCityDistrict taiwanCityDistrict){
        return taiwanCityDistrictMapper.deleteByPrimaryKey(taiwanCityDistrict.getCity(), taiwanCityDistrict.getDistrict());
    }
}
