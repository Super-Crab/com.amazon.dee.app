package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: javaElements.kt */
/* loaded from: classes3.dex */
public interface JavaAnnotationOwner extends JavaElement {
    @Nullable
    /* renamed from: findAnnotation */
    JavaAnnotation mo11638findAnnotation(@NotNull FqName fqName);

    @NotNull
    /* renamed from: getAnnotations */
    Collection<JavaAnnotation> mo11639getAnnotations();

    boolean isDeprecatedInJavaDoc();
}
