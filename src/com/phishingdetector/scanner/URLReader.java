package com.phishingdetector.scanner;

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
        String[] testUrls = {
                "http://example.com",
                "https://google.com",
                "invalid-url"
        };

        for(String URL : testUrls){
            System.out.println(URL + " is valid URL" + isValidURL(URL));
        }
    }
}
