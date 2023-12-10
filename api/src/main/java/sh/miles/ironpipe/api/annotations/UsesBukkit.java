package sh.miles.ironpipe.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Signifies that the given method no longer uses IronPipe NMS code, but rather uses bukkit as a back end. a class or
 * method being annotated with this method indicates that it is now within the bukkit api and that it is safe to use the
 * underlying bukkit code instead of iron pipe.
 *
 * @since 1.0.0
 */
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface UsesBukkit {
}
