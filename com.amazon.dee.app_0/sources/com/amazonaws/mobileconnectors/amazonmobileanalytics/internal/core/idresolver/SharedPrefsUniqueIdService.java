package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver;

import android.content.Context;
import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences;
import java.util.UUID;
@Deprecated
/* loaded from: classes13.dex */
public class SharedPrefsUniqueIdService implements UniqueIdService {
    protected static final String PREFS_NAME = "com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.SharedPrefsUniqueIdService";
    private static final String TAG = "SharedPrefsUniqueIdService";
    protected static final String UNIQUE_ID_KEY = "UniqueId";
    private String appId;
    private Context applicationContext;

    public SharedPrefsUniqueIdService(String str, Context context) {
        this.appId = null;
        this.applicationContext = null;
        this.appId = str;
        this.applicationContext = context;
    }

    private Id getIdFromPreferences(Preferences preferences) {
        Id emptyId = Id.getEmptyId();
        if (getLegacyId() != Id.getEmptyId()) {
            return getLegacyId();
        }
        String string = preferences.getString(UNIQUE_ID_KEY, null);
        return string != null ? new Id(string) : emptyId;
    }

    private Id getLegacyId() {
        Context context;
        String str = this.appId;
        if (str != null && (context = this.applicationContext) != null) {
            String string = context.getSharedPreferences(str, 0).getString(UNIQUE_ID_KEY, null);
            if (string != null) {
                return new Id(string);
            }
            return Id.getEmptyId();
        }
        return Id.getEmptyId();
    }

    private void storeUniqueId(Preferences preferences, Id id) {
        try {
            preferences.putString(UNIQUE_ID_KEY, id.getValue());
        } catch (Exception e) {
            Log.e(TAG, "There was an exception when trying to store the unique id into the Preferences", e);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.idresolver.UniqueIdService
    public Id getUniqueId(AnalyticsContext analyticsContext) {
        if (analyticsContext != null && analyticsContext.getSystem() != null && analyticsContext.getSystem().getPreferences() != null) {
            Id idFromPreferences = getIdFromPreferences(analyticsContext.getSystem().getPreferences());
            if (idFromPreferences != Id.getEmptyId()) {
                return idFromPreferences;
            }
            Id id = new Id(UUID.randomUUID().toString());
            storeUniqueId(analyticsContext.getSystem().getPreferences(), id);
            return id;
        }
        return Id.getEmptyId();
    }
}
