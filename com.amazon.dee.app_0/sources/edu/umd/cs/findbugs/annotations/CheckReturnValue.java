package edu.umd.cs.findbugs.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Deprecated
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes3.dex */
public @interface CheckReturnValue {
    Confidence confidence() default Confidence.MEDIUM;

    String explanation() default "";

    @Deprecated
    Priority priority() default Priority.MEDIUM;
}
