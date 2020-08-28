package org.redcastlemedia.multitallented.civs.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.redcastlemedia.multitallented.civs.towns.Town;
import org.redcastlemedia.multitallented.civs.towns.TownType;

@Getter
public class TownCreatedEvent extends Event {
    private static final HandlerList hList = new HandlerList();
    private final Town town;
    private final TownType townType;

    public TownCreatedEvent(Town town, TownType townType) {
        this.town = town;
        this.townType = townType;
    }

    public static HandlerList getHandlerList() {
        return hList;
    }

    @Override
    public HandlerList getHandlers() {
        return hList;
    }
}
