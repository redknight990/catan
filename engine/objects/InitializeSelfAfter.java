package objects;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InitializeSelfAfter {
    Class<? extends GameScript> clazz();
}
