package me.theheall.plugin.events;

import me.theheall.plugin.PlayerInventory;
import me.theheall.plugin.file.ParkourFile;
import me.theheall.plugin.games.parkour.Checkpoint;
import me.theheall.plugin.games.parkour.ParkourGame;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ParkourEvent implements Listener {

    @EventHandler
    public void onPoint(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        ParkourFile config = new ParkourFile();

        if(action.equals(Action.PHYSICAL)) {
            assert block != null;
            if (config.hasCheckpoint(block)) {
                Checkpoint checkpoint = config.getCheckpoint(block);

                switch (checkpoint.getType()) {
                    case START:
                        ParkourGame.start(player, checkpoint);
                        break;

                    case MIDDLE:
                        ParkourGame.checkpoint(player, checkpoint);
                        break;

                    case FINISH:
                        ParkourGame.finish(player);
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();


        if((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))) {
            switch (Objects.requireNonNull(item).getType()) {
                case HEAVY_WEIGHTED_PRESSURE_PLATE:
                    event.setCancelled(true);
                    ParkourGame.teleport(player);
                    break;

                case RED_BED:
                    event.setCancelled(true);
                    ParkourGame.stop(player);
                    PlayerInventory.open(player, PlayerInventory.Type.MENU);
                    break;

                case OAK_DOOR:
                    event.setCancelled(true);
                    ParkourGame.reset(player);
                    break;
            }
        }
    }
}
