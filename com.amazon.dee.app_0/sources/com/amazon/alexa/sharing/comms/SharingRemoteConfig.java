package com.amazon.alexa.sharing.comms;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
/* loaded from: classes10.dex */
public final class SharingRemoteConfig {
    private static final String TAG = "SharingRemoteConfig";
    private static SharingRemoteConfig instance;
    Lazy<AlexaCommsCoreRemoteConfigurationService> commsConfigService;

    /* loaded from: classes10.dex */
    public enum RemoteKey {
        ALEXA_SERVICE_HOST("ACMS.Endpoints.PFM.US", "https://alexa-comms-mobile-service.amazon.com"),
        ACMS_SEND_MSG_TIMEOUT("ACMS.TimeoutsSec.SendMessages", Constants.DEFAULT_SENDMSG_TIMEOUT),
        ACMS_GET_SINGLE_MSG_RETRY_BACKOFF_TIME("ACMS.BackOffTimeMs.GetSingleMessage", "200");
        
        public final Object defaultValue;
        public final String remoteKey;

        RemoteKey(String str, Object obj) {
            this.remoteKey = str;
            this.defaultValue = obj;
        }

        @Nullable
        public static RemoteKey getKeyByName(@NonNull String str) {
            RemoteKey[] values;
            for (RemoteKey remoteKey : values()) {
                if (str.equals(remoteKey.name())) {
                    return remoteKey;
                }
            }
            return null;
        }

        @NonNull
        public String getConfigKey() {
            return this.remoteKey;
        }

        @NonNull
        public Object getDefault() {
            return this.defaultValue;
        }
    }

    private SharingRemoteConfig(Lazy<AlexaCommsCoreRemoteConfigurationService> lazy) {
        this.commsConfigService = lazy;
    }

    @VisibleForTesting
    static void clearInstance() {
        instance = null;
    }

    public static SharingRemoteConfig getInstance(Lazy<AlexaCommsCoreRemoteConfigurationService> lazy) {
        if (instance == null) {
            instance = new SharingRemoteConfig(lazy);
        }
        return instance;
    }

    @NonNull
    public String getStringValue(@NonNull RemoteKey remoteKey) {
        AlexaCommsCoreRemoteConfigurationService mo358get = this.commsConfigService.mo358get();
        if (mo358get == null) {
            return (String) remoteKey.getDefault();
        }
        try {
            return mo358get.getRemoteConfiguration(remoteKey.getConfigKey(), remoteKey.getDefault()).toString();
        } catch (Exception unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Config Service failed to retrieve key ");
            outline107.append(remoteKey.getConfigKey());
            outline107.append(". Using default values.");
            Log.w(TAG, outline107.toString());
            return (String) remoteKey.getDefault();
        }
    }

    @NonNull
    public Object getValue(@NonNull RemoteKey remoteKey) {
        AlexaCommsCoreRemoteConfigurationService mo358get = this.commsConfigService.mo358get();
        if (mo358get == null) {
            return remoteKey.getDefault();
        }
        try {
            return mo358get.getRemoteConfiguration(remoteKey.getConfigKey(), remoteKey.getDefault()).getRawValue();
        } catch (Exception unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Config Service failed to retrieve key ");
            outline107.append(remoteKey.getConfigKey());
            outline107.append(". Using default values.");
            Log.w(TAG, outline107.toString());
            return remoteKey.getDefault();
        }
    }

    @Nullable
    public Object getValueByName(@NonNull String str) {
        return getValue(RemoteKey.getKeyByName(str));
    }
}
