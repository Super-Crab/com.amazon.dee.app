package com.amazon.deecomms.oobe.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import java.text.MessageFormat;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class PhoneVerifiedFragment extends MainOOBEFragment {
    private final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, UserSelectionFragment.class);
    private ProgressDialog progressDialog;
    private Person user;

    /* loaded from: classes12.dex */
    private class ProvisionCommsIdCallback implements IHttpClient.Callback {
        private ProvisionCommsIdCallback() {
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onFailure(IHttpClient.Call call, ServiceException serviceException) {
            if (PhoneVerifiedFragment.this.isActivityAvailable()) {
                OOBEActivity oOBEActivity = (OOBEActivity) PhoneVerifiedFragment.this.getActivity();
                oOBEActivity.dismissProgressDialog(PhoneVerifiedFragment.this.progressDialog);
                PhoneVerifiedFragment.this.LOG.e("Error occurred when trying to provision account with commsId", serviceException);
                oOBEActivity.onError(serviceException, MetricKeys.SCREEN_NAME_OOBE_MOBILE_VERIFICATION, AlertSource.newRequestSource(serviceException));
            }
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onResult(IHttpClient.Call call, IHttpClient.Response response) {
            if (PhoneVerifiedFragment.this.isActivityAvailable()) {
                final OOBEActivity oOBEActivity = (OOBEActivity) PhoneVerifiedFragment.this.getActivity();
                oOBEActivity.dismissProgressDialog(PhoneVerifiedFragment.this.progressDialog);
                try {
                    String string = new JSONObject(response.getBody()).getString("commsId");
                    CommsLogger commsLogger = PhoneVerifiedFragment.this.LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Got commsId for user ");
                    sb.append(PhoneVerifiedFragment.this.LOG.sensitive(PhoneVerifiedFragment.this.user.commsId));
                    commsLogger.d(sb.toString());
                    oOBEActivity.onProvisioned(string);
                    oOBEActivity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.PhoneVerifiedFragment.ProvisionCommsIdCallback.1
                        @Override // java.lang.Runnable
                        public void run() {
                            oOBEActivity.nextFragment();
                        }
                    });
                } catch (JSONException e) {
                    PhoneVerifiedFragment.this.LOG.e("Malformed JSON returned from creating new account");
                    oOBEActivity.onError(e, MetricKeys.SCREEN_NAME_OOBE_MOBILE_VERIFICATION, AlertSource.newRequestSource(response));
                }
            }
        }

        /* synthetic */ ProvisionCommsIdCallback(AnonymousClass1 anonymousClass1) {
        }
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        if (isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_oobe_phone_verified, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.oobe_phone_verified, viewGroup, false);
        }
        Context applicationContext = getActivity().getApplicationContext();
        this.themingUpdateUtil.applyBackgroundColorToView(inflate, applicationContext, R.attr.mosaicBackground);
        this.user = ((OOBEActivity) getActivity()).getUser();
        TextView textView = (TextView) inflate.findViewById(R.id.oobe_phone_verified_title);
        this.themingUpdateUtil.applyColorToTextView(textView, applicationContext, R.attr.mosaicNeutral10);
        if (!Utils.isNullOrEmpty(this.user.phoneNumber)) {
            textView.setText(String.format(getString(R.string.oobe_phone_verified_title), Utils.formatPhoneNumber(this.user.phoneNumber)));
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.oobe_phone_verified_content);
        this.themingUpdateUtil.applyColorToTextView(textView2, applicationContext, R.attr.mosaicNeutral10);
        Spannable spannable = (Spannable) Html.fromHtml(getString(R.string.oobe_phone_verified_content, CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.TERMS_OF_USE_URL_KEY)));
        removeUnderlinesAndSetColor(spannable);
        textView2.setText(spannable);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        Button button = (Button) inflate.findViewById(R.id.oobe_phone_verified_continue_btn);
        this.themingUpdateUtil.applyBackgroundToButton(button, applicationContext, R.drawable.mosaic_button_primary);
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.PhoneVerifiedFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new Thread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.PhoneVerifiedFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        new ACMSClient(MetricKeys.OP_CREATE_ACCOUNT_FROM_OOBE).request(MessageFormat.format(AppUrl.OOBE_PROVISION_COMMSID, PhoneVerifiedFragment.this.user.directedId)).authenticated(PhoneVerifiedFragment.this.user.directedId).postJson(null).enqueue(new ProvisionCommsIdCallback(null));
                    }
                }).start();
                PhoneVerifiedFragment phoneVerifiedFragment = PhoneVerifiedFragment.this;
                phoneVerifiedFragment.progressDialog = ((OOBEActivity) phoneVerifiedFragment.getActivity()).newProgressDialog(PhoneVerifiedFragment.this.getActivity().getResources().getString(R.string.oobe_provision_progress));
                PhoneVerifiedFragment.this.progressDialog.show();
            }
        });
        if (getActivity() != null && !getActivity().isFinishing()) {
            ((OOBEActivity) getActivity()).showHeaderBar();
            ((OOBEActivity) getActivity()).hideBackButton();
            ((OOBEActivity) getActivity()).showSkipButton();
        }
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_PHONE_VERIFIED_START);
    }
}
