package com.amazon.commscore.commsbridge.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import com.amazon.commscore.api.commsbridge.CommsBridgeError;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.commsbridge.EventListener;
import com.amazon.commscore.api.commsbridge.MessageHandle;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import com.amazon.commscore.dependencies.BaseComponentWrapper;
/* loaded from: classes12.dex */
public class CommsBridgeServiceWrapper extends BaseComponentWrapper<CommsBridgeService> implements CommsBridgeService {
    public CommsBridgeServiceWrapper(ComponentGetter componentGetter, Context context) {
        super(componentGetter, context);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public MessageHandle addEventListener(@NonNull String str, @NonNull EventListener<String> eventListener) {
        return mo3276getImplementation().addEventListener(str, eventListener);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public void emitEventToReact(@NonNull String str, @Nullable Object obj) {
        mo3276getImplementation().emitEventToReact(str, obj);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public boolean isRequestHandlerRegistered(@NonNull String str) {
        return mo3276getImplementation().isRequestHandlerRegistered(str);
    }

    @Override // com.amazon.commscore.api.commsbridge.CommsBridgeService
    public MessageHandle registerRequestHandler(@NonNull String str, @NonNull RequestHandler<String> requestHandler) throws CommsBridgeError {
        return mo3276getImplementation().registerRequestHandler(str, requestHandler);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.commscore.dependencies.BaseComponentWrapper
    /* renamed from: getImplementation */
    public CommsBridgeService mo3276getImplementation() {
        return this.commsCoreComponent.getCommsBridgeService();
    }
}
