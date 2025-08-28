package com.phishingdetector.ai;

public class FeatureExtractor {

    public static double[] extractFeatures(String url) {
        double[] features = new double[4];

        // Feature 1: URL length
        features[0] = url.length();

        // Feature 2: Number of '@' symbols
        features[1] = url.chars().filter(c -> c == '@').count();

        // Feature 3: Number of '-' symbols
        features[2] = url.chars().filter(c -> c == '-').count();

        // Feature 4: Suspicious keywords
        features[3] = (url.contains("login") || url.contains("verify") || url.contains("bank")) ? 1 : 0;

        return features;
    }
}
