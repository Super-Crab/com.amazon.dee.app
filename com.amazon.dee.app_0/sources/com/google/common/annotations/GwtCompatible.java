package com.google.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.METHOD})
@GwtCompatible
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface GwtCompatible {
    boolean emulated() default false;

    boolean serializable() default false;
}
