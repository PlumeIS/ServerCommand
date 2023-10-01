package xyz.plumc.servercommand;

import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

import java.io.IOException;
import java.nio.file.Files;

@Mod("sercommand")
@Mod.EventBusSubscriber
public class ServerCommand {
    public static LevelStorageSource.LevelStorageAccess levelStorageAccess;


    public ServerCommand() {
    }

    @SubscribeEvent
    public static void onServerAboutToStartEvent(FMLServerAboutToStartEvent event) {
        PlayerList playerList = event.getServer().getPlayerList();
        try {
            if (PlayerList.USERBANLIST_FILE.exists()){
                PlayerList.USERBANLIST_FILE.delete();
            }
            Files.copy(levelStorageAccess.getLevelPath(LevelResource.ROOT).resolve(PlayerList.USERBANLIST_FILE.toPath()), PlayerList.USERBANLIST_FILE.toPath());
            playerList.getBans().load();
        } catch (IOException ignored) {
        }
        try {
            if (PlayerList.IPBANLIST_FILE.exists()){
                PlayerList.IPBANLIST_FILE.delete();
            }
            Files.copy(levelStorageAccess.getLevelPath(LevelResource.ROOT).resolve(PlayerList.IPBANLIST_FILE.toPath()), PlayerList.IPBANLIST_FILE.toPath());
            playerList.getIpBans().load();
        } catch (IOException ignored) {
        }
        try {
            if (PlayerList.OPLIST_FILE.exists()){
                PlayerList.OPLIST_FILE.delete();
            }
            Files.copy(levelStorageAccess.getLevelPath(LevelResource.ROOT).resolve(PlayerList.OPLIST_FILE.toPath()), PlayerList.OPLIST_FILE.toPath());
            playerList.getOps().load();
        } catch (IOException ignored) {
        }
        try {
            if (PlayerList.WHITELIST_FILE.exists()){
                PlayerList.WHITELIST_FILE.delete();
            }
            Files.copy(levelStorageAccess.getLevelPath(LevelResource.ROOT).resolve(PlayerList.WHITELIST_FILE.toPath()), PlayerList.WHITELIST_FILE.toPath());
            playerList.getOps().load();
        } catch (IOException ignored) {
        }
    }

    @SubscribeEvent
    public static void onServerStopped(FMLServerStoppedEvent event){
        PlayerList.OPLIST_FILE.delete();
        PlayerList.USERBANLIST_FILE.delete();
        PlayerList.IPBANLIST_FILE.delete();
        PlayerList.WHITELIST_FILE.delete();
    }
}
