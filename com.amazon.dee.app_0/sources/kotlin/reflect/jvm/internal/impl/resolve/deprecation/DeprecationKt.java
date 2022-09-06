package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: deprecation.kt */
/* loaded from: classes4.dex */
public final class DeprecationKt {
    @NotNull
    private static final CallableDescriptor.UserDataKey<Object> DEPRECATED_FUNCTION_KEY = new CallableDescriptor.UserDataKey<Object>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt$DEPRECATED_FUNCTION_KEY$1
    };

    @NotNull
    public static final CallableDescriptor.UserDataKey<Object> getDEPRECATED_FUNCTION_KEY() {
        return DEPRECATED_FUNCTION_KEY;
    }
}
