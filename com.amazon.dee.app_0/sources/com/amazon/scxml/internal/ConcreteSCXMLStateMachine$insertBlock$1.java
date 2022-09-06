package com.amazon.scxml.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* compiled from: ConcreteSCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "TUserContext", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class ConcreteSCXMLStateMachine$insertBlock$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function2 $completion;
    final /* synthetic */ ConcreteSCXMLStateMachine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcreteSCXMLStateMachine$insertBlock$1(ConcreteSCXMLStateMachine concreteSCXMLStateMachine, Function2 function2) {
        super(0);
        this.this$0 = concreteSCXMLStateMachine;
        this.$completion = function2;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        Set set;
        List mutableList;
        List<State> sorted;
        int collectionSizeOrDefault;
        boolean _unsafeValidateInternalStructure;
        set = this.this$0.activeStates;
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) set);
        sorted = CollectionsKt___CollectionsKt.sorted(mutableList);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(sorted, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (State state : sorted) {
            arrayList.add(state.getId());
        }
        _unsafeValidateInternalStructure = this.this$0._unsafeValidateInternalStructure();
        this.$completion.mo12248invoke(arrayList, Boolean.valueOf(_unsafeValidateInternalStructure));
    }
}
