package com.techelevator.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private File logFile = new File("Log.txt");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss a");
    private LocalDateTime a;

    public String writeFeedMoney(BigDecimal moneyAdded, BigDecimal balance) {
        a = LocalDateTime.now();
        return appendToLog(a.format(formatter) + " FEED MONEY: $" + moneyAdded + " $" + balance);
    }

    public String writeItemPurchased(String key, String name, BigDecimal itemPrice, BigDecimal balance) {
        a = LocalDateTime.now();
        return appendToLog(a.format(formatter) + " " + key + " $" + itemPrice + " $" + balance);
    }

    public String writeCompleteTransaction(BigDecimal change, BigDecimal balance) {
        a = LocalDateTime.now();
        return appendToLog(a.format(formatter) + " GIVE CHANGE: $" + change + " $" + balance);
    }

    private String appendToLog(String string) {
        String result = "";
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
            writer.println(string);
        } catch (FileNotFoundException e) {
            result = "\nFile not found.";
        }
        return result;
    }

}