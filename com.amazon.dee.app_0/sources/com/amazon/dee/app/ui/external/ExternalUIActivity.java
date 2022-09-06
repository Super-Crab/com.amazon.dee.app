package com.amazon.dee.app.ui.external;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.ExternalUiBinding;
import com.amazon.dee.app.dependencies.ExternalUIComponent;
import com.amazon.dee.app.dependencies.ExternalUIModule;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.ui.web.ExternalUILauncherBridge;
import com.amazon.dee.app.ui.web.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class ExternalUIActivity extends AppCompatActivity implements ReloadableView, ExternalUIHandler {
    public static final String BROADCAST_CLOSE = "com.amazon.dee.webapp.activity.ExternalUIActivity.close";
    public static final String EXTERNAL_URL_LOAD_ERROR_CODE = "externalUrlLoadErrorCode";
    public static final String EXTERNAL_URL_LOAD_RESULT_DESCRIPTION = "externalUrlLoadResultDescription";
    int activityResultCode;
    Intent activityResultIntent;
    ExternalUiBinding binding;
    ExternalUIComponent component;
    BroadcastReceiver mBroadcastReceiver;
    ExternalUIWebViewManager mExternalUIWebViewManager;
    WebView mWebView;
    ViewGroup mWebViewContainer;
    ExternalUIViewModel viewModel;
    private static final Map<String, Integer> WEBVIEW_TITLES = ImmutableMap.builder().mo7828put(WebConstants.ExternalURL.BLUEPRINTS_URL, Integer.valueOf((int) R.string.alexa_blueprints)).mo7826build();
    private static final String TAG = ExternalUIActivity.class.getName();

    private ViewGroup getWebViewContainer() {
        if (this.mWebViewContainer == null) {
            this.mWebViewContainer = (ViewGroup) findViewById(R.id.external_ui_content);
        }
        return this.mWebViewContainer;
    }

    private void loadWebView(String str) {
        this.mExternalUIWebViewManager.loadUrl(str);
    }

    public void exitActivity(String str) {
        if (str != null) {
            this.activityResultIntent.putExtra("RETURN_URL", str);
        }
        exitActivity();
    }

    public ExternalUIComponent getComponent() {
        if (this.component == null) {
            this.component = ((AlexaApplication) getApplication()).getComponent().plus(new ExternalUIModule(this));
        }
        return this.component;
    }

    public final WebView getWebView() {
        if (this.mWebView == null) {
            this.mWebView = new WebView(this);
        }
        return this.mWebView;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        if (getWebView().canGoBack()) {
            getWebView().goBack();
        } else {
            exitActivity();
        }
    }

    @Override // com.amazon.dee.app.ui.external.ExternalUIHandler
    public void onCancelClicked() {
        exitActivity();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        this.viewModel = getComponent().inject(new ExternalUIViewModel());
        this.viewModel.create(bundle == null ? null : bundle.getBundle("viewModel"));
        this.binding = (ExternalUiBinding) DataBindingUtil.setContentView(this, R.layout.external_ui);
        this.binding.setHandler(this);
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.dee.app.ui.external.ExternalUIActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                ExternalUIActivity.this.exitActivity();
            }
        };
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
            try {
                ((TextView) findViewById(R.id.external_ui_title)).setText(getResources().getString(intent.getIntExtra("WEBVIEW_TITLE_ID_KEY", -1)));
            } catch (Resources.NotFoundException unused) {
                Log.e(TAG, "WebView header title resource not found");
            }
            if (WEBVIEW_TITLES.get(stringExtra) != null) {
                ((TextView) findViewById(R.id.external_ui_title)).setText(WEBVIEW_TITLES.get(stringExtra).intValue());
            }
            String stringExtra2 = intent.getStringExtra("BRIDGE_ACTION_KEY");
            if (ExternalUILauncherBridge.BridgeAction.THIRD_PARTY.toString().equals(stringExtra2)) {
                this.mExternalUIWebViewManager = new ThirdPartyUIWebViewManager(this, getWebView(), intent.getStringArrayListExtra(WebConstants.ExternalUIConstants.HOST_ALLOWLIST_KEY), this.viewModel);
            } else if (stringExtra2.equals(ExternalUILauncherBridge.BridgeAction.EXTERNAL.toString())) {
                String stringExtra3 = intent.getStringExtra("URL_REGEX_KEY");
                boolean booleanExtra = intent.getBooleanExtra("IS_DOM_STORAGE_ENABLED", false);
                boolean booleanExtra2 = intent.getBooleanExtra("CONTINUE_ON_NON_FATAL_ERRORS", false);
                this.mExternalUIWebViewManager = new ExternalUIWebViewManager(this, getWebView(), stringExtra3, this.viewModel);
                this.mExternalUIWebViewManager.setDOMStorageEnabled(booleanExtra);
                this.mExternalUIWebViewManager.setContinueOnNonFatalErrors(booleanExtra2);
            } else if (ExternalUILauncherBridge.BridgeAction.THIRD_PARTY_IN_APP.toString().equals(stringExtra2)) {
                this.mExternalUIWebViewManager = new ThirdPartyUIWebViewManager(this, getWebView(), intent.getStringArrayListExtra(WebConstants.ExternalUIConstants.HOST_ALLOWLIST_KEY), this.viewModel);
            }
            loadWebView(stringExtra);
        }
        getWebViewContainer().addView(getWebView(), new ViewGroup.LayoutParams(-1, -1));
        this.activityResultCode = -1;
        this.activityResultIntent = new Intent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.mBroadcastReceiver);
        getWebView().onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        registerReceiver(this.mBroadcastReceiver, GeneratedOutlineSupport1.outline10(BROADCAST_CLOSE));
        getWebView().onResume();
    }

    @Override // com.amazon.dee.app.ui.external.ReloadableView
    public final void reloadView() {
        this.mExternalUIWebViewManager.reload();
    }

    public void setActivityResultData(Bundle bundle) {
        this.activityResultIntent.putExtras(bundle);
    }

    public final void setWebView(WebView webView) {
        this.mWebView = webView;
    }

    public void tellUserToConnectToNetwork() {
        if (isFinishing()) {
            return;
        }
        new AlertDialog.Builder(this).setMessage(R.string.main_no_network_message).setNegativeButton(R.string.main_connection_timed_out_message, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.ui.external.ExternalUIActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ExternalUIActivity.this.exitActivity();
            }
        }).show();
    }

    public void exitActivity() {
        setResult(this.activityResultCode, this.activityResultIntent);
        finish();
    }
}
