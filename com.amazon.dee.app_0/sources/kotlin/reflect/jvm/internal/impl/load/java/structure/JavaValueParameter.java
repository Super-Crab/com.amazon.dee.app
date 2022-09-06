package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: javaElements.kt */
/* loaded from: classes3.dex */
public interface JavaValueParameter extends JavaAnnotationOwner {
    @Nullable
    Name getName();

    @NotNull
    /* renamed from: getType */
    JavaType mo11640getType();

    boolean isVararg();
}
