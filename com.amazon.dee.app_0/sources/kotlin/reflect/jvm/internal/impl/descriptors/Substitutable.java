package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import org.jetbrains.annotations.NotNull;
/* compiled from: Substitutable.kt */
/* loaded from: classes2.dex */
public interface Substitutable<T extends DeclarationDescriptorNonRoot> {
    @NotNull
    /* renamed from: substitute */
    T mo12098substitute(@NotNull TypeSubstitutor typeSubstitutor);
}
