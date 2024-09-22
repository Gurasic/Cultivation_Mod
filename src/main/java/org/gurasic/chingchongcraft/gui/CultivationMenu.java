package org.gurasic.chingchongcraft.gui;

import com.lowdragmc.lowdraglib.gui.texture.WidgetTexture;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.gurasic.chingchongcraft.ChingChongCraft;
import org.gurasic.chingchongcraft.Events;
import org.gurasic.chingchongcraft.cultivation.Cultivation;
import org.gurasic.chingchongcraft.cultivation.manuals.Manual;
import org.gurasic.chingchongcraft.util.ItemRegister;
import org.gurasic.chingchongcraft.util.health;

import java.util.ArrayList;
import java.util.List;

public class CultivationMenu extends Screen {
    public CultivationMenu(Text title) {
        super(title);
    }
    public ButtonWidget button1;


    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
                    MinecraftClient.getInstance().setScreen(new UpgradeTreeScreen());
                })
                .dimensions(width / 2 + 100, 100, 20, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
         addDrawableChild(button1);
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        Cultivation Cultivation = Events.playerList.get(MinecraftClient.getInstance().player).cult;
        renderBackground(context);
        Identifier gui = new Identifier("chingchongcraft", "textures/gui/gui.png");
        context.drawTexture(gui, 200, 50, 0, 0, 256, 256);
        Identifier gui_bar_empty = new Identifier("chingchongcraft", "textures/gui/gui_bar_empty.png");
        Identifier gui_bar_full = new Identifier("chingchongcraft", "textures/gui/gui_bar_full.png");

        int CultivationBarWidth = 240;
        int CultivationBarHeight = 7;
        int CultivationBarX = 207;
        int CultivationBarY = 85;
        int maxValue = Cultivation.CurrentRealm.requiredQi.intValue();
        if(Cultivation.CurrentSubRealm != 1) {
            maxValue = Cultivation.CurrentRealm.requiredQi.intValue() * (int) (Cultivation.CurrentSubRealm * 1.03);
        }
        int CultivationBarFillAmount = (int) ((Cultivation.Qi.floatValue() / maxValue) * CultivationBarWidth);
        context.drawTexture(gui_bar_empty, CultivationBarX, CultivationBarY, 0, 0, CultivationBarWidth, CultivationBarHeight, CultivationBarWidth, CultivationBarHeight);
        context.drawTexture(gui_bar_full, CultivationBarX, CultivationBarY, 0, 0, CultivationBarFillAmount, CultivationBarHeight, CultivationBarWidth, CultivationBarHeight);

        context.drawText(this.textRenderer, Cultivation.CurrentRealm.name + " " + ChingChongCraft.NumberToRoman(Cultivation.CurrentSubRealm), 210, 80 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        context.drawText(this.textRenderer, Cultivation.Qi.toString() + " / " + maxValue, 210, 92 - this.textRenderer.fontHeight - 10, 0xbbbbbb, true);
        button1.render(context, mouseX, mouseY, delta);
        if (Cultivation.CurrentManual != null) {
            context.drawText(this.textRenderer, "Current Manual: " + Cultivation.CurrentManual.Name, 210, 120 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
            if (UpgradeTreeScreen.isMouseOverArea(mouseX, mouseY, 210, 125 - this.textRenderer.fontHeight - 10, 200, 10)) {
                List<Text> tooltip = new ArrayList<>();
                Cultivation.CurrentManual.LoadTooltip(tooltip);
                context.drawTooltip(client.textRenderer,tooltip,210, 130);
            }
        } else {
            context.drawText(this.textRenderer, "Current Manual: " + "None", 210, 120 - this.textRenderer.fontHeight - 10, 0xFFFFFFFF, true);
        }
    }
}
