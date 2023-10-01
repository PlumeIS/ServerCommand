package xyz.plumc.servercommand.mixin;


import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ServerResources;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.WorldData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.plumc.servercommand.ServerCommand;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Thread thread, Minecraft arg, RegistryAccess.RegistryHolder arg2, LevelStorageSource.LevelStorageAccess arg3, PackRepository arg4, ServerResources arg5, WorldData arg6, MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository, GameProfileCache arg7, ChunkProgressListenerFactory arg8, CallbackInfo ci){
        ServerCommand.levelStorageAccess = arg3;
    }

    @Inject(method = "getOperatorUserPermissionLevel", at = @At("RETURN"), cancellable = true)
    private void onGetOperatorUserPermissionLevel(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(4);
    }
}
