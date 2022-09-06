package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
/* compiled from: JvmBuiltIns.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltIns extends KotlinBuiltIns {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltIns.class), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsSettings;"))};
    private boolean isAdditionalBuiltInsFeatureSupported;
    private ModuleDescriptor ownerModuleDescriptor;
    @NotNull
    private final NotNullLazyValue settings$delegate;

    /* compiled from: JvmBuiltIns.kt */
    /* loaded from: classes2.dex */
    public enum Kind {
        FROM_DEPENDENCIES,
        FROM_CLASS_LOADER,
        FALLBACK
    }

    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];

        static {
            $EnumSwitchMapping$0[Kind.FROM_DEPENDENCIES.ordinal()] = 1;
            $EnumSwitchMapping$0[Kind.FROM_CLASS_LOADER.ordinal()] = 2;
            $EnumSwitchMapping$0[Kind.FALLBACK.ordinal()] = 3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltIns(@NotNull StorageManager storageManager, @NotNull Kind kind) {
        super(storageManager);
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        this.isAdditionalBuiltInsFeatureSupported = true;
        this.settings$delegate = storageManager.createLazyValue(new JvmBuiltIns$settings$2(this, storageManager));
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i != 1) {
            if (i == 2) {
                createBuiltInsModule(false);
            } else if (i != 3) {
            } else {
                createBuiltInsModule(true);
            }
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    @NotNull
    protected AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return getSettings();
    }

    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    @NotNull
    /* renamed from: getPlatformDependentDeclarationFilter */
    protected PlatformDependentDeclarationFilter mo11513getPlatformDependentDeclarationFilter() {
        return getSettings();
    }

    @NotNull
    public final JvmBuiltInsSettings getSettings() {
        return (JvmBuiltInsSettings) StorageKt.getValue(this.settings$delegate, this, $$delegatedProperties[0]);
    }

    public final void initialize(@NotNull ModuleDescriptor moduleDescriptor, boolean z) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "moduleDescriptor");
        boolean z2 = this.ownerModuleDescriptor == null;
        if (!_Assertions.ENABLED || z2) {
            this.ownerModuleDescriptor = moduleDescriptor;
            this.isAdditionalBuiltInsFeatureSupported = z;
            return;
        }
        throw new AssertionError("JvmBuiltins repeated initialization");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    @NotNull
    /* renamed from: getClassDescriptorFactories  reason: collision with other method in class */
    public List<ClassDescriptorFactory> mo11515getClassDescriptorFactories() {
        List<ClassDescriptorFactory> plus;
        Iterable<ClassDescriptorFactory> mo11515getClassDescriptorFactories = super.mo11515getClassDescriptorFactories();
        Intrinsics.checkExpressionValueIsNotNull(mo11515getClassDescriptorFactories, "super.getClassDescriptorFactories()");
        StorageManager storageManager = getStorageManager();
        Intrinsics.checkExpressionValueIsNotNull(storageManager, "storageManager");
        ModuleDescriptorImpl builtInsModule = getBuiltInsModule();
        Intrinsics.checkExpressionValueIsNotNull(builtInsModule, "builtInsModule");
        plus = CollectionsKt___CollectionsKt.plus(mo11515getClassDescriptorFactories, new JvmBuiltInClassDescriptorFactory(storageManager, builtInsModule, null, 4, null));
        return plus;
    }
}
