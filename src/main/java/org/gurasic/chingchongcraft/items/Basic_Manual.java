package org.gurasic.chingchongcraft.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.gurasic.chingchongcraft.cultivation.manuals.Manual;
import org.gurasic.chingchongcraft.cultivation.manuals.Upgrade;

import java.util.ArrayList;
import java.util.List;

public class Basic_Manual extends Item {
    public Manual manual = new Manual("Basic Manual", "The Starter Kits of Cultivation", new ArrayList<>(),1, 1, 1, 1, 1, 1,1, 1);
    public Basic_Manual(Settings settings) {
        super(settings);
        manual.Upgrades.add(new Upgrade("Emerald Infusion", "Your manual feels the energy of the gems", "Adds +1x to Qi Gain", Items.EMERALD, 2, "Diamond Enchantment", "null", -150, -50, 1));
        manual.Upgrades.get(0).setTooltip();
        manual.Upgrades.get(0).Current = true;
        manual.Upgrades.add(new Upgrade("Diamond Enchantment", "The manual gains the power of earth", "Adds +2x to Qi Gain", Items.DIAMOND, 2, "Test3", "Emerald Infusion", -150, -100, 2));
        manual.Upgrades.get(1).setTooltip();
        manual.Upgrades.add(new Upgrade("Test3", "Test3", "Adds +0,3x to Qi Gain", Items.REDSTONE, 10, "null", "Test2", -150, -150, 3));
        manual.Upgrades.get(2).setTooltip();
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
            manual.LoadTooltip(tooltip);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        manual.ManualLogic(user);
        ItemStack heldStack = user.getStackInHand(hand);
        heldStack.setCount(0);
        return TypedActionResult.success(heldStack);
    }
}
