package com.amazon.org.codehaus.jackson.map.annotate;

import com.amazon.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes13.dex */
public @interface JsonDeserialize {
    Class<?> as() default NoClass.class;

    Class<?> contentAs() default NoClass.class;

    Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;

    Class<?> keyAs() default NoClass.class;

    Class<? extends KeyDeserializer> keyUsing() default KeyDeserializer.None.class;

    Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}
