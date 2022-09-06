package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaClassDescriptor.kt */
/* loaded from: classes3.dex */
public final class LazyJavaClassDescriptor$scopeHolder$1 extends Lambda implements Function1<KotlinTypeRefiner, LazyJavaClassMemberScope> {
    final /* synthetic */ LazyJavaClassDescriptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassDescriptor$scopeHolder$1(LazyJavaClassDescriptor lazyJavaClassDescriptor) {
        super(1);
        this.this$0 = lazyJavaClassDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final LazyJavaClassMemberScope mo12165invoke(@NotNull KotlinTypeRefiner kotlinTypeRefiner) {
        LazyJavaClassMemberScope lazyJavaClassMemberScope;
        Intrinsics.checkParameterIsNotNull(kotlinTypeRefiner, "kotlinTypeRefiner");
        LazyJavaResolverContext lazyJavaResolverContext = this.this$0.c;
        LazyJavaClassDescriptor lazyJavaClassDescriptor = this.this$0;
        JavaClass jClass = lazyJavaClassDescriptor.getJClass();
        boolean z = this.this$0.additionalSupertypeClassDescriptor != null;
        lazyJavaClassMemberScope = this.this$0.unsubstitutedMemberScope;
        return new LazyJavaClassMemberScope(lazyJavaResolverContext, lazyJavaClassDescriptor, jClass, z, lazyJavaClassMemberScope);
    }
}
