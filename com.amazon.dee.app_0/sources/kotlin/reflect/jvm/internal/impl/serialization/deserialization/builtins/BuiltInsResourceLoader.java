package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BuiltInsResourceLoader.kt */
/* loaded from: classes4.dex */
public final class BuiltInsResourceLoader {
    @Nullable
    public final InputStream loadResource(@NotNull String path) {
        InputStream resourceAsStream;
        Intrinsics.checkParameterIsNotNull(path, "path");
        ClassLoader classLoader = BuiltInsResourceLoader.class.getClassLoader();
        return (classLoader == null || (resourceAsStream = classLoader.getResourceAsStream(path)) == null) ? ClassLoader.getSystemResourceAsStream(path) : resourceAsStream;
    }
}
