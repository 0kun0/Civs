package org.redcastlemedia.multitallented.civs.tutorials;

import lombok.Getter;
import lombok.Setter;
import org.redcastlemedia.multitallented.civs.items.CVItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TutorialStep {
    private String type;
    private String region;
    private int times;
    private String killType;
    private ArrayList<CVItem> rewardItems;
    private double rewardMoney;
    private List<String> commands;
    private List<String> permissions;
    private ArrayList<String> paths;

    public TutorialStep() {
        rewardItems = new ArrayList<>();
        paths = new ArrayList<>();
        commands = new ArrayList<>();
        permissions = new ArrayList<>();
    }
}
