package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import java.util.UUID;
/* loaded from: classes12.dex */
interface RequestRegistry {
    @Nullable
    RequestHandler<String> getRequestHandlerById(@NonNull UUID uuid);

    @Nullable
    RequestHandler<String> getRequestHandlerByName(@NonNull String str);

    boolean isRegistered(@NonNull String str);

    boolean registerRequestHandler(@NonNull String str, @NonNull RequestHandler<String> requestHandler, @NonNull UUID uuid);

    void unregisterRequestHandler(@NonNull UUID uuid);
}
