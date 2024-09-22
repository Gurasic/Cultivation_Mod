package org.gurasic.chingchongcraft.keyListener;

import com.lowdragmc.photon.client.fx.BlockEffect;
import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.gurasic.chingchongcraft.Events;
import org.gurasic.chingchongcraft.cultivation.Cultivation;
import org.gurasic.chingchongcraft.cultivation.OveralCultivation;
import org.gurasic.chingchongcraft.gui.CultivationMenu;
import org.gurasic.chingchongcraft.util.ItemChecker;
import org.gurasic.chingchongcraft.util.health;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static com.lowdragmc.photon.client.fx.EntityEffect.CACHE;
import static org.gurasic.chingchongcraft.cultivation.Cultivation.*;

public class KeyListener {
    private static final KeyBinding KEY_H = new KeyBinding("key.h", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_H, "category.h");
    private static final KeyBinding KEY_K = new KeyBinding("key.k", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_K, "category.k");

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null) {
                Cultivation Cultivation = Events.playerList.get(client.player).cult;
                OveralCultivation OE = Events.playerList.get(client.player);
                if (Cultivation.CurrentManual != null && KEY_H.isPressed()) {
                    assert client.player != null;
                    OE.HeldTime++;
                    if (OE.HeldTime % 5 == 0) {
                        Cultivation.Qi = Cultivation.Qi.add(BigDecimal.ONE);
                    }
                    Cultivation.AttemptBreakthru(client.player);
                    ItemChecker.lightNearbyItems(client.player, 3);
                    if (!OE.effectIsRunning) {
                        if (Cultivation.CurrentRealm.effect != null) {
                            OE.hasLooped = true;
                        }
                        OE.effectIsRunning = true;
                    }
                    OE.isHeld = true;
                } else if (KEY_H.isPressed()) {
                    if (Cultivation.CurrentManual == null) {
                        assert client.player != null;
                        client.player.sendMessage(Text.of("§8§oYou need a cultivation manual to be able to cultivate"), false);
                    }
                } else {
                    if (OE.isHeld) {
                        FXHelper.clearCache();
                        OE.isHeld = false;
                        OE.effectIsRunning = false;
                        OE.hasLooped = false;
                    }
                }
                if (KEY_K.isPressed()) {
                    MinecraftClient.getInstance().setScreen(new CultivationMenu(Text.of("Cultivation Menu")));
                }
            }
        });
    }
}

