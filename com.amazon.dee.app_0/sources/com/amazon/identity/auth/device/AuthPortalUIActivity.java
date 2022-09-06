package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.deecomms.common.Constants;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.api.WebViewHelper;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.dr;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.endpoint.TokenRequestHelpers;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class AuthPortalUIActivity extends Activity {
    private static final String TAG = AuthPortalUIActivity.class.getName();
    private ej bR;
    private ds bb;
    private BackwardsCompatiableDataStorage bi;
    private String bk;
    private mv dT;
    private mv dU;
    private String dV;
    private Timer dX;
    private MAPAccountManager dY;
    private RemoteCallbackWrapper dZ;
    private String ea;
    private String eb;
    private Bundle ec;
    private String ed;
    private Set<String> ee;
    private String ef;
    private String eg;
    private boolean eh;
    private boolean ei;
    private boolean ej;
    private int ek;
    private WebView el;
    private de em;
    private MAPSmsReceiver en;
    private dr eo;
    private boolean ep;
    private ValueCallback<Uri[]> eq;
    private CustomerInformationManager er;
    private fe es;
    private String eu;
    private Set<String> ev;
    private hy ew;
    private ed o;
    private AmazonAccountManager s;
    private mv dP = null;
    private mv dQ = null;
    private mv dR = null;
    private mv dS = null;
    private mv dW = null;
    private AtomicBoolean et = new AtomicBoolean(false);
    private AtomicBoolean ex = new AtomicBoolean(false);
    private int ey = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.AuthPortalUIActivity$6  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] eF;
        static final /* synthetic */ int[] eG = new int[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.values().length];

        static {
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.TOP_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.TOP_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.CENTER_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.CENTER_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.CENTER_RIGHT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.BOTTOM_LEFT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.BOTTOM_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                eG[MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.BOTTOM_RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            eF = new int[MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.values().length];
            try {
                eF[MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.PROGRESS_BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                eF[MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.OFF.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                eF[MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.SPINNER_SMALL.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                eF[MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.SPINNER_MEDIUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                eF[MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.SPINNER_LARGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    class a extends TimerTask {
        private a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (AuthPortalUIActivity.this.bR != null) {
                AuthPortalUIActivity.this.bR.bA(AuthPortalUIActivity.this.dV + ":NetworkState:" + mp.aL(AuthPortalUIActivity.this.o));
            }
            mq.incrementCounterAndRecord("NetworkError2:AuthPortalUIActivity", new String[0]);
            AuthPortalUIActivity.this.n(m.a(MAPError.CommonError.NETWORK_ERROR, "Unable to render content. Request timed out.", MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), "Unable to render content. Request timed out."));
        }

        /* synthetic */ a(AuthPortalUIActivity authPortalUIActivity, byte b) {
            this();
        }
    }

    static /* synthetic */ void F(AuthPortalUIActivity authPortalUIActivity) {
        if (!authPortalUIActivity.eh || authPortalUIActivity.ei) {
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(authPortalUIActivity.getBaseContext(), ResourceHelper.A(authPortalUIActivity, "delay_fade_anim"));
        authPortalUIActivity.ei = true;
        ProgressBar progressBar = (ProgressBar) authPortalUIActivity.findViewById(authPortalUIActivity.ek);
        progressBar.startAnimation(loadAnimation);
        progressBar.setVisibility(4);
    }

    @SuppressLint({"NewApi"})
    private void aA() {
        View decorView;
        Bundle bundle = this.ec;
        if (bundle == null || !bundle.containsKey(MAPAccountManager.AuthPortalActivityUIOptions.KEY_SYSTEM_UI_VISIBILITY)) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        int i2 = this.ec.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_SYSTEM_UI_VISIBILITY);
        Window window = getWindow();
        if (window == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.setSystemUiVisibility(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> aB() {
        Bundle bundle;
        HashMap hashMap = new HashMap();
        try {
            bundle = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException e) {
            io.e(TAG, "No meta found for this package", e);
        }
        if (bundle == null) {
            return hashMap;
        }
        String string = bundle.getString("debugParams");
        if (!TextUtils.isEmpty(string)) {
            TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(JsonReaderKt.COMMA);
            simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext()) {
                String next = simpleStringSplitter.next();
                TextUtils.SimpleStringSplitter simpleStringSplitter2 = new TextUtils.SimpleStringSplitter(Chars.EQ);
                simpleStringSplitter2.setString(next);
                if (simpleStringSplitter2.hasNext()) {
                    String next2 = simpleStringSplitter2.next();
                    if (simpleStringSplitter2.hasNext()) {
                        hashMap.put(next2, simpleStringSplitter2.next());
                    }
                }
            }
        }
        return hashMap;
    }

    private boolean aC() {
        Bundle bundle = this.ec;
        return bundle != null && bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aD() {
        if (this.em.cJ() && this.dT == null && this.bR != null) {
            io.dm(TAG);
            this.dT = this.bR.bz("MFA:ChallengeCodeEnterTime");
        }
        if (this.em.cK() && this.dU == null && this.bR != null) {
            io.dm(TAG);
            this.dU = this.bR.bz("DCQ:ChallengeQuestionEnterTime");
        }
        aI();
        final ProgressBar progressBar = (ProgressBar) findViewById(this.ek);
        if (progressBar.getVisibility() == 0) {
            ji.c(new Runnable() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.12
                @Override // java.lang.Runnable
                public void run() {
                    if (AuthPortalUIActivity.this.ej) {
                        AuthPortalUIActivity.F(AuthPortalUIActivity.this);
                    }
                    if (AuthPortalUIActivity.this.eh) {
                        progressBar.setVisibility(4);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aE() {
        this.ei = false;
        if (this.eh) {
            ProgressBar progressBar = (ProgressBar) findViewById(this.ek);
            progressBar.setProgress(0);
            progressBar.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RemoteCallbackWrapper aF() {
        RemoteCallbackWrapper remoteCallbackWrapper = this.dZ;
        this.dZ = null;
        return remoteCallbackWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG() {
        this.et.set(false);
        super.finish();
    }

    private boolean aH() {
        return this.ec.getBoolean("isAccountStateFixUpFlow");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aI() {
        Timer timer = this.dX;
        if (timer != null) {
            timer.cancel();
            this.dX = null;
        }
    }

    private RelativeLayout aJ() {
        return (RelativeLayout) findViewById(ResourceHelper.z(this, "apparentlayout"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av(String str) {
        String dK;
        if (TextUtils.isEmpty(this.eu) || (dK = jn.dK(str)) == null || this.ev.contains(dK)) {
            return;
        }
        String str2 = TAG;
        "Setting up the frc cookie in AuthPortalUIActivity for domain : ".concat(dK);
        io.dm(str2);
        jn.a(this.o, dK, "frc", this.eu, "/ap", null, true);
        this.ev.add(dK);
    }

    private void aw(String str) {
        if (this.ec.containsKey("InjectCookiesToAuthPortalUIActivity")) {
            io.i(TAG, "Need to inject the cookies into the webview.");
            String[] stringArray = this.ec.getStringArray("InjectCookiesToAuthPortalUIActivity");
            if (stringArray == null) {
                return;
            }
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            jo.aJ(this.o);
            for (String str2 : stringArray) {
                String str3 = TAG;
                "Adding cookie to CookieManager: ".concat(String.valueOf(str2));
                io.dm(str3);
                cookieManager.setCookie(str, str2);
            }
            jo.aJ(this.o);
        }
    }

    private void ay() {
        Bundle bundle = this.ec;
        if (bundle != null) {
            this.dZ = (RemoteCallbackWrapper) bundle.getParcelable("callback");
            this.ec.remove("callback");
        }
    }

    private void az() {
        ArrayList<String> stringArrayList;
        Bundle bundle = this.ec;
        if (bundle != null && (stringArrayList = bundle.getStringArrayList(MAPAccountManager.KEY_ADDITIONAL_SIGNIN_DOMAINS)) != null) {
            this.ee.addAll(stringArrayList);
        }
        this.ee.add(this.ea);
        this.ee = hr.i(this.ee);
        String str = TAG;
        new StringBuilder("Allowed signin domains after formatting ").append(this.ee);
        io.dm(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebView getWebView() {
        WebView webView = this.el;
        if (webView != null) {
            return webView;
        }
        this.el = (WebView) findViewById(ResourceHelper.z(this, "apwebview"));
        return this.el;
    }

    static /* synthetic */ void j(AuthPortalUIActivity authPortalUIActivity) {
        String a2 = fm.a(authPortalUIActivity.o, authPortalUIActivity.getPackageName(), authPortalUIActivity.bR, authPortalUIActivity.ep);
        if (!TextUtils.isEmpty(a2)) {
            jn.a(authPortalUIActivity.o, authPortalUIActivity.ed, "map-md", a2, "/ap", null, true);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (!this.et.get()) {
            io.dm(TAG);
            Bundle b = m.b(MAPError.CommonError.OPERATION_CANCELLED, "Registration canceled", 4, "Registration canceled");
            if (aH()) {
                b.putStringArrayList(MAPAccountManager.KEY_ACCOUNT_MISSING_ATTRIBUTES, this.ec.getStringArrayList(MAPAccountManager.KEY_ACCOUNT_MISSING_ATTRIBUTES));
            }
            n(b);
            return;
        }
        aG();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        io.i(TAG, "AuthPortalUIActivity onActivityResult()");
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.er.a(i2, intent);
        } else if (this.eq == null) {
        } else {
            if (intent != null && i2 == -1) {
                String dataString = intent.getDataString();
                this.eq.onReceiveValue(!TextUtils.isEmpty(dataString) ? new Uri[]{Uri.parse(dataString)} : null);
                this.eq = null;
                return;
            }
            this.eq.onReceiveValue(new Uri[0]);
            this.eq = null;
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.et.get()) {
            mv mvVar = this.dS;
            if (mvVar != null) {
                mvVar.iL();
            }
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            WebView webView = getWebView();
            if (webView == null) {
                return;
            }
            webView.invalidate();
        } catch (NoSuchFieldError e) {
            String str = TAG;
            new StringBuilder("Android Resource error: ").append(e.getMessage());
            io.dm(str);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        final WebView webView;
        final String str;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        boolean z2;
        String str2;
        String str3;
        int i5;
        int i6;
        try {
            io.i(TAG, String.format("Login webview called in package %s by package %s", getPackageName(), getCallingPackage()));
            ja.a(this);
            this.o = ed.M(getApplicationContext());
            this.dY = new MAPAccountManager(this.o);
            this.s = new AmazonAccountManager(this.o);
            this.bb = (ds) this.o.getSystemService("sso_platform");
            this.bi = new BackwardsCompatiableDataStorage(this.o);
            this.ev = new HashSet();
            this.ee = new HashSet();
            this.ef = mz.bl(this);
            this.bk = iw.c(this.o, DeviceAttribute.CentralDeviceType);
            this.eg = OpenIdRequest.m(this.ef, this.bk);
            this.ew = new hy();
            this.ew.gu();
            Intent intent = getIntent();
            b(intent);
            requestWindowFeature(1);
            super.onCreate(bundle);
            if (!TextUtils.isEmpty(this.ef) && !TextUtils.isEmpty(this.bk)) {
                final OpenIdRequest d = d(intent);
                setContentView(ResourceHelper.B(this, "apwebviewlayout"));
                WebView webView2 = getWebView();
                if (webView2 == null) {
                    webView = null;
                } else {
                    if (bundle != null) {
                        webView2.restoreState(bundle);
                    }
                    webView2.setScrollBarStyle(0);
                    WebSettings settings = webView2.getSettings();
                    settings.setSavePassword(false);
                    settings.setSaveFormData(false);
                    settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                    settings.setAllowFileAccess(false);
                    settings.setDomStorageEnabled(true);
                    int i7 = Build.VERSION.SDK_INT;
                    settings.setAllowContentAccess(false);
                    int i8 = Build.VERSION.SDK_INT;
                    settings.setMixedContentMode(1);
                    webView2.clearFormData();
                    webView2.getSettings().setJavaScriptEnabled(true);
                    String str4 = TAG;
                    new StringBuilder("Current MAP Webview version:").append(settings.getUserAgentString());
                    io.dm(str4);
                    webView = webView2;
                }
                if (webView == null) {
                    n(m.a(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occured while setting up the WebView.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView."));
                    return;
                }
                this.en = new MAPSmsReceiver(this.bR, webView);
                if (!TextUtils.isEmpty(this.ec.getString("directedid"))) {
                    str = this.ec.getString("directedid");
                    String str5 = TAG;
                    "Directed id for ConfirmCredential has been set to ".concat(String.valueOf(str));
                    io.dm(str5);
                } else {
                    io.dm(TAG);
                    str = null;
                }
                this.em = new de(this.o, this.en, d.bW(), d.bU(), OpenIdRequest.TOKEN_SCOPE.ACCESS, this.ee, this.ec == null ? false : this.ec.getBoolean(MAPAccountManager.KEY_ALLOW_ALL_SIGNIN_PATHS), new de.a() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.10
                    @Override // com.amazon.identity.auth.device.de.a
                    public void aM() {
                        ImageView imageView = (ImageView) AuthPortalUIActivity.this.findViewById(ResourceHelper.z(AuthPortalUIActivity.this, "apimageview"));
                        WebView webView3 = AuthPortalUIActivity.this.getWebView();
                        if (webView3 == null) {
                            AuthPortalUIActivity.this.n(m.a(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occured while setting up the WebView.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView."));
                            return;
                        }
                        if (imageView.getVisibility() == 0) {
                            imageView.setVisibility(8);
                        }
                        if (webView3.getVisibility() != 0) {
                            webView3.setVisibility(0);
                            webView3.requestFocusFromTouch();
                        }
                        AuthPortalUIActivity.this.f(100);
                        AuthPortalUIActivity.this.aD();
                        if (AuthPortalUIActivity.this.dR != null) {
                            AuthPortalUIActivity.this.dR.iL();
                        }
                        if (AuthPortalUIActivity.this.dQ == null) {
                            return;
                        }
                        AuthPortalUIActivity.this.dQ.iL();
                    }

                    @Override // com.amazon.identity.auth.device.de.a
                    public void aN() {
                        io.dm(AuthPortalUIActivity.TAG);
                        if (AuthPortalUIActivity.this.em.cJ() && AuthPortalUIActivity.this.dT != null) {
                            io.dm(AuthPortalUIActivity.TAG);
                            AuthPortalUIActivity.this.dT.iL();
                            AuthPortalUIActivity.this.dT = null;
                        }
                        if (!AuthPortalUIActivity.this.em.cK() || AuthPortalUIActivity.this.dU == null) {
                            return;
                        }
                        io.dm(AuthPortalUIActivity.TAG);
                        AuthPortalUIActivity.this.dU.iL();
                        AuthPortalUIActivity.this.dU = null;
                    }

                    @Override // com.amazon.identity.auth.device.de.a
                    public void ax(String str6) {
                        AuthPortalUIActivity.this.av(str6);
                        if (AuthPortalUIActivity.this.dP != null) {
                            AuthPortalUIActivity.this.dP.iL();
                        }
                        String str7 = "AuthPortalPageTimeout:" + d.bW().name();
                        if (!AuthPortalUIActivity.this.em.cJ()) {
                            if (AuthPortalUIActivity.this.em.cK()) {
                                str7 = GeneratedOutlineSupport1.outline72(str7, ":DCQ");
                            }
                        } else {
                            str7 = GeneratedOutlineSupport1.outline72(str7, ":MFA");
                        }
                        AuthPortalUIActivity.this.dV = str7;
                        AuthPortalUIActivity.this.aI();
                        AuthPortalUIActivity.this.dX = new Timer();
                        AuthPortalUIActivity.this.dX.schedule(new a(AuthPortalUIActivity.this, (byte) 0), 250000L);
                        AuthPortalUIActivity.this.aE();
                    }

                    @Override // com.amazon.identity.auth.device.de.a
                    public void ay(String str6) {
                        String string = AuthPortalUIActivity.this.ec.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB);
                        mq.incrementCounterAndRecord("chromeCustomTabLaunch:OpenFederatedAuthSignInRequest:Url=".concat(String.valueOf(str6)), new String[0]);
                        GeneratedOutlineSupport1.outline161(str6, "Opening in chrome custom tab - url=", AuthPortalUIActivity.TAG);
                        bw.a(AuthPortalUIActivity.this.getWebView().getContext(), Uri.parse(str6), string);
                    }

                    @Override // com.amazon.identity.auth.device.de.a
                    public void b(cm cmVar) {
                        AuthPortalUIActivity.this.en.df();
                        AuthPortalUIActivity.this.a(d.bW(), cmVar, str);
                    }

                    @Override // com.amazon.identity.auth.device.de.a
                    public void o(Bundle bundle2) {
                        AuthPortalUIActivity.this.aI();
                        AuthPortalUIActivity.this.n(bundle2);
                    }
                }, this.bR);
                this.el.setWebViewClient(this.em);
                this.er = new CustomerInformationManager(this, 2);
                this.es = new fe(this.o, this.en);
                if (!WebViewHelper.enableAmazonAuthenticatorForWebView(webView, null)) {
                    io.i(TAG, "Error occurred while enabling Amazon Authenticator JS bridge for MAP WebView");
                }
                a(webView, this.er, this.es, new dr.a() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.1
                    @Override // com.amazon.identity.auth.device.dr.a
                    public void a(MAPRuntimePermissionHandler mAPRuntimePermissionHandler) {
                        AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
                        mAPRuntimePermissionHandler.a(authPortalUIActivity, authPortalUIActivity.eo, AuthPortalUIActivity.this.el, AuthPortalUIActivity.this.bR, AuthPortalUIActivity.this.ep);
                    }

                    @Override // com.amazon.identity.auth.device.dr.a
                    public void aK() {
                        AuthPortalUIActivity.this.aD();
                    }

                    @Override // com.amazon.identity.auth.device.dr.a
                    public void aL() {
                        if (AuthPortalUIActivity.this.bR != null) {
                            AuthPortalUIActivity.this.bR.bA("OnCFCalledByAuthPortal");
                        }
                        if (AuthPortalUIActivity.this.dW != null) {
                            AuthPortalUIActivity.this.dW.stop();
                            AuthPortalUIActivity.this.dW = null;
                        }
                    }
                });
                this.ed = d.bT();
                a(this.ed, "sid", "/", ia.gz(), false);
                if (this.ec != null) {
                    String string = this.ec.getString("directedid");
                    if (!TextUtils.isEmpty(string)) {
                        String z3 = this.bi.z(string, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
                        if (!TextUtils.isEmpty(z3)) {
                            jn.a(this.o, this.ed, "sid", z3, "/", ia.gz(), false);
                        }
                    }
                }
                this.eu = fm.h(this.o, this.ef);
                av(this.ed);
                aw(this.ed);
                Bundle bundle2 = this.ec;
                MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState progressBarState = MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.OFF;
                MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition screenPosition = MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.BOTTOM_CENTER;
                this.ej = true;
                if (bundle2 != null) {
                    String string2 = bundle2.getString("progressbar_state");
                    if (string2 != null) {
                        progressBarState = MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.get(string2);
                    }
                    String string3 = bundle2.getString("progressbar_position");
                    if (string3 != null) {
                        screenPosition = MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.get(string3);
                    }
                    this.ej = bundle2.getBoolean("progressbar_fade", this.ej);
                    z = bundle2.getBoolean("progressbar_stretch", true);
                    z2 = bundle2.getBoolean("progressbar_invert_spinner", false);
                    i2 = bundle2.getInt("progressbar_resource", -1);
                    i4 = bundle2.getInt("progressbar_background_resource", -1);
                    i3 = bundle2.getInt("progressbar_primary_color", -1);
                    i = bundle2.getInt("progressbar_secondary_color", -1);
                } else {
                    i = -1;
                    i2 = -1;
                    i3 = -1;
                    i4 = -1;
                    z = true;
                    z2 = false;
                }
                this.ek = ResourceHelper.z(this, "approgressbar");
                ProgressBar progressBar = (ProgressBar) findViewById(this.ek);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
                int i9 = AnonymousClass6.eF[progressBarState.ordinal()];
                if (i9 == 1) {
                    str2 = null;
                    this.eh = true;
                    if (z) {
                        layoutParams.width = -1;
                    } else {
                        layoutParams.width = -2;
                    }
                    if (-1 != i2) {
                        Drawable drawable = getResources().getDrawable(i2);
                        progressBar.setProgressDrawable(new ClipDrawable(drawable, 3, 1));
                        if (-1 != i4) {
                            progressBar.setBackgroundDrawable(getResources().getDrawable(i4));
                        }
                        layoutParams.width = drawable.getMinimumWidth();
                    } else if (-1 != i3) {
                        if (-1 == i) {
                            i = i3;
                        }
                        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i3, i});
                        gradientDrawable.setShape(0);
                        gradientDrawable.setCornerRadius(5.0f);
                        progressBar.setProgressDrawable(new ClipDrawable(gradientDrawable, 3, 1));
                        progressBar.setBackgroundDrawable(getResources().getDrawable(17301612));
                    }
                } else if (i9 != 2) {
                    this.eh = true;
                    int a2 = a(progressBarState, z2);
                    layoutParams.width = -2;
                    progressBar.setVisibility(8);
                    str2 = null;
                    progressBar = new ProgressBar(this, null, a2);
                    aJ().addView(progressBar);
                    int z4 = ResourceHelper.z(this, "apspinner_progressbar");
                    progressBar.setId(z4);
                    this.ek = z4;
                } else {
                    str2 = null;
                    this.eh = false;
                    progressBar.setVisibility(8);
                }
                if (this.eh) {
                    progressBar.setLayoutParams(a(layoutParams, screenPosition));
                    progressBar.bringToFront();
                }
                webView.setWebChromeClient(new WebChromeClient() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.9
                    @Override // android.webkit.WebChromeClient
                    public void onProgressChanged(WebView webView3, int i10) {
                        if (AuthPortalUIActivity.this.eh) {
                            AuthPortalUIActivity.a(AuthPortalUIActivity.this, webView3, i10);
                        }
                    }

                    @Override // android.webkit.WebChromeClient
                    public boolean onShowFileChooser(WebView webView3, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                        io.i(AuthPortalUIActivity.TAG, "AuthPortalUIActivity onShowFileChooser()");
                        AuthPortalUIActivity.a(AuthPortalUIActivity.this, valueCallback);
                        return true;
                    }
                });
                Bundle bundle3 = this.ec;
                ImageView imageView = (ImageView) findViewById(ResourceHelper.z(this, "apimageview"));
                if (bundle3 != null) {
                    i6 = bundle3.getInt("splashscreen_resource", -1);
                    str3 = bundle3.getString("splashscreen_scale_type");
                    i5 = -1;
                } else {
                    str3 = str2;
                    i5 = -1;
                    i6 = -1;
                }
                if (i5 == i6) {
                    imageView.setVisibility(8);
                    webView.setVisibility(0);
                    webView.requestFocusFromTouch();
                } else {
                    imageView.setImageResource(i6);
                    if (!TextUtils.isEmpty(str3)) {
                        imageView.setScaleType(ImageView.ScaleType.valueOf(str3));
                    }
                }
                if (this.ec.containsKey("domain_hint") && this.ec.containsKey("ab_federated_auth")) {
                    this.ex.set(true);
                    this.ey++;
                    bw.a(this, Uri.parse(d.bS()), this.ec.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB));
                    mq.incrementCounterAndRecord("chromeCustomTabLaunch:OpenFederatedAuthRegistrationRequest:Url=" + d.bS(), new String[0]);
                    String str6 = TAG;
                    new StringBuilder("Opening in chrome custom tab - url=").append(d.bS());
                    io.dm(str6);
                    return;
                }
                this.ex.set(false);
                this.ey = 0;
                ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.7
                    @Override // java.lang.Runnable
                    public void run() {
                        AuthPortalUIActivity.j(AuthPortalUIActivity.this);
                        AuthPortalUIActivity.a(AuthPortalUIActivity.this, webView, d);
                    }
                });
                return;
            }
            n(m.a(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occurred while setting up the WebView. Cannot fetch client id. If this is an unregistered Grover device or Canary device, this error is expected.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView. Cannot fetch client id! If it is an unregistered Grover(versions lesser than )/Canary(all version) device, it is expected."));
        } catch (IllegalArgumentException e) {
            n(m.a(MAPError.CommonError.BAD_REQUEST, String.format("An IllegalArgumentException was thrown with message: %s", e.getMessage()), MAPAccountManager.RegistrationError.BAD_REQUEST.value(), e.getMessage()));
        } catch (Exception e2) {
            n(m.a(MAPError.CommonError.INTERNAL_ERROR, String.format("An Exception was thrown with message: %s", e2.getMessage()), MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), e2.getMessage()));
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        io.dm(TAG);
        MAPSmsReceiver mAPSmsReceiver = this.en;
        if (mAPSmsReceiver != null) {
            mAPSmsReceiver.L(this.o);
        }
        Set<String> set = this.ev;
        if (set != null && set.size() > 0) {
            io.dm(TAG);
            for (String str : this.ev) {
                a(str, "frc", "/ap", null, true);
            }
            this.ev.clear();
        }
        String str2 = this.ed;
        if (str2 != null) {
            a(str2, "map-md", "/ap", null, true);
        }
        ej ejVar = this.bR;
        if (ejVar != null) {
            ejVar.eb();
        }
        aI();
        if (this.el != null) {
            aJ().removeView(this.el);
            this.el.removeAllViews();
            this.el.destroy();
            this.el = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        WebHistoryItem itemAtIndex;
        if (i == 4) {
            if (this.em.cJ() && this.bR != null) {
                io.i(TAG, "MFA canceled");
                this.bR.bA("MFACanceled");
            }
            if (this.em.cK() && this.bR != null) {
                io.i(TAG, "DCQ canceled");
                this.bR.bA("DCQCanceled");
            }
            WebView webView = getWebView();
            if (webView == null) {
                n(m.a(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occured while setting up the WebView.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView."));
                return false;
            } else if (webView.canGoBack()) {
                WebBackForwardList copyBackForwardList = webView.copyBackForwardList();
                if (copyBackForwardList != null && copyBackForwardList.getSize() > 0 && (itemAtIndex = copyBackForwardList.getItemAtIndex(copyBackForwardList.getCurrentIndex() - 1)) != null) {
                    if (this.en.a(itemAtIndex.getUrl(), this.o)) {
                        if (webView.canGoBackOrForward(-2)) {
                            webView.goBackOrForward(-2);
                            return true;
                        }
                        io.a(TAG, this.bR, "Cannot go the the page before previous page. Something is wrong.", "SkipAutoPhoneVerificationPageError");
                        finish();
                        return false;
                    }
                }
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        io.i(TAG, String.format("Existing login webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        ja.a(this);
        RemoteCallbackWrapper aF = aF();
        Uri data = intent.getData();
        if (data != null && TextUtils.equals(data.getPath(), "/ap/maplanding")) {
            this.ey++;
            cm cmVar = new cm(data.toString());
            this.et.set(false);
            this.dZ = aF;
            if (!this.eh) {
                this.eh = true;
                this.ej = true;
                MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState progressBarState = MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.get("spinner_medium");
                MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition screenPosition = MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.get("center_center");
                this.ej = true;
                int a2 = a(progressBarState, false);
                ProgressBar progressBar = (ProgressBar) findViewById(this.ek);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
                layoutParams.width = -2;
                progressBar.setVisibility(8);
                ProgressBar progressBar2 = new ProgressBar(this, null, a2);
                aJ().addView(progressBar2);
                int z = ResourceHelper.z(this, "apspinner_progressbar");
                progressBar2.setId(z);
                this.ek = z;
                if (this.eh) {
                    progressBar2.setLayoutParams(a(layoutParams, screenPosition));
                    progressBar2.bringToFront();
                }
            }
            aE();
            getWindow().setFlags(16, 16);
            f(60);
            this.en.df();
            a(OpenIdRequest.REQUEST_TYPE.SIGN_IN, cmVar, (String) null);
            mq.incrementCounterAndRecord("federatedAuthenticationCallbackUrlSuccess", new String[0]);
            io.dm(TAG);
            return;
        }
        if (aF != null) {
            aF.onError(m.b(MAPError.CommonError.OPERATION_CANCELLED, "Registration canceled", 4, "Registration canceled"));
        }
        b(intent);
        OpenIdRequest d = d(intent);
        Uri parse = Uri.parse(d.bS());
        if ("true".equalsIgnoreCase(parse.getQueryParameter("ab_federated_auth")) && !TextUtils.isEmpty(parse.getQueryParameter("domain_hint"))) {
            if (this.ex.get()) {
                return;
            }
            this.ex.set(true);
            this.ey++;
            bw.a(this, parse, this.ec.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB));
            mq.incrementCounterAndRecord("chromeCustomTabLaunch:OpenFederatedAuthRegistrationRequest:Url=" + parse.toString(), new String[0]);
            String str = TAG;
            new StringBuilder("Opening in chrome custom tab - url=").append(parse.toString());
            io.dm(str);
            return;
        }
        this.ex.set(false);
        this.ey = 0;
        getWebView().loadUrl(d.bS());
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        MAPRuntimePermissionHandler i2 = MAPRuntimePermissionHandler.i(i);
        if (i2 != null) {
            i2.a(this.o, this.eo, this.el, this.bR, this.ep);
        } else {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.ex.get()) {
            this.ey--;
            if (this.ey >= 0) {
                return;
            }
            mq.incrementCounterAndRecord("chromeCustomTabClosed:CloseFederatedAuthRegistrationRequest", new String[0]);
            io.dm(TAG);
            onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        WebView webView = getWebView();
        if (webView != null) {
            webView.saveState(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i) {
        if (this.eh) {
            ProgressBar progressBar = (ProgressBar) findViewById(this.ek);
            if (progressBar.getVisibility() != 0) {
                return;
            }
            progressBar.setProgress(i);
        }
    }

    private void m(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean(MAPAccountManager.AuthPortalActivityUIOptions.KEY_ABOVE_LOCKSCREEN)) {
            getWindow().addFlags(524288);
        }
        if (bundle.containsKey(MAPAccountManager.AuthPortalActivityUIOptions.KEY_REQUESTED_ORIENTATION)) {
            setRequestedOrientation(bundle.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_REQUESTED_ORIENTATION));
        }
        if (!bundle.getBoolean(MAPAccountManager.AuthPortalActivityUIOptions.KEY_IS_FULLSCREEN)) {
            return;
        }
        getWindow().addFlags(1024);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(final Bundle bundle) {
        final RemoteCallbackWrapper aF = aF();
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.13
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle2 = bundle;
                if (bundle2 == null) {
                    AuthPortalUIActivity.this.finish();
                    return;
                }
                RemoteCallbackWrapper remoteCallbackWrapper = aF;
                if (remoteCallbackWrapper != null) {
                    remoteCallbackWrapper.onError(bundle2);
                }
                AuthPortalUIActivity.this.aG();
            }
        });
    }

    private OpenIdRequest d(Intent intent) {
        if (intent != null) {
            this.ec = intent.getExtras();
            this.bR = ej.b(intent, "MAP_AuthPortalUIActivity");
            Bundle bundle = this.ec;
            if (bundle != null) {
                this.ep = bundle.getBoolean(MAPAccountManager.KEY_DISABLE_USERNAME_AUTO_SUGGESTION);
                this.eb = EnvironmentUtils.cd().getPandaHost(hr.I(this.ec));
            }
            aA();
            ay();
            m(this.ec);
        } else {
            io.e(TAG, "The intent in AuthPortalUIActivity is null. This should never happen.");
        }
        Bundle bundle2 = this.ec;
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        this.ec = bundle2;
        this.ec.putString("code_challenge", this.ew.gw());
        this.ec.putString("code_challenge_method", "S256");
        this.ec.putBoolean("use_code_response_type", true);
        OpenIdRequest openIdRequest = new OpenIdRequest(this.eg, c(intent), this.ec);
        this.ea = openIdRequest.getHost();
        az();
        if (a(openIdRequest.bW())) {
            a(openIdRequest);
        }
        if (openIdRequest.bW() == OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL) {
            b(openIdRequest);
        } else {
            openIdRequest.e(this.ec.getBoolean(MAPAccountManager.KEY_DISABLE_REGISTER_WITHUI_PRE_POPULATION, false));
        }
        return openIdRequest;
    }

    private void b(Intent intent) {
        int[] intArray;
        Window window = getWindow();
        if (window != null) {
            window.addFlags(65536);
            window.addFlags(256);
            window.addFlags(8192);
            Bundle extras = intent.getExtras();
            if (extras == null || (intArray = extras.getIntArray(MAPAccountManager.AuthPortalActivityUIOptions.KEY_WINDOW_FLAGS)) == null) {
                return;
            }
            for (int i : intArray) {
                window.addFlags(i);
            }
        }
    }

    private OpenIdRequest.REQUEST_TYPE c(Intent intent) {
        Bundle extras;
        String string;
        OpenIdRequest.REQUEST_TYPE request_type = OpenIdRequest.REQUEST_TYPE.SIGN_IN;
        if (intent == null || (extras = intent.getExtras()) == null || (string = extras.getString(NotificationConstants.REQUEST_TYPE)) == null) {
            return request_type;
        }
        if (OpenIdRequest.REQUEST_TYPE.CALLBACK_FOR_3P_LOGIN.toString().equalsIgnoreCase(string)) {
            return OpenIdRequest.REQUEST_TYPE.CALLBACK_FOR_3P_LOGIN;
        }
        if (OpenIdRequest.REQUEST_TYPE.REGISTER.toString().equalsIgnoreCase(string)) {
            return OpenIdRequest.REQUEST_TYPE.REGISTER;
        }
        if (OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString().equalsIgnoreCase(string)) {
            return OpenIdRequest.REQUEST_TYPE.SIGN_IN;
        }
        if (OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD.toString().equalsIgnoreCase(string)) {
            return OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD;
        }
        if (OpenIdRequest.REQUEST_TYPE.CNEP.toString().equalsIgnoreCase(string)) {
            return OpenIdRequest.REQUEST_TYPE.CNEP;
        }
        if (OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL.toString().equalsIgnoreCase(string)) {
            return OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL;
        }
        return OpenIdRequest.REQUEST_TYPE.AUTHENTICATE.toString().equalsIgnoreCase(string) ? OpenIdRequest.REQUEST_TYPE.AUTHENTICATE : request_type;
    }

    private void a(OpenIdRequest openIdRequest) {
        openIdRequest.aQ("0");
        openIdRequest.bR();
    }

    private void a(String str, String str2, String str3, String str4, boolean z) {
        jn.a(this.o, str, str2, "", str3, str4, z);
    }

    private int a(MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState progressBarState, boolean z) {
        int i = AnonymousClass6.eF[progressBarState.ordinal()];
        if (i == 3) {
            return z ? 16843400 : 16842873;
        } else if (i == 4) {
            return z ? 16843399 : 16842871;
        } else if (i == 5) {
            return z ? 16843401 : 16842874;
        } else {
            io.e(TAG, "Bar State not recongized");
            return 0;
        }
    }

    private void b(OpenIdRequest openIdRequest) {
        String str;
        if (this.bb.dh()) {
            String z = this.bi.z(this.ec.getString("directedid"), AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN);
            if (TextUtils.isEmpty(z)) {
                n(hf.a(MAPError.TokenError.MISSING_TOKEN, "X-MAIN should exist. There is likely a registration bug.", MAPAccountManager.RegistrationError.UNRECOGNIZED, "X-MAIN should exist. There is likely a registration bug."));
                return;
            }
            String host = openIdRequest.getHost();
            String str2 = Constants.AUTH_DOMAIN_DEVELOPMENT;
            if (host.contains(str2)) {
                str = "x-tacbus";
            } else {
                str = "x-main";
                str2 = ".amazon.com";
            }
            MAPCookie mAPCookie = new MAPCookie(str, z, str2, ia.gz(), "/", null, false, false);
            openIdRequest.aR("http://www.amazon.com/ap/specs/auth/confirm_credentials");
            jn.a(this.o, openIdRequest.bS(), mAPCookie);
        } else {
            openIdRequest.aR(TokenRequestHelpers.a(this.ec, TokenRequestHelpers.PROTOCOL.HTTPS, openIdRequest.getHost()) + "/ap/id/" + this.ec.get("directedid"));
        }
        openIdRequest.aQ("0");
        openIdRequest.e(false);
    }

    private static RelativeLayout.LayoutParams a(RelativeLayout.LayoutParams layoutParams, MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition screenPosition) {
        io.a("Bar Pos: %s", screenPosition.getValue());
        switch (AnonymousClass6.eG[screenPosition.ordinal()]) {
            case 1:
            case 2:
            case 3:
                layoutParams.addRule(10);
                break;
            case 4:
            case 5:
            case 6:
                layoutParams.addRule(15);
                break;
            case 7:
            case 8:
            case 9:
                layoutParams.addRule(12);
                break;
        }
        switch (AnonymousClass6.eG[screenPosition.ordinal()]) {
            case 1:
            case 4:
            case 7:
                layoutParams.addRule(9);
                break;
            case 2:
            case 5:
            case 8:
                layoutParams.addRule(14);
                break;
            case 3:
            case 6:
            case 9:
                layoutParams.addRule(11);
                break;
        }
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0055, code lost:
        if (android.text.TextUtils.isEmpty(r6) != false) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.amazon.identity.auth.device.endpoint.OpenIdRequest.REQUEST_TYPE r19, final com.amazon.identity.auth.device.cm r20, final java.lang.String r21) {
        /*
            Method dump skipped, instructions count: 582
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.AuthPortalUIActivity.a(com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE, com.amazon.identity.auth.device.cm, java.lang.String):void");
    }

    static /* synthetic */ void b(AuthPortalUIActivity authPortalUIActivity, final Bundle bundle, final RemoteCallbackWrapper remoteCallbackWrapper) {
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.14
            @Override // java.lang.Runnable
            public void run() {
                Bundle bundle2 = bundle;
                if (bundle2 == null) {
                    AuthPortalUIActivity.this.finish();
                    return;
                }
                RemoteCallbackWrapper remoteCallbackWrapper2 = remoteCallbackWrapper;
                if (remoteCallbackWrapper2 != null) {
                    remoteCallbackWrapper2.onError(bundle2);
                }
                AuthPortalUIActivity.this.aG();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(cm cmVar, String str) {
        RemoteCallbackWrapper aF = aF();
        Bundle bundle = new Bundle();
        String directedId = cmVar.getDirectedId();
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", directedId);
        if (TextUtils.isEmpty(str)) {
            bundle.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", this.bi.z(directedId, "com.amazon.dcp.sso.token.oauth.amazon.access_token"));
        } else {
            bundle.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", str);
        }
        a(bundle, aF);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Bundle bundle, final RemoteCallbackWrapper remoteCallbackWrapper) {
        ji.d(new Runnable() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.2
            @Override // java.lang.Runnable
            public void run() {
                RemoteCallbackWrapper remoteCallbackWrapper2 = remoteCallbackWrapper;
                if (remoteCallbackWrapper2 != null) {
                    remoteCallbackWrapper2.onSuccess(bundle);
                }
                AuthPortalUIActivity.this.finish();
            }
        });
    }

    private boolean a(OpenIdRequest.REQUEST_TYPE request_type) {
        return request_type.equals(OpenIdRequest.REQUEST_TYPE.AUTHENTICATE) && this.ec.getBoolean("isWarmSeatAuthentication");
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void a(WebView webView, CustomerInformationManager customerInformationManager, fe feVar, dr.a aVar) {
        int i = Build.VERSION.SDK_INT;
        this.eo = new dr(aVar);
        webView.addJavascriptInterface(this.eo, "embedNotification");
        webView.addJavascriptInterface(new fj(webView, customerInformationManager, feVar), "MAPAndroidJSBridge");
    }

    private void a(final cm cmVar, final boolean z, final Callback callback, final Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(AccountConstants.KEY_AUTHORIZATION_CODE, cmVar.bX());
        bundle2.putString(AccountConstants.KEY_CODE_VERIFIER, this.ew.gv());
        bundle2.putString("code_challenge_method", this.ew.gx());
        bundle2.putString(AccountConstants.KEY_CLIENT_DOMAIN, AccountConstants.CLIENT_DOMAIN_DEVICE_LEGACY);
        bundle2.putString("client_id", this.eg);
        bundle2.putBoolean("authorizationCode", true);
        new TokenManagement(this.o).getToken(cmVar.getDirectedId(), "com.amazon.dcp.sso.token.oauth.amazon.access_token", bundle2, new Callback() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.5
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle3) {
                io.i(AuthPortalUIActivity.TAG, "Exchange token with authorization code failed");
                AuthPortalUIActivity.b(AuthPortalUIActivity.this, bundle3, AuthPortalUIActivity.this.aF());
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle3) {
                io.i(AuthPortalUIActivity.TAG, "Exchange token with authorization code succeed");
                String string = bundle3.getString("value_key");
                if (z) {
                    io.i(AuthPortalUIActivity.TAG, "Continue to perform device registration through FIRS");
                    bundle.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", string);
                    AuthPortalUIActivity.this.dY.registerAccount(RegistrationType.FROM_ACCESS_TOKEN, bundle, callback);
                    return;
                }
                AuthPortalUIActivity.this.a(cmVar, string);
            }
        });
    }

    private boolean a(cm cmVar) {
        return !TextUtils.isEmpty(cmVar.bX());
    }

    static /* synthetic */ void a(AuthPortalUIActivity authPortalUIActivity, final WebView webView, final OpenIdRequest openIdRequest) {
        authPortalUIActivity.runOnUiThread(new Runnable() { // from class: com.amazon.identity.auth.device.AuthPortalUIActivity.8
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(webView.getUrl())) {
                    openIdRequest.b(AuthPortalUIActivity.this.aB());
                    String bS = openIdRequest.bS();
                    Map<String, String> requestHeaders = openIdRequest.getRequestHeaders();
                    String unused = AuthPortalUIActivity.TAG;
                    io.a("Loading AuthPortal Signin Url: %s", bS);
                    if (AuthPortalUIActivity.this.bR != null) {
                        AuthPortalUIActivity authPortalUIActivity2 = AuthPortalUIActivity.this;
                        ej ejVar = authPortalUIActivity2.bR;
                        authPortalUIActivity2.dP = ejVar.bz("AuthPortalUIActivity_FirstOnPageStarted:" + mp.eQ(bS));
                        AuthPortalUIActivity authPortalUIActivity3 = AuthPortalUIActivity.this;
                        ej ejVar2 = authPortalUIActivity3.bR;
                        authPortalUIActivity3.dQ = ejVar2.bz("AuthPortalUIActivity_FirstPageLoad:" + mp.eQ(bS));
                        AuthPortalUIActivity authPortalUIActivity4 = AuthPortalUIActivity.this;
                        ej ejVar3 = authPortalUIActivity4.bR;
                        authPortalUIActivity4.dR = ejVar3.bz("AuthPortalUIActivity_FirstPageRender:" + mp.eQ(bS));
                        AuthPortalUIActivity authPortalUIActivity5 = AuthPortalUIActivity.this;
                        ej ejVar4 = authPortalUIActivity5.bR;
                        authPortalUIActivity5.dS = ejVar4.bz("AuthPortalUIActivity_BackPressedInWebView:" + mp.eQ(bS));
                    }
                    AuthPortalUIActivity.a(AuthPortalUIActivity.this, bS);
                    webView.loadUrl(bS, requestHeaders);
                    return;
                }
                webView.requestLayout();
            }
        });
    }

    static /* synthetic */ void a(AuthPortalUIActivity authPortalUIActivity, String str) {
        int i = Build.VERSION.SDK_INT;
        ej ejVar = authPortalUIActivity.bR;
        if (ejVar != null) {
            authPortalUIActivity.dW = ejVar.bz("AuthPortalUIActivity_CriticalFeatureLoaded:" + mp.eQ(str));
        }
    }

    static /* synthetic */ void a(AuthPortalUIActivity authPortalUIActivity, WebView webView, int i) {
        int i2 = Build.VERSION.SDK_INT;
        authPortalUIActivity.f(i);
    }

    static /* synthetic */ void a(AuthPortalUIActivity authPortalUIActivity, ValueCallback valueCallback) {
        ValueCallback<Uri[]> valueCallback2 = authPortalUIActivity.eq;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(null);
        }
        authPortalUIActivity.eq = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        authPortalUIActivity.startActivityForResult(Intent.createChooser(intent, "File Browser"), 1);
    }
}
