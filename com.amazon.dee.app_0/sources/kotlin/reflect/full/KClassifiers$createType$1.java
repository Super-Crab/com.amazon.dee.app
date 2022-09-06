package kotlin.reflect.full;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClassifier;
import org.jetbrains.annotations.NotNull;
/* compiled from: KClassifiers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
final class KClassifiers$createType$1 extends Lambda implements Function0 {
    final /* synthetic */ KClassifier $this_createType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassifiers$createType$1(KClassifier kClassifier) {
        super(0);
        this.$this_createType = kClassifier;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Void mo12560invoke() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Java type is not yet supported for types created with createType (classifier = ");
        outline107.append(this.$this_createType);
        outline107.append(')');
        throw new NotImplementedError(GeneratedOutlineSupport1.outline72("An operation is not implemented: ", outline107.toString()));
    }
}
