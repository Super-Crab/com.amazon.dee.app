package com.amazon.alexa.accessory.capabilities.cloudpairing;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import java.io.IOException;
/* loaded from: classes.dex */
public final class CloudPairingCapability extends AccessoryCapability {
    private final CloudPairingHandler actionHandler;
    private final CloudPairingProducer cloudPairingProducer;
    private ControlStream stream;

    /* loaded from: classes.dex */
    final class CloudPairingHandler implements CloudPairingProducer.ActionHandler {
        private final ActionQueue getCloudPairingStatusQueue = new ActionQueue();
        private final ActionQueue getCloudPairingCapabilitiesQueue = new ActionQueue();
        private final ActionQueue setCloudPairingKeysQueue = new ActionQueue();
        private final ActionQueue updateCloudPairingKeysQueue = new ActionQueue();
        private final ActionQueue removeCloudPairingKeysQueue = new ActionQueue();

        public CloudPairingHandler() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handleRemoveCloudPairingKeys$2(Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("RemoveCloudPairingKeys failed with error code ")));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handleReplaceCloudPairingKeys$1(Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("ReplaceCloudPairingKeys failed with error code ")));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handleSetCloudPairingKeys$0(Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("SetCloudPairingKeys failed with error code ")));
        }

        void cancelAllActions() {
            this.getCloudPairingStatusQueue.cancelAll();
            this.getCloudPairingCapabilitiesQueue.cancelAll();
            this.setCloudPairingKeysQueue.cancelAll();
            this.updateCloudPairingKeysQueue.cancelAll();
            this.removeCloudPairingKeysQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer.ActionHandler
        public void handleGetCloudPairingAttributes(Producer.Result<Cloudpairing.CloudPairingAttributes> result) {
            Preconditions.notNull(result, "result");
            this.getCloudPairingCapabilitiesQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CLOUD_PAIRING_ATTRIBUTES).setGetCloudPairingAttributes(Cloudpairing.GetCloudPairingAttributes.newBuilder().mo10084build()).mo10084build()), CloudPairingCapability.this.stream, result, Accessories.Response.PayloadCase.CLOUD_PAIRING_ATTRIBUTES, $$Lambda$VSwJhX8guIwtnENaW4T0Ei1hdQ.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer.ActionHandler
        public void handleGetCloudPairingStatus(Cloudpairing.Seed seed, Producer.Result<Cloudpairing.CloudPairingStatus> result) {
            Preconditions.notNull(seed, "seed");
            Preconditions.notNull(result, "result");
            this.getCloudPairingStatusQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CLOUD_PAIRING_STATUS).setGetCloudPairingStatus(Cloudpairing.GetCloudPairingStatus.newBuilder().setSeed(seed).mo10084build()).mo10084build()), CloudPairingCapability.this.stream, result, Accessories.Response.PayloadCase.CLOUD_PAIRING_STATUS, $$Lambda$Bhk07x6J0i6BG7AqoOJaK_TzJ8.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer.ActionHandler
        public void handleRemoveCloudPairingKeys(Cloudpairing.Seed seed, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(seed, "seed");
            Preconditions.notNull(result, "result");
            this.removeCloudPairingKeysQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.REMOVE_CLOUD_PAIRING_KEYS).setRemoveCloudPairingKeys(Cloudpairing.RemoveCloudPairingKeys.newBuilder().setSeed(seed).mo10084build()).mo10084build()), CloudPairingCapability.this.stream, result, $$Lambda$CloudPairingCapability$CloudPairingHandler$CIV1JBi2XPfM6UVWEgJkffIzQ.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer.ActionHandler
        public void handleReplaceCloudPairingKeys(Cloudpairing.Seed seed, Cloudpairing.CloudPairingKeys cloudPairingKeys, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(seed, "currentSeed");
            Preconditions.notNull(cloudPairingKeys, QuickTimeAtomTypes.ATOM_KEYS);
            Preconditions.notNull(result, "result");
            this.updateCloudPairingKeysQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.REPLACE_CLOUD_PAIRING_KEYS).setReplaceCloudPairingKeys(Cloudpairing.ReplaceCloudPairingKeys.newBuilder().setCurrentSeed(seed).setNewKeys(cloudPairingKeys).mo10084build()).mo10084build()), CloudPairingCapability.this.stream, result, $$Lambda$CloudPairingCapability$CloudPairingHandler$EsdESEJWfLiTJOm9Ugm6cmtz7I.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.cloudpairing.CloudPairingProducer.ActionHandler
        public void handleSetCloudPairingKeys(Cloudpairing.CloudPairingKeys cloudPairingKeys, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(cloudPairingKeys, QuickTimeAtomTypes.ATOM_KEYS);
            Preconditions.notNull(result, "result");
            this.setCloudPairingKeysQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_CLOUD_PAIRING_KEYS).setSetCloudPairingKeys(Cloudpairing.SetCloudPairingKeys.newBuilder().setKeys(cloudPairingKeys).mo10084build()).mo10084build()), CloudPairingCapability.this.stream, result, $$Lambda$CloudPairingCapability$CloudPairingHandler$QKjiCKe8cpI838SMXUVrnGiSq4.INSTANCE));
        }
    }

    public CloudPairingCapability(CloudPairingProducer cloudPairingProducer) {
        Preconditions.notNull(cloudPairingProducer, "producer");
        this.cloudPairingProducer = cloudPairingProducer;
        this.actionHandler = new CloudPairingHandler();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.cloudPairingProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.cloudPairingProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        accessoryDescriptor.add(this.stream);
    }
}
