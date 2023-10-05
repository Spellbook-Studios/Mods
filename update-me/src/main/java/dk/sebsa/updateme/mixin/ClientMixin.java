package dk.sebsa.updateme.mixin;

import dk.sebsa.updateme.UpdateMEConfig;
import dk.sebsa.updateme.client.UpdateMEClient;
import dk.sebsa.updateme.client.UpdateMEScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MinecraftClient.class)
public class ClientMixin {
    @ModifyVariable(at = @At("HEAD"), method = "setScreen", argsOnly = true)
    private Screen help(Screen variable) {
        if(!UpdateMEClient.screenShown && variable instanceof TitleScreen && UpdateMEClient.needsUpdate) {
            UpdateMEClient.screenShown = true;
            return new UpdateMEScreen(variable);
        }

        return variable;
    }
}