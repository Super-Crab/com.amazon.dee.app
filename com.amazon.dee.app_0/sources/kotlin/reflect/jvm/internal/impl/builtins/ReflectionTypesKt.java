package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReflectionTypes.kt */
/* loaded from: classes2.dex */
public final class ReflectionTypesKt {
    @NotNull
    private static final List<String> PREFIXES;
    @NotNull
    private static final FqName KOTLIN_REFLECT_FQ_NAME = new FqName("kotlin.reflect");
    @NotNull
    private static final String K_PROPERTY_PREFIX = K_PROPERTY_PREFIX;
    @NotNull
    private static final String K_PROPERTY_PREFIX = K_PROPERTY_PREFIX;
    @NotNull
    private static final String K_MUTABLE_PROPERTY_PREFIX = K_MUTABLE_PROPERTY_PREFIX;
    @NotNull
    private static final String K_MUTABLE_PROPERTY_PREFIX = K_MUTABLE_PROPERTY_PREFIX;
    @NotNull
    private static final String K_FUNCTION_PREFIX = K_FUNCTION_PREFIX;
    @NotNull
    private static final String K_FUNCTION_PREFIX = K_FUNCTION_PREFIX;
    @NotNull
    private static final String K_SUSPEND_FUNCTION_PREFIX = K_SUSPEND_FUNCTION_PREFIX;
    @NotNull
    private static final String K_SUSPEND_FUNCTION_PREFIX = K_SUSPEND_FUNCTION_PREFIX;

    static {
        List<String> listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{K_PROPERTY_PREFIX, K_MUTABLE_PROPERTY_PREFIX, K_FUNCTION_PREFIX, K_SUSPEND_FUNCTION_PREFIX});
        PREFIXES = listOf;
    }

    @NotNull
    public static final FqName getKOTLIN_REFLECT_FQ_NAME() {
        return KOTLIN_REFLECT_FQ_NAME;
    }
}
