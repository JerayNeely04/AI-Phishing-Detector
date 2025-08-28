package com.phishingdetector.scanner;

import com.phishingdetector.ai.FeatureExtractor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class URLReader {

    public static boolean isValidURL(String url){
        try{
            new java.net.URL(url).toURI();              //creates a new URL object that uses
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //Testing the method
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);                               //opens scanner for user input
        System.out.println("Enter a URL:");
        String url = scanner.nextLine();                                        //reads the users input

        if(URLReader.isValidURL(url)){                                          //if URL is valid return true
            System.out.println("URL is valid!");
            String html = URLReader.fetchContent(url);                          //fetch the contents of the URL
            System.out.println("HTML length: " + html.length());
        } else {
            System.out.println("URL is invalid!");
        }

        String tempURL = "https://app.dataannotation.tech/workers/tasks/35e99021-7167-40be-b2c3-364aa4abf455?task_response_id=4bcea47e-363c-421e-99fa-7ead2aea68a5";
        double[] features = FeatureExtractor.extractFeatures(url);
        System.out.println("Features:");
        for(double f : features) {
            System.out.println(f);
        }
    }

    public static String fetchContent(String urlStr){
        StringBuilder content = new StringBuilder();   //stores the HTML
        try{
            java.net.URL url = new URL(urlStr);                                     // create URL object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();      // open connection to website
            conn.setRequestMethod("GET");                                           // HTTP GET request to webpage needed


            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));   //reads the input line by line
            String inputLine;
            while((inputLine = in.readLine()) != null){
                content.append(inputLine);
            }
            in.close();                                                             //closes the stream to free resources

        }catch(Exception e){
            System.out.println("Error fetching URL: " + e.getMessage());           //return error message
        }
        return content.toString();
    }

}
