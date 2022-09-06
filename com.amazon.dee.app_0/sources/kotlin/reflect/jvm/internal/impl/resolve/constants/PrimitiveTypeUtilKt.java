package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* compiled from: PrimitiveTypeUtil.kt */
/* loaded from: classes4.dex */
public final class PrimitiveTypeUtilKt {
    @NotNull
    public static final Collection<KotlinType> getAllSignedLiteralTypes(@NotNull ModuleDescriptor allSignedLiteralTypes) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(allSignedLiteralTypes, "$this$allSignedLiteralTypes");
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new SimpleType[]{allSignedLiteralTypes.getBuiltIns().getIntType(), allSignedLiteralTypes.getBuiltIns().getLongType(), allSignedLiteralTypes.getBuiltIns().getByteType(), allSignedLiteralTypes.getBuiltIns().getShortType()});
        return listOf;
    }
}
