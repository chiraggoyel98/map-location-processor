package org.example;


import org.example.models.Location;
import org.example.models.MergedLocation;
import org.example.models.Metadata;
import org.example.services.DataProcessor;
import org.example.utils.JSONReader;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("<<<<<<<< LOCATION MAP DATA PROCESSOR >>>>>>>> ");
        List<Location> locations = JSONReader.readJson("data/locations.json", Location[].class);
        List<Metadata> metadataList = JSONReader.readJson("data/metadata.json", Metadata[].class);

        DataProcessor processor = new DataProcessor(locations, metadataList);

        Map<String, Long> typeCounts = processor.countLocationsByType();
        System.out.println("Location Counts by Type: " + typeCounts);

        Map<String, Double> avgRatings = processor.averageRatingByType();
        System.out.println("Average Ratings by Type: " + avgRatings);

        MergedLocation mostReviewed = processor.findMostReviewedLocation();
        if (mostReviewed != null) {
            System.out.println("Most Reviewed Location: " + mostReviewed.getMetadata().getId() +
                    " with " + mostReviewed.getMetadata().getReviews() + " reviews");
        }

        List<MergedLocation> incompleteData = processor.findIncompleteData();
        System.out.println("Incomplete Data Locations: " + incompleteData.size());
    }
}
