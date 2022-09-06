package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.map.device.AccountManagerConstants;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class aw {
    private static final String TAG = "com.amazon.identity.auth.device.aw";
    private final kf dN = new kf();
    private final BackwardsCompatiableDataStorage dO;
    private final ed o;

    public aw(Context context) {
        this.o = ed.M(context);
        this.dO = new BackwardsCompatiableDataStorage(this.o);
    }

    private URL ax() {
        try {
            return new URL(new Uri.Builder().scheme("https").authority(EnvironmentUtils.cd().cm()).appendPath("FirsProxy").appendPath("getStoreCredentials").build().toString());
        } catch (MalformedURLException unused) {
            io.e(TAG, "Cannot construct store credentials request");
            return null;
        }
    }

    private gf b(HttpURLConnection httpURLConnection) throws IOException {
        IOException e;
        try {
            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    byte[] a = jd.a(inputStream);
                    mk mkVar = new mk();
                    mkVar.c(a, a.length);
                    Document iI = mkVar.iI();
                    if (iI == null) {
                        io.e(TAG, "Could not parse get Store credentials response XML");
                        jd.a((Closeable) inputStream);
                        return null;
                    }
                    Element documentElement = iI.getDocumentElement();
                    if (documentElement != null && documentElement.getTagName().equals("response")) {
                        gf gfVar = new gf(this.dN.b(ml.a(documentElement, AccountManagerConstants.GetCookiesParams.COOKIES)));
                        jd.a((Closeable) inputStream);
                        return gfVar;
                    }
                    io.e(TAG, "Get Store Credentials request form was invalid. Could not get cookies");
                    io.a("Store Credentials response: %s", new String(a));
                    jd.a((Closeable) inputStream);
                    return null;
                } catch (IOException e2) {
                    e = e2;
                    cy.a(httpURLConnection, "Get Kindle Store Credentials Service");
                    throw e;
                }
            } catch (Throwable th) {
                th = th;
                jd.a((Closeable) null);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            jd.a((Closeable) null);
            throw th;
        }
    }

    public boolean at(String str) {
        gf au = au(str);
        if (au == null) {
            io.e(TAG, "Cannot update store credential cookies");
            return false;
        }
        fz fzVar = new fz(str, null, null);
        fzVar.v(AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN, au.fb());
        fzVar.v("com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies", au.fc());
        this.dO.a(fzVar);
        return true;
    }

    public gf au(String str) {
        try {
            URL ax = ax();
            if (ax == null) {
                return null;
            }
            HttpURLConnection openConnection = cy.openConnection(ax, new AuthenticationMethodFactory(this.o, str).newAuthenticationMethod(AuthenticationType.ADPAuthenticator));
            openConnection.setRequestMethod("GET");
            io.i(TAG, String.format("Received Response %d from Firs Proxy getStoreCredentials", Integer.valueOf(RetryLogic.d(openConnection))));
            return b(openConnection);
        } catch (IOException e) {
            io.e(TAG, "Could not get cookies because we could not reach get Store Cookies endpoint.", e);
            return null;
        }
    }
}
