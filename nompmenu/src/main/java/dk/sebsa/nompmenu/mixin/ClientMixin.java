package dk.sebsa.nompmenu.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MinecraftClient.class)
public class ClientMixin {
    @ModifyVariable(at = @At("HEAD"), method = "setScreen", argsOnly = true)
    private Screen help(Screen variable) {
        if(variable instanceof net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen) return new TitleScreen();
        return variable;
    }
}
