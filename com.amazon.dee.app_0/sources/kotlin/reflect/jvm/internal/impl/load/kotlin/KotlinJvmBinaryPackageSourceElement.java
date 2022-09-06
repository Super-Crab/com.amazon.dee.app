package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaPackageFragment;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinJvmBinaryPackageSourceElement.kt */
/* loaded from: classes3.dex */
public final class KotlinJvmBinaryPackageSourceElement implements SourceElement {
    private final LazyJavaPackageFragment packageFragment;

    public KotlinJvmBinaryPackageSourceElement(@NotNull LazyJavaPackageFragment packageFragment) {
        Intrinsics.checkParameterIsNotNull(packageFragment, "packageFragment");
        this.packageFragment = packageFragment;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
    @NotNull
    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
        return sourceFile;
    }

    @NotNull
    public String toString() {
        return this.packageFragment + RealTimeTextConstants.COLON_SPACE + this.packageFragment.getBinaryClasses$descriptors_jvm().keySet();
    }
}
