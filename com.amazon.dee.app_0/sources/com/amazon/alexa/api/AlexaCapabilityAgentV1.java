package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaDirectiveProcessingCallbacksProxy;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaCapabilityAgentV1 extends MessageProcessor<ab> {
    private static final String EMPTY_REPLY_RECEIVER_ERROR_MESSAGE = "reply receiver can't be null for ";
    private static final String TAG = "AlexaCapabilityAgentV1";
    private final AlexaCapabilityAgentService alexaCapabilityAgentService;
    private final Map<String, AlexaDirective> processingDirectives = new HashMap();
    private final Map<String, AlexaDirectiveProcessingCallbacksProxy> directiveProcessingCallbacks = new HashMap();

    /* renamed from: com.amazon.alexa.api.AlexaCapabilityAgentV1$4  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$CapabilityAgentMessageType = new int[ab.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$CapabilityAgentMessageType[ab.PREPROCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$CapabilityAgentMessageType[ab.PROCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$CapabilityAgentMessageType[ab.CANCEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$CapabilityAgentMessageType[ab.GET_CAPABILITIES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class CapabilitiesMessagePayload extends BaseMessagePayload {
        CapabilitiesMessagePayload(ExtendedClient extendedClient, List<AlexaCapability> list) {
            super(extendedClient);
            addParcelableArray(aa.ALEXA_CAPABILITIES, list);
        }
    }

    public AlexaCapabilityAgentV1(AlexaCapabilityAgentService alexaCapabilityAgentService) {
        this.alexaCapabilityAgentService = alexaCapabilityAgentService;
    }

    private void cancel(Bundle bundle) {
        final String string = Bundles.getString(bundle, aa.ALEXA_DIRECTIVE_ID);
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaCapabilityAgentV1.3
            @Override // java.lang.Runnable
            public void run() {
                AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy = (AlexaDirectiveProcessingCallbacksProxy) AlexaCapabilityAgentV1.this.directiveProcessingCallbacks.remove(string);
                if (AlexaCapabilityAgentV1.this.processingDirectives.containsKey(string)) {
                    AlexaCapabilityAgentV1.this.onCancelStarted(alexaDirectiveProcessingCallbacksProxy);
                    AlexaDirective alexaDirective = (AlexaDirective) AlexaCapabilityAgentV1.this.processingDirectives.remove(string);
                    boolean z = false;
                    try {
                        z = AlexaCapabilityAgentV1.this.alexaCapabilityAgentService.cancel(alexaDirective);
                    } catch (Exception e) {
                        Log.e(AlexaCapabilityAgentV1.TAG, String.format(java.util.Locale.US, "Error encountered while cancelling directive %s. %s", alexaDirective.getAlexaHeader().getMessageId(), e.getMessage()));
                    }
                    if (z) {
                        AlexaCapabilityAgentV1.this.onCancelFinished(alexaDirectiveProcessingCallbacksProxy);
                        return;
                    }
                }
                AlexaCapabilityAgentV1.this.onError(alexaDirectiveProcessingCallbacksProxy);
            }
        });
    }

    private List<AlexaCapability> getCapabilities(Bundle bundle) {
        Set<AlexaCapability> capabilities = this.alexaCapabilityAgentService.getCapabilities();
        if (capabilities == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(capabilities.size());
        arrayList.addAll(capabilities);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCancelFinished(@Nullable AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
        if (alexaDirectiveProcessingCallbacksProxy != null) {
            try {
                alexaDirectiveProcessingCallbacksProxy.onCancelFinished();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception sending cancel finished callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCancelStarted(@Nullable AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
        if (alexaDirectiveProcessingCallbacksProxy != null) {
            try {
                alexaDirectiveProcessingCallbacksProxy.onCancelStarted();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception sending cancel started callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onError(@Nullable AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
        if (alexaDirectiveProcessingCallbacksProxy != null) {
            try {
                alexaDirectiveProcessingCallbacksProxy.onError();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception sending error callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProcessFinished(@Nullable AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
        if (alexaDirectiveProcessingCallbacksProxy != null) {
            try {
                alexaDirectiveProcessingCallbacksProxy.onProcessFinished();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception sending finished callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProcessStarted(@Nullable AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy) {
        if (alexaDirectiveProcessingCallbacksProxy != null) {
            try {
                alexaDirectiveProcessingCallbacksProxy.onProcessStarted();
            } catch (RemoteException e) {
                Log.e(TAG, "Exception sending started callback", e);
            }
        }
    }

    private void preprocess(Bundle bundle) {
        final AlexaDirective alexaDirective = (AlexaDirective) Bundles.getParcelable(bundle, aa.ALEXA_DIRECTIVE, AlexaDirective.class);
        AlexaDirectiveProcessingCallbacksProxy asInterface = AlexaDirectiveProcessingCallbacksProxy.Stub.asInterface(Bundles.getBinder(bundle, aa.ALEXA_DIRECTIVE_PROCESSING_CALLBACKS_PROXY));
        final String messageId = alexaDirective.getAlexaHeader().getMessageId();
        this.processingDirectives.put(messageId, alexaDirective);
        this.directiveProcessingCallbacks.put(messageId, asInterface);
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaCapabilityAgentV1.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z = false;
                try {
                    z = AlexaCapabilityAgentV1.this.alexaCapabilityAgentService.preprocess(alexaDirective);
                } catch (Exception e) {
                    Log.e(AlexaCapabilityAgentV1.TAG, String.format(java.util.Locale.US, "Error encountered while preprocessing directive %s. %s", alexaDirective.getAlexaHeader().getMessageId(), e.getMessage()));
                }
                if (!z) {
                    AlexaCapabilityAgentV1.this.onError((AlexaDirectiveProcessingCallbacksProxy) AlexaCapabilityAgentV1.this.directiveProcessingCallbacks.get(messageId));
                }
            }
        });
    }

    private void process(Bundle bundle) {
        final String string = Bundles.getString(bundle, aa.ALEXA_DIRECTIVE_ID);
        ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaCapabilityAgentV1.2
            @Override // java.lang.Runnable
            public void run() {
                AlexaDirectiveProcessingCallbacksProxy alexaDirectiveProcessingCallbacksProxy = (AlexaDirectiveProcessingCallbacksProxy) AlexaCapabilityAgentV1.this.directiveProcessingCallbacks.remove(string);
                if (AlexaCapabilityAgentV1.this.processingDirectives.containsKey(string)) {
                    AlexaDirective alexaDirective = (AlexaDirective) AlexaCapabilityAgentV1.this.processingDirectives.remove(string);
                    AlexaCapabilityAgentV1.this.onProcessStarted(alexaDirectiveProcessingCallbacksProxy);
                    boolean z = false;
                    try {
                        z = AlexaCapabilityAgentV1.this.alexaCapabilityAgentService.process(alexaDirective);
                    } catch (Exception e) {
                        Log.e(AlexaCapabilityAgentV1.TAG, String.format(java.util.Locale.US, "Error encountered while processing directive %s. %s", alexaDirective.getAlexaHeader().getMessageId(), e.getMessage()));
                    }
                    if (z) {
                        AlexaCapabilityAgentV1.this.onProcessFinished(alexaDirectiveProcessingCallbacksProxy);
                        return;
                    }
                }
                AlexaCapabilityAgentV1.this.onError(alexaDirectiveProcessingCallbacksProxy);
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public ab mo845getMessageType(Message message) {
        try {
            return ab.a(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type, ", e);
            return ab.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public synchronized void processMessage(ab abVar, Bundle bundle, @Nullable Messenger messenger) {
        try {
            ExtendedClient client = Bundles.getClient(bundle);
            int ordinal = abVar.ordinal();
            if (ordinal == 1) {
                preprocess(bundle);
            } else if (ordinal == 2) {
                process(bundle);
            } else if (ordinal == 3) {
                cancel(bundle);
            } else if (ordinal != 4) {
                String str = TAG;
                Log.w(str, "Unsupported message " + abVar);
            } else {
                Preconditions.notNull(messenger, EMPTY_REPLY_RECEIVER_ERROR_MESSAGE + abVar);
                reply(messenger, abVar, new CapabilitiesMessagePayload(client, getCapabilities(bundle)).getBundle());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message!", e);
        }
    }
}
