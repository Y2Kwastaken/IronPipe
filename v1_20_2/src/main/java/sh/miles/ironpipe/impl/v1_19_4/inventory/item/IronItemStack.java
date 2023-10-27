package sh.miles.ironpipe.impl.v1_19_4.inventory.item;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R2.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R2.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R2.util.CraftNamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sh.miles.ironpipe.api.inventory.item.ItemStack;
import sh.miles.ironpipe.api.inventory.item.ToolTip;
import sh.miles.ironpipe.impl.v1_19_4.internal.ComponentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IronItemStack implements ItemStack {

    private final net.minecraft.world.item.ItemStack minecraft;

    public IronItemStack(Material bukkit) {
        minecraft = new net.minecraft.world.item.ItemStack(CraftMagicNumbers.getItem(bukkit));
    }

    @Override
    public @NotNull BaseComponent getName() {
        return ComponentUtils.toBungeeChat(minecraft.getHoverName());
    }

    @Override
    public void setName(@NotNull final BaseComponent... component) {
        minecraft.setHoverName(ComponentUtils.toMinecraftChat(component));
    }

    @NotNull
    @Override
    public List<BaseComponent> getLore() {
        final CompoundTag tag = minecraft.getTag();
        if (tag == null) {
            return List.of();
        }

        final CompoundTag displayTag = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
        if (displayTag == null) {
            return List.of();
        }

        final ListTag loreTag = displayTag.getList(net.minecraft.world.item.ItemStack.TAG_LORE, CraftMagicNumbers.NBT.TAG_LIST);
        if (loreTag == null) {
            return List.of();
        }

        final List<BaseComponent> lore = new ArrayList<>();
        for (int i = 0; i < loreTag.size(); i++) {
            lore.add(ComponentUtils.toBungeeChat(loreTag.getString(i)));
        }

        return lore;
    }

    @Override
    public void setLore(@NotNull final List<BaseComponent> lore) {
        setLore(minecraft, lore, (index, tag, list) -> {
            tag.add(index, StringTag.valueOf(ComponentUtils.toJsonString(lore.get(index))));
        });
    }

    @Override
    public void setLoreArray(@NotNull final List<BaseComponent[]> lore) {
        setLore(minecraft, lore, (index, tag, list) -> {
            tag.add(index, StringTag.valueOf(ComponentUtils.toJsonString(lore.get(index))));
        });
    }

    @Override
    public void enchant(@NotNull final Enchantment enchantment, final short level) {
        final net.minecraft.world.item.enchantment.Enchantment nms = BuiltInRegistries.ENCHANTMENT.get(CraftNamespacedKey.toMinecraft(enchantment.getKey()));
        minecraft.enchant(nms, level);
    }

    @NotNull
    @Override
    public Map<Enchantment, Short> getEnchantments() {
        final Map<Enchantment, Short> iron = new HashMap<>();
        final Map<net.minecraft.world.item.enchantment.Enchantment, Integer> nms = EnchantmentHelper.getEnchantments(minecraft);
        for (final net.minecraft.world.item.enchantment.Enchantment enchantment : nms.keySet()) {
            final Enchantment bukkit = Enchantment.getByKey(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.ENCHANTMENT.getKey(enchantment)));
            iron.put(bukkit, nms.get(enchantment).shortValue());
        }
        return iron;
    }

    @Override
    public boolean isEnchantable() {
        return minecraft.isEnchantable();
    }

    @Override
    public int getDamage() {
        return minecraft.getDamageValue();
    }

    @Override
    public void setDamage(final int damage) {
        minecraft.setDamageValue(damage);
    }

    @Override
    public int getMaxDamage() {
        return minecraft.getMaxDamage();
    }

    @Override
    public void damage(final int amount, final Player player) {
        final ServerPlayer nms = ((CraftPlayer) player).getHandle();
        minecraft.hurtAndBreak(amount, nms, (human) -> {
            human.broadcastBreakEvent(human.getUsedItemHand());
        });
    }

    @Override
    public void hideToolTip(final ToolTip tooltip) {
        minecraft.hideTooltipPart(net.minecraft.world.item.ItemStack.TooltipPart.values()[tooltip.ordinal()]);
    }

    @Override
    public void grow(final byte amount) {
        Preconditions.checkArgument(amount < 0, "You can not grow an item stack by a negative amount");
        Preconditions.checkArgument(amount + minecraft.getCount() > 64, "You can not grow an item stack to exceed 64");
        minecraft.grow(amount);
    }

    @Override
    public void shrink(byte amount) {
        amount = (byte) Math.abs(amount);
        Preconditions.checkArgument(getCount() - amount < 0, "You can not shrink an item stack below 0");
        minecraft.shrink(amount);
    }

    @Override
    public byte getCount() {
        return (byte) minecraft.getCount();
    }

    @NotNull
    @Override
    public org.bukkit.inventory.ItemStack asBukkitCopy() {
        return CraftItemStack.asCraftMirror(minecraft.copy());
    }

    private static <T> void setLore(@NotNull final net.minecraft.world.item.ItemStack minecraft, @NotNull final List<T> lore, @NotNull final LoreFunction<T> function) {
        final CompoundTag tag = minecraft.getTag() == null ? new CompoundTag() : minecraft.getTag();
        if (!tag.contains(net.minecraft.world.item.ItemStack.TAG_DISPLAY)) {
            tag.put(net.minecraft.world.item.ItemStack.TAG_DISPLAY, new CompoundTag());
        }

        final CompoundTag displayTag = tag.getCompound(net.minecraft.world.item.ItemStack.TAG_DISPLAY);
        ListTag loreTag = new ListTag();
        for (int i = 0; i < lore.size(); i++) {
            function.consume(i, loreTag, lore);
        }

        displayTag.put(net.minecraft.world.item.ItemStack.TAG_LORE, loreTag);
    }

    private interface LoreFunction<E> {
        void consume(int index, ListTag tag, List<E> list);
    }
}
