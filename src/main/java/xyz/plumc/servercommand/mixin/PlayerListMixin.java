package xyz.plumc.servercommand.mixin;

import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class PlayerListMixin {
    @Inject(method = "isAllowCheatsForAllPlayers", at = @At("RETURN"), cancellable = true)
    private void allowCheatsForAllPlayers(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}
