package chip.devicecontroller;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class PreferencesKeyValueStoreManager implements KeyValueStoreManager {
    private SharedPreferences preferences;
    private final String TAG = KeyValueStoreManager.class.getSimpleName();
    private final String PREFERENCE_FILE_KEY = "com.google.chip.KeyValueStore";

    public PreferencesKeyValueStoreManager(Context context) {
        this.preferences = context.getSharedPreferences("com.google.chip.KeyValueStore", 0);
    }

    @Override // chip.devicecontroller.KeyValueStoreManager
    public void delete(String str) {
        GeneratedOutlineSupport1.outline158("[delete] Key: ", str);
        this.preferences.edit().remove(str).apply();
    }

    @Override // chip.devicecontroller.KeyValueStoreManager
    public String get(String str) {
        String string = this.preferences.getString(str, null);
        if (string == null) {
            String str2 = "Key '" + str + "' not found in shared preferences";
        }
        String str3 = "[get] Key: " + str + " Value: " + string;
        return string;
    }

    @Override // chip.devicecontroller.KeyValueStoreManager
    public void set(String str, String str2) {
        String str3 = "[set] Key: " + str + " Value: " + str2;
        this.preferences.edit().putString(str, str2).apply();
    }
}
