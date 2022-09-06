package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
/* loaded from: classes.dex */
public class UnavailableBulkDataOrchestrator implements BulkDataOrchestrator {
    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator
    public void deregisterApi(BulkDataOrchestrator.BulkDataCapabilityApi bulkDataCapabilityApi) {
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator
    public void onNotifyIncomingDataAvailable(BulkDataOrchestrator.BulkDataCapabilityApi bulkDataCapabilityApi, int i) {
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator
    public void onReceiveOutgoingManifestRequest(int i, BulkDataOrchestrator.BulkDataCapabilityApi bulkDataCapabilityApi, BulkDataOrchestrator.OnRequestManifestCallback onRequestManifestCallback) {
    }

    @Override // com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator
    public void registerApi(BulkDataOrchestrator.BulkDataCapabilityApi bulkDataCapabilityApi) {
    }
}
