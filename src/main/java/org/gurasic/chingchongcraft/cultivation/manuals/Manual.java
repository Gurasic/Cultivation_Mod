package org.gurasic.chingchongcraft.cultivation.manuals;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.gurasic.chingchongcraft.Events;
import org.gurasic.chingchongcraft.cultivation.Cultivation;
import org.gurasic.chingchongcraft.cultivation.Realm;

import java.util.List;

public class Manual {
    public String Name;
    public String Description;
    public List<Upgrade> Upgrades;
    public int Tier;
    public int Multi;
    public int EarthMulti;
    public int FireMulti;
    public int WaterMulti;
    public int WindMulti;
    public int LightMulti;
    public int DarkMulti;
    public Manual(String name, String description, List<Upgrade> upgrades, int tier, int multi, int earthMulti, int fireMulti, int waterMulti, int windMulti, int lightMulti, int darkMulti) {
        this.Upgrades = upgrades;
        this.Name = name;
        this.Description = description;
        this.Multi = multi;
        this.EarthMulti = earthMulti;
        this.FireMulti = fireMulti;
        this.WaterMulti = waterMulti;
        this.WindMulti = windMulti;
        this.LightMulti = lightMulti;
        this.DarkMulti = darkMulti;
        this.Tier = tier;
    }
    public void ManualLogic(PlayerEntity player) {
        Events.playerList.get(player).cult.CurrentManual = this;
    }

    public void LoadTooltip(List<Text> tooltip) {
        tooltip.add(Text.of("§f§l§nTier " + this.Tier + " Manual"));
        tooltip.add(Text.of("§8--------------------"));
        tooltip.add(Text.of("§dQi Gain Multi: " + this.Multi + "x"));
        tooltip.add(Text.of("§8--------------------"));;
        tooltip.add(Text.of("§a§lEarth§r§a Multi: " + this.EarthMulti + "x"));
        tooltip.add(Text.of("§c§lFire§r§c Multi: " + this.FireMulti + "x"));
        tooltip.add(Text.of("§b§lWater§r§b Multi: " + this.WaterMulti + "x"));
        tooltip.add(Text.of("§e§lWind§r§e Multi: " + this.WindMulti + "x"));
        tooltip.add(Text.of("§f§lLight§r§f Multi: " + this.LightMulti + "x"));
        tooltip.add(Text.of("§7§lDark§r§7 Multi: " + this.DarkMulti + "x"));
        tooltip.add(Text.of("§8--------------------"));
        tooltip.add(Text.of("§8§o" + this.Description));
    }
    public Upgrade GetNextUpgrade(String name) {
            for(Upgrade upgrade : Upgrades) {
                if (upgrade.Name.equals(name)) {
                    return upgrade;
                }
            }
        return null;
    }
    public static List<Text> getTooltip(Manual manual) {
        List<Text> tooltip = new java.util.ArrayList<>();
        tooltip.add(Text.of("§f§l§nTier " + manual.Tier + " Manual"));
        tooltip.add(Text.of("§8--------------------"));
        tooltip.add(Text.of("§dQi Gain Multi: " + manual.Multi + "x"));
        tooltip.add(Text.of("§8--------------------"));;
        tooltip.add(Text.of("§a§lEarth§r§a Multi: " + manual.EarthMulti + "x"));
        tooltip.add(Text.of("§c§lFire§r§c Multi: " + manual.FireMulti + "x"));
        tooltip.add(Text.of("§b§lWater§r§b Multi: " + manual.WaterMulti + "x"));
        tooltip.add(Text.of("§e§lWind§r§e Multi: " + manual.WindMulti + "x"));
        tooltip.add(Text.of("§f§lLight§r§f Multi: " + manual.LightMulti + "x"));
        tooltip.add(Text.of("§7§lDark§r§7 Multi: " + manual.DarkMulti + "x"));
        tooltip.add(Text.of("§8--------------------"));
        tooltip.add(Text.of("§8§o" + manual.Description));
        return tooltip;
    }
}
