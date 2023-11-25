package sh.miles.ironpipe.api;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.ironpipe.api.entity.PipeEntity;
import sh.miles.ironpipe.api.internal.PipeUnsafe;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.item.ItemStack;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;

import java.util.function.Consumer;

/**
 * Main Interface location for all IronPipe classes
 *
 * @since 2023.10.22
 */
public interface Pipe {

    /**
     * Opens a newly created empty container for the given player
     *
     * @param human the human to open the container for
     * @param type  the type of container to open
     * @param <T>   The ContainerScene type held by the container
     * @return the container scene
     * @since 2023.10.27
     */
    @Nullable
    <T extends ContainerScene> T openContainer(@NotNull final HumanEntity human, ContainerType<T> type, String title);


    /**
     * Opens a newly created empty container for the given player
     *
     * @param human the human to open the container for
     * @param type  the type of container to open
     * @param title the title to give the container
     * @param <T>   The ContainerScene type held by the container
     * @return the container scene
     * @since 2023.10.27
     */
    @Nullable
    <T extends ContainerScene> T openContainer(@NotNull final HumanEntity human, ContainerType<T> type, BaseComponent... title);

    /**
     * Opens a container scene for the given player
     *
     * @param human the player to open the scene for
     * @param scene the scene to open
     * @return the player to open the container for
     * @since 2023.10.27
     */
    @NotNull
    ContainerScene openContainer(@NotNull final HumanEntity human, @NotNull final ContainerScene scene);

    /**
     * Opens an inventory with a base component title. Note this implementation uses CraftContainer so Inventories
     * opened in this way will not work as intended
     *
     * @param player    the player
     * @param inventory the inventory to open
     * @param title     the title to use
     * @return the legacy bukkit inventory view associated with this task
     */
    @Nullable
    InventoryView openInventory(@NotNull final Player player, @NotNull final Inventory inventory, @NotNull BaseComponent... title);

    /**
     * Spawns an entity within the world at the specified locations and executes a specific set of modifications before
     * adding it to the world
     *
     * @param world         the world to spawn the entity in
     * @param location      the location to spawn the entity at
     * @param clazz         the class type of the entity
     * @param modifications the modifications to make before spawning it in
     */
    <T extends Entity> void spawnEntity(@NotNull final World world, @NotNull final Location location, Class<T> clazz, Consumer<PipeEntity> modifications);

    /**
     * Creates a new item stack
     *
     * @param material the material to create the item with
     * @return the newly created ItemStack
     * @since 2023.10.28
     */
    @NotNull
    default ItemStack newItem(@NotNull final Material material) {
        return getUnsafe().newItem(material);
    }

    /**
     * @return the unsafe class from pipe
     * @since 2023.10.22
     */
    @NotNull
    @ApiStatus.Internal
    PipeUnsafe getUnsafe();

}
