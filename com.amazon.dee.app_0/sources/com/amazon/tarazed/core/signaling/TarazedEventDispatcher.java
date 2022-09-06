package com.amazon.tarazed.core.signaling;

import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementsKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedEventDispatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tJ\u0006\u0010\u0013\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\tJ\u001e\u0010\u0016\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\tH\u0007J\u0012\u0010\u0017\u001a\u00020\u00112\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000b0\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "registeredHandlers", "", "", "", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "registeredHandlersNames", "", "getRegisteredHandlersNames", "()Ljava/util/List;", "deregisterHandlerForKey", "", "key", "deregisterHandlers", "dispatchEvent", "eventJson", "getRegisteredHandlersForKey", "registerHandler", "handler", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedEventDispatcher {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String JSON_KEY_PAYLOAD = "payload";
    private static final String JSON_KEY_TYPE = "type";
    private static final String METRIC_IOT_MESSAGE_RECEIVED = "IotMessageReceived";
    private static final String METRIC_UNHANDLED_EVENT_TYPE = "UnhandledEventType";
    private static final String TAG = "TarazedEventDispatcher";
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final Map<String, List<TarazedEventHandler<Object>>> registeredHandlers;

    /* compiled from: TarazedEventDispatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher$Companion;", "", "()V", "JSON_KEY_PAYLOAD", "", "JSON_KEY_TYPE", "METRIC_IOT_MESSAGE_RECEIVED", "METRIC_UNHANDLED_EVENT_TYPE", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedEventDispatcher(@NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.registeredHandlers = new LinkedHashMap();
    }

    public final void deregisterHandlerForKey(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        this.registeredHandlers.remove(key);
    }

    public final void deregisterHandlers() {
        this.registeredHandlers.clear();
    }

    public final void dispatchEvent(@NotNull String eventJson) {
        String content;
        Intrinsics.checkParameterIsNotNull(eventJson, "eventJson");
        JsonElement parseJson = Json.Default.getPlain().parseJson(eventJson);
        if (!(parseJson instanceof JsonObject)) {
            parseJson = null;
        }
        JsonObject jsonObject = (JsonObject) parseJson;
        if (jsonObject != null) {
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) "type");
            if (jsonElement != null && (content = JsonElementsKt.getContent(jsonElement)) != null) {
                JsonElement jsonElement2 = (JsonElement) jsonObject.get((Object) "payload");
                List<TarazedEventHandler<Object>> list = this.registeredHandlers.get(content);
                if (list == null) {
                    this.logger.w(TAG, "No handler registered for event type \"" + content + "\", ignoring");
                    this.metricsHelper.addCount(TAG, METRIC_UNHANDLED_EVENT_TYPE, 1.0d);
                    return;
                }
                for (TarazedEventHandler<Object> tarazedEventHandler : list) {
                    Object parse = Json.Default.parse(tarazedEventHandler.getPayloadSerializer(), String.valueOf(jsonElement2));
                    if (parse != null) {
                        TarazedEvent<? extends Object> tarazedEvent = new TarazedEvent<>(content, parse);
                        this.metricsHelper.incrementMetricCounter(TAG, METRIC_IOT_MESSAGE_RECEIVED, 1.0d);
                        this.logger.d(TAG, "Dispatching message type: " + content);
                        this.logger.v(TAG, "Body of dispatched message: " + eventJson);
                        this.logger.v(TAG, "Parsed dispatched message: " + tarazedEvent);
                        tarazedEventHandler.handleEvent(tarazedEvent);
                    } else {
                        throw new SerializationException("Failed to parse payload", null, 2, null);
                    }
                }
                return;
            }
            throw new SerializationException("\"type\" cannot be null", null, 2, null);
        }
        throw new SerializationException("Expected input to be a JSON object", null, 2, null);
    }

    @VisibleForTesting
    @Nullable
    public final List<TarazedEventHandler<Object>> getRegisteredHandlersForKey(@NotNull String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.registeredHandlers.get(key);
    }

    @NotNull
    public final List<String> getRegisteredHandlersNames() {
        List<String> list;
        list = CollectionsKt___CollectionsKt.toList(this.registeredHandlers.keySet());
        return list;
    }

    public final void registerHandler(@NotNull TarazedEventHandler<?> handler) {
        List<TarazedEventHandler<Object>> mutableListOf;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        for (String str : handler.getHandledEventTypes()) {
            if (this.registeredHandlers.containsKey(str)) {
                TarazedSessionLogger tarazedSessionLogger = this.logger;
                tarazedSessionLogger.i(TAG, "Handler already registered for " + str + ", registering additional handler.");
                List<TarazedEventHandler<Object>> list = this.registeredHandlers.get(str);
                if (list != null) {
                    list.add(handler);
                }
            } else {
                Map<String, List<TarazedEventHandler<Object>>> map = this.registeredHandlers;
                mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(handler);
                map.put(str, mutableListOf);
            }
        }
    }
}
