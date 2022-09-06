package com.amazon.commscore.api.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface CommsBridgeService {
    MessageHandle addEventListener(@NonNull String str, @NonNull EventListener<String> eventListener);

    void emitEventToReact(@NonNull String str, @Nullable Object obj);

    boolean isRequestHandlerRegistered(@NonNull String str);

    MessageHandle registerRequestHandler(@NonNull String str, @NonNull RequestHandler<String> requestHandler) throws CommsBridgeError;
}
