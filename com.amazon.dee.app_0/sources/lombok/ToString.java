package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface ToString {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Exclude {
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Include {
        String name() default "";

        int rank() default 0;
    }

    boolean callSuper() default false;

    boolean doNotUseGetters() default false;

    String[] exclude() default {};

    boolean includeFieldNames() default true;

    String[] of() default {};

    boolean onlyExplicitlyIncluded() default false;
}
