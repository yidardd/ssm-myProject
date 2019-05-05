package com.txn.mapper;


import com.txn.dto.DwRole;

public interface DwRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DwRole record);

    DwRole selectByPrimaryKey(Long id);
}