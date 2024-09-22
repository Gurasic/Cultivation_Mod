package org.gurasic.chingchongcraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.gurasic.chingchongcraft.Events;
import org.gurasic.chingchongcraft.cultivation.Cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UpgradeTreeScreen extends Screen {
    private int offsetX, offsetY;
    private boolean dragging;
    private int dragStartX, dragStartY;

    public UpgradeTreeScreen() {
        super(Text.of("Upgrade Tree"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Cultivation Cultivation = Events.playerList.get(MinecraftClient.getInstance().player).cult;
        super.render(context, mouseX, mouseY, delta);
        Identifier deepslate = new Identifier("chingchongcraft", "textures/gui/deepslate.png");
        Identifier frame = new Identifier("chingchongcraft", "textures/gui/frame.png");
        Identifier locked_frame = new Identifier("chingchongcraft", "textures/gui/locked_frame.png");
        Identifier completed_frame = new Identifier("chingchongcraft", "textures/gui/completed_frame.png");
        Identifier overlay = new Identifier("chingchongcraft", "textures/gui/overlay.png");
        context.drawTexture(deepslate, offsetX - 1000, offsetY - 1000, 0, 0, 2000, 2000, 16, 16);
        for (int i = 0; i < Cultivation.CurrentManual.Upgrades.toArray().length; i++) {
            if (Cultivation.CurrentManual.GetNextUpgrade(Cultivation.CurrentManual.Upgrades.get(i).Next) != null) {
                drawLine(context, new Vec3d( -Cultivation.CurrentManual.Upgrades.get(i).X + (offsetX + 12),  -Cultivation.CurrentManual.Upgrades.get(i).Y + (offsetY + 12), 0), new Vec3d( -Cultivation.CurrentManual.GetNextUpgrade(Cultivation.CurrentManual.Upgrades.get(i).Next).X + (offsetX + 12),  -Cultivation.CurrentManual.GetNextUpgrade(Cultivation.CurrentManual.Upgrades.get(i).Next).Y + (offsetY + 12), 0), 0xFFFFFFFF);
            }
            if (Cultivation.CurrentManual.Upgrades.get(i).Current) {
                context.drawTexture(frame, offsetX - Cultivation.CurrentManual.Upgrades.get(i).X, offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y, 0, 0, 26, 26, 26, 26);
                ItemStack stack = new ItemStack(Cultivation.CurrentManual.Upgrades.get(i).Cost);
                context.drawItem(stack, (offsetX - Cultivation.CurrentManual.Upgrades.get(i).X) + 5, (offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y) + 5, 0);
            } else if (Cultivation.CurrentManual.Upgrades.get(i).Completed) {
                context.drawTexture(completed_frame, offsetX - Cultivation.CurrentManual.Upgrades.get(i).X, offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y, 0, 0, 26, 26, 26, 26);
                ItemStack stack = new ItemStack(Cultivation.CurrentManual.Upgrades.get(i).Cost);
                context.drawItem(stack, (offsetX - Cultivation.CurrentManual.Upgrades.get(i).X) + 5, (offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y) + 5, 0);
            } else {
                context.drawTexture(locked_frame, offsetX - Cultivation.CurrentManual.Upgrades.get(i).X, offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y, 0, 0, 26, 26, 26, 26);
            }
            if (Cultivation.CurrentManual.Upgrades.get(i).Current && isMouseOverArea(mouseX, mouseY, offsetX - Cultivation.CurrentManual.Upgrades.get(i).X, offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y, 26, 26) || Cultivation.CurrentManual.Upgrades.get(i).Completed  && isMouseOverArea(mouseX, mouseY, offsetX - Cultivation.CurrentManual.Upgrades.get(i).X, offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y, 26, 26)) {
                context.drawTooltip(client.textRenderer, Cultivation.CurrentManual.Upgrades.get(i).tooltip, (offsetX - Cultivation.CurrentManual.Upgrades.get(i).X) + 22, (offsetY - Cultivation.CurrentManual.Upgrades.get(i).Y));
            }
        }
        RenderSystem.setShaderTexture(0, overlay);
        context.drawTexture(overlay, 0, 0, 0, 0, this.width, this.height, this.width, this.height);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            dragging = true;
            dragStartX = (int) mouseX;
            dragStartY = (int) mouseY;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            dragging = false;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging) {
            offsetX += (int) deltaX;
            offsetY += (int) deltaY;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
    public static boolean isMouseOverArea(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
    public static void drawLine(DrawContext context, Vec3d start, Vec3d end, int color) {
        if (start.x != end.x) {
            // Horizontal line
            context.drawHorizontalLine((int) start.x, (int) end.x, (int) start.y, color);
        } else if (start.y != end.y) {
            // Vertical line
            context.drawVerticalLine((int) start.x, (int) start.y, (int) end.y, color);
        }
    }
}