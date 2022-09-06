package com.amazon.tarazed.core.utility;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectionHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u0004\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\b\u001a\u00020\tJ$\u0010\n\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\u000b\u001a\u00020\t¨\u0006\f"}, d2 = {"Lcom/amazon/tarazed/core/utility/ReflectionHelper;", "", "()V", "getHiddenMethod", "Ljava/lang/reflect/Method;", ExifInterface.GPS_DIRECTION_TRUE, "classToFindFrom", "Ljava/lang/Class;", "hiddenMethodName", "", "getHiddenStaticFieldValue", "hiddenFieldName", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ReflectionHelper {
    public static final ReflectionHelper INSTANCE = new ReflectionHelper();

    private ReflectionHelper() {
    }

    @Nullable
    public final <T> Method getHiddenMethod(@NotNull Class<T> classToFindFrom, @NotNull String hiddenMethodName) {
        Method[] methods;
        Intrinsics.checkParameterIsNotNull(classToFindFrom, "classToFindFrom");
        Intrinsics.checkParameterIsNotNull(hiddenMethodName, "hiddenMethodName");
        for (Method method : classToFindFrom.getMethods()) {
            Intrinsics.checkExpressionValueIsNotNull(method, "method");
            if (Intrinsics.areEqual(method.getName(), hiddenMethodName)) {
                return method;
            }
        }
        return null;
    }

    @Nullable
    public final <T> Object getHiddenStaticFieldValue(@NotNull Class<T> classToFindFrom, @NotNull String hiddenFieldName) {
        Intrinsics.checkParameterIsNotNull(classToFindFrom, "classToFindFrom");
        Intrinsics.checkParameterIsNotNull(hiddenFieldName, "hiddenFieldName");
        Field hiddenField = classToFindFrom.getDeclaredField(hiddenFieldName);
        Intrinsics.checkExpressionValueIsNotNull(hiddenField, "hiddenField");
        hiddenField.setAccessible(true);
        return hiddenField.get(null);
    }
}
