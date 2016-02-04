package cc.isotopestudio.TrustedTeleport.trustedteleport;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TrustedTeleporTask extends BukkitRunnable {

	private Player from;
	private Player to;
	private Location loc;

	public TrustedTeleporTask(Player from, Player to) {
		this.from = from;
		this.to = to;
	}

	public TrustedTeleporTask(Player from, Player to, Location loc) {
		this.from = from;
		this.to = to;
		this.loc = loc;
	}

	@Override
	public void run() {
		if (loc == null)
			from.teleport(to);
		else
			from.teleport(loc);
	}

}
