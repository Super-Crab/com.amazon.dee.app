package amazon.speech.simclient.context;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
/* loaded from: classes.dex */
public enum DeviceContextQueryResult {
    TRUE("true"),
    FALSE(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE),
    DEPRECATED("deprecated"),
    ERROR("error");
    
    private final String mName;

    DeviceContextQueryResult(String str) {
        this.mName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
