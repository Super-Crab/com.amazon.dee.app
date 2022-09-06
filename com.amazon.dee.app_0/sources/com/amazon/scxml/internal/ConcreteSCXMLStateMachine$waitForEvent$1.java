package com.amazon.scxml.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: ConcreteSCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "TUserContext", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class ConcreteSCXMLStateMachine$waitForEvent$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $eventName;
    final /* synthetic */ Function0 $func;
    final /* synthetic */ String $uuid;
    final /* synthetic */ ConcreteSCXMLStateMachine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcreteSCXMLStateMachine$waitForEvent$1(ConcreteSCXMLStateMachine concreteSCXMLStateMachine, String str, Function0 function0, String str2) {
        super(0);
        this.this$0 = concreteSCXMLStateMachine;
        this.$eventName = str;
        this.$func = function0;
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
        Map map2;
        map = this.this$0.eventWaitingMap;
        Map map3 = (Map) map.get(this.$eventName);
        if (map3 == null) {
            map3 = new LinkedHashMap();
        }
        map3.put(this.$uuid, this.$func);
        map2 = this.this$0.eventWaitingMap;
        map2.put(this.$eventName, map3);
    }
}
