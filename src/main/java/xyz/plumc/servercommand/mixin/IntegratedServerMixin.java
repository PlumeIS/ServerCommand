package xyz.plumc.servercommand.mixin;


import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IntegratedServer.class)
public class IntegratedServerMixin {
    @Inject(method = "getOperatorUserPermissionLevel", at = @At("RETURN"), cancellable = true)
    private void onGetOperatorUserPermissionLevel(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(4);
    }
}
