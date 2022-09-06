package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.NEv;
import com.amazon.alexa.Ppr;
import com.amazon.alexa.QYV;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaSupportedInitiationType;
import com.amazon.alexa.api.AlexaUserSpeechProviderMetadata;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.api.AlexaWakeWordListener;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.api.WakeWordState;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.wakeword.AudioCapturerAuthority;
import com.amazon.alexa.wakeword.ClassificationData;
import com.amazon.alexa.wakeword.EnrollmentData;
import com.amazon.alexa.wakeword.WakeWordArbitration;
import com.amazon.alexa.wakeword.WakeWordData;
import com.amazon.alexa.wakeword.WakeWordDetectionController;
import com.amazon.alexa.wakeword.WakeWordDetector;
import com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import com.amazon.alexa.wakeword.pryon.WakeWordModelUserParams;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: WakeWordAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class bjR implements WakeWordModelContentProviderHelper.WakeWordsChangedListener {
    public static final List<String> BIo = Arrays.asList("alexa");
    public static final String zZm = "bjR";
    public final AlexaClientEventBus HvC;
    public final WakeWordDetectionController JTe;
    public final jdJ LPk;
    public final FdV Mlj;
    public final AlexaHandsFreeDeviceInformation NXS;
    public final TimeProvider Qgh;
    public final ExtendedClient Qle;
    public final Shr<AlexaWakeWordListener> Tbw;
    public WakeWordState XWf;
    public final WakeWordModelContentProviderHelper dMe;
    public final WakeWordArbitration jiA;
    public final Box lOf;
    public final Object noQ;
    public ExecutorService uuO;
    public final MultiWakeWordFeatureEnabledProvider uzr;
    public final FLQ vkx;
    public final Set<InternalWakeWordPrecondition> wDP;
    public final nno yPL;
    public final tPx zQM;
    public final AudioCapturerAuthority zyO;
    public final WakeWordDownloadManager zzR;

    /* compiled from: WakeWordAuthority.java */
    /* loaded from: classes.dex */
    private class BIo implements WakeWordDetector.WakeWordDetectionListener {
        public /* synthetic */ BIo(Rqp rqp) {
        }

        @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
        public void onClassificationEvent(ClassificationData classificationData) {
        }

        @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
        public void onEnrollmentExampleEvent(EnrollmentData enrollmentData) {
        }

        @Override // com.amazon.alexa.wakeword.WakeWordDetector.WakeWordDetectionListener
        public void onWakewordDetected(WakeWordData wakeWordData) {
            bjR.this.zZm(wakeWordData);
        }
    }

    /* compiled from: WakeWordAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements WakeWordArbitration.ArbitrationListener {
        public /* synthetic */ zZm(Rqp rqp) {
        }

        @Override // com.amazon.alexa.wakeword.WakeWordArbitration.ArbitrationListener
        public void startDetectingWakeWord() {
            bjR.this.jiA();
        }

        @Override // com.amazon.alexa.wakeword.WakeWordArbitration.ArbitrationListener
        public void stopDetectingWakeWord() {
            bjR.this.Qle();
        }
    }

    @Inject
    public bjR(WakeWordDetectionController wakeWordDetectionController, AudioCapturerAuthority audioCapturerAuthority, jdJ jdj, WakeWordArbitration wakeWordArbitration, tPx tpx, nno nnoVar, FLQ flq, Set<InternalWakeWordPrecondition> set, AlexaClientEventBus alexaClientEventBus, FdV fdV, WakeWordDownloadManager wakeWordDownloadManager, Box box, WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, MultiWakeWordFeatureEnabledProvider multiWakeWordFeatureEnabledProvider, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation) {
        ExecutorService newSingleThreadExecutor = ExecutorFactory.newSingleThreadExecutor("wakewordauthority");
        this.Qle = AlexaClient.CLIENT;
        this.wDP = new HashSet();
        this.noQ = new Object();
        this.Qgh = new TimeProvider();
        Log.i(zZm, "In app wake word component created");
        this.JTe = wakeWordDetectionController;
        this.zyO = audioCapturerAuthority;
        this.LPk = jdj;
        this.jiA = wakeWordArbitration;
        this.zQM = tpx;
        this.yPL = nnoVar;
        this.vkx = flq;
        this.HvC = alexaClientEventBus;
        this.jiA.addArbitrationListener(new zZm(null));
        this.Mlj = fdV;
        this.zzR = wakeWordDownloadManager;
        this.lOf = box;
        this.dMe = wakeWordModelContentProviderHelper;
        this.uzr = multiWakeWordFeatureEnabledProvider;
        this.uuO = newSingleThreadExecutor;
        this.Tbw = new Shr<>();
        this.XWf = WakeWordState.builder().build();
        this.NXS = alexaHandsFreeDeviceInformation;
        zZm(BIo, false);
        wakeWordDetectionController.setWakeWordDetectionListener(new BIo(null));
        for (InternalWakeWordPrecondition internalWakeWordPrecondition : set) {
            this.wDP.add(internalWakeWordPrecondition);
            this.jiA.addPrecondition(internalWakeWordPrecondition, false);
        }
        alexaClientEventBus.zZm(this);
        this.dMe.registerWakeWordsListener(this);
        this.uuO.execute(new Rqp(this, wakeWordModelContentProviderHelper));
    }

    public void JTe() {
        Log.i(zZm, "In app wake word component torn down");
        this.HvC.BIo(this);
        this.JTe.setWakeWordDetectionListener(null);
        this.JTe.stopCapturing();
        zZm();
        this.dMe.unregisterWakeWordsListener(this);
        synchronized (this.noQ) {
            this.jiA.teardown();
            for (InternalWakeWordPrecondition internalWakeWordPrecondition : this.wDP) {
                internalWakeWordPrecondition.teardown();
            }
        }
        this.wDP.clear();
        this.zyO.teardown();
    }

    public final void Qle() {
        Log.i(zZm, "In app wake word stopped detecting wake word");
        this.JTe.stopDetectingWakeWord();
        zZm(false);
    }

    public final void jiA() {
        try {
            if (this.JTe.startDetectingWakeWord(this.yPL, this.Mlj) == WakeWordDetector.DetectingStatus.STARTED) {
                zZm(true);
                Log.i(zZm, "In app wake word started detecting wake word");
            } else {
                Log.e(zZm, "Failed to start wake word detection");
            }
        } catch (IOException e) {
            Log.e(zZm, "Failed to start wake word detection", e);
        }
    }

    @Subscribe
    public void on(Ppr ppr) {
        List<Locale> list;
        IlB ilB = (IlB) ppr;
        if (ilB.BIo != Ppr.zZm.LOCALE || !ilB.zQM || this.lOf.zZm() == null || (list = ((jFa) this.lOf.zZm()).zZm) == null || list.isEmpty()) {
            return;
        }
        String.format("Settings successfully updated with new locales: %s. Using primary for wake word: %s.", list.toString(), list.get(0).toLanguageTag());
        this.zzR.downloadWakeWordModelAsync(new WakeWordModelUserParams(list.get(0).toLanguageTag()));
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper.WakeWordsChangedListener
    public void onWakeWordsChanged(List<String> list) {
        C0179Pya.zZm("onWakeWordsChanged ", (Object) list);
        this.uuO.execute(new dbd(this, this.XWf.isListening(), list));
    }

    public void zQM() {
        Log.i(zZm, "log out");
        Qle();
    }

    public final void zyO() {
        for (AlexaWakeWordListener alexaWakeWordListener : this.Tbw.zZm()) {
            C0179Pya.zZm("publishing WakeWordState to listener ", (Object) alexaWakeWordListener);
            alexaWakeWordListener.onWakeWordState(this.XWf);
        }
    }

    public boolean BIo() {
        return this.JTe.isDetectingWakeWord();
    }

    @VisibleForTesting
    public void zZm(ExtendedClient extendedClient, AlexaWakeWordListener alexaWakeWordListener) {
        if (!this.Tbw.zZm((Shr<AlexaWakeWordListener>) alexaWakeWordListener)) {
            List<String> readWakeWords = this.dMe.readWakeWords();
            if (readWakeWords == null) {
                String str = zZm;
                StringBuilder zZm2 = C0179Pya.zZm("wake words list is null, defaulting to default wake words \"");
                zZm2.append(BIo);
                zZm2.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                Log.w(str, zZm2.toString());
                readWakeWords = BIo;
            }
            this.XWf = WakeWordState.builder().setEnabled(this.XWf.isListening()).setWakeWords(readWakeWords).build();
            this.Tbw.zZm(extendedClient, alexaWakeWordListener);
            alexaWakeWordListener.onWakeWordState(this.XWf);
        }
    }

    @Subscribe
    public void on(oJW ojw) {
        Log.i(zZm, "on: RegisterWakeWordListenerEvent");
        Nyb nyb = (Nyb) ojw;
        zZm(nyb.zQM, nyb.jiA);
        zZm(nyb.BIo);
    }

    public void zZm(ExtendedClient extendedClient, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Log.i(zZm, "start listening");
        synchronized (this.noQ) {
            if (alexaDialogExtras != null) {
                this.zQM.zZm(alexaDialogExtras);
            }
            OvX zZm2 = this.vkx.zZm(extendedClient);
            if (this.vkx.isWakeWordAllowed()) {
                zZm(this.XWf.getWakeWords());
            }
            zZm2.zZm(true);
            this.jiA.addPreconditions(zZm2);
        }
    }

    @Subscribe
    public void on(WGo wGo) {
        Log.i(zZm, "on: DeregisterWakeWordListenerEvent");
        mQM mqm = (mQM) wGo;
        this.Tbw.remove(mqm.jiA);
        zZm(mqm.BIo);
    }

    public void zZm(ExtendedClient extendedClient) {
        Log.i(zZm, "stop listening");
        synchronized (this.noQ) {
            OvX zQM = this.vkx.zQM(extendedClient);
            if (!this.vkx.isWakeWordAllowed()) {
                zZm();
            }
            if (zQM != null) {
                this.jiA.removePreconditions(zQM);
            }
        }
    }

    @Subscribe
    public void on(RCa rCa) {
        Log.i(zZm, "on: SetWakeWordsEvent");
        if (!this.uzr.isEnabled()) {
            EJn eJn = (EJn) rCa;
            if (eJn.jiA.size() > 1) {
                Log.w(zZm, "multi ww is disabled, failing setWakeWords event");
                this.HvC.zyO(NEv.zZm.zZm(eJn.BIo, ApiCallFailure.InternalFailure.create("MULTI_WW_DISABLED")));
                return;
            }
        }
        EJn eJn2 = (EJn) rCa;
        List<String> list = eJn2.jiA;
        eOP eop = eJn2.BIo;
        this.zzR.downloadWakeWordModelAsync(new WakeWordModelUserParams(this.dMe.readLastUsedLocale(), list), new zWW(this, eop));
    }

    public void zZm(ExtendedClient extendedClient, boolean z) {
        Log.i(zZm, "updateWakeWordPreconditionForClient");
        synchronized (this.noQ) {
            OvX BIo2 = this.vkx.BIo(extendedClient);
            if (BIo2 != null) {
                BIo2.zZm(z);
            } else {
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append("Client attempted to update wake word without being registered: ");
                sb.append(extendedClient.getId());
                sb.append(", isWakeWordAllowed: ");
                sb.append(z);
                Log.w(str, sb.toString());
            }
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        uyC uyc = (uyC) xzv;
        zZm(uyc.BIo);
        this.Tbw.BIo(uyc.BIo);
    }

    public final void zZm(boolean z) {
        if (this.XWf.isListening() == z) {
            String str = zZm;
            Log.w(str, "listening state is already " + z + ", ignoring");
            return;
        }
        this.XWf = WakeWordState.builder().setWakeWords(this.XWf.getWakeWords()).setEnabled(z).build();
        zyO();
    }

    public final void zZm(List<String> list, boolean z) {
        if (!this.XWf.getWakeWords().equals(list) && !this.NXS.isCurrentDeviceHandsFree()) {
            zZm(list);
        }
        this.XWf = WakeWordState.builder().setWakeWords(list).setEnabled(this.XWf.isListening()).build();
        if (z) {
            zyO();
        }
    }

    public final void zZm(List<String> list) {
        AlexaUserSpeechProviderMetadata create = AlexaUserSpeechProviderMetadata.create(Collections.singleton(AlexaSupportedInitiationType.WAKE_WORD), new HashSet(list), AlexaUserSpeechProviderScope.APPLICATION);
        if (this.LPk.zZm(this.zQM) != null) {
            this.LPk.zZm(this.Qle, this.zQM);
        }
        if (this.LPk.zZm(this.zQM) == null) {
            this.LPk.zZm(this.Qle, this.zQM, create);
        }
    }

    public final void zZm() {
        if (this.LPk.zZm(this.zQM) != null) {
            this.LPk.zZm(this.Qle, this.zQM);
        }
    }

    public final void zZm(WakeWordData wakeWordData) {
        Log.i(zZm, "In app wake word detected valid wake word");
        this.HvC.zyO(new ocm(this.Qgh.elapsedRealTime()));
        this.zQM.zZm(wakeWordData);
        String wakeWordName = wakeWordData.getAlexaWakeWord().getWakeWordName();
        UWt zZm2 = this.LPk.zZm(this.zQM);
        if (zZm2 == null) {
            Log.e(zZm, "Wake word speech provider is not registered");
            return;
        }
        this.HvC.zyO(QYV.zZm.zZm(esV.WAKE_WORD, zZm2, AlexaDialogRequest.builder().setInvocationType("AlexaApp.WakeWord").build(), wakeWordName));
    }

    public final void zZm(eOP eop) {
        this.HvC.zyO(NEv.zQM.zZm(eop));
    }
}
