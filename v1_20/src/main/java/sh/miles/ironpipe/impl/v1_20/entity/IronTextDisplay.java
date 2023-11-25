package sh.miles.ironpipe.impl.v1_20.entity;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTextDisplay;
import sh.miles.ironpipe.api.entity.TextDisplay;
import sh.miles.ironpipe.impl.v1_20.internal.ComponentUtils;

public class IronTextDisplay implements TextDisplay {

    private CraftTextDisplay bukkitDisplay;

    public IronTextDisplay(CraftTextDisplay bukkitDisplay) {
        this.bukkitDisplay = bukkitDisplay;
    }

    @Override
    public void setText(final BaseComponent... text) {
        bukkitDisplay.getHandle().setText(ComponentUtils.toMinecraftChat(text));
    }

    @Override
    public org.bukkit.entity.TextDisplay getBukkit() {
        return this.bukkitDisplay;
    }
}
