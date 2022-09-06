package com.amazon.alexa.dnssd.subscribers;

import android.net.nsd.NsdServiceInfo;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.dnssd.RxNsd;
import com.amazon.alexa.dnssd.messages.DiscoveryResponseMessage;
import com.amazon.alexa.dnssd.messages.StartDiscoveryMessage;
import com.amazon.alexa.dnssd.util.Metrics;
import com.amazon.alexa.dnssd.util.ObjectMapperFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.tasks.api.TaskManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class StartDiscoverySubscriber implements DnssdEventSubscriber {
    public static final int DEFAULT_TIMEOUT = 5000;
    public static final String ERROR_RESPONSE_EVENT_TYPE = "dnssd::discover:response:error";
    public static final String REQUEST_EVENT_TYPE = "dnssd::discover:request";
    public static final String RESPONSE_EVENT_TYPE = "dnssd::discover:response";
    private static final String TAG = "StartDiscoverySubscriber";
    private final EventBus eventBus;
    private final Metrics metrics;
    private final RxNsd nsd;
    private final ObjectMapperFactory objectMapperFactory;
    private final TaskManager taskManager;

    @Inject
    public StartDiscoverySubscriber(EventBus eventBus, Metrics metrics, ObjectMapperFactory objectMapperFactory, RxNsd rxNsd, TaskManager taskManager) {
        this.eventBus = eventBus;
        this.metrics = metrics;
        this.objectMapperFactory = objectMapperFactory;
        this.nsd = rxNsd;
        this.taskManager = taskManager;
    }

    @Override // com.amazon.alexa.dnssd.subscribers.DnssdEventSubscriber
    public String getEventType() {
        return REQUEST_EVENT_TYPE;
    }

    public /* synthetic */ void lambda$onMessageReceived$0$StartDiscoverySubscriber(ObjectMapper objectMapper, NsdServiceInfo nsdServiceInfo) throws Throwable {
        String str = TAG;
        Log.i(str, "Found: " + nsdServiceInfo);
        DiscoveryResponseMessage discoveryResponseMessage = new DiscoveryResponseMessage();
        discoveryResponseMessage.textRecords = nsdServiceInfo.getAttributes();
        discoveryResponseMessage.service = new DiscoveryResponseMessage.Service();
        discoveryResponseMessage.service.type = nsdServiceInfo.getServiceType();
        discoveryResponseMessage.service.name = nsdServiceInfo.getServiceName();
        discoveryResponseMessage.service.host = nsdServiceInfo.getHost().getHostAddress();
        discoveryResponseMessage.service.port = Integer.valueOf(nsdServiceInfo.getPort());
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Returning discovered service back on event bus:\n");
        outline107.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(discoveryResponseMessage));
        Log.i(str2, outline107.toString());
        this.eventBus.publish(new Message.Builder().setEventType(RESPONSE_EVENT_TYPE).setPayload(objectMapper.writeValueAsString(discoveryResponseMessage)).build());
        this.metrics.recordCount(TAG, "subscriber_success");
    }

    public /* synthetic */ void lambda$onMessageReceived$1$StartDiscoverySubscriber(Throwable th) throws Throwable {
        String str = TAG;
        Log.i(str, "Discovery error: " + th);
        this.eventBus.publish(new Message.Builder().setEventType(ERROR_RESPONSE_EVENT_TYPE).build());
        this.metrics.recordCount(TAG, "subscriber_error");
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        final ObjectMapper newObjectMapper;
        StartDiscoveryMessage startDiscoveryMessage;
        Log.i(TAG, "Received discovery request message");
        int i = 0;
        try {
            newObjectMapper = this.objectMapperFactory.newObjectMapper();
            startDiscoveryMessage = (StartDiscoveryMessage) newObjectMapper.readValue(message.getPayloadAsString(), StartDiscoveryMessage.class);
        } catch (JsonMappingException e) {
            Log.i(TAG, "Invalid message payload received", e);
            this.eventBus.publish(new Message.Builder().setEventType(ERROR_RESPONSE_EVENT_TYPE).build());
        } catch (Throwable th) {
            Log.e(TAG, "Unexpected exception", th);
            this.eventBus.publish(new Message.Builder().setEventType(ERROR_RESPONSE_EVENT_TYPE).build());
        }
        if (startDiscoveryMessage.serviceType == null) {
            Log.i(TAG, "Received message with empty service type");
            this.eventBus.publish(new Message.Builder().setEventType(ERROR_RESPONSE_EVENT_TYPE).build());
            return;
        }
        String str = startDiscoveryMessage.serviceType;
        Integer num = startDiscoveryMessage.timeout;
        int intValue = num == null ? 5000 : num.intValue();
        String str2 = TAG;
        Log.i(str2, "Attempting to start discovery with timeout of " + intValue + "ms");
        this.nsd.discover(str, intValue).observeOn(Schedulers.from(this.taskManager.getExecutor(0))).subscribe(new Consumer() { // from class: com.amazon.alexa.dnssd.subscribers.-$$Lambda$StartDiscoverySubscriber$5qboFYDd1R3LrPe3bGSI9fdYzaI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StartDiscoverySubscriber.this.lambda$onMessageReceived$0$StartDiscoverySubscriber(newObjectMapper, (NsdServiceInfo) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.dnssd.subscribers.-$$Lambda$StartDiscoverySubscriber$byrewuoNgx2wbnXluGDVGVJI_0A
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StartDiscoverySubscriber.this.lambda$onMessageReceived$1$StartDiscoverySubscriber((Throwable) obj);
            }
        }, $$Lambda$StartDiscoverySubscriber$cbBhDI5p7xPBIyHMnd0hdbH8GQQ.INSTANCE);
        i = 1;
        this.metrics.recordCount(TAG, "onMessageReceived_success", i);
    }
}
