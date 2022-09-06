package com.amazon.scxml.internal;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: ConcreteSCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "TUserContext", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class ConcreteSCXMLStateMachine$waitForEvent$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $eventName;
    final /* synthetic */ String $uuid;
    final /* synthetic */ ConcreteSCXMLStateMachine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcreteSCXMLStateMachine$waitForEvent$2(ConcreteSCXMLStateMachine concreteSCXMLStateMachine, String str, String str2) {
        super(0);
        this.this$0 = concreteSCXMLStateMachine;
        this.$eventName = str;
        this.$uuid = str2;
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
        Map map;
        map = this.this$0.eventWaitingMap;
        Map map2 = (Map) map.get(this.$eventName);
        if (map2 != null) {
            Function0 function0 = (Function0) map2.remove(this.$uuid);
        }
    }
}
