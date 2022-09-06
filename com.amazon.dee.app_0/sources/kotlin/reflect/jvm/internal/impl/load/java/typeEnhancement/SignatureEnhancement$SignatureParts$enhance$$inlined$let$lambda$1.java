package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: signatureEnhancement.kt */
/* loaded from: classes3.dex */
final class SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1 extends Lambda implements Function1<Integer, JavaTypeQualifiers> {
    final /* synthetic */ TypeEnhancementInfo $predefined$inlined;
    final /* synthetic */ Function1 $qualifiers$inlined;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignatureEnhancement$SignatureParts$enhance$$inlined$let$lambda$1(TypeEnhancementInfo typeEnhancementInfo, Function1 function1) {
        super(1);
        this.$predefined$inlined = typeEnhancementInfo;
        this.$qualifiers$inlined = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ JavaTypeQualifiers mo12165invoke(Integer num) {
        return invoke(num.intValue());
    }

    @NotNull
    public final JavaTypeQualifiers invoke(int i) {
        JavaTypeQualifiers javaTypeQualifiers = this.$predefined$inlined.getMap().get(Integer.valueOf(i));
        return javaTypeQualifiers != null ? javaTypeQualifiers : (JavaTypeQualifiers) this.$qualifiers$inlined.mo12165invoke(Integer.valueOf(i));
    }
}
