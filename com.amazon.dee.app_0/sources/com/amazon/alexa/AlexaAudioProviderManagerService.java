package com.amazon.alexa;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.amazon.alexa.api.AlexaAudioProviderManagerV2;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.auth.map.AccountManagerModule;
import com.amazon.alexa.auth.map.AccountManagerModule_ProvidesAccountManagerFactory;
import com.amazon.alexa.utils.security.SignatureVerifier;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Inject;
import javax.inject.Provider;
/* loaded from: classes.dex */
public class AlexaAudioProviderManagerService extends Service {
    public static final String zZm = "AlexaAudioProviderManagerService";
    public MessageReceiver BIo;
    @Inject
    public MessageReceiversManager Qle;
    @Inject
    public peZ jiA;
    public AlexaAudioProviderManagerV2 zQM;
    @Inject
    public AccountManager zyO;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Preconditions.checkNotNull(new zVs(getApplication()));
        dyd dydVar = (dyd) Preconditions.checkNotNull(new dyd(getApplicationContext()));
        AccountManagerModule accountManagerModule = new AccountManagerModule();
        Preconditions.checkBuilderRequirement(dydVar, dyd.class);
        JaC jaC = new JaC();
        ENl eNl = new ENl();
        kbj kbjVar = new kbj();
        WMj wMj = new WMj();
        TNh tNh = new TNh();
        C0178PyL c0178PyL = new C0178PyL();
        Provider provider = DoubleCheck.provider(PXQ.zZm(dydVar));
        Provider provider2 = DoubleCheck.provider(AccountManagerModule_ProvidesAccountManagerFactory.create(accountManagerModule, provider));
        Provider provider3 = DoubleCheck.provider(new XDp(jaC, provider));
        Provider provider4 = DoubleCheck.provider(new KrI(eNl, provider));
        Provider provider5 = DoubleCheck.provider(new eWA(eNl, provider4));
        Provider provider6 = DoubleCheck.provider(new UWm(kbjVar));
        Provider provider7 = DoubleCheck.provider(new KCK(wMj));
        DelegateFactory delegateFactory = new DelegateFactory();
        DelegateFactory.setDelegate(delegateFactory, DoubleCheck.provider(new Afw(provider4, provider5, provider6, DoubleCheck.provider(new rvx(provider7, delegateFactory)), DoubleCheck.provider(new LmR(tNh)))));
        Provider provider8 = DoubleCheck.provider(new zNZ(provider3, delegateFactory, DoubleCheck.provider(new QWZ(jaC, provider))));
        Provider provider9 = DoubleCheck.provider(new McH(c0178PyL, provider));
        zZm.zZm(this, (AccountManager) provider2.mo10268get());
        zZm.zZm(this, (peZ) provider8.mo10268get());
        zZm.zZm(this, Gfw.zZm(c0178PyL, (SignatureVerifier) provider9.mo10268get()));
        this.zQM = new AlexaAudioProviderManagerV2(this, this.zyO, this.jiA);
        this.BIo = this.Qle.createMessageReceiver(this.zQM);
        IBinder binder = this.BIo.getMessenger().getBinder();
        String str = zZm;
        Log.i(str, "binder: " + binder);
        return binder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.Qle.clear();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        AlexaAudioProviderManagerV2 alexaAudioProviderManagerV2 = this.zQM;
        if (alexaAudioProviderManagerV2 != null) {
            alexaAudioProviderManagerV2.onAllClientsDisconnected();
        }
        this.Qle.removeMessageReceiver(this.BIo);
        this.zyO.teardown();
        return false;
    }
}
