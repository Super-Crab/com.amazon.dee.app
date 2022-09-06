package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class CapabilityAgentMessageSender extends AlexaBidirectionalMessageSender<ab> {
    private static final String TAG = "CapabilityAgentMessageSender";
    private at<List<AlexaCapability>> capabilitiesTask;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.CapabilityAgentMessageSender$2  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ab.values().length];

        static {
            try {
                a[ab.GET_CAPABILITIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class a extends b {
        a(ExtendedClient extendedClient, AlexaDirective alexaDirective, AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
            super(extendedClient);
            add(aa.ALEXA_DIRECTIVE, alexaDirective);
            add(aa.ALEXA_DIRECTIVE_PROCESSING_CALLBACKS_PROXY, alexaDirectiveProcessingCallbacksProxy.asBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class b extends BaseMessagePayload {
        b(ExtendedClient extendedClient) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.CLIENT, extendedClient.asClient());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class c extends b {
        c(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(aa.ALEXA_DIRECTIVE_ID, str);
        }
    }

    /* loaded from: classes6.dex */
    private class d extends MessageProcessor<ab> {
        private d() {
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: a */
        public ab mo845getMessageType(Message message) {
            try {
                return ab.a(message.what);
            } catch (IllegalArgumentException e) {
                Log.e(CapabilityAgentMessageSender.TAG, "Unrecognized message type, ", e);
                return ab.UNKNOWN;
            }
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: a */
        public void processMessage(ab abVar, Bundle bundle, @Nullable Messenger messenger) {
            String str = CapabilityAgentMessageSender.TAG;
            Log.i(str, "received response " + abVar);
            if (AnonymousClass2.a[abVar.ordinal()] == 1) {
                CapabilityAgentMessageSender.this.onCapabilities(Bundles.getParcelableList(bundle, aa.ALEXA_CAPABILITIES, AlexaCapability.class));
                return;
            }
            String str2 = CapabilityAgentMessageSender.TAG;
            Log.w(str2, "Unsupported message " + abVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CapabilityAgentMessageSender(IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder, messageReceiversManager);
    }

    public void cancel(ExtendedClient extendedClient, String str) throws RemoteException {
        sendMessage(ab.CANCEL, new c(extendedClient, str).getBundle());
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<ab> createResponseProcessor() {
        return new d();
    }

    public List<AlexaCapability> getCapabilities(ExtendedClient extendedClient) throws RemoteException {
        final b bVar = new b(extendedClient);
        if (this.capabilitiesTask == null) {
            this.capabilitiesTask = new at<List<AlexaCapability>>(1000L, Collections.emptyList()) { // from class: com.amazon.alexa.api.CapabilityAgentMessageSender.1
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    CapabilityAgentMessageSender.this.sendMessage(ab.GET_CAPABILITIES, bVar.getBundle());
                }
            };
        }
        return this.capabilitiesTask.call();
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<ab> getExpectedMessageTypes() {
        return Collections.unmodifiableSet(EnumSet.of(ab.GET_CAPABILITIES));
    }

    void onCapabilities(List<AlexaCapability> list) {
        Log.i(TAG, "onCapabilities");
        at<List<AlexaCapability>> atVar = this.capabilitiesTask;
        if (atVar != null) {
            atVar.setResult(list);
        }
        this.capabilitiesTask = null;
    }

    public void preprocess(ExtendedClient extendedClient, AlexaDirective alexaDirective, AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) throws RemoteException {
        sendMessage(ab.PREPROCESS, new a(extendedClient, alexaDirective, alexaDirectiveProcessingCallbacksProxy).getBundle());
    }

    public void process(ExtendedClient extendedClient, String str) throws RemoteException {
        sendMessage(ab.PROCESS, new c(extendedClient, str).getBundle());
    }
}
