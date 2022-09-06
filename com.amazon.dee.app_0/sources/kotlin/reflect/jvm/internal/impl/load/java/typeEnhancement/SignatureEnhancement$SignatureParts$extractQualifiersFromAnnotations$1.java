package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
final class SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1 extends Lambda implements Function2<List<? extends FqName>, T, T> {
    final /* synthetic */ Annotations $composedAnnotation;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$1(Annotations annotations) {
        super(2);
        this.$composedAnnotation = annotations;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Object mo12248invoke(List<? extends FqName> list, Object obj) {
        return invoke2((List<FqName>) list, (List<? extends FqName>) obj);
    }

    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final <T> T invoke2(@NotNull List<FqName> ifPresent, @NotNull T qualifier) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(ifPresent, "$this$ifPresent");
        Intrinsics.checkParameterIsNotNull(qualifier, "qualifier");
        boolean z2 = false;
        if (!(ifPresent instanceof Collection) || !ifPresent.isEmpty()) {
            Iterator<T> it2 = ifPresent.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (this.$composedAnnotation.mo11701findAnnotation((FqName) it2.next()) != null) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    z2 = true;
                    break;
                }
            }
        }
        if (z2) {
            return qualifier;
        }
        return null;
    }
}
