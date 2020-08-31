package com.txn.controller;


import com.txn.common.ResponseObject;
import com.txn.common.ResultCode;
import com.txn.exception.MyprojectException;

import javax.servlet.http.HttpSession;

public abstract class BaseController {

    protected void checkCode(String code, HttpSession session) {
        // 获取session中验证码
        String vercode = (String) session.getAttribute("verifyCode");

        // 校验
        if (null != vercode && vercode.equals(code)) {
            session.removeAttribute("verifyCode");
            return;
        }
        throw new MyprojectException("验证码错误");
    }

    protected ResponseObject returnResponse(Object page) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatus(ResultCode.BUSINESS_SUCESS.getErrorCode());
        responseObject.setData(page);
        return responseObject;
    }

    protected ResponseObject failMsg(String message) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.failed(message);
        return responseObject;
    }

    protected ResponseObject successMsg() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.success("ok");
        return responseObject;
    }


}
