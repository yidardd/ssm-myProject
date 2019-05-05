package com.txn.service.impl;

import com.txn.dto.DwRole;
import com.txn.mapper.DwRoleMapper;
import com.txn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by stack on 2019/5/5.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private DwRoleMapper dwRoleMapper;

    @Override
    public String test() {
        DwRole dwRole = dwRoleMapper.selectByPrimaryKey(1L);
        return "aa";
    }

}
