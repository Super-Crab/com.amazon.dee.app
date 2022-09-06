package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
final class SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1 extends Lambda implements Function1<Integer, JavaTypeQualifiers> {
    final /* synthetic */ JavaTypeQualifiers[] $computedResult;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignatureEnhancement$SignatureParts$computeIndexedQualifiersForOverride$1(JavaTypeQualifiers[] javaTypeQualifiersArr) {
        super(1);
        this.$computedResult = javaTypeQualifiersArr;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ JavaTypeQualifiers mo12165invoke(Integer num) {
        return invoke(num.intValue());
    }

    @NotNull
    public final JavaTypeQualifiers invoke(int i) {
        int lastIndex;
        JavaTypeQualifiers[] javaTypeQualifiersArr = this.$computedResult;
        if (i >= 0) {
            lastIndex = ArraysKt___ArraysKt.getLastIndex(javaTypeQualifiersArr);
            if (i <= lastIndex) {
                return javaTypeQualifiersArr[i];
            }
        }
        return JavaTypeQualifiers.Companion.getNONE();
    }
}
