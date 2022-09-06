package com.amazon.alexa.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes6.dex */
public @interface ApiType {
    String defaultReturnValueName() default "";

    Exposure exposure() default Exposure.HIDDEN;

    String nameOverride() default "";

    String responseEnumNameOverride() default "";

    UseCase[] useCases() default {UseCase.PRIVILEGED};
}
