package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: JavaDescriptorUtil.kt */
/* loaded from: classes3.dex */
public final class JavaDescriptorUtilKt {
    public static final boolean isJavaField(@NotNull PropertyDescriptor isJavaField) {
        Intrinsics.checkParameterIsNotNull(isJavaField, "$this$isJavaField");
        return isJavaField.mo11581getGetter() == null;
    }
}
