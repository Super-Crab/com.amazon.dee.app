package com.amazon.identity.auth.device;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gf {
    private static final String TAG = "com.amazon.identity.auth.device.gf";
    private final AtomicBoolean nN = new AtomicBoolean(false);
    private final List<ke> nO;
    private String nP;
    private JSONObject nQ;

    public gf(List<ke> list) {
        if (list != null) {
            this.nO = list;
        } else {
            this.nO = new ArrayList();
        }
        this.nQ = new JSONObject();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002e A[Catch: IllegalArgumentException -> 0x0036, TRY_LEAVE, TryCatch #0 {IllegalArgumentException -> 0x0036, blocks: (B:3:0x0001, B:5:0x000a, B:13:0x002e, B:7:0x0013, B:9:0x0020, B:10:0x0028), top: B:18:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String ci(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = ";"
            java.lang.String[] r5 = r5.split(r1)     // Catch: java.lang.IllegalArgumentException -> L36
            int r1 = r5.length     // Catch: java.lang.IllegalArgumentException -> L36
            if (r1 > 0) goto L13
            java.lang.String r5 = com.amazon.identity.auth.device.gf.TAG     // Catch: java.lang.IllegalArgumentException -> L36
            java.lang.String r1 = "Cookie does not seem to be in a valid format"
            com.amazon.identity.auth.device.io.e(r5, r1)     // Catch: java.lang.IllegalArgumentException -> L36
        L11:
            r5 = r0
            goto L2b
        L13:
            r1 = 0
            r5 = r5[r1]     // Catch: java.lang.IllegalArgumentException -> L36
            java.lang.String r1 = "="
            java.lang.String[] r5 = r5.split(r1)     // Catch: java.lang.IllegalArgumentException -> L36
            int r1 = r5.length     // Catch: java.lang.IllegalArgumentException -> L36
            r2 = 2
            if (r1 == r2) goto L28
            java.lang.String r5 = com.amazon.identity.auth.device.gf.TAG     // Catch: java.lang.IllegalArgumentException -> L36
            java.lang.String r1 = "Cookie name/value pair does not seem to be in a valid format"
            com.amazon.identity.auth.device.io.e(r5, r1)     // Catch: java.lang.IllegalArgumentException -> L36
            goto L11
        L28:
            r1 = 1
            r5 = r5[r1]     // Catch: java.lang.IllegalArgumentException -> L36
        L2b:
            if (r5 == 0) goto L2e
            return r5
        L2e:
            java.lang.String r5 = com.amazon.identity.auth.device.gf.TAG     // Catch: java.lang.IllegalArgumentException -> L36
            java.lang.String r1 = "Found no x-main cookie in RegisterDeviceResponse"
            com.amazon.identity.auth.device.io.e(r5, r1)     // Catch: java.lang.IllegalArgumentException -> L36
            goto L4e
        L36:
            r5 = move-exception
            java.lang.String r1 = com.amazon.identity.auth.device.gf.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Caught exception parsing the cookie value out of RegisterDeviceResponse"
            r2.<init>(r3)
            java.lang.String r5 = r5.getMessage()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            com.amazon.identity.auth.device.io.e(r1, r5)
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.gf.ci(java.lang.String):java.lang.String");
    }

    private boolean cj(String str) {
        return str.startsWith("x-main") || str.startsWith("x-acb");
    }

    private void fd() {
        if (this.nN.getAndSet(true)) {
            return;
        }
        for (ke keVar : this.nO) {
            try {
                String value = keVar.getValue();
                if (value != null) {
                    if (value.startsWith("x-main")) {
                        this.nP = ci(value);
                    }
                    if (cj(value)) {
                        JSONObject jSONObject = this.nQ;
                        jSONObject.put("https://www" + keVar.getUrl(), keVar.getValue());
                    }
                }
            } catch (JSONException e) {
                io.e(TAG, "Adding JSON value failed", e);
            }
        }
    }

    public String fb() {
        fd();
        return this.nP;
    }

    public String fc() {
        fd();
        return this.nQ.toString();
    }
}
