package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: javaElements.kt */
/* loaded from: classes3.dex */
public interface JavaClass extends JavaClassifier, JavaModifierListOwner, JavaTypeParameterListOwner {
    @NotNull
    /* renamed from: getConstructors */
    Collection<JavaConstructor> mo11619getConstructors();

    @NotNull
    /* renamed from: getFields */
    Collection<JavaField> mo11621getFields();

    @Nullable
    FqName getFqName();

    @NotNull
    /* renamed from: getInnerClassNames */
    Collection<Name> mo11622getInnerClassNames();

    @Nullable
    LightClassOriginKind getLightClassOriginKind();

    @NotNull
    /* renamed from: getMethods */
    Collection<JavaMethod> mo11623getMethods();

    @Nullable
    /* renamed from: getOuterClass */
    JavaClass mo11624getOuterClass();

    @NotNull
    Collection<JavaClassifierType> getSupertypes();

    boolean hasDefaultConstructor();

    boolean isAnnotationType();

    boolean isEnum();

    boolean isInterface();
}
