package ro.deiutzblaxo.CustomTitleandSubtitle;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_13_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutTitle.EnumTitleAction;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getCommand("reloadctas").setExecutor(new Reload());
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Custom Title and Subtitle has been enabled!");
	    getConfig().options().copyDefaults(true);
	    saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);	
		if(!getConfig().getBoolean("EnableTitle")) {
			
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "-----------------------------------------------------------------------");	
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "Title is Disabled. If this is wrong please check the config!");
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "-----------------------------------------------------------------------");
		}else
		if(getConfig().getBoolean("EnableTitle")) {
			getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-----------------------------------------------------------------------");	
			getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Title is Enabled. If this is wrong please check the config!");
			getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-----------------------------------------------------------------------");
		}
			if(!getConfig().getBoolean("EnableSubtitle")) {
				getServer().getConsoleSender().sendMessage(ChatColor.RED + "-----------------------------------------------------------------------");	
				getServer().getConsoleSender().sendMessage(ChatColor.RED + "Subtitle is Disabled. If this is wrong please check the config!");
				getServer().getConsoleSender().sendMessage(ChatColor.RED + "-----------------------------------------------------------------------");
				}else
				if(getConfig().getBoolean("EnableSubtitle")) {
					getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-----------------------------------------------------------------------");	
					getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Subtitle is Enabled. If this is wrong please check the config!");
					getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-----------------------------------------------------------------------");
				}
		}
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "Custom Title and Subtitle has been disabled!");
	}
		
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(getConfig().getBoolean("EnableTitle"))  {
			
		
		String titlestring = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Title").replaceAll("%player%", 
				player.getDisplayName()));
	    PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + titlestring + "\"}"), 1, 20, 20);
		
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(title);		
		}
		else if(!getConfig().getBoolean("EnableTitle")) {
			
		}
		
		if(getConfig().getBoolean("EnableSubtitle")) {
			String subtitlestring = ChatColor.translateAlternateColorCodes('&', getConfig().getString("Subtitle").replaceAll("%player%", 
					player.getDisplayName()));
			PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" 
			+ subtitlestring + "\"}"), 1, 20, 20);
			((CraftPlayer)player).getHandle().playerConnection.sendPacket(subtitle);
			}else if(!getConfig().getBoolean("EnableSubtitle")) {
				
			}
	}
}
