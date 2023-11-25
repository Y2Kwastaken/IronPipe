package sh.miles.ironpipe.api.entity;

import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Represents an entity equivalent to {@link org.bukkit.entity.TextDisplay} that displays text from the source of an
 * entity within the world
 */
public interface TextDisplay extends PipeEntity {

    /**
     * Sets the text of the current display entity to the given components
     *
     * @param text the text to display on the entity
     */
    void setText(BaseComponent... text);

    /**
     * Gets the bukkit TextDisplay
     *
     * @return the bukkit text display
     */
    org.bukkit.entity.TextDisplay getBukkit();
}
