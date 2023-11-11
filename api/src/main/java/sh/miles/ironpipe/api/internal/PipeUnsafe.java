package sh.miles.ironpipe.api.internal;

import org.bukkit.Material;
import org.jetbrains.annotations.ApiStatus;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.item.ItemStack;

/**
 * An unstable internal class
 *
 * @since 2023.10.22
 */
@ApiStatus.Internal
public interface PipeUnsafe {
    ContainerType<?> getContainer(String id);

    ItemStack newItem(Material material);
}
