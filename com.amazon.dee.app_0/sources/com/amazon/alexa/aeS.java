package com.amazon.alexa;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.Ppr;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.settings.AutoValue_Setting;
import com.amazon.alexa.client.alexaservice.settings.AutoValue_SupportsMobileDownchannelSetting;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import java.util.Collection;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SettingsAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class aeS {
    public static final String zZm = "aeS";
    public final AlexaClientEventBus BIo;
    public Boolean JTe;
    public TimeZone Qle;
    public final gSO jiA;
    public final Box zQM;
    public final Context zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SettingsAuthority.java */
    /* loaded from: classes.dex */
    public class BIo extends UcG {
        public final TimeZone zZm;

        public BIo(TimeZone timeZone) {
            this.zZm = timeZone;
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            aeS.this.BIo.zyO(Ppr.zZm(Ppr.zZm.TIME_ZONE, num));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            aeS.this.BIo(this.zZm);
            aeS.this.BIo.zyO(Ppr.zZm(Ppr.zZm.TIME_ZONE));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SettingsAuthority.java */
    /* loaded from: classes.dex */
    public class zZm extends UcG {
        public final boolean zZm;

        public zZm(boolean z) {
            this.zZm = z;
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            aeS.this.BIo.zyO(Ppr.zZm(Ppr.zZm.SUPPORTS_MOBILE_DOWNCHANNEL, num));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            aeS.this.zZm(this.zZm);
            aeS.this.BIo.zyO(Ppr.zZm(Ppr.zZm.SUPPORTS_MOBILE_DOWNCHANNEL));
        }
    }

    @Inject
    public aeS(AlexaClientEventBus alexaClientEventBus, Box box, Context context, gSO gso) {
        this.BIo = alexaClientEventBus;
        this.zQM = box;
        this.zyO = context;
        this.jiA = gso;
        alexaClientEventBus.zZm(this);
    }

    @VisibleForTesting
    public synchronized void BIo(TimeZone timeZone) {
        this.Qle = timeZone;
        this.zQM.zZm(timeZone);
    }

    @Subscribe
    public synchronized void on(final Bob bob) {
        if (this.zyO.getPackageName().equals("com.amazon.dee.app")) {
            if (this.JTe == null) {
                this.JTe = Boolean.valueOf(this.zQM.zyO());
            }
            if (!this.JTe.booleanValue()) {
                this.jiA.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$aeS$sbYhpgA-cUCmTvIPZi8DIs8UmC8
                    @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
                    public final void onFeatureServiceReady(boolean z) {
                        aeS.this.zZm(bob, z);
                    }
                });
            }
        }
    }

    public synchronized void zQM() {
        zZm();
        TimeZone timeZone = TimeZone.getDefault();
        if (!timeZone.equals(this.Qle)) {
            this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.Settings.zZm).setName(AvsApiConstants.Settings.Events.SettingsUpdated.zZm).build(), xsd.zZm(new AutoValue_Setting("timezoneid", timeZone.getID())))).zZm(new BIo(timeZone)).zZm());
        }
    }

    public synchronized TimeZone zZm() {
        if (this.Qle == null) {
            this.Qle = this.zQM.jiA();
        }
        return this.Qle;
    }

    public synchronized void BIo() {
        this.Qle = null;
        this.zQM.zzR();
        zZm(false);
    }

    public synchronized void zZm(boolean z) {
        this.zQM.zZm(z);
        this.JTe = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void BIo(boolean z) {
        if (!z) {
            zQM();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(Bob bob, boolean z) {
        if (z || !bob.BIo()) {
            return;
        }
        this.BIo.zyO(JjI.BIo().zZm(Message.create(Header.builder().setNamespace(AvsApiConstants.System.zZm).setName(AvsApiConstants.System.Events.SettingsUpdated.zZm).setMessageIdentifier(MessageIdentifier.createRandom()).build(), tPB.zZm(new AutoValue_SupportsMobileDownchannelSetting("System.supportsMobileDownchannel", true)))).zZm(new zZm(true)).zZm());
    }

    @Subscribe
    public synchronized void on(Txs txs) {
        this.jiA.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$aeS$OanSAJMCAxlbbSHmRPLr5KhPexM
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z) {
                aeS.this.BIo(z);
            }
        });
    }

    public BIo zZm(TimeZone timeZone) {
        return new BIo(timeZone);
    }
}
