package sh.miles.ironpipe.api.inventory.scene;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * Similar in function to {@link InventoryView}, all children provide useful functions for changing data that should
 * otherwise be managed within the InventoryView
 *
 * @since 2023.10.22
 */
public interface ContainerScene {

    /**
     * @return the base component title of the scene
     */
    BaseComponent getTitle();

    /**
     * Note: Using this will cause de-sync with Bukkit's getTitle method
     *
     * @param title sets the component title of the scene
     */
    void setTitle(BaseComponent... title);

    /**
     * Gets the bukkit view this ContainerScene is based upon
     *
     * @return the inventory view
     */
    @NotNull
    InventoryView getBukkitView();
}
