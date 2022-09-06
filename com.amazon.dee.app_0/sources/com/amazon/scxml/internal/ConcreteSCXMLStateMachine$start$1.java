package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* compiled from: ConcreteSCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "TUserContext", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class ConcreteSCXMLStateMachine$start$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ConcreteSCXMLStateMachine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcreteSCXMLStateMachine$start$1(ConcreteSCXMLStateMachine concreteSCXMLStateMachine) {
        super(0);
        this.this$0 = concreteSCXMLStateMachine;
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
        boolean z;
        String tag;
        String tag2;
        Set set;
        this.this$0._unsafeInitialize();
        z = this.this$0.isStarted;
        if (z) {
            return;
        }
        this.this$0.isStarted = true;
        Logger log = Loggable.Companion.getLog();
        tag = this.this$0.getTAG();
        log.i(tag, "SCXML:start");
        this.this$0.moveToFirstState();
        this.this$0.processMacrostep();
        Logger log2 = Loggable.Companion.getLog();
        tag2 = this.this$0.getTAG();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" after start: active states: [");
        set = this.this$0.activeStates;
        outline107.append(set);
        outline107.append(JsonReaderKt.END_LIST);
        log2.i(tag2, outline107.toString());
    }
}
