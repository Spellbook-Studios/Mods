package dk.sebsa.updateme.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class UpdateMEScreen extends Screen {
    private final Screen parent;

    public UpdateMEScreen(Screen parent) {
        super(Text.literal("Update ME! screen"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        ButtonWidget buttonWidget = this.addDrawableChild(ButtonWidget.builder(Text.literal("To the main menu"), (buttonWidgetx) -> {
            this.client.setScreen(this.parent);
        }).width(128).build());
        buttonWidget.setPosition(this.width / 2 - 64, this.height - 60);

        ButtonWidget buttonWidget2 = this.addDrawableChild(ButtonWidget.builder(Text.literal("Close game"), (buttonWidgetx) -> {
            this.client.scheduleStop();
        }).width(128).build());
        buttonWidget2.setPosition(this.width / 2 - 64, this.height - 30);
    }

    @Override
    public void render(DrawContext drawContext, int i, int j, float f) {
        super.render(drawContext, i, j, f);
        drawContext.drawCenteredTextWithShadow(this.textRenderer, Text.literal("It is time to update this modpack"), width / 2, 20, 0xffffff);
        drawContext.drawCenteredTextWithShadow(this.textRenderer, Text.literal("You're running an outdated version of this modpack"), width / 2, 20+10, 0xffffff);
        drawContext.drawCenteredTextWithShadow(this.textRenderer, Text.literal("Please consider updating from "
                + UpdateMEClient.currVer
                + " to "
                + UpdateMEClient.newVer), width / 2, 20+20, 0xffffff);
    }
}
