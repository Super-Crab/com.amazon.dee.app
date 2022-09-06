package com.amazon.alexa.mosaic.components;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class ThemePreferenceSubscriber implements Subscriber {
    private static final String DEFAULT_THEME_OPTION = "system theme";
    private static final String TAG = "ThemePreferenceSubscriber";
    private static final String THEME_EVENT_PAYLOAD_KEY = "selectedTheme";
    private static final String THEME_PREFERENCE_ACTION = "ui:theme:preference";
    public static final String THEME_PREFERENCE_STORAGE_KEY = "userThemePreference";
    private static ThemePreferenceSubscriber instance;
    private final PersistentStorage persistentStorage;
    private final Runnable runOnUiThread;

    private ThemePreferenceSubscriber(Runnable runnable, PersistentStorage persistentStorage) {
        this.runOnUiThread = runnable;
        this.persistentStorage = persistentStorage;
    }

    public static ThemePreferenceSubscriber getInstance(Runnable runnable, PersistentStorage persistentStorage) {
        if (instance == null) {
            instance = new ThemePreferenceSubscriber(runnable, persistentStorage);
        }
        return instance;
    }

    private void updateUIonMainThread() {
        new Handler(Looper.getMainLooper()).post(this.runOnUiThread);
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public UUID getUUID() {
        return UUID.randomUUID();
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        char c;
        String eventType = message.getEventType();
        int hashCode = eventType.hashCode();
        if (hashCode == -1717819618) {
            if (eventType.equals(IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != -1487781241) {
            if (hashCode == -1264961710 && eventType.equals(THEME_PREFERENCE_ACTION)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (eventType.equals(IdentityEvent.IDENTITY_SIGN_IN_SUCCESS)) {
                c = 2;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                ThemeUtil.resetAppTheme();
                return;
            } else if (c != 2) {
                Log.e(TAG, "Invalid error message");
                return;
            } else {
                updateUIonMainThread();
                return;
            }
        }
        try {
            String optString = new JSONObject(message.getPayloadAsString()).optString(THEME_EVENT_PAYLOAD_KEY);
            if (!optString.equals(DEFAULT_THEME_OPTION)) {
                this.persistentStorage.edit().set(THEME_PREFERENCE_STORAGE_KEY, optString).commit();
            } else {
                this.persistentStorage.edit().remove(THEME_PREFERENCE_STORAGE_KEY).commit();
            }
            updateUIonMainThread();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        String eventType = message.getEventType();
        if (eventType != null) {
            char c = 65535;
            int hashCode = eventType.hashCode();
            if (hashCode != -1717819618) {
                if (hashCode != -1487781241) {
                    if (hashCode == -1264961710 && eventType.equals(THEME_PREFERENCE_ACTION)) {
                        c = 0;
                    }
                } else if (eventType.equals(IdentityEvent.IDENTITY_SIGN_IN_SUCCESS)) {
                    c = 2;
                }
            } else if (eventType.equals(IdentityEvent.IDENTITY_SIGN_OUT_SUCCESS)) {
                c = 1;
            }
            return c == 0 || c == 1 || c == 2;
        }
        return false;
    }
}
