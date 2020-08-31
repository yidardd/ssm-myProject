package com.txn.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2019/10/17 18:26
 * @Version 1.0
 * @Description User
 */
@Data
public class User implements Serializable {

    private Integer id ;
    private String userName;
    private String password;

}
