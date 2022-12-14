package org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes5.dex */
public @interface JsonSetter {
    String value() default "";
}
