package com.amazon.alexa.accessory.repositories.cloudpairing;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessory.repositories.Producer;
/* loaded from: classes6.dex */
public interface CloudPairingProducer extends Producer<ActionHandler> {

    /* loaded from: classes6.dex */
    public interface ActionHandler {
        void handleGetCloudPairingAttributes(Producer.Result<Cloudpairing.CloudPairingAttributes> result);

        void handleGetCloudPairingStatus(Cloudpairing.Seed seed, Producer.Result<Cloudpairing.CloudPairingStatus> result);

        void handleRemoveCloudPairingKeys(Cloudpairing.Seed seed, Producer.Result<CompletableResult.Value> result);

        void handleReplaceCloudPairingKeys(Cloudpairing.Seed seed, Cloudpairing.CloudPairingKeys cloudPairingKeys, Producer.Result<CompletableResult.Value> result);

        void handleSetCloudPairingKeys(Cloudpairing.CloudPairingKeys cloudPairingKeys, Producer.Result<CompletableResult.Value> result);
    }
}
