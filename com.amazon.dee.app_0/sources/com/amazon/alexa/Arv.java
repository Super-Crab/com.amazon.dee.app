package com.amazon.alexa;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.HYG;
import com.amazon.alexa.KLX;
import com.amazon.alexa.Sas;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerCall;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerConfiguration;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerDevice;
import com.amazon.alexa.client.alexaservice.comms.payload.AutoValue_PhoneCallControllerStatePayload;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentStateHeader;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.CapabilityInterface;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.iqq;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import java.util.ArrayList;
import java.util.Arrays;
import javax.inject.Inject;
/* compiled from: PhoneCallControllerComponentStateAuthority.java */
/* loaded from: classes.dex */
public class Arv extends Xtu {
    public static final String JTe = "Arv";
    @VisibleForTesting
    public static final ComponentStateHeader LPk = ComponentStateHeader.zZm(AvsApiConstants.Alexa.Comms.PhoneCallController.zZm, AvsApiConstants.Alexa.Comms.PhoneCallController.ComponentStates.PhoneCallControllerState.zZm);
    @VisibleForTesting
    public static final rAH yPL = new AutoValue_PhoneCallControllerConfiguration(Arrays.asList(HYG.zZm(HYG.zZm.VIDEO_SUPPORTED.toString(), true)));
    public Context Mlj;
    public Dtt dMe;
    public final Sas lOf;
    public final TelephonyManager zzR;

    @Inject
    public Arv(Context context, AlexaClientEventBus alexaClientEventBus, lEV lev, LrI lrI, TelephonyManager telephonyManager, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation, Dtt dtt) {
        super(alexaClientEventBus, lev, lrI);
        this.Mlj = context;
        this.zzR = telephonyManager;
        this.lOf = new AutoValue_PhoneCallControllerDevice(alexaHandsFreeDeviceInformation.isCurrentDeviceHandsFree() ? Sas.zZm.CONNECTED : Sas.zZm.DISCONNECTED);
        this.dMe = dtt;
    }

    @Override // com.amazon.alexa.Xtu
    public CapabilityInterface BIo() {
        return AvsApiConstants.Alexa.Comms.PhoneCallController.BIo;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentState getState() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        KLX.zZm zZm = KLX.zZm();
        int i = 0;
        if (Build.VERSION.SDK_INT <= 30 || this.dMe.zZm(this.Mlj, "android.permission.READ_PHONE_STATE") == 0) {
            i = this.zzR.getCallState();
        }
        vJW zZm2 = vJW.zZm();
        ArrayList arrayList = new ArrayList();
        vUA vua = null;
        if (i == 1) {
            vua = vUA.zZm(zZm2, dZc.INBOUND_RINGING);
        } else if (i == 2) {
            vua = vUA.zZm(zZm2, dZc.ACTIVE);
        }
        if (vua != null) {
            ((iqq.zZm) zZm).BIo = new AutoValue_PhoneCallControllerCall(zZm2);
            arrayList.add(vua);
        }
        iqq.zZm zzm = (iqq.zZm) zZm;
        zzm.zZm = arrayList;
        Sas sas = this.lOf;
        if (sas != null) {
            zzm.zQM = sas;
            zzm.zyO = yPL;
            String str = "";
            if (zzm.zQM == null) {
                str = C0179Pya.zZm(str, " device");
            }
            if (str.isEmpty()) {
                ComponentState create = ComponentState.create(LPk, new AutoValue_PhoneCallControllerStatePayload(zzm.zZm, zzm.BIo, zzm.zQM, zzm.zyO));
                String str2 = "PCC ComponentState gathered in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " ms";
                return create;
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
        throw new NullPointerException("Null device");
    }

    @Override // com.amazon.alexa.Xtu
    public Namespace zQM() {
        return AvsApiConstants.Alexa.Comms.PhoneCallController.zZm;
    }

    @Override // com.amazon.alexa.dRG
    public ComponentStateHeader zZm() {
        return LPk;
    }
}
