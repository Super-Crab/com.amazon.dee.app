package com.amazon.alexa.handsfree.uservoicerecognition.ui.primer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.R;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.ProgressDialogFragment;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.marketplace.CountryCode;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public class EnrollmentTermsFragment extends Fragment {
    public static final String EXTRA_URL = "URL";
    public static final String LEARN_MORE_URL = "LEARN_MORE_URL";
    public static final String PRIVACY_URL = "PRIVACY_URL";
    private static final int PROGRESS_DIALOG_REQUEST_CODE = 1;
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    public static final String TERMS_URL = "TERMS_URL";
    private final IdentityService mIdentityService = (IdentityService) GeneratedOutlineSupport1.outline20(IdentityService.class);
    private static final String TAG = EnrollmentTermsFragment.class.getSimpleName();
    private static Map<String, String> termsUrlMap = new HashMap();
    private static Map<String, String> learnMoreUrlMap = new HashMap();
    private static Map<String, String> alexaPrivacyUrlMap = new HashMap();

    static {
        termsUrlMap.put("AE", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("AU", "https://www.amazon.com.au/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("BR", "https://www.amazon.com.br/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("CA", "https://www.amazon.ca/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("CN", "https://www.amazon.cn/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("DE", "https://www.amazon.de/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("ES", "https://www.amazon.es/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("FR", "https://www.amazon.fr/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("GB", "https://www.amazon.co.uk/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("ID", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("IN", "https://www.amazon.in/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("IT", "https://www.amazon.it/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("JP", "https://www.amazon.co.jp/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("MX", "https://www.amazon.com.mx/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("NL", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("RU", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("SA", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("TR", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        termsUrlMap.put("US", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201809740");
        learnMoreUrlMap.put("AE", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("AU", "https://www.amazon.com.au/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("BR", "https://www.amazon.com.br/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("CA", "https://www.amazon.ca/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("CN", "https://www.amazon.cn/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("DE", "https://www.amazon.de/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("ES", "https://www.amazon.es/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("FR", "https://www.amazon.fr/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("GB", "https://www.amazon.co.uk/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("ID", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("IN", "https://www.amazon.in/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("IT", "https://www.amazon.it/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("JP", "https://www.amazon.co.jp/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("MX", "https://www.amazon.com.mx/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("NL", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("RU", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("SA", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("TR", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        learnMoreUrlMap.put("US", "https://www.amazon.com/gp/help/customer/display.html?nodeId=201602230");
        alexaPrivacyUrlMap.put("AE", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("AU", "https://www.amazon.com.au/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("BR", "https://www.amazon.com.br/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("CA", "https://www.amazon.ca/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("CN", "https://www.amazon.cn/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("DE", "https://www.amazon.de/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("ES", "https://www.amazon.es/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("FR", "https://www.amazon.fr/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("GB", "https://www.amazon.co.uk/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("ID", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("IN", "https://www.amazon.in/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("IT", "https://www.amazon.it/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("JP", "https://www.amazon.co.jp/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("MX", "https://www.amazon.com.mx/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("NL", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("RU", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("SA", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("TR", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
        alexaPrivacyUrlMap.put("US", "https://www.amazon.com/gp/help/customer/display.html?nodeId=GVP69FUJ48X9DK8V");
    }

    private String getUrl() {
        Map<String, String> map;
        String countryCode = this.mIdentityService.getUser(TAG).getMarketplace().getCountryCode().toString();
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null && "LEARN_MORE_URL".equals(extras.getString("URL"))) {
            map = learnMoreUrlMap;
        } else if (extras != null && "PRIVACY_URL".equals(extras.getString("URL"))) {
            map = alexaPrivacyUrlMap;
        } else {
            map = termsUrlMap;
        }
        String str = map.get(countryCode);
        return str != null ? str : map.get(CountryCode.US.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        Log.d(TAG, "inside hideLoading method");
        if (!isAdded()) {
            Log.e(TAG, "Fragment is not attached to an activity");
            return;
        }
        Fragment findFragmentByTag = getActivity().getSupportFragmentManager().findFragmentByTag("progress_dialog");
        if (findFragmentByTag == null) {
            return;
        }
        getActivity().getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commitAllowingStateLoss();
    }

    private void initializeWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() { // from class: com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentTermsFragment.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                Log.d(EnrollmentTermsFragment.TAG, "Inside webview onPageFinished");
                EnrollmentTermsFragment.this.hideLoading();
                super.onPageFinished(webView2, str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                GeneratedOutlineSupport1.outline167("Inside webview onPageStarted ", str, EnrollmentTermsFragment.TAG);
                EnrollmentTermsFragment.this.showLoading();
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, WebResourceRequest webResourceRequest) {
                String uri = webResourceRequest.getUrl().toString();
                String str = EnrollmentTermsFragment.TAG;
                Log.i(str, "Inside shouldOverrideUrlLoading with url: " + uri);
                webView2.loadUrl(uri);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoading() {
        Log.d(TAG, "inside showLoading method");
        if (!isAdded()) {
            Log.e(TAG, "Fragment is not attached to an activity");
        } else if (getActivity().getSupportFragmentManager().findFragmentByTag("progress_dialog") != null) {
        } else {
            ProgressDialogFragment createInstance = ProgressDialogFragment.createInstance(getString(R.string.loading_title), getString(R.string.loading_msg));
            createInstance.setTargetFragment(this, 1);
            createInstance.show(getActivity().getSupportFragmentManager(), "progress_dialog");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.activity_enrollment_terms, viewGroup, false);
        WebView webView = (WebView) inflate.findViewById(R.id.terms_webview);
        initializeWebView(webView);
        webView.loadUrl(getUrl());
        showLoading();
        return inflate;
    }
}
