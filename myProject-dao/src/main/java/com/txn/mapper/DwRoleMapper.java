package com.txn.mapper;


import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import com.txn.dto.DwRole;

public interface DwRoleMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(DwRole record);
//
//    DwRole selectByPrimaryKey(Long id);

    @Select("select * from role")
    public List<Map<String, String>> query();

}