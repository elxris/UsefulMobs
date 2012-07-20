
package mx.consalvo.UsefulMobs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class UsefulMobsEntityListener implements Listener {
	private final String FaltaString;
	private final String PescadoCocido;

	FileConfiguration config;
	JavaPlugin plugin;

	public UsefulMobsEntityListener(JavaPlugin plugin) {
		this.config = plugin.getConfig();
		this.plugin = plugin;
		this.FaltaString = this.config.getString("Messages.FaltaString");
		this.PescadoCocido = this.config.getString("Messages.PescadoCocido");
	}

	@EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof LivingEntity) {
			LivingEntity clickedEntity = (LivingEntity)event.getRightClicked();
			Player player = (Player)event.getPlayer();
		
		if (clickedEntity instanceof Spider) {
			if (player.getItemInHand().getType() == Material.STRING) {
				if (!player.getInventory().contains(Material.STRING, 3)){
				player.sendMessage(ChatColor.RED  + FaltaString);
					return;
				}
				if (!player.getInventory().contains(Material.STRING, 2)){
				player.sendMessage(ChatColor.RED  + FaltaString);
					return;
				}
			((Spider) clickedEntity).setTarget(null);
			player.getInventory().removeItem(new ItemStack (287, 3));
			clickedEntity.getWorld().dropItem(clickedEntity.getLocation(), new ItemStack(30, 1));
			clickedEntity.getWorld().playEffect(clickedEntity.getLocation(), Effect.STEP_SOUND, 30);
				}
			}
		
		if (clickedEntity instanceof Squid) {
			if (player.getItemInHand().getType() == Material.COOKED_FISH) {
				player.sendMessage(ChatColor.RED  + PescadoCocido);
					return;
			}
			if (player.getItemInHand().getType() == Material.RAW_FISH) {
			((Squid) clickedEntity).setTarget(null);
			player.getInventory().removeItem(new ItemStack (349, 1));
			clickedEntity.getWorld().dropItem(clickedEntity.getLocation(), new ItemStack(19, 1));
			clickedEntity.getWorld().playEffect(clickedEntity.getLocation(), Effect.STEP_SOUND, 19);
				}
			}
		}
	}
}