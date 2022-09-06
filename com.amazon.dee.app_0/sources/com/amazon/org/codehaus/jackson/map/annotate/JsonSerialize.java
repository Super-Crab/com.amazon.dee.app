package com.amazon.org.codehaus.jackson.map.annotate;

import com.amazon.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes13.dex */
public @interface JsonSerialize {

    /* loaded from: classes13.dex */
    public enum Inclusion {
        ALWAYS,
        NON_NULL,
        NON_DEFAULT,
        NON_EMPTY
    }

    /* loaded from: classes13.dex */
    public enum Typing {
        DYNAMIC,
        STATIC
    }

    Class<?> as() default NoClass.class;

    Class<?> contentAs() default NoClass.class;

    Class<? extends JsonSerializer<?>> contentUsing() default JsonSerializer.None.class;

    Inclusion include() default Inclusion.ALWAYS;

    Class<?> keyAs() default NoClass.class;

    Class<? extends JsonSerializer<?>> keyUsing() default JsonSerializer.None.class;

    Typing typing() default Typing.DYNAMIC;

    Class<? extends JsonSerializer<?>> using() default JsonSerializer.None.class;
}
