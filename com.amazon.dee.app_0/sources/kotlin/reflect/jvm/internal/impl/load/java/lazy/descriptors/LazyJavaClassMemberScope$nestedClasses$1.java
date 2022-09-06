package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaClassMemberScope.kt */
/* loaded from: classes3.dex */
public final class LazyJavaClassMemberScope$nestedClasses$1 extends Lambda implements Function1<Name, ClassDescriptorBase> {
    final /* synthetic */ LazyJavaResolverContext $c;
    final /* synthetic */ LazyJavaClassMemberScope this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaClassMemberScope$nestedClasses$1(LazyJavaClassMemberScope lazyJavaClassMemberScope, LazyJavaResolverContext lazyJavaResolverContext) {
        super(1);
        this.this$0 = lazyJavaClassMemberScope;
        this.$c = lazyJavaResolverContext;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final ClassDescriptorBase mo12165invoke(@NotNull Name name) {
        NotNullLazyValue notNullLazyValue;
        JavaClass javaClass;
        NotNullLazyValue notNullLazyValue2;
        Intrinsics.checkParameterIsNotNull(name, "name");
        notNullLazyValue = this.this$0.nestedClassIndex;
        if (!((Set) notNullLazyValue.mo12560invoke()).contains(name)) {
            notNullLazyValue2 = this.this$0.enumEntryIndex;
            JavaField javaField = (JavaField) ((Map) notNullLazyValue2.mo12560invoke()).get(name);
            if (javaField == null) {
                return null;
            }
            return EnumEntrySyntheticClassDescriptor.create(this.$c.getStorageManager(), this.this$0.mo11690getOwnerDescriptor(), name, this.$c.getStorageManager().createLazyValue(new LazyJavaClassMemberScope$nestedClasses$1$enumMemberNames$1(this)), LazyJavaAnnotationsKt.resolveAnnotations(this.$c, javaField), this.$c.getComponents().getSourceElementFactory().source(javaField));
        }
        JavaClassFinder finder = this.$c.getComponents().getFinder();
        ClassId classId = DescriptorUtilsKt.getClassId(this.this$0.mo11690getOwnerDescriptor());
        if (classId == null) {
            Intrinsics.throwNpe();
        }
        ClassId createNestedClassId = classId.createNestedClassId(name);
        Intrinsics.checkExpressionValueIsNotNull(createNestedClassId, "ownerDescriptor.classId!…createNestedClassId(name)");
        javaClass = this.this$0.jClass;
        JavaClass findClass = finder.findClass(new JavaClassFinder.Request(createNestedClassId, null, javaClass, 2, null));
        if (findClass == null) {
            return null;
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor = new LazyJavaClassDescriptor(this.$c, this.this$0.mo11690getOwnerDescriptor(), findClass, null, 8, null);
        this.$c.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor);
        return lazyJavaClassDescriptor;
    }
}
