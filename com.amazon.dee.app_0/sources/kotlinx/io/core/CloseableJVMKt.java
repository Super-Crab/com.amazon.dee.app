package kotlinx.io.core;

import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
/* compiled from: CloseableJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0001\"\u001d\u0010\u0000\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003*\n\u0010\n\"\u00020\u000b2\u00020\u000b¨\u0006\f"}, d2 = {"AddSuppressedMethod", "Ljava/lang/reflect/Method;", "getAddSuppressedMethod", "()Ljava/lang/reflect/Method;", "AddSuppressedMethod$delegate", "Lkotlin/Lazy;", "addSuppressedInternal", "", "", "other", "Closeable", "Ljava/io/Closeable;", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CloseableJVMKt {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Reflection.getOrCreateKotlinPackage(CloseableJVMKt.class, "kotlinx-io"), "AddSuppressedMethod", "getAddSuppressedMethod()Ljava/lang/reflect/Method;"))};
    private static final Lazy AddSuppressedMethod$delegate;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(CloseableJVMKt$AddSuppressedMethod$2.INSTANCE);
        AddSuppressedMethod$delegate = lazy;
    }

    @PublishedApi
    public static final void addSuppressedInternal(@NotNull Throwable receiver$0, @NotNull Throwable other) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Method addSuppressedMethod = getAddSuppressedMethod();
        if (addSuppressedMethod != null) {
            addSuppressedMethod.invoke(receiver$0, other);
        }
    }

    private static final Method getAddSuppressedMethod() {
        Lazy lazy = AddSuppressedMethod$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Method) lazy.getValue();
    }
}
