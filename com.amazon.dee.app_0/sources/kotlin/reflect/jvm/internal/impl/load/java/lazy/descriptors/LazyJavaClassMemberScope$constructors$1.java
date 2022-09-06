package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaClassMemberScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaClassMemberScope$constructors$1 extends Lambda implements Function0<List<? extends ClassConstructorDescriptor>> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassMemberScope$constructors$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, LazyJavaResolverContext lazyJavaResolverContext) {
        super(0);
        this.this$0 = lazyJavaClassMemberScope;
        this.$c = lazyJavaResolverContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List] */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends ClassConstructorDescriptor> mo12560invoke() {
        JavaClass javaClass;
        List<? extends ClassConstructorDescriptor> list;
        ClassConstructorDescriptor createDefaultConstructor;
        ?? listOfNotNull;
        JavaClassConstructorDescriptor resolveConstructor;
        javaClass = this.this$0.jClass;
        Collection<JavaConstructor> mo11619getConstructors = javaClass.mo11619getConstructors();
        ArrayList arrayList = new ArrayList(mo11619getConstructors.size());
        for (JavaConstructor javaConstructor : mo11619getConstructors) {
            resolveConstructor = this.this$0.resolveConstructor(javaConstructor);
            arrayList.add(resolveConstructor);
        }
        SignatureEnhancement signatureEnhancement = this.$c.getComponents().getSignatureEnhancement();
        LazyJavaResolverContext lazyJavaResolverContext = this.$c;
        boolean isEmpty = arrayList.isEmpty();
        ArrayList arrayList2 = arrayList;
        if (isEmpty) {
            createDefaultConstructor = this.this$0.createDefaultConstructor();
            listOfNotNull = CollectionsKt__CollectionsKt.listOfNotNull(createDefaultConstructor);
            arrayList2 = listOfNotNull;
        }
        list = CollectionsKt___CollectionsKt.toList(signatureEnhancement.enhanceSignatures(lazyJavaResolverContext, arrayList2));
        return list;
    }
}
