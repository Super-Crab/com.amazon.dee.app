package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface Getter {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface AnyAnnotation {
    }

    boolean lazy() default false;

    AnyAnnotation[] onMethod() default {};

    AccessLevel value() default AccessLevel.PUBLIC;
}
