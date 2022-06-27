package dev.tom;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Heart extends JavaPlugin {



    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enabled Command Blocker");
        this.getServer().getPluginManager().registerEvents(new commandEvent(), this);
        config();
        depends();
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Server.TAB_COMPLETE) {
            @Override
            //Player sending tab packet
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                String[] completions = packet.getStringArrays().read(0);
                boolean sendCompletions = true;
                for(String players : completions){
                    if(Bukkit.getPlayer(players) == null){
                        sendCompletions = false;
                        break;
                    }
                }
                event.setCancelled(!sendCompletions);

            }

            @Override
            public void onPacketReceiving(PacketEvent event) {

                event.setCancelled(false);
            }
        });
    }

    public void config(){
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
    }

    public void depends(){
        if(getServer().getPluginManager().getPlugin("ProtocolLib") != null){
                System.out.println("Found ProtocolLib");
        } else {
            this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "ERROR CANNOT FIND PROTOCOL LIB");
            this.getPluginLoader().disablePlugin(this);
        }

    }

}
