package com.amazon.alexa.accessory.avsclient.sco;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.sco.-$$Lambda$AlexaClientScoPrioritizer$RI1y-FKQFc-N6b6XsuwDzFY5DoE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaClientScoPrioritizer$RI1yFKQFcN6b6XsuwDzFY5DoE implements Function {
    public static final /* synthetic */ $$Lambda$AlexaClientScoPrioritizer$RI1yFKQFcN6b6XsuwDzFY5DoE INSTANCE = new $$Lambda$AlexaClientScoPrioritizer$RI1yFKQFcN6b6XsuwDzFY5DoE();

    private /* synthetic */ $$Lambda$AlexaClientScoPrioritizer$RI1yFKQFcN6b6XsuwDzFY5DoE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        SingleSource onErrorReturnItem;
        onErrorReturnItem = ((AccessorySession) obj).getStateRepository().query(StateFeature.BLUETOOTH_SCO_PRIORITIZED).firstOrError().onErrorReturnItem(StateOuterClass.State.newBuilder().setBoolean(false).mo10084build());
        return onErrorReturnItem;
    }
}
