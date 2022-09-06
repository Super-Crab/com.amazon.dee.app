package com.amazon.CoralAndroidClient.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
/* loaded from: classes.dex */
public class TimestampValue implements Value {
    private Date mValue;

    public TimestampValue(Date date) {
        this.mValue = date == null ? new Date() : date;
    }

    public Date getValue() {
        return this.mValue;
    }

    public void setValue(Date date) {
        if (date == null) {
            date = new Date();
        }
        this.mValue = date;
    }

    public String toISO8601() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).format(this.mValue);
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() throws JSONException {
        return Long.valueOf(this.mValue.getTime() / 1000);
    }

    public TimestampValue() {
        this((Date) null);
    }

    public TimestampValue(long j) {
        this(new Date(j * 1000));
    }
}
