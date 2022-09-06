package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.CommsBridgeError;
import com.amazon.commscore.api.commsbridge.EventListener;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.commsbridge.BridgeMessage;
import com.amazon.commscore.commsbridge.utils.UuidProvider;
import com.amazon.commscore.metrics.MetricKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
/* loaded from: classes12.dex */
public class EventBusBridgeAdapter implements CommsNativeBridge {
    private static final CommsLogger LOG = CommsLogger.getLogger(EventBusBridgeAdapter.class);
    @VisibleForTesting
    static final String NATIVE_EB_MSG = "comms::eb::msg-native";
    @VisibleForTesting
    static final String REACT_EB_MSG = "comms::eb::msg-react";
    @VisibleForTesting
    static Gson gson;
    private final EventBus mEventBus;
    private final EventDispatcher mEventDispatcher;
    private final EventRegistry mEventRegistry;
    private final AlexaCommsCoreMetricsService mMetricsService;
    private final RequestDispatcher mRequestDispatcher;
    private final RequestRegistry mRequestRegistry;
    private final UuidProvider mUuidProvider;

    /* renamed from: com.amazon.commscore.commsbridge.EventBusBridgeAdapter$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commscore$commsbridge$BridgeMessage$MessageType = new int[BridgeMessage.MessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$commscore$commsbridge$BridgeMessage$MessageType[BridgeMessage.MessageType.EVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$commscore$commsbridge$BridgeMessage$MessageType[BridgeMessage.MessageType.REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes12.dex */
    static class BridgeMessageAdapter implements JsonDeserializer<BridgeMessage> {
        BridgeMessageAdapter() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        /* renamed from: deserialize */
        public BridgeMessage mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            String asString = asJsonObject.get("name").getAsString();
            String asString2 = asJsonObject.get("id").getAsString();
            BridgeMessage.MessageType fromString = BridgeMessage.MessageType.fromString(asJsonObject.get("type").getAsString());
            JsonElement jsonElement2 = asJsonObject.get("payload");
            int ordinal = ((BridgeMessage.MessageType) Objects.requireNonNull(fromString)).ordinal();
            if (ordinal != 0) {
                if (ordinal != 2) {
                    EventBusBridgeAdapter.LOG.w("[comms-bridge] Unable to deserialize message");
                    EventBusBridgeAdapter.LOG.d("[comms-bridge] Unable to deserialize message", asJsonObject);
                    return null;
                }
                return EventMessage.create(asString, asString2, jsonElement2);
            }
            return RequestMessage.create(asString, asString2, jsonElement2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class EventBusResponseResolver implements ResponseResolver {
        private final RequestMessage mRequest;

        EventBusResponseResolver(@NonNull RequestMessage requestMessage) {
            this.mRequest = requestMessage;
        }

        private void recordRequestCallMetric(String str) {
            HashMap hashMap = new HashMap();
            hashMap.put("source", this.mRequest.getId());
            EventBusBridgeAdapter.this.mMetricsService.recordOccurrence(String.format("%s.%s.%s", str, this.mRequest.getName(), "call"), "comms-bridge", true, hashMap);
        }

        private void recordRequestSuccessMetric(String str) {
            HashMap hashMap = new HashMap();
            hashMap.put("source", this.mRequest.getId());
            EventBusBridgeAdapter.this.mMetricsService.recordOccurrence(String.format("%s.%s.%s", str, this.mRequest.getName(), MetricKeys.SUFFIX_SUCCESS), "comms-bridge", true, hashMap);
        }

        @Override // com.amazon.commscore.api.commsbridge.ResponseResolver
        public String getRequestId() {
            return this.mRequest.getId();
        }

        @Override // com.amazon.commscore.api.commsbridge.ResponseResolver
        public void reject(@NonNull Throwable th) {
            ResponseMessage create = ResponseMessage.create(this.mRequest, th);
            CommsLogger commsLogger = EventBusBridgeAdapter.LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[comms-bridge] Sending a Reject back for: ");
            outline107.append(this.mRequest.getId());
            commsLogger.w(outline107.toString());
            recordRequestCallMetric(MetricKeys.ALEXA_COMMS_BRIDGE_REQUEST_REJECT);
            EventBusBridgeAdapter.this.sendMessage(create);
            CommsLogger commsLogger2 = EventBusBridgeAdapter.LOG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("[comms-bridge] Completed Response for: ");
            outline1072.append(this.mRequest.getId());
            commsLogger2.w(outline1072.toString());
            recordRequestSuccessMetric(MetricKeys.ALEXA_COMMS_BRIDGE_REQUEST_REJECT);
        }

        @Override // com.amazon.commscore.api.commsbridge.ResponseResolver
        public void resolve(@Nullable Object obj) {
            ResponseMessage create = ResponseMessage.create(this.mRequest, obj);
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("[comms-bridge] Sending a Response back for: "), this.mRequest.getId(), EventBusBridgeAdapter.LOG);
            recordRequestCallMetric(MetricKeys.ALEXA_COMMS_BRIDGE_REQUEST_RESOLVE);
            EventBusBridgeAdapter.this.sendMessage(create);
            GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107("[comms-bridge] Completed Response for: "), this.mRequest.getId(), EventBusBridgeAdapter.LOG);
            recordRequestSuccessMetric(MetricKeys.ALEXA_COMMS_BRIDGE_REQUEST_RESOLVE);
        }
    }

    public EventBusBridgeAdapter(@NonNull EventBus eventBus, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this(eventBus, alexaCommsCoreMetricsService, $$Lambda$zmbM7nWs3Hj39GwpnrcpBo_frKA.INSTANCE);
    }

    private void emitReceiveReactMessageByType(BridgeMessage bridgeMessage, String str, BridgeMessage.MessageType messageType) {
        this.mMetricsService.recordOccurrence(String.format(MetricKeys.ALEXA_COMMS_BRIDGE_REACT_NATIVE_RECEIVED_FORMAT, messageType.toMetricKey(), bridgeMessage.getName()), "comms-bridge", true, GeneratedOutlineSupport1.outline133("source", str));
    }

    private void handleRequest(RequestMessage requestMessage) {
        this.mRequestDispatcher.dispatch(requestMessage, new EventBusResponseResolver(requestMessage));
    }

    private void onEvent(EventMessage eventMessage) {
        this.mEventDispatcher.dispatch(eventMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(@NonNull BridgeMessage bridgeMessage) {
        this.mEventBus.publish(new Message.Builder().setEventType(NATIVE_EB_MSG).setPayload(gson.toJson(bridgeMessage)).build());
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    public UUID addEventListener(@NonNull String str, @NonNull EventListener<String> eventListener) {
        UUID randomUuid = this.mUuidProvider.randomUuid();
        this.mEventRegistry.addEventListener(str, eventListener, randomUuid);
        return randomUuid;
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    public void emitEventToReact(@NonNull String str, @Nullable Object obj) {
        sendMessage(EventMessage.create(str, this.mUuidProvider.randomUuid().toString(), obj));
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    @Nullable
    public EventListener<String> getEventListener(@NonNull UUID uuid) {
        return this.mEventRegistry.getEventListener(uuid);
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    public boolean isRequestHandlerRegistered(@NonNull String str) {
        return this.mRequestRegistry.isRegistered(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onReactEBMessage(Message message) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[comms-bridge] REB Message from source: ");
        outline107.append(message.getSource());
        outline107.append(", ID: ");
        outline107.append(message.getUUID());
        commsLogger.i(outline107.toString());
        BridgeMessage bridgeMessage = (BridgeMessage) gson.fromJson(message.getPayloadAsString(), (Class<Object>) BridgeMessage.class);
        String str = bridgeMessage.getName() + ":" + bridgeMessage.getId();
        BridgeMessage.MessageType type = bridgeMessage.getType();
        emitReceiveReactMessageByType(bridgeMessage, str, type);
        int ordinal = type.ordinal();
        if (ordinal == 0) {
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("[comms-bridge] REB Message ");
            outline1072.append(message.getUUID());
            outline1072.append(" is a request. Key: ");
            outline1072.append(str);
            commsLogger2.i(outline1072.toString());
            handleRequest((RequestMessage) bridgeMessage);
        } else if (ordinal == 2) {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("[comms-bridge] REB Message ");
            outline1073.append(message.getUUID());
            outline1073.append(" is an event. Key: ");
            outline1073.append(str);
            commsLogger3.i(outline1073.toString());
            onEvent((EventMessage) bridgeMessage);
        } else {
            CommsLogger commsLogger4 = LOG;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("[comms-bridge] REB Message ");
            outline1074.append(message.getUUID());
            outline1074.append(" has invalid type: ");
            outline1074.append(type);
            outline1074.append(", Key: ");
            outline1074.append(str);
            commsLogger4.e(outline1074.toString());
            throw new IllegalArgumentException("Unsupported message type.");
        }
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    public UUID registerRequestHandler(@NonNull String str, @NonNull RequestHandler<String> requestHandler) {
        UUID randomUuid = this.mUuidProvider.randomUuid();
        if (this.mRequestRegistry.registerRequestHandler(str, requestHandler, randomUuid)) {
            return randomUuid;
        }
        String format = String.format("Request %s already has a registered handler", str);
        LOG.e(format);
        throw new CommsBridgeError(format);
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    @Nullable
    public EventListener<String> removeEventListener(@NonNull UUID uuid) {
        return this.mEventRegistry.removeEventListener(uuid);
    }

    @Override // com.amazon.commscore.commsbridge.CommsNativeBridge
    public void unregisterRequestHandler(@NonNull UUID uuid) {
        this.mRequestRegistry.unregisterRequestHandler(uuid);
    }

    @VisibleForTesting
    EventBusBridgeAdapter(@NonNull EventBus eventBus, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService, @NonNull UuidProvider uuidProvider) {
        this.mEventBus = eventBus;
        this.mMetricsService = alexaCommsCoreMetricsService;
        this.mEventBus.getNewSubscriber().subscribeFilter($$Lambda$EventBusBridgeAdapter$IxIH0AlbmulAEW1TzSYBakUXzYY.INSTANCE, new MessageHandler() { // from class: com.amazon.commscore.commsbridge.-$$Lambda$35PznqjPP-lb-kO9f5Lh7tgz38k
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EventBusBridgeAdapter.this.onReactEBMessage(message);
            }
        });
        this.mUuidProvider = uuidProvider;
        this.mRequestRegistry = new RequestRegistryImpl();
        this.mRequestDispatcher = new RequestDispatcherImpl(this.mRequestRegistry);
        this.mEventRegistry = new EventRegistryImpl();
        this.mEventDispatcher = new EventDispatcherImpl(this.mEventRegistry);
        gson = new GsonBuilder().serializeNulls().registerTypeHierarchyAdapter(BridgeMessage.class, new BridgeMessageAdapter()).create();
    }
}
