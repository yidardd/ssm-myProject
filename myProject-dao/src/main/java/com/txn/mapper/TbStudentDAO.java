package com.txn.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Select;

public interface TbStudentDAO {
  //    int deleteByPrimaryKey(Integer id);
//
//    int insert(TbStudent record);
//
//    int insertSelective(TbStudent record);
//
//    TbStudent selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(TbStudent record);
//
//    int updateByPrimaryKey(TbStudent record);
  @Select("select * from tb_student")
  public List<Map<String, String>> query();


}