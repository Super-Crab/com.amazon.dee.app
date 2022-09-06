package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;
/* compiled from: NewCapturedType.kt */
/* loaded from: classes4.dex */
final class NewCapturedTypeConstructor$initializeSupertypes$2 extends Lambda implements Function0<List<? extends UnwrappedType>> {
    final /* synthetic */ List $supertypes;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewCapturedTypeConstructor$initializeSupertypes$2(List list) {
        super(0);
        this.$supertypes = list;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends UnwrappedType> mo12560invoke() {
        return this.$supertypes;
    }
}
