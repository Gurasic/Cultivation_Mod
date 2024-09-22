package org.gurasic.chingchongcraft.cultivation.manuals;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class Upgrade {
    public String Name;
    public String Description;
    public String Effect;
    public Item Cost;
    public int Amount;
    public int X;
    public int Y;
    public String Next;
    public String Previous;
    public List<Text> tooltip;
    public boolean Completed;
    public boolean Current;
    public int QiMulti;

    public Upgrade(String name, String description, String effect,Item cost, int amount, String next, String previous, int x, int y, int qiMulti) {
        this.Name = name;
        this.Description = description;
        this.Cost = cost;
        this.Amount = amount;
        this.Next = next;
        this.Previous = previous;
        this.Effect = effect;
        this.X = x;
        this.Y = y;
        Completed = false;
        QiMulti = qiMulti;
    }
    public void setTooltip() {
        List<Text> tooltip = new ArrayList<>();
        tooltip.add(Text.of("§f§l§n" + this.Name+ " Upgrade"));
        tooltip.add(Text.of("§8--------------------"));
        tooltip.add(Text.of("§a§lCost:§r§a " + this.Amount + "x"));
        tooltip.add(Text.of("§c§l" + this.Effect));
        tooltip.add(Text.of("§8--------------------"));
        tooltip.add(Text.of("§8§o" + this.Description));
        this.tooltip = tooltip;
    }

}
