package amazon.speech.nexusclient.event;

import amazon.speech.nexusclient.TimeUtil;
import amazon.speech.nexusclient.event.BaseNexusEvent;
import amazon.speech.nexusclient.event.NexusEventField;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class BaseNexusEvent<T extends BaseNexusEvent> implements INexusEvent {
    private String mISOTimeStamp;
    private String mMessageId;
    private String mProducerId;
    private String mSchemaId;
    private long mTimestamp;
    private static final String TAG = GeneratedOutlineSupport1.outline39(BaseNexusEvent.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    public static final NexusEventField SCHEMA_ID_KEY = new NexusEventField.StringField("schemaId", false);
    public static final NexusEventField ISO_TIMESTAMP_KEY = new NexusEventField.StringField("timestamp", false);
    public static final NexusEventField PRODUCER_ID_KEY = new NexusEventField.StringField("producerId", false);
    public static final NexusEventField MESSAGE_ID_KEY = new NexusEventField.StringField("messageId", false);

    public BaseNexusEvent(String str, String str2) {
        ensureString(str, "schema id must not be null");
        ensureString(str2, "producer id must not be null");
        this.mSchemaId = str;
        this.mProducerId = str2;
        this.mMessageId = UUID.randomUUID().toString();
    }

    private static boolean checkPutArgs(JSONObject jSONObject, NexusEventField nexusEventField) {
        if (jSONObject == null) {
            Log.e(TAG, "null json");
            return false;
        } else if (nexusEventField != null) {
            return true;
        } else {
            Log.e(TAG, "null key");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void ensureString(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return;
        }
        throw new IllegalArgumentException(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void put(JSONObject jSONObject, NexusEventField nexusEventField, String str) throws JSONException {
        if (!checkPutArgs(jSONObject, nexusEventField)) {
            return;
        }
        if (str != null) {
            nexusEventField.addToJson(jSONObject, str);
        } else {
            nexusEventField.addNullToJson(jSONObject);
        }
    }

    public String getMessageId() {
        return this.mMessageId;
    }

    public String getProducerId() {
        return this.mProducerId;
    }

    public String getSchemaId() {
        return this.mSchemaId;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public T setMessageId(String str) {
        ensureString(str, "message id must not be null");
        this.mMessageId = str;
        return this;
    }

    public T setProducerId(String str) {
        ensureString(str, "producer id must not be null");
        this.mProducerId = str;
        return this;
    }

    public T setSchemaId(String str) {
        ensureString(str, "schema id must not be null");
        this.mSchemaId = str;
        return this;
    }

    public T setTimestamp(long j) {
        if (j > 0) {
            this.mTimestamp = j;
            this.mISOTimeStamp = TimeUtil.getISOTimestamp(j);
            return this;
        }
        throw new IllegalArgumentException("time must be positive");
    }

    @Override // amazon.speech.nexusclient.event.INexusEvent
    public JSONObject toJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        put(jSONObject, SCHEMA_ID_KEY, this.mSchemaId);
        put(jSONObject, ISO_TIMESTAMP_KEY, this.mISOTimeStamp);
        put(jSONObject, PRODUCER_ID_KEY, this.mProducerId);
        put(jSONObject, MESSAGE_ID_KEY, this.mMessageId);
        return jSONObject;
    }

    public String toString() {
        try {
            return toJSON().toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void put(JSONObject jSONObject, NexusEventField nexusEventField, Map<String, String> map) throws JSONException {
        if (!checkPutArgs(jSONObject, nexusEventField)) {
            return;
        }
        if (map != null) {
            nexusEventField.addToJson(jSONObject, map);
        } else {
            nexusEventField.addNullToJson(jSONObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void put(JSONObject jSONObject, NexusEventField nexusEventField, long j) throws JSONException {
        if (!checkPutArgs(jSONObject, nexusEventField)) {
            return;
        }
        nexusEventField.addToJson(jSONObject, j);
    }
}
