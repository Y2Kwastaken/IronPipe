package sh.miles.ironpipe.api;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.internal.PipeUnsafe;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;

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
     * @return the unsafe class from pipe
     * @since 2023.10.22
     */
    @NotNull
    @ApiStatus.Internal
    PipeUnsafe getUnsafe();

}
