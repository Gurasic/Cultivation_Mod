package org.gurasic.chingchongcraft.util;

import net.minecraft.text.Text;

import java.math.BigDecimal;

public class health {
    public static BigDecimal PlayerHealth = new BigDecimal("200000000000000000000");
    public static BigDecimal PlayerMaxHealth = new BigDecimal("200000000000000000000");

    public static void onDamage() {
        PlayerHitCallback.EVENT.register((player, source, amount) -> {
            player.setHealth(player.getMaxHealth());
            PlayerHealth = PlayerHealth.subtract(BigDecimal.valueOf((int)amount));
            player.sendMessage(Text.of(PlayerHealth + " / " + PlayerMaxHealth), false);
            if (PlayerHealth.compareTo(BigDecimal.ZERO) <= 0) {
                player.kill();
                PlayerHealth = PlayerMaxHealth;
            }
        });
    }
}
