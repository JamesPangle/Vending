package com.techelevator.view;

import java.io.File;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    String logPath;
    File logFile;
    PrintWriter logWriter;
    SimpleDateFormat simpleDateFormat;
    String pattern;

    public Log() {
        this.logPath = "Log.txt";
        this.logFile = new File(logPath);
        this.pattern = "MM/dd/yyyy hh:mm:ss a";
        this.simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            this.logWriter = new PrintWriter(new FileOutputStream(logFile), true);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void log(String activity, double currentMoney, double remainingMoney) {
        String localTime = simpleDateFormat.format(new Date());
        logWriter.printf("%s %s: $%.2f $%.2f\n", localTime, activity, currentMoney, remainingMoney);
    }

}