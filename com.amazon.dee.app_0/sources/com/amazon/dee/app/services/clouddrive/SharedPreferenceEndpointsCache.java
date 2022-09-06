package com.amazon.dee.app.services.clouddrive;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.presence.eventbus.EventMessageFilter;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.clouddrive.configuration.Endpoints;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class SharedPreferenceEndpointsCache implements EndpointsCache {
    public static final String CACHED_ACCOUNT = "cachedAccount";
    private static final String CACHE_TIMESTAMP = "cacheTimestamp";
    public static final String CONTENT_ENDPOINT = "cachedContentEndpoint";
    public static final String ENDPOINTS_CACHE = "endpoints_cache_";
    public static final String GROUP_ENDPOINT = "cachedGroupEndpoint";
    public static final String META_DATA_ENDPOINT = "cachedMetaDataEndpoint";
    private static final String TAG = Log.tag(SharedPreferenceEndpointsCache.class);
    private String directedId;
    private final long mCacheLifeSpanMS;
    private final Context mContext;
    PersistentStorage persistentStorage;
    PersistentStorage.Factory persistentStorageFactory;

    public SharedPreferenceEndpointsCache(Context context, PersistentStorage.Factory factory, final IdentityService identityService, EventBus eventBus, long j, TimeUnit timeUnit) {
        this.mContext = context;
        this.persistentStorageFactory = factory;
        eventBus.getSubscriber().subscribeFilter(new EventMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.clouddrive.-$$Lambda$SharedPreferenceEndpointsCache$UC3RXMn-z_J-Stbnvgu22u8NAt0
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SharedPreferenceEndpointsCache.this.lambda$new$0$SharedPreferenceEndpointsCache(identityService, message);
            }
        });
        setCurrentUser(identityService.getUser(TAG));
        this.mCacheLifeSpanMS = timeUnit.toMillis(j);
    }

    private boolean areEndpointsValid(String str, String str2, String str3, String str4) {
        return (str == null || !str.equals(this.persistentStorage.getString("cachedAccount", "")) || str2 == null || str3 == null || str4 == null) ? false : true;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public boolean cacheHasExpired() {
        boolean z = System.currentTimeMillis() >= this.persistentStorage.getLong(CACHE_TIMESTAMP, 0L);
        Object[] objArr = new Object[1];
        objArr[0] = z ? "yes" : "no";
        String.format("cacheHasExpired? %s", objArr);
        return z;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void clear() {
        PersistentStorage persistentStorage = this.persistentStorage;
        if (persistentStorage == null) {
            return;
        }
        persistentStorage.edit().remove("cachedAccount").remove(CACHE_TIMESTAMP).remove("cachedMetaDataEndpoint").remove("cachedContentEndpoint").remove("cachedGroupEndpoint").commit();
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public Endpoints getEndpoints() {
        PersistentStorage persistentStorage = this.persistentStorage;
        if (persistentStorage == null) {
            return null;
        }
        String string = persistentStorage.getString("cachedMetaDataEndpoint", null);
        String string2 = this.persistentStorage.getString("cachedContentEndpoint", null);
        String string3 = this.persistentStorage.getString("cachedGroupEndpoint", null);
        if (!areEndpointsValid(this.directedId, string, string2, string3)) {
            return null;
        }
        return new Endpoints(string, string2, string3);
    }

    public /* synthetic */ void lambda$new$0$SharedPreferenceEndpointsCache(IdentityService identityService, Message message) {
        setCurrentUser(identityService.getUser(TAG));
    }

    void setCurrentUser(@Nullable UserIdentity userIdentity) {
        clear();
        if (userIdentity != null) {
            this.directedId = userIdentity.getDirectedId();
            PersistentStorage.Factory factory = this.persistentStorageFactory;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("endpoints_cache_");
            outline107.append(this.directedId);
            this.persistentStorage = factory.create(outline107.toString());
            setEndpointsToBuildConfigDefaults();
            return;
        }
        this.directedId = null;
        this.persistentStorage = null;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void setEndpoints(Endpoints endpoints) {
        if (this.persistentStorage == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() + this.mCacheLifeSpanMS;
        StringBuilder outline111 = GeneratedOutlineSupport1.outline111("[Sharing] CDS Endpoints will be cached until:", currentTimeMillis, " - Cache lifespan in ms:");
        outline111.append(this.mCacheLifeSpanMS);
        outline111.toString();
        this.persistentStorage.edit().set("cachedAccount", this.directedId).set(CACHE_TIMESTAMP, currentTimeMillis).set("cachedMetaDataEndpoint", endpoints.getMetaDataEndpoint()).set("cachedContentEndpoint", endpoints.getContentEndpoint()).set("cachedGroupEndpoint", endpoints.getGroupEndpoint()).commit();
    }

    public void setEndpointsToBuildConfigDefaults() {
        if (TextUtils.isEmpty("") || TextUtils.isEmpty("") || TextUtils.isEmpty("")) {
            return;
        }
        setEndpoints(new Endpoints("", "", ""));
    }
}
