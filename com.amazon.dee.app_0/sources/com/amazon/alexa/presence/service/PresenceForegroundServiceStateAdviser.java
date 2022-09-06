package com.amazon.alexa.presence.service;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes9.dex */
public class PresenceForegroundServiceStateAdviser {
    private static final String TAG = "com.amazon.alexa.presence.service.PresenceForegroundServiceStateAdviser";
    private boolean bleV2OverrideOn;
    private final PresenceServiceConfiguration config;
    private final Context ctx;

    public PresenceForegroundServiceStateAdviser(Context context) {
        this(context, PresenceServiceConfiguration.defaultConfiguration());
    }

    private boolean isBluetoothReady(Intent intent) {
        String action = intent == null ? "" : intent.getAction();
        if (!TextUtils.isEmpty(action) && action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            GeneratedOutlineSupport1.outline149("Ble state changed to: ", intExtra);
            return intExtra == 12;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return this.ctx.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le") && defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public boolean getBleV2Override() {
        return this.bleV2OverrideOn;
    }

    public boolean isBleConnEnabled(PresenceServiceConfiguration presenceServiceConfiguration) {
        if (!presenceServiceConfiguration.isForegroundServiceEnabled()) {
            return false;
        }
        String foregroundServiceFeature = presenceServiceConfiguration.foregroundServiceFeature();
        if (TextUtils.isEmpty(foregroundServiceFeature)) {
            return true;
        }
        return isFeatureEnabled(foregroundServiceFeature);
    }

    public boolean isBleConnV1Enabled() {
        boolean z = !isFeatureEnabled(PresenceServiceConfiguration.FOREGROUND_SERVICE_ENABLED_DEE_FEATURE_2);
        GeneratedOutlineSupport1.outline172("PhoneId v1 enabled? ", z);
        return z;
    }

    public boolean isBleConnV2Enabled() {
        boolean z = getBleV2Override() || isFeatureEnabled(PresenceServiceConfiguration.FOREGROUND_SERVICE_ENABLED_DEE_FEATURE) || isFeatureEnabled(PresenceServiceConfiguration.FOREGROUND_SERVICE_ENABLED_DEE_FEATURE_2);
        GeneratedOutlineSupport1.outline172("PhoneId v2 enabled? ", z);
        return z;
    }

    public boolean isFeatureEnabled(String str) {
        FeatureQuery featureQuery = (FeatureQuery) GeneratedOutlineSupport1.outline21(FeatureQuery.class);
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Feature detected: ", str, ". Active? ");
        outline115.append(featureQuery != null && featureQuery.isActive(str));
        outline115.toString();
        return featureQuery != null && featureQuery.isActive(str);
    }

    public boolean serviceShouldBeRunning() {
        return serviceShouldBeRunning(null);
    }

    public void setBleV2Override(boolean z) {
        this.bleV2OverrideOn = z;
    }

    public PresenceForegroundServiceStateAdviser(Context context, PresenceServiceConfiguration presenceServiceConfiguration) {
        this.ctx = context;
        this.config = presenceServiceConfiguration;
    }

    public boolean serviceShouldBeRunning(Intent intent) {
        boolean isBleConnEnabled = isBleConnEnabled(this.config);
        boolean presenceRequested = PersistentLocalStorage.presenceRequested(this.ctx);
        boolean isBluetoothReady = isBluetoothReady(intent);
        String str = "Checking if feature enabled: " + isBleConnEnabled;
        String str2 = "Checking if setting enabled: " + presenceRequested;
        String str3 = "Checking if bluetooth hardware ready: " + isBluetoothReady;
        return isBleConnEnabled && presenceRequested && isBluetoothReady;
    }
}
