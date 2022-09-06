package com.amazon.org.codehaus.jackson.schema;

import com.amazon.org.codehaus.jackson.annotate.JacksonAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes13.dex */
public @interface JsonSerializableSchema {
    String schemaItemDefinition() default "##irrelevant";

    String schemaObjectPropertiesDefinition() default "##irrelevant";

    String schemaType() default "any";
}
