package ro.deiutzblaxo.CustomTitleandSubtitle;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Reload implements CommandExecutor{
	private Main plugin = Main.getPlugin(Main.class);
	@Override
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reloadctas")) {
			
		
		if(plugin == null) {
	   System.out.println("Yes, it is.");
	}else

		if(sender.hasPermission("ctas.reload") || sender == plugin.getServer().getConsoleSender() ) {
            plugin.getConfig();
            plugin.saveConfig();
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            plugin.getServer().getPluginManager().enablePlugin(plugin);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Reload")));
		}else if(!sender.hasPermission("ctas.reload")) {
			
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("NoPermissionReload")));
		}
		}
		return true;
	}

}
