package cc.isotopestudio.TrustedTeleport.trustedteleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

class TrustedTeleportCommand implements CommandExecutor {
    private final TrustedTeleport plugin;

    TrustedTeleportCommand(TrustedTeleport plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ttp")) {
            if (args.length > 0 && !args[0].equals("help") && sender instanceof Player) {
                Player player = (Player) sender;
                if (args[0].equals("add")) {
                    if (args.length != 2) {
                        sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <玩家名字>")
                                .append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("添加玩家到信任列表")
                                .toString());
                        return true;
                    }
                    Player trustedPlayer = Bukkit.getServer().getPlayer(args[1]);
                    if (trustedPlayer == null) {
                        sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("玩家")
                                .append(args[1]).append("不在线").toString());
                        return true;
                    }
                    if (trustedPlayer == player) {
                        sender.sendMessage(
                                (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("不要添加自己！").toString());
                        return true;
                    }
                    List<String> trustedPlayers = plugin.getPlayersData().getStringList("Players." + player.getName());
                    if (trustedPlayers.size() > 0) {
                        for (String trustedPlayer1 : trustedPlayers) {
                            if (trustedPlayer1.equals(trustedPlayer.getName())) {
                                player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("玩家")
                                        .append(args[1]).append("已经在你的信任列表了").toString());
                                return true;
                            }
                        }
                    }
                    trustedPlayers.add(trustedPlayer.getName());

                    plugin.getPlayersData().set("Players." + player.getName(), trustedPlayers);
                    plugin.savePlayersData();
                    player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("成功添加玩家")
                            .append(player.getName()).toString());
                    trustedPlayer.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("成功添加玩家")
                            .append(player.getName()).append("把你添加到了信任列表，你可以输入").append(ChatColor.GRAY)
                            .append("/tt ").append(player.getName()).append(ChatColor.AQUA).append("传送到ta那里了").toString());

                    return true;
                }

                if (args[0].equals("remove")) {
                    if (args.length != 2) {
                        sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp remove <玩家名字>")
                                .append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
                                .append("将玩家从信任列表移除").toString());
                        return true;
                    }
                    Player removedPlayer = (Bukkit.getServer().getPlayer(args[1]));
                    if (removedPlayer == player) {
                        sender.sendMessage(
                                (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("不信任自己？！").toString());
                        return true;
                    }
                    List<String> trustedPlayers = plugin.getPlayersData().getStringList("Players." + player.getName());
                    if (trustedPlayers.size() > 0) {
                        for (int i = 0; i < trustedPlayers.size(); i++) {
                            if (trustedPlayers.get(i).equals(removedPlayer.getName())) {
                                trustedPlayers.remove(i);
                                plugin.getPlayersData().set("Players." + player.getName(), trustedPlayers);
                                plugin.savePlayersData();

                                player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA)
                                        .append("成功将玩家").append(removedPlayer.getName()).append("从信任列表移除").toString());
                                return true;
                            }
                        }
                    }
                    player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("玩家")
                            .append(removedPlayer.getName()).append("不在你的信任列表中").toString());
                    return true;
                }

                if (args[0].equals("list")) {
                    List<String> trustedPlayers = plugin.getPlayersData().getStringList("Players." + player.getName());
                    if (trustedPlayers.size() > 0) {
                        player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA)
                                .append("你的信任列表(绿色在线，灰色不在线)").toString());
                        for (String trustedPlayer : trustedPlayers) {
                            StringBuilder temp = new StringBuilder("    ");
                            Player tempPlayer = Bukkit.getServer().getPlayer(trustedPlayer);
                            if (tempPlayer == null) {
                                temp.append(ChatColor.GRAY);
                            } else
                                temp.append(ChatColor.GREEN);
                            player.sendMessage(temp.append(trustedPlayer).toString());
                        }
                        return true;
                    }
                    player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA)
                            .append("你没有信任的玩家，为何不添加一个呢").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <玩家名字>")
                            .append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("添加玩家到信任列表")
                            .toString());
                    return true;
                }

                if (args[0].equals("about")) {
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GRAY).append("---- ").append(plugin.prefix)
                            .append(ChatColor.RESET).append(ChatColor.DARK_GRAY).append(" " + TrustedTeleport.version)
                            .append(ChatColor.GRAY).append(" ----").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.BLUE).append(ChatColor.ITALIC)
                            .append("为服务器制作的传送插件").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
                            .append("制作： ").append(ChatColor.RESET).append(ChatColor.AQUA)
                            .append("Mars (ISOTOPE Studio)").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
                            .append("网址： ").append(ChatColor.RESET).append(ChatColor.AQUA)
                            .append("http://isotopestudio.cc/minecraft.html").toString());
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
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/tt <玩家名字>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("传送到被信任的玩家").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <玩家名字>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("添加玩家到信任列表").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp remove <玩家名字>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("将玩家从信任列表移除").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp list").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("查看信任列表").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp about").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("查看插件信息").toString());

                if (!(sender instanceof Player)) {
                    sender.sendMessage(
                            (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("只有玩家能执行这些命令！").toString());
                }
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("tt")) {
            if (args.length == 1 && sender instanceof Player) {
                Player requiringPlayer = (Player) sender;
                Player requiredPlayer = (Bukkit.getServer().getPlayer(args[0]));
                if (requiredPlayer == null) {
                    sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("玩家")
                            .append(args[0]).append("不在线").toString());
                    return true;
                }
                if (requiringPlayer == requiredPlayer) {
                    sender.sendMessage(
                            (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("传送到自己？！").toString());
                    return true;
                }
                List<String> trustedPlayers = plugin.getPlayersData().getStringList("Players." + requiredPlayer.getName());
                if (trustedPlayers.size() > 0) {
                    for (String trustedPlayer : trustedPlayers) {
                        if (trustedPlayer.equals(requiringPlayer.getName())) {
                            double delaySeconds = plugin.getConfig().getDouble("Teleporting.delay");
                            long delayTicks = (int) (delaySeconds * 20);
                            boolean tpToLocWhereReq = plugin.getConfig().getBoolean("Teleporting.tpToLocWhereReq");
                            if (tpToLocWhereReq) {
                                Location loc = requiredPlayer.getLocation();
                                new TrustedTeleporTask(requiringPlayer, requiredPlayer, loc)
                                        .runTaskLater(plugin, delayTicks);
                            } else {
                                new TrustedTeleporTask(requiringPlayer, requiredPlayer)
                                        .runTaskLater(plugin, delayTicks);
                            }

                            requiringPlayer.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA)
                                    .append(delaySeconds).append("秒后传送到玩家").append(requiredPlayer.getName()).toString());
                            requiredPlayer
                                    .sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("玩家")
                                            .append(requiringPlayer.getName()).append(delaySeconds).append("秒后传送到你这里").toString());
                            return true;
                        }
                    }
                }
                sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("你不被玩家")
                        .append(requiringPlayer.getName()).append("信任").toString());
                return true;

            } else {
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/tt <玩家名字>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("传送到被信任的玩家").toString());
            }
            return true;
        }

        return false;

    }

}
