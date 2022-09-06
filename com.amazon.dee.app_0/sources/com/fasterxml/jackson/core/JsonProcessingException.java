package com.fasterxml.jackson.core;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes2.dex */
public class JsonProcessingException extends JacksonException {
    private static final long serialVersionUID = 123;
    protected JsonLocation _location;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, th);
        this._location = jsonLocation;
    }

    public void clearLocation() {
        this._location = null;
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public JsonLocation getLocation() {
        return this._location;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = CapabilityQueryConstants.TARGET_NOT_AVAILABLE;
        }
        JsonLocation location = getLocation();
        String messageSuffix = getMessageSuffix();
        if (location == null && messageSuffix == null) {
            return message;
        }
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(100, message);
        if (messageSuffix != null) {
            outline105.append(messageSuffix);
        }
        if (location != null) {
            outline105.append('\n');
            outline105.append(" at ");
            outline105.append(location.toString());
        }
        return outline105.toString();
    }

    protected String getMessageSuffix() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public String getOriginalMessage() {
        return super.getMessage();
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    /* renamed from: getProcessor */
    public Object mo7003getProcessor() {
        return null;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getClass().getName() + RealTimeTextConstants.COLON_SPACE + getMessage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(String str, Throwable th) {
        this(str, null, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonProcessingException(Throwable th) {
        this(null, null, th);
    }
}
