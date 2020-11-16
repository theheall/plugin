package tk.theheall.plugin.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class SeedEvent implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Action action = event.getAction();
        final Block block = event.getClickedBlock();

        if (player.isSneaking()) return;

        if (action.equals(Action.LEFT_CLICK_BLOCK)) {
            assert block != null;

            if (block.getBlockData() instanceof Ageable)
                event.setCancelled(true);
        }

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            assert block != null;

            if (block.getBlockData() instanceof Ageable) {
                Ageable crop = (Ageable) block.getBlockData();

                if (crop.getAge() == crop.getMaximumAge()) {
                    block.getDrops().forEach(item -> {
                        if (isSeed(item.getType()) && item.getAmount() > 1) {
                            item.setAmount(item.getAmount() - 1);
                        }
                        block.getWorld().dropItemNaturally(block.getLocation(), item);
                    });
                    crop.setAge(0);
                    block.setBlockData(crop, true);
                }
            }
        }
    }

    private boolean isSeed(Material type) {
        switch (type) {
            case WHEAT_SEEDS:
            case CARROT:
            case POTATO:
            case BEETROOT_SEEDS:
                return true;
        }

        return false;
    }
}
