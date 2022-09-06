package kotlinx.serialization.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: NullableSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007¨\u0006\u0005"}, d2 = {"makeNullable", "Lkotlinx/serialization/KSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "actualSerializer", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NullableSerializerKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favor of extension", replaceWith = @ReplaceWith(expression = "actualSerializer.nullable)", imports = {}))
    @InternalSerializationApi
    @NotNull
    public static final <T> KSerializer<T> makeNullable(@NotNull KSerializer<T> actualSerializer) {
        Intrinsics.checkParameterIsNotNull(actualSerializer, "actualSerializer");
        return new NullableSerializer(actualSerializer);
    }
}
