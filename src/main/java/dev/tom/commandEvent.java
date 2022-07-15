package dev.tom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;


public class commandEvent implements Listener {

    public static brain h = (brain) brain.getPlugin(brain.class);


    private List<String> list = h.getConfig().getStringList("blockedCommands");
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
        if(event.getMessage().contains(":")){
            if(message[0].equals("/schematic") || message[0].equals("//schematic")){
                return;
            }
            event.getPlayer().sendMessage("Unknown command. Type \"/help\" for help.");
            event.setCancelled(true);
            return;

        }


        if(list.contains(message[0].replace("/", ""))){
            event.setCancelled(true);
            if(h.getConfig().getBoolean("discreteBlock")){
                event.getPlayer().sendMessage("Unknown command. Type \"/help\" for help.");
            } else {
                event.getPlayer().sendMessage(format.format(h.getConfig().getString("blockedMessage")));
            }
        }


    }

}
