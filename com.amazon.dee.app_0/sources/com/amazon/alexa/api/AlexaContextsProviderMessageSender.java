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
import com.amazon.alexa.utils.validation.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlexaContextsProviderMessageSender extends AlexaBidirectionalMessageSender<AlexaContextsProviderMessageType> implements AlexaContextsProvider {
    private static final String TAG = "AlexaContextsProviderMessageSender";
    private final ExtendedClient client;
    at<Collection<AlexaContext>> getAlexaContextsTask;
    private MessageProcessor<AlexaContextsProviderMessageType> responseProcessor;

    /* renamed from: com.amazon.alexa.api.AlexaContextsProviderMessageSender$2  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaContextsProviderMessageType = new int[AlexaContextsProviderMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaContextsProviderMessageType[AlexaContextsProviderMessageType.GET_ALEXA_CONTEXTS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes6.dex */
    private class ResponseProcessor extends MessageProcessor<AlexaContextsProviderMessageType> {
        private ResponseProcessor() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: getMessageType */
        public AlexaContextsProviderMessageType mo845getMessageType(Message message) {
            try {
                return AlexaContextsProviderMessageType.fromOrdinal(message.what);
            } catch (IllegalStateException e) {
                Log.e(AlexaContextsProviderMessageSender.TAG, "Unrecognized message type, ", e);
                return AlexaContextsProviderMessageType.UNKNOWN;
            }
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        public void processMessage(AlexaContextsProviderMessageType alexaContextsProviderMessageType, Bundle bundle, @Nullable Messenger messenger) {
            String str = AlexaContextsProviderMessageSender.TAG;
            Log.i(str, "Received response " + alexaContextsProviderMessageType);
            if (alexaContextsProviderMessageType.ordinal() == 1) {
                AlexaContextsProviderMessageSender.this.onAlexaContexts(BundleTransformer.getDefaultInstance().getCollectionFromBundle(bundle, AlexaContext.class));
                return;
            }
            String str2 = AlexaContextsProviderMessageSender.TAG;
            Log.w(str2, "Unsupported message " + alexaContextsProviderMessageType);
        }
    }

    public AlexaContextsProviderMessageSender(IBinder iBinder, ExtendedClient extendedClient, MessageReceiversManager messageReceiversManager) {
        super(iBinder, messageReceiversManager);
        Preconditions.notNull(extendedClient, "client is null");
        this.client = extendedClient;
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<AlexaContextsProviderMessageType> createResponseProcessor() {
        if (this.responseProcessor == null) {
            this.responseProcessor = new ResponseProcessor();
        }
        return this.responseProcessor;
    }

    @Override // com.amazon.alexa.api.AlexaContextsProvider
    public Set<AlexaContext> getAlexaContexts() {
        try {
            final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(this.client);
            if (this.getAlexaContextsTask == null) {
                this.getAlexaContextsTask = new at<Collection<AlexaContext>>(1000L, Collections.emptySet()) { // from class: com.amazon.alexa.api.AlexaContextsProviderMessageSender.1
                    @Override // com.amazon.alexa.api.at
                    protected void execute() throws RemoteException {
                        AlexaContextsProviderMessageSender.this.sendMessage(AlexaContextsProviderMessageType.GET_ALEXA_CONTEXTS, baseMessagePayload.getBundle());
                    }
                };
            }
            return new LinkedHashSet(this.getAlexaContextsTask.call());
        } catch (RemoteException e) {
            Set<AlexaContext> emptySet = Collections.emptySet();
            Log.e(TAG, "Failed to send getAlexaContexts event ", e);
            return emptySet;
        }
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<AlexaContextsProviderMessageType> getExpectedMessageTypes() {
        return Collections.unmodifiableSet(EnumSet.of(AlexaContextsProviderMessageType.GET_ALEXA_CONTEXTS));
    }

    void onAlexaContexts(@Nullable Collection<AlexaContext> collection) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAlexaContexts, size = ");
        outline107.append(collection != null ? Integer.valueOf(collection.size()) : "null");
        Log.i(str, outline107.toString());
        at<Collection<AlexaContext>> atVar = this.getAlexaContextsTask;
        if (atVar != null) {
            atVar.setResult(collection);
            this.getAlexaContextsTask = null;
        }
    }
}
