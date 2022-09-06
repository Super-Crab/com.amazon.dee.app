package com.amazon.deecomms.oobe.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.CookieUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.CommsIdentityBridge;
import com.amazon.deecomms.oobe.CommsIdentityReceiver;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.OOBEStateMachine;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.oobe.Result;
import com.amazon.deecomms.oobe.VerificationFailure;
import com.amazon.deecomms.oobe.structures.CVFResult;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CVFFragment extends MainOOBEFragment {
    public static final String COMMS_IDENTITY_BRIDGE_IDENTIFIER = "CommsIdentity";
    private static final String CVF_INVALID_CODE = "cvf-widget-alert-id-cvf-invalid-code";
    private static final String CVF_INVALID_PHONE_NUMBER = "cvf-widget-alert-id-cvf-invalid-mobile-phone";
    private static final String CVF_ON_VERIFICATION_FAILURE = "CommsIdentity.onVerificationFailure";
    private static final String CVF_ON_VERIFIED_IDENTITY = "CommsIdentity.onVerifiedIdentity";
    private static final String CVF_PHONE_NUMBER_TAKEN = "cvf-widget-alert-id-cvf-exists-mobile-phone";
    private static final String KEY_PREFIX;
    private static final String KEY_TIMESTAMP;
    private static final String KEY_WEBVIEW_STATE;
    private static final String LANGUAGE_COOKIE_DOMAIN = ".amazon.com";
    private static final String LANGUAGE_COOKIE_EXPIRATION = "Sat, 31-Dec-2016 18:59:59 GMT";
    private static final String LANGUAGE_COOKIE_NAME = "lc-main";
    private static final String LANGUAGE_COOKIE_VALUE = "en_US";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, UserSelectionFragment.class);
    private static final long WEBVIEW_STATE_MAX_AGE = 840000;
    @Inject
    ApplicationManager applicationManager;
    private String originalLanguageCookie;
    @Nullable
    private ProgressDialog progressDialog;
    private CommsIdentityReceiver receiver;
    private Person user;
    private WebView webView;
    private final Bundle webViewState = new Bundle();
    private boolean enableLearnMore = false;
    private boolean revertOriginalLanguageCookie = false;

    static {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("OOBE_STATE.");
        outline1.append(OOBEStateMachine.STATE.MOBILE_VERIFICATION.name());
        KEY_PREFIX = outline1.toString();
        KEY_TIMESTAMP = GeneratedOutlineSupport1.outline91(new StringBuilder(), KEY_PREFIX, ".timestamp");
        KEY_WEBVIEW_STATE = GeneratedOutlineSupport1.outline91(new StringBuilder(), KEY_PREFIX, ".webview");
    }

    static /* synthetic */ void access$500(CVFFragment cVFFragment, String str) {
        WebView webView = cVFFragment.webView;
        if (webView != null) {
            webView.loadUrl(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void evaluateHtml(String str) {
        if (TextUtils.isEmpty(str)) {
            LOG.e("Invalid HTML from CVF");
        } else if (str.contains(CVF_ON_VERIFIED_IDENTITY)) {
            LOG.i("Success from CVF");
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_CORRECT_PIN);
        } else if (str.contains(CVF_ON_VERIFICATION_FAILURE)) {
            LOG.e("Error from the redirect URL");
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_INCORRECT_PIN);
        } else if (str.contains(CVF_INVALID_PHONE_NUMBER)) {
            LOG.e("Invalid phone number from CVF");
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PHONE_INVALID);
        } else if (str.contains(CVF_INVALID_CODE)) {
            LOG.e("Incorrect code from CVF");
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_INCORRECT_PIN);
        } else if (!str.contains(CVF_PHONE_NUMBER_TAKEN)) {
        } else {
            LOG.e("Phone number taken from CVF");
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PHONE_TAKEN);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceLanguageCookie() {
        if (!this.revertOriginalLanguageCookie) {
            String cookieValue = getCookieValue(".amazon.com", LANGUAGE_COOKIE_NAME);
            if (LANGUAGE_COOKIE_VALUE.equals(cookieValue)) {
                return;
            }
            setCookieValue(".amazon.com", LANGUAGE_COOKIE_NAME, LANGUAGE_COOKIE_VALUE);
            this.originalLanguageCookie = cookieValue;
            this.revertOriginalLanguageCookie = true;
        }
    }

    @VisibleForTesting
    static void forceRefreshCookies(@Nullable String str) {
        String[] cookiesForDirectedId = CookieUtils.getCookiesForDirectedId(str, true);
        CookieManager cookieManager = CookieManager.getInstance();
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("https://www");
        outline1.append(CookieUtils.determineDomain());
        String sb = outline1.toString();
        for (String str2 : cookiesForDirectedId) {
            cookieManager.setCookie(sb, str2);
        }
    }

    private static String getCookieValue(@NonNull String str, @NonNull String str2) {
        String str3;
        String[] split;
        String cookie = CookieManager.getInstance().getCookie(str);
        if (!TextUtils.isEmpty(cookie)) {
            for (String str4 : cookie.split(";")) {
                int indexOf = str4.indexOf(Config.Compare.EQUAL_TO);
                if (indexOf != -1 && str2.equalsIgnoreCase(str4.substring(0, indexOf).trim())) {
                    str3 = str4.substring(indexOf + 1).trim();
                    break;
                }
            }
        }
        str3 = null;
        LOG.d(String.format("Cookie %s for domain %s: %s", str2, str, str3));
        return str3;
    }

    private Bundle getWebViewState(@Nullable Bundle bundle) {
        if (bundle != null) {
            return bundle;
        }
        if (!this.webViewState.isEmpty()) {
            return this.webViewState;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoadingIndicator() {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.progressDialog = null;
        }
    }

    private void launchCVFWidget() {
        new AsyncTask<Object, Void, Result<String, VerificationFailure>>() { // from class: com.amazon.deecomms.oobe.fragments.CVFFragment.3
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.AsyncTask
            @NonNull
            /* renamed from: doInBackground */
            public Result<String, VerificationFailure> mo3823doInBackground(Object... objArr) {
                String str;
                try {
                    try {
                        CVFFragment.forceRefreshCookies(CVFFragment.this.user.directedId);
                        IHttpClient.Request request = new ACMSClient(MetricKeys.OP_CVF_URL_FOR_OOBE).request(AppUrl.OOBE_CVF);
                        str = request.getRequestId();
                        try {
                            IHttpClient.Response mo3640execute = request.authenticated(CVFFragment.this.user.directedId).get().mo3640execute();
                            String body = mo3640execute.getBody();
                            if (body != null && !TextUtils.isEmpty(body)) {
                                CVFFragment.this.forceLanguageCookie();
                                return Result.value(body);
                            }
                            CVFFragment.LOG.e("CVF endpoint returned by ACMS was null!");
                            return Result.error(new VerificationFailure(CVFResult.SERVICE_RESPONSE_MISSING_INFO, AlertSource.newRequestSource(mo3640execute)));
                        } catch (Exception e) {
                            e = e;
                            CVFFragment.LOG.e("Unknown exception occurred while launching CVF", e);
                            return Result.error(new VerificationFailure(CVFResult.UNKNOWN, AlertSource.newRequestSource(str)));
                        }
                    } catch (ServiceException e2) {
                        CVFFragment.LOG.e("Failed to fetch the CVF widget endpoint from ACMS", e2);
                        return Result.error(new VerificationFailure(CVFResult.ACMS_ERROR, e2.getRequestId(), e2.getClientId(), AlertSource.newRequestSource(e2)));
                    }
                } catch (Exception e3) {
                    e = e3;
                    str = null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(@NonNull Result<String, VerificationFailure> result) {
                if (result.hasValue()) {
                    CVFFragment.access$500(CVFFragment.this, result.getValue());
                    return;
                }
                FragmentActivity activity = CVFFragment.this.getActivity();
                VerificationFailure error = result.getError();
                OOBEActivity oOBEActivity = activity != null ? (OOBEActivity) activity : null;
                if (oOBEActivity == null || oOBEActivity.isFinishing()) {
                    CommsLogger commsLogger = CVFFragment.LOG;
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("Received CVF failure ");
                    outline1.append(error.getErrorClass().toString());
                    commsLogger.e(outline1.toString());
                    if (error.getErrorClass() == CVFResult.NETWORK_DISCONNECTED) {
                        MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_OFFLINE, MetricKeys.SCREEN_NAME_OOBE, error.getAlertSource());
                        return;
                    } else {
                        MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_PHONE_INVALID, MetricKeys.SCREEN_NAME_OOBE, error.getAlertSource());
                        return;
                    }
                }
                oOBEActivity.dismissProgressDialog(CVFFragment.this.progressDialog);
                CVFFragment.this.receiver.onVerificationFailure(error);
            }
        }.execute(new Object[0]);
    }

    @UiThread
    private void loadEndpointInWebView(String str) {
        WebView webView = this.webView;
        if (webView != null) {
            webView.loadUrl(str);
        }
    }

    private static void removeCookieValue(@NonNull String str, @NonNull String str2) {
        CookieManager.getInstance().setCookie(str, String.format("%s=; expires=%s", str2, LANGUAGE_COOKIE_EXPIRATION));
        LOG.d(String.format("Removing cookie %s value for domain %s", str2, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreLanguageCookie() {
        if (this.revertOriginalLanguageCookie) {
            String str = this.originalLanguageCookie;
            if (str != null) {
                setCookieValue(".amazon.com", LANGUAGE_COOKIE_NAME, str);
            } else {
                removeCookieValue(".amazon.com", LANGUAGE_COOKIE_NAME);
            }
            this.originalLanguageCookie = null;
            this.revertOriginalLanguageCookie = false;
        }
    }

    private static void setCookieValue(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        CookieManager.getInstance().setCookie(str, String.format("%s=%s", str2, str3));
        LOG.d(String.format("Setting cookie %s value for domain %s: %s", str2, str, str3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadingIndicator() {
        if (this.progressDialog == null) {
            this.progressDialog = ((OOBEActivity) getActivity()).newProgressDialog(getActivity().getResources().getString(R.string.oobe_loading));
            this.progressDialog.show();
        }
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void loadState(@NonNull Bundle bundle) {
        this.webViewState.clear();
        if (System.currentTimeMillis() > bundle.getLong(KEY_TIMESTAMP, 0L) + WEBVIEW_STATE_MAX_AGE) {
            LOG.w("Ignoring stale saved state");
            return;
        }
        Bundle bundle2 = bundle.getBundle(KEY_WEBVIEW_STATE);
        if (bundle2 == null) {
            return;
        }
        LOG.d("Saving web view state");
        this.webViewState.putAll(bundle2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CommsIdentityReceiver) {
            this.receiver = (CommsIdentityReceiver) activity;
        } else {
            LOG.e("CVFFragment expects the containing Activity to implement theCommsIdentityReceiver interface. Did you do that?");
        }
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate(bundle);
        this.user = ((OOBEActivity) getActivity()).getUser();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        WebView webView = this.webView;
        if (webView != null) {
            webView.destroy();
        }
        View inflate = layoutInflater.inflate(R.layout.oobe_cvf_main, viewGroup, false);
        this.progressDialog = ((OOBEActivity) getActivity()).newProgressDialog(getActivity().getResources().getString(R.string.oobe_loading));
        this.progressDialog.show();
        this.webView = (WebView) inflate.findViewById(R.id.cvf_webview);
        this.webView.setWebViewClient(new CVFWidgetWebViewClient(this.receiver, new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.CVFFragment.1
            @Override // java.lang.Runnable
            public void run() {
                CVFFragment.this.showLoadingIndicator();
            }
        }, new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.CVFFragment.2
            @Override // java.lang.Runnable
            public void run() {
                CVFFragment.this.hideLoadingIndicator();
            }
        }));
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(new CommsIdentityBridge(this.receiver), COMMS_IDENTITY_BRIDGE_IDENTIFIER);
        this.webView.setBackgroundColor(0);
        Bundle webViewState = getWebViewState(bundle);
        if (webViewState == null) {
            LOG.d("Launching widget");
            showLoadingIndicator();
            launchCVFWidget();
        } else {
            settings.setCacheMode(1);
            LOG.d("Restoring widget");
            this.webView.restoreState(webViewState);
        }
        this.webViewState.clear();
        if (getActivity() != null && !getActivity().isFinishing()) {
            ((OOBEActivity) getActivity()).showHeaderBar();
            ((OOBEActivity) getActivity()).hideBackButton();
            ((OOBEActivity) getActivity()).showSkipButton();
        }
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        WebView webView = this.webView;
        if (webView != null) {
            webView.destroy();
            this.webView = null;
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.webView.onPause();
        final boolean isEmpty = this.webViewState.isEmpty();
        new Thread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.CVFFragment.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (isEmpty) {
                        CVFFragment.forceRefreshCookies(CVFFragment.this.applicationManager.getAccountManager().getAccount());
                    }
                    CVFFragment.this.restoreLanguageCookie();
                } catch (Exception e) {
                    CVFFragment.LOG.e("Failed to reset cookies in CookieManager to primary account.", e);
                }
            }
        }).start();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        final boolean isEmpty = this.webViewState.isEmpty();
        new Thread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.CVFFragment.4
            @Override // java.lang.Runnable
            public void run() {
                if (isEmpty) {
                    CVFFragment.forceRefreshCookies(CVFFragment.this.user.directedId);
                }
                CVFFragment.this.forceLanguageCookie();
            }
        }).start();
        this.webView.onResume();
        this.enableLearnMore = false;
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.webView != null) {
            this.webViewState.clear();
            this.webView.saveState(this.webViewState);
            bundle.putAll(this.webViewState);
        }
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_MOBILE_VERIFICATION_START);
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void saveState(@NonNull Bundle bundle) {
        if (this.webView != null) {
            this.webViewState.clear();
            this.webView.saveState(this.webViewState);
            LOG.w("Saving web view state");
            bundle.putLong(KEY_TIMESTAMP, System.currentTimeMillis());
            bundle.putBundle(KEY_WEBVIEW_STATE, this.webViewState);
            return;
        }
        bundle.remove(KEY_TIMESTAMP);
        bundle.remove(KEY_WEBVIEW_STATE);
    }

    /* loaded from: classes12.dex */
    private class CVFWidgetWebViewClient extends WebViewClient {
        private final Runnable onWidgetLoaded;
        private final Runnable onWidgetLoading;
        private final CommsIdentityReceiver receiver;

        CVFWidgetWebViewClient(CommsIdentityReceiver commsIdentityReceiver, Runnable runnable, Runnable runnable2) {
            this.receiver = commsIdentityReceiver;
            this.onWidgetLoading = runnable;
            this.onWidgetLoaded = runnable2;
        }

        private void handleError(int i, CharSequence charSequence, String str) {
            this.onWidgetLoaded.run();
            CVFFragment.LOG.e(String.format("Aborting verification: The CVF widget failed to load %s in the WebView. Error %s: %s", str, Integer.valueOf(i), charSequence));
            if (!CommsDaggerWrapper.getComponent().getCommsConnectivityMonitor().isConnected()) {
                this.receiver.onVerificationFailure(new VerificationFailure(CVFResult.NETWORK_DISCONNECTED, AlertSource.newClassSource(CVFWidgetWebViewClient.class.getName())));
            } else {
                this.receiver.onVerificationFailure(new VerificationFailure(CVFResult.WEBVIEW_ERROR, AlertSource.newClassSource(CVFWidgetWebViewClient.class.getName())));
            }
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_CVF_FAILED_TO_LOAD);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            this.onWidgetLoaded.run();
            final CVFFragment cVFFragment = CVFFragment.this;
            webView.evaluateJavascript("document.body.innerHTML", new ValueCallback() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$CVFFragment$CVFWidgetWebViewClient$ZEkQpq7dioeJeqSa8igSmP0FzvM
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    CVFFragment.this.evaluateHtml((String) obj);
                }
            });
            if (str == null || !str.endsWith("#learnmore") || !CVFFragment.this.enableLearnMore) {
                CVFFragment.this.enableLearnMore = true;
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.FAQ_URL_KEY)));
            if (intent.resolveActivity(webView.getContext().getPackageManager()) == null) {
                return;
            }
            webView.getContext().startActivity(intent);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.onWidgetLoading.run();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            handleError(i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            handleError(webResourceError.getErrorCode(), webResourceError.getDescription(), webResourceRequest.getUrl().toString());
        }
    }
}
