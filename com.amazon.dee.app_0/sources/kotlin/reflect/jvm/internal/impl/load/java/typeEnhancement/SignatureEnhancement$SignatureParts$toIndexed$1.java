package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaTypeQualifiersByElementType;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
public final class SignatureEnhancement$SignatureParts$toIndexed$1 extends Lambda implements Function2<KotlinType, LazyJavaResolverContext, Unit> {
    final /* synthetic */ ArrayList $list;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignatureEnhancement$SignatureParts$toIndexed$1(ArrayList arrayList) {
        super(2);
        this.$list = arrayList;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(KotlinType kotlinType, LazyJavaResolverContext lazyJavaResolverContext) {
        invoke2(kotlinType, lazyJavaResolverContext);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull KotlinType type, @NotNull LazyJavaResolverContext ownerContext) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(ownerContext, "ownerContext");
        LazyJavaResolverContext copyWithNewDefaultTypeQualifiers = ContextKt.copyWithNewDefaultTypeQualifiers(ownerContext, type.mo12070getAnnotations());
        ArrayList arrayList = this.$list;
        JavaTypeQualifiersByElementType defaultTypeQualifiers = copyWithNewDefaultTypeQualifiers.getDefaultTypeQualifiers();
        arrayList.add(new TypeAndDefaultQualifiers(type, defaultTypeQualifiers != null ? defaultTypeQualifiers.get(AnnotationTypeQualifierResolver.QualifierApplicabilityType.TYPE_USE) : null));
        for (TypeProjection typeProjection : type.getArguments()) {
            if (typeProjection.isStarProjection()) {
                ArrayList arrayList2 = this.$list;
                KotlinType type2 = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type2, "arg.type");
                arrayList2.add(new TypeAndDefaultQualifiers(type2, null));
            } else {
                KotlinType type3 = typeProjection.getType();
                Intrinsics.checkExpressionValueIsNotNull(type3, "arg.type");
                invoke2(type3, copyWithNewDefaultTypeQualifiers);
            }
        }
    }
}
