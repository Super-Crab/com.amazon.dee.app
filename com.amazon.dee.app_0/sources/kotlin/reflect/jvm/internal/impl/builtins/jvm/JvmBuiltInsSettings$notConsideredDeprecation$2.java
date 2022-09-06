package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: JvmBuiltInsSettings.kt */
/* loaded from: classes2.dex */
public final class JvmBuiltInsSettings$notConsideredDeprecation$2 extends Lambda implements Function0<Annotations> {
    final /* synthetic */ JvmBuiltInsSettings this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmBuiltInsSettings$notConsideredDeprecation$2(JvmBuiltInsSettings jvmBuiltInsSettings) {
        super(0);
        this.this$0 = jvmBuiltInsSettings;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Annotations mo12560invoke() {
        ModuleDescriptor moduleDescriptor;
        List<? extends AnnotationDescriptor> listOf;
        moduleDescriptor = this.this$0.moduleDescriptor;
        AnnotationDescriptor createDeprecatedAnnotation$default = AnnotationUtilKt.createDeprecatedAnnotation$default(moduleDescriptor.getBuiltIns(), "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", null, null, 6, null);
        Annotations.Companion companion = Annotations.Companion;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(createDeprecatedAnnotation$default);
        return companion.create(listOf);
    }
}
