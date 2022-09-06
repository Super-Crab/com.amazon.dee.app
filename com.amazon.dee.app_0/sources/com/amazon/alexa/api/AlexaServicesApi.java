package com.amazon.alexa.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes6.dex */
@interface AlexaServicesApi {
    Exposure exposure() default Exposure.HIDDEN;

    String nameOverride() default "";

    UseCase[] useCases() default {UseCase.PRIVILEGED};
}
