package kotlin.reflect.jvm.internal.impl.types.checker;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: IntersectionType.kt */
/* loaded from: classes4.dex */
final class TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1 extends Lambda implements Function0<String> {
    final /* synthetic */ Set $inputTypes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeIntersector$intersectTypesWithoutIntersectionType$errorMessage$1(Set set) {
        super(0);
        this.$inputTypes = set;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final String mo12560invoke() {
        String joinToString$default;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("This collections cannot be empty! input types: ");
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.$inputTypes, null, null, null, 0, null, null, 63, null);
        outline107.append(joinToString$default);
        return outline107.toString();
    }
}
