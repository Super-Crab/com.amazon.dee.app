package kotlinx.serialization;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialFormat.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u0002H\u0004H&¢\u0006\u0002\u0010\bJ)\u0010\t\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00040\u000b2\u0006\u0010\f\u001a\u00020\u0003H&¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/BinaryFormat;", "Lkotlinx/serialization/SerialFormat;", "dump", "", ExifInterface.GPS_DIRECTION_TRUE, "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)[B", "load", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "bytes", "(Lkotlinx/serialization/DeserializationStrategy;[B)Ljava/lang/Object;", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BinaryFormat extends SerialFormat {
    @NotNull
    <T> byte[] dump(@NotNull SerializationStrategy<? super T> serializationStrategy, T t);

    <T> T load(@NotNull DeserializationStrategy<T> deserializationStrategy, @NotNull byte[] bArr);
}
