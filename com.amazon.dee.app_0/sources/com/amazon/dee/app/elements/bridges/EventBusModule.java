package com.amazon.dee.app.elements.bridges;

import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.dee.app.elements.CollectionsFactory;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.tcomm.TCommService;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@ReactModule(name = "EventBus")
/* loaded from: classes12.dex */
public class EventBusModule extends ReactContextBaseJavaModule {
    private static final String EVENT_NAME = "onEventBusMessage";
    private static final String MODULE_NAME = "NativeEventBus";
    private static final String TAG = "EventBusModule";
    @VisibleForTesting
    static final String TCOMM_STATUS_CONNECTED = "connected";
    private final CollectionsFactory collectionsFactory;
    private final EventBus eventBus;
    private final MessageMapAdapter messageMapAdapter;
    private final ElementsSubscriber subscriber;

    /* renamed from: com.amazon.dee.app.elements.bridges.EventBusModule$1  reason: invalid class name */
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

    @VisibleForTesting
    /* loaded from: classes12.dex */
    static class ElementsSubscriber implements Subscriber {
        private MessageHandler handler;
        private UUID uuid = UUID.randomUUID();
        private Map<UUID, MessageFilter> filterMap = new HashMap();

        ElementsSubscriber() {
        }

        @Override // com.amazon.alexa.eventbus.api.Subscriber
        public UUID getUUID() {
            return this.uuid;
        }

        @Override // com.amazon.alexa.eventbus.api.Subscriber
        public void onMessageReceived(@NonNull Message message) {
            MessageHandler messageHandler = this.handler;
            if (messageHandler != null) {
                messageHandler.handle(message);
            }
        }

        public void setHandler(MessageHandler messageHandler) {
            this.handler = messageHandler;
        }

        UUID subscribe(@NonNull MessageFilter messageFilter) {
            UUID randomUUID = UUID.randomUUID();
            synchronized (this.filterMap) {
                this.filterMap.put(randomUUID, messageFilter);
            }
            return randomUUID;
        }

        @Override // com.amazon.alexa.eventbus.api.Subscriber
        public boolean supportsMessage(@NonNull Message message) {
            synchronized (this.filterMap) {
                for (MessageFilter messageFilter : this.filterMap.values()) {
                    if (messageFilter.isMatch(message)) {
                        return true;
                    }
                }
                return false;
            }
        }

        void unsubscribe(@NonNull UUID uuid) {
            synchronized (this.filterMap) {
                this.filterMap.remove(uuid);
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes12.dex */
    static class MessageMapAdapter {
        @VisibleForTesting
        static final String FIELD_DATE = "date";
        @VisibleForTesting
        static final String FIELD_EVENT_TYPE = "eventType";
        @VisibleForTesting
        static final String FIELD_PAYLOAD = "payload";
        @VisibleForTesting
        static final String FIELD_PAYLOAD_TYPE = "payloadType";
        @VisibleForTesting
        static final String FIELD_SOURCE = "source";
        @VisibleForTesting
        static final String FIELD_TTL = "ttl";
        @VisibleForTesting
        static final String FIELD_UUID = "uuid";
        private final CollectionsFactory collectionsFactory;

        MessageMapAdapter(CollectionsFactory collectionsFactory) {
            this.collectionsFactory = collectionsFactory;
        }

        Message fromReadableMap(@NonNull ReadableMap readableMap) {
            Message.Builder builder = new Message.Builder();
            if (readableMap.hasKey("source") && !readableMap.isNull("source")) {
                builder.setSource(Message.Source.fromString(readableMap.getString("source")));
            }
            if (readableMap.hasKey(FIELD_EVENT_TYPE) && !readableMap.isNull(FIELD_EVENT_TYPE)) {
                builder.setEventType(readableMap.getString(FIELD_EVENT_TYPE));
            }
            if (readableMap.hasKey("ttl") && !readableMap.isNull("ttl")) {
                builder.setTTL(readableMap.getInt("ttl"));
            }
            if (readableMap.hasKey("payload") && !readableMap.isNull("payload") && readableMap.hasKey(FIELD_PAYLOAD_TYPE) && !readableMap.isNull(FIELD_PAYLOAD_TYPE)) {
                int ordinal = Message.PayloadType.fromString(readableMap.getString(FIELD_PAYLOAD_TYPE)).ordinal();
                if (ordinal == 0) {
                    builder.setPayload(readableMap.getString("payload"));
                } else if (ordinal == 1) {
                    builder.setPayload(Base64.decode(readableMap.getString("payload"), 0));
                }
            }
            return builder.build();
        }

        WritableMap toWritableMap(@NonNull Message message) {
            WritableMap createMap = this.collectionsFactory.createMap();
            createMap.putString("uuid", message.getUUID().toString());
            createMap.putString("source", message.getSource().toString());
            createMap.putString(FIELD_EVENT_TYPE, message.getEventType());
            createMap.putDouble("date", message.getDate());
            createMap.putInt("ttl", (int) message.getTTL());
            if (message.getPayload() != null && message.getPayloadType() != null) {
                createMap.putString(FIELD_PAYLOAD_TYPE, message.getPayloadType().toString());
                int ordinal = message.getPayloadType().ordinal();
                if (ordinal == 0) {
                    createMap.putString("payload", message.getPayloadAsString());
                } else if (ordinal != 1) {
                    createMap.putNull("payload");
                } else {
                    createMap.putString("payload", Base64.encodeToString(message.getPayload(), 0));
                }
            } else {
                createMap.putNull(FIELD_PAYLOAD_TYPE);
                createMap.putNull("payload");
            }
            return createMap;
        }
    }

    @VisibleForTesting
    EventBusModule(ReactApplicationContext reactApplicationContext, CollectionsFactory collectionsFactory, MessageMapAdapter messageMapAdapter, EventBus eventBus, ElementsSubscriber elementsSubscriber) {
        super(reactApplicationContext);
        this.collectionsFactory = collectionsFactory;
        this.messageMapAdapter = messageMapAdapter;
        this.eventBus = eventBus;
        this.subscriber = elementsSubscriber;
        this.subscriber.setHandler(new MessageHandler() { // from class: com.amazon.dee.app.elements.bridges.-$$Lambda$2YXJMcM66QaP2YaCUH-FKziuY0o
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EventBusModule.this.handle(message);
            }
        });
        try {
            eventBus.subscribe(elementsSubscriber);
        } catch (EventBusException e) {
            String str = TAG;
            Log.e(str, "Event bus rejected subscription: " + e);
        }
    }

    public static EventBusModule create(ReactApplicationContext reactApplicationContext, EventBus eventBus) {
        CollectionsFactory collectionsFactory = new CollectionsFactory();
        return new EventBusModule(reactApplicationContext, collectionsFactory, new MessageMapAdapter(collectionsFactory), eventBus, new ElementsSubscriber());
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getTCommStatus(Promise promise) {
        WritableMap createMap = this.collectionsFactory.createMap();
        createMap.putBoolean("connected", TCommService.isConnected());
        promise.resolve(createMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void handle(@NonNull Message message) {
        if (getReactApplicationContext().hasActiveCatalystInstance()) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(EVENT_NAME, this.messageMapAdapter.toWritableMap(message));
        }
    }

    @ReactMethod
    public void publish(ReadableMap readableMap, Promise promise) {
        try {
            if (readableMap != null) {
                this.eventBus.publish(this.messageMapAdapter.fromReadableMap(readableMap));
                promise.resolve(null);
                return;
            }
            throw new Exception("Expected a ReadableMap, got null");
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "Event bus publishing failed: " + e);
            promise.reject(e);
        }
    }

    @ReactMethod
    public void subscribe(String str, Promise promise) {
        String str2 = "Received Subscribe from RN : " + str;
        if (TextUtils.isEmpty(str)) {
            promise.reject(new IllegalArgumentException("Match string cannot be empty"));
        } else {
            promise.resolve(this.subscriber.subscribe(new EventTypeMessageFilter(str)).toString());
        }
    }

    @ReactMethod
    public void unsubscribe(String str, Promise promise) {
        this.subscriber.unsubscribe(UUID.fromString(str));
        promise.resolve(null);
    }
}
