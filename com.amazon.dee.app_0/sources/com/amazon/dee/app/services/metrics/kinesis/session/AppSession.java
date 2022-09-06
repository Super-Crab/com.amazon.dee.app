package com.amazon.dee.app.services.metrics.kinesis.session;

import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.kinesis.context.KinesisContext;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONBuilder;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.StringUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class AppSession implements JSONSerializable {
    private static final String JSON_SESSION_ID_ATTR = "session_id";
    private static final String JSON_START_TIME_ATTR = "start_time";
    private static final String JSON_STOP_TIME_ATTR = "stop_time";
    protected static final int SESSION_ID_APPKEY_LENGTH = 8;
    protected static final String SESSION_ID_DATE_FORMAT = "yyyyMMdd";
    protected static final char SESSION_ID_DELIMITER = '-';
    protected static final char SESSION_ID_PAD_CHAR = '_';
    protected static final String SESSION_ID_TIME_FORMAT = "HHmmssSSS";
    protected static final int SESSION_ID_UNIQID_LENGTH = 8;
    private static final String TAG = Log.tag(AppSession.class);
    private final String sessionID;
    private final DateFormat sessionIdTimeFormat = new SimpleDateFormat("yyyyMMdd-HHmmssSSS", Locale.US);
    private final Long startTime;
    private Long stopTime;

    protected AppSession(KinesisContext kinesisContext) {
        this.stopTime = null;
        this.sessionIdTimeFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        this.startTime = Long.valueOf(System.currentTimeMillis());
        this.stopTime = null;
        this.sessionID = generateSessionID(kinesisContext);
    }

    public static AppSession getSessionFromSerializedSession(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new AppSession(jSONObject.getString(JSON_SESSION_ID_ATTR), jSONObject.getString(JSON_START_TIME_ATTR), jSONObject.getString(JSON_STOP_TIME_ATTR));
        } catch (JSONException e) {
            Log.e(TAG, e, "Unable to create session", new Object[0]);
            return null;
        }
    }

    public static AppSession newInstance(KinesisContext kinesisContext) {
        return new AppSession(kinesisContext);
    }

    public String generateSessionID(KinesisContext kinesisContext) {
        String value = kinesisContext.getUniqueId().getValue();
        String format = this.sessionIdTimeFormat.format(this.startTime);
        return StringUtil.trimOrPadString(value, 8, SESSION_ID_PAD_CHAR) + SESSION_ID_DELIMITER + format;
    }

    public Long getSessionDuration() {
        Long l = this.stopTime;
        if (l == null) {
            l = Long.valueOf(System.currentTimeMillis());
        }
        if (l.longValue() < this.startTime.longValue()) {
            return 0L;
        }
        long j = -1L;
        try {
            return Long.valueOf(l.longValue() - this.startTime.longValue());
        } catch (NumberFormatException unused) {
            Log.e(TAG, "Exception while calculating the session duration");
            return j;
        }
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public Long getStopTime() {
        return this.stopTime;
    }

    public boolean isPaused() {
        return this.stopTime != null;
    }

    public void pause() {
        if (!isPaused()) {
            this.stopTime = Long.valueOf(System.currentTimeMillis());
        }
    }

    public void resume() {
        this.stopTime = null;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.JSONSerializable
    public JSONObject toJSONObject() {
        long j = this.stopTime;
        if (j == null) {
            j = Long.MIN_VALUE;
        }
        JSONBuilder jSONBuilder = new JSONBuilder(this);
        jSONBuilder.withAttribute(JSON_SESSION_ID_ATTR, this.sessionID);
        jSONBuilder.withAttribute(JSON_START_TIME_ATTR, this.startTime);
        jSONBuilder.withAttribute(JSON_STOP_TIME_ATTR, j);
        return jSONBuilder.toJSONObject();
    }

    public String toString() {
        JSONObject jSONObject = toJSONObject();
        try {
            return jSONObject.toString(4);
        } catch (JSONException unused) {
            return jSONObject.toString();
        }
    }

    protected AppSession(String str, String str2, String str3) {
        this.stopTime = null;
        this.sessionIdTimeFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        this.startTime = Long.valueOf(new Scanner(str2).nextLong());
        this.stopTime = Long.valueOf(new Scanner(str3).nextLong());
        this.sessionID = str;
        if (this.stopTime.longValue() == Long.MIN_VALUE) {
            this.stopTime = null;
        }
    }
}
