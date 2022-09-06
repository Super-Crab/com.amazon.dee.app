package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaElement;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import org.jetbrains.annotations.NotNull;
/* compiled from: RuntimeSourceElementFactory.kt */
/* loaded from: classes2.dex */
public final class RuntimeSourceElementFactory implements JavaSourceElementFactory {
    public static final RuntimeSourceElementFactory INSTANCE = new RuntimeSourceElementFactory();

    /* compiled from: RuntimeSourceElementFactory.kt */
    /* loaded from: classes2.dex */
    public static final class RuntimeSourceElement implements JavaSourceElement {
        @NotNull
        private final ReflectJavaElement javaElement;

        public RuntimeSourceElement(@NotNull ReflectJavaElement javaElement) {
            Intrinsics.checkParameterIsNotNull(javaElement, "javaElement");
            this.javaElement = javaElement;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.SourceElement
        @NotNull
        public SourceFile getContainingFile() {
            SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
            Intrinsics.checkExpressionValueIsNotNull(sourceFile, "SourceFile.NO_SOURCE_FILE");
            return sourceFile;
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement
        @NotNull
        /* renamed from: getJavaElement */
        public ReflectJavaElement mo11614getJavaElement() {
            return this.javaElement;
        }

        @NotNull
        public String toString() {
            return RuntimeSourceElement.class.getName() + RealTimeTextConstants.COLON_SPACE + mo11614getJavaElement().toString();
        }
    }

    private RuntimeSourceElementFactory() {
    }

    @Override // kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory
    @NotNull
    public JavaSourceElement source(@NotNull JavaElement javaElement) {
        Intrinsics.checkParameterIsNotNull(javaElement, "javaElement");
        return new RuntimeSourceElement((ReflectJavaElement) javaElement);
    }
}
