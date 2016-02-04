package cc.isotopestudio.TrustedTeleport.trustedteleport;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class TrustedTeleportCommand implements CommandExecutor {
	private final TrustedTeleport plugin;

	public TrustedTeleportCommand(TrustedTeleport plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ttp")) {
			if (args.length > 0)
				args[0].toLowerCase();
			if (args.length > 0 && !args[0].equals("help")) {
				if (args[0].equals("add")) {
					
					return true;
				}

				if (args[0].equals("remove")) {
					
					return true;
				}

				if (args[0].equals("list")) {
					return true;
				}

				if (args[0].equals("about")) {
					sender.sendMessage((new StringBuilder()).append(ChatColor.GRAY).append("---- " + plugin.prefix)
							.append(ChatColor.RESET).append(ChatColor.DARK_GRAY).append(" " + plugin.version)
							.append(ChatColor.GRAY).append(" ----").toString());
					sender.sendMessage((new StringBuilder()).append(ChatColor.BLUE).append(ChatColor.ITALIC)
							.append("为服务器制作的传送插件").toString());
					sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
							.append("制作： ").append(ChatColor.RESET).append(ChatColor.GREEN)
							.append("Mars (ISOTOPE Studio)").toString());
					sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
							.append("网址： ").append(ChatColor.RESET).append(ChatColor.GREEN)
							.append("http://isotopestudio.cc").toString());
					return true;
				}

				// Wrong args0
				else {
					sender.sendMessage(
							(new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("未知命令").toString());
					return true;
				}

			} else { // Help Menu
				sender.sendMessage(
						(new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("== 帮助菜单 ==").toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/tt <玩家名字>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("传送到被信任的玩家")
						.toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <玩家名字>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("添加玩家到信任列表")
						.toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp remove <玩家名字>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("将玩家从信任列表移除")
						.toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp list")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("查看信任列表")
						.toString());
				sender.sendMessage(
						(new StringBuilder()).append(ChatColor.GOLD).append("/ttp about").append(ChatColor.GRAY)
								.append(" - ").append(ChatColor.LIGHT_PURPLE).append("查看插件信息").toString());
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("tt")) {
			return true;
		}
		
		return false;

	}

}
