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
							.append("Ϊ�����������Ĵ��Ͳ��").toString());
					sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
							.append("������ ").append(ChatColor.RESET).append(ChatColor.GREEN)
							.append("Mars (ISOTOPE Studio)").toString());
					sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
							.append("��ַ�� ").append(ChatColor.RESET).append(ChatColor.GREEN)
							.append("http://isotopestudio.cc").toString());
					return true;
				}

				// Wrong args0
				else {
					sender.sendMessage(
							(new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("δ֪����").toString());
					return true;
				}

			} else { // Help Menu
				sender.sendMessage(
						(new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("== �����˵� ==").toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/tt <�������>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("���͵������ε����")
						.toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <�������>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�����ҵ������б�")
						.toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp remove <�������>")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("����Ҵ������б��Ƴ�")
						.toString());
				sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp list")
						.append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����б�")
						.toString());
				sender.sendMessage(
						(new StringBuilder()).append(ChatColor.GOLD).append("/ttp about").append(ChatColor.GRAY)
								.append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����Ϣ").toString());
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("tt")) {
			return true;
		}
		
		return false;

	}

}
