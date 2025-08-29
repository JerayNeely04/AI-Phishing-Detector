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


        double[] features = com.phishingdetector.ai.FeatureExtractor.extractFeatures(url);
        String phishingResults = com.phishingdetector.model.PhishingClassifier.classify(features); //based on conditions returns if safe or phishing

        System.out.println("Features:");
        for(double f : features) {
            System.out.println(f);
        }
        System.out.println("Classification result: " + phishingResults);                        //prints out the results of link being safe or not
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
