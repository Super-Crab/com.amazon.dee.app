package org.codehaus.jackson;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.io.IOException;
/* loaded from: classes5.dex */
public class JsonProcessingException extends IOException {
    static final long serialVersionUID = 123;
    protected JsonLocation mLocation;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this.mLocation = jsonLocation;
    }

    public JsonLocation getLocation() {
        return this.mLocation;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = CapabilityQueryConstants.TARGET_NOT_AVAILABLE;
        }
        JsonLocation location = getLocation();
        if (location != null) {
            return message + "\n at " + location.toString();
        }
        return message;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getClass().getName() + RealTimeTextConstants.COLON_SPACE + getMessage();
    }

    protected JsonProcessingException(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, null);
    }

    protected JsonProcessingException(String str, Throwable th) {
        this(str, null, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(Throwable th) {
        this(null, null, th);
    }
}
