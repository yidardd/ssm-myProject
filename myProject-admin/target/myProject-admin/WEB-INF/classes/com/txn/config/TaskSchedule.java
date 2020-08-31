package com.txn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 任务定时
 */
@Component
@EnableScheduling
@EnableAsync
public class TaskSchedule {

    private final Logger log = LoggerFactory.getLogger(getClass());

//    @Scheduled(cron = "0/4 * * * * ? ")
//    @Async
//    public void penSaasResultManagerRun() {
//        penSaasResultManager.execute();
//        log.error("penSaasResultManagerRun...finish");
//        try {
//            TimeUnit.DAYS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}