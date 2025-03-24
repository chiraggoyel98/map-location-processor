package org.example.models;

public class MergedLocation {
    private Location location;
    private Metadata metadata;

    public MergedLocation(Location location, Metadata metadata) {
        this.location = location;
        this.metadata = metadata;
    }

    public Location getLocation() { return location; }
    public Metadata getMetadata() { return metadata; }
}
