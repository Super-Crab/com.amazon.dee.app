package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: javaElements.kt */
/* loaded from: classes3.dex */
public interface JavaMethod extends JavaMember, JavaTypeParameterListOwner {

    /* compiled from: javaElements.kt */
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static boolean getHasAnnotationParameterDefaultValue(JavaMethod javaMethod) {
            return javaMethod.getAnnotationParameterDefaultValue() != null;
        }
    }

    @Nullable
    JavaAnnotationArgument getAnnotationParameterDefaultValue();

    boolean getHasAnnotationParameterDefaultValue();

    @NotNull
    /* renamed from: getReturnType */
    JavaType mo11632getReturnType();

    @NotNull
    List<JavaValueParameter> getValueParameters();
}
