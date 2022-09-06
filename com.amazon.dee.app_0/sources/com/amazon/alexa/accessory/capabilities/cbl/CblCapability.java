package com.amazon.alexa.accessory.capabilities.cbl;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.ActionQueue;
import com.amazon.alexa.accessory.internal.SuccessResponseAction;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Cbl;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.cbl.CblProducer;
import com.amazon.alexa.accessory.repositories.cbl.CblProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import java.io.IOException;
/* loaded from: classes.dex */
public class CblCapability extends AccessoryCapability {
    private final CblActionHandler actionHandler;
    private final CblProducer cblProducer;
    private final CblProvider cblProvider;
    private ControlStream stream;

    /* loaded from: classes.dex */
    final class CblActionHandler implements CblProducer.ActionHandler {
        private final ActionQueue getCblInformationQueue = new ActionQueue();

        CblActionHandler() {
        }

        void cancelAllActions() {
            this.getCblInformationQueue.cancelAll();
        }

        @Override // com.amazon.alexa.accessory.repositories.cbl.CblProducer.ActionHandler
        public void handleGetCblInformation(Producer.Result<Cbl.CblInformation> result) {
            Preconditions.notNull(result, "result");
            this.getCblInformationQueue.enqueue(new SuccessResponseAction(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CBL_INFORMATION).mo10084build()), CblCapability.this.stream, result, Accessories.Response.PayloadCase.CBL_INFORMATION, $$Lambda$D92nwRpdqddfk0P8ANEmKYOcujA.INSTANCE));
        }

        @Override // com.amazon.alexa.accessory.repositories.cbl.CblProducer.ActionHandler
        public void handleGetCblLoginState() {
            CblCapability.this.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.GET_CBL_LOGIN_STATE).setGetCblLoginState(Cbl.GetCblLoginState.newBuilder().mo10084build()).mo10084build()));
        }
    }

    public CblCapability(CblProducer cblProducer, CblProvider cblProvider) {
        Preconditions.notNull(cblProducer, "cblProducer");
        Preconditions.notNull(cblProvider, "cblProvider");
        this.cblProducer = cblProducer;
        this.cblProvider = cblProvider;
        this.actionHandler = new CblActionHandler();
    }

    private ControlResponseHandler getCblLoginStateHandler() {
        return new ControlResponseHandler() { // from class: com.amazon.alexa.accessory.capabilities.cbl.-$$Lambda$CblCapability$uHxrz1xG9parRPGtSt_nRZCW7S8
            @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
            public final void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
                CblCapability.this.lambda$getCblLoginStateHandler$1$CblCapability(controlStream, command, response);
            }
        };
    }

    private ControlMessageHandler<Cbl.NotifyCblLoginState> getNotifyCblLoginStateHandler() {
        return new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.cbl.-$$Lambda$CblCapability$RrsGUtu1DoHRUddxd0sre31I24w
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public final void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                CblCapability.this.lambda$getNotifyCblLoginStateHandler$0$CblCapability(controlStream, command, (Cbl.NotifyCblLoginState) obj);
            }
        };
    }

    public /* synthetic */ void lambda$getCblLoginStateHandler$1$CblCapability(ControlStream controlStream, Accessories.Command command, Accessories.Response response) throws Exception {
        if (response.getErrorCode() == Common.ErrorCode.SUCCESS && response.getPayloadCase() == Accessories.Response.PayloadCase.CBL_LOGIN_STATE) {
            this.cblProvider.provideCblLoginState(response.getCblLoginState());
            return;
        }
        CblProvider cblProvider = this.cblProvider;
        cblProvider.provideCblLoginStateError(new IOException("Invalid response " + response));
    }

    public /* synthetic */ void lambda$getNotifyCblLoginStateHandler$0$CblCapability(ControlStream controlStream, Accessories.Command command, Cbl.NotifyCblLoginState notifyCblLoginState) throws Exception {
        this.cblProvider.provideCblLoginState(notifyCblLoginState.getLoginState());
        controlStream.respond(Accessories.Command.NOTIFY_CBL_LOGIN_STATE, Common.ErrorCode.SUCCESS);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.cblProducer.detachActionHandler(this.actionHandler);
        this.actionHandler.cancelAllActions();
        accessoryDescriptor.remove(this.stream);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        this.cblProducer.attachActionHandler(this.actionHandler);
        this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAwareDispatcher(), ControlStream.MessageAuthenticationMode.ATTEMPT_AUTHENTICATION);
        this.stream.addResponseHandler(Accessories.Command.GET_CBL_LOGIN_STATE, getCblLoginStateHandler());
        this.stream.addMessageHandler(Accessories.Command.NOTIFY_CBL_LOGIN_STATE, getNotifyCblLoginStateHandler());
        accessoryDescriptor.add(this.stream);
    }
}
