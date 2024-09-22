package org.gurasic.chingchongcraft;

import com.google.common.eventbus.Subscribe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.gurasic.chingchongcraft.cultivation.Cultivation;
import org.gurasic.chingchongcraft.elements.Element;
import org.gurasic.chingchongcraft.keyListener.KeyListener;
import org.gurasic.chingchongcraft.util.ItemRegister;
import org.gurasic.chingchongcraft.util.health;

import java.math.BigDecimal;

public class ChingChongCraft implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("Hewo!!!!!!!! :3 :3 :3 :3");

        Element.initializeRealms();
        FabricLoader.getInstance().getEnvironmentType();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            Events.onHudRenderCallback();
        }
        Events.onPlayerJoin();
        KeyListener.init();
        health.onDamage();
        ItemRegister.initialize();
        Element.HeavenSi = BigDecimal.valueOf(20);
        Element.WaterSi = BigDecimal.valueOf(30);
        Element.MountainSi = BigDecimal.valueOf(10);
        Element.FlameSi = BigDecimal.valueOf(40);
        Element.WindSi = BigDecimal.valueOf(37);
        Element.LakeSi = BigDecimal.valueOf(12);

    }

    public static String NumberToRoman(int number) {
        if (number == 0) return "";
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                roman.append(symbols[i]);
            }
        }
        return roman.toString();
    }
}
