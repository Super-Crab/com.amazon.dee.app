package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ft extends fr {
    private final String eO;
    private final String eV;
    private final Bundle ec;
    private final hc ng;
    private final Map<String, String> nh;
    private final Map<String, String> ni;
    private final String nj;

    ft(Context context, String str, Map<String, String> map, String str2, Map<String, String> map2, String str3, hc hcVar, Bundle bundle) {
        super(ed.M(context));
        this.eO = str;
        this.eV = str2;
        this.nj = str3;
        this.ec = bundle;
        this.nh = map;
        this.ni = map2;
        this.ng = hcVar;
    }

    public static ft a(Context context, String str, Map<String, String> map, String str2, Map<String, String> map2, String str3, Bundle bundle, hc hcVar) {
        return new ft(context, str, map, str2, map2, str3, hcVar, bundle);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected JSONObject b(ej ejVar) throws JSONException {
        return this.ng.a(this.eO, this.nh, this.eV, this.ni, this.nj, this.ec);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eF() {
        return hr.c(this.o, this.eO);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String eG() {
        return hr.n(this.o, this.eO);
    }

    @Override // com.amazon.identity.auth.device.fr
    protected AuthenticationMethod eH() {
        return null;
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getHttpVerb() {
        return "POST";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String getPath() {
        return "/auth/upgradeToken";
    }

    @Override // com.amazon.identity.auth.device.fr
    protected String j(JSONObject jSONObject) {
        return ik.a(jSONObject, "error_index", null);
    }
}
