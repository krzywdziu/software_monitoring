package com.zstwp.mans.alerts;

import com.zstwp.mans.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.InetAddress;
import java.time.LocalDateTime;
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

//
                try { 
                    File file = new File(System.getProperty("user.dir") +
                            "/agent/src/main/java/com/zstwp/mans/alerts/logs.txt");

                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String data = scanner.nextLine();

                        // using pattern with flags
                        Pattern pattern = Pattern.compile("PROTERR", Pattern.CASE_INSENSITIVE);

                        Matcher matcher = pattern.matcher(data);

                        // using Matcher find()
                        while (matcher.find()) {
                            System.out.println("Found the text \"" + matcher.group()
                                    + "\" starting at " + matcher.start()
                                    + " index and ending at index " + matcher.end());
                            
                            System.out.println(data);
                            
                            AlertStatus status = AlertStatus.UNASSIGNED;

                            LocalDateTime time = LocalDateTime .now();

                            AlertSeverity severity = AlertSeverity.ERROR;

                            InetAddress.getLocalHost();

                            AlertDto alert = new AlertDto(data,status,InetAddress.getLocalHost().toString(),severity,time);

                            sendMessage(alert.toString());
                        }
                    }
                    scanner.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 5, TimeUnit.SECONDS);
    }
    
    public void sendMessage(String data) {
        kafkaProducer.writeMessage(data);
        System.out.println("sent");
    }
}
