package com.amazon.deecomms.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.EncryptionUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.LinkedHashMap;
import java.util.Map;
@Deprecated
/* loaded from: classes12.dex */
public class SecuredSharedPreference {
    private static final String ALIAS = "com.amazon.alexa.kdacommsapp.securedprefs";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SecuredSharedPreference.class);
    private static final String PREFS_NAME = "SECURED_SHARED_PREFS";
    private CapabilitiesManager mCapbilitiesManager;
    private CommsSharedPreferences mCommsSharedPreferences;
    private final Context mContext;
    private final EncryptionUtils mEncryptionUtils;

    @Deprecated
    public SecuredSharedPreference(@NonNull Context context) {
        this.mContext = context;
        this.mEncryptionUtils = new EncryptionUtils(this.mContext);
        this.mCapbilitiesManager = CommsDaggerWrapper.getComponent().getCapabilitiesManager();
        this.mCommsSharedPreferences = new CommsSharedPreferences(this.mContext);
    }

    @Deprecated
    public static synchronized void clearAllPrefs(@NonNull Context context) {
        synchronized (SecuredSharedPreference.class) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
            if (sharedPreferences == null) {
                LOG.e("Unable to fetch secured shared prefs, so we cannot clear it");
            } else {
                sharedPreferences.edit().clear().apply();
            }
        }
    }

    private void clearPreferencesOnInvalidKey() {
        if (!this.mEncryptionUtils.isKeyValid(ALIAS)) {
            LOG.w("Clearing all preferences due to invalid key");
            clearAllPrefs(this.mContext);
        }
    }

    private synchronized String getStringFromSecuredPreferences(@NonNull String str, String str2) {
        GeneratedOutlineSupport.outline4("Caesar: getString: key: ", str, LOG);
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(PREFS_NAME, 0);
        if (sharedPreferences == null) {
            LOG.e("Unable to fetch secured shared preference from context");
            return null;
        }
        String string = sharedPreferences.getString(str, null);
        if (string == null) {
            CommsLogger commsLogger = LOG;
            commsLogger.w("Cannot find value from secured preference for the key = " + str);
            return str2;
        }
        String decryptString = this.mEncryptionUtils.decryptString(ALIAS, string);
        if (decryptString != null) {
            return decryptString;
        }
        LOG.e("Unable to decrypt successfully, returning default value");
        clearPreferencesOnInvalidKey();
        return str2;
    }

    private void resecurePreferences() {
        LOG.i("Resetting all shared preference in secured preference");
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(PREFS_NAME, 0);
        if (sharedPreferences == null) {
            LOG.e("Unable to fetch secured shared preference from context");
            return;
        }
        Map<String, ?> all = sharedPreferences.getAll();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            linkedHashMap.put(entry.getKey(), this.mEncryptionUtils.decryptString(ALIAS, entry.getValue().toString()));
        }
        this.mEncryptionUtils.generateKey(ALIAS);
        sharedPreferences.edit().clear().apply();
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            String str = (String) entry2.getKey();
            String encryptString = this.mEncryptionUtils.encryptString(ALIAS, (String) entry2.getValue());
            if (str != null && encryptString != null) {
                sharedPreferences.edit().putString(str, encryptString).apply();
            }
        }
        LOG.i("Finished resetting all preference in secured shared preference");
        all.clear();
        linkedHashMap.clear();
    }

    @Deprecated
    public synchronized String getString(@NonNull String str, String str2) {
        if (!this.mCommsSharedPreferences.containsKey(str) && this.mContext.getSharedPreferences(PREFS_NAME, 0).contains(str)) {
            GeneratedOutlineSupport.outline4("Caesar: getting value from SecuredSharedPrefs and storing it in CommsSharedPrefs : key: ", str, LOG);
            this.mCommsSharedPreferences.putString(str, getStringFromSecuredPreferences(str, str2));
        }
        return this.mCommsSharedPreferences.getString(str, str2);
    }

    @Deprecated
    public void putNonEmptyString(@Nullable String str, @Nullable String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        putString(str, str2);
    }

    @Deprecated
    public synchronized void putString(@NonNull String str, @NonNull String str2) {
        this.mCommsSharedPreferences.putString(str, str2);
    }

    @Deprecated
    public SecuredSharedPreference(Context context, EncryptionUtils encryptionUtils, CapabilitiesManager capabilitiesManager, CommsSharedPreferences commsSharedPreferences) {
        this.mContext = context;
        this.mEncryptionUtils = encryptionUtils;
        this.mCapbilitiesManager = capabilitiesManager;
        this.mCommsSharedPreferences = commsSharedPreferences;
    }
}
