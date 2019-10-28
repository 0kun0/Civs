package org.redcastlemedia.multitallented.civs.menus.regions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.redcastlemedia.multitallented.civs.civilians.Civilian;
import org.redcastlemedia.multitallented.civs.items.CVItem;
import org.redcastlemedia.multitallented.civs.items.ItemManager;
import org.redcastlemedia.multitallented.civs.menus.CustomMenu;
import org.redcastlemedia.multitallented.civs.menus.MenuIcon;
import org.redcastlemedia.multitallented.civs.menus.MenuManager;
import org.redcastlemedia.multitallented.civs.regions.RegionType;
import org.redcastlemedia.multitallented.civs.towns.TownType;

public class RegionTypeListMenu extends CustomMenu {
    @Override
    public Map<String, Object> createData(Civilian civilian, Map<String, String> params) {
        Map<String, Object> data = new HashMap<>();

        if (params.containsKey("page")) {
            data.put("page", Integer.parseInt(params.get("page")));
        } else {
            data.put("page", 0);
        }
        String category = params.get("category");
        String townName = params.get("town");
        HashMap<String, Integer> regionTypes;
        if (category == null || townName == null) {
            regionTypes = new HashMap<>();
        } else if (category.equals("reqs")) {
            TownType townType = (TownType) ItemManager.getInstance().getItemType(townName);
            regionTypes = new HashMap<>(townType.getReqs());
        } else if (category.equals("limits")) {
            TownType townType = (TownType) ItemManager.getInstance().getItemType(townName);
            regionTypes = new HashMap<>(townType.getRegionLimits());
        } else {
            regionTypes = new HashMap<>();
        }
        data.put("regionTypes", regionTypes);

        int maxPage = (int) Math.ceil((double) regionTypes.size() / (double) itemsPerPage.get("regions"));
        maxPage = maxPage > 0 ? maxPage - 1 : 0;
        data.put("maxPage", maxPage);

        for (String key : params.keySet()) {
            if (key.equals("page") || key.equals("maxPage")) {
                continue;
            }
            data.put(key, params.get(key));
        }

        return data;
    }

    @Override
    protected ItemStack createItemStack(Civilian civilian, MenuIcon menuIcon, int count) {
        if (menuIcon.getKey().equals("regionTypes")) {
            HashMap<String, Integer> regionTypes;
            if (MenuManager.getData(civilian.getUuid(), "regionTypes") != null) {
                regionTypes = (HashMap<String, Integer>) MenuManager.getData(civilian.getUuid(), "regionTypes");
            } else {
                return new ItemStack(Material.AIR);
            }
            ArrayList<CVItem> fullListRegionTypes = new ArrayList<>();
            for (String regionTypeName : regionTypes.keySet()) {
                RegionType regionType = (RegionType) ItemManager.getInstance().getItemType(regionTypeName);
                CVItem currentItem = regionType.getShopIcon().clone();
                currentItem.setQty(regionTypes.get(regionTypeName));
                fullListRegionTypes.add(currentItem);
            }
            int page = (int) MenuManager.getData(civilian.getUuid(), "page");
            int startIndex = page * menuIcon.getIndex().size();
            if (fullListRegionTypes.size() <= startIndex + count) {
                return new ItemStack(Material.AIR);
            }
            CVItem cvItem = fullListRegionTypes.get(startIndex + count);
            ItemStack itemStack = cvItem.createItemStack();
            putActions(civilian, menuIcon, itemStack, count);
            return itemStack;
        }
        return super.createItemStack(civilian, menuIcon, count);
    }

    @Override
    public String getFileName() {
        return "region-type-list";
    }
}
