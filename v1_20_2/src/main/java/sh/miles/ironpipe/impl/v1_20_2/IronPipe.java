package sh.miles.ironpipe.impl.v1_20_2;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.Pipe;
import sh.miles.ironpipe.api.internal.PipeUnsafe;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.impl.v1_20_2.internal.IronPipeUnsafe;

public class IronPipe implements Pipe {


    @Override
    public <T extends ContainerScene> T openContainer(@NotNull final HumanEntity human, final ContainerType<T> type, final String title) {
        final T scene = type.create(human, title);
        this.openContainer(human, scene);
        return scene;
    }

    @Override
    public <T extends ContainerScene> T openContainer(@NotNull final HumanEntity human, final ContainerType<T> type, final BaseComponent... title) {
        final T scene = type.create(human, title);
        this.openContainer(human, scene);
        return scene;
    }

    @NotNull
    @Override
    public ContainerScene openContainer(@NotNull final HumanEntity human, @NotNull final ContainerScene scene) {
        human.openInventory(scene.getBukkitView());
        return scene;
    }

    @NotNull
    @Override
    public PipeUnsafe getUnsafe() {
        return IronPipeUnsafe.INSTANCE;
    }
}
