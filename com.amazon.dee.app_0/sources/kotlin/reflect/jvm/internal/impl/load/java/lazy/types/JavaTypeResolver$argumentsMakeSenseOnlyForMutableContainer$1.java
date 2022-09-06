package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaWildcardType;
import org.jetbrains.annotations.Nullable;
/* compiled from: JavaTypeResolver.kt */
/* loaded from: classes3.dex */
final class JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1 extends Lambda implements Function1<JavaType, Boolean> {
    public static final JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1 INSTANCE = new JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1();

    JavaTypeResolver$argumentsMakeSenseOnlyForMutableContainer$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(JavaType javaType) {
        return Boolean.valueOf(invoke2(javaType));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@Nullable JavaType javaType) {
        if (!(javaType instanceof JavaWildcardType)) {
            javaType = null;
        }
        JavaWildcardType javaWildcardType = (JavaWildcardType) javaType;
        return (javaWildcardType == null || javaWildcardType.mo11641getBound() == null || javaWildcardType.isExtends()) ? false : true;
    }
}
