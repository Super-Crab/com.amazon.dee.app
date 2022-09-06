package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.commscore.api.commsbridge.CommsBridgeError;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.commsbridge.EventListener;
import com.amazon.commscore.api.commsbridge.MessageHandle;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import java.util.UUID;
/* loaded from: classes12.dex */
public class CommsBridgeServiceImpl implements CommsBridgeService {
    private final CommsNativeBridge mBridge;

    public CommsBridgeServiceImpl(@NonNull EventBus eventBus, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.mBridge = new EventBusBridgeAdapter(eventBus, alexaCommsCoreMetricsService);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public MessageHandle addEventListener(@NonNull String str, @NonNull EventListener<String> eventListener) {
        final UUID addEventListener = this.mBridge.addEventListener(str, eventListener);
        return new MessageHandle() { // from class: com.amazon.commscore.commsbridge.CommsBridgeServiceImpl.2
            @Override // com.amazon.commscore.api.commsbridge.MessageHandle
            public boolean isRegistered() {
                return CommsBridgeServiceImpl.this.mBridge.getEventListener(addEventListener) != null;
            }

            @Override // com.amazon.commscore.api.commsbridge.MessageHandle
            public void unregister() {
                CommsBridgeServiceImpl.this.mBridge.removeEventListener(addEventListener);
            }
        };
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public void emitEventToReact(@NonNull String str, @Nullable Object obj) {
        this.mBridge.emitEventToReact(str, obj);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public boolean isRequestHandlerRegistered(@NonNull String str) {
        return this.mBridge.isRequestHandlerRegistered(str);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public MessageHandle registerRequestHandler(@NonNull final String str, @NonNull RequestHandler<String> requestHandler) throws CommsBridgeError {
        final UUID registerRequestHandler = this.mBridge.registerRequestHandler(str, requestHandler);
        return new MessageHandle() { // from class: com.amazon.commscore.commsbridge.CommsBridgeServiceImpl.1
            @Override // com.amazon.commscore.api.commsbridge.MessageHandle
            public boolean isRegistered() {
                return CommsBridgeServiceImpl.this.mBridge.isRequestHandlerRegistered(str);
            }

            @Override // com.amazon.commscore.api.commsbridge.MessageHandle
            public void unregister() {
                CommsBridgeServiceImpl.this.mBridge.unregisterRequestHandler(registerRequestHandler);
            }
        };
    }
}
