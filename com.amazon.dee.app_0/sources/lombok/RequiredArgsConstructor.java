package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface RequiredArgsConstructor {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface AnyAnnotation {
    }

    AccessLevel access() default AccessLevel.PUBLIC;

    AnyAnnotation[] onConstructor() default {};

    String staticName() default "";
}
