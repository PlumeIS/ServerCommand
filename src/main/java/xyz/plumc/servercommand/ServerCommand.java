package xyz.plumc.servercommand;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod("sercommand")
public class ServerCommand {
    protected static MinecraftServer server;
    public ServerCommand() {
        MinecraftForge.EVENT_BUS.register(new RegisterCommandListener());
        MinecraftForge.EVENT_BUS.register(new PermissionsListener());
    }
    @Mod.EventBusSubscriber
    public static class RegisterCommandListener {
        @SubscribeEvent
        public static void onRegisterCommand(RegisterCommandsEvent event) {
            if (event.getEnvironment().equals(Commands.CommandSelection.INTEGRATED)){
                BanIpCommands.register(event.getDispatcher());
                BanListCommands.register(event.getDispatcher());
                BanPlayerCommands.register(event.getDispatcher());
                DeOpCommands.register(event.getDispatcher());
                OpCommand.register(event.getDispatcher());
                PardonCommand.register(event.getDispatcher());
                PardonIpCommand.register(event.getDispatcher());
                PerfCommand.register(event.getDispatcher());
                SaveAllCommand.register(event.getDispatcher());
                SaveOffCommand.register(event.getDispatcher());
                SaveOnCommand.register(event.getDispatcher());
                SetPlayerIdleTimeoutCommand.register(event.getDispatcher());
                StopCommand.register(event.getDispatcher());
                WhitelistCommand.register(event.getDispatcher());
            }
        }
    }

    @Mod.EventBusSubscriber
    public static class PermissionsListener{
        @SubscribeEvent
        public static void onServerStarting(ServerStartedEvent event){
            server = event.getServer();
        }

        @SubscribeEvent
        public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
            if (Objects.nonNull(server)){
                if (event.getPlayer().getGameProfile().getName().equals(Minecraft.getInstance().getUser().getName())){
                    server.getPlayerList().op(event.getPlayer().getGameProfile());
                } else {
                    server.getPlayerList().deop(event.getPlayer().getGameProfile());
                }
            }
        }
    }
}
