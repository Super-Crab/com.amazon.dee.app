package com.amazon.identity.auth.device.activity;

import amazon.os.Build;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.fe;
import com.amazon.identity.auth.device.fm;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager;
import com.amazon.identity.auth.device.ia;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ja;
import com.amazon.identity.auth.device.jn;
import com.amazon.identity.auth.device.jo;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mz;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class MAPWebviewActivityTemplate extends Activity {
    protected ej bR;
    protected BackwardsCompatiableDataStorage bi;
    protected WebView el;
    protected MAPSmsReceiver en;
    protected CustomerInformationManager er;
    protected fe es;
    protected String eu;
    protected Set<String> ev;
    protected Bundle fc;
    protected String fd;
    protected RemoteCallbackWrapper fe;
    protected ed o;

    public static JSONObject ba() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bb()) {
            jSONObject.put("ui_type", "WebView");
            jSONObject.put("ui_version", "1.0");
        } else {
            mq.incrementCounterAndRecord("MAPWebviewActivityTemplate_NO_WEBVIEW", new String[0]);
            jSONObject.put("ui_type", "NoUISupported");
        }
        return jSONObject;
    }

    private static boolean bb() {
        try {
            Class.forName("android.webkit.WebView");
            return true;
        } catch (Exception unused) {
            mq.incrementCounterAndRecord(String.format("NO_WEBVIEW_%s_%s_API_%d", Build.MANUFACTURER, Build.MODEL.trim().replaceAll("\\s+", "_"), Integer.valueOf(Build.VERSION.SDK_INT)), new String[0]);
            io.w("MAPUIActivityTemplate", "Webview is not supported on this device.");
            return false;
        }
    }

    protected void a(String str, String[] strArr) {
        io.dm("MAPUIActivityTemplate");
        if (strArr != null) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            jo.aJ(this.o);
            for (String str2 : strArr) {
                "Adding cookie to CookieManager: ".concat(String.valueOf(str2));
                io.dm("MAPUIActivityTemplate");
                cookieManager.setCookie(str, str2);
            }
            jo.aJ(this.o);
            return;
        }
        io.e("MAPUIActivityTemplate", "Fail to detect account/actor auth cookies, it shouldn't happen for challenge use case.");
        n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.INTERNAL_ERROR, "No auth cookies in the option bundle, this should not happen"));
    }

    protected void aA(String str) {
        io.dm("MAPUIActivityTemplate");
        String a = fm.a(this.o, getPackageName(), this.bR, true);
        if (!TextUtils.isEmpty(a)) {
            jn.a(this.o, str, "map-md", a, "/ap", null, true);
        }
    }

    protected void aB(String str) {
        io.i("MAPUIActivityTemplate", "Clearing MAP MD cookies");
        jn.a(this.o, str, "map-md", "", "/ap", null, true);
    }

    protected abstract RemoteCallbackWrapper aO();

    protected abstract String aP();

    protected abstract String aQ();

    protected abstract String aR();

    protected abstract void aS();

    protected abstract String aT();

    protected abstract String aU();

    protected abstract String[] aV();

    protected abstract void aW();

    protected abstract String aX();

    protected void aY() {
        requestWindowFeature(1);
        ja.a(this);
        this.fc = getIntent().getExtras();
        this.fe = aO();
        this.o = ed.M(getApplicationContext());
        setContentView(ResourceHelper.B(this, aQ()));
        this.el = (WebView) findViewById(ResourceHelper.z(this, aR()));
        if (this.el == null) {
            n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.INTERNAL_ERROR, "Failed to get webview! This shouldn't happen."));
        }
        this.bR = ej.b(getIntent(), aP());
        this.fd = mz.bl(this);
        this.eu = fm.h(this.o, this.fd);
        this.ev = new HashSet();
        this.bi = new BackwardsCompatiableDataStorage(this.o);
        this.er = new CustomerInformationManager(this, 1);
        this.en = new MAPSmsReceiver(this.bR, this.el);
        this.es = new fe(this.o, this.en);
    }

    protected void aZ() {
        io.i("MAPUIActivityTemplate", "Clearing frc cookies");
        Set<String> set = this.ev;
        if (set == null || set.size() <= 0) {
            return;
        }
        io.dm("MAPUIActivityTemplate");
        for (String str : this.ev) {
            jn.a(this.o, str, "frc", "", "/ap", null, true);
        }
        this.ev.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void av(String str) {
        String dK;
        GeneratedOutlineSupport1.outline161(str, "Setting FRC cookies for url: ", "MAPUIActivityTemplate");
        if (TextUtils.isEmpty(this.eu) || (dK = jn.dK(str)) == null || this.ev.contains(dK)) {
            return;
        }
        StringBuilder sb = new StringBuilder("Setting up the frc cookie in ");
        sb.append(aP());
        sb.append(" for domain : ");
        sb.append(dK);
        io.dm("MAPUIActivityTemplate");
        jn.a(this.o, dK, "frc", this.eu, "/ap", null, true);
        this.ev.add(dK);
    }

    protected void az(String str) {
        io.i("MAPUIActivityTemplate", "Clearing User Spec cookies");
        jn.a(this.o, str, "sid", "/", ia.gz());
    }

    protected abstract void n(Bundle bundle);

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        io.i("MAPUIActivityTemplate", "onActivityResult()");
        if (i != 1) {
            return;
        }
        this.er.a(i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            aY();
            aS();
            p(bundle);
            az(aT());
            String aT = aT();
            io.i("MAPUIActivityTemplate", "Setting SID cookie");
            String z = this.bi.z(aU(), AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
            if (!TextUtils.isEmpty(z)) {
                jn.a(this.o, aT, "sid", z, "/", ia.gz(), false);
            }
            av(aT());
            a(aT(), aV());
            aW();
            aA(aT());
        } catch (IllegalArgumentException e) {
            n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.BAD_REQUEST, String.format("An IllegalArgumentException was thrown with message: %s", e.getMessage())));
        } catch (Exception e2) {
            n(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.INTERNAL_ERROR, String.format("An Exception was thrown with message: %s", e2.getMessage())));
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        io.i("MAPUIActivityTemplate", aP() + " onDestroy called");
        aZ();
        aB(aT());
        this.bR.eb();
        this.el.removeAllViews();
        this.el.destroy();
        this.el = null;
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.el.canGoBack()) {
            this.el.goBack();
            return true;
        }
        mq.incrementCounterAndRecord(GeneratedOutlineSupport1.outline91(new StringBuilder(), aX(), "OPERATION_CANCELED"), new String[0]);
        io.e("MAPUIActivityTemplate", "Customer hit back and cannot go back in webview. Returning error.");
        n(null);
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.el.saveState(bundle);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void p(Bundle bundle) {
        io.i("MAPUIActivityTemplate", "Initializing auth challenge webview");
        if (bundle != null) {
            this.el.restoreState(bundle);
        }
        this.el.setScrollBarStyle(0);
        WebSettings settings = this.el.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        int i = Build.VERSION.SDK_INT;
        settings.setMixedContentMode(1);
        this.el.clearFormData();
        this.el.getSettings().setJavaScriptEnabled(true);
        new StringBuilder("Current WebView user agent version:").append(settings.getUserAgentString());
        io.dm("MAPUIActivityTemplate");
    }
}
