package com.reactcommunity.rndatetimepicker;

import android.os.Bundle;
import java.util.Calendar;
/* loaded from: classes3.dex */
public class RNDate {
    private Calendar now = Calendar.getInstance();

    public RNDate(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("value")) {
            return;
        }
        set(bundle.getLong("value"));
    }

    public int day() {
        return this.now.get(5);
    }

    public int hour() {
        return this.now.get(11);
    }

    public int minute() {
        return this.now.get(12);
    }

    public int month() {
        return this.now.get(2);
    }

    public void set(long j) {
        this.now.setTimeInMillis(j);
    }

    public int year() {
        return this.now.get(1);
    }
}
