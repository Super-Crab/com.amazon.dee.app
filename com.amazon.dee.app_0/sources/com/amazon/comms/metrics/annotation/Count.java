package com.amazon.comms.metrics.annotation;

import com.amazon.comms.metrics.MetricsDestination;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes11.dex */
public @interface Count {
    int increment() default 1;

    MetricsDestination metricsDestination() default MetricsDestination.DCM;

    String name();

    String source();
}
