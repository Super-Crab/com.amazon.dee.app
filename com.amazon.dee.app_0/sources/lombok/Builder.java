package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface Builder {

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface Default {
    }

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface ObtainVia {
        String field() default "";

        boolean isStatic() default false;

        String method() default "";
    }

    AccessLevel access() default AccessLevel.PUBLIC;

    String buildMethodName() default "build";

    String builderClassName() default "";

    String builderMethodName() default "builder";

    boolean toBuilder() default false;
}
