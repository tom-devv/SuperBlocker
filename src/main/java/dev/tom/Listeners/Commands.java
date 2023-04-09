package dev.tom.Listeners;

import dev.tom.Blocker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;


public class Commands implements Listener {

    private final List<String> list = Blocker.getInstance().getConfig().getStringList("blockedCommands");
    @EventHandler
    public void onMessage(PlayerCommandPreprocessEvent event){

        if(event.getPlayer().hasPermission("block.bypass")){
            event.setCancelled(false);
            return;
        }


        String[] message = event.getMessage().toLowerCase().split("\\s+");

        /*
        Block colon commands
         */
        if(event.getMessage().contains(":") && Blocker.getInstance().getConfig().getBoolean("blockColonCommands")){
            if(message[0].equals("/schematic") || message[0].equals("//schematic")){
                return;
            }
            event.getPlayer().sendMessage(Blocker.BlockMessage);
            event.setCancelled(true);
            return;

        }

        if(list.contains(message[0].replace("/", ""))){
            event.setCancelled(true);
            event.getPlayer().sendMessage(Blocker.BlockMessage);
        }


    }

}
