package org.redcastlemedia.multitallented.civs.regions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RegionBlockCheckResponse {
    private final RegionPoints regionPoints;
    private final List<HashMap<Material, Integer>> missingItems;
}
