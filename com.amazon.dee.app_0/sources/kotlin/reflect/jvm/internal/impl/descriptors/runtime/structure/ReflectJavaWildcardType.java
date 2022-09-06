package kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReflectJavaWildcardType.kt */
/* loaded from: classes2.dex */
public final class ReflectJavaWildcardType extends ReflectJavaType implements JavaWildcardType {
    @NotNull
    private final WildcardType reflectType;

    public ReflectJavaWildcardType(@NotNull WildcardType reflectType) {
        Intrinsics.checkParameterIsNotNull(reflectType, "reflectType");
        this.reflectType = reflectType;
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    public boolean isExtends() {
        Type[] upperBounds = mo11642getReflectType().getUpperBounds();
        Intrinsics.checkExpressionValueIsNotNull(upperBounds, "reflectType.upperBounds");
        return !Intrinsics.areEqual((Type) ArraysKt.firstOrNull(upperBounds), Object.class);
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType
    @Nullable
    /* renamed from: getBound */
    public ReflectJavaType mo11641getBound() {
        Type[] upperBounds = mo11642getReflectType().getUpperBounds();
        Type[] lowerBounds = mo11642getReflectType().getLowerBounds();
        if (upperBounds.length <= 1 && lowerBounds.length <= 1) {
            if (lowerBounds.length == 1) {
                ReflectJavaType.Factory factory = ReflectJavaType.Factory;
                Intrinsics.checkExpressionValueIsNotNull(lowerBounds, "lowerBounds");
                Object single = ArraysKt.single(lowerBounds);
                Intrinsics.checkExpressionValueIsNotNull(single, "lowerBounds.single()");
                return factory.create((Type) single);
            } else if (upperBounds.length != 1) {
                return null;
            } else {
                Intrinsics.checkExpressionValueIsNotNull(upperBounds, "upperBounds");
                Type ub = (Type) ArraysKt.single(upperBounds);
                if (!(!Intrinsics.areEqual(ub, Object.class))) {
                    return null;
                }
                ReflectJavaType.Factory factory2 = ReflectJavaType.Factory;
                Intrinsics.checkExpressionValueIsNotNull(ub, "ub");
                return factory2.create(ub);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wildcard types with many bounds are not yet supported: ");
        outline107.append(mo11642getReflectType());
        throw new UnsupportedOperationException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaType
    @NotNull
    /* renamed from: getReflectType  reason: collision with other method in class */
    public WildcardType mo11642getReflectType() {
        return this.reflectType;
    }
}
