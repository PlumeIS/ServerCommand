package xyz.plumc.servercommand.mixin;

import net.minecraft.commands.Commands;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Commands.CommandSelection.class)
public class CommandSelectionMixin {
    @Mutable
    @Shadow @Final
    boolean includeDedicated;


    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(String p_82151_, int p_82152_, boolean par3, boolean par4, CallbackInfo ci) {
        this.includeDedicated = true;
    }
}
