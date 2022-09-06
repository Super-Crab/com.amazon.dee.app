package com.amazon.deecomms.media.photos;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
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
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
/* loaded from: classes12.dex */
public class SharedPreferenceEndpointsCache implements EndpointsCache {
    public static final String CACHED_ACCOUNT = "endpoints_cache_cachedAccount";
    protected static final String CACHE_TIMESTAMP = "endpoints_cache_cacheTimestamp";
    public static final String CONTENT_ENDPOINT = "endpoints_cache_cachedContentEndpoint";
    public static final String ENDPOINTS_CACHE = "endpoints_cache_";
    public static final String GROUP_ENDPOINT = "endpoints_cache_cachedGroupEndpoint";
    public static final String META_DATA_ENDPOINT = "endpoints_cache_cachedMetaDataEndpoint";
    private String directedId;
    private long mCacheLifeSpanMS;
    private final Context mContext;
    private EventBus mEventBus;
    SharedPreferences persistentStorage;
    private Subscription userChangedSubscription;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SharedPreferenceEndpointsCache.class);
    private static final String className = SharedPreferenceEndpointsCache.class.getName();

    public SharedPreferenceEndpointsCache(Context context, final IdentityService identityService, long j, TimeUnit timeUnit, EventBus eventBus) {
        this.mCacheLifeSpanMS = 3600000L;
        this.mContext = context;
        this.mEventBus = eventBus;
        this.persistentStorage = context.getSharedPreferences("SHARED_PREFS", 0);
        this.mEventBus.getSubscriber().subscribeFilter($$Lambda$SharedPreferenceEndpointsCache$03xjREqgdL7fcg04mebtGwhu7c0.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.media.photos.-$$Lambda$SharedPreferenceEndpointsCache$iET4DFQFJtAt-dlixsInh-h8Nkk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SharedPreferenceEndpointsCache.this.lambda$new$1$SharedPreferenceEndpointsCache(identityService, message);
            }
        });
        setCurrentUser(identityService.getUser(className));
        this.mCacheLifeSpanMS = timeUnit.toMillis(j);
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[Sharing] Cache lifespan set to :");
        outline1.append(this.mCacheLifeSpanMS);
        outline1.append(" ms");
        commsLogger.d(outline1.toString());
    }

    @VisibleForTesting
    boolean areEndpointsValid(String str, String str2, String str3, String str4) {
        String string = this.persistentStorage.getString(CACHED_ACCOUNT, "");
        return str != null && (TextUtils.isEmpty(string) || string.equals(str)) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4);
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public boolean cacheHasExpired() {
        boolean z = getCurrentTimeMillis() >= this.persistentStorage.getLong(CACHE_TIMESTAMP, 0L) || getEndpoints() == null;
        if (z) {
            LOG.d("[Sharing] Shared preference endpoints cache has expired.");
        }
        return z;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void clear() {
        SharedPreferences sharedPreferences = this.persistentStorage;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().remove(CACHE_TIMESTAMP).remove(CACHED_ACCOUNT).remove(META_DATA_ENDPOINT).remove(CONTENT_ENDPOINT).remove(GROUP_ENDPOINT).apply();
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
        String string = sharedPreferences.getString(META_DATA_ENDPOINT, null);
        String string2 = this.persistentStorage.getString(CONTENT_ENDPOINT, null);
        String string3 = this.persistentStorage.getString(GROUP_ENDPOINT, null);
        if (!areEndpointsValid(this.directedId, string, string2, string3)) {
            return null;
        }
        return new Endpoints(string, string2, string3);
    }

    public /* synthetic */ void lambda$new$1$SharedPreferenceEndpointsCache(IdentityService identityService, Message message) {
        setCurrentUser(identityService.getUser(className));
    }

    @VisibleForTesting
    void setCurrentUser(@Nullable UserIdentity userIdentity) {
        if (userIdentity != null) {
            this.directedId = userIdentity.getDirectedId();
            this.persistentStorage = this.mContext.getSharedPreferences("SHARED_PREFS", 0);
            String string = this.persistentStorage.getString(CACHED_ACCOUNT, "");
            if (TextUtils.isEmpty(string) || string.equals(this.directedId)) {
                return;
            }
            clear();
            return;
        }
        clear();
        this.directedId = null;
        this.persistentStorage = null;
    }

    @VisibleForTesting
    void setDirectedId(String str) {
        this.directedId = str;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void setEndpoints(@NonNull Endpoints endpoints) {
        if (this.persistentStorage != null && areEndpointsValid(this.directedId, endpoints.getMetaDataEndpoint(), endpoints.getContentEndpoint(), endpoints.getGroupEndpoint())) {
            long currentTimeMillis = getCurrentTimeMillis() + this.mCacheLifeSpanMS;
            CommsLogger commsLogger = LOG;
            StringBuilder outline111 = GeneratedOutlineSupport1.outline111("[Sharing] Storing CDS Endpoints until:", currentTimeMillis, " - Cache lifespan in ms:");
            outline111.append(this.mCacheLifeSpanMS);
            commsLogger.d(outline111.toString());
            this.persistentStorage.edit().putString(CACHED_ACCOUNT, this.directedId).putLong(CACHE_TIMESTAMP, currentTimeMillis).putString(META_DATA_ENDPOINT, endpoints.getMetaDataEndpoint()).putString(CONTENT_ENDPOINT, endpoints.getContentEndpoint()).putString(GROUP_ENDPOINT, endpoints.getGroupEndpoint()).apply();
        }
    }
}
