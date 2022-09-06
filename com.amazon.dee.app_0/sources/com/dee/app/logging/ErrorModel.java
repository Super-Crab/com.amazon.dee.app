package com.dee.app.logging;

import com.dee.app.data.reactnative.ReactNativeJson;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class ErrorModel {
    private final Map<String, String> data;
    private final ErrorCode errorCode;
    private final int httpStatusCode;
    private final String message;
    private final Class origin;
    private final Throwable throwable;

    public ErrorModel(Class cls, ErrorCode errorCode, Object... objArr) {
        this(cls, -1, null, errorCode, null, objArr);
    }

    private String resolveMessage(ErrorCode errorCode, Object[] objArr) {
        String format = String.format(errorCode.getDefaultMessage(), objArr);
        if (format.contains("${")) {
            Throwable th = this.throwable;
            if (th != null) {
                format = format.replace("${exception}", th.getMessage() != null ? this.throwable.getMessage() : "");
            }
            return format.replace("${httpStatus}", String.valueOf(this.httpStatusCode));
        }
        return format;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public String getEndUserMessage() {
        return this.message;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    public Class getOrigin() {
        return this.origin;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public boolean hasData() {
        return !this.data.isEmpty();
    }

    public WritableMap toWritableMap() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("origin", this.origin.getCanonicalName());
        createMap.putString("message", this.message);
        createMap.putString("throwable", ErrorHandler.getStackTrace(this.throwable));
        createMap.putString("errorCode", this.errorCode.toString());
        createMap.putInt("httpStatusCode", this.httpStatusCode);
        Map<String, String> map = this.data;
        if (map != null) {
            if (ReactNativeJson.isArray(map)) {
                createMap.putArray("data", ReactNativeJson.convertJsonToArray(this.data));
            } else {
                createMap.putMap("data", ReactNativeJson.convertJsonToMap(this.data));
            }
        }
        return createMap;
    }

    public ErrorModel(Class cls, Throwable th, ErrorCode errorCode, Object... objArr) {
        this(cls, -1, th, errorCode, null, objArr);
    }

    public ErrorModel(Class cls, int i, Throwable th, ErrorCode errorCode, Object... objArr) {
        this(cls, i, th, errorCode, null, objArr);
    }

    public ErrorModel(Class cls, int i, Throwable th, ErrorCode errorCode, Map<String, String> map, Object... objArr) {
        this.origin = cls;
        this.throwable = th;
        this.errorCode = errorCode;
        this.message = resolveMessage(errorCode, objArr);
        this.httpStatusCode = i;
        this.data = map;
    }
}
