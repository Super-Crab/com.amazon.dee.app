package com.amazon.identity.auth.device;

import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class kr<T> extends WebResponseParser<T> {
    private static final String TAG = "com.amazon.identity.auth.device.kr";
    private final ByteArrayOutputStream ss;
    private final kz<T> st;
    private T su;

    public kr(String str, kz<T> kzVar) {
        super(str);
        this.ss = new ByteArrayOutputStream();
        this.su = null;
        this.st = kzVar;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j) {
        this.ss.write(bArr, 0, (int) j);
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public boolean b(me meVar) {
        return true;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public T hf() {
        return this.su;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void hg() {
        try {
            this.ss.close();
        } catch (IOException unused) {
            io.e(TAG, " Failed to close buffer");
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(this.ss.toByteArray(), "UTF-8"));
            io.a(" Panda JSON Response: %s", jSONObject.toString());
            this.su = this.st.y(jSONObject);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JSONException unused2) {
            b(ParseError.ParseErrorMalformedBody);
        }
    }
}
