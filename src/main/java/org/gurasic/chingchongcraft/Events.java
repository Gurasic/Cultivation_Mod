package org.gurasic.chingchongcraft;

import com.lowdragmc.photon.client.fx.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import org.gurasic.chingchongcraft.cultivation.Cultivation;
import org.gurasic.chingchongcraft.cultivation.OveralCultivation;
import org.gurasic.chingchongcraft.elements.Element;
import org.gurasic.chingchongcraft.keyListener.KeyListener;
import org.gurasic.chingchongcraft.util.health;

import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;



public class Events {
    public static Dictionary<PlayerEntity, OveralCultivation> playerList = new Hashtable<>();
    public static void onHudRenderCallback() {
        HudRenderCallback.EVENT.register((context, tickDeltaManager) -> {
            // Cultivation Logic + Progress Bar Rendering Logic
            int progressBarWidth = 96;
            int progressBarHeight = 32;
            int x = 5;
            int y = 300;

            Cultivation Cultivation = playerList.get(MinecraftClient.getInstance().player).cult;
            int maxValue = Cultivation.CurrentRealm.requiredQi.intValue();
            if(Cultivation.CurrentSubRealm != 1) {
                maxValue = Cultivation.CurrentRealm.requiredQi.intValue() * (int) (Cultivation.CurrentSubRealm * 1.03);
            }
            int currentValue = Cultivation.Qi.intValue();

            int fillAmount = (int) ((currentValue / (float) maxValue) * progressBarWidth);

            context.drawTexture(new Identifier("chingchongcraft", "textures/purple_piss_spirte_bottle.png"), x, y, 0, 0, fillAmount, progressBarHeight, progressBarWidth, progressBarHeight);

            // Health Bar Rendering Logic
            Identifier health_empty = new Identifier("chingchongcraft", "textures/health/health_empty.png");
            Identifier health_full = new Identifier("chingchongcraft", "textures/health/health_full.png");
            int healthBarWidth = 80;
            int healthBarHeight = 9;
            int healthBarX = 230;
            int healthBarY = 295;
            int healthBarFillAmount = (int) ((health.PlayerHealth.floatValue() / health.PlayerMaxHealth.floatValue()) * healthBarWidth);
            context.drawTexture(health_empty, healthBarX, healthBarY, 0, 0, healthBarWidth, healthBarHeight, healthBarWidth, healthBarHeight);
            context.drawTexture(health_full, healthBarX, healthBarY, 0, 0, healthBarFillAmount, healthBarHeight, healthBarWidth, healthBarHeight);
            String healthText = formatHealthText(health.PlayerHealth, health.PlayerMaxHealth);
            MinecraftClient client = MinecraftClient.getInstance();
            int textWidth = client.textRenderer.getWidth(Text.of(healthText));
            context.drawText(client.textRenderer, healthText, healthBarX + (healthBarWidth / 2) - (textWidth / 2), (healthBarY - 1) + (healthBarHeight / 4), 0xFFFFFF, false);

            // Element Bar Rendering Logic
            Identifier Element_Empty = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_empty.png");
            Identifier Heaven_Bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_heaven.png");
            Identifier Earth_Bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_earth.png");
            Identifier Lake_bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_lake.png");
            Identifier Wind_Bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_wind.png");
            Identifier Flame_bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_flame.png");
            Identifier Thunder_bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_thunder.png");
            Identifier Water_Bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_water.png");
            Identifier Mountain_bar = new Identifier("chingchongcraft", "textures/gui/elements/element_bar_mountain.png");


            int ElementBarWidth = 8;
            int ElementBarHeight = 20;
            int ElementBarX = 147;
            int ElementBarY = 313;
            int HeavenBarFillAmount = (int) ((Element.HeavenSi.floatValue() / Element.CurrentHeavenRealm.requiredQi.floatValue()) * ElementBarHeight);
            int EarthBarFillAmount = (int) ((Element.EarthSi.floatValue() / Element.CurrentEarthRealm.requiredQi.floatValue()) * ElementBarHeight);
            int LakeBarFillAmount = (int) ((Element.WaterSi.floatValue() / Element.CurrentLakeRealm.requiredQi.floatValue()) * ElementBarHeight);
            int WindFillAmount = (int) ((Element.WindSi.floatValue() / Element.CurrentWindRealm.requiredQi.floatValue()) * ElementBarHeight);
            int FlameBarFillAmount = (int) ((Element.FlameSi.floatValue() / Element.CurrentFlameRealm.requiredQi.floatValue()) * ElementBarHeight);
            int ThunderBarFillAmount = (int) ((Element.ThunderSi.floatValue() / Element.CurrentThunderRealm.requiredQi.floatValue()) * ElementBarHeight);
            int WaterFillAmount = (int) ((Element.WaterSi.floatValue() / Element.CurrentWaterRealm.requiredQi.floatValue()) * ElementBarHeight);
            int MountainFillAmount = (int) ((Element.MountainSi.floatValue() / Element.CurrentMountainRealm.requiredQi.floatValue()) * ElementBarHeight);

            context.drawTexture(Heaven_Bar, ElementBarX, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - HeavenBarFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Earth_Bar, ElementBarX + 10, ElementBarY, 0, 0, ElementBarWidth,  ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 10, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - EarthBarFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Lake_bar, ElementBarX + 20, ElementBarY, 0, 0, ElementBarWidth,  ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 20, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - LakeBarFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Wind_Bar, ElementBarX + 30, ElementBarY, 0, 0, ElementBarWidth,  ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 30, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - WindFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Flame_bar, ElementBarX + 40, ElementBarY, 0, 0, ElementBarWidth,  ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 40, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - FlameBarFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Thunder_bar, ElementBarX + 50, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 50, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - ThunderBarFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Water_Bar, ElementBarX + 60, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 60, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - WaterFillAmount, ElementBarWidth, ElementBarHeight);

            context.drawTexture(Mountain_bar, ElementBarX + 70, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight, ElementBarWidth, ElementBarHeight);
            context.drawTexture(Element_Empty, ElementBarX + 70, ElementBarY, 0, 0, ElementBarWidth, ElementBarHeight - MountainFillAmount, ElementBarWidth, ElementBarHeight);


        });

    }


    public static void onPlayerJoin() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            playerList.put(client.player, new OveralCultivation());
            // FX QiCondensingFx = FXHelper.getFX(new Identifier("chingchongcraft", "effects/qi_condensing"));
            // EntityEffect QiCondensingEE = new EntityEffect(QiCondensingFx, client.world, client.player);
            // playerList.get(client.player).cult.Realms.get(0).setEffect(QiCondensingEE);
        });
    }


    // Health Related Methds ||| NOT EVENTS
    private static String formatHealthText(BigDecimal health, BigDecimal maxHealth) {
        String healthStr = formatNumber(health);
        String maxHealthStr = formatNumber(maxHealth);
        return healthStr + "/" + maxHealthStr;
    }

    private static String formatNumber(BigDecimal number) {
        if (number.compareTo(BigDecimal.valueOf(1000000000000000000L)) >= 0) {
            return formatNumberWithSuffix(number, "Qn");
        } else if (number.compareTo(BigDecimal.valueOf(1000000000000000L)) >= 0) {
            return formatNumberWithSuffix(number, "Qd");
        } else if (number.compareTo(BigDecimal.valueOf(1000000000000L)) >= 0) {
            return formatNumberWithSuffix(number, "T");
        } else if (number.compareTo(BigDecimal.valueOf(1000000000L)) >= 0) {
            return formatNumberWithSuffix(number, "B");
        } else if (number.compareTo(BigDecimal.valueOf(1000000L)) >= 0) {
            return formatNumberWithSuffix(number, "M");
        } else if (number.compareTo(BigDecimal.valueOf(1000L)) >= 0) {
            return formatNumberWithSuffix(number, "K");
        } else {
            return number.toString();
        }
    }

    private static String formatNumberWithSuffix(BigDecimal number, String suffix) {
        BigDecimal suffixValue;
        switch (suffix) {
            case "K":
                suffixValue = BigDecimal.valueOf(1000);
                break;
            case "M":
                suffixValue = BigDecimal.valueOf(1000000);
                break;
            case "B":
                suffixValue = BigDecimal.valueOf(1000000000);
                break;
            case "T":
                suffixValue = BigDecimal.valueOf(1000000000000L);
                break;
            case "Qd":
                suffixValue = BigDecimal.valueOf(1000000000000000L);
                break;
            case "Qn":
                suffixValue = BigDecimal.valueOf(1000000000000000000L);
                break;
            default:
                throw new IllegalArgumentException("Unsupported suffix");
        }
        BigDecimal valueWithoutSuffix = number.divide(suffixValue);
        return String.format("%s%s", valueWithoutSuffix, suffix);
    }
    // --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
}
