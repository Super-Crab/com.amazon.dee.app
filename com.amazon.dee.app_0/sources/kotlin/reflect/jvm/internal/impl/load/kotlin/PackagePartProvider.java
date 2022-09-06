package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PackagePartProvider.kt */
/* loaded from: classes3.dex */
public interface PackagePartProvider {

    /* compiled from: PackagePartProvider.kt */
    /* loaded from: classes3.dex */
    public static final class Empty implements PackagePartProvider {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider
        @NotNull
        public List<String> findPackageParts(@NotNull String packageFqName) {
            List<String> emptyList;
            Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return emptyList;
        }
    }

    @NotNull
    List<String> findPackageParts(@NotNull String str);
}
