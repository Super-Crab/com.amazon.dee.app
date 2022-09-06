package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: ReflectKotlinClassFinder.kt */
/* loaded from: classes2.dex */
public final class ReflectKotlinClassFinderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String toRuntimeFqName(@NotNull ClassId classId) {
        String replace$default;
        String asString = classId.getRelativeClassName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "relativeClassName.asString()");
        replace$default = StringsKt__StringsJVMKt.replace$default(asString, '.', '$', false, 4, (Object) null);
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "packageFqName");
        if (packageFqName.isRoot()) {
            return replace$default;
        }
        return classId.getPackageFqName() + '.' + replace$default;
    }
}
