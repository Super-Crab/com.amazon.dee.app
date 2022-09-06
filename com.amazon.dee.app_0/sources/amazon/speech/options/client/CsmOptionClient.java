package amazon.speech.options.client;

import amazon.speech.config.BaseSpeechClientPolicy;
import amazon.speech.config.SpeechPolicyFactory;
import amazon.speech.options.remote.NullStrategy;
import amazon.speech.options.remote.RemoteSettingsManager;
import amazon.speech.options.remote.RemoteSettingsStrategy;
import amazon.speech.options.remote.RemoteStrategyFactory;
import amazon.speech.util.DebugUtil;
import amazon.speech.util.SystemPropertiesHelper;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class CsmOptionClient {
    protected static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.CONF);
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.CONF, CsmOptionClient.class);
    private static CsmOptionClient mTestClient = null;
    private static CsmOptionClient sCsmOptionClient = null;
    final RemoteSettingsManager mRemoteManager;
    SystemPropertiesHelper mSystemPropertiesHelper;

    CsmOptionClient(RemoteSettingsManager remoteSettingsManager) {
        if (remoteSettingsManager != null) {
            this.mSystemPropertiesHelper = new SystemPropertiesHelper();
            this.mRemoteManager = remoteSettingsManager;
            return;
        }
        throw new IllegalArgumentException();
    }

    static synchronized CsmOptionClient getCsmOptionClientInstance(Context context) {
        CsmOptionClient csmOptionClient;
        synchronized (CsmOptionClient.class) {
            if (context != null) {
                if (sCsmOptionClient == null) {
                    sCsmOptionClient = new CsmOptionClient(new RemoteSettingsManager(getRemoteStrategy(context, SpeechPolicyFactory.getPolicy(context))));
                }
                csmOptionClient = sCsmOptionClient;
            } else {
                throw new IllegalArgumentException("Context can't be null");
            }
        }
        return csmOptionClient;
    }

    private static RemoteSettingsStrategy getRemoteStrategy(Context context, BaseSpeechClientPolicy baseSpeechClientPolicy) {
        if (baseSpeechClientPolicy.supportsRemoteConfiguration() && baseSpeechClientPolicy.useRemoteServiceForCsmOptions() && !TextUtils.isEmpty(baseSpeechClientPolicy.getAppConfigId())) {
            return new RemoteStrategyFactory().create(context, baseSpeechClientPolicy.getAppConfigId());
        }
        Log.w(TAG, "policy was not enable useFosArcusServiceForCsmOptions");
        return new NullStrategy();
    }

    private boolean isRemoteSettingAllowedAvailableAndPrioritized(CsmOption csmOption, boolean z) {
        if (!z) {
            return false;
        }
        return (TextUtils.isEmpty(csmOption.getRemoteSettingsKey()) ^ true) && (!TextUtils.isEmpty(csmOption.getRemoteSettingsPriority()) && "arcus".equals(this.mRemoteManager.getString(csmOption.getRemoteSettingsPriority(), "")));
    }

    public static synchronized CsmOptionClient newCsmOptionClient(Context context) {
        synchronized (CsmOptionClient.class) {
            if (mTestClient != null) {
                Log.i(TAG, "TestClient was set, returning the TestClient");
                return mTestClient;
            } else if (context != null) {
                return getCsmOptionClientInstance(context);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void setTestCsmOptionClient(CsmOptionClient csmOptionClient) {
        if (csmOptionClient != null) {
            mTestClient = csmOptionClient;
            return;
        }
        throw new IllegalArgumentException("Cant have a null test client");
    }

    public boolean getBoolean(CsmOption csmOption, boolean z, boolean z2) {
        if (csmOption != null) {
            if (isRemoteSettingAllowedAvailableAndPrioritized(csmOption, z2)) {
                return this.mRemoteManager.getBoolean(csmOption.getRemoteSettingsKey(), z);
            }
            if (!TextUtils.isEmpty(csmOption.getPropertyName())) {
                String property = this.mSystemPropertiesHelper.getProperty(csmOption.getPropertyName());
                if (!TextUtils.isEmpty(property)) {
                    return this.mSystemPropertiesHelper.parseBooleanPropertyValue(property, z);
                }
            }
            return (!z2 || !(TextUtils.isEmpty(csmOption.getRemoteSettingsKey()) ^ true)) ? z : this.mRemoteManager.getBoolean(csmOption.getRemoteSettingsKey(), z);
        }
        throw new IllegalArgumentException();
    }

    public int getInteger(CsmOption csmOption, int i, boolean z) {
        if (csmOption != null) {
            if (isRemoteSettingAllowedAvailableAndPrioritized(csmOption, z)) {
                return this.mRemoteManager.getInteger(csmOption.getRemoteSettingsKey(), i);
            }
            if (!TextUtils.isEmpty(csmOption.getPropertyName())) {
                String property = this.mSystemPropertiesHelper.getProperty(csmOption.getPropertyName());
                if (!TextUtils.isEmpty(property)) {
                    return Integer.parseInt(property);
                }
            }
            return (!z || !(TextUtils.isEmpty(csmOption.getRemoteSettingsKey()) ^ true)) ? i : this.mRemoteManager.getInteger(csmOption.getRemoteSettingsKey(), i);
        }
        throw new IllegalArgumentException();
    }

    public long getLong(CsmOption csmOption, long j, boolean z) {
        if (csmOption != null) {
            if (!TextUtils.isEmpty(csmOption.getPropertyName())) {
                String property = this.mSystemPropertiesHelper.getProperty(csmOption.getPropertyName());
                if (!TextUtils.isEmpty(property)) {
                    if (DEBUG) {
                        String str = TAG;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CsmOptionClient:getLong Found local system setting: ");
                        outline107.append(csmOption.getPropertyName());
                        outline107.append(" : ");
                        outline107.append(property);
                        Log.i(str, outline107.toString());
                    }
                    return Long.parseLong(property);
                }
            }
            if (DEBUG) {
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("CsmOptionClient:getLong returning default value: ");
                outline1072.append(String.valueOf(j));
                Log.i(str2, outline1072.toString());
            }
            return j;
        }
        throw new IllegalArgumentException();
    }

    public RemoteSettingsManager getRemoteSettingsManager() {
        return this.mRemoteManager;
    }

    public String getString(CsmOption csmOption, String str, boolean z) {
        if (csmOption != null) {
            if (isRemoteSettingAllowedAvailableAndPrioritized(csmOption, z)) {
                return this.mRemoteManager.getString(csmOption.getRemoteSettingsKey(), str);
            }
            if (!TextUtils.isEmpty(csmOption.getPropertyName())) {
                String property = this.mSystemPropertiesHelper.getProperty(csmOption.getPropertyName());
                if (!TextUtils.isEmpty(property)) {
                    return property;
                }
            }
            return (!z || !(TextUtils.isEmpty(csmOption.getRemoteSettingsKey()) ^ true)) ? str : this.mRemoteManager.getString(csmOption.getRemoteSettingsKey(), str);
        }
        throw new IllegalArgumentException();
    }

    public long getLong(CsmOption csmOption, long j) {
        return getLong(csmOption, j, true);
    }

    public String getString(CsmOption csmOption, String str) {
        return getString(csmOption, str, true);
    }

    public boolean getBoolean(CsmOption csmOption, boolean z) {
        return getBoolean(csmOption, z, true);
    }

    public int getInteger(CsmOption csmOption, int i) {
        return getInteger(csmOption, i, true);
    }
}
