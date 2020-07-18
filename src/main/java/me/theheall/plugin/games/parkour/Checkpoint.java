package me.theheall.plugin.games.parkour;

import org.bukkit.Location;

public class Checkpoint {

    private Location location;
    private CheckpointType type;
    private int number;

    public Checkpoint(Location location, CheckpointType type, int number) {
        this.location = location;
        this.type = type;
        this.number = number;
    }

    public Location getLocation() {
        return location;
    }

    public CheckpointType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }
}
