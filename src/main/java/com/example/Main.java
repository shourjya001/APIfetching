package com.socgen.dbe;

import org.apache.log4j.FileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomRealTimeAppender extends FileAppender {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String lastFileName1;
    private String lastFileName2;

    @Override
    public void activateOptions() {
        updateFileNames();
        super.activateOptions();
    }

    private void updateFileNames() {
        try {
            String date = dateFormat.format(new Date());

            // Define two different log directories
            File logsDir1 = new File("logs/folder1");
            File logsDir2 = new File("logs/folder2");

            if (!logsDir1.exists()) {
                logsDir1.mkdirs();
            }
            if (!logsDir2.exists()) {
                logsDir2.mkdirs();
            }

            // Define file names for both logs
            String newFileName1 = "logs/folder1/maestrologs_" + date + ".log";
            String newFileName2 = "logs/folder2/maestrologs_" + date + ".log";

            if (!newFileName1.equals(lastFileName1)) {
                lastFileName1 = newFileName1;
                setFile(newFileName1, true, bufferedIO, bufferSize);
            }

            if (!newFileName2.equals(lastFileName2)) {
                lastFileName2 = newFileName2;
                setFile(newFileName2, true, bufferedIO, bufferSize);
            }

        } catch (IOException e) {
            LogLog.error("Error updating log file", e);
        }
    }

    @Override
    protected void subAppend(LoggingEvent event) {
        updateFileNames();
        super.subAppend(event);
        if (qw != null) {
            qw.flush();
        }
    }
}