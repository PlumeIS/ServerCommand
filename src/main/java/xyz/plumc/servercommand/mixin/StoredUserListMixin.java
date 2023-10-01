package xyz.plumc.servercommand.mixin;

import net.minecraft.server.players.StoredUserList;
import net.minecraft.world.level.storage.LevelResource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.plumc.servercommand.ServerCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mixin(StoredUserList.class)
public class StoredUserListMixin {
    @Final
    @Shadow
    private File file;

    @Inject(method = "save", at = @At("RETURN"))
    private void onSave(CallbackInfo ci){
        new Thread(()->{
            try {
                Path movePath = ServerCommand.levelStorageAccess.getLevelPath(LevelResource.ROOT).resolve(file.getName());
                if (movePath.toFile().exists()){
                    movePath.toFile().delete();
                }
                Files.move(file.toPath(), movePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
