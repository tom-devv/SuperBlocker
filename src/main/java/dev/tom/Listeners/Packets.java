package dev.tom.Listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import dev.tom.Blocker;
import org.bukkit.Bukkit;

public class Packets {

    public Packets(ProtocolManager protocolManager, Blocker plugin){
        protocolManager.addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.TAB_COMPLETE) {
            //Tab Packet -> Server
            @Override
            public void onPacketSending(PacketEvent event)
            {
                if(event.getPlayer().hasPermission("block.bypass")){
                    return;
                }

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

}
