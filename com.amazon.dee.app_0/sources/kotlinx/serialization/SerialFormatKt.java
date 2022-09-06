package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.internal.InternalHexConverter;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialFormat.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0007\u001a+\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"dumps", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/serialization/BinaryFormat;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/BinaryFormat;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Ljava/lang/String;", "loads", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "hex", "(Lkotlinx/serialization/BinaryFormat;Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/String;)Ljava/lang/Object;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SerialFormatKt {
    @NotNull
    public static final <T> String dumps(@NotNull BinaryFormat dumps, @NotNull SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkParameterIsNotNull(dumps, "$this$dumps");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
        return InternalHexConverter.INSTANCE.printHexBinary(dumps.dump(serializer, t), true);
    }

    public static final <T> T loads(@NotNull BinaryFormat loads, @NotNull DeserializationStrategy<T> deserializer, @NotNull String hex) {
        Intrinsics.checkParameterIsNotNull(loads, "$this$loads");
        Intrinsics.checkParameterIsNotNull(deserializer, "deserializer");
        Intrinsics.checkParameterIsNotNull(hex, "hex");
        return (T) loads.load(deserializer, InternalHexConverter.INSTANCE.parseHexBinary(hex));
    }
}
