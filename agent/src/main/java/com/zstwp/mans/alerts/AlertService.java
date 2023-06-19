package com.zstwp.mans.alerts;

import com.zstwp.mans.dto.AlertDto;
import com.zstwp.mans.dto.AlertSeverity;
import com.zstwp.mans.dto.AlertStatus;
import com.zstwp.mans.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AlertService {

    private final KafkaProducer kafkaProducer;

    public void startScanning() {
        System.out.println("test_controller");

         //periodic function; invoking every 5 sec
        Runnable helloRunnable = new Runnable() {
            public void run() {

                try {
                    String configFilePath = System.getProperty("user.dir") + "\\agent\\src\\main\\java\\com\\zstwp\\mans\\config.properties";
                    FileInputStream propsInput = new FileInputStream(configFilePath);
                    Properties prop = new Properties();
                    prop.load(propsInput);
                    File directoryPath = new File(System.getProperty("user.dir") + prop.getProperty("AGENT_LOGS_PATH"));
                    //List of all files and directories
                    File filesList[] = directoryPath.listFiles();
                    System.out.println("List of files and directories in the specified directory:");
                    for(File file : filesList) {
                        System.out.println("File name: "+file.getName());


                        File file_log = new File(System.getProperty("user.dir") + "/agent/src/main/java/com/zstwp/mans/logs/" + file.getName());

                        Scanner myReader = new Scanner(file_log);

                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();

                            // using pattern with flags
                            Pattern pattern_err = Pattern.compile("ERROR", Pattern.CASE_INSENSITIVE);

                            Matcher matcher_err = pattern_err.matcher(data);

                            Pattern pattern_warn = Pattern.compile("WARNING", Pattern.CASE_INSENSITIVE);

                            Matcher matcher_warn = pattern_warn.matcher(data);

                            Pattern pattern_not = Pattern.compile("NOTICE", Pattern.CASE_INSENSITIVE);

                            Matcher matcher_not = pattern_not.matcher(data);

                            Pattern pattern_crit = Pattern.compile("CRITICAL", Pattern.CASE_INSENSITIVE);

                            Matcher matcher_crit = pattern_crit.matcher(data);

                            //TASK multiple patterns in one while

                            // using Matcher find()
                            while (matcher_err.find()) {
                                System.out.println("Found the text \"" + matcher_err.group()
                                        + "\" starting at " + matcher_err.start()
                                        + " index and ending at index " + matcher_err.end());
                                System.out.println(data + "new");

                                //alert to server

                                AlertStatus status = AlertStatus.UNASSIGNED;

                                LocalDateTime time = LocalDateTime .now();

                                AlertSeverity severity = AlertSeverity.ERROR;

                                InetAddress.getLocalHost();

                                AlertDto alert = new AlertDto(data,status,InetAddress.getLocalHost().toString(),severity,time);


                                kafkaProducer.sendMessage(alert);

                            }

                            while (matcher_warn.find()) {
                                System.out.println("Found the text \"" + matcher_warn.group()
                                        + "\" starting at " + matcher_warn.start()
                                        + " index and ending at index " + matcher_warn.end());
                                System.out.println(data + "new");

                                //alert to server

                                AlertStatus status = AlertStatus.UNASSIGNED;

                                LocalDateTime time = LocalDateTime .now();

                                AlertSeverity severity = AlertSeverity.WARNING;

                                InetAddress.getLocalHost();

                                AlertDto alert = new AlertDto(data,status,InetAddress.getLocalHost().toString(),severity,time);

                                kafkaProducer.sendMessage(alert);

                            }

                            while (matcher_not.find()) {
                                System.out.println("Found the text \"" + matcher_not.group()
                                        + "\" starting at " + matcher_not.start()
                                        + " index and ending at index " + matcher_not.end());
                                System.out.println(data + "new");

                                //alert to server

                                AlertStatus status = AlertStatus.UNASSIGNED;

                                LocalDateTime time = LocalDateTime .now();

                                AlertSeverity severity = AlertSeverity.NOTICE;

                                InetAddress.getLocalHost();

                                AlertDto alert = new AlertDto(data,status,InetAddress.getLocalHost().toString(),severity,time);

                                kafkaProducer.sendMessage(alert);

                            }

                            while (matcher_crit.find()) {
                                System.out.println("Found the text \"" + matcher_crit.group()
                                        + "\" starting at " + matcher_crit.start()
                                        + " index and ending at index " + matcher_crit.end());
                                System.out.println(data + "new");

                                //alert to server

                                AlertStatus status = AlertStatus.UNASSIGNED;

                                LocalDateTime time = LocalDateTime .now();

                                AlertSeverity severity = AlertSeverity.WARNING;

                                InetAddress.getLocalHost();

                                AlertDto alert = new AlertDto(data,status,InetAddress.getLocalHost().toString(),severity,time);

                                kafkaProducer.sendMessage(alert);

                            }
                        }
                        myReader.close();
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 60, TimeUnit.SECONDS);
    }
    
    public void sendMessage(AlertDto alert) {
        kafkaProducer.sendMessage(alert);
        System.out.println("alert sent");
    }
}
