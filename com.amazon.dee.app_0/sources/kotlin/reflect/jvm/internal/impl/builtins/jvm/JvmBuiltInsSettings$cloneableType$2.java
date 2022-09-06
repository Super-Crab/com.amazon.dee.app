package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltInsSettings$cloneableType$2 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ StorageManager $storageManager;
    final /* synthetic */ JvmBuiltInsSettings this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInsSettings$cloneableType$2(JvmBuiltInsSettings jvmBuiltInsSettings, StorageManager storageManager) {
        super(0);
        this.this$0 = jvmBuiltInsSettings;
        this.$storageManager = storageManager;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final SimpleType mo12560invoke() {
        ModuleDescriptor ownerModuleDescriptor;
        ModuleDescriptor ownerModuleDescriptor2;
        ownerModuleDescriptor = this.this$0.getOwnerModuleDescriptor();
        ClassId cloneable_class_id = JvmBuiltInClassDescriptorFactory.Companion.getCLONEABLE_CLASS_ID();
        StorageManager storageManager = this.$storageManager;
        ownerModuleDescriptor2 = this.this$0.getOwnerModuleDescriptor();
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(ownerModuleDescriptor, cloneable_class_id, new NotFoundClasses(storageManager, ownerModuleDescriptor2)).getDefaultType();
    }
}
