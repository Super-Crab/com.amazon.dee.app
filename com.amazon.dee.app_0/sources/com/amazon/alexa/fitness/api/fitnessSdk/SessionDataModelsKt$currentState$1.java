package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.fitness.api.fitnessSdk.SessionEventType;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"lastKnownState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionDataModelsKt$currentState$1 extends Lambda implements Function0<FitnessSessionState> {
    final /* synthetic */ Session $this_currentState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionDataModelsKt$currentState$1(Session session) {
        super(0);
        this.$this_currentState = session;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke */
    public final FitnessSessionState mo12560invoke() {
        List reversed;
        reversed = CollectionsKt___CollectionsKt.reversed(this.$this_currentState.getEvents());
        Iterator it2 = reversed.iterator();
        if (it2.hasNext()) {
            SessionEvent sessionEvent = (SessionEvent) it2.next();
            if (!(sessionEvent.getEventType() instanceof SessionEventType.StateChangeEvent)) {
                return null;
            }
            return ((SessionEventType.StateChangeEvent) sessionEvent.getEventType()).getState();
        }
        return null;
    }
}
