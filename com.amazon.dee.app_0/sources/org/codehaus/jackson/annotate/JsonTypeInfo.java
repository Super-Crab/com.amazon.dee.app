package org.codehaus.jackson.annotate;

import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes5.dex */
public @interface JsonTypeInfo {

    /* loaded from: classes5.dex */
    public enum As {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY
    }

    /* loaded from: classes5.dex */
    public enum Id {
        NONE(null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE),
        CUSTOM(null);
        
        private final String _defaultPropertyName;

        Id(String str) {
            this._defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return this._defaultPropertyName;
        }
    }

    /* loaded from: classes5.dex */
    public static abstract class None {
    }

    Class<?> defaultImpl() default None.class;

    As include() default As.PROPERTY;

    String property() default "";

    Id use();
}
