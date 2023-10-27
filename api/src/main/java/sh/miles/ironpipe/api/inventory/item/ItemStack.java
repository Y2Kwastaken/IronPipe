package sh.miles.ironpipe.api.inventory.item;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * an Item that can be used
 */
public interface ItemStack {

    /**
     * @return the name of the item
     */
    @NotNull
    BaseComponent getName();

    /**
     * Sets the name of the item
     *
     * @param component the component to use
     */
    void setName(@NotNull final BaseComponent... component);

    /**
     * @return the lore on this item
     */
    @NotNull
    List<BaseComponent> getLore();

    /**
     * Sets the lore on this item
     *
     * @param lore the lore to set
     */
    void setLoreArray(@NotNull final List<BaseComponent[]> lore);

    /**
     * Sets the lore on this item
     *
     * @param lore the lore to set
     */
    void setLore(@NotNull final List<BaseComponent> lore);

    /**
     * Enchants the item with the provided enchant at the level
     *
     * @param enchantment the enchantment to enchant this item with
     * @param level       the level to set the enchantment to
     */
    void enchant(@NotNull final Enchantment enchantment, final short level);

    /**
     * @return a map of enchantments on this item
     */
    @NotNull
    Map<Enchantment, Short> getEnchantments();

    /**
     * Gets whether the item is enchantable within the vanilla game. Note, if the item is not enchantable within the
     * vanilla game that does not mean that the item can not be enchanted
     *
     * @return true if the item is enchantable within the vanilla game, otherwise false
     */
    boolean isEnchantable();

    /**
     * @return the damage value of this item
     */
    int getDamage();

    /**
     * Sets the damage value of this item
     *
     * @param damage the damage value to set
     */
    void setDamage(int damage);

    /**
     * Gets the max damage attribute of this item
     *
     * @return the max damage
     */
    int getMaxDamage();

    /**
     * Damages this item for the player
     *
     * @param amount the amount to damage this item
     * @param player the player to damage the item for
     */
    void damage(int amount, Player player);

    /**
     * Hides a tooltip on this item
     * @param tooltip the tooltip to hide
     */
    void hideToolTip(ToolTip tooltip);

    /**
     * Grows the item stack by the given amount, must be between 0-63
     *
     * @param amount the amount to grow the stack by
     */
    void grow(final byte amount);

    /**
     * Grows the item stack by the given amount, must be between 0-64
     *
     * @param amount the amount to grow the stack by
     */
    void shrink(final byte amount);

    /**
     * @return the count of this item
     */
    byte getCount();

    /**
     * @return the bukkit item stack
     */
    @NotNull
    org.bukkit.inventory.ItemStack asBukkitCopy();
}
