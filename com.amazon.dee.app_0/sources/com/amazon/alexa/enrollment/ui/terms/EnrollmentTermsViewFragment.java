package com.amazon.alexa.enrollment.ui.terms;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.enrollment.R;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.module.library.Injector;
import com.amazon.alexa.enrollment.ui.AbstractEnrollmentViewFragment;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class EnrollmentTermsViewFragment extends AbstractEnrollmentViewFragment {
    public static final String DEFAULT_ENDPOINT = "https://www.amazon.com";
    public static final String EXTRA_URL = "URL";
    public static final String LEARN_MORE_URL = "LEARN_MORE_URL";
    public static final String LEARN_MORE_URL_SUFFIX = "/gp/help/customer/display.html?nodeId=201602230";
    public static final String PRIVACY_URL = "PRIVACY_URL";
    public static final String PRIVACY_URL_SUFFIX = "/gp/help/customer/display.html?nodeId=GA7E98TJFEJLYSFR";
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentTermsViewFragment.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    public static final String TERMS_URL = "TERMS_URL";
    public static final String TERMS_URL_SUFFIX = "/gp/help/customer/display.html?nodeId=201809740";
    @Inject
    EnrollmentMetricsRecorder enrollmentMetricsRecorder;
    @Inject
    EnvironmentService environmentService;

    public EnrollmentTermsViewFragment() {
    }

    public static EnrollmentTermsViewFragment createInstance() {
        return new EnrollmentTermsViewFragment();
    }

    private String getUrl() {
        String str;
        Bundle extrasFromIntent = getExtrasFromIntent();
        if (extrasFromIntent != null && "LEARN_MORE_URL".equals(extrasFromIntent.getString("URL"))) {
            String.format("%s was clicked", "LEARN_MORE_URL");
            str = LEARN_MORE_URL_SUFFIX;
        } else if (extrasFromIntent != null && "PRIVACY_URL".equals(extrasFromIntent.getString("URL"))) {
            String.format("%s was clicked", "PRIVACY_URL");
            str = PRIVACY_URL_SUFFIX;
        } else {
            String.format("%s was clicked", "TERMS_URL");
            str = TERMS_URL_SUFFIX;
        }
        try {
            String retailEndpoint = this.environmentService.getRetailEndpoint();
            String str2 = "Retrieved retail endpoint is: " + retailEndpoint;
            return retailEndpoint + str;
        } catch (Exception e) {
            String str3 = "Unable to fetch the retail endpoint " + e;
            return DEFAULT_ENDPOINT + str;
        }
    }

    private void initializeWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() { // from class: com.amazon.alexa.enrollment.ui.terms.EnrollmentTermsViewFragment.1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                String unused = EnrollmentTermsViewFragment.TAG;
                EnrollmentTermsViewFragment.this.hideLoading(null);
                super.onPageFinished(webView2, str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                String unused = EnrollmentTermsViewFragment.TAG;
                GeneratedOutlineSupport1.outline158("Inside webview onPageStarted ", str);
                EnrollmentTermsViewFragment.this.showLoading(null);
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, WebResourceRequest webResourceRequest) {
                String uri = webResourceRequest.getUrl().toString();
                String str = EnrollmentTermsViewFragment.TAG;
                Log.i(str, "Inside shouldOverrideUrlLoading with url: " + uri);
                webView2.loadUrl(uri);
                return true;
            }
        });
    }

    @VisibleForTesting
    Bundle getExtrasFromIntent() {
        return getActivity().getIntent().getExtras();
    }

    @VisibleForTesting
    void injectDependency() {
        Injector.inject(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        injectDependency();
        this.enrollmentMetricsRecorder.initializeMetricsContext(getEnrollmentContext());
        View inflate = layoutInflater.inflate(R.layout.activity_enrollment_terms, viewGroup, false);
        WebView webView = (WebView) inflate.findViewById(R.id.terms_webview);
        initializeWebView(webView);
        webView.loadUrl(getUrl());
        showLoading(null);
        return inflate;
    }

    @VisibleForTesting
    EnrollmentTermsViewFragment(EnrollmentMetricsRecorder enrollmentMetricsRecorder, EnvironmentService environmentService) {
        this.environmentService = environmentService;
        this.enrollmentMetricsRecorder = enrollmentMetricsRecorder;
    }
}
