package com.lu.quartz.config;

import com.lu.quartz.demo.QuartzDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author 小卢
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(QuartzDemo.class);
        return factoryBean;
    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        //关联jobDetail对象
//        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//        //表示一个执行间隔的毫秒数
//        factoryBean.setRepeatInterval(2000);
//        //重复次数
//        factoryBean.setRepeatCount(5);
//        return factoryBean;
//    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0/2 * * * * ?");
        return factoryBean;
    }

    @Autowired
    MyAdaptableJobFactory adaptableJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean,MyAdaptableJobFactory myAdaptableJobFactory) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        //关联trigger
        factoryBean.setTriggers(cronTriggerFactoryBean.getObject());

        //设置自己写的适配器任务工厂类 可以让quartzDemo可以使用springIOC
        factoryBean.setJobFactory(myAdaptableJobFactory);
        return factoryBean;
    }
}
