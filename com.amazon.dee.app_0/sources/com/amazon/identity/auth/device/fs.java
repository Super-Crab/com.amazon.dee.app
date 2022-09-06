package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fs extends fr {
    protected final String bP;
    protected final String ne;
    protected final String nf;

    public fs(Context context, String str, String str2, String str3) {
        super(ed.M(context));
        this.bP = str;
        this.ne = str2;
        this.nf = str3;
    }

    @Override // com.amazon.identity.auth.device.fr
    protected JSONObject b(ej ejVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", this.ne);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("refreshToken", jSONObject);
        if (!jg.dE(this.nf)) {
            jSONObject2.put("keyIdentifier", this.nf);
        }
        return jSONObject2;
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eF() {
        return hr.c(this.o, this.bP);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eG() {
        return hr.n(this.o, this.bP);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected AuthenticationMethod eH() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.fr
    public Map<String, String> eK() {
        return super.eK();
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getHttpVerb() {
        return "POST";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getPath() {
        return "/auth/mobile/encryptionkey";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String j(JSONObject jSONObject) {
        return ik.a(jSONObject, "error_index", null);
    }
}
