package io.ktor.client.call;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* compiled from: TypeInfoJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\u0086\b*\n\u0010\u0003\"\u00020\u00042\u00020\u0004¨\u0006\u0005"}, d2 = {"typeInfo", "Lio/ktor/client/call/TypeInfo;", ExifInterface.GPS_DIRECTION_TRUE, "Type", "Ljava/lang/reflect/Type;", "ktor-client-core"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class TypeInfoJvmKt {
    private static final <T> TypeInfo typeInfo() {
        Intrinsics.needClassReification();
        new TypeBase<T>() { // from class: io.ktor.client.call.TypeInfoJvmKt$typeInfo$base$1
        };
        Type genericSuperclass = TypeInfoJvmKt$typeInfo$base$1.class.getGenericSuperclass();
        if (genericSuperclass == null) {
            Intrinsics.throwNpe();
        }
        if (genericSuperclass != null) {
            Object outline23 = GeneratedOutlineSupport1.outline23((ParameterizedType) genericSuperclass, "(superType as Parameteri…Type).actualTypeArguments");
            if (outline23 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            return new TypeInfo(Reflection.getOrCreateKotlinClass(Object.class), (Type) outline23);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.reflect.ParameterizedType");
    }
}
