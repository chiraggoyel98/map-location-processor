package org.example.services;

import org.example.models.Location;
import org.example.models.MergedLocation;
import org.example.models.Metadata;

import java.util.*;
import java.util.stream.Collectors;

public class DataProcessor {
    private List<MergedLocation> mergedLocations;

    public DataProcessor(List<Location> locations, List<Metadata> metadataList) {
        Map<String, Metadata> metadataMap = metadataList.stream()
                .collect(Collectors.toMap(Metadata::getId, meta -> meta));

        this.mergedLocations = locations.stream()
                .map(loc -> new MergedLocation(loc, metadataMap.get(loc.getId())))
                .collect(Collectors.toList());
    }

    public Map<String, Long> countLocationsByType() {
        return mergedLocations.stream()
                .filter(loc -> !this.isIncomplete(loc))
                .collect(Collectors.groupingBy(loc -> loc.getMetadata().getType(), Collectors.counting()));
    }

    public Map<String, Double> averageRatingByType() {
        Map<String, DoubleSummaryStatistics> stats = mergedLocations.stream()
                .filter(loc -> !this.isIncomplete(loc))
                .collect(Collectors.groupingBy(
                        loc -> loc.getMetadata().getType(),
                        Collectors.summarizingDouble(loc -> loc.getMetadata().getRating())
                ));

        return stats.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getAverage()));
    }

    public MergedLocation findMostReviewedLocation() {
        return mergedLocations.stream()
                .filter(loc -> !this.isIncomplete(loc))
                .max(Comparator.comparingInt(loc -> loc.getMetadata().getReviews()))
                .orElse(null);
    }

    public List<MergedLocation> findIncompleteData() {
        return mergedLocations.stream()
                .filter(this::isIncomplete)
                .collect(Collectors.toList());
    }

    private boolean isIncomplete(MergedLocation loc) {
        if (loc.getMetadata() == null) {
            return true; // No metadata at all
        }
        Metadata metadata = loc.getMetadata();
        return metadata.getType() == null || metadata.getRating() == null || metadata.getReviews() == null;
    }
}
