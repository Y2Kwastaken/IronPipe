package sh.miles.ironpipe.impl.v1_19_4.internal;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;
import sh.miles.ironpipe.api.internal.PipeUnsafe;
import sh.miles.ironpipe.api.inventory.ContainerType;
import sh.miles.ironpipe.api.inventory.item.ItemStack;
import sh.miles.ironpipe.impl.v1_19_4.inventory.IronContainerType;
import sh.miles.ironpipe.impl.v1_19_4.inventory.item.IronItemStack;

public class IronPipeUnsafe implements PipeUnsafe {

    public static final IronPipeUnsafe INSTANCE = new IronPipeUnsafe();

    private final CraftServer server = (CraftServer) Bukkit.getServer();

    private IronPipeUnsafe() {
    }

    @Override
    public ContainerType<?> getContainer(final String id) {
        return new IronContainerType<>(NamespacedKey.minecraft(id), getRegistry(Registries.MENU).get(new ResourceLocation("minecraft:%s".formatted(id))));
    }

    @Override
    public ItemStack newItem(final Material material) {
        return new IronItemStack(material);
    }

    public <T> Registry<T> getRegistry(ResourceKey<? extends Registry<T>> registryKey) {
        return server.getHandle().getServer().registryAccess().registryOrThrow(registryKey);
    }

}
