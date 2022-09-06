package com.amazon.alexa.handsfree.devices;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
/* loaded from: classes8.dex */
public class TestModeVoiceAppProvider {
    @VisibleForTesting
    static final String TEST_MODE_STATE_PREFERENCE = "TESTMODE";
    @VisibleForTesting
    static final String TEST_MODE_VOICE_PACKAGE_NAME = "TestModeVoiceAppName";
    private static TestModeVoiceAppProvider mInstance;
    private final Context mContext;
    private SharedPreferences mTestModePreferences = null;

    @VisibleForTesting
    TestModeVoiceAppProvider(Context context) {
        this.mContext = context;
    }

    public static synchronized TestModeVoiceAppProvider getInstance(Context context) {
        TestModeVoiceAppProvider testModeVoiceAppProvider;
        synchronized (TestModeVoiceAppProvider.class) {
            if (mInstance == null) {
                mInstance = new TestModeVoiceAppProvider(context);
            }
            testModeVoiceAppProvider = mInstance;
        }
        return testModeVoiceAppProvider;
    }

    @Nullable
    public synchronized String getTestModeVoiceApp() {
        Context applicationContext = this.mContext.getApplicationContext();
        if (this.mTestModePreferences == null && applicationContext != null) {
            this.mTestModePreferences = applicationContext.getSharedPreferences(TEST_MODE_STATE_PREFERENCE, 0);
        }
        if (this.mTestModePreferences != null) {
            return this.mTestModePreferences.getString(TEST_MODE_VOICE_PACKAGE_NAME, null);
        }
        return null;
    }

    public synchronized void updateTestModeVoiceApp(@NonNull String str) {
        Context applicationContext = this.mContext.getApplicationContext();
        if (this.mTestModePreferences == null && applicationContext != null) {
            this.mTestModePreferences = applicationContext.getSharedPreferences(TEST_MODE_STATE_PREFERENCE, 0);
        }
        if (this.mTestModePreferences != null) {
            this.mTestModePreferences.edit().putString(TEST_MODE_VOICE_PACKAGE_NAME, str).apply();
        }
    }
}
