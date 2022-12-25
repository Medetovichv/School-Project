package com.example.school;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchoolScheduler {
    TaskScheduler taskScheduler;

    public void scheduleTask(Date dateTime){
        taskScheduler.schedule(
                this::myTask,
                dateTime
        );
    }

    public void myTask(){

    }



}
