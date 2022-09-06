package com.amazon.alexa.accessory.streams.control;

import com.amazon.alexa.accessory.AccessoryStream;
import com.amazon.alexa.accessory.internal.util.LoggerUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.UIUtils;
import com.amazon.alexa.accessory.io.Buffer;
import com.amazon.alexa.accessory.io.DataSink;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class ControlStream implements AccessoryStream {
    private final TransportDispatcher dispatcher;
    private final MessageAuthenticationMode messageAuthenticationMode;
    private final Map<Accessories.Command, ControlMessageHandler> messageHandlers;
    private final Map<Accessories.Command, ControlResponseHandler> responseHandlers;

    /* loaded from: classes6.dex */
    public enum MessageAuthenticationMode {
        FORCE_UNAUTHENTICATED(false),
        ATTEMPT_AUTHENTICATION(true);
        
        private final boolean shouldAttemptAuthenticationIfSupported;

        MessageAuthenticationMode(boolean z) {
            this.shouldAttemptAuthenticationIfSupported = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean shouldAttemptAuthenticationIfSupported() {
            return this.shouldAttemptAuthenticationIfSupported;
        }
    }

    public ControlStream(TransportDispatcher transportDispatcher, MessageAuthenticationMode messageAuthenticationMode) {
        Preconditions.notNull(transportDispatcher, "dispatcher");
        this.dispatcher = transportDispatcher;
        this.messageHandlers = new HashMap();
        this.responseHandlers = new HashMap();
        this.messageAuthenticationMode = messageAuthenticationMode;
    }

    private boolean handleMessage(final ControlMessage controlMessage) throws Exception {
        final ControlMessageHandler controlMessageHandler;
        synchronized (this.messageHandlers) {
            controlMessageHandler = this.messageHandlers.get(controlMessage.getCommand());
        }
        if (controlMessageHandler == null) {
            return false;
        }
        return ((Boolean) UIUtils.execute(new Callable() { // from class: com.amazon.alexa.accessory.streams.control.-$$Lambda$ControlStream$T-uMBL9kFRwcMfTCwEZwd7w7Q6E
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ControlStream.this.lambda$handleMessage$0$ControlStream(controlMessage, controlMessageHandler);
            }
        })).booleanValue();
    }

    private boolean handleResponse(final Accessories.Command command, final Accessories.Response response) throws Exception {
        final ControlResponseHandler controlResponseHandler;
        synchronized (this.responseHandlers) {
            controlResponseHandler = this.responseHandlers.get(command);
        }
        if (controlResponseHandler == null) {
            return false;
        }
        return ((Boolean) UIUtils.execute(new Callable() { // from class: com.amazon.alexa.accessory.streams.control.-$$Lambda$ControlStream$78mLwCMPfwFTk7A_DkL4Ov2_vE8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ControlStream.this.lambda$handleResponse$1$ControlStream(command, response, controlResponseHandler);
            }
        })).booleanValue();
    }

    public void addMessageHandler(Accessories.Command command, ControlMessageHandler controlMessageHandler) {
        Preconditions.notNull(command, "command");
        Preconditions.notNull(controlMessageHandler, "messageHandler");
        synchronized (this.messageHandlers) {
            if (!this.messageHandlers.containsKey(command)) {
                this.messageHandlers.put(command, controlMessageHandler);
            } else {
                throw new IllegalArgumentException("A message handler is already registered for " + command);
            }
        }
    }

    public void addResponseHandler(Accessories.Command command, ControlResponseHandler controlResponseHandler) {
        Preconditions.notNull(command, "command");
        Preconditions.notNull(controlResponseHandler, "responseHandler");
        synchronized (this.responseHandlers) {
            if (!this.responseHandlers.containsKey(command)) {
                this.responseHandlers.put(command, controlResponseHandler);
            } else {
                throw new IllegalArgumentException("A response handler is already registered for " + command);
            }
        }
    }

    public void dispatch(ControlMessage controlMessage, long j) {
        throw new UnsupportedOperationException("Boom! Not yet implemented.");
    }

    @Override // com.amazon.alexa.accessory.AccessoryStream
    public int getId() {
        return 0;
    }

    @Override // com.amazon.alexa.accessory.AccessoryStream
    public boolean handleData(SizedSource sizedSource) throws Exception {
        sizedSource.reset();
        ProtobufControlMessage create = new ProtobufControlMessage.Factory().create((Source) sizedSource);
        return !create.isResponse() ? handleMessage(create) : handleResponse(create.getCommand(), (Accessories.Response) create.getPayload());
    }

    public /* synthetic */ Boolean lambda$handleMessage$0$ControlStream(ControlMessage controlMessage, ControlMessageHandler controlMessageHandler) throws Exception {
        LoggerUtils.received(controlMessage);
        controlMessageHandler.onMessageReceived(this, controlMessage.getCommand(), controlMessage.getPayload());
        return true;
    }

    public /* synthetic */ Boolean lambda$handleResponse$1$ControlStream(Accessories.Command command, Accessories.Response response, ControlResponseHandler controlResponseHandler) throws Exception {
        LoggerUtils.received(command, response);
        controlResponseHandler.onResponseReceived(this, command, response);
        return true;
    }

    public void removeMessageHandler(ControlMessageHandler controlMessageHandler) {
        Preconditions.notNull(controlMessageHandler, "messageHandler");
        synchronized (this.messageHandlers) {
            Iterator<Map.Entry<Accessories.Command, ControlMessageHandler>> it2 = this.messageHandlers.entrySet().iterator();
            while (it2.hasNext()) {
                if (it2.next().getValue() == controlMessageHandler) {
                    it2.remove();
                }
            }
        }
    }

    public void removeResponseHandler(ControlResponseHandler controlResponseHandler) {
        Preconditions.notNull(controlResponseHandler, "responseHandler");
        synchronized (this.responseHandlers) {
            Iterator<Map.Entry<Accessories.Command, ControlResponseHandler>> it2 = this.responseHandlers.entrySet().iterator();
            while (it2.hasNext()) {
                if (it2.next().getValue() == controlResponseHandler) {
                    it2.remove();
                }
            }
        }
    }

    public void respond(Accessories.Command command, Common.ErrorCode errorCode) {
        dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(command).setResponse(Accessories.Response.newBuilder().setErrorCode(errorCode)).mo10084build()));
    }

    public void dispatch(ControlMessage controlMessage) {
        dispatch(this.dispatcher, controlMessage, this.messageAuthenticationMode);
    }

    public static void dispatch(TransportDispatcher transportDispatcher, ControlMessage controlMessage, MessageAuthenticationMode messageAuthenticationMode) {
        LoggerUtils.send(controlMessage);
        Buffer buffer = new Buffer();
        try {
            controlMessage.write(new DataSink(buffer));
            transportDispatcher.dispatch(TransportTransaction.newBuilder().stream(0).data(buffer).attemptAuthenticationIfSupported(messageAuthenticationMode.shouldAttemptAuthenticationIfSupported()).commit(true).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
