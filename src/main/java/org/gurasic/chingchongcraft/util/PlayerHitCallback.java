package org.gurasic.chingchongcraft.util;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public interface PlayerHitCallback {
    Event<PlayerHitCallback> EVENT = EventFactory.createArrayBacked(PlayerHitCallback.class,
            (listeners) -> (player, source, amount) -> {
                for (PlayerHitCallback listener : listeners) {
                    listener.onHit(player, source, amount);
                }
            });

    void onHit(PlayerEntity player, DamageSource source, float amount);
}
