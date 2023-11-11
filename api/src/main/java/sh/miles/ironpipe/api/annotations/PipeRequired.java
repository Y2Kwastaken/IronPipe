package sh.miles.ironpipe.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used in other libraries to specify what does and doesn't require iron pipe
 */
@Inherited
@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface PipeRequired {
}
