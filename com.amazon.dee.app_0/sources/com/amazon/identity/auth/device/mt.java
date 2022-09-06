package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.mv;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class mt implements mu {
    private final String mTag;

    public mt(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTag = mt.class.getName();
        } else {
            this.mTag = str;
        }
    }

    @Override // com.amazon.identity.auth.device.mu
    public void a(String str, String str2, String... strArr) {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("IncrementCounter: ", str);
        for (String str3 : strArr) {
            outline112.append(",");
            outline112.append(str3);
            outline112.append("=1");
        }
        eU(outline112.toString());
    }

    @Override // com.amazon.identity.auth.device.mu
    public void b(String str, String... strArr) {
        Log.i(this.mTag, str);
        if (strArr != null) {
            for (String str2 : strArr) {
                Log.i(this.mTag, str + ":" + str2);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.mu
    public void bA(String str) {
        Log.i(this.mTag, "Increment counter : ".concat(String.valueOf(str)));
    }

    @Override // com.amazon.identity.auth.device.mu
    public mv eO(String str) {
        return new mv.b();
    }

    protected void eU(String str) {
        Log.i(this.mTag, str);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void iJ() {
        Log.i(this.mTag, "Recording metric event object");
    }

    @Override // com.amazon.identity.auth.device.mu
    public void incrementCounter(String str, double d) {
        String str2 = this.mTag;
        Log.i(str2, "Increment counter : " + str + ", by : " + d);
    }

    @Override // com.amazon.identity.auth.device.mu
    public void a(String str, String str2, long j) {
        eU(String.format("RecordTiming: %s:%s=%d", str, str2, Long.valueOf(j)));
    }
}
