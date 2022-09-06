package com.amazon.identity.auth.device.framework.smartlock;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.fb;
import com.amazon.identity.auth.device.fc;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import com.amazon.identity.auth.device.io;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Set;
import java.util.regex.Pattern;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class CustomerInformationManager {
    private static final Pattern lW = Pattern.compile("^\\+?[1-9]\\d{1,14}$");
    private a lX;
    private b lY;
    private boolean lZ;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum HintType {
        NAME,
        EMAIL,
        PHONE
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void b(fc fcVar);
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class b implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private final Activity mActivity;
        private final int ma;
        private GoogleApiClient mb;

        public b(Activity activity, int i) {
            this.mActivity = activity;
            this.ma = i;
            this.mb = new GoogleApiClient.Builder(activity).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.CREDENTIALS_API).build();
        }

        public void h(Set<HintType> set) throws IntentSender.SendIntentException {
            boolean z = true;
            HintRequest.Builder phoneNumberIdentifierSupported = new HintRequest.Builder().setHintPickerConfig(new CredentialPickerConfig.Builder().setShowCancelButton(true).setPrompt(1).build()).setPhoneNumberIdentifierSupported(set.contains(HintType.PHONE));
            if (!set.contains(HintType.NAME) && !set.contains(HintType.EMAIL)) {
                z = false;
            }
            this.mActivity.startIntentSenderForResult(Auth.CredentialsApi.getHintPickerIntent(this.mb, phoneNumberIdentifierSupported.setEmailAddressIdentifierSupported(z).build()).getIntentSender(), this.ma, null, 0, 0, 0);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(@Nullable Bundle bundle) {
            io.i("CustomerInfoManager", "google api client onConnected");
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            io.e("CustomerInfoManager", "google api client onConnectionFailed:" + connectionResult.toString());
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            io.i("CustomerInfoManager", "google api client onConnectionSuspended");
        }
    }

    @Deprecated
    public CustomerInformationManager() {
    }

    public void a(Set<HintType> set, a aVar) {
        try {
            this.lX = aVar;
            if (this.lZ) {
                this.lY.h(set);
            } else {
                a(new fc.a().a(CustomeInformationResultType.NO_PLAY_SERVICE_SUPPORT).er());
            }
        } catch (Throwable th) {
            io.e("CustomerInfoManager", "startIntentSenderForResult", th);
            a(new fc.a().a(CustomeInformationResultType.ERROR).er());
        }
    }

    public CustomerInformationManager(Activity activity, int i) {
        this.lZ = MAPRuntimePermissionHandler.J(activity.getApplicationContext());
        io.i("CustomerInfoManager", "smartlock supporting:" + this.lZ);
        this.lX = null;
        if (this.lZ) {
            this.lY = new b(activity, i);
        } else {
            this.lY = null;
        }
    }

    protected void a(fc fcVar) {
        a aVar = this.lX;
        if (aVar == null) {
            io.w("CustomerInfoManager", "got null consumer callback, there may be errors when sending hint request");
            return;
        }
        aVar.b(fcVar);
        this.lX = null;
    }

    public void a(int i, Intent intent) {
        fc er;
        try {
            if (i == -1) {
                Credential parcelableExtra = intent.getParcelableExtra("com.google.android.gms.credentials.Credential");
                String id = parcelableExtra.getId();
                if (!TextUtils.isEmpty(id)) {
                    fb.a aVar = new fb.a();
                    aVar.bH(parcelableExtra.getName());
                    if (lW.matcher(id).matches()) {
                        aVar.bJ(id);
                    } else {
                        aVar.bI(id);
                    }
                    er = new fc.a().a(CustomeInformationResultType.OK).a(aVar.eo()).er();
                } else {
                    throw new RuntimeException("got empty id from hint response:".concat(String.valueOf(id)));
                }
            } else if (i == 1002) {
                io.i("CustomerInfoManager", "No Available hint");
                er = new fc.a().a(CustomeInformationResultType.NO_HINTS_AVAILABLE).er();
            } else {
                io.i("CustomerInfoManager", "Hint Read cancelled");
                er = new fc.a().a(CustomeInformationResultType.CANCELLED).er();
            }
        } catch (Exception e) {
            io.e("CustomerInfoManager", "parseActivityResult error:" + e.getMessage(), e);
            er = new fc.a().a(CustomeInformationResultType.ERROR).er();
        }
        a(er);
    }
}
