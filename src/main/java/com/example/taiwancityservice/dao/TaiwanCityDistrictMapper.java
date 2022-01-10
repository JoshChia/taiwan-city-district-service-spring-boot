package com.example.taiwancityservice.dao;

import com.example.taiwancityservice.entity.TaiwanCityDistrict;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaiwanCityDistrictMapper {
    int deleteByPrimaryKey(@Param("city") String city, @Param("district") String district);

    int insert(TaiwanCityDistrict record);

    TaiwanCityDistrict selectByPrimaryKey(@Param("city") String city, @Param("district") String district);

    List<TaiwanCityDistrict> selectAll();

    List<TaiwanCityDistrict> selectByCityAndDistrict(@Param("city") String city, @Param("district") String district);

    int updateByPrimaryKey(TaiwanCityDistrict record);

    int updateCityDistrictByPrimaryKey(TaiwanCityDistrict record, @Param("oldCity") String oldCity, @Param("oldDistrict") String oldDistrict);
}