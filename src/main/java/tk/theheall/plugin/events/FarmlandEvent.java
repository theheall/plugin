package tk.theheall.plugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FarmlandEvent implements Listener {

    @EventHandler
    public void onFarmlandEvent(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Action action = event.getAction();

        if (action.equals(Action.PHYSICAL)) {
            assert block != null;
            if (block.getType().equals(Material.FARMLAND)) event.setCancelled(true);
        }
    }
}
