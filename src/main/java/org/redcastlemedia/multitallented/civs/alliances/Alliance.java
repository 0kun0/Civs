package org.redcastlemedia.multitallented.civs.alliances;

import lombok.Getter;
import lombok.Setter;
import org.redcastlemedia.multitallented.civs.towns.Town;
import org.redcastlemedia.multitallented.civs.towns.TownManager;

import java.util.HashSet;
import java.util.UUID;

@Getter
@Setter
public class Alliance {
    private String name;
    private HashSet<String> members;
    private UUID lastRenamedBy;

    public Alliance() {
        members = new HashSet<>();
    }

    public boolean isInAlliance(UUID uuid) {
        for (String memberName : members) {
            Town town = TownManager.getInstance().getTown(memberName);
            if (town.getRawPeople().containsKey(uuid)) {
                return true;
            }
        }
        return false;
    }
}
