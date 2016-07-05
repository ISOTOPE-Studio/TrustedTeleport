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
                        sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <�������>")
                                .append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�����ҵ������б�")
                                .toString());
                        return true;
                    }
                    Player trustedPlayer = Bukkit.getServer().getPlayer(args[1]);
                    if (trustedPlayer == null) {
                        sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("���")
                                .append(args[1]).append("������").toString());
                        return true;
                    }
                    if (trustedPlayer == player) {
                        sender.sendMessage(
                                (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("��Ҫ����Լ���").toString());
                        return true;
                    }
                    List<String> trustedPlayers = plugin.getPlayersData().getStringList("Players." + player.getName());
                    if (trustedPlayers.size() > 0) {
                        for (String trustedPlayer1 : trustedPlayers) {
                            if (trustedPlayer1.equals(trustedPlayer.getName())) {
                                player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("���")
                                        .append(args[1]).append("�Ѿ�����������б���").toString());
                                return true;
                            }
                        }
                    }
                    trustedPlayers.add(trustedPlayer.getName());

                    plugin.getPlayersData().set("Players." + player.getName(), trustedPlayers);
                    plugin.savePlayersData();
                    player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("�ɹ�������")
                            .append(player.getName()).toString());
                    trustedPlayer.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("�ɹ�������")
                            .append(player.getName()).append("������ӵ��������б����������").append(ChatColor.GRAY)
                            .append("/tt ").append(player.getName()).append(ChatColor.AQUA).append("���͵�ta������").toString());

                    return true;
                }

                if (args[0].equals("remove")) {
                    if (args.length != 2) {
                        sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp remove <�������>")
                                .append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE)
                                .append("����Ҵ������б��Ƴ�").toString());
                        return true;
                    }
                    Player removedPlayer = (Bukkit.getServer().getPlayer(args[1]));
                    if (removedPlayer == player) {
                        sender.sendMessage(
                                (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("�������Լ�����").toString());
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
                                        .append("�ɹ������").append(removedPlayer.getName()).append("�������б��Ƴ�").toString());
                                return true;
                            }
                        }
                    }
                    player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("���")
                            .append(removedPlayer.getName()).append("������������б���").toString());
                    return true;
                }

                if (args[0].equals("list")) {
                    List<String> trustedPlayers = plugin.getPlayersData().getStringList("Players." + player.getName());
                    if (trustedPlayers.size() > 0) {
                        player.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA)
                                .append("��������б�(��ɫ���ߣ���ɫ������)").toString());
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
                            .append("��û�����ε���ң�Ϊ�β����һ����").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <�������>")
                            .append(ChatColor.GRAY).append(" - ").append(ChatColor.LIGHT_PURPLE).append("�����ҵ������б�")
                            .toString());
                    return true;
                }

                if (args[0].equals("about")) {
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GRAY).append("---- ").append(plugin.prefix)
                            .append(ChatColor.RESET).append(ChatColor.DARK_GRAY).append(" " + TrustedTeleport.version)
                            .append(ChatColor.GRAY).append(" ----").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.BLUE).append(ChatColor.ITALIC)
                            .append("Ϊ�����������Ĵ��Ͳ��").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
                            .append("������ ").append(ChatColor.RESET).append(ChatColor.AQUA)
                            .append("Mars (ISOTOPE Studio)").toString());
                    sender.sendMessage((new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD)
                            .append("��ַ�� ").append(ChatColor.RESET).append(ChatColor.AQUA)
                            .append("http://isotopestudio.cc/minecraft.html").toString());
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
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/tt <�������>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("���͵������ε����").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp add <�������>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("�����ҵ������б�").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp remove <�������>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("����Ҵ������б��Ƴ�").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp list").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����б�").toString());
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/ttp about").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("�鿴�����Ϣ").toString());

                if (!(sender instanceof Player)) {
                    sender.sendMessage(
                            (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("ֻ�������ִ����Щ���").toString());
                }
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("tt")) {
            if (args.length == 1 && sender instanceof Player) {
                Player requiringPlayer = (Player) sender;
                Player requiredPlayer = (Bukkit.getServer().getPlayer(args[0]));
                if (requiredPlayer == null) {
                    sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("���")
                            .append(args[0]).append("������").toString());
                    return true;
                }
                if (requiringPlayer == requiredPlayer) {
                    sender.sendMessage(
                            (new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("���͵��Լ�����").toString());
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
                                    .append(delaySeconds).append("����͵����").append(requiredPlayer.getName()).toString());
                            requiredPlayer
                                    .sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.AQUA).append("���")
                                            .append(requiringPlayer.getName()).append(delaySeconds).append("����͵�������").toString());
                            return true;
                        }
                    }
                }
                sender.sendMessage((new StringBuilder(plugin.prefix)).append(ChatColor.RED).append("�㲻�����")
                        .append(requiringPlayer.getName()).append("����").toString());
                return true;

            } else {
                sender.sendMessage(
                        (new StringBuilder()).append(ChatColor.GOLD).append("/tt <�������>").append(ChatColor.GRAY)
                                .append(" - ").append(ChatColor.LIGHT_PURPLE).append("���͵������ε����").toString());
            }
            return true;
        }

        return false;

    }

}
