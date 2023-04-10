package dev.tom;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import dev.tom.Listeners.Commands;
import dev.tom.Listeners.Packets;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Blocker extends JavaPlugin {


    public static Blocker instance;
    public ProtocolManager protocolManager;
    public static String BlockMessage;

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enabled Command Blocker");
        instance = this;
        getServer().getPluginManager().registerEvents(new Commands(), this);
        config();
        this.protocolManager = ProtocolLibrary.getProtocolManager();
        BlockMessage = this.getConfig().getBoolean("disableTabComplete") ? "Unknown command. Type \"/help\" for help." : Util.format(this.getConfig().getString("blockedMessage"));
        new Packets(protocolManager, this);
    }

    public void config(){
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
    }

    public static Blocker getInstance() {
        return instance;
    }
}
