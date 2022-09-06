package com.amazon.alexa.api;

import android.os.RemoteException;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.MNR;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class ApiCallback {
    public final long BIo;
    @Nullable
    public final TimeUnit zQM;
    public final MNR zZm;
    @Nullable
    public final AlexaApiCallbacksMessageSender zyO;

    public ApiCallback() {
        this(null);
    }

    public long BIo() {
        return this.BIo;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("ApiCallExtras{id="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public void zQM() throws RemoteException {
        AlexaApiCallbacksMessageSender alexaApiCallbacksMessageSender = this.zyO;
        if (alexaApiCallbacksMessageSender != null) {
            alexaApiCallbacksMessageSender.onSuccess();
        }
    }

    @Nullable
    public TimeUnit zZm() {
        return this.zQM;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ApiCallback(@com.amazon.alexa.client.annotations.Nullable android.os.Bundle r4) {
        /*
            r3 = this;
            r3.<init>()
            r0 = 0
            if (r4 != 0) goto L15
            r1 = 0
            r3.BIo = r1
            r3.zQM = r0
            com.amazon.alexa.MNR r4 = com.amazon.alexa.MNR.zZm(r0)
            r3.zZm = r4
            r3.zyO = r0
            return
        L15:
            com.amazon.alexa.api.AlexaApiCallbacksBundler$ApiCallExtrasKeys r1 = com.amazon.alexa.api.AlexaApiCallbacksBundler.ApiCallExtrasKeys.ID
            java.lang.String r1 = com.amazon.alexa.api.Bundles.getString(r4, r1)
            com.amazon.alexa.MNR r1 = com.amazon.alexa.MNR.zZm(r1)
            r3.zZm = r1
            com.amazon.alexa.api.AlexaApiCallbacksBundler$ApiCallExtrasKeys r1 = com.amazon.alexa.api.AlexaApiCallbacksBundler.ApiCallExtrasKeys.TIMEOUT_UNIT
            java.lang.String r1 = com.amazon.alexa.api.Bundles.getOptionalString(r4, r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L32
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.valueOf(r1)     // Catch: java.lang.IllegalArgumentException -> L32
            goto L33
        L32:
            r1 = r0
        L33:
            r3.zQM = r1
            com.amazon.alexa.api.AlexaApiCallbacksBundler$ApiCallExtrasKeys r1 = com.amazon.alexa.api.AlexaApiCallbacksBundler.ApiCallExtrasKeys.TIMEOUT_VALUE
            long r1 = com.amazon.alexa.api.Bundles.getLong(r4, r1)
            r3.BIo = r1
            com.amazon.alexa.api.AlexaApiCallbacksBundler$ApiCallExtrasKeys r1 = com.amazon.alexa.api.AlexaApiCallbacksBundler.ApiCallExtrasKeys.CALLBACK
            android.os.IBinder r4 = com.amazon.alexa.api.Bundles.getOptionalBinder(r4, r1)
            if (r4 == 0) goto L4f
            com.amazon.alexa.api.AlexaApiCallbacksMessageSender r0 = new com.amazon.alexa.api.AlexaApiCallbacksMessageSender
            com.amazon.alexa.api.ExtendedClient r1 = com.amazon.alexa.api.AlexaClient.CLIENT
            r0.<init>(r4, r1)
            r3.zyO = r0
            goto L51
        L4f:
            r3.zyO = r0
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.ApiCallback.<init>(android.os.Bundle):void");
    }

    public void zZm(ApiCallFailure apiCallFailure) throws RemoteException {
        AlexaApiCallbacksMessageSender alexaApiCallbacksMessageSender = this.zyO;
        if (alexaApiCallbacksMessageSender != null) {
            alexaApiCallbacksMessageSender.onFailure(apiCallFailure);
        }
    }
}
