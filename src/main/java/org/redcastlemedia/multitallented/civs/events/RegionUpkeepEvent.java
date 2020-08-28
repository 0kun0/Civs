package org.redcastlemedia.multitallented.civs.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.redcastlemedia.multitallented.civs.regions.Region;

public class RegionUpkeepEvent extends Event {
    private static final HandlerList hList = new HandlerList();
    private final Region region;
    private final int upkeepIndex;

    public RegionUpkeepEvent(Region region, int upkeepIndex) {
        this.region = region;
        this.upkeepIndex = upkeepIndex;
    }

    public static HandlerList getHandlerList() {
        return hList;
    }

    @Override
    public HandlerList getHandlers() {
        return hList;
    }

    public Region getRegion() {
        return region;
    }

    public int getUpkeepIndex() {
        return upkeepIndex;
    }
}
