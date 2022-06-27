package dev.tom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;




public class commandEvent implements Listener {

    public static Heart h = (Heart) Heart.getPlugin(Heart.class);


    List<String> list = h.getConfig().getStringList("blockedCommands");
    @EventHandler
    public void onMessage(PlayerCommandPreprocessEvent event){

        /*
        Block colon commands
         */
        if(event.getMessage().contains(":")){
            event.setCancelled(true);
            return;
        }

        String[] message = event.getMessage().split("\\s+");


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
