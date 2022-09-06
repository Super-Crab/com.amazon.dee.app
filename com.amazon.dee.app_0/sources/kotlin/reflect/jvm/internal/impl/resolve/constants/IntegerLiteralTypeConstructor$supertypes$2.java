package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutionKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IntegerLiteralTypeConstructor.kt */
/* loaded from: classes4.dex */
public final class IntegerLiteralTypeConstructor$supertypes$2 extends Lambda implements Function0<List<SimpleType>> {
    final /* synthetic */ IntegerLiteralTypeConstructor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntegerLiteralTypeConstructor$supertypes$2(IntegerLiteralTypeConstructor integerLiteralTypeConstructor) {
        super(0);
        this.this$0 = integerLiteralTypeConstructor;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<SimpleType> mo12560invoke() {
        SimpleType simpleType;
        List listOf;
        List<SimpleType> mutableListOf;
        boolean isContainsOnlyUnsignedTypes;
        ClassDescriptor comparable = this.this$0.getBuiltIns().getComparable();
        Intrinsics.checkExpressionValueIsNotNull(comparable, "builtIns.comparable");
        SimpleType defaultType = comparable.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "builtIns.comparable.defaultType");
        Variance variance = Variance.IN_VARIANCE;
        simpleType = this.this$0.type;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(new TypeProjectionImpl(variance, simpleType));
        mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(TypeSubstitutionKt.replace$default(defaultType, listOf, (Annotations) null, 2, (Object) null));
        isContainsOnlyUnsignedTypes = this.this$0.isContainsOnlyUnsignedTypes();
        if (!isContainsOnlyUnsignedTypes) {
            mutableListOf.add(this.this$0.getBuiltIns().getNumberType());
        }
        return mutableListOf;
    }
}
