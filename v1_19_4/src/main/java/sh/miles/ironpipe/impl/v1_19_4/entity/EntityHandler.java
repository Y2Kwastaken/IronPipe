package sh.miles.ironpipe.impl.v1_19_4.entity;

import org.bukkit.craftbukkit.v1_19_R3.entity.CraftTextDisplay;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sh.miles.ironpipe.api.entity.PipeEntity;
import sh.miles.ironpipe.api.entity.TextDisplay;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class EntityHandler {

    public static final EntityHandler INSTANCE = new EntityHandler();

    private final Map<Class<? extends Entity>, Function<Entity, PipeEntity>> builder;

    private EntityHandler() {
        this.builder = new HashMap<>();
        this.builder.put(org.bukkit.entity.TextDisplay.class, (Entity display) -> new IronTextDisplay((CraftTextDisplay) display));
    }

    @Nullable
    public PipeEntity build(@NotNull final Entity bukkit) {
        Function<Entity, PipeEntity> function = builder.get(bukkit.getClass());
        if (function == null) {
            return null;
        }
        return function.apply(bukkit);
    }

}
