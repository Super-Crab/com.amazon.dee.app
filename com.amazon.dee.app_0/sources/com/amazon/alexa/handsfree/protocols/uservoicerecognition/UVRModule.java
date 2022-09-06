package com.amazon.alexa.handsfree.protocols.uservoicerecognition;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.callback.SettingsCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.exception.UVRUnsupportedException;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.Objects;
/* loaded from: classes8.dex */
public enum UVRModule {
    INSTANCE;
    
    private static final int DEFAULT_FLAG = 0;
    private static final String TAG = UVRModule.class.getSimpleName();
    private SettingsCallback mSettingsCallback;
    private UVRContract mUVRContract;
    private String mVoiceAppPackageName;

    @NonNull
    public SettingsCallback getSettingsCallback() {
        SettingsCallback settingsCallback = this.mSettingsCallback;
        if (settingsCallback != null) {
            return settingsCallback;
        }
        throw new UVRUnsupportedException();
    }

    @NonNull
    public UVRContract getUVRContract() {
        UVRContract uVRContract = this.mUVRContract;
        if (uVRContract != null) {
            return uVRContract;
        }
        throw new UVRUnsupportedException();
    }

    @NonNull
    public String getVoiceAppPackageName() {
        String str = this.mVoiceAppPackageName;
        if (str != null) {
            return str;
        }
        throw new UVRUnsupportedException();
    }

    @NonNull
    public String getVoiceApplicationName(@NonNull Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(INSTANCE.getVoiceAppPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "onViewCreated: Voice App Package not found", e, new Object[0]);
            return context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();
        }
    }

    public synchronized void initialize(@NonNull UVRContract uVRContract, @NonNull SettingsCallback settingsCallback, @NonNull String str) {
        this.mUVRContract = (UVRContract) Objects.requireNonNull(uVRContract, "UVR Contract cannot be null");
        this.mSettingsCallback = (SettingsCallback) Objects.requireNonNull(settingsCallback, "Settings callback cannot be null");
        this.mVoiceAppPackageName = (String) Objects.requireNonNull(str, "Voice App package name cannot be null");
        Log.i(TAG, "UVR initialized successfully");
    }

    public boolean isUVRAvailable() {
        UVRContract uVRContract = this.mUVRContract;
        return uVRContract != null && uVRContract.getVendorSettings().isUVRAvailable();
    }

    @NonNull
    public void setUVRContract(UVRContract uVRContract) {
        Log.i(TAG, "setting UVRModule uvrcontract");
        this.mUVRContract = uVRContract;
    }
}
