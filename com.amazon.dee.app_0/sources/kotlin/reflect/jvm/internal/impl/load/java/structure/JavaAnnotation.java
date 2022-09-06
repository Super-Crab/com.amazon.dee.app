package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: javaElements.kt */
/* loaded from: classes3.dex */
public interface JavaAnnotation extends JavaElement {

    /* compiled from: javaElements.kt */
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static boolean isIdeExternalAnnotation(JavaAnnotation javaAnnotation) {
            return false;
        }
    }

    @NotNull
    Collection<JavaAnnotationArgument> getArguments();

    @Nullable
    ClassId getClassId();

    boolean isIdeExternalAnnotation();

    @Nullable
    /* renamed from: resolve */
    JavaClass mo11615resolve();
}
