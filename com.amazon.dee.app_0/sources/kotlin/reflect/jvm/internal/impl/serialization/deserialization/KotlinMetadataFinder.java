package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.io.InputStream;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KotlinMetadataFinder.kt */
/* loaded from: classes4.dex */
public interface KotlinMetadataFinder {
    @Nullable
    InputStream findBuiltInsData(@NotNull FqName fqName);
}
