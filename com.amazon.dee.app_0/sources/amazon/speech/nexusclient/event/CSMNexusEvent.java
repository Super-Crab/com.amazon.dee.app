package amazon.speech.nexusclient.event;

import amazon.speech.nexusclient.event.NexusEventField;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class CSMNexusEvent extends BaseNexusEvent<CSMNexusEvent> {
    private static final String DEFAULT_PRODUCER = "echodevice";
    private static final String DEFAULT_SCHEMA_ID = "echodevicenexusclient.CSMNexusEvent.9";
    private String mDeviceId;
    private Map<String, String> mMetadata;
    private String mName;
    private String mNamespace;
    private String mNexusSessionId;
    private String mPreviousRequestId;
    private String mRequestId;
    private String mRequestType;
    private String mUserIntentName;
    public static final NexusEventField PREVIOUS_REQUEST_ID_KEY = new NexusEventField.StringField("previousRequestId", true);
    public static final NexusEventField REQUEST_ID_KEY = new NexusEventField.StringField("requestId", false);
    public static final NexusEventField REQUEST_TYPE_KEY = new NexusEventField.StringField(NotificationConstants.REQUEST_TYPE, false);
    public static final NexusEventField REQUEST_TIME_KEY = new NexusEventField.LongField("requestTime", false);
    public static final NexusEventField REQUEST_META_DATA_KEY = new NexusEventField.MapField("requestMetadata", true);
    public static final NexusEventField NAME_KEY = new NexusEventField.StringField("name", true);
    public static final NexusEventField NAMESPACE_KEY = new NexusEventField.StringField("namespace", true);
    public static final NexusEventField NEXUS_SESSION_ID_KEY = new NexusEventField.StringField("nexusSessionId", false);
    public static final NexusEventField USER_INTENT_NAME_KEY = new NexusEventField.StringField("userIntentName", true);
    public static final NexusEventField DEVICE_ID_KEY = new NexusEventField.StringField("device_id", true);

    public CSMNexusEvent() {
        super(DEFAULT_SCHEMA_ID, "echodevice");
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public Map<String, String> getMetadata() {
        return this.mMetadata;
    }

    public String getName() {
        return this.mName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public final String getNexusSessionId() {
        return this.mNexusSessionId;
    }

    public String getPreviousRequestId() {
        return this.mPreviousRequestId;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public long getRequestTime() {
        return getTimestamp();
    }

    public String getRequestType() {
        return this.mRequestType;
    }

    public String getUserIntentName() {
        return this.mUserIntentName;
    }

    public CSMNexusEvent setDeviceId(String str) {
        BaseNexusEvent.ensureString(str, "nexus device id must not be null");
        this.mDeviceId = str;
        return this;
    }

    public CSMNexusEvent setMetadata(Map<String, String> map) {
        this.mMetadata = map;
        return this;
    }

    public CSMNexusEvent setName(String str) {
        this.mName = str;
        return this;
    }

    public CSMNexusEvent setNamespace(String str) {
        this.mNamespace = str;
        return this;
    }

    public CSMNexusEvent setNexusSessionId(String str) {
        BaseNexusEvent.ensureString(str, "nexus session id must not be null");
        this.mNexusSessionId = str;
        return this;
    }

    public CSMNexusEvent setPreviousRequestId(String str) {
        this.mPreviousRequestId = str;
        return this;
    }

    public CSMNexusEvent setRequestId(String str) {
        BaseNexusEvent.ensureString(str, "request id cannot be empty");
        this.mRequestId = str;
        return this;
    }

    public CSMNexusEvent setRequestTime(long j) {
        return setTimestamp(j);
    }

    public CSMNexusEvent setRequestType(String str) {
        BaseNexusEvent.ensureString(str, "Request type must not be null");
        this.mRequestType = str;
        return this;
    }

    public CSMNexusEvent setUserIntentName(String str) {
        this.mUserIntentName = str;
        return this;
    }

    @Override // amazon.speech.nexusclient.event.BaseNexusEvent, amazon.speech.nexusclient.event.INexusEvent
    public JSONObject toJSON() throws JSONException {
        JSONObject json = super.toJSON();
        BaseNexusEvent.put(json, REQUEST_META_DATA_KEY, this.mMetadata);
        BaseNexusEvent.put(json, PREVIOUS_REQUEST_ID_KEY, this.mPreviousRequestId);
        BaseNexusEvent.put(json, REQUEST_ID_KEY, this.mRequestId);
        BaseNexusEvent.put(json, REQUEST_TYPE_KEY, this.mRequestType);
        BaseNexusEvent.put(json, REQUEST_TIME_KEY, getRequestTime());
        BaseNexusEvent.put(json, NAME_KEY, this.mName);
        BaseNexusEvent.put(json, NAMESPACE_KEY, this.mNamespace);
        BaseNexusEvent.put(json, NEXUS_SESSION_ID_KEY, this.mNexusSessionId);
        BaseNexusEvent.put(json, USER_INTENT_NAME_KEY, this.mUserIntentName);
        BaseNexusEvent.put(json, DEVICE_ID_KEY, this.mDeviceId);
        return json;
    }
}
