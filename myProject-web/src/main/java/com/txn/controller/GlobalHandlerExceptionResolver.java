package com.txn.controller;


import com.txn.common.ResponseObject;
import com.txn.common.ResultCode;
import com.txn.exception.ZjPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by stack on 2019/4/20.
 */
@ControllerAdvice
public class GlobalHandlerExceptionResolver {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseObject allExceptionHandler(Exception e) {
         log.info("================【开始打印异常信息】================");
        log.error("具体错误信息:【" + e.getMessage() + "】");
        int count = 0;
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            log.error(stackTraceElement.toString());
            if (count++ > 30) break;
        }
        log.info("================【异常信息打印完毕】================");
        e.printStackTrace();
        ResponseObject result = new ResponseObject();

        if (e instanceof MethodArgumentNotValidException) {
            String substring = e.getMessage().substring(e.getMessage().lastIndexOf("["), e.getMessage().length());
            result.setStatus(ResultCode.INVALID_ARGUMENT.getErrorCode());
            result.setMsg(substring.substring(0, substring.length() - 2));
            return result;
        }

        result.setStatus(ResultCode.INTERNAL_ERROR.getErrorCode());
        result.setMsg(e.getMessage());
        return result;
    }

    @ResponseBody
    @ExceptionHandler({ZjPortException.class})
    public ResponseObject zjPortException(ZjPortException e) {
        ResponseObject result = new ResponseObject();
        result.setStatus(ResultCode.INVALID_ARGUMENT.getErrorCode());
        result.setMsg(e.getMsg());
        return result;
    }

}
