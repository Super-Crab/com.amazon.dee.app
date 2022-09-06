package com.amazon.alexa.accessory.capabilities.hearing;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.MappedResponseAction;
import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementProducer;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
/* loaded from: classes.dex */
public final class HearingEnhancementCapability extends AccessoryCapability {
    private final HearingEnhancementHandler actionHandler;
    private final HearingEnhancementProducer hearingEnhancementProducer;
    private ControlStream stream;

    /* loaded from: classes.dex */
    final class HearingEnhancementHandler implements HearingEnhancementProducer.ActionHandler {
        private final ActionQueue getAudiogramQueue = new ActionQueue();
        private final ActionQueue setAudiogramQueue = new ActionQueue();
        private final ActionQueue getMediaEnhancementCorrectionAmountQueue = new ActionQueue();
        private final ActionQueue setMediaEnhancementCorrectionAmountQueue = new ActionQueue();

        HearingEnhancementHandler() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handleSetAudiogram$0(Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("setAudiogram failed with error code ")));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ CompletableResult.Value lambda$handleSetMediaEnhancementCorrectionAmount$1(Accessories.Response response) throws IOException {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                return CompletableResult.Value.complete();
            }
            throw new IOException(GeneratedOutlineSupport1.outline34(response, GeneratedOutlineSupport1.outline107("setCorrectionAmount failed with error code ")));
        }

        void cancelAllActions() {
            this.getAudiogramQueue.cancelAll();
            this.setAudiogramQueue.cancelAll();
            this.getMediaEnhancementCorrectionAmountQueue.cancelAll();
            this.setMediaEnhancementCorrectionAmountQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementProducer.ActionHandler
        public void handleGetAudiogram(int i, Producer.Result<Hearing.Audiogram> result) {
            this.getAudiogramQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_AUDIOGRAM).setGetAudiogram(Hearing.GetAudiogram.newBuilder().setDeviceId(i)).mo10084build()), HearingEnhancementCapability.this.stream, result, Accessories.Response.PayloadCase.AUDIOGRAM, $$Lambda$zBtQzJ_zq5AtreNWo36KeQw5kZo.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementProducer.ActionHandler
        public void handleGetMediaEnhancementCorrectionAmount(int i, Producer.Result<Hearing.MediaEnhancementCorrectionAmount> result) {
            this.getMediaEnhancementCorrectionAmountQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT).setGetMediaEnhancementCorrectionAmount(Hearing.GetMediaEnhancementCorrectionAmount.newBuilder().setDeviceId(i)).mo10084build()), HearingEnhancementCapability.this.stream, result, Accessories.Response.PayloadCase.MEDIA_ENHANCEMENT_CORRECTION_AMOUNT, $$Lambda$SRFa8aCoq3Y1weGu6PBYEXY7J8E.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementProducer.ActionHandler
        public void handleSetAudiogram(Hearing.Audiogram audiogram, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(audiogram, EventBusConstants.JSON_KEY_AUDIO_PROFILE);
            this.setAudiogramQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_AUDIOGRAM).setSetAudiogram(Hearing.SetAudiogram.newBuilder().setAudiogram(audiogram)).mo10084build()), HearingEnhancementCapability.this.stream, result, $$Lambda$HearingEnhancementCapability$HearingEnhancementHandler$1yZXBmQu7LoOE5LaKLb26VsyEGc.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.hearing.HearingEnhancementProducer.ActionHandler
        public void handleSetMediaEnhancementCorrectionAmount(Hearing.MediaEnhancementCorrectionAmount mediaEnhancementCorrectionAmount, Producer.Result<CompletableResult.Value> result) {
            Preconditions.notNull(mediaEnhancementCorrectionAmount, "mediaEnhancementCorrectionAmount");
            this.setMediaEnhancementCorrectionAmountQueue.enqueue(new MappedResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.SET_MEDIA_ENHANCEMENT_CORRECTION_AMOUNT).setSetMediaEnhancementCorrectionAmount(Hearing.SetMediaEnhancementCorrectionAmount.newBuilder().setCorrectionAmount(mediaEnhancementCorrectionAmount)).mo10084build()), HearingEnhancementCapability.this.stream, result, $$Lambda$HearingEnhancementCapability$HearingEnhancementHandler$iPYRvYeYKykvedO6A6IOGb99xgU.INSTANCE));
        }
    }

    public HearingEnhancementCapability(HearingEnhancementProducer hearingEnhancementProducer) {
        Preconditions.notNull(hearingEnhancementProducer, "producer");
        this.hearingEnhancementProducer = hearingEnhancementProducer;
        this.actionHandler = new HearingEnhancementHandler();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.hearingEnhancementProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.hearingEnhancementProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        accessoryDescriptor.add(this.stream);
    }
}
