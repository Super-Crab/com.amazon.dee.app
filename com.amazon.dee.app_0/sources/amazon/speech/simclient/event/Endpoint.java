package amazon.speech.simclient.event;

import java.io.Serializable;
import java.util.Map;
/* loaded from: classes.dex */
public class Endpoint implements Serializable {
    private final Map<String, String> mCookie;
    private final String mEndpointId;

    public Endpoint() {
        this.mEndpointId = null;
        this.mCookie = null;
    }

    public Map<String, String> getCookie() {
        return this.mCookie;
    }

    public String getEndpointId() {
        return this.mEndpointId;
    }

    public Endpoint(String str, Map<String, String> map) {
        this.mEndpointId = str;
        this.mCookie = map;
    }
}
