package com.amazon.alexa.mode.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mode.R;
import com.amazon.alexa.mode.model.Headunit;
import com.amazon.alexa.mode.util.LogTag;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class AutoBluetoothDetector {
    private static final String TAG = LogTag.forClass(AutoBluetoothDetector.class);
    @VisibleForTesting
    FeatureServiceV2.FeatureUpdateListener mBluetoothFriendlyNameDisabledListener;
    @NonNull
    private final Pattern mCarBluetoothPattern = getCarBluetoothPattern();
    private boolean mStandAloneModeBluetoothFriendlyNameDisabled;
    @NonNull
    private final List<Headunit> mSupportedHeadunitList;

    public AutoBluetoothDetector(Context context) {
        this.mSupportedHeadunitList = parseHeadunitTypesJson(getHeadunitJsonFromJsonResource(context));
    }

    private FeatureServiceV2.FeatureUpdateListener createBluetoothFriendlyNameDisabledUpdateListener() {
        return new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.mode.bluetooth.AutoBluetoothDetector.1
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(String str) {
                AutoBluetoothDetector.this.updateWeblabState();
                String str2 = AutoBluetoothDetector.TAG;
                StringBuilder outline115 = GeneratedOutlineSupport1.outline115("onFeatureUpdate: Weblab ", str, "is ");
                outline115.append(AutoBluetoothDetector.this.mStandAloneModeBluetoothFriendlyNameDisabled);
                Log.i(str2, outline115.toString());
            }
        };
    }

    private Pattern getCarBluetoothPattern() {
        List<Headunit> list = this.mSupportedHeadunitList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Headunit headunit : this.mSupportedHeadunitList) {
            sb.append(headunit.getRegularExpression());
            sb.append(AccessoryMetricsConstants.DELIMITER);
        }
        sb.deleteCharAt(sb.length() - 1);
        return Pattern.compile(sb.toString(), 2);
    }

    private static String getHeadunitJsonFromJsonResource(Context context) {
        String str;
        try {
        } catch (Resources.NotFoundException | IOException e) {
            GeneratedOutlineSupport1.outline156("Read AMA devices json file error: ", e, TAG);
        }
        if (context.getResources() != null) {
            InputStream openRawResource = context.getResources().openRawResource(R.raw.supported_headunits);
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            openRawResource.close();
            str = new String(bArr, StandardCharsets.UTF_8);
            GeneratedOutlineSupport1.outline163("headunit json list: | ", str, TAG);
            return str;
        }
        str = "";
        GeneratedOutlineSupport1.outline163("headunit json list: | ", str, TAG);
        return str;
    }

    @SuppressLint({"MissingPermission"})
    private Boolean isAutoClassOfDevice(@NonNull BluetoothDevice bluetoothDevice) {
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        boolean z = false;
        if (bluetoothClass != null) {
            if (bluetoothClass.getDeviceClass() == 1056 || bluetoothClass.getDeviceClass() == 1032) {
                z = true;
            }
            return Boolean.valueOf(z);
        }
        return false;
    }

    private boolean isBluetoothFriendlyNameDisabled() {
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline21(FeatureServiceV2.class);
        return featureServiceV2 != null && featureServiceV2.hasAccess("ALEXA_AUTO_ANDROID_STANDALONE_MODE_BLUETOOTH_FRIENDLY_NAME_DISABLED", false);
    }

    private static List<Headunit> parseHeadunitTypesJson(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            return (List) new Gson().fromJson(str, TypeToken.getParameterized(ArrayList.class, Headunit.class).getType());
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("parseHeadunitTypesJson | error parsing json : ", e, TAG);
            return arrayList;
        }
    }

    public void deInit() {
        if (((EventBus) GeneratedOutlineSupport1.outline21(EventBus.class)) == null) {
            Log.e(TAG, "Error uninitializing FeatureChecker | event bus is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public String getAutoBluetoothType(@Nullable BluetoothDevice bluetoothDevice) {
        String name;
        if (bluetoothDevice != null && this.mCarBluetoothPattern != null && (name = bluetoothDevice.getName()) != null && !name.isEmpty()) {
            for (Headunit headunit : this.mSupportedHeadunitList) {
                if (Pattern.compile(headunit.getRegularExpression(), 2).matcher(name).find()) {
                    return headunit.getHeadunitName();
                }
            }
        }
        return null;
    }

    public boolean getBluetoothFriendlyNameDisabled() {
        return this.mStandAloneModeBluetoothFriendlyNameDisabled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init() {
        if (((EventBus) GeneratedOutlineSupport1.outline21(EventBus.class)) == null) {
            Log.e(TAG, "Error initializing FeatureChecker | event bus is null");
            return;
        }
        updateWeblabState();
        this.mBluetoothFriendlyNameDisabledListener = createBluetoothFriendlyNameDisabledUpdateListener();
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline21(FeatureServiceV2.class);
        if (featureServiceV2 != null) {
            featureServiceV2.observeFeature("ALEXA_AUTO_ANDROID_STANDALONE_MODE_BLUETOOTH_FRIENDLY_NAME_DISABLED", this.mBluetoothFriendlyNameDisabledListener);
        } else {
            Log.e(TAG, "Failed to observe ALEXA_AUTO_ANDROID_STANDALONE_MODE_BLUETOOTH_FRIENDLY_NAME_DISABLED feature - featureServiceV2 is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"MissingPermission"})
    public boolean isSupportedAutoBluetooth(@Nullable BluetoothDevice bluetoothDevice) {
        String name;
        if (bluetoothDevice == null || this.mCarBluetoothPattern == null || (name = bluetoothDevice.getName()) == null || name.isEmpty()) {
            return false;
        }
        String str = TAG;
        Log.i(str, "isSupportedCarBluetooth | bluetooth device: " + name);
        if (getBluetoothFriendlyNameDisabled()) {
            Log.i(TAG, "Bluetooth friendly name check is disabled, checking only COD");
            return isAutoClassOfDevice(bluetoothDevice).booleanValue();
        }
        Log.i(TAG, "Bluetooth friendly name check is enabled, checking both COD and friendly name");
        return this.mCarBluetoothPattern.matcher(name).find() || isAutoClassOfDevice(bluetoothDevice).booleanValue();
    }

    void updateWeblabState() {
        this.mStandAloneModeBluetoothFriendlyNameDisabled = isBluetoothFriendlyNameDisabled();
    }
}
