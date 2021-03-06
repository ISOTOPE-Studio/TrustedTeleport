package cc.isotopestudio.TrustedTeleport.trustedteleport;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class TrustedTeleport extends JavaPlugin {
	public final String version = "v1.2.1";

	public final String prefix = (new StringBuilder()).append(ChatColor.GREEN).append("[").append(ChatColor.ITALIC)
			.append(ChatColor.BOLD).append("信任传送").append(ChatColor.RESET).append(ChatColor.GREEN).append("]")
			.append(ChatColor.RESET).toString();

	public void createFile(String name) {

		File file;
		file = new File(getDataFolder(), name + ".yml");
		if (!file.exists()) {
			saveDefaultConfig();
		}
	}

	String FileVersion = "1";

	@Override
	public void onEnable() {
		getLogger().info("加载配置文件中");

		createFile("config");
		if (!getConfig().getString("FileVersion").equals(FileVersion)) {
			getLogger().info("信任传送 配置文件错误!");
			onDisable();
			return;
		}

		try {
			getPlayersData().save(dataFile);
		} catch (IOException e) {
		}

		// PluginManager pm = this.getServer().getPluginManager();

		this.getCommand("ttp").setExecutor(new TrustedTeleportCommand(this));
		this.getCommand("tt").setExecutor(new TrustedTeleportCommand(this));

		getLogger().info("信任传送 成功加载!");
		getLogger().info("信任传送 由ISOTOPE Studio制作!");
		getLogger().info("http://isotopestudio.cc");
	}

	public void onReload() {
		reloadPlayersData();
		this.reloadConfig();
	}

	@Override
	public void onDisable() {
		savePlayersData();
		getLogger().info("信任传送 成功卸载!");
	}

	private File dataFile = null;
	private FileConfiguration data = null;

	public void reloadPlayersData() {
		if (dataFile == null) {
			dataFile = new File(getDataFolder(), "playersData.yml");
		}
		data = YamlConfiguration.loadConfiguration(dataFile);
	}

	public FileConfiguration getPlayersData() {
		if (data == null) {
			reloadPlayersData();
		}
		return data;
	}

	public void savePlayersData() {
		if (data == null || dataFile == null) {
			return;
		}
		try {
			getPlayersData().save(dataFile);
		} catch (IOException ex) {
			getLogger().info("玩家文件保存失败！");
		}
	}

}
