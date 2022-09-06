package com.amazon.dee.app.ui.web;

import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.tcomm.TCommService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class EventBusWebViewBridge extends JavaScriptBridge {
    @VisibleForTesting
    static final String JS_INTERFACE_NAME = "NativeEventBus";
    private static final String TAG = "EventBusWebViewBridge";
    @VisibleForTesting
    static final String TRIGGER_CONTACT_SYNC_EVENT = "comms::contacts::sync";
    private EventBus eventBus;
    private ExecutorService executorService;
    private Map<String, JavaScriptBridgeMethod> methods;
    private MultiFilterSubscriber onTCommMessageSubscriber;

    /* renamed from: com.amazon.dee.app.ui.web.EventBusWebViewBridge$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$eventbus$api$Message$PayloadType = new int[Message.PayloadType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$eventbus$api$Message$PayloadType[Message.PayloadType.Binary.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$eventbus$api$Message$PayloadType[Message.PayloadType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes12.dex */
    class AccountLinkingUpdatedMethod implements JavaScriptBridgeMethod {
        AccountLinkingUpdatedMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            javaScriptResponse.setError(!EventBusWebViewBridge.this.publish(new Message.Builder().setEventType(EventBusWebViewBridge.TRIGGER_CONTACT_SYNC_EVENT).build()));
            EventBusWebViewBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    class GetTCommStatusMethod implements JavaScriptBridgeMethod {
        GetTCommStatusMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            boolean isConnected = TCommService.isConnected();
            String str = EventBusWebViewBridge.TAG;
            Log.i(str, "Executing GetTCommStatusMethod, returning " + isConnected);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("status", isConnected);
            javaScriptResponse.setResponse(jSONObject2);
            EventBusWebViewBridge.this.completeRequest(javaScriptResponse);
        }
    }

    /* loaded from: classes12.dex */
    class PublishMethod implements JavaScriptBridgeMethod {
        PublishMethod() {
        }

        @Override // com.amazon.dee.app.ui.web.JavaScriptBridgeMethod
        public void execute(JSONObject jSONObject, JavaScriptResponse javaScriptResponse) throws JSONException {
            String string = jSONObject.getString("eventType");
            javaScriptResponse.setError(!EventBusWebViewBridge.this.publish(new Message.Builder().setEventType(string).setPayload(jSONObject.getString("payload")).build()));
            EventBusWebViewBridge.this.completeRequest(javaScriptResponse);
        }
    }

    public EventBusWebViewBridge(JavaScriptInjector javaScriptInjector, JavaScriptResponseQueue javaScriptResponseQueue, EventBus eventBus) {
        super(javaScriptInjector, javaScriptResponseQueue);
        String str = "Constructing EventBusWebViewBridge: " + javaScriptInjector + ", " + javaScriptResponseQueue;
        this.eventBus = eventBus;
        this.onTCommMessageSubscriber = new SimpleMultiFilterSubscriber();
        this.onTCommMessageSubscriber.subscribe(new EventTypeMessageFilter("tcomm::*"), new MessageHandler() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$KGOM9ArgJuQk6BdLnA6ggweC68w
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EventBusWebViewBridge.this.onTCommMessage(message);
            }
        });
        this.executorService = Executors.newSingleThreadExecutor();
        this.methods = new HashMap();
        this.methods.put("getStatus", new GetTCommStatusMethod());
        this.methods.put("accountLinkingUpdated", new AccountLinkingUpdatedMethod());
        this.methods.put("publish", new PublishMethod());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean publish(Message message) {
        Log.i(TAG, "Sending event");
        try {
            this.eventBus.publish(message);
            return true;
        } catch (EventBusException e) {
            Log.e(TAG, String.format("Failed to send event type '%s': %s", message.getEventType(), e.getMessage()));
            return false;
        }
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public Map<String, JavaScriptBridgeMethod> getExposedMethods() {
        return this.methods;
    }

    @Override // com.amazon.dee.app.ui.web.JavaScriptBridge
    public String getJavaScriptInterfaceName() {
        return JS_INTERFACE_NAME;
    }

    public /* synthetic */ void lambda$onTCommMessage$0$EventBusWebViewBridge(Message message) {
        String eventType = message.getEventType();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Backbone.trigger('");
        outline107.append(eventType.replaceAll("'", "\\'"));
        outline107.append("'");
        if (message.getPayloadType() != null) {
            outline107.append(", '");
            int ordinal = message.getPayloadType().ordinal();
            if (ordinal == 0) {
                outline107.append(message.getPayloadAsString().replace("'", "\\'").replace("\\", "\\\\"));
            } else if (ordinal == 1) {
                outline107.append(Base64.encodeToString(message.getPayload(), 0));
            }
            outline107.append("'");
        }
        outline107.append(");");
        this.javaScriptInjector.inject(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onTCommMessage(final Message message) {
        this.executorService.execute(new Runnable() { // from class: com.amazon.dee.app.ui.web.-$$Lambda$EventBusWebViewBridge$L0fsNLnbn9erF0l6QEf-iHvuSZE
            @Override // java.lang.Runnable
            public final void run() {
                EventBusWebViewBridge.this.lambda$onTCommMessage$0$EventBusWebViewBridge(message);
            }
        });
    }

    public void startListening() {
        this.eventBus.subscribe(this.onTCommMessageSubscriber);
    }

    public void stopListening() {
        this.eventBus.unsubscribe(this.onTCommMessageSubscriber);
    }
}
