package org.gurasic.chingchongcraft.cultivation;

import org.gurasic.chingchongcraft.elements.Element;

public class OveralCultivation {
    public Cultivation cult;
    public Element ele;
    public int HeldTime = 0;
    public boolean isHeld = false;
    public boolean hasLooped = false;
    public boolean effectIsRunning = false;

    public OveralCultivation() {
        cult = new Cultivation();
        ele = new Element();
    }
}
