package com.amazon.identity.auth.device.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.gg;
import com.amazon.identity.auth.device.gh;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class GetAuthenticatorResultsActivity extends Activity {
    private RemoteCallbackWrapper fb;
    private gg w;

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}";
        if (i2 == -1) {
            if (intent.hasExtra("FIDO2_ERROR_EXTRA")) {
                AuthenticatorErrorResponse deserializeFromBytes = AuthenticatorErrorResponse.deserializeFromBytes(intent.getByteArrayExtra("FIDO2_ERROR_EXTRA"));
                int errorCodeAsInt = deserializeFromBytes.getErrorCodeAsInt();
                String errorMessage = deserializeFromBytes.getErrorMessage();
                MAPError mAPError = MAPError.FidoAuthenticatorError.AUTHENTICATOR_ERRORS_TO_MAP_ERRORS.get(Integer.valueOf(errorCodeAsInt));
                if (mAPError == null) {
                    if (i == 0) {
                        mq.incrementCounterAndRecord("GetAuthResultsUnexpectedError:CreateCredential" + errorCodeAsInt + ":" + errorMessage, new String[0]);
                        io.i("GetAuthenticatorResultsActivity", "CreateAuthenticatorCredential call in GetAuthenticatorResultsActivity returned with AuthenticatorErrorResponse that is not mapped to MAPError. AuthenticatorErrorResponse = " + errorCodeAsInt + " " + errorMessage);
                    } else if (i == 1) {
                        mq.incrementCounterAndRecord("GetAuthResultsUnexpectedError:GetAssertion" + errorCodeAsInt + ":" + errorMessage, new String[0]);
                        io.i("GetAuthenticatorResultsActivity", "GetAssertionWithAuthenticatorCredential call in GetAuthenticatorResultsActivity returned with AuthenticatorErrorResponse that is not mapped to MAPError. AuthenticatorErrorResponse = " + errorCodeAsInt + " " + errorMessage);
                    } else {
                        mq.incrementCounterAndRecord("GetAuthResultsUnexpectedError:UnknownOperation" + errorCodeAsInt + ":" + errorMessage, new String[0]);
                        io.i("GetAuthenticatorResultsActivity", "Unknown operation in GetAuthenticatorResultsActivity returned with AuthenticatorErrorResponse that is not mapped to MAPError. AuthenticatorErrorResponse = " + errorCodeAsInt + " " + errorMessage);
                    }
                    mAPError = MAPError.CommonError.INTERNAL_ERROR;
                }
                if (i == 0) {
                    mq.incrementCounterAndRecord("CreateAuthenticatorCredential:" + mAPError.getErrorType(), new String[0]);
                    io.i("GetAuthenticatorResultsActivity", "Create authenticator credential returned with error = ".concat(String.valueOf(mAPError)));
                } else if (i == 1) {
                    mq.incrementCounterAndRecord("GetAuthenticatorAssertion:" + mAPError.getErrorType(), new String[0]);
                    io.i("GetAuthenticatorResultsActivity", "Get authenticator assertion returned with error = ".concat(String.valueOf(mAPError)));
                } else {
                    mq.incrementCounterAndRecord("UnexpectedOperation:" + mAPError.getErrorType(), new String[0]);
                    io.i("GetAuthenticatorResultsActivity", "Unexpected operation returned with error = ".concat(String.valueOf(mAPError)));
                }
                Bundle bundle = new Bundle();
                if (mAPError == MAPError.FidoAuthenticatorError.LOCK_SCREEN_NOT_SECURE) {
                    str = MAPError.getErrorTypeWithUnderscores(mAPError.getErrorType());
                }
                bundle.putString("error", str);
                bundle.putString("errorMessage", mAPError.getErrorMessage());
                this.fb.onError(bundle);
            } else {
                String str2 = null;
                if (i == 0) {
                    try {
                        AuthenticatorAttestationResponse deserializeFromBytes2 = AuthenticatorAttestationResponse.deserializeFromBytes(intent.getByteArrayExtra("FIDO2_RESPONSE_EXTRA"));
                        String encodeToString = deserializeFromBytes2.getAttestationObject() != null ? Base64.encodeToString(deserializeFromBytes2.getAttestationObject(), 3) : null;
                        String encodeToString2 = deserializeFromBytes2.getClientDataJSON() != null ? Base64.encodeToString(deserializeFromBytes2.getClientDataJSON(), 3) : null;
                        if (deserializeFromBytes2.getKeyHandle() != null) {
                            str2 = Base64.encodeToString(deserializeFromBytes2.getKeyHandle(), 3);
                        }
                        if (encodeToString != null && encodeToString2 != null && str2 != null) {
                            mq.incrementCounterAndRecord("CreateAuthenticatorCredential:SUCCESS", new String[0]);
                            io.i("GetAuthenticatorResultsActivity", "Successfully created authenticator credential");
                            this.w.g("fido_authenticator_credential_namespace", str2, "true");
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("attestationObject", encodeToString);
                            bundle2.putString("clientDataJson", encodeToString2);
                            bundle2.putString("credentialId", str2);
                            this.fb.onSuccess(bundle2);
                        } else {
                            mq.incrementCounterAndRecord("CreateAuthenticatorCredential:FAILED", new String[0]);
                            io.i("GetAuthenticatorResultsActivity", "Create authenticator credential returned null data responses");
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("error", str);
                            bundle3.putString("errorMessage", "Create authenticator credential returned null data responses");
                            this.fb.onError(bundle3);
                        }
                    } catch (Exception e) {
                        mq.incrementCounterAndRecord("CreateAuthenticatorCredential:FAILED", new String[0]);
                        io.i("GetAuthenticatorResultsActivity", "Exception occurred while parsing AuthenticatorAttestationResponse for create authenticator credential call. Exception = ".concat(String.valueOf(e)));
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("error", str);
                        bundle4.putString("errorMessage", "Exception occurred while parsing AuthenticatorAttestationResponse for create authenticator credential call.");
                        this.fb.onError(bundle4);
                    }
                } else if (i == 1) {
                    try {
                        AuthenticatorAssertionResponse deserializeFromBytes3 = AuthenticatorAssertionResponse.deserializeFromBytes(intent.getByteArrayExtra("FIDO2_RESPONSE_EXTRA"));
                        String encodeToString3 = deserializeFromBytes3.getAuthenticatorData() != null ? Base64.encodeToString(deserializeFromBytes3.getAuthenticatorData(), 3) : null;
                        String encodeToString4 = deserializeFromBytes3.getClientDataJSON() != null ? Base64.encodeToString(deserializeFromBytes3.getClientDataJSON(), 3) : null;
                        String encodeToString5 = deserializeFromBytes3.getSignature() != null ? Base64.encodeToString(deserializeFromBytes3.getSignature(), 3) : null;
                        String encodeToString6 = deserializeFromBytes3.getUserHandle() != null ? Base64.encodeToString(deserializeFromBytes3.getUserHandle(), 3) : null;
                        if (deserializeFromBytes3.getKeyHandle() != null) {
                            str2 = Base64.encodeToString(deserializeFromBytes3.getKeyHandle(), 3);
                        }
                        if (encodeToString5 != null) {
                            mq.incrementCounterAndRecord("GetAuthenticatorAssertion:SUCCESS", new String[0]);
                            io.i("GetAuthenticatorResultsActivity", "Successfully generated assertion with authenticator credential");
                            Bundle bundle5 = new Bundle();
                            bundle5.putString("authenticatorData", encodeToString3);
                            bundle5.putString("signature", encodeToString5);
                            bundle5.putString("credentialId", str2);
                            bundle5.putString("userHandle", encodeToString6);
                            bundle5.putString("clientDataJson", encodeToString4);
                            this.fb.onSuccess(bundle5);
                        } else {
                            mq.incrementCounterAndRecord("GetAuthenticatorAssertion:FAILED", new String[0]);
                            io.i("GetAuthenticatorResultsActivity", "getAssertionWithAuthenticatorCredential returned a null signature response");
                            Bundle bundle6 = new Bundle();
                            bundle6.putString("error", str);
                            bundle6.putString("errorMessage", "getAssertionWithAuthenticatorCredential returned a null signature response");
                            this.fb.onError(bundle6);
                        }
                    } catch (Exception e2) {
                        mq.incrementCounterAndRecord("GetAuthenticatorAssertion:FAILED", new String[0]);
                        io.i("GetAuthenticatorResultsActivity", "Exception occurred while parsing AuthenticatorAssertionResponse for getAssertionWithAuthenticatorCredential call. Exception = ".concat(String.valueOf(e2)));
                        Bundle bundle7 = new Bundle();
                        bundle7.putString("error", str);
                        bundle7.putString("errorMessage", "Exception occurred while parsing AuthenticatorAssertionResponse for getAssertionWithAuthenticatorCredential call.");
                        this.fb.onError(bundle7);
                    }
                } else {
                    mq.incrementCounterAndRecord("UnexpectedOperation:SUCCESS", new String[0]);
                    io.i("GetAuthenticatorResultsActivity", "Unknown operation returned successfully.");
                    Bundle bundle8 = new Bundle();
                    bundle8.putString("error", str);
                    bundle8.putString("errorMessage", "Unexpected operation was invoked");
                    this.fb.onError(bundle8);
                }
            }
        } else {
            if (i == 0) {
                mq.incrementCounterAndRecord("CreateAuthenticatorCredential:FAILED", new String[0]);
                io.i("GetAuthenticatorResultsActivity", "Create authenticator credential call failed");
            } else if (i == 1) {
                mq.incrementCounterAndRecord("GetAuthenticatorAssertion:FAILED", new String[0]);
                io.i("GetAuthenticatorResultsActivity", "Get authenticator assertion call failed");
            } else {
                mq.incrementCounterAndRecord("UnexpectedOperation:FAILED", new String[0]);
                io.i("GetAuthenticatorResultsActivity", "Unexpected operation call failed");
            }
            this.fb.onError(GeneratedOutlineSupport1.outline12("error", str, "errorMessage", "Call returned with status code not ok."));
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(ResourceHelper.B(this, "getauthenticatorresultsactivitylayout"));
        this.w = ((gh) ed.M(getApplicationContext()).getSystemService("dcp_data_storage_factory")).dV();
        PendingIntent pendingIntent = (PendingIntent) getIntent().getExtras().get("pendingIntentKey");
        int intExtra = getIntent().getIntExtra("requestTypeKey", -1);
        this.fb = (RemoteCallbackWrapper) getIntent().getParcelableExtra("callbackKey");
        if (this.fb == null) {
            io.i("GetAuthenticatorResultsActivity", "GetAuthenticatorResultsActivity invoked with null callback");
            finish();
            return;
        }
        try {
            startIntentSenderForResult(pendingIntent.getIntentSender(), intExtra, null, 0, 0, 0);
        } catch (Exception e) {
            io.i("GetAuthenticatorResultsActivity", "Exception occurred while creating authenticator credential or generating an assertion. Error = ".concat(String.valueOf(e)));
            Bundle bundle2 = new Bundle();
            bundle2.putString("error", "{\"error\":\"General_Error\",\"errorMessage\":\"An internal MAP error exception occurred\"}");
            bundle2.putString("errorMessage", "Exception occurred while creating authenticator credential or generating an assertion.");
            this.fb.onError(bundle2);
            finish();
        }
    }
}
