package tk.theheall.plugin.events;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import tk.theheall.plugin.Chat;
import tk.theheall.plugin.files.LocationFile;

import java.util.Arrays;
import java.util.Objects;

public class MenuEvent implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Chat chat = new Chat(player);
        int slot = event.getSlot();
        String menu = event.getView().getTitle();

        switch (menu) {
            case "Warps":
                event.setCancelled(true);
                if(LocationFile.hasLocation(slot)) {
                    player.teleport(LocationFile.getLocationBySlot(slot));
                    ItemStack item = LocationFile.getLocationItemBySlot(slot);

                    TextComponent warp = new TextComponent(LocationFile.getLocationNameBySlot(slot));
                    warp.setBold(true);
                    warp.setColor(ChatColor.DARK_AQUA);
                    warp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            Arrays.asList(
                                    new Text(Objects.requireNonNull(item.getItemMeta()).getDisplayName() + "\n"),
                                    new Text(Objects.requireNonNull(item.getItemMeta().getLore()).get(0)))));

                    TextComponent end = new TextComponent(" «");
                    end.setColor(ChatColor.AQUA);

                    TextComponent message = new TextComponent("» Sei stato teletrasportato nel warp ");
                    message.setColor(ChatColor.AQUA);
                    message.addExtra(warp);
                    message.addExtra(end);

                    player.spigot().sendMessage(message);
                }
                break;
        }
    }
}
