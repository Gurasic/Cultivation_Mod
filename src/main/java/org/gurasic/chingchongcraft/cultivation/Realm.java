package org.gurasic.chingchongcraft.cultivation;

import com.lowdragmc.photon.client.fx.EntityEffect;

import java.math.BigDecimal;

public class Realm {
    public String name;
    public BigDecimal requiredQi;
    public String next;
    public int Subrealms;
    public EntityEffect effect;
    public Realm(String name, int subrealms, BigDecimal requiredQi, String next) {
        this.name = name;
        this.requiredQi = requiredQi;
        this.next = next;
        this.Subrealms = subrealms;
    }

    public void setEffect(EntityEffect effect) {
        this.effect = effect;
    }

}
