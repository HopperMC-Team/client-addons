package plugin.server;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Login.Client;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import hopperclient.shared.MiscConstants;
import hopperclient.shared.MultiplayerConstants;
import net.md_5.bungee.api.ChatColor;
import plugin.server.packets.server.CustomPacketManager;
import plugin.server.packets.server.SPacketTest;

public class Main extends JavaPlugin implements Listener {

	protected static final String UUID = null;

	@Override
	public void onEnable() {
		getLogger().info("Hopper Client Plugin Loading!");
		getServer().getPluginManager().registerEvents(this, this);
		
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Status.Server.SERVER_INFO) {
			
			@Override
			public void onPacketSending(PacketEvent event) {
				
				WrappedServerPing ping = event.getPacket().getServerPings().read(0);
				ping.setVersionProtocol(MultiplayerConstants.PING_VERSION);
				ping.setVersionName("Hopper Client v" + MiscConstants.CLIENT_VERSION);;
				
			}
			
	});
	
}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		String[] hostnameParts = e.getHostname().split(";")[0].split("\0");
		
		if(hostnameParts.length == 2) {
			
			if(hostnameParts[1].equals(MultiplayerConstants.AUTH_KEY)) {
				new BukkitRunnable() {
					
					@Override
					public void run() {
						
						e.getPlayer().sendMessage(ChatColor.YELLOW + "Thank you for using Hopper Client!");
						
						}
					
					}.runTaskLater(this, 2);
				};
			}
			
		}
	
	

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		//Temp
		p.setGameMode(GameMode.CREATIVE);
		p.setOp(true);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				CustomPacketManager.sendCustomPacket(p, new SPacketTest());
				
			}
		}.runTaskLater(this, 2);
	}
}
