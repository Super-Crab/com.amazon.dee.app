package com.amazon.alexa.accessory.speechapi.context;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ContextType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/context/ContextType;", "", "(Ljava/lang/String;I)V", "IO_COMPONENTS", "TRUSTED_STATE", "ALEXA_ACCESSORY", "UNKNOWN", "Companion", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum ContextType {
    IO_COMPONENTS,
    TRUSTED_STATE,
    ALEXA_ACCESSORY,
    UNKNOWN;
    
    public static final Companion Companion = new Companion(null);

    /* compiled from: ContextType.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/context/ContextType$Companion;", "", "()V", "fromString", "Lcom/amazon/alexa/accessory/speechapi/context/ContextType;", "value", "", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final ContextType fromString(@NotNull String value) {
            ContextType[] values;
            Intrinsics.checkParameterIsNotNull(value, "value");
            for (ContextType contextType : ContextType.values()) {
                if (Intrinsics.areEqual(contextType.name(), value)) {
                    return contextType;
                }
            }
            return ContextType.UNKNOWN;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    @NotNull
    public static final ContextType fromString(@NotNull String str) {
        return Companion.fromString(str);
    }
}
