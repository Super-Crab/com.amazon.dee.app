package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: resolvers.kt */
/* loaded from: classes3.dex */
public final class LazyJavaTypeParameterResolver$resolve$1 extends Lambda implements Function1<JavaTypeParameter, LazyJavaTypeParameterDescriptor> {
    final /* synthetic */ LazyJavaTypeParameterResolver this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaTypeParameterResolver$resolve$1(LazyJavaTypeParameterResolver lazyJavaTypeParameterResolver) {
        super(1);
        this.this$0 = lazyJavaTypeParameterResolver;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final LazyJavaTypeParameterDescriptor mo12165invoke(@NotNull JavaTypeParameter typeParameter) {
        Map map;
        LazyJavaResolverContext lazyJavaResolverContext;
        int i;
        DeclarationDescriptor declarationDescriptor;
        Intrinsics.checkParameterIsNotNull(typeParameter, "typeParameter");
        map = this.this$0.typeParameters;
        Integer num = (Integer) map.get(typeParameter);
        if (num != null) {
            int intValue = num.intValue();
            lazyJavaResolverContext = this.this$0.c;
            LazyJavaResolverContext child = ContextKt.child(lazyJavaResolverContext, this.this$0);
            i = this.this$0.typeParametersIndexOffset;
            int i2 = i + intValue;
            declarationDescriptor = this.this$0.containingDeclaration;
            return new LazyJavaTypeParameterDescriptor(child, typeParameter, i2, declarationDescriptor);
        }
        return null;
    }
}
