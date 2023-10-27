package sh.miles.ironpipe.api.internal;

import org.jetbrains.annotations.ApiStatus;
import sh.miles.ironpipe.api.inventory.ContainerType;

/**
 * An unstable internal class
 *
 * @since 2023.10.22
 */
@ApiStatus.Internal
public interface PipeUnsafe {
    ContainerType<?> getContainer(String id);
}
