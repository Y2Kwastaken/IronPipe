package sh.miles.ironpipe.impl.v1_19_4.inventory;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryView;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.impl.v1_19_4.internal.ComponentUtils;
import sh.miles.ironpipe.impl.v1_19_4.inventory.scene.IronContainerSceneFactory;

public record IronContainerType<T extends ContainerScene>(NamespacedKey key,
                                                          MenuType<?> handle) implements ContainerType<T> {

    @Override
    public T create(@NotNull final HumanEntity player, @NotNull final String title) {
        return create(player, TextComponent.fromLegacyText(title));
    }

    @Override
    public T create(@NotNull final HumanEntity player, @NotNull final BaseComponent... title) {
        Preconditions.checkArgument(player != null, "there must be a valid player to use");
        Preconditions.checkArgument(title != null, "there must be a valid title to use");
        final AbstractContainerMenu menu = ContainerBuilder.INSTANCE.build((CraftHumanEntity) player, this.handle);
        menu.setTitle(ComponentUtils.toMinecraftChat(title));
        menu.checkReachable = false;
        return IronContainerSceneFactory.make(this, (CraftInventoryView) menu.getBukkitView());
    }

    public MenuType<?> getHandle() {
        return handle;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }
}
