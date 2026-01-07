package com.evergreen.EvergreenServer;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EvergreenServerApplication {

    public static void main(String[] args) {

        // trdational java array
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");
        HashMap<String, Integer> newMap = new HashMap<>();
        newMap.put("zhmed", 2123);
        newMap.put("ahmad", 212);
        newMap.put("kabeer", 765654);
        System.out.println("----" + newMap);
        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            System.out.println("key => " + entry.getKey() + " and value =>" + entry.getValue());
        }
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");
        System.out.println("================================================");

        SpringApplication.run(EvergreenServerApplication.class, args);
    }

}
