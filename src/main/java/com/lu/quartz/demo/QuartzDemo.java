package com.lu.quartz.demo;

import com.lu.quartz.service.FileService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author 小卢
 */
public class QuartzDemo implements Job {

    @Autowired
    private FileService fileService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(LocalDateTime.now());
        fileService.test();
    }
}
