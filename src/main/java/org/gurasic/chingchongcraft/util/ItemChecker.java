package org.gurasic.chingchongcraft.util;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.entity.ItemEntity;

import java.util.List;

public class ItemChecker {

    public static void lightNearbyItems(PlayerEntity player, double radius) {
        World world = player.getWorld();
        Box box = new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius,
                player.getX() + radius, player.getY() + radius, player.getZ() + radius);

        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, item -> true);

        for (ItemEntity itemEntity : items) {
            ItemStack itemStack = itemEntity.getStack();
            itemEntity.age = -32768;
            itemEntity.setGlowing(true);
            for (int i = 0; i < 10; i++) {
                double offsetX = (world.random.nextDouble() - 0.5) * 0.2;
                double offsetY = (world.random.nextDouble() - 0.5) * 0.2;
                double offsetZ = (world.random.nextDouble() - 0.5) * 0.2;
                if (i % 5 == 0) {
                    world.addParticle(ParticleTypes.CRIT,
                            itemEntity.getX() + offsetX,
                            (itemEntity.getY() + offsetY) + 0.8,
                            itemEntity.getZ() + offsetZ,
                            0, 0, 0);
                }
            }
        }
    }
    public static List<ItemStack> getNearbyItems(PlayerEntity player, double radius) {
        World world = player.getWorld();
        Box box = new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius,
                player.getX() + radius, player.getY() + radius, player.getZ() + radius);

        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, item -> true);
        return items.stream().map(ItemEntity::getStack).toList();
    }
    public static void deleteNearbyItem(PlayerEntity player, double radius, ItemStack item, int amount) {
        World world = player.getWorld();
        Box box = new Box(player.getX() - radius, player.getY() - radius, player.getZ() - radius,
                player.getX() + radius, player.getY() + radius, player.getZ() + radius);
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, item1 -> true);
        items.stream().filter(itemEntity -> itemEntity.getStack().getItem() == item.getItem() && itemEntity.getStack().getCount() >= amount).forEach(Entity::kill);

    }
}