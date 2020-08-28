package org.redcastlemedia.multitallented.civs.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

class CycleGUI {
    private final UUID uuid;
    private final HashMap<Integer, CycleGUIItemSet> cycleItems;

    CycleGUI(UUID uuid) {
        this.uuid = uuid;
        this.cycleItems = new HashMap<>();
    }

    synchronized void advanceItemPositions() {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null) {
            return;
        }
        try {
            Inventory inventory = player.getOpenInventory().getTopInventory();

            HashMap<Integer, CycleGUI.CycleGUIItemSet> clonedCycleItems = new HashMap<>(cycleItems);
            for (Integer index : clonedCycleItems.keySet()) {
                CycleGUI.CycleGUIItemSet CycleGUIItemSet = clonedCycleItems.get(index);
                int position = CycleGUIItemSet.getPosition();
                int pos = position;
                if (CycleGUIItemSet.getItems().size() - 2 < position) {
                    pos = 0;
                } else {
                    pos++;
                }
                ItemStack is = CycleGUIItemSet.getItems().get(pos).clone();
                inventory.setItem(index, is);
                CycleGUIItemSet.setPosition(pos);
            }
        } catch (Exception e) {

        }
    }

    void addCycleItem(int index, ItemStack is) {
        if (cycleItems.containsKey(index)) {
            cycleItems.get(index).addItem(is);
        } else {
            List<ItemStack> items = new ArrayList<>();
            items.add(is);
            cycleItems.put(index, new CycleGUIItemSet(0, items));
        }
    }

    void putCycleItems(int index, List<ItemStack> items) {
        cycleItems.put(index, new CycleGUIItemSet(0, items));
    }

    private static class CycleGUIItemSet {
        private final List<ItemStack> items;
        private int position;

        CycleGUIItemSet(int position, List<ItemStack> items) {
            this.position = position;
            this.items = items;
        }

        int getPosition() {
            return position;
        }

        void setPosition(int position) {
            this.position = position;
        }

        public List<ItemStack> getItems() {
            return items;
        }

        void addItem(ItemStack is) {
            items.add(is);
        }
    }
}
