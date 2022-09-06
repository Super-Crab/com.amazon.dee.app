package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.jl;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class iy {
    public static final String rA = "AmazonWebView/MAPClientLib/" + hv.gs().qV + "/Android/" + Build.VERSION.RELEASE + "/" + Build.MODEL;
    private final AuthEndpointErrorParser aU = new AuthEndpointErrorParser();

    private HttpURLConnection a(Context context, URL url, String str, String str2, List<MAPCookie> list, String str3, ej ejVar) throws IOException {
        HttpURLConnection a = a(context, url, list, ejVar);
        a.addRequestProperty("Content-Type", str);
        a.addRequestProperty("x-amzn-identity-auth-domain", hr.n(ed.M(context), str3));
        io.i("RequestHelper", "Starting request to endpoint ".concat(String.valueOf(url)));
        io.a("Request body: %s", str2);
        OutputStream outputStream = a.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        try {
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            return a;
        } finally {
            jd.a(outputStream);
            jd.a(outputStreamWriter);
        }
    }

    public HttpURLConnection a(Context context, URL url, jl.b bVar, List<MAPCookie> list, String str, ej ejVar) throws IOException {
        return a(context, url, "application/x-www-form-urlencoded", bVar.gU(), list, str, ejVar);
    }

    public HttpURLConnection a(Context context, URL url, JSONObject jSONObject, List<MAPCookie> list, String str, String str2, ej ejVar) throws IOException {
        return a(context, url, "application/json", jSONObject.toString(), list, str, ejVar);
    }

    public HttpURLConnection a(Context context, URL url, List<MAPCookie> list, ej ejVar) throws IOException {
        HttpURLConnection a = cy.a(url, new dm(context), ejVar, context);
        a.setDoOutput(true);
        if (list != null && list.size() > 0) {
            for (MAPCookie mAPCookie : list) {
                a.addRequestProperty("Cookie", String.format("%s=%s", mAPCookie.getName(), mAPCookie.getValue()));
            }
        }
        a.setRequestMethod("POST");
        a.setRequestProperty("User-Agent", rA);
        return a;
    }

    public boolean a(Integer num) {
        return AuthEndpointErrorParser.a(num);
    }
}
