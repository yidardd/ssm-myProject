package com.txn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.txn.dto.DwRole;
import com.txn.mapper.DwRoleMapper;
import com.txn.service.TestService;

/**
 * Created by stack on 2019/5/5.
 */
@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private DwRoleMapper dwRoleMapper;

  @Override
  public String test() {
//    DwRole dwRole = dwRoleMapper.selectByPrimaryKey(1L);
    return "aa";
  }

  @Override
  public String printStr() {
    System.out.println("TestServiceImpl  return  printStr");
    return "printStr";
  }

}
