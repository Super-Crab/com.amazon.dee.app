package com.amazon.comms.log.annotation;

import com.amazon.comms.log.LogLevel;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes11.dex */
public @interface Log {
    LogLevel level() default LogLevel.Info;

    String message() default "";
}
