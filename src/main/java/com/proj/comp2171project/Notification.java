package com.proj.comp2171project;

import java.util.Date;

public class Notification {

    private String batchID;
    private String fname;
    private String lname;
    private Date trainingDate;
    private static String notification;

    public static String generateBatchNotification(String batchID,String fname,String lname){

        notification = "Dear, Mr/Ms." + lname +
                " You were assigned to training Batch " + batchID + " and will begin Training on May 22, 2022 in Kingston " +
                " Regards," +
                " A.Walsh";
        return (notification);

    }
}
