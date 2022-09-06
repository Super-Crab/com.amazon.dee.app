package com.amazon.alexa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.alexa.VIE;
import com.amazon.alexa.api.AlexaPreferences;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: InternalTrustedStatesProvider.java */
@Singleton
/* loaded from: classes.dex */
public class adp implements VIE {
    public static final String zZm = "adp";
    public final Context BIo;
    public final SimpleDateFormat JTe = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.US);
    public final TimeProvider LPk;
    public final IntentFilter Mlj;
    public final Msx Qle;
    public final gSO dMe;
    public final DeviceInformation jiA;
    public final AlexaClientEventBus lOf;
    public final AMPDInformationProvider uzr;
    public boolean yPL;
    public final peZ zQM;
    public final Box zyO;
    public final zZm zzR;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: InternalTrustedStatesProvider.java */
    /* loaded from: classes.dex */
    public class zZm extends BroadcastReceiver {
        public boolean zZm;

        public zZm(IntentFilter intentFilter) {
            Log.d(adp.zZm, "Registering lock screen broadcast receiver");
            adp.this.BIo.registerReceiver(this, intentFilter);
            this.zZm = true;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.SCREEN_OFF")) {
                if (adp.this.yPL) {
                    return;
                }
                adp.this.zyO.BIo(adp.this.JTe.format(adp.this.LPk.getCurrentTimestamp()));
                adp adpVar = adp.this;
                adpVar.yPL = adpVar.Qle.zZm();
            } else if (!action.equals("android.intent.action.USER_PRESENT")) {
            } else {
                adp.this.zyO.BIo(adp.this.JTe.format(adp.this.LPk.getCurrentTimestamp()));
                adp adpVar2 = adp.this;
                adpVar2.yPL = adpVar2.Qle.zZm();
            }
        }
    }

    @Inject
    public adp(Context context, peZ pez, Box box, @Nullable DeviceInformation deviceInformation, Msx msx, TimeProvider timeProvider, AlexaClientEventBus alexaClientEventBus, gSO gso, AMPDInformationProvider aMPDInformationProvider) {
        this.BIo = context;
        this.yPL = msx.zZm();
        this.zQM = pez;
        this.zyO = box;
        this.jiA = deviceInformation;
        this.Qle = msx;
        this.LPk = timeProvider;
        this.JTe.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        this.Mlj = new IntentFilter();
        this.Mlj.addAction("android.intent.action.SCREEN_OFF");
        this.Mlj.addAction("android.intent.action.USER_PRESENT");
        this.zzR = new zZm(this.Mlj);
        this.lOf = alexaClientEventBus;
        this.dMe = gso;
        this.uzr = aMPDInformationProvider;
        if (!msx.zZm()) {
            box.BIo(this.JTe.format(timeProvider.getCurrentTimestamp()));
        }
    }

    public VIE.BIo BIo() {
        if (!zZm(AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_NONE) || !this.Qle.zZm()) {
            return this.Qle.zZm() ? VIE.BIo.NEVER_UNLOCKED : VIE.BIo.UNLOCKED;
        }
        return VIE.BIo.LOCKED;
    }

    @Nullable
    public String zZm() {
        if (!this.Qle.zZm()) {
            this.zyO.BIo(this.JTe.format(this.LPk.getCurrentTimestamp()));
        }
        if (zZm(AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_NONE)) {
            return this.zyO.Qle();
        }
        if (!this.Qle.zZm()) {
            return this.zyO.Qle();
        }
        return null;
    }

    public final boolean zZm(AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue) {
        return this.zQM.zZm().preferDisplayOverLockscreenWithVerifiedVoiceValue().equals(showOnLockscreenWithVerifiedVoiceValue.name());
    }
}
