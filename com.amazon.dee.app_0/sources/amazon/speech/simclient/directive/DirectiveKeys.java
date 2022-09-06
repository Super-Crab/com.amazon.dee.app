package amazon.speech.simclient.directive;

import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DirectiveKeys {
    public static final String KEY_CHANNEL = "channel";
    public static final String KEY_INTERACTION_LABEL = "interactionLabel";
    public static final String KEY_INTERACTION_TIMESTAMP = "interactionTimestamp";
    private static final String KEY_IS_BLOCKING = "isBlocking";
    private static final String KEY_NAVIGATION_TYPE = "navigationType";
    private static final String TAG = GeneratedOutlineSupport1.outline39(DirectiveKeys.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private final JSONObject mKeysJson;
    private final String mKeysString;

    public DirectiveKeys(String str) {
        String str2 = "{}";
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    str2 = str;
                    jSONObject = new JSONObject(str);
                }
            } catch (Exception e) {
                String str3 = TAG;
                Log.e(str3, "Failure to parse keys JSON " + str, e);
            }
        }
        this.mKeysJson = jSONObject;
        this.mKeysString = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DirectiveKeys)) {
            return false;
        }
        return this.mKeysString.equals(((DirectiveKeys) obj).mKeysString);
    }

    public boolean getBoolean(String str, boolean z) {
        JSONObject jSONObject = this.mKeysJson;
        return jSONObject != null && jSONObject.optBoolean(str, z);
    }

    public int getInt(String str, int i) {
        JSONObject jSONObject = this.mKeysJson;
        return jSONObject == null ? i : jSONObject.optInt(str, i);
    }

    public long getLong(String str, long j) {
        JSONObject jSONObject = this.mKeysJson;
        return jSONObject == null ? j : jSONObject.optLong(str, j);
    }

    public String getNavigationType() {
        return getString(KEY_NAVIGATION_TYPE, null);
    }

    public String getRawJson() {
        return this.mKeysString;
    }

    public String getString(String str, String str2) {
        JSONObject jSONObject = this.mKeysJson;
        return (jSONObject != null && !jSONObject.isNull(str)) ? this.mKeysJson.optString(str, str2) : str2;
    }

    public int hashCode() {
        return this.mKeysString.hashCode();
    }

    public boolean isBlocking() {
        return getBoolean("isBlocking", false);
    }

    public String toString() {
        return this.mKeysString;
    }
}
