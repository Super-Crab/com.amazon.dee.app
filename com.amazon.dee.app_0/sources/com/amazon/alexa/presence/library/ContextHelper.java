package com.amazon.alexa.presence.library;

import android.annotation.TargetApi;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import java.util.HashSet;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class ContextHelper {
    public static final String ROAMING_GUEST_DOMAIN = "roamingGuest";
    private final PersistentLocalStorage.PresenceDataStore dataStore;
    private final Context mContext;

    public ContextHelper(Context context) {
        this(context, PersistentLocalStorage.getWrapper());
    }

    @TargetApi(23)
    private boolean hasFineLocationAccessAndroidM() {
        return this.mContext.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    @TargetApi(23)
    private void setScanSettingAndroidM(ScanSettings.Builder builder) {
        builder.setScanMode(1).setCallbackType(1).setMatchMode(1).setNumOfMatches(1);
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean hasLocationPermissions() {
        return hasFineLocationAccessAndroidM();
    }

    public boolean hasPermissions() {
        return !isAndroidMOrLater() || hasLocationPermissions();
    }

    public boolean isAndroidMOrLater() {
        return Compatibility.isAndroidMOrLater();
    }

    public boolean isAndroidOreoOrLater() {
        return Compatibility.isAndroidOOrLater();
    }

    public boolean isAnyDomainRequestingV1Presence() {
        HashSet hashSet = new HashSet(this.dataStore.getDomains(this.mContext));
        hashSet.remove(ROAMING_GUEST_DOMAIN);
        return hashSet.size() > 0;
    }

    public void setScanSettings(ScanSettings.Builder builder) {
        if (isAndroidMOrLater()) {
            setScanSettingAndroidM(builder);
        }
    }

    @Inject
    public ContextHelper(Context context, PersistentLocalStorage.PresenceDataStore presenceDataStore) {
        this.mContext = context;
        this.dataStore = presenceDataStore;
    }
}
