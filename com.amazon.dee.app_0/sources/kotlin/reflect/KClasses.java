package kotlin.reflect;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: KClasses.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0002\u0010\u0005\u001a-\u0010\u0006\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"cast", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlin/reflect/KClass;", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "safeCast", "kotlin-stdlib"}, k = 2, mv = {1, 1, 16})
@JvmName(name = "KClasses")
/* loaded from: classes2.dex */
public final class KClasses {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    @LowPriorityInOverloadResolution
    public static final <T> T cast(@NotNull KClass<T> cast, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(cast, "$this$cast");
        if (cast.isInstance(obj)) {
            if (obj == 0) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            return obj;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Value cannot be cast to ");
        outline107.append(cast.getQualifiedName());
        throw new ClassCastException(outline107.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    @LowPriorityInOverloadResolution
    public static final <T> T safeCast(@NotNull KClass<T> safeCast, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(safeCast, "$this$safeCast");
        if (safeCast.isInstance(obj)) {
            if (obj == 0) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            return obj;
        }
        return null;
    }
}
