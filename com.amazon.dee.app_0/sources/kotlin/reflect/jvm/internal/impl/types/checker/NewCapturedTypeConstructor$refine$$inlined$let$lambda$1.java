package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import org.jetbrains.annotations.NotNull;
/* compiled from: NewCapturedType.kt */
/* loaded from: classes4.dex */
final class NewCapturedTypeConstructor$refine$$inlined$let$lambda$1 extends Lambda implements Function0<List<? extends UnwrappedType>> {
    final /* synthetic */ KotlinTypeRefiner $kotlinTypeRefiner$inlined;
    final /* synthetic */ NewCapturedTypeConstructor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewCapturedTypeConstructor$refine$$inlined$let$lambda$1(NewCapturedTypeConstructor newCapturedTypeConstructor, KotlinTypeRefiner kotlinTypeRefiner) {
        super(0);
        this.this$0 = newCapturedTypeConstructor;
        this.$kotlinTypeRefiner$inlined = kotlinTypeRefiner;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends UnwrappedType> mo12560invoke() {
        int collectionSizeOrDefault;
        List<UnwrappedType> mo12135getSupertypes = this.this$0.mo12135getSupertypes();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(mo12135getSupertypes, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (UnwrappedType unwrappedType : mo12135getSupertypes) {
            arrayList.add(unwrappedType.mo12133refine(this.$kotlinTypeRefiner$inlined));
        }
        return arrayList;
    }
}
