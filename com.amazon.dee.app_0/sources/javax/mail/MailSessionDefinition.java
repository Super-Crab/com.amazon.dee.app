package javax.mail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface MailSessionDefinition {
    String description() default "";

    String from() default "";

    String host() default "";

    String name();

    String password() default "";

    String[] properties() default {};

    String storeProtocol() default "";

    String transportProtocol() default "";

    String user() default "";
}
