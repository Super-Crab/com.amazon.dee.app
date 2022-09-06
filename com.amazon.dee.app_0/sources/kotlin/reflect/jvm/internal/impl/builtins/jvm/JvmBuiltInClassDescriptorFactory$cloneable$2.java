package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmBuiltInClassDescriptorFactory.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltInClassDescriptorFactory$cloneable$2 extends Lambda implements Function0<ClassDescriptorImpl> {
    final /* synthetic */ StorageManager $storageManager;
    final /* synthetic */ JvmBuiltInClassDescriptorFactory this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInClassDescriptorFactory$cloneable$2(JvmBuiltInClassDescriptorFactory jvmBuiltInClassDescriptorFactory, StorageManager storageManager) {
        super(0);
        this.this$0 = jvmBuiltInClassDescriptorFactory;
        this.$storageManager = storageManager;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final ClassDescriptorImpl mo12560invoke() {
        Function1 function1;
        ModuleDescriptor moduleDescriptor;
        Name name;
        ModuleDescriptor moduleDescriptor2;
        List listOf;
        Set<ClassConstructorDescriptor> emptySet;
        function1 = this.this$0.computeContainingDeclaration;
        moduleDescriptor = this.this$0.moduleDescriptor;
        DeclarationDescriptor declarationDescriptor = (DeclarationDescriptor) function1.mo12165invoke(moduleDescriptor);
        name = JvmBuiltInClassDescriptorFactory.CLONEABLE_NAME;
        Modality modality = Modality.ABSTRACT;
        ClassKind classKind = ClassKind.INTERFACE;
        moduleDescriptor2 = this.this$0.moduleDescriptor;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(moduleDescriptor2.getBuiltIns().getAnyType());
        ClassDescriptorImpl classDescriptorImpl = new ClassDescriptorImpl(declarationDescriptor, name, modality, classKind, listOf, SourceElement.NO_SOURCE, false, this.$storageManager);
        CloneableClassScope cloneableClassScope = new CloneableClassScope(this.$storageManager, classDescriptorImpl);
        emptySet = SetsKt__SetsKt.emptySet();
        classDescriptorImpl.initialize(cloneableClassScope, emptySet, null);
        return classDescriptorImpl;
    }
}
