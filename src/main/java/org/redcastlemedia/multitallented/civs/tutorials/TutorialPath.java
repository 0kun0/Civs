package org.redcastlemedia.multitallented.civs.tutorials;

import lombok.Getter;
import lombok.Setter;
import org.redcastlemedia.multitallented.civs.items.CVItem;

import java.util.ArrayList;

@Getter
@Setter
public class TutorialPath {
    private CVItem icon;
    private ArrayList<TutorialStep> steps;

    public TutorialPath() {
        steps = new ArrayList<>();
    }
}
