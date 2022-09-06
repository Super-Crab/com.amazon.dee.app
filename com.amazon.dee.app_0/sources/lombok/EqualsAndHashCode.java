package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface EqualsAndHashCode {

    @Target({})
    @Deprecated
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface AnyAnnotation {
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Exclude {
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Include {
        String replaces() default "";
    }

    boolean callSuper() default false;

    boolean doNotUseGetters() default false;

    String[] exclude() default {};

    String[] of() default {};

    AnyAnnotation[] onParam() default {};

    boolean onlyExplicitlyIncluded() default false;
}
