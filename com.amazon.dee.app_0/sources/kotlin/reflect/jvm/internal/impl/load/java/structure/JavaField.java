package kotlin.reflect.jvm.internal.impl.load.java.structure;

import org.jetbrains.annotations.NotNull;
/* compiled from: javaElements.kt */
/* loaded from: classes3.dex */
public interface JavaField extends JavaMember {
    boolean getHasConstantNotNullInitializer();

    @NotNull
    /* renamed from: getType */
    JavaType mo11627getType();

    boolean isEnumEntry();
}
