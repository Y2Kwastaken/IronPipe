package sh.miles.ironpipe.impl.v1_20;

import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftContainer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.ironpipe.api.Pipe;
import sh.miles.ironpipe.api.internal.PipeUnsafe;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.impl.v1_20.internal.ComponentUtils;
import sh.miles.ironpipe.impl.v1_20.internal.IronPipeUnsafe;

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


    @Nullable
    @Override
    public InventoryView openInventory(@NotNull final Player player, @NotNull final Inventory inventory, @NotNull final BaseComponent... title) {
        ServerPlayer nms = ((CraftPlayer) player).getHandle();
        // legacy method to be replaced
        MenuType<?> mojType = CraftContainer.getNotchInventoryType(inventory);

        if (mojType == null) {
            throw new IllegalArgumentException("could not find menu type for inventory type of " + inventory.getType());
        }
        AbstractContainerMenu menu = new CraftContainer(inventory, nms, nms.nextContainerCounter());
        menu = CraftEventFactory.callInventoryOpenEvent(nms, menu);
        if (menu == null) {
            throw new IllegalStateException("Unable to open menu for unknown reason");
        }

        nms.connection.send(new ClientboundOpenScreenPacket(menu.containerId, mojType, ComponentUtils.toMinecraftChat(title)));
        nms.containerMenu = menu;
        nms.initMenu(menu);
        return nms.containerMenu.getBukkitView();
    }

    @NotNull
    @Override
    public PipeUnsafe getUnsafe() {
        return IronPipeUnsafe.INSTANCE;
    }
}
