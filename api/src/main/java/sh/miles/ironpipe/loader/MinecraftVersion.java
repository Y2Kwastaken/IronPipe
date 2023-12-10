package sh.miles.ironpipe.loader;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that represents a minecraft version
 *
 * @since 1.0.0
 */
public class MinecraftVersion {

    private final int major;
    private final int minor;
    private final int patch;

    public static final MinecraftVersion CURRENT;
    public static final MinecraftVersion v1_19_4 = new MinecraftVersion(1, 19, 4);
    public static final MinecraftVersion V1_20_2 = new MinecraftVersion(1, 20, 2);
    public static final MinecraftVersion V1_20_4 = new MinecraftVersion(1, 20, 4);

    static {
        String[] split = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
        final int major = Integer.parseInt(split[0]);
        final int minor = Integer.parseInt(split[1]);
        boolean hasPatch = split.length == 3;
        final int patch = hasPatch ? Integer.parseInt(split[2]) : 0;

        CURRENT = new MinecraftVersion(major, minor, patch);
    }

    private MinecraftVersion(final int major, final int minor) {
        this(major, minor, 0);
    }

    private MinecraftVersion(final int major, final int minor, final int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * Gets major number
     *
     * @return the major number
     */
    public int getMajor() {
        return major;
    }

    /**
     * gets minor number
     *
     * @return the minor number
     */
    public int getMinor() {
        return minor;
    }

    /**
     * gets patch number or 0 if none
     *
     * @return patch number or 0
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Gets the standard name of the version e.g. 1.19.4
     *
     * @return the name of the version
     */
    @NotNull
    public String getName() {
        return "%d.%d%s".formatted(major, minor, patch == 0 ? "" : "." + patch);
    }

    /**
     * Gets the internal name used in Suketto
     *
     * @return the internal name in format vMajor_Minor_Patch
     */
    @NotNull
    public String getInternalName() {
        return "v%d_%d%s".formatted(major, minor, patch == 0 ? "" : "_" + patch);
    }

    @NotNull
    public int[] toArray() {
        return patch == 0 ? new int[]{major, minor} : new int[]{major, minor, patch};
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MinecraftVersion that)) return false;
        return major == that.major && minor == that.minor && patch == that.patch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }

    @NotNull
    public static MinecraftVersion fromArray(int[] majorMinorPatch) throws IllegalArgumentException {
        Preconditions.checkArgument(majorMinorPatch.length > 1, "This method must be provided an array with 2 or 3 entries in the format. Major,Minor,Patch or Major,Minor");
        if (majorMinorPatch.length == 2) {
            return new MinecraftVersion(majorMinorPatch[0], majorMinorPatch[1]);
        }
        return new MinecraftVersion(majorMinorPatch[0], majorMinorPatch[1], majorMinorPatch[2]);
    }
}
