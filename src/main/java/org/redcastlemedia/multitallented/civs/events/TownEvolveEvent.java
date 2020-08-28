package org.redcastlemedia.multitallented.civs.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.redcastlemedia.multitallented.civs.towns.Town;
import org.redcastlemedia.multitallented.civs.towns.TownType;

@Getter
public class TownEvolveEvent extends Event {
    private static final HandlerList hList = new HandlerList();
    private final Town town;
    private final TownType oldTownType;
    private final TownType newTownType;

    public TownEvolveEvent(Town town, TownType oldTownType, TownType newTownType) {
        this.town = town;
        this.oldTownType = oldTownType;
        this.newTownType = newTownType;
    }

    public static HandlerList getHandlerList() {
        return hList;
    }

    @Override
    public HandlerList getHandlers() {
        return hList;
    }
}
