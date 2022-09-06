package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.api.commsbridge.EventListener;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import java.util.UUID;
/* loaded from: classes12.dex */
public interface CommsNativeBridge {
    UUID addEventListener(@NonNull String str, @NonNull EventListener<String> eventListener);

    void emitEventToReact(@NonNull String str, @Nullable Object obj);

    @Nullable
    EventListener<String> getEventListener(@NonNull UUID uuid);

    boolean isRequestHandlerRegistered(@NonNull String str);

    UUID registerRequestHandler(@NonNull String str, @NonNull RequestHandler<String> requestHandler);

    @Nullable
    EventListener<String> removeEventListener(@NonNull UUID uuid);

    void unregisterRequestHandler(@NonNull UUID uuid);
}
