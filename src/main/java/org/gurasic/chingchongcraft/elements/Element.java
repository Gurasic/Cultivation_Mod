package org.gurasic.chingchongcraft.elements;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.gurasic.chingchongcraft.ChingChongCraft;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Element {
    public static BigDecimal HeavenSi = BigDecimal.ZERO;
    public static BigDecimal LakeSi = BigDecimal.ZERO;
    public static BigDecimal FlameSi = BigDecimal.ZERO;
    public static BigDecimal ThunderSi = BigDecimal.ZERO;
    public static BigDecimal WindSi = BigDecimal.ZERO;
    public static BigDecimal WaterSi = BigDecimal.ZERO;
    public static BigDecimal MountainSi = BigDecimal.ZERO;
    public static BigDecimal EarthSi = BigDecimal.ZERO;
    public static RealmElement CurrentHeavenRealm = getRealmWithName(0);
    public static RealmElement CurrentLakeRealm = getRealmWithName(1);
    public static RealmElement CurrentFlameRealm = getRealmWithName(2);
    public static RealmElement CurrentThunderRealm = getRealmWithName(3);
    public static RealmElement CurrentWindRealm = getRealmWithName(4);
    public static RealmElement CurrentWaterRealm = getRealmWithName(5);
    public static RealmElement CurrentMountainRealm = getRealmWithName(6);
    public static RealmElement CurrentEarthRealm = getRealmWithName(7);

    public static int CurrentHeavenSubRealm = 1;
    public static int CurrentLakeSubRealm = 1;
    public static int CurrentFlameSubRealm = 1;
    public static int CurrentThunderSubRealm = 1;
    public static int CurrentWindSubRealm = 1;
    public static int CurrentWaterSubRealm = 1;
    public static int CurrentMountainSubRealm = 1;
    public static int CurrentEarthSubRealm = 1;

    public static List<RealmElement> Realms = new ArrayList<>();


    public static void initializeRealms() {
        Realms.add(new RealmElement("Mid Element", "Half-Decent", 10, BigDecimal.valueOf(70)));
        Realms.add(new RealmElement("Half-Decent Element", "null", 3, BigDecimal.valueOf(700)));

    }

    public static void attemptElementBreakthrough() {
        breakthrough(HeavenSi, CurrentHeavenRealm.requiredQi, CurrentHeavenRealm.Subrealms, CurrentHeavenSubRealm, CurrentHeavenRealm.next, 0, CurrentHeavenRealm.name);
        breakthrough(LakeSi, CurrentLakeRealm.requiredQi, CurrentLakeRealm.Subrealms, CurrentLakeSubRealm, CurrentLakeRealm.next, 1, CurrentLakeRealm.name);
        breakthrough(FlameSi, CurrentFlameRealm.requiredQi, CurrentFlameRealm.Subrealms, CurrentFlameSubRealm, CurrentFlameRealm.next, 2, CurrentFlameRealm.name);
        breakthrough(ThunderSi, CurrentThunderRealm.requiredQi, CurrentThunderRealm.Subrealms, CurrentThunderSubRealm, CurrentThunderRealm.next, 3, CurrentThunderRealm.name);
        breakthrough(WindSi, CurrentWindRealm.requiredQi, CurrentWindRealm.Subrealms, CurrentWindSubRealm, CurrentWindRealm.next, 4, CurrentWindRealm.name);
        breakthrough(WaterSi, CurrentWaterRealm.requiredQi, CurrentWaterRealm.Subrealms, CurrentWaterSubRealm, CurrentWaterRealm.next, 5, CurrentWaterRealm.name);
        breakthrough(MountainSi, CurrentMountainRealm.requiredQi, CurrentMountainRealm.Subrealms, CurrentMountainSubRealm, CurrentMountainRealm.next, 6, CurrentMountainRealm.name);
        breakthrough(EarthSi, CurrentEarthRealm.requiredQi, CurrentEarthRealm.Subrealms, CurrentEarthSubRealm, CurrentEarthRealm.next, 7, CurrentEarthRealm.name);
    }
    public static void breakthrough(BigDecimal qi, BigDecimal requiredqi, int maxSubrealm, int subrealms, String next, int id, String realmName) {
        if (qi.compareTo(requiredqi) >= 0) {
            if (subrealms >= maxSubrealm) {
                switch (id) {
                    case 0: HeavenSi = BigDecimal.ZERO; CurrentHeavenRealm = getbyName(next); break;
                    case 1: LakeSi = BigDecimal.ZERO; CurrentLakeRealm = getbyName(next); break;
                    case 2: FlameSi = BigDecimal.ZERO; CurrentFlameRealm = getbyName(next); break;
                    case 3: ThunderSi = BigDecimal.ZERO; CurrentThunderRealm = getbyName(next); break;
                    case 4: WindSi = BigDecimal.ZERO; CurrentWindRealm = getbyName(next); break;
                    case 5: WaterSi = BigDecimal.ZERO; CurrentWaterRealm = getbyName(next); break;
                    case 6: MountainSi = BigDecimal.ZERO; CurrentMountainRealm = getbyName(next); break;
                    case 7: EarthSi = BigDecimal.ZERO; CurrentEarthRealm = getbyName(next); break;

                }
                MinecraftClient.getInstance().player.sendMessage(Text.of("Congratulations Your Element affinity broke into the " + next + " " + ChingChongCraft.NumberToRoman(subrealms)), false);
            }
            else {
                switch (id) {
                    case 0: CurrentHeavenSubRealm++; break;
                    case 1: CurrentLakeSubRealm++; break;
                    case 2: CurrentFlameSubRealm++; break;
                    case 3: CurrentThunderSubRealm++; break;
                    case 4: CurrentWindSubRealm++; break;
                    case 5: CurrentWaterSubRealm++; break;
                    case 6: CurrentMountainSubRealm++; break;
                    case 7: CurrentEarthSubRealm++; break;
                }
                MinecraftClient.getInstance().player.sendMessage(Text.of("Congratulations Your Element affinity broke into the " + realmName + " " + ChingChongCraft.NumberToRoman(subrealms)), false);

            }
        }
    }
    public static RealmElement getbyName(String name) {
        for(RealmElement realm : Realms) {
            if (realm.name.equals(name)) {
                return realm;
            }
        }
        return new RealmElement("Error", "Mid", 0, BigDecimal.valueOf(50));
    }
    public static String getElementName(int id) {
        return switch (id) {
            case 0 -> "Heaven";
            case 1 -> "Lake";
            case 2 -> "Flame";
            case 3 -> "Thunder";
            case 4 -> "Wind";
            case 5 -> "Water";
            case 6 -> "Mountain";
            case 7 -> "Earth";
            default -> "Null";
        };
    }
    public static RealmElement getRealmWithName(int id){

        return switch (id) {
            case 0 -> new RealmElement("Weak " + getElementName(0), "Mid", 0, BigDecimal.valueOf(50));
            case 1 -> new RealmElement("Weak " + getElementName(1), "Mid", 0, BigDecimal.valueOf(50));
            case 2 -> new RealmElement("Weak " + getElementName(2), "Mid", 0, BigDecimal.valueOf(50));
            case 3 -> new RealmElement("Weak " + getElementName(3), "Mid", 0, BigDecimal.valueOf(50));
            case 4 -> new RealmElement("Weak " + getElementName(4), "Mid", 0, BigDecimal.valueOf(50));
            case 5 -> new RealmElement("Weak " + getElementName(5), "Mid", 0, BigDecimal.valueOf(50));
            case 6 -> new RealmElement("Weak " + getElementName(6), "Mid", 0, BigDecimal.valueOf(50));
            case 7 -> new RealmElement("Weak " + getElementName(7), "Mid", 0, BigDecimal.valueOf(50));

            default -> new RealmElement("Error", "Mid", 0, BigDecimal.valueOf(50));
        };
    }
}
