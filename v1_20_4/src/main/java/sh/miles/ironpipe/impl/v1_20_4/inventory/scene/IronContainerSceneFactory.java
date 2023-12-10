package sh.miles.ironpipe.impl.v1_20_4.inventory.scene;

import net.minecraft.world.inventory.MenuType;
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftInventoryView;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.scene.ContainerScene;
import sh.miles.ironpipe.impl.v1_20_4.inventory.IronContainerType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class IronContainerSceneFactory {

    private static final Map<MenuType<?>, Function<CraftInventoryView, ContainerScene>> factory = new HashMap<>();

    static {
        factory.put(MenuType.ANVIL, IronAnvilScene::new);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ContainerScene> T make(ContainerType<T> type, CraftInventoryView view) {
        return (T) factory.getOrDefault(((IronContainerType<T>) type).getHandle(), IronContainerScene::new).apply(view);
    }
}
