package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.cc;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class VisualTaskSender extends AlexaBidirectionalMessageSender<ca> implements AlexaVisualTask {
    private static final long RESULT_TIMEOUT_MILLISECONDS = 1000;
    private static final String TAG = "VisualTaskSender";
    private final ExtendedClient extendedClient;
    private at<String> getTaskComponentNameTask;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.VisualTaskSender$2  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ca.values().length];

        static {
            try {
                a[ca.GET_TASK_COMPONENT_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes6.dex */
    static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient) {
            super(extendedClient);
        }
    }

    /* loaded from: classes6.dex */
    static class b extends BaseMessagePayload {
        b(ExtendedClient extendedClient) {
            super(extendedClient);
        }
    }

    /* loaded from: classes6.dex */
    private class c extends MessageProcessor<ca> {
        private c() {
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: a */
        public ca mo845getMessageType(Message message) {
            try {
                return ca.a(message.what);
            } catch (IllegalArgumentException unused) {
                return ca.UNKNOWN;
            }
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: a */
        public void processMessage(ca caVar, Bundle bundle, @Nullable Messenger messenger) {
            if (AnonymousClass2.a[caVar.ordinal()] == 1) {
                VisualTaskSender.this.onGetTaskComponentNameResult(bundle);
                return;
            }
            String str = VisualTaskSender.TAG;
            Log.w(str, "Unsupported message: " + caVar);
        }
    }

    public VisualTaskSender(ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder, messageReceiversManager);
        this.extendedClient = extendedClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGetTaskComponentNameResult(Bundle bundle) {
        if (this.getTaskComponentNameTask != null) {
            this.getTaskComponentNameTask.setResult(Bundles.getString(bundle, cc.a.RETURN_VALUE_KEY));
            this.getTaskComponentNameTask = null;
        }
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<ca> createResponseProcessor() {
        return new c();
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<ca> getExpectedMessageTypes() {
        return Collections.unmodifiableSet(EnumSet.of(ca.GET_TASK_COMPONENT_NAME));
    }

    @Override // com.amazon.alexa.api.AlexaVisualTask
    public String getTaskComponentName() {
        try {
            final a aVar = new a(this.extendedClient);
            this.getTaskComponentNameTask = new at<String>(1000L, null) { // from class: com.amazon.alexa.api.VisualTaskSender.1
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    VisualTaskSender.this.sendMessage(ca.GET_TASK_COMPONENT_NAME, aVar.getBundle());
                }
            };
            return this.getTaskComponentNameTask.call();
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: getTaskComponentName", e);
            return null;
        }
    }

    @Override // com.amazon.alexa.api.AlexaVisualTask
    public void onStop() {
        try {
            sendMessage(ca.ON_STOP, new b(this.extendedClient).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onStop", e);
        }
    }
}
