package org.gurasic.chingchongcraft.elements;

import java.math.BigDecimal;

public class RealmElement {
    public String name;
    public BigDecimal requiredQi;
    public String next;
    public int Subrealms;


    public RealmElement(String name, String next, int Subrealms, BigDecimal requiredQi) {
        this.name = name;
        this.next = next;
        this.requiredQi = requiredQi;
        this.Subrealms = Subrealms;
    }

}
