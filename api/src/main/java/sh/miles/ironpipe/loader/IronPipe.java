package sh.miles.ironpipe.loader;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.Pipe;
import sh.miles.ironpipe.api.internal.PipeUnsafe;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.loader.internal.ReflectionUtils;

/**
 * Provides API for IronPipe
 *
 * @since 2023.10.22
 */
@ApiStatus.AvailableSince("2023.10.22")
public final class IronPipe implements Pipe {

    private static final String PATH = "sh.miles.ironpipe.impl.%s.%s%s";
    private static final String PREFIX = "Iron";
    private static IronPipe instance;

    private Pipe pipe;

    private IronPipe() {
        this.pipe = getHandle(Pipe.class);
    }

    /**
     * Opens a newly created empty container for the given player
     *
     * @param human the human to open the container for
     * @param type  the type of container to open
     * @param <T>   The ContainerScene type held by the container
     * @return the container scene
     * @since 2023.10.27
     */
    @Override
    public <T extends ContainerScene> T openContainer(@NotNull final HumanEntity human, final ContainerType<T> type, final String title) {
        return pipe.openContainer(human, type, title);
    }

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
    @Override
    public <T extends ContainerScene> T openContainer(@NotNull final HumanEntity human, final ContainerType<T> type, final BaseComponent... title) {
        return pipe.openContainer(human, type, title);
    }

    /**
     * Opens a container scene for the given player
     *
     * @param human the player to open the scene for
     * @param scene the scene to open
     * @return the player to open the container for
     * @since 2023.10.27
     */
    @NotNull
    @Override
    public ContainerScene openContainer(@NotNull final HumanEntity human, @NotNull final ContainerScene scene) {
        return pipe.openContainer(human, scene);
    }

    /**
     * @return the unsafe class from pipe
     * @since 2023.10.22
     */
    @NotNull
    @Override
    @ApiStatus.Internal
    public PipeUnsafe getUnsafe() {
        return pipe.getUnsafe();
    }


    @NotNull
    public static IronPipe getInstance() {
        if (instance == null) {
            instance = new IronPipe();
        }
        return instance;
    }

    /**
     * Creates a NMS handle
     *
     * @param clazz the class to get hte handle
     * @param <T>   the type
     * @return returns the type of handle
     */
    @ApiStatus.Internal
    private static <T> T getHandle(Class<T> clazz) {
        return ReflectionUtils.newInstance(PATH.formatted(MinecraftVersion.CURRENT.getInternalName(), PREFIX, clazz.getSimpleName()), new Object[0]);
    }

}
