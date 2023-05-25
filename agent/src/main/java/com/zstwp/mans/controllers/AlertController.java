package com.zstwp.mans.controllers;

import com.zstwp.mans.kafka.KafkaProducer;
import com.zstwp.mans.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
public class AlertController {
    //private final AlertService alertService;
    private final KafkaProducer kafkaProducer;

    /*
    @PostMapping("/alert")
    public void writeMessageToTopic(@RequestParam("message") String message) {

        this.alertService.alertMessage(message);
    }*/



    @PostMapping("/alert")
    public void main(String[] args) {
        System.out.println("test_controller");

        //periodic function; invoking every 5 sec
        Runnable helloRunnable = new Runnable() {
            public void run() {

                try {
                    String filePath = "C:\\Users\\Patryk\\Studia\\Semestr 6\\sieci_praktyce\\com\\agent\\src\\main\\java\\com\\zstwp\\mans\\controllers\\logs.txt";

                    File file = new File(filePath);

                    Scanner myReader = new Scanner(file);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();

                        // using pattern with flags
                        Pattern pattern = Pattern.compile("PROTERR", Pattern.CASE_INSENSITIVE);

                        Matcher matcher = pattern.matcher(data);

                        //TASK multiple patterns in one while

                        // using Matcher find()
                        while (matcher.find()) {
                            System.out.println("Found the text \"" + matcher.group()
                                    + "\" starting at " + matcher.start()
                                    + " index and ending at index " + matcher.end());
                            System.out.println(data);

                            //call to backend alert function

                            kafkaProducer.writeMessage(data);

                        }
                    }
                    myReader.close();
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 5, TimeUnit.SECONDS);
    }

}
