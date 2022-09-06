package com.amazon.alexa.accessorykit.focusfilter;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.notificationpublisher.FocusFilterBridgeModule;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes6.dex */
public class FocusFilterInfoSupplierModule {
    private final FocusFilterBridgeModule focusFilterBridgeModule;
    private final MapModelTransformer<String> focusFilterEventMapModelTransformer;
    private final RxRN rxRN;

    public FocusFilterInfoSupplierModule(RxRN rxRN, ContainerProvider containerProvider, FocusFilterBridgeModule focusFilterBridgeModule) {
        Preconditions.notNull(rxRN, "rxRn");
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(focusFilterBridgeModule, "focusFilterBridgeModule");
        this.rxRN = rxRN;
        this.focusFilterBridgeModule = focusFilterBridgeModule;
        this.focusFilterEventMapModelTransformer = new FocusFilterInfoTransformer(containerProvider);
    }

    public void queryFocusFilterSettings(ReadableMap readableMap, String str) {
        this.rxRN.subscribe(RxRNEventId.from(readableMap), this.focusFilterEventMapModelTransformer, this.focusFilterBridgeModule.getObservable(str));
    }
}
