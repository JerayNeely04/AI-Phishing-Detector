package com.phishingdetector.model;

public class PhishingClassifier {



    /*
        indicates if the link is phishing or safe
     */
        public static String classify(double[] features){
            double urlLength = features[0];
            double atCount = features[1];
            double dashCount = features[2];
            double suspiciousKeyword = features[3];

            if(atCount > 0 || dashCount > 3 || suspiciousKeyword == 1 || urlLength >75){
                return "Phishing link";
            }else
            {
               return "Safe";
            }

        }



}
