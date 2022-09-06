package com.amazon.commscore.commsbridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.commsbridge.RequestHandler;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
class RequestRegistryImpl implements RequestRegistry {
    private static final CommsLogger LOG = CommsLogger.getLogger(RequestRegistryImpl.class);
    @VisibleForTesting
    final ConcurrentHashMap<UUID, String> mRequestNameByUUID = new ConcurrentHashMap<>();
    @VisibleForTesting
    final ConcurrentHashMap<String, RequestHandler<String>> mRequestHandlerByName = new ConcurrentHashMap<>();

    @Override // com.amazon.commscore.commsbridge.RequestRegistry
    @Nullable
    public RequestHandler<String> getRequestHandlerById(@NonNull UUID uuid) {
        String str = this.mRequestNameByUUID.get(uuid);
        if (str != null) {
            return getRequestHandlerByName(str);
        }
        LOG.i("[comms-bridge] un-register request handler, name:", str);
        return null;
    }

    @Override // com.amazon.commscore.commsbridge.RequestRegistry
    @Nullable
    public RequestHandler<String> getRequestHandlerByName(@NonNull String str) {
        return this.mRequestHandlerByName.get(str);
    }

    @Override // com.amazon.commscore.commsbridge.RequestRegistry
    public boolean isRegistered(@NonNull String str) {
        return this.mRequestHandlerByName.containsKey(str);
    }

    @Override // com.amazon.commscore.commsbridge.RequestRegistry
    public boolean registerRequestHandler(@NonNull String str, @NonNull RequestHandler<String> requestHandler, @NonNull UUID uuid) {
        if (this.mRequestHandlerByName.containsKey(str)) {
            return false;
        }
        LOG.i("[comms-bridge] register request handler, name:", str);
        this.mRequestNameByUUID.put(uuid, str);
        this.mRequestHandlerByName.put(str, requestHandler);
        return true;
    }

    @Override // com.amazon.commscore.commsbridge.RequestRegistry
    public void unregisterRequestHandler(@NonNull UUID uuid) {
        String str = this.mRequestNameByUUID.get(uuid);
        if (str != null) {
            LOG.i("[comms-bridge] un-register request handler, name:", str);
            this.mRequestHandlerByName.remove(str);
            this.mRequestNameByUUID.remove(uuid);
        }
    }
}
