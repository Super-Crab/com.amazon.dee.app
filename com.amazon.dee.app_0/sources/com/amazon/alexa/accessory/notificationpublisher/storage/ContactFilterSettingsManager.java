package com.amazon.alexa.accessory.notificationpublisher.storage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Telephony;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.exceptions.RxBlockingCallException;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.CommsNotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONHelpers;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ContactFilterSettingsManager {
    private static final String TAG = "ContactFilterSettingsManager";
    private static ContactFilterSettingsManager contactFilterSettingsManager;
    private StorageWrapper storageWrapper;

    private ContactFilterSettingsManager() {
    }

    public static ContactFilterSettingsManager getInstance() {
        if (contactFilterSettingsManager == null) {
            contactFilterSettingsManager = new ContactFilterSettingsManager();
        }
        return contactFilterSettingsManager;
    }

    private synchronized StorageWrapper getStorageWrapper() {
        if (this.storageWrapper != null) {
            return this.storageWrapper;
        }
        this.storageWrapper = new StorageWrapper();
        return this.storageWrapper;
    }

    public void addContactSettings(String str, @NonNull Object obj) throws JSONException, RxBlockingCallException, UnsupportedEncodingException {
        Log.i(TAG, "Add contact settings");
        if (SettingsStorageModule.VIP_FILTER_CONTACT_SETTINGS_KEY.equals(str)) {
            JSONArray jSONArray = (JSONArray) obj;
            synchronized (SettingsStorageModule.getInstance()) {
                JSONArray allContactFilterSettingsKeys = SettingsStorageModule.getInstance().getAllContactFilterSettingsKeys();
                if (allContactFilterSettingsKeys == null) {
                    allContactFilterSettingsKeys = new JSONArray();
                }
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    String string = jSONObject.getString(SettingsStorageModule.FILTER_SETTINGS_CONTACT_NAME_KEY);
                    String string2 = jSONObject.getString(SettingsStorageModule.FILTER_SETTINGS_APP_ID_KEY);
                    if (jSONObject.getString(SettingsStorageModule.FILTER_SETTINGS_ALIAS_KEY).isEmpty()) {
                        jSONObject.put(SettingsStorageModule.FILTER_SETTINGS_ALIAS_KEY, string);
                    }
                    jSONObject.put(SettingsStorageModule.FILTER_SETTINGS_LAST_UPDATED_KEY, System.currentTimeMillis());
                    String buildEncodedFilterSettingsKey = SettingsStorageModule.buildEncodedFilterSettingsKey(new CommsNotificationSource(string2, string).getSourceId());
                    if (!JSONHelpers.isArrayContains(allContactFilterSettingsKeys, buildEncodedFilterSettingsKey)) {
                        allContactFilterSettingsKeys.put(buildEncodedFilterSettingsKey);
                    }
                    getStorageWrapper().putLocalSync(buildEncodedFilterSettingsKey, jSONObject);
                }
                String str2 = TAG;
                Log.i(str2, "AllContactFilterSettingsKeys size: " + allContactFilterSettingsKeys.length());
                SettingsStorageModule.getInstance().putAllContactFilterSettingsKeys(allContactFilterSettingsKeys);
                String str3 = TAG;
                Log.d(str3, "putContactFilterSettings - allContactFilterKeyList after append " + allContactFilterSettingsKeys);
                getStorageWrapper().putCloud(str, new JSONObject(), null);
            }
            return;
        }
        throw new IllegalArgumentException("Wrong key is passed for addContactSettings");
    }

    public JSONObject getDefaultSmsMessagingAppInfo(Context context) throws JSONException, PackageManager.NameNotFoundException {
        JSONObject jSONObject = new JSONObject();
        String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(context);
        PackageManager packageManager = context.getPackageManager();
        jSONObject.put(SettingsStorageModule.FILTER_SETTINGS_APP_ID_KEY, defaultSmsPackage);
        jSONObject.put(SettingsStorageModule.FILTER_SETTINGS_APP_NAME_KEY, (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(defaultSmsPackage, 0)));
        return jSONObject;
    }

    @VisibleForTesting
    ContactFilterSettingsManager(StorageWrapper storageWrapper) {
        this.storageWrapper = storageWrapper;
    }
}
