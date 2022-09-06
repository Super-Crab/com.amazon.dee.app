package com.amazon.deecomms.oobe.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.identity.api.ProfileAttributes;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.LanguageUtil;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.OOBEStateMachine;
import com.amazon.deecomms.oobe.PCAProfile;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.LinkSpannable;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class NameConfirmationFragment extends MainOOBEFragment {
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, NameConfirmationFragment.class);
    private static final String PHONETIC_FIRST_NAME = "phoneticFirstName";
    private static final String PHONETIC_LAST_NAME = "phoneticLastName";
    private Button continueBtn;
    private EditText firstName;
    private TextView firstNameErrorView;
    private TextView firstNameLabel;
    private boolean isNewUser;
    private EditText lastName;
    private TextView lastNameErrorView;
    private TextView lastNameLabel;
    private TextView mTvOobeTermsLink;
    private OOBEStateMachine.STATE oobeState;
    private EditText phoneticFirstName;
    private TextView phoneticFirstNameErrorView;
    private TextView phoneticFirstNameLabel;
    private EditText phoneticLastName;
    private TextView phoneticLastNameErrorView;
    private TextView phoneticLastNameLabel;
    private ProgressDialog progressDialog;
    private int sFirstNameMaxLength;
    private int sLastNameMaxLength;
    private int sPhoneticFirstNameMaxLength;
    private int sPhoneticLastNameMaxLength;
    private PCAProfile selectedPCAProfile;
    private Person selectedUser;
    private String userPFM;

    /* renamed from: com.amazon.deecomms.oobe.fragments.NameConfirmationFragment$3  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$oobe$fragments$NameConfirmationFragment$FieldError = new int[FieldError.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$oobe$fragments$NameConfirmationFragment$FieldError[FieldError.EMPTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$fragments$NameConfirmationFragment$FieldError[FieldError.INVALID_CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$fragments$NameConfirmationFragment$FieldError[FieldError.EXCEEDS_MAX_LENGTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    private class CreateAccountCallback implements IHttpClient.Callback {
        private static final int MAX_ACCOUNTS_HTTP_CODE = 409;

        private CreateAccountCallback() {
        }

        private void showMaxAccountsErrorDialog(@NonNull final AlertSource alertSource) {
            final FragmentActivity activity = NameConfirmationFragment.this.getActivity();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.CreateAccountCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_OOBE, alertSource);
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage(R.string.oobe_max_accounts_error);
                        builder.setPositiveButton(R.string.acknowledge, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.CreateAccountCallback.1.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NameConfirmationFragment.this.goBack();
                            }
                        });
                        AlertDialog create = builder.create();
                        create.setCanceledOnTouchOutside(false);
                        create.setCancelable(false);
                        create.show();
                    }
                });
            }
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onFailure(IHttpClient.Call call, ServiceException serviceException) {
            NameConfirmationFragment.LOG.w("[createNewAccount] API call failed");
            ((OOBEActivity) NameConfirmationFragment.this.getActivity()).dismissProgressDialog(NameConfirmationFragment.this.progressDialog);
            if (serviceException.getHttpResponseCode() == null || 409 != serviceException.getHttpResponseCode().intValue()) {
                if (NameConfirmationFragment.this.isNewUser) {
                    ((OOBEActivity) NameConfirmationFragment.this.getActivity()).onError(serviceException, MetricKeys.SCREEN_NAME_OOBE_NAME_CONFIRMATION, AlertSource.newRequestSource(serviceException));
                    NameConfirmationFragment.LOG.e("[createNewAccount] Unable to create user account", serviceException);
                    return;
                }
                ((OOBEActivity) NameConfirmationFragment.this.getActivity()).onError(serviceException, MetricKeys.SCREEN_NAME_OOBE_NAME_CONFIRMATION, AlertSource.newRequestSource(serviceException));
                NameConfirmationFragment.LOG.e("[createNewAccount] Unable to update user account ", serviceException);
                return;
            }
            showMaxAccountsErrorDialog(AlertSource.newRequestSource(serviceException));
            NameConfirmationFragment.LOG.i("[createNewAccount] ACMS responded with code 409, too many profiles. Going back to user selection screen");
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onResult(IHttpClient.Call call, IHttpClient.Response response) {
            NameConfirmationFragment.LOG.i("[createNewAccount] API call successful");
            OOBEActivity oOBEActivity = (OOBEActivity) NameConfirmationFragment.this.getActivity();
            if (oOBEActivity == null || oOBEActivity.isFinishing()) {
                NameConfirmationFragment.LOG.w("[createNewAccount] Activity is no longer available. Do not execute remaining code.");
                return;
            }
            oOBEActivity.dismissProgressDialog(NameConfirmationFragment.this.progressDialog);
            try {
                JSONObject jSONObject = new JSONObject(response.getBody());
                if (NameConfirmationFragment.this.isNewUser) {
                    NameConfirmationFragment.this.selectedUser.directedId = jSONObject.getString("directedId");
                }
                NameConfirmationFragment.this.selectedUser.commsId = jSONObject.getString("commsId");
                if (jSONObject.has("autoprovisionOutcome") && "SUCCESS".equalsIgnoreCase(jSONObject.getString("autoprovisionOutcome"))) {
                    NameConfirmationFragment.this.selectedUser.commsProvisionStatus = CommsProvisionStatus.AUTO_PROVISIONED;
                    oOBEActivity.persistProvisionStatus(NameConfirmationFragment.this.selectedUser.commsProvisionStatus);
                    CommsLogger commsLogger = NameConfirmationFragment.LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[createNewAccount][OOBE-AUTOPROVISION] VerifyName created auto provisioned user account with commsId ");
                    sb.append(NameConfirmationFragment.LOG.sensitive(NameConfirmationFragment.this.selectedUser.commsId));
                    commsLogger.i(sb.toString());
                    MetricsHelper.recordSingleOccurrence(CounterMetric.generateOperational(MetricKeys.OOBE_AUTOPROVISIONED_SUCCESS));
                }
                oOBEActivity.saveUser(NameConfirmationFragment.this.selectedUser);
                CommsLogger commsLogger2 = NameConfirmationFragment.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("[createNewAccount] Created/updated user account with directedId ");
                outline1.append(NameConfirmationFragment.LOG.sensitive(NameConfirmationFragment.this.selectedUser.directedId));
                commsLogger2.i(outline1.toString());
                oOBEActivity.nextFragment();
            } catch (JSONException e) {
                oOBEActivity.onError(e, MetricKeys.SCREEN_NAME_OOBE_NAME_CONFIRMATION, AlertSource.newRequestSource(response));
                NameConfirmationFragment.LOG.e("[createNewAccount] Malformed JSON returned from creating account", e);
            }
        }

        /* synthetic */ CreateAccountCallback(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum FieldError {
        EMPTY,
        INVALID_CHAR,
        EXCEEDS_MAX_LENGTH
    }

    /* loaded from: classes12.dex */
    private class NameConfirmationTextWatcher implements TextWatcher {
        private String nameField;

        public NameConfirmationTextWatcher(String str) {
            this.nameField = str;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0088, code lost:
            if (r2.equals("firstName") != false) goto L15;
         */
        @Override // android.text.TextWatcher
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onTextChanged(java.lang.CharSequence r9, int r10, int r11, int r12) {
            /*
                r8 = this;
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r9 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.EditText r10 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$000(r9)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$100(r11)
                r12 = 0
                com.google.common.base.Optional r9 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$200(r9, r10, r11, r12)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r10 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.EditText r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$300(r10)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r0 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r0 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$400(r0)
                com.google.common.base.Optional r10 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$200(r10, r11, r0, r12)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                java.lang.String r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$500(r11)
                java.lang.String r0 = "JP"
                boolean r11 = r11.equals(r0)
                r0 = 1
                if (r11 == 0) goto L51
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.EditText r1 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$600(r11)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$700(r2)
                com.google.common.base.Optional r11 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$200(r11, r1, r2, r0)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r1 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.EditText r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$800(r1)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r3 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r3 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$900(r3)
                com.google.common.base.Optional r1 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$200(r1, r2, r3, r0)
                goto L59
            L51:
                com.google.common.base.Optional r11 = com.google.common.base.Optional.absent()
                com.google.common.base.Optional r1 = com.google.common.base.Optional.absent()
            L59:
                java.lang.String r2 = r8.nameField
                r3 = -1
                int r4 = r2.hashCode()
                r5 = -1866166053(0xffffffff90c490db, float:-7.7531554E-29)
                r6 = 3
                r7 = 2
                if (r4 == r5) goto L95
                r5 = -1459599807(0xffffffffa9004641, float:-2.8482645E-14)
                if (r4 == r5) goto L8b
                r5 = 132835675(0x7eae95b, float:3.534556E-34)
                if (r4 == r5) goto L82
                r12 = 1385410241(0x5293aec1, float:3.17146038E11)
                if (r4 == r12) goto L77
                goto La0
            L77:
                java.lang.String r12 = "phoneticLastName"
                boolean r12 = r2.equals(r12)
                if (r12 == 0) goto La0
                r12 = r6
                goto La1
            L82:
                java.lang.String r4 = "firstName"
                boolean r2 = r2.equals(r4)
                if (r2 == 0) goto La0
                goto La1
            L8b:
                java.lang.String r12 = "lastName"
                boolean r12 = r2.equals(r12)
                if (r12 == 0) goto La0
                r12 = r0
                goto La1
            L95:
                java.lang.String r12 = "phoneticFirstName"
                boolean r12 = r2.equals(r12)
                if (r12 == 0) goto La0
                r12 = r7
                goto La1
            La0:
                r12 = r3
            La1:
                if (r12 == 0) goto Le0
                if (r12 == r0) goto Lce
                if (r12 == r7) goto Lbc
                if (r12 == r6) goto Laa
                goto Lf1
            Laa:
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r12 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r0 = com.amazon.deecomms.R.string.oobe_phonetic_last_name_error
                java.lang.CharSequence r0 = r12.getText(r0)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.TextView r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1400(r2)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1100(r12, r1, r0, r2)
                goto Lf1
            Lbc:
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r12 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r0 = com.amazon.deecomms.R.string.oobe_phonetic_first_name_error
                java.lang.CharSequence r0 = r12.getText(r0)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.TextView r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1300(r2)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1100(r12, r11, r0, r2)
                goto Lf1
            Lce:
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r12 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r0 = com.amazon.deecomms.R.string.oobe_last_name_error
                java.lang.CharSequence r0 = r12.getText(r0)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.TextView r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1200(r2)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1100(r12, r10, r0, r2)
                goto Lf1
            Le0:
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r12 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                int r0 = com.amazon.deecomms.R.string.oobe_first_name_error
                java.lang.CharSequence r0 = r12.getText(r0)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                android.widget.TextView r2 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1000(r2)
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$1100(r12, r9, r0, r2)
            Lf1:
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment r12 = com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.this
                com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.access$2000(r12, r9, r10, r11, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.NameConfirmationTextWatcher.onTextChanged(java.lang.CharSequence, int, int, int):void");
        }
    }

    /* loaded from: classes12.dex */
    private class TextOnFocusChangListener implements View.OnFocusChangeListener {
        private final EditText editName;
        private final String hint;
        private final TextView label;

        public TextOnFocusChangListener(EditText editText, TextView textView, String str) {
            this.editName = editText;
            this.label = textView;
            this.hint = str;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.label.setVisibility(0);
                this.editName.setHint("");
                return;
            }
            this.label.setVisibility(4);
            this.editName.setHint(this.hint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyNameValidationState(Optional<FieldError> optional, CharSequence charSequence, TextView textView) {
        if (optional.isPresent()) {
            int ordinal = optional.get().ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    charSequence = ordinal != 2 ? "" : getText(R.string.input_maximum_length_exceeded);
                } else {
                    charSequence = getText(R.string.input_name_invalid_char_error);
                }
            }
            textView.setText(charSequence);
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeContinueBtn(@NonNull Optional<FieldError> optional, @NonNull Optional<FieldError> optional2, @NonNull Optional<FieldError> optional3, @NonNull Optional<FieldError> optional4) {
        if (optional.or(optional2).or(optional3).or(optional4).isPresent()) {
            this.continueBtn.setEnabled(false);
            if (this.themingUpdateUtil.isMosaicThemingEnabled()) {
                this.continueBtn.setAlpha(0.3f);
                return;
            } else if (isThemedUIEnabled()) {
                this.continueBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_button_primary_disabled));
                this.continueBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.fiesta_text_primary_30));
                return;
            } else {
                this.continueBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.disabledGray));
                this.continueBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.button_disabled_text));
                return;
            }
        }
        this.continueBtn.setEnabled(true);
        if (this.themingUpdateUtil.isMosaicThemingEnabled()) {
            this.continueBtn.setAlpha(1.0f);
        } else if (isThemedUIEnabled()) {
            this.continueBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiesta_button_primary));
            this.continueBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.fiesta_text_primary));
        } else {
            this.continueBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.alexaBlue));
            this.continueBtn.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createNewAccount() {
        new Thread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.2
            @Override // java.lang.Runnable
            public void run() {
                new ACMSClient(MetricKeys.OP_PROV_COMMS_USER_FROM_OOBE).request(AppUrl.OOBE_ACCOUNTS).authenticated().post(LocationPublisher.CONTENT_TYPE_JSON, NameConfirmationFragment.this.createPayloadForCreateAccount().getBytes()).enqueue(new CreateAccountCallback(null));
            }
        }).start();
        LOG.i("[createNewAccount] starting API call");
        MetricsHelper.recordCounterMetricOperational(MetricKeys.CREATE_ACCOUNT_STARTED_FROM_OOBE, 1.0d);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null) {
                currentFocus = new View(activity);
            }
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void init(View view) {
        Optional<FieldError> absent;
        Optional<FieldError> absent2;
        int colorFromAttribute;
        OOBEActivity oOBEActivity = (OOBEActivity) getActivity();
        ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
        this.userPFM = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false);
        this.sFirstNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.IDENTITY_FIRST_NAME_MAX_LENGTH).intValue();
        this.sLastNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.IDENTITY_LAST_NAME_MAX_LENGTH).intValue();
        this.sPhoneticFirstNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.IDENTITY_FIRST_NAME_MAX_LENGTH).intValue();
        this.sPhoneticLastNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.IDENTITY_LAST_NAME_MAX_LENGTH).intValue();
        this.selectedUser = oOBEActivity.getUser();
        this.selectedPCAProfile = oOBEActivity.getPCAProfile();
        this.isNewUser = this.selectedUser.isNewUser();
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.first_section);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.second_section);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.third_section);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.fourth_section);
        this.firstName = (EditText) view.findViewById(R.id.oobe_name_confirmation_firstname);
        this.firstName.setText(this.selectedUser.firstName);
        this.firstNameLabel = (TextView) view.findViewById(R.id.oobe_first_name_label);
        this.firstNameErrorView = (TextView) view.findViewById(R.id.oobe_first_name_error);
        this.lastName = (EditText) view.findViewById(R.id.oobe_name_confirmation_lastname);
        this.lastName.setText(this.selectedUser.lastName);
        this.lastNameLabel = (TextView) view.findViewById(R.id.oobe_last_name_label);
        this.lastNameErrorView = (TextView) view.findViewById(R.id.oobe_last_name_error);
        this.phoneticFirstName = (EditText) view.findViewById(R.id.oobe_name_confirmation_phonetic_firstname);
        this.phoneticFirstName.setText(this.selectedUser.phoneticFirstName);
        this.phoneticFirstNameLabel = (TextView) view.findViewById(R.id.oobe_phonetic_first_name_label);
        this.phoneticFirstNameErrorView = (TextView) view.findViewById(R.id.oobe_phonetic_first_name_error);
        this.phoneticLastName = (EditText) view.findViewById(R.id.oobe_name_confirmation_phonetic_lastname);
        this.phoneticLastName.setText(this.selectedUser.phoneticLastName);
        this.phoneticLastNameLabel = (TextView) view.findViewById(R.id.oobe_phonetic_last_name_label);
        this.phoneticLastNameErrorView = (TextView) view.findViewById(R.id.oobe_phonetic_last_name_error);
        this.mTvOobeTermsLink = (TextView) view.findViewById(R.id.oobe_name_confirmation_terms);
        if (this.userPFM.equals("JP")) {
            linearLayout.removeAllViews();
            linearLayout2.removeAllViews();
            linearLayout3.removeAllViews();
            linearLayout4.removeAllViews();
            linearLayout.addView(this.lastNameLabel);
            linearLayout.addView(this.lastName);
            linearLayout.addView(this.lastNameErrorView);
            linearLayout2.addView(this.phoneticLastNameLabel);
            linearLayout2.addView(this.phoneticLastName);
            linearLayout2.addView(this.phoneticLastNameErrorView);
            linearLayout3.addView(this.firstNameLabel);
            linearLayout3.addView(this.firstName);
            linearLayout3.addView(this.firstNameErrorView);
            linearLayout4.addView(this.phoneticFirstNameLabel);
            linearLayout4.addView(this.phoneticFirstName);
            linearLayout4.addView(this.phoneticFirstNameErrorView);
        }
        Optional<FieldError> validateName = validateName(this.firstName, this.sFirstNameMaxLength, false);
        Optional<FieldError> validateName2 = validateName(this.lastName, this.sLastNameMaxLength, false);
        if (!this.userPFM.equals("JP")) {
            this.phoneticFirstName.setVisibility(8);
            this.phoneticLastName.setVisibility(8);
            absent = Optional.absent();
            absent2 = Optional.absent();
        } else {
            this.phoneticFirstName.setVisibility(0);
            this.phoneticLastName.setVisibility(0);
            absent = Optional.absent();
            absent2 = Optional.absent();
        }
        ((TextView) view.findViewById(R.id.oobe_name_confirmation_title)).setText(R.string.oobe_name_enter);
        final String configString = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.TERMS_OF_USE_URL_KEY);
        final String configString2 = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.CONDITIONS_OF_USE_URL_KEY);
        String string = getResources().getString(R.string.oobe_alexa_usage_terms_link);
        String string2 = getResources().getString(R.string.oobe_alexa_usage_conditions_link);
        String string3 = getResources().getString(R.string.oobe_alexa_usage_terms);
        String string4 = getResources().getString(R.string.oobe_alexa_usage_and_text);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$NameConfirmationFragment$srFnsOvSf30LpYJx6Qj_ii6wbdE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NameConfirmationFragment.this.lambda$init$0$NameConfirmationFragment(configString, view2);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$NameConfirmationFragment$WthfV5CgeVSWOMPnNWmzm74xfIA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                NameConfirmationFragment.this.lambda$init$1$NameConfirmationFragment(configString2, view2);
            }
        };
        String format = String.format("%s %s %s %s.", string3, string2, string4, string);
        int length = string4.length() + string2.length() + string3.length() + 3;
        int length2 = format.length() - 1;
        int length3 = string3.length() + 1;
        int length4 = string2.length() + string3.length() + 1;
        if (!this.themingUpdateUtil.isMosaicThemingEnabled()) {
            colorFromAttribute = getResources().getColor(R.color.fiesta_btn_normal);
        } else {
            colorFromAttribute = this.themingUpdateUtil.getColorFromAttribute(getActivity().getApplicationContext(), R.attr.mosaicAction20);
        }
        LinkSpannable create = new LinkSpannable.Builder().from(length).to(length2).setLinkColor(Integer.valueOf(colorFromAttribute)).setListener(onClickListener).create();
        LinkSpannable create2 = new LinkSpannable.Builder().from(length3).to(length4).setLinkColor(Integer.valueOf(colorFromAttribute)).setListener(onClickListener2).create();
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(create, length, length2, 33);
        spannableString.setSpan(create2, length3, length4, 33);
        this.mTvOobeTermsLink.setText(spannableString, TextView.BufferType.SPANNABLE);
        this.mTvOobeTermsLink.setMovementMethod(LinkMovementMethod.getInstance());
        this.continueBtn = (Button) view.findViewById(R.id.oobe_name_confirmation_continue_btn);
        changeContinueBtn(validateName, validateName2, absent, absent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Optional<FieldError> validateName(EditText editText, int i, boolean z) {
        String obj = editText.getText().toString();
        int length = obj.trim().length();
        if (length == 0 && !z) {
            return Optional.of(FieldError.EMPTY);
        }
        if ("JP".equals(this.userPFM)) {
            if (z && LanguageUtil.containUnsupportedCharForJPPhoneticNames(obj)) {
                return Optional.of(FieldError.INVALID_CHAR);
            }
            if (!z && LanguageUtil.containUnsupportedCharForJPNames(obj)) {
                return Optional.of(FieldError.INVALID_CHAR);
            }
        } else if (LanguageUtil.containsUnsupportedCharSet(obj)) {
            return Optional.of(FieldError.INVALID_CHAR);
        }
        if (length > i) {
            return Optional.of(FieldError.EXCEEDS_MAX_LENGTH);
        }
        return Optional.absent();
    }

    public String createPayloadForCreateAccount() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("firstName", this.firstName.getText().toString().trim());
            jSONObject.put("lastName", this.lastName.getText().toString().trim());
            jSONObject.put("phoneticFirstName", this.phoneticFirstName.getText().toString().trim());
            jSONObject.put("phoneticLastName", this.phoneticLastName.getText().toString().trim());
            if (!this.isNewUser) {
                jSONObject.put("directedId", this.selectedUser.directedId);
            }
            jSONObject.put(ProfileAttributes.PERSON_ID, this.selectedPCAProfile.personIdV2);
            return jSONObject.toString();
        } catch (JSONException e) {
            LOG.e("Couldn't format name to JSON", e);
            return "";
        }
    }

    public /* synthetic */ void lambda$init$0$NameConfirmationFragment(String str, View view) {
        Utils.openUrlInExternalBrowser(str, this);
    }

    public /* synthetic */ void lambda$init$1$NameConfirmationFragment(String str, View view) {
        Utils.openUrlInExternalBrowser(str, this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        if (this.themingUpdateUtil.isMosaicThemingEnabled()) {
            inflate = LayoutInflater.from(new ContextThemeWrapper(getActivity().getApplicationContext(), R.style.Theme_Mosaic_Jasper)).inflate(R.layout.mosaic_oobe_name_confirmation, viewGroup, false);
        } else if (isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_oobe_name_confirmation, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.oobe_name_confirmation, viewGroup, false);
        }
        init(inflate);
        this.firstName.addTextChangedListener(new NameConfirmationTextWatcher("firstName"));
        this.lastName.addTextChangedListener(new NameConfirmationTextWatcher("lastName"));
        this.phoneticFirstName.addTextChangedListener(new NameConfirmationTextWatcher("phoneticFirstName"));
        this.phoneticLastName.addTextChangedListener(new NameConfirmationTextWatcher("phoneticLastName"));
        EditText editText = this.firstName;
        editText.setOnFocusChangeListener(new TextOnFocusChangListener(editText, this.firstNameLabel, getString(R.string.first_name)));
        EditText editText2 = this.lastName;
        editText2.setOnFocusChangeListener(new TextOnFocusChangListener(editText2, this.lastNameLabel, getString(R.string.last_name)));
        EditText editText3 = this.phoneticFirstName;
        editText3.setOnFocusChangeListener(new TextOnFocusChangListener(editText3, this.phoneticFirstNameLabel, getString(R.string.phonetic_first_name)));
        EditText editText4 = this.phoneticLastName;
        editText4.setOnFocusChangeListener(new TextOnFocusChangListener(editText4, this.phoneticLastNameLabel, getString(R.string.phonetic_last_name)));
        this.continueBtn.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.NameConfirmationFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Optional absent;
                Optional absent2;
                NameConfirmationFragment nameConfirmationFragment = NameConfirmationFragment.this;
                Optional validateName = nameConfirmationFragment.validateName(nameConfirmationFragment.firstName, NameConfirmationFragment.this.sFirstNameMaxLength, false);
                NameConfirmationFragment nameConfirmationFragment2 = NameConfirmationFragment.this;
                Optional validateName2 = nameConfirmationFragment2.validateName(nameConfirmationFragment2.lastName, NameConfirmationFragment.this.sLastNameMaxLength, false);
                if (NameConfirmationFragment.this.userPFM.equals("JP")) {
                    NameConfirmationFragment nameConfirmationFragment3 = NameConfirmationFragment.this;
                    absent = nameConfirmationFragment3.validateName(nameConfirmationFragment3.phoneticFirstName, NameConfirmationFragment.this.sPhoneticFirstNameMaxLength, true);
                    NameConfirmationFragment nameConfirmationFragment4 = NameConfirmationFragment.this;
                    absent2 = nameConfirmationFragment4.validateName(nameConfirmationFragment4.phoneticLastName, NameConfirmationFragment.this.sPhoneticLastNameMaxLength, true);
                } else {
                    absent = Optional.absent();
                    absent2 = Optional.absent();
                }
                NameConfirmationFragment nameConfirmationFragment5 = NameConfirmationFragment.this;
                nameConfirmationFragment5.applyNameValidationState(validateName, nameConfirmationFragment5.getText(R.string.oobe_first_name_error), NameConfirmationFragment.this.firstNameErrorView);
                NameConfirmationFragment nameConfirmationFragment6 = NameConfirmationFragment.this;
                nameConfirmationFragment6.applyNameValidationState(validateName2, nameConfirmationFragment6.getText(R.string.oobe_last_name_error), NameConfirmationFragment.this.lastNameErrorView);
                NameConfirmationFragment nameConfirmationFragment7 = NameConfirmationFragment.this;
                nameConfirmationFragment7.applyNameValidationState(absent, nameConfirmationFragment7.getText(R.string.oobe_phonetic_first_name_error), NameConfirmationFragment.this.phoneticFirstNameErrorView);
                NameConfirmationFragment nameConfirmationFragment8 = NameConfirmationFragment.this;
                nameConfirmationFragment8.applyNameValidationState(absent2, nameConfirmationFragment8.getText(R.string.oobe_phonetic_last_name_error), NameConfirmationFragment.this.phoneticLastNameErrorView);
                NameConfirmationFragment.hideKeyboard(NameConfirmationFragment.this.getActivity());
                if (!validateName.or(validateName2).or(absent).or(absent2).isPresent()) {
                    NameConfirmationFragment.this.selectedUser.firstName = NameConfirmationFragment.this.firstName.getText().toString().trim();
                    NameConfirmationFragment.this.selectedUser.lastName = NameConfirmationFragment.this.lastName.getText().toString().trim();
                    NameConfirmationFragment.this.selectedUser.phoneticFirstName = NameConfirmationFragment.this.phoneticFirstName.getText().toString().trim();
                    NameConfirmationFragment.this.selectedUser.phoneticLastName = NameConfirmationFragment.this.phoneticLastName.getText().toString().trim();
                    ((OOBEActivity) NameConfirmationFragment.this.getActivity()).saveUser(NameConfirmationFragment.this.selectedUser);
                    NameConfirmationFragment.this.createNewAccount();
                    if (NameConfirmationFragment.this.isNewUser) {
                        NameConfirmationFragment nameConfirmationFragment9 = NameConfirmationFragment.this;
                        nameConfirmationFragment9.progressDialog = ((OOBEActivity) nameConfirmationFragment9.getActivity()).newProgressDialog(NameConfirmationFragment.this.getActivity().getResources().getString(R.string.oobe_create_account_progress));
                    } else {
                        NameConfirmationFragment nameConfirmationFragment10 = NameConfirmationFragment.this;
                        nameConfirmationFragment10.progressDialog = ((OOBEActivity) nameConfirmationFragment10.getActivity()).newProgressDialog(NameConfirmationFragment.this.getActivity().getResources().getString(R.string.oobe_update_account_progress));
                    }
                    NameConfirmationFragment.this.progressDialog.show();
                }
            }
        });
        OOBEActivity oOBEActivity = (OOBEActivity) getActivity();
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        if (!oOBEActivity.isFinishing()) {
            oOBEActivity.showHeaderBar();
            oOBEActivity.showBackButton();
            oOBEActivity.hideSkipButton();
        }
        getActivity().getWindow().setSoftInputMode(16);
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        if (this.oobeState == OOBEStateMachine.STATE.NAME_CONFIRMATION) {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_NAME_CONFIRMATION_START);
        } else {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_NEW_PROFILE_START);
        }
    }

    public void setOobeState(OOBEStateMachine.STATE state) {
        this.oobeState = state;
    }
}
