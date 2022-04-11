package com.proj.comp2171project;

import java.util.Date;

public class Notification {

    private String batchID;
    private String fname;
    private String lname;
    private Date trainingDate;
    private static String notification;

    public static String generateBatchNotification(String batchID,String fname,String lname){

        notification = "<p>Dear, Mr/Ms." + lname + ","+"<br>" +
                "<br> You were assigned to training Batch " + batchID + " and will begin Training on May 22, 2022 in Kingston. " +
                " <br><br>Regards," +
                " <br>A.User<p>";
        return (notification);

    }
}
