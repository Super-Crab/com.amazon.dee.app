package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConcreteSCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "TUserContext", "wasAdded", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ConcreteSCXMLStateMachine$sendEventWithDelay$1 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ String $eventName;
    final /* synthetic */ ConcreteSCXMLStateMachine this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ConcreteSCXMLStateMachine.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "TUserContext", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.scxml.internal.ConcreteSCXMLStateMachine$sendEventWithDelay$1$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        AnonymousClass1() {
            super(0);
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
            String tag;
            Logger log = Loggable.Companion.getLog();
            tag = ConcreteSCXMLStateMachine$sendEventWithDelay$1.this.this$0.getTAG();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXML:triggering processMacrostep() for delated event [");
            outline107.append(ConcreteSCXMLStateMachine$sendEventWithDelay$1.this.$eventName);
            outline107.append(JsonReaderKt.END_LIST);
            log.i(tag, outline107.toString());
            ConcreteSCXMLStateMachine$sendEventWithDelay$1.this.this$0.processMacrostep();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConcreteSCXMLStateMachine$sendEventWithDelay$1(ConcreteSCXMLStateMachine concreteSCXMLStateMachine, String str) {
        super(1);
        this.this$0 = concreteSCXMLStateMachine;
        this.$eventName = str;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        DispatchQueue dispatchQueue;
        if (z) {
            dispatchQueue = this.this$0.q;
            dispatchQueue.async(new AnonymousClass1());
        }
    }
}
