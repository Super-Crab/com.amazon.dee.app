package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmBuiltIns.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltIns$settings$2 extends Lambda implements Function0<JvmBuiltInsSettings> {
    final /* synthetic */ StorageManager $storageManager;
    final /* synthetic */ JvmBuiltIns this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JvmBuiltIns.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns$settings$2$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<ModuleDescriptor> {
        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final ModuleDescriptor mo12560invoke() {
            ModuleDescriptor moduleDescriptor;
            moduleDescriptor = JvmBuiltIns$settings$2.this.this$0.ownerModuleDescriptor;
            if (moduleDescriptor != null) {
                return moduleDescriptor;
            }
            throw new AssertionError("JvmBuiltins has not been initialized properly");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JvmBuiltIns.kt */
    /* renamed from: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns$settings$2$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends Lambda implements Function0<Boolean> {
        AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke */
        public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
            return Boolean.valueOf(mo12560invoke());
        }

        /* JADX WARN: Type inference failed for: r0v6, types: [boolean, java.lang.Boolean] */
        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: collision with other method in class */
        public final Boolean mo12560invoke() {
            ModuleDescriptor moduleDescriptor;
            ?? r0;
            moduleDescriptor = JvmBuiltIns$settings$2.this.this$0.ownerModuleDescriptor;
            if (moduleDescriptor != null) {
                r0 = JvmBuiltIns$settings$2.this.this$0.isAdditionalBuiltInsFeatureSupported;
                return r0;
            }
            throw new AssertionError("JvmBuiltins has not been initialized properly");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltIns$settings$2(JvmBuiltIns jvmBuiltIns, StorageManager storageManager) {
        super(0);
        this.this$0 = jvmBuiltIns;
        this.$storageManager = storageManager;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final JvmBuiltInsSettings mo12560invoke() {
        ModuleDescriptorImpl builtInsModule = this.this$0.getBuiltInsModule();
        Intrinsics.checkExpressionValueIsNotNull(builtInsModule, "builtInsModule");
        return new JvmBuiltInsSettings(builtInsModule, this.$storageManager, new AnonymousClass1(), new AnonymousClass2());
    }
}
