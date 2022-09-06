package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SerializationUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0005*\u0002H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"deserialize", ExifInterface.GPS_DIRECTION_TRUE, "", "([B)Ljava/lang/Object;", "serialize", "Ljava/io/Serializable;", "(Ljava/io/Serializable;)[B", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SerializationUtilsKt {
    @Nullable
    public static final /* synthetic */ <T> T deserialize(@NotNull byte[] deserialize) {
        Intrinsics.checkParameterIsNotNull(deserialize, "$this$deserialize");
        try {
            T t = (T) SerializationUtils.deserialize(deserialize);
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return t;
        } catch (Exception unused) {
            return null;
        }
    }

    @NotNull
    public static final <T extends Serializable> byte[] serialize(@NotNull T serialize) {
        Intrinsics.checkParameterIsNotNull(serialize, "$this$serialize");
        byte[] serialize2 = SerializationUtils.serialize(serialize);
        Intrinsics.checkExpressionValueIsNotNull(serialize2, "SerializationUtils.serialize(this)");
        return serialize2;
    }
}
