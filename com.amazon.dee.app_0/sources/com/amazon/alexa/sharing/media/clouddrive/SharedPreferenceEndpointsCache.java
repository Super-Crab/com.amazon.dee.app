package com.amazon.alexa.sharing.media.clouddrive;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.clouddrive.configuration.Endpoints;
import com.amazon.clouddrive.configuration.EndpointsCache;
import com.amazon.comms.log.CommsLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class SharedPreferenceEndpointsCache implements EndpointsCache {
    public static final String CACHED_ACCOUNT = "cachedAccount";
    protected static final String CACHE_TIMESTAMP = "cacheTimestamp";
    public static final String CONTENT_ENDPOINT = "cachedContentEndpoint";
    public static final String ENDPOINTS_CACHE = "endpoints_cache_";
    public static final String GROUP_ENDPOINT = "cachedGroupEndpoint";
    private static final CommsLogger LOG = CommsLogger.getLogger("Alexa-Social-Sharing", SharedPreferenceEndpointsCache.class);
    public static final String META_DATA_ENDPOINT = "cachedMetaDataEndpoint";
    private static final String TAG = "Alexa-Social-Sharing";
    private String directedId;
    private final long mCacheLifeSpanMS;
    private final Context mContext;
    SharedPreferences persistentStorage;

    public SharedPreferenceEndpointsCache(Context context, final IdentityService identityService, EventBus eventBus, long j, TimeUnit timeUnit) {
        this.mContext = context;
        eventBus.getSubscriber().subscribeFilter($$Lambda$SharedPreferenceEndpointsCache$rhf5VOQF1Xq_kYRTLbIooeGvsws.INSTANCE, new MessageHandler() { // from class: com.amazon.alexa.sharing.media.clouddrive.-$$Lambda$SharedPreferenceEndpointsCache$j1DbKdHKFDYrc54mLrg7zqn8l8k
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SharedPreferenceEndpointsCache.this.lambda$new$1$SharedPreferenceEndpointsCache(identityService, message);
            }
        });
        setCurrentUser(identityService.getUser("Alexa-Social-Sharing"));
        this.mCacheLifeSpanMS = timeUnit.toMillis(j);
    }

    boolean areEndpointsValid(String str, String str2, String str3, String str4) {
        String string = this.persistentStorage.getString("cachedAccount", "");
        return str != null && (isEmpty(string) || string.equals(str)) && !isEmpty(str2) && !isEmpty(str3) && !isEmpty(str4);
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public boolean cacheHasExpired() {
        boolean z = getCurrentTimeMillis() >= this.persistentStorage.getLong(CACHE_TIMESTAMP, 0L) || getEndpoints() == null;
        CommsLogger commsLogger = LOG;
        Object[] objArr = new Object[1];
        objArr[0] = z ? "yes" : "no";
        commsLogger.d(String.format("cacheHasExpired? %s", objArr));
        return z;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void clear() {
        SharedPreferences sharedPreferences = this.persistentStorage;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().remove("cachedAccount").remove(CACHE_TIMESTAMP).remove("cachedMetaDataEndpoint").remove("cachedContentEndpoint").remove("cachedGroupEndpoint").apply();
    }

    @VisibleForTesting
    long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public Endpoints getEndpoints() {
        SharedPreferences sharedPreferences = this.persistentStorage;
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString("cachedMetaDataEndpoint", null);
        String string2 = this.persistentStorage.getString("cachedContentEndpoint", null);
        String string3 = this.persistentStorage.getString("cachedGroupEndpoint", null);
        if (!areEndpointsValid(this.directedId, string, string2, string3)) {
            return null;
        }
        return new Endpoints(string, string2, string3);
    }

    boolean isEmpty(@Nullable String str) {
        return str == null || str.isEmpty();
    }

    public /* synthetic */ void lambda$new$1$SharedPreferenceEndpointsCache(IdentityService identityService, Message message) {
        setCurrentUser(identityService.getUser("Alexa-Social-Sharing"));
    }

    void setCurrentUser(@Nullable UserIdentity userIdentity) {
        if (userIdentity != null) {
            this.directedId = userIdentity.getDirectedId();
            this.persistentStorage = this.mContext.getSharedPreferences("SHARED_PREFS", 0);
            String string = this.persistentStorage.getString("cachedAccount", "");
            if (string.isEmpty() || string.equals(this.directedId)) {
                return;
            }
            clear();
            return;
        }
        clear();
        this.directedId = null;
        this.persistentStorage = null;
    }

    void setDirectedId(String str) {
        this.directedId = str;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void setEndpoints(Endpoints endpoints) {
        if (this.persistentStorage != null && areEndpointsValid(this.directedId, endpoints.getMetaDataEndpoint(), endpoints.getContentEndpoint(), endpoints.getGroupEndpoint())) {
            long currentTimeMillis = getCurrentTimeMillis() + this.mCacheLifeSpanMS;
            CommsLogger commsLogger = LOG;
            StringBuilder outline111 = GeneratedOutlineSupport1.outline111("[Sharing] CDS Endpoints will be cached until:", currentTimeMillis, " - Cache lifespan in ms:");
            outline111.append(this.mCacheLifeSpanMS);
            commsLogger.d(outline111.toString());
            this.persistentStorage.edit().putString("cachedAccount", this.directedId).putLong(CACHE_TIMESTAMP, currentTimeMillis).putString("cachedMetaDataEndpoint", endpoints.getMetaDataEndpoint()).putString("cachedContentEndpoint", endpoints.getContentEndpoint()).putString("cachedGroupEndpoint", endpoints.getGroupEndpoint()).apply();
        }
    }
}
