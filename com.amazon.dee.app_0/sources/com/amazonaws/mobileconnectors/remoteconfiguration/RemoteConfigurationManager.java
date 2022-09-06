package com.amazonaws.mobileconnectors.remoteconfiguration;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.MalformedAppConfigIdException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ArcusThrottler;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.Arn;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.AttributesImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationDb;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.LocalConfigInstanceIdGenerationHelper;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AndroidRemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RequestThrottledException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class RemoteConfigurationManager {
    private static final String DEFAULT_ENDPOINT = "https://arcus-uswest.amazon.com";
    private static final String KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    private static final String REMOTE_CONFIG_SHARED_PREF_FILE_SUFFIX = "configuration.prefs";
    private static final String TAG = "RemoteConfigurationManager";
    private final String mAppConfigId;
    private final Attributes mAttributes;
    private final ConfigurationDb mConfigurationDb;
    private int mLastSuccessfulSyncAttributeHash;
    private final RemoteConfigurationFetcher mRemoteConfigurationFetcher;
    private final SharedPreferences mSharedPreferences;
    private ArcusThrottler mThrottler;

    /* loaded from: classes13.dex */
    public static class Builder {
        private static final ConcurrentHashMap<String, RemoteConfigurationManager> mManagers = new ConcurrentHashMap<>();
        private final String mAppConfigId;
        private final Context mContext;
        private JSONObject mDefaultConfiguration = new JSONObject();

        public Builder(Context context, String str) {
            if (context != null) {
                if (str != null) {
                    RemoteConfigurationManager.verifyAppConfigId(str);
                    this.mContext = context;
                    this.mAppConfigId = str;
                    return;
                }
                throw new NullPointerException("The App Configuration ID may not be null");
            }
            throw new NullPointerException("The Context may not be null");
        }

        private void ensureBuilderReady() {
            if (this.mContext != null) {
                if (this.mAppConfigId != null) {
                    if (this.mDefaultConfiguration == null) {
                        throw new IllegalStateException("The default configuration may not be null");
                    }
                    return;
                }
                throw new IllegalStateException("The App Configuration ID may not be null");
            }
            throw new IllegalStateException("The Context may not be null");
        }

        public RemoteConfigurationManager createOrGet() {
            String str = this.mAppConfigId;
            if (str != null) {
                if (!mManagers.containsKey(str)) {
                    ensureBuilderReady();
                    mManagers.putIfAbsent(this.mAppConfigId, new RemoteConfigurationManager(this));
                }
                return mManagers.get(this.mAppConfigId);
            }
            throw new IllegalStateException("An App Configuration ID must be specified");
        }

        public Builder withDefaultConfiguration(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.mDefaultConfiguration = jSONObject;
                return this;
            }
            throw new NullPointerException("The default configuration may not be null");
        }
    }

    public static Builder forAppId(Context context, String str) {
        return new Builder(context, str);
    }

    public static RemoteConfigurationManager getInstance(String str) {
        Checks.checkNotNull(str, "The App Configuration ID may not be null");
        return (RemoteConfigurationManager) Builder.mManagers.get(str);
    }

    private String getOrGenerateLocalConfigurationInstanceId() {
        String string = this.mSharedPreferences.getString(KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID, null);
        if (TextUtils.isEmpty(string)) {
            String generateLocalConfigInstanceId = LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(UUID.randomUUID().toString());
            setLocalConfigurationInstanceId(generateLocalConfigInstanceId);
            return generateLocalConfigInstanceId;
        }
        return string;
    }

    private String getPreferencesFileNameForAppConfig() {
        return GeneratedOutlineSupport1.outline92(new StringBuilder(), this.mAppConfigId, "_", REMOTE_CONFIG_SHARED_PREF_FILE_SUFFIX);
    }

    private void setLocalConfigurationInstanceId(String str) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putString(KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID, str);
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void syncOnCurrentThread(ConfigurationSyncCallback configurationSyncCallback) {
        Attributes mo6694clone = this.mAttributes.mo6694clone();
        if (!this.mThrottler.isSyncAllowedRightNow() && (this.mThrottler.getCause() != 10 || this.mLastSuccessfulSyncAttributeHash == mo6694clone.hashCode())) {
            configurationSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
            return;
        }
        RemoteConfiguration readRemoteConfiguration = this.mConfigurationDb.readRemoteConfiguration(this.mAppConfigId);
        String str = null;
        if (readRemoteConfiguration != null) {
            str = readRemoteConfiguration.getEntityTag();
        }
        try {
            RemoteConfiguration fetch = this.mRemoteConfigurationFetcher.fetch(this.mAppConfigId, mo6694clone, str, getOrGenerateLocalConfigurationInstanceId());
            this.mLastSuccessfulSyncAttributeHash = mo6694clone.hashCode();
            this.mThrottler.updateSyncTimeAfterSuccess();
            if (fetch.isUpdate()) {
                this.mConfigurationDb.saveConfiguration(fetch);
                configurationSyncCallback.onConfigurationModified(fetch.getConfiguration());
                return;
            }
            RemoteConfigurationImpl remoteConfigurationImpl = new RemoteConfigurationImpl(new ConfigurationImpl(readRemoteConfiguration.getConfiguration().getAsJsonString(), new Date()), readRemoteConfiguration.getAppConfigurationId(), readRemoteConfiguration.getOrigin(), readRemoteConfiguration.getEntityTag(), false);
            this.mConfigurationDb.saveConfiguration(remoteConfigurationImpl);
            configurationSyncCallback.onConfigurationUnmodified(remoteConfigurationImpl.getConfiguration());
        } catch (RequestThrottledException unused) {
            this.mThrottler.updateSyncTimeAfterThrottle(0L);
            configurationSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
        } catch (Exception e) {
            this.mThrottler.updateSyncTimeAfterFailure();
            configurationSyncCallback.onFailure(e);
        }
    }

    private void syncOnNewThread(final ConfigurationSyncCallback configurationSyncCallback) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.submit(new Runnable() { // from class: com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager.1
            @Override // java.lang.Runnable
            public void run() {
                RemoteConfigurationManager.this.syncOnCurrentThread(configurationSyncCallback);
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void verifyAppConfigId(String str) {
        try {
            Arn.fromArn(str);
        } catch (IllegalArgumentException unused) {
            throw new MalformedAppConfigIdException("Invalid appConfigId ARN.");
        }
    }

    public Attributes openAttributes() {
        return this.mAttributes;
    }

    public Configuration openConfiguration() {
        return this.mConfigurationDb.readConfiguration();
    }

    public void overwriteConfiguration(JSONObject jSONObject) {
        Checks.checkNotNull(jSONObject, "The Configuration cannot be null");
        this.mConfigurationDb.saveConfiguration(new RemoteConfigurationImpl(new ConfigurationImpl(jSONObject.toString(), new Date()), this.mAppConfigId, 3, null, false));
    }

    public void setRampingKey(String str) {
        if (str != null) {
            if (!str.isEmpty() && str.length() <= 64) {
                setLocalConfigurationInstanceId(LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(str));
                return;
            }
            throw new IllegalArgumentException("The ramping key cannot be empty and its length is limited to 64 characters.");
        }
        throw new NullPointerException("The ramping key cannot be null.");
    }

    public void sync(ConfigurationSyncCallback configurationSyncCallback) {
        Checks.checkNotNull(configurationSyncCallback, "ConfigurationSyncCallback cannot be null");
        syncOnNewThread(configurationSyncCallback);
    }

    private RemoteConfigurationManager(Builder builder) {
        this(builder.mContext, builder.mAppConfigId, builder.mDefaultConfiguration);
    }

    RemoteConfigurationManager(Context context, String str, JSONObject jSONObject) {
        this(context.getApplicationContext(), str, jSONObject, ConfigurationDb.getOrCreateInstance(context, str), DEFAULT_ENDPOINT);
    }

    RemoteConfigurationManager(Context context, String str, JSONObject jSONObject, ConfigurationDb configurationDb, String str2) {
        this.mLastSuccessfulSyncAttributeHash = 0;
        this.mThrottler = new ArcusThrottler();
        Checks.checkNotNull(context, "appContext cannot be null");
        Checks.checkNotNull(str, "appConfigId cannot be null");
        verifyAppConfigId(str);
        try {
            URL url = new URL(str2);
            this.mAppConfigId = str;
            this.mSharedPreferences = context.getSharedPreferences(getPreferencesFileNameForAppConfig(), 0);
            this.mAttributes = new AttributesImpl(context);
            this.mLastSuccessfulSyncAttributeHash = this.mAttributes.hashCode();
            this.mConfigurationDb = configurationDb;
            this.mRemoteConfigurationFetcher = new AndroidRemoteConfigurationFetcher(context, url);
            if (jSONObject == null) {
                return;
            }
            RemoteConfiguration readRemoteConfiguration = configurationDb.readRemoteConfiguration(this.mAppConfigId);
            if (readRemoteConfiguration != null && readRemoteConfiguration.getOrigin() != 1) {
                return;
            }
            configurationDb.saveConfiguration(new RemoteConfigurationImpl(new ConfigurationImpl(jSONObject.toString(), new Date()), this.mAppConfigId, 1, null, false));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid endpoint", e);
        }
    }
}
