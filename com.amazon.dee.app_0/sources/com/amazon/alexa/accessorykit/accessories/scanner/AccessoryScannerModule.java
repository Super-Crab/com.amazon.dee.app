package com.amazon.alexa.accessorykit.accessories.scanner;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessoryclient.client.accessories.AccessoryScanner;
import com.amazon.alexa.accessoryclient.common.api.AccessoryInquiryResult;
import com.amazon.alexa.accessoryclient.common.api.AccessoryScanResult;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRN;
import com.amazon.alexa.accessorykit.internal.rxreactnative.RxRNEventId;
/* loaded from: classes6.dex */
public final class AccessoryScannerModule {
    private final MapModelTransformer<AccessoryInquiryResult> accessoryInquiryResultModelTransformer;
    private final MapModelTransformer<AccessoryScanResult> accessoryScanResultModelTransformer;
    private final AccessoryScanner clientAccessoryScanner;
    private final RxRN rxrn;

    public AccessoryScannerModule(RxRN rxRN, ContainerProvider containerProvider, AccessoryScanner accessoryScanner) {
        Preconditions.notNull(rxRN, "rxrn");
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(accessoryScanner, "clientAccessoryScanner");
        this.rxrn = rxRN;
        this.accessoryScanResultModelTransformer = new AccessoryScanResultModelTransformer(containerProvider);
        this.accessoryInquiryResultModelTransformer = new AccessoryInquiryResultModelTransformer(containerProvider);
        this.clientAccessoryScanner = accessoryScanner;
    }

    public void observeOnBleAccessoryFoundNearby(RxRNEventId rxRNEventId) {
        Preconditions.notNull(rxRNEventId, "eventId");
        this.rxrn.subscribe(rxRNEventId, this.accessoryScanResultModelTransformer, this.clientAccessoryScanner.observeOnBleAccessoryFoundNearby());
    }

    public void observeOnConnectedAccessoryFound(RxRNEventId rxRNEventId) {
        Preconditions.notNull(rxRNEventId, "eventId");
        this.rxrn.subscribe(rxRNEventId, this.accessoryInquiryResultModelTransformer, this.clientAccessoryScanner.observeOnConnectedAccessoryFound());
    }

    public void observeOnConnectedAccessoryLost(RxRNEventId rxRNEventId) {
        Preconditions.notNull(rxRNEventId, "eventId");
        this.rxrn.subscribe(rxRNEventId, this.accessoryInquiryResultModelTransformer, this.clientAccessoryScanner.observeOnConnectedAccessoryLost());
    }
}
