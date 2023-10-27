package sh.miles.ironpipe.impl.v1_20.inventory.scene;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.impl.v1_20.internal.ComponentUtils;

public class IronContainerScene<T extends AbstractContainerMenu> implements ContainerScene {

    private final CraftInventoryView view;
    protected final T container;

    @SuppressWarnings("unchecked")
    public IronContainerScene(CraftInventoryView view) {
        this.view = view;
        this.container = (T) view.getHandle();
    }

    @Override
    public BaseComponent getTitle() {
        return ComponentUtils.toBungeeChat(container.getTitle());
    }

    @Override
    public void setTitle(final BaseComponent... title) {
        container.setTitle(ComponentUtils.toMinecraftChat(title));
    }

    @NotNull
    @Override
    public InventoryView getBukkitView() {
        return this.view;
    }
}
