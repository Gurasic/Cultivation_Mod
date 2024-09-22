package org.gurasic.chingchongcraft.cultivation;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.gurasic.chingchongcraft.ChingChongCraft;
import org.gurasic.chingchongcraft.Events;
import org.gurasic.chingchongcraft.cultivation.manuals.Manual;
import org.gurasic.chingchongcraft.keyListener.KeyListener;
import org.gurasic.chingchongcraft.util.ItemChecker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cultivation {
    public BigDecimal Qi = BigDecimal.ZERO;
    public Realm CurrentRealm = new Realm("Mortal",0, BigDecimal.valueOf(50), "Qi Condensing");
    public List<Realm> Realms = new ArrayList<>();
    public Manual CurrentManual = null;
    public int CurrentSubRealm = 1;
    public Cultivation() {

    }
    public  void initializeRealms() {
        Realms.add(new Realm("Qi Condensing",10, BigDecimal.valueOf(70), "Qi Darko"));
        Realms.add(new Realm("Qi-Enhanced Mortal",10, BigDecimal.valueOf(700), "Martial Initiate"));
        Realms.add(new Realm("Qi Condensing",10, BigDecimal.valueOf(70), "Qi Darko"));
    }

    public Realm getBaseRealmsByName(String name){
        for(Realm realm : Realms) {
            if (realm.name.equals(name)) {
                return realm;
            }
        }
        return new Realm("Mortal",0, BigDecimal.valueOf(50), "Qi Condensing");
    }


    public void AttemptBreakthru(PlayerEntity user) {
        int maxValue = CurrentRealm.requiredQi.intValue();
        if(CurrentSubRealm != 1) {
            maxValue = CurrentRealm.requiredQi.intValue() * (int) (CurrentSubRealm * 1.03);
        }
        if (Qi.compareTo(BigDecimal.valueOf(maxValue)) >= 0) {
            if (CurrentSubRealm >= CurrentRealm.Subrealms) {
                CurrentRealm = getBaseRealmsByName(CurrentRealm.next);
                CurrentSubRealm = 1;
                MinecraftClient.getInstance().player.sendMessage(Text.of("Congratulations You broke into the " + CurrentRealm.name + " " + ChingChongCraft.NumberToRoman(CurrentSubRealm) + " Realm"), false);
            } else {
                CurrentSubRealm++;
                MinecraftClient.getInstance().player.sendMessage(Text.of("Congratulations You broke into the " + CurrentRealm.name + " " + ChingChongCraft.NumberToRoman(CurrentSubRealm) + " Realm"), false);
            }
            if (ItemChecker.getNearbyItems(MinecraftClient.getInstance().player, 3) != null) {
                List<ItemStack> items = ItemChecker.getNearbyItems(MinecraftClient.getInstance().player, 3);
                for (int i = 0; i < CurrentManual.Upgrades.toArray().length; i++) {
                    for (int e = 0; e < items.toArray().length; e++) {
                        if (items.get(e).getItem() == CurrentManual.Upgrades.get(i).Cost) {
                            if (items.get(e).getCount() >= CurrentManual.Upgrades.get(i).Amount && CurrentManual.Upgrades.get(i).Current) {
                                ItemChecker.deleteNearbyItem(MinecraftClient.getInstance().player, 3, items.get(e), CurrentManual.Upgrades.get(i).Amount);
                                CurrentManual.Upgrades.get(i).Completed = true;
                                CurrentManual.Upgrades.get(i).Current = false;
                                CurrentManual.GetNextUpgrade(CurrentManual.Upgrades.get(i).Next).Current = true;
                                CurrentManual.Multi += CurrentManual.Upgrades.get(i).QiMulti;
                                MinecraftClient.getInstance().player.sendMessage(Text.of("Congratulations!! The Manual received the " +  CurrentManual.Upgrades.get(i).Name + " Upgrade"), false);
                            }
                        }
                    }
                }

            }
            Events.playerList.get(user).HeldTime = 0;
            Qi = BigDecimal.ZERO;
        }
    }
}
