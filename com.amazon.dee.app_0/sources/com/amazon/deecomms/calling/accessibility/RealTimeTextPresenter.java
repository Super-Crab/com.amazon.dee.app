package com.amazon.deecomms.calling.accessibility;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.RealTimeTextListener;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;
/* loaded from: classes12.dex */
public class RealTimeTextPresenter implements RealTimeTextListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RealTimeTextPresenter.class);
    private final Call call;
    private String localString = "";
    private RealTimeTextViewContract realTimeTextViewContract;

    public RealTimeTextPresenter(@NonNull RealTimeTextViewContract realTimeTextViewContract, @NonNull SipClientState sipClientState) {
        this.call = sipClientState.getCurrentActiveCall();
        this.realTimeTextViewContract = realTimeTextViewContract;
    }

    private String computeStringDifference(@NonNull String str, @NonNull String str2) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str.length() && i < str2.length() && str.charAt(i) == str2.charAt(i)) {
            i++;
        }
        for (int i2 = i; i2 < str.length(); i2++) {
            sb.append('\b');
        }
        while (i < str2.length()) {
            sb.append(str2.charAt(i));
            i++;
        }
        return sb.toString();
    }

    private void sendTheData(@NonNull String str) {
        int length = str.length();
        ArrayList arrayList = new ArrayList(str.length());
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == '\b') {
                arrayList.add((byte) 8);
            } else {
                arrayList.add(Byte.valueOf((byte) str.charAt(i)));
            }
        }
        this.call.sendRealTimeTextData(ArrayUtils.toPrimitive((Byte[]) arrayList.toArray(new Byte[0])));
    }

    public void beforeTextInputChanged(@NonNull CharSequence charSequence) {
        this.localString = charSequence.toString();
    }

    public String getInitialsForContactName(@NonNull String str) {
        if (str.matches(".*\\d.*")) {
            return str;
        }
        String[] split = str.split(" ");
        StringBuilder sb = new StringBuilder();
        if (split.length < 2) {
            return split[0];
        }
        for (String str2 : split) {
            sb.append(str2.charAt(0));
        }
        return sb.toString();
    }

    public void localTextInputChanged(@NonNull String str) {
        sendTheData(computeStringDifference(this.localString, str));
    }

    @Override // com.amazon.comms.calling.service.RealTimeTextListener
    public void onRealTimeTextMessageReceived(@NonNull Call call, @NonNull byte[] bArr) {
        this.realTimeTextViewContract.showIncomingMessage(new String(bArr).toString());
    }

    public void registerForRTTChanges() {
        this.call.registerRealTimeTextListener(this);
    }

    public void unRegisterForRTTChanges() {
        this.call.unregisterRealTimeTextListener(this);
    }
}
