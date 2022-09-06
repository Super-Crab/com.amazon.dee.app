package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaPackageFragment.kt */
/* loaded from: classes3.dex */
public final class LazyJavaPackageFragment$partToFacade$2 extends Lambda implements Function0<HashMap<JvmClassName, JvmClassName>> {
    final /* synthetic */ LazyJavaPackageFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaPackageFragment$partToFacade$2(LazyJavaPackageFragment lazyJavaPackageFragment) {
        super(0);
        this.this$0 = lazyJavaPackageFragment;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final HashMap<JvmClassName, JvmClassName> mo12560invoke() {
        HashMap<JvmClassName, JvmClassName> hashMap = new HashMap<>();
        for (Map.Entry<String, KotlinJvmBinaryClass> entry : this.this$0.getBinaryClasses$descriptors_jvm().entrySet()) {
            JvmClassName byInternalName = JvmClassName.byInternalName(entry.getKey());
            Intrinsics.checkExpressionValueIsNotNull(byInternalName, "JvmClassName.byInternalName(partInternalName)");
            KotlinClassHeader classHeader = entry.getValue().getClassHeader();
            int i = LazyJavaPackageFragment.WhenMappings.$EnumSwitchMapping$0[classHeader.getKind().ordinal()];
            if (i == 1) {
                String multifileClassName = classHeader.getMultifileClassName();
                if (multifileClassName != null) {
                    JvmClassName byInternalName2 = JvmClassName.byInternalName(multifileClassName);
                    Intrinsics.checkExpressionValueIsNotNull(byInternalName2, "JvmClassName.byInternalN…: continue@kotlinClasses)");
                    hashMap.put(byInternalName, byInternalName2);
                }
            } else if (i == 2) {
                hashMap.put(byInternalName, byInternalName);
            }
        }
        return hashMap;
    }
}
