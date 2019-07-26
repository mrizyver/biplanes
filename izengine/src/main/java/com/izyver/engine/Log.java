package com.izyver.engine;

public class Log {
    public static final String Error = "ERROR";
    public static final String Warning = "WARNING";
    public static final String Debug = "DEBUG";

    private static final int DEBUG_LEVEL =      0b0000_0001;
    private static final int WARNING_LEVEL =     0b0000_0010;
    private static final int ERROR_LEVEL =      0b0000_0100;

    private static int allowedLog = DEBUG_LEVEL | WARNING_LEVEL | ERROR_LEVEL;

    public static void enableLog(int logLevel){
        allowedLog |= logLevel;
    }

    public static void d(String message){
        if ((allowedLog & DEBUG_LEVEL) > 0){
            log(Debug, message);
        }
    }

    public static void w(String message){
        if ((allowedLog & WARNING_LEVEL) > 0){
            log(Warning, message);
        }
    }

    private static void log(String tag, String message){
        System.out.println(tag + ": " + message);
    }
}
