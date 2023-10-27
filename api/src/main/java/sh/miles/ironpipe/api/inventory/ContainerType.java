package sh.miles.ironpipe.api.inventory;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Keyed;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.inventory.scene.AnvilScene;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.loader.IronPipe;

/**
 * Represents a container that can be created and viewed by a player
 *
 * @param <T> the container scene
 * @since 2023.10.22
 */
public interface ContainerType<T extends ContainerScene> extends Keyed {
    ContainerType<ContainerScene> GENERIC_9x1 = get("generic_9x1");
    ContainerType<ContainerScene> GENERIC_9x2 = get("generic_9x2");
    ContainerType<ContainerScene> GENERIC_9x3 = get("generic_9x3");
    ContainerType<ContainerScene> GENERIC_9x4 = get("generic_9x4");
    ContainerType<ContainerScene> GENERIC_9x5 = get("generic_9x5");
    ContainerType<ContainerScene> GENERIC_9x6 = get("generic_9x6");
    ContainerType<ContainerScene> GENERIC_3x3 = get("generic_3x3");
    ContainerType<AnvilScene> ANVIL = get("anvil");
    ContainerType<ContainerScene> BEACON = get("beacon");
    ContainerType<ContainerScene> BLAST_FURNACE = get("blast_furnace");
    ContainerType<ContainerScene> BREWING_STAND = get("brewing_stand");
    ContainerType<ContainerScene> CRAFTING = get("crafting");
    ContainerType<ContainerScene> ENCHANTMENT = get("enchantment");
    ContainerType<ContainerScene> FURNACE = get("furnace");
    ContainerType<ContainerScene> GRINDSTONE = get("grindstone");
    ContainerType<ContainerScene> HOPPER = get("hopper");
    ContainerType<ContainerScene> LECTERN = get("lectern");
    ContainerType<ContainerScene> LOOM = get("loom");
    ContainerType<ContainerScene> MERCHANT = get("merchant");
    ContainerType<ContainerScene> SHULKER_BOX = get("shulker_box");
    ContainerType<ContainerScene> SMITHING = get("smithing");
    ContainerType<ContainerScene> SMOKER = get("smoker");
    ContainerType<ContainerScene> CARTOGRAPHY_TABLE = get("cartography_table");
    ContainerType<ContainerScene> STONECUTTER = get("stonecutter");

    /**
     * Creates a ContainerScene of this container for the player with the provided title
     *
     * @param player the player
     * @param title  the title
     * @return a container scene
     */
    T create(@NotNull final HumanEntity player, @NotNull final String title);

    /**
     * Creates a ContainerScene of this container for the player with the provided title
     *
     * @param player the player
     * @param title  the title
     * @return a container scene
     */
    T create(@NotNull final HumanEntity player, @NotNull final BaseComponent... title);

    @SuppressWarnings("unchecked")
    private static <T extends ContainerScene> ContainerType<T> get(String id) {
        return (ContainerType<T>) IronPipe.getInstance().getUnsafe().getContainer(id);
    }
}
