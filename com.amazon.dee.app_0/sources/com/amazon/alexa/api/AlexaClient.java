package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.AHr;
import com.amazon.alexa.AYd;
import com.amazon.alexa.AgS;
import com.amazon.alexa.Arv;
import com.amazon.alexa.AxK;
import com.amazon.alexa.BPW;
import com.amazon.alexa.BeW;
import com.amazon.alexa.BnD;
import com.amazon.alexa.Boy;
import com.amazon.alexa.BrT;
import com.amazon.alexa.C0168Dge;
import com.amazon.alexa.C0172Lqm;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.C0199dlc;
import com.amazon.alexa.C0234ubm;
import com.amazon.alexa.C0237wXy;
import com.amazon.alexa.Ccz;
import com.amazon.alexa.Crs;
import com.amazon.alexa.DJb;
import com.amazon.alexa.DJw;
import com.amazon.alexa.DtD;
import com.amazon.alexa.DxN;
import com.amazon.alexa.EEN;
import com.amazon.alexa.EJn;
import com.amazon.alexa.EOT;
import com.amazon.alexa.FQX;
import com.amazon.alexa.FsB;
import com.amazon.alexa.Fsz;
import com.amazon.alexa.GvA;
import com.amazon.alexa.HHC;
import com.amazon.alexa.HTC;
import com.amazon.alexa.HVk;
import com.amazon.alexa.IOV;
import com.amazon.alexa.IYE;
import com.amazon.alexa.IYJ;
import com.amazon.alexa.IcB;
import com.amazon.alexa.Ixs;
import com.amazon.alexa.JOD;
import com.amazon.alexa.JXl;
import com.amazon.alexa.JgM;
import com.amazon.alexa.JjI;
import com.amazon.alexa.Jkd;
import com.amazon.alexa.Jpe;
import com.amazon.alexa.Jvr;
import com.amazon.alexa.KLb;
import com.amazon.alexa.KrR;
import com.amazon.alexa.KvZ;
import com.amazon.alexa.LBB;
import com.amazon.alexa.LPk;
import com.amazon.alexa.Lnt;
import com.amazon.alexa.LrI;
import com.amazon.alexa.LuX;
import com.amazon.alexa.MBE;
import com.amazon.alexa.MNR;
import com.amazon.alexa.MSk;
import com.amazon.alexa.Msx;
import com.amazon.alexa.NDW;
import com.amazon.alexa.NEv;
import com.amazon.alexa.NTk;
import com.amazon.alexa.NXS;
import com.amazon.alexa.NoB;
import com.amazon.alexa.Nyb;
import com.amazon.alexa.OGT;
import com.amazon.alexa.OPl;
import com.amazon.alexa.OeW;
import com.amazon.alexa.QIY;
import com.amazon.alexa.QMR;
import com.amazon.alexa.QYV;
import com.amazon.alexa.QeM;
import com.amazon.alexa.Qgh;
import com.amazon.alexa.Qoi;
import com.amazon.alexa.QtV;
import com.amazon.alexa.RfA;
import com.amazon.alexa.RrI;
import com.amazon.alexa.SCB;
import com.amazon.alexa.Seo;
import com.amazon.alexa.Shr;
import com.amazon.alexa.Svl;
import com.amazon.alexa.TEg;
import com.amazon.alexa.TrI;
import com.amazon.alexa.TtM;
import com.amazon.alexa.UWt;
import com.amazon.alexa.UaM;
import com.amazon.alexa.UcG;
import com.amazon.alexa.VEQ;
import com.amazon.alexa.WXj;
import com.amazon.alexa.Wyh;
import com.amazon.alexa.XPi;
import com.amazon.alexa.XUD;
import com.amazon.alexa.Xvi;
import com.amazon.alexa.YKQ;
import com.amazon.alexa.ZIT;
import com.amazon.alexa.adp;
import com.amazon.alexa.aeS;
import com.amazon.alexa.api.AlexaClient;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.bNQ;
import com.amazon.alexa.bXh;
import com.amazon.alexa.bij;
import com.amazon.alexa.bjR;
import com.amazon.alexa.blL;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import com.amazon.alexa.brA;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateProvider;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageTransformer;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.dAN;
import com.amazon.alexa.dRG;
import com.amazon.alexa.dVL;
import com.amazon.alexa.eCj;
import com.amazon.alexa.eDG;
import com.amazon.alexa.eOP;
import com.amazon.alexa.esV;
import com.amazon.alexa.fGu;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import com.amazon.alexa.fjm;
import com.amazon.alexa.fuM;
import com.amazon.alexa.gMz;
import com.amazon.alexa.gSO;
import com.amazon.alexa.hlu;
import com.amazon.alexa.hrT;
import com.amazon.alexa.jbU;
import com.amazon.alexa.jcJ;
import com.amazon.alexa.jdJ;
import com.amazon.alexa.kDa;
import com.amazon.alexa.kbp;
import com.amazon.alexa.ktr;
import com.amazon.alexa.lEV;
import com.amazon.alexa.lhN;
import com.amazon.alexa.mCQ;
import com.amazon.alexa.mMl;
import com.amazon.alexa.mQM;
import com.amazon.alexa.nOx;
import com.amazon.alexa.oGE;
import com.amazon.alexa.pBR;
import com.amazon.alexa.pZY;
import com.amazon.alexa.pbo;
import com.amazon.alexa.pwz;
import com.amazon.alexa.qxC;
import com.amazon.alexa.rte;
import com.amazon.alexa.shl;
import com.amazon.alexa.system.UserInactivityAuthority;
import com.amazon.alexa.szT;
import com.amazon.alexa.tNI;
import com.amazon.alexa.tjk;
import com.amazon.alexa.tkT;
import com.amazon.alexa.tol;
import com.amazon.alexa.tsJ;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.uyC;
import com.amazon.alexa.vYS;
import com.amazon.alexa.vkx;
import com.amazon.alexa.vly;
import com.amazon.alexa.xUA;
import com.amazon.alexa.xZV;
import com.amazon.alexa.yED;
import com.amazon.alexa.yYy;
import com.amazon.alexa.yhM;
import com.amazon.alexa.yjS;
import com.amazon.alexa.zDj;
import com.amazon.alexa.zMV;
import com.amazon.alexa.zZD;
import com.amazon.alexa.zmg;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
@Singleton
/* loaded from: classes6.dex */
public class AlexaClient {
    public static final String API_CALL_FAILED_MESSAGE = "API call failed";
    public static final ExtendedClient CLIENT = new ExtendedClient("internal");
    public static final String TAG = "AlexaClient";
    public final QIY alertsAuthority;
    public final QtV alertsBroadcastReceiver;
    public final LPk alexaAudioPlaybackAuthority;
    public final AlexaClientEventBus alexaClientEventBus;
    public final AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation;
    public final tkT alexaLauncherCapabilityAgent;
    public final NDW alexaMediaPlaybackAuthority;
    public final AlexaNotificationManager alexaNotificationManager;
    public final vkx alexaStateAuthority;
    public final Qgh alexaSuppressionAuthority;
    public final NXS alexaUserSpeechAuthority;
    public final JXl apiCallAuthority;
    public final TrI applicationManagerCapabilityAgent;
    public final shl attachmentStore;
    public final hrT attentionSystemAuthority;
    public final AxK attentionSystemLatencyProcessor;
    public final OPl audioFocusManager;
    public final HVk audioPlayerCapabilityAgent;
    public final oGE audioPlayerComponentStateAuthority;
    public final tol authAuthority;
    public final mCQ authChangeBroadcastReceiver;
    public final eDG avsConnectionStateAuthority;
    public final ktr batteryLevelReceiver;
    public final tjk becomingNoisyManager;
    public final bXh capabilityAgentManager;
    public final pZY capabilityAuthority;
    public final yYy cardRendererCapabilityAgent;
    public final IYJ clientConnectionsAuthority;
    public final lhN componentStateAuthority;
    public final qxC connectivityAuthority;
    public final Context context;
    public final IcB deviceLocaleChangedBroadcastReceiver;
    public final XPi deviceTimeZoneChangedBroadCastReceiver;
    public final xUA endpointAuthority;
    public final pbo eventBroadcastSender;
    public final JgM externalCapabilityAgentFinder;
    public final LrI externalCapabilityAgentRegistry;
    public final YKQ externalComponentStateAuthority;
    public final DJw externalMediaPlayerCapabilityAgent;
    public final Boy externalMediaPlayerStateProvider;
    public final hlu failedInteractionTracker;
    public final gSO featureFlagConfigurationAuthority;
    public final eCj geolocationComponent;
    public final Gson gson;
    public final jbU instrumentationReceiver;
    public final zMV interactionModelCapabilityAgent;
    public final brA interactionScheduler;
    public final C0234ubm internalCapabilityAgentRegistry;
    public final lEV internalComponentStateProviders;
    public final adp internalTrustedStatesProvider;
    public boolean isTornDown;
    public final MBE localeAuthority;
    public final ZIT locationProviderChangedBroadcastReceiver;
    public final Msx lockscreenManager;
    public final Lnt mediaBrowserPlayerFinder;
    public final zmg mediaPlayersAuthority;
    public final Qoi messageProcessingSequencer;
    public final KvZ messageReceiverAuthority;
    public final MessageTransformer messageTransformer;
    public final Fsz metricsAuthority;
    public final Wyh multiTurnDialogAuthority;
    public final dAN networkAuthority;
    public final szT offlinePromptDownloadInitiator;
    public final QMR packagesChangedBroadcastReceiver;
    public final Lazy<Arv> phoneCallControllerComponentStateAuthority;
    public final HTC playbackStateReporterProvider;
    public final DtD powerConnectionReceiver;
    public final BrT promptPlayer;
    public final XUD readinessAuthority;
    public final BluetoothScoController scoController;
    public final aeS settingsAuthority;
    public final AHr settingsController;
    public final ScheduledExecutorService sharedExecutorService;
    public final jcJ soundAuthority;
    public final VEQ soundEffectPlayer;
    public final LuX speechRecognizerCapabilityAgent;
    public final fjm speechSynthesizerCapabilityAgent;
    public final yjS textAuthority;
    public final Jvr textCapabilityAgent;
    public final Ccz textInteractionAuthority;
    public final zDj timeZoneAuthority;
    public final JOD uiEventBroadcastReceiver;
    public final vYS uiManager;
    public final UserInactivityAuthority userInactivityAuthority;
    public final OGT userSpeechProviderAuthority;
    public final jdJ userSpeechProviderRegistry;
    public final kDa visualFocusManager;
    public final gMz visualInteractionScheduler;
    public final rte voiceInteractionAuthority;
    public final KrR volumeChangeHandler;
    public final RfA wakeWordArtifactDownload;
    public final bjR wakeWordAuthority;
    public final long startTime = SystemClock.elapsedRealtime();
    public final Shr<ForceDisconnectMessageSender> disconnectListeners = new Shr<>();
    public final Shr<ClientConnectionControllerMessageSender> connectionControllers = new Shr<>();

    /* renamed from: com.amazon.alexa.api.AlexaClient$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements Runnable {
        public final /* synthetic */ ExtendedClient val$client;

        public AnonymousClass1(ExtendedClient extendedClient) {
            this.val$client = extendedClient;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void zZm(boolean z) {
            if (z) {
                AlexaClient.this.localeAuthority.zQM(z);
                AlexaClient.this.timeZoneAuthority.zQM();
                return;
            }
            AlexaClient.this.settingsController.zZm();
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                AlexaClient.this.authAuthority.getToken();
                if (AlexaClient.this.authAuthority.zyO()) {
                    AlexaClient.this.featureFlagConfigurationAuthority.zZm(Feature.ALEXA_VOX_ANDROID_DLS, new FeatureFlagListener() { // from class: com.amazon.alexa.api.-$$Lambda$AlexaClient$1$peGDAn5whA_rKKDylBDMdoTt0Eo
                        @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
                        public final void onFeatureServiceReady(boolean z) {
                            AlexaClient.AnonymousClass1.this.zZm(z);
                        }
                    });
                    AlexaClient.this.alexaClientEventBus.zyO(AgS.BIo());
                    AlexaClient.this.tellClientToStartService(this.val$client);
                } else {
                    Log.i(AlexaClient.TAG, "No account registered. Stopping service");
                    AlexaClient.this.clientConnectionsAuthority.BIo(this.val$client);
                    AlexaClient.this.alexaClientEventBus.zyO(new EEN());
                }
            } catch (Exception e) {
                Log.e(AlexaClient.TAG, "Caught exception while checking for login state: ", e);
                AlexaClient.this.clientConnectionsAuthority.BIo(this.val$client);
                AlexaClient.this.alexaClientEventBus.zyO(new EEN());
            }
        }
    }

    /* renamed from: com.amazon.alexa.api.AlexaClient$3  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$client$alexaservice$networking$SendMessageCallback$DropReason = new int[TtM.zZm.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$client$alexaservice$networking$SendMessageCallback$DropReason[TtM.zZm.AUTHORIZATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$client$alexaservice$networking$SendMessageCallback$DropReason[TtM.zZm.NETWORK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$client$alexaservice$networking$SendMessageCallback$DropReason[TtM.zZm.TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$client$alexaservice$networking$SendMessageCallback$DropReason[TtM.zZm.TEARDOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Inject
    public AlexaClient(@Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, IYJ iyj, vkx vkxVar, NXS nxs, LPk lPk, AlexaClientEventBus alexaClientEventBus, AlexaNotificationManager alexaNotificationManager, QIY qiy, tol tolVar, bXh bxh, Context context, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation, Qoi qoi, brA bra, gMz gmz, FQX fqx, IOV iov, lhN lhn, OGT ogt, jdJ jdj, fjm fjmVar, QeM qeM, Lazy<SCB> lazy, Lazy<blL> lazy2, LuX luX, HVk hVk, oGE oge, yED yed, yYy yyy, AHr aHr, dAN dan, tjk tjkVar, UserInactivityAuthority userInactivityAuthority, DJb dJb, VEQ veq, yhM yhm, NDW ndw, KrR krR, Fsz fsz, pbo pboVar, qxC qxc, Qgh qgh, BrT brT, zmg zmgVar, Lnt lnt, JgM jgM, KvZ kvZ, C0234ubm c0234ubm, LrI lrI, YKQ ykq, lEV lev, pZY pzy, MessageTransformer messageTransformer, shl shlVar, vYS vys, Msx msx, DJw dJw, Boy boy, HTC htc, jbU jbu, jcJ jcj, OPl oPl, kDa kda, zMV zmv, Wyh wyh, aeS aes, pBR pbr, TrI trI, BluetoothScoController bluetoothScoController, IcB icB, XPi xPi, QMR qmr, mCQ mcq, ZIT zit, JOD jod, QtV qtV, ktr ktrVar, DtD dtD, bjR bjr, Lazy<Arv> lazy3, Gson gson, rte rteVar, Ccz ccz, AxK axK, XUD xud, tkT tkt, hrT hrt, @Named("fire_os_tablet_capabilities") Set<CapabilityAgent> set, AYd aYd, hlu hluVar, JXl jXl, gSO gso, eDG edg, C0237wXy c0237wXy, Jvr jvr, yjS yjs, adp adpVar, szT szt, RfA rfA, xUA xua, MBE mbe, zDj zdj) {
        this.sharedExecutorService = scheduledExecutorService;
        this.clientConnectionsAuthority = iyj;
        this.alexaStateAuthority = vkxVar;
        this.alexaUserSpeechAuthority = nxs;
        this.alexaAudioPlaybackAuthority = lPk;
        this.alexaMediaPlaybackAuthority = ndw;
        this.alexaClientEventBus = alexaClientEventBus;
        this.alexaNotificationManager = alexaNotificationManager;
        this.alertsAuthority = qiy;
        this.authAuthority = tolVar;
        this.capabilityAgentManager = bxh;
        this.context = context;
        this.alexaHandsFreeDeviceInformation = alexaHandsFreeDeviceInformation;
        this.messageProcessingSequencer = qoi;
        this.interactionScheduler = bra;
        this.visualInteractionScheduler = gmz;
        this.externalComponentStateAuthority = ykq;
        this.internalComponentStateProviders = lev;
        this.componentStateAuthority = lhn;
        this.speechRecognizerCapabilityAgent = luX;
        this.speechSynthesizerCapabilityAgent = fjmVar;
        this.audioPlayerCapabilityAgent = hVk;
        this.networkAuthority = dan;
        this.userSpeechProviderAuthority = ogt;
        this.userSpeechProviderRegistry = jdj;
        this.settingsController = aHr;
        this.becomingNoisyManager = tjkVar;
        this.userInactivityAuthority = userInactivityAuthority;
        this.cardRendererCapabilityAgent = yyy;
        this.audioPlayerComponentStateAuthority = oge;
        this.soundEffectPlayer = veq;
        this.volumeChangeHandler = krR;
        this.connectivityAuthority = qxc;
        this.alexaSuppressionAuthority = qgh;
        this.promptPlayer = brT;
        this.mediaPlayersAuthority = zmgVar;
        this.mediaBrowserPlayerFinder = lnt;
        this.externalCapabilityAgentFinder = jgM;
        this.messageReceiverAuthority = kvZ;
        this.metricsAuthority = fsz;
        this.eventBroadcastSender = pboVar;
        this.capabilityAuthority = pzy;
        this.internalCapabilityAgentRegistry = c0234ubm;
        this.externalCapabilityAgentRegistry = lrI;
        this.messageTransformer = messageTransformer;
        this.attachmentStore = shlVar;
        this.gson = gson;
        this.uiManager = vys;
        this.lockscreenManager = msx;
        this.instrumentationReceiver = jbu;
        this.soundAuthority = jcj;
        this.audioFocusManager = oPl;
        this.visualFocusManager = kda;
        this.interactionModelCapabilityAgent = zmv;
        this.multiTurnDialogAuthority = wyh;
        this.settingsAuthority = aes;
        this.externalMediaPlayerCapabilityAgent = dJw;
        this.externalMediaPlayerStateProvider = boy;
        this.playbackStateReporterProvider = htc;
        this.applicationManagerCapabilityAgent = trI;
        this.scoController = bluetoothScoController;
        this.deviceLocaleChangedBroadcastReceiver = icB;
        this.deviceTimeZoneChangedBroadCastReceiver = xPi;
        this.packagesChangedBroadcastReceiver = qmr;
        this.authChangeBroadcastReceiver = mcq;
        this.locationProviderChangedBroadcastReceiver = zit;
        this.uiEventBroadcastReceiver = jod;
        this.alertsBroadcastReceiver = qtV;
        this.batteryLevelReceiver = ktrVar;
        this.powerConnectionReceiver = dtD;
        this.voiceInteractionAuthority = rteVar;
        this.textInteractionAuthority = ccz;
        this.wakeWordAuthority = bjr;
        this.phoneCallControllerComponentStateAuthority = lazy3;
        this.attentionSystemLatencyProcessor = axK;
        this.readinessAuthority = xud;
        this.alexaLauncherCapabilityAgent = tkt;
        this.attentionSystemAuthority = hrt;
        this.failedInteractionTracker = hluVar;
        this.apiCallAuthority = jXl;
        this.featureFlagConfigurationAuthority = gso;
        this.avsConnectionStateAuthority = edg;
        this.alexaClientEventBus.zZm(this);
        this.textCapabilityAgent = jvr;
        this.textAuthority = yjs;
        this.internalTrustedStatesProvider = adpVar;
        this.offlinePromptDownloadInitiator = szt;
        this.wakeWordArtifactDownload = rfA;
        this.endpointAuthority = xua;
        this.localeAuthority = mbe;
        this.timeZoneAuthority = zdj;
        this.isTornDown = false;
        ((fGu) jbu).zZm();
        gso.JTe.execute(new HHC(gso));
        krR.zZm();
        qxc.zyO();
        icB.zZm();
        xPi.zZm();
        qmr.zZm();
        mcq.zZm();
        zit.zZm();
        LocalBroadcastManager.getInstance(jod.BIo).registerReceiver(jod, new IntentFilter("com.amazon.alexa.intent.action.PUBLISH_UI_EVENT"));
        qtV.zZm();
        if (!ktrVar.jiA) {
            String str = ktr.zZm;
            ktrVar.BIo.registerReceiver(ktrVar, ktrVar.zyO);
            ktrVar.jiA = true;
        } else {
            String str2 = ktr.zZm;
        }
        if (!dtD.jiA) {
            String str3 = DtD.zZm;
            dtD.BIo.registerReceiver(dtD, dtD.zyO);
            dtD.jiA = true;
        } else {
            String str4 = DtD.zZm;
        }
        bluetoothScoController.register();
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.SpeechSynthesizer.zZm, fjmVar);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.SpeechRecognizer.zZm, luX);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.System.zZm, yed);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Speaker.zZm, dJb);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.AudioPlayer.zZm, hVk);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.CardRenderer.zZm, yyy);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Navigation.zZm, yhm);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.ExternalMediaPlayer.zZm, dJw);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Alexa.FavoritesController.zZm, dJw);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Alexa.PlaybackController.zZm, dJw);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Alexa.PlaylistController.zZm, dJw);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Alexa.SeekController.zZm, dJw);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.InteractionModel.zZm, zmv);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.ApplicationManager.zZm, trI);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Alexa.Launcher.zZm, tkt);
        this.internalCapabilityAgentRegistry.zZm(AvsApiConstants.Input.Text.zZm, jvr);
        registerInternalCapabilityAgents(set);
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
            this.geolocationComponent = lazy2.mo358get();
        } else {
            this.geolocationComponent = lazy.mo358get();
        }
        registerInternalComponentStateProviders(this.geolocationComponent, qeM, oge, dJb, fqx, iov, boy, htc, pbr, c0237wXy, aYd);
        if (alexaHandsFreeDeviceInformation.isCurrentDeviceHandsFree()) {
            registerInternalComponentStateProviders(lazy3.mo358get());
        }
        dan.zZm();
        alexaClientEventBus.jiA(new C0168Dge());
        String str5 = TAG;
        StringBuilder zZm = C0179Pya.zZm("Initialized AlexaClient v");
        zZm.append(Versions.CURRENT_API_VERSION.getValue());
        Log.i(str5, zZm.toString());
    }

    private void checkAccountStatus(ExtendedClient extendedClient) {
        Log.i(TAG, "checkAccountStatus");
        this.sharedExecutorService.submit(new AnonymousClass1(extendedClient));
    }

    private UcG createSendMessageCallbackFrom(final eOP eop) {
        return new UcG() { // from class: com.amazon.alexa.api.AlexaClient.2
            @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
            public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
                if (num != null) {
                    AlexaClient.this.alexaClientEventBus.zyO(NEv.zZm.zZm(eop, ApiCallFailure.ServerErrorFailure.create(AlexaClient.API_CALL_FAILED_MESSAGE, num)));
                } else if (exc != null) {
                    AlexaClient.this.alexaClientEventBus.zyO(NEv.zZm.zZm(eop, ApiCallFailure.InternalFailure.create(AlexaClient.API_CALL_FAILED_MESSAGE, exc)));
                } else {
                    AlexaClient.this.alexaClientEventBus.zyO(NEv.zZm.zZm(eop, ApiCallFailure.UnknownFailure.create(AlexaClient.API_CALL_FAILED_MESSAGE)));
                }
            }

            @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
            public void onRequestDropped(RrI rrI, TtM.zZm zzm) {
                int ordinal = zzm.ordinal();
                if (ordinal == 1) {
                    AlexaClient.this.alexaClientEventBus.zyO(NEv.zZm.zZm(eop, ApiCallFailure.NetworkFailure.create(AlexaClient.API_CALL_FAILED_MESSAGE, null)));
                } else if (ordinal != 2) {
                    AlexaClient.this.alexaClientEventBus.zyO(NEv.zZm.zZm(eop, ApiCallFailure.TimeoutFailure.create(AlexaClient.API_CALL_FAILED_MESSAGE)));
                } else {
                    AlexaClient.this.alexaClientEventBus.zyO(NEv.zZm.zZm(eop, ApiCallFailure.AuthorizationFailure.create(AlexaClient.API_CALL_FAILED_MESSAGE)));
                }
            }

            @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
            public void onSuccess(RrI rrI, Collection<Message> collection) {
                AlexaClient.this.alexaClientEventBus.zyO(NEv.zQM.zZm(eop));
            }
        };
    }

    private void disconnectClients() {
        for (ExtendedClient extendedClient : this.clientConnectionsAuthority.zQM()) {
            this.alexaClientEventBus.zyO(xZV.zZm(extendedClient));
        }
    }

    private List<String> getLanguageTagsFromLocales(List<java.util.Locale> list) {
        ArrayList arrayList = new ArrayList();
        for (java.util.Locale locale : list) {
            arrayList.add(locale.toLanguageTag());
        }
        return arrayList;
    }

    private List<java.util.Locale> getLocalesFromLanguageTags(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String str : list) {
                arrayList.add(java.util.Locale.forLanguageTag(str));
            }
        }
        return arrayList;
    }

    private void internalNext() {
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaAudioPlaybackAuthority.zZm();
    }

    private void internalPrevious() {
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaAudioPlaybackAuthority.zyO();
    }

    private void registerInternalCapabilityAgents(Set<CapabilityAgent> set) {
        for (CapabilityAgent capabilityAgent : set) {
            for (Capability capability : capabilityAgent.getCapabilities()) {
                this.internalCapabilityAgentRegistry.zZm(Namespace.create(capability.getInterface().getValue()), capabilityAgent);
            }
        }
    }

    private void registerInternalComponentStateProviders(dRG... drgArr) {
        for (dRG drg : drgArr) {
            this.internalComponentStateProviders.zZm.put(drg.zZm(), drg);
        }
    }

    private void startListening(boolean z, AlexaDialogExtras alexaDialogExtras) {
        esV zZm;
        if (!verifyLoginStatus()) {
            this.alexaClientEventBus.zyO(kbp.zyO.zZm(alexaDialogExtras.getInvocationType(), bij.AUTHORIZATION_ERROR));
            return;
        }
        if (z) {
            zZm = esV.NOTIFICATION_TAP;
        } else {
            zZm = esV.zZm(alexaDialogExtras.getLaunchType());
        }
        this.userInactivityAuthority.zQM();
        if (this.lockscreenManager.zZm()) {
            this.alexaClientEventBus.zyO(kbp.zyO.zZm(alexaDialogExtras.getInvocationType(), mMl.SCREEN_LOCKED));
        } else {
            this.alexaStateAuthority.zZm(zZm, alexaDialogExtras);
        }
    }

    private void stopListening() {
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaStateAuthority.Qle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tellClientToStartService(ExtendedClient extendedClient) {
        for (ClientConnectionControllerMessageSender clientConnectionControllerMessageSender : this.connectionControllers.zZm(extendedClient)) {
            try {
                clientConnectionControllerMessageSender.onStartService();
            } catch (RemoteException e) {
                Log.e(TAG, "Caught exception while telling client to start service: ", e);
                this.alexaClientEventBus.zyO(xZV.zZm(extendedClient));
            }
        }
    }

    private boolean verifyLoginStatus() {
        try {
            boolean zyO = this.authAuthority.zyO();
            if (!zyO) {
                Log.e(TAG, "Attempting to use AlexaService without a logged in user.");
            }
            return zyO;
        } catch (Exception e) {
            Log.e(TAG, "Exception encountered while verifying login status", e);
            return false;
        }
    }

    public synchronized void cacheContexts(ExtendedClient extendedClient, Set<AlexaContext> set) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": cacheContexts", TAG);
        this.externalComponentStateAuthority.zZm(extendedClient, ComponentState.createMultiple(set));
    }

    public synchronized void cancelUserInteraction(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": cancelUserInteraction", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaStateAuthority.jiA();
    }

    public synchronized void clearContextCache(ExtendedClient extendedClient, Set<String> set) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": clearContextCache", TAG);
        this.externalComponentStateAuthority.BIo(extendedClient, Namespace.createMultiple(set));
    }

    public synchronized void deregisterAlertsListener(ExtendedClient extendedClient, AlertsListener alertsListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlertsListener", TAG);
        this.alertsAuthority.BIo(alertsListener);
    }

    public synchronized void deregisterAlexaArtifactDownloadListener(ExtendedClient extendedClient, AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaArtifactDownloadListener", TAG);
        this.wakeWordArtifactDownload.zZm(alexaArtifactDownloadListener);
    }

    public synchronized void deregisterAlexaAudioPlaybackListener(ExtendedClient extendedClient, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaAudioPlaybackListener", TAG);
        this.alexaAudioPlaybackAuthority.zZm(alexaAudioPlaybackListenerProxy);
    }

    public synchronized void deregisterAlexaAudioPlaybackStatusListener(ExtendedClient extendedClient, AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaAudioPlaybackStatusListener", TAG);
        this.alexaAudioPlaybackAuthority.zZm(alexaAudioPlaybackStatusListener);
    }

    public synchronized void deregisterAlexaCaptionListener(ExtendedClient extendedClient, AlexaCaptionListener alexaCaptionListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaCaptionListener", TAG);
        this.speechSynthesizerCapabilityAgent.zZm(alexaCaptionListener);
    }

    public synchronized void deregisterAlexaCardRendererListener(ExtendedClient extendedClient, GvA gvA) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaCardRendererListener", TAG);
        this.cardRendererCapabilityAgent.zZm(gvA);
    }

    public synchronized void deregisterAlexaMediaPlaybackListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaAudioPlaybackListener", TAG);
        this.alexaClientEventBus.zyO(new C0199dlc(eOP.zZm(apiCallback.zZm, ApiName.HvC), extendedClient, apiCallback, alexaMediaPlaybackListener));
    }

    public synchronized void deregisterAlexaPlayerInfoCardListener(ExtendedClient extendedClient, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaPlayerInfoCardListener", TAG);
        this.cardRendererCapabilityAgent.zZm(alexaPlayerInfoCardListenerProxy);
    }

    public synchronized void deregisterAlexaSettingsListener(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaSettingsListener", TAG);
        this.settingsController.zZm(alexaSettingsListenerProxy);
        this.localeAuthority.zZm(alexaSettingsListenerProxy);
    }

    public synchronized void deregisterAlexaStateListener(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaStateListener", TAG);
        this.alexaStateAuthority.zZm(alexaStateListenerProxy);
    }

    @Deprecated
    public synchronized void deregisterAlexaTemplateCardListener(ExtendedClient extendedClient, AlexaTemplateCardListenerProxy alexaTemplateCardListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaTemplateCardListener", TAG);
        Log.w(TAG, "Template cards are no longer supported. Ignoring deregistration");
    }

    public synchronized void deregisterAlexaUserSpeechListener(ExtendedClient extendedClient, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaUserSpeechListener", TAG);
        this.alexaUserSpeechAuthority.zZm(alexaUserSpeechListenerProxy);
    }

    public synchronized void deregisterAlexaWakeWordListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaWakeWordListener alexaWakeWordListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAlexaWakeWordListener", TAG);
        this.alexaClientEventBus.zyO(new mQM(eOP.zZm(apiCallback.zZm, ApiName.wDP), extendedClient, apiCallback, alexaWakeWordListener));
    }

    public synchronized void deregisterAttentionSystemListener(ExtendedClient extendedClient, AlexaAttentionSystemListener alexaAttentionSystemListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAttentionSystemListener", TAG);
        this.alexaStateAuthority.zZm(alexaAttentionSystemListener);
    }

    public synchronized void deregisterAttentionSystemSettingsListener(ExtendedClient extendedClient, AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterAttentionSystemSettingsListener", TAG);
        this.attentionSystemAuthority.zZm(alexaAttentionSystemSettingsListener);
    }

    public synchronized void deregisterClientConnectionController(ExtendedClient extendedClient, ClientConnectionControllerMessageSender clientConnectionControllerMessageSender) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterClientConnectionController", TAG);
        this.clientConnectionsAuthority.BIo(extendedClient);
        this.connectionControllers.remove(clientConnectionControllerMessageSender);
    }

    public synchronized void deregisterContextProvider(ExtendedClient extendedClient, AlexaContextProviderProxy alexaContextProviderProxy) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": deregisterContextProvider");
        this.externalComponentStateAuthority.zZm(extendedClient, new ExternalComponentStateProvider(new LegacyContextProvider(alexaContextProviderProxy), extendedClient));
    }

    public synchronized void deregisterContextsProvider(ExtendedClient extendedClient, AlexaContextsProvider alexaContextsProvider) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": deregisterContextsProvider");
        this.externalComponentStateAuthority.zZm(extendedClient, new ExternalComponentStateProvider(alexaContextsProvider, extendedClient));
    }

    public synchronized void deregisterDriveModeListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaDriveModeListener alexaDriveModeListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterDriveModeListener", TAG);
        this.alexaClientEventBus.zyO(new vly(eOP.zZm(apiCallback.zZm, ApiName.jiA), extendedClient, apiCallback, alexaDriveModeListener));
    }

    public synchronized void deregisterExpectTextListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaExpectTextListener alexaExpectTextListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterExpectTextListener", TAG);
        this.alexaClientEventBus.zyO(new Crs(eOP.zZm(apiCallback.zZm, ApiName.uuO), extendedClient, apiCallback, alexaExpectTextListener));
    }

    public synchronized void deregisterForceDisconnectListener(ExtendedClient extendedClient, ForceDisconnectMessageSender forceDisconnectMessageSender) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterForceDisconnectListener", TAG);
        this.clientConnectionsAuthority.BIo(extendedClient);
        this.disconnectListeners.remove(forceDisconnectMessageSender);
    }

    public synchronized void deregisterLocalesListener(ExtendedClient extendedClient, AlexaLocalesListener alexaLocalesListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterLocalesListener", TAG);
        this.settingsController.zZm(alexaLocalesListener);
        this.localeAuthority.zZm(alexaLocalesListener);
    }

    public synchronized void deregisterMetricsListener(ExtendedClient extendedClient, AlexaMetricsListener alexaMetricsListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterMetricsListener", TAG);
        this.metricsAuthority.zZm(alexaMetricsListener);
    }

    public synchronized void deregisterReadinessListener(ExtendedClient extendedClient, AlexaReadinessListener alexaReadinessListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterForReadiness", TAG);
        this.readinessAuthority.zZm(alexaReadinessListener);
    }

    public synchronized void deregisterSupportedLocalesListener(ExtendedClient extendedClient, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterSupportedLocalesListener", TAG);
        this.settingsController.zZm(alexaSupportedLocalesListener);
        this.localeAuthority.zZm(alexaSupportedLocalesListener);
    }

    public synchronized void deregisterTextResponseListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaTextResponseListener alexaTextResponseListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterTextResponseListener", TAG);
        this.alexaClientEventBus.zyO(new KLb(eOP.zZm(apiCallback.zZm, ApiName.Mlj), extendedClient, apiCallback, alexaTextResponseListener));
    }

    public synchronized void deregisterUserPerceivedLatencyListener(ExtendedClient extendedClient, bNQ bnq) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterUserPerceivedLatencyListener", TAG);
        this.metricsAuthority.zZm(bnq);
    }

    public synchronized void deregisterUserSpeechProvider(ExtendedClient extendedClient, AlexaUserSpeechProvider alexaUserSpeechProvider) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": deregisterUserSpeechProvider", TAG);
        this.userSpeechProviderRegistry.zZm(extendedClient, alexaUserSpeechProvider);
    }

    public synchronized void disableExternalCapabilityAgent(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": disableExternalCapabilityAgent", TAG);
        this.alexaClientEventBus.zyO(new dVL(eOP.zZm(apiCallback.zZm, ApiName.XWf), extendedClient, apiCallback, alexaCapabilityAgentRegistration));
    }

    public synchronized void enableExternalCapabilityAgent(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": enableExternalCapabilityAgent", TAG);
        this.alexaClientEventBus.zyO(new tsJ(eOP.zZm(apiCallback.zZm, ApiName.Tbw), extendedClient, apiCallback, alexaCapabilityAgentRegistration));
    }

    public synchronized String getLocale(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": getLocale", TAG);
        java.util.Locale Qle = this.localeAuthority.Qle();
        if (Qle == null) {
            return null;
        }
        return Qle.toLanguageTag();
    }

    public synchronized List<String> getLocales(ExtendedClient extendedClient) {
        List<java.util.Locale> zyO;
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": getLocales", TAG);
        zyO = this.localeAuthority.zyO();
        return zyO == null ? null : getLanguageTagsFromLocales(zyO);
    }

    public synchronized AlexaReadyState getReadyState(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": getReadyState", TAG);
        return this.readinessAuthority.zZm();
    }

    public synchronized List<String> getSupportedLocales(ExtendedClient extendedClient) {
        ArrayList arrayList;
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": getSupportedLocales");
        arrayList = new ArrayList();
        for (java.util.Locale locale : this.localeAuthority.LPk()) {
            arrayList.add(locale.toLanguageTag());
        }
        return arrayList;
    }

    public synchronized boolean isDetectingWakeWord(ExtendedClient extendedClient) {
        String str = extendedClient.getId() + ": isDetectingWakeWord";
        return this.wakeWordAuthority.BIo();
    }

    public synchronized boolean isEndpointSoundEnabled(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": isEndpointSoundEnabled", TAG);
        return this.attentionSystemAuthority.zZm();
    }

    public synchronized boolean isUserLoggedIn(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": isUserLoggedIn", TAG);
        return this.authAuthority.zyO();
    }

    public synchronized boolean isWakeSoundEnabled(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": isWakeSoundEnabled", TAG);
        return this.attentionSystemAuthority.BIo();
    }

    public synchronized void muteMicrophone(ExtendedClient extendedClient, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": muteMicrophone", TAG);
        this.alexaClientEventBus.jiA(new Xvi(z));
    }

    public synchronized void next(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": next", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaAudioPlaybackAuthority.zZm();
    }

    @Subscribe
    public synchronized void on(NTk nTk) {
        startListening(true, DialogExtras.zZm);
    }

    public synchronized void onClientConnect(ExtendedClient extendedClient, ClientConnectionControllerMessageSender clientConnectionControllerMessageSender, boolean z, boolean z2, boolean z3) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": onClientConnect. requires foreground? " + z + ", force capability refresh? " + z3);
        this.clientConnectionsAuthority.zZm(extendedClient);
        this.clientConnectionsAuthority.BIo(extendedClient, z);
        this.clientConnectionsAuthority.zZm(extendedClient, z2);
        this.connectionControllers.zZm(extendedClient, clientConnectionControllerMessageSender);
        if (z3) {
            this.alexaClientEventBus.zyO(new C0172Lqm());
        }
        checkAccountStatus(extendedClient);
    }

    public synchronized void onClientDisconnect(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": onClientDisconnect", TAG);
        this.clientConnectionsAuthority.BIo(extendedClient);
        this.alexaClientEventBus.zyO(new uyC(extendedClient, true));
        this.messageReceiverAuthority.BIo(extendedClient);
    }

    public synchronized void onLogOut() {
        String str = TAG;
        Log.i(str, "onLogOut " + this);
        this.alexaNotificationManager.LPk();
        this.localeAuthority.yPL();
        this.settingsAuthority.BIo();
        this.cardRendererCapabilityAgent.zZm();
        this.externalMediaPlayerCapabilityAgent.zZm();
        this.capabilityAuthority.zZm();
        this.attachmentStore.BIo();
        this.interactionScheduler.zZm();
        this.visualInteractionScheduler.zZm();
        this.speechSynthesizerCapabilityAgent.zZm();
        this.audioPlayerCapabilityAgent.zQM();
        hrT hrt = this.attentionSystemAuthority;
        hrt.zyO = null;
        hrt.jiA = null;
        hrt.zZm.zyO();
        hrt.zZm.zQM();
        this.wakeWordAuthority.zQM();
        this.textAuthority.zZm();
        disconnectClients();
    }

    public synchronized void pause(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": pause", TAG);
        pause();
    }

    public synchronized void play(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": play", TAG);
        play();
    }

    public synchronized void previous(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": previous", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaAudioPlaybackAuthority.zyO();
    }

    public synchronized void registerAlertsListener(ExtendedClient extendedClient, AlertsListener alertsListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlertsListener", TAG);
        this.alertsAuthority.zZm(alertsListener);
    }

    public synchronized void registerAlexaArtifactDownloadListener(ExtendedClient extendedClient, AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaArtifactDownloadListener", TAG);
        this.wakeWordArtifactDownload.zZm(extendedClient, alexaArtifactDownloadListener);
    }

    public synchronized void registerAlexaAudioPlaybackListener(ExtendedClient extendedClient, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaAudioPlaybackListener", TAG);
        this.alexaAudioPlaybackAuthority.zZm(extendedClient, alexaAudioPlaybackListenerProxy);
    }

    public synchronized void registerAlexaAudioPlaybackStatusListener(ExtendedClient extendedClient, AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaAudioPlaybackStatusListener", TAG);
        this.alexaAudioPlaybackAuthority.zZm(extendedClient, alexaAudioPlaybackStatusListener);
    }

    public synchronized void registerAlexaCaptionListener(ExtendedClient extendedClient, AlexaCaptionListener alexaCaptionListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaCaptionListener", TAG);
        this.speechSynthesizerCapabilityAgent.zZm(extendedClient, alexaCaptionListener);
    }

    public synchronized void registerAlexaCardRendererListener(ExtendedClient extendedClient, GvA gvA) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaCardRendererListener", TAG);
        this.cardRendererCapabilityAgent.zZm(extendedClient, gvA);
    }

    public synchronized void registerAlexaMediaPlaybackListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaMediaPlaybackListener", TAG);
        this.alexaClientEventBus.zyO(new UaM(eOP.zZm(apiCallback.zZm, ApiName.uzr), extendedClient, apiCallback, alexaMediaPlaybackListener));
    }

    public synchronized void registerAlexaPlayerInfoCardListener(ExtendedClient extendedClient, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaPlayerInfoCardListener", TAG);
        this.cardRendererCapabilityAgent.zZm(extendedClient, alexaPlayerInfoCardListenerProxy);
    }

    public synchronized void registerAlexaSettingsListener(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaSettingsListener", TAG);
        this.settingsController.zZm(extendedClient, alexaSettingsListenerProxy);
        this.localeAuthority.zZm(extendedClient, alexaSettingsListenerProxy);
    }

    public synchronized void registerAlexaStateListener(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaStateListener", TAG);
        this.alexaStateAuthority.zZm(extendedClient, alexaStateListenerProxy);
    }

    @Deprecated
    public synchronized void registerAlexaTemplateCardListener(ExtendedClient extendedClient, AlexaTemplateCardListenerProxy alexaTemplateCardListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaTemplateCardListener", TAG);
        Log.w(TAG, "Template cards are no longer supported. Ignoring registration");
    }

    public synchronized void registerAlexaUserSpeechListener(ExtendedClient extendedClient, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaUserSpeechListener", TAG);
        this.alexaUserSpeechAuthority.zZm(extendedClient, alexaUserSpeechListenerProxy);
    }

    public synchronized void registerAlexaWakeWordListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaWakeWordListener alexaWakeWordListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAlexaWakeWordListener", TAG);
        this.alexaClientEventBus.zyO(new Nyb(eOP.zZm(apiCallback.zZm, ApiName.vkx), extendedClient, apiCallback, alexaWakeWordListener));
    }

    public synchronized void registerAttentionSystemListener(ExtendedClient extendedClient, AlexaAttentionSystemListener alexaAttentionSystemListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAttentionSystemListener", TAG);
        this.alexaStateAuthority.zZm(extendedClient, alexaAttentionSystemListener);
    }

    public synchronized void registerAttentionSystemSettingsListener(ExtendedClient extendedClient, AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerAttentionSystemSettingsListener", TAG);
        this.attentionSystemAuthority.zZm(extendedClient, alexaAttentionSystemSettingsListener);
    }

    public synchronized void registerContextProvider(ExtendedClient extendedClient, AlexaContextProviderProxy alexaContextProviderProxy) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": registerContextProvider");
        ExternalComponentStateProvider externalComponentStateProvider = new ExternalComponentStateProvider(new LegacyContextProvider(alexaContextProviderProxy), extendedClient);
        this.externalComponentStateAuthority.zZm(extendedClient, externalComponentStateProvider.getComponentStateNamespaces(), false);
        this.externalComponentStateAuthority.BIo(extendedClient, externalComponentStateProvider);
    }

    public synchronized void registerContextsProvider(ExtendedClient extendedClient, AlexaContextsProvider alexaContextsProvider) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": registerContextsProvider");
        this.externalComponentStateAuthority.BIo(extendedClient, new ExternalComponentStateProvider(alexaContextsProvider, extendedClient));
    }

    public synchronized void registerDriveModeListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaDriveModeListener alexaDriveModeListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerDriveModeListener", TAG);
        this.alexaClientEventBus.zyO(new EOT(eOP.zZm(apiCallback.zZm, ApiName.zyO), extendedClient, apiCallback, alexaDriveModeListener));
    }

    public synchronized void registerExpectTextListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaExpectTextListener alexaExpectTextListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerExpectTextListener", TAG);
        this.alexaClientEventBus.zyO(new pwz(eOP.zZm(apiCallback.zZm, ApiName.NXS), extendedClient, apiCallback, alexaExpectTextListener));
    }

    public synchronized void registerForceDisconnectListener(ExtendedClient extendedClient, ForceDisconnectMessageSender forceDisconnectMessageSender) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerForceDisconnectListener", TAG);
        this.clientConnectionsAuthority.zZm(extendedClient);
        this.disconnectListeners.zZm(extendedClient, forceDisconnectMessageSender);
    }

    public synchronized void registerLocalesListener(ExtendedClient extendedClient, AlexaLocalesListener alexaLocalesListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerLocalesListener", TAG);
        this.settingsController.zZm(extendedClient, alexaLocalesListener);
        this.localeAuthority.zZm(extendedClient, alexaLocalesListener);
    }

    public synchronized void registerMetricsListener(ExtendedClient extendedClient, AlexaMetricsListener alexaMetricsListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerMetricsListener", TAG);
        this.metricsAuthority.zZm(extendedClient, alexaMetricsListener);
    }

    public synchronized void registerReadinessListener(ExtendedClient extendedClient, AlexaReadinessListener alexaReadinessListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerForReadiness", TAG);
        this.readinessAuthority.zZm(extendedClient, alexaReadinessListener);
    }

    public synchronized void registerSupportedLocalesListener(ExtendedClient extendedClient, AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerSupportedLocalesListener", TAG);
        this.settingsController.zZm(extendedClient, alexaSupportedLocalesListener);
        this.localeAuthority.zZm(extendedClient, alexaSupportedLocalesListener);
    }

    public synchronized void registerTextResponseListener(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaTextResponseListener alexaTextResponseListener) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerTextResponseListener", TAG);
        this.alexaClientEventBus.zyO(new Svl(eOP.zZm(apiCallback.zZm, ApiName.yPL), extendedClient, apiCallback, alexaTextResponseListener));
    }

    public synchronized void registerUserPerceivedLatencyListener(ExtendedClient extendedClient, bNQ bnq) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerUserPerceivedLatencyListener", TAG);
        this.metricsAuthority.zZm(extendedClient, bnq);
    }

    public synchronized void registerUserSpeechProvider(ExtendedClient extendedClient, AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaUserSpeechProviderMetadata alexaUserSpeechProviderMetadata) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": registerUserSpeechProvider", TAG);
        this.userSpeechProviderRegistry.zZm(extendedClient, alexaUserSpeechProvider, alexaUserSpeechProviderMetadata);
    }

    public synchronized void requestDialog(ExtendedClient extendedClient, AlexaUserSpeechProvider alexaUserSpeechProvider, AlexaDialogRequest alexaDialogRequest) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": requestDialog", TAG);
        UWt zZm = this.userSpeechProviderRegistry.zZm(alexaUserSpeechProvider);
        if (zZm == null) {
            String str = "Failed to request dialog for " + alexaUserSpeechProvider + ". It must be registered first.";
            Log.e(TAG, str);
            this.alexaClientEventBus.zyO(kbp.zyO.zZm(alexaDialogRequest.getInvocationType(), mMl.SPEECH_PROVIDER_NOT_REGISTERED));
            alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.SPEECH_PROVIDER_NOT_REGISTERED, str));
            return;
        }
        this.alexaClientEventBus.zyO(QYV.zZm.zZm(esV.zZm(alexaDialogRequest.getLaunchType()), zZm, alexaDialogRequest, ""));
    }

    public synchronized void schedule(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaVisualTask alexaVisualTask) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": schedule " + alexaVisualTask + " for: " + alexaVisualTask.getTaskComponentName());
        this.alexaClientEventBus.zyO(new FsB(eOP.zZm(apiCallback.zZm, ApiName.lOf), extendedClient, apiCallback, alexaVisualTask));
    }

    public synchronized void scheduleInteraction(ExtendedClient extendedClient, AlexaAudioInteractionProxy alexaAudioInteractionProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": scheduleInteraction", TAG);
        try {
            new MSk(IYE.zZm(alexaAudioInteractionProxy.getIdentifier()), alexaAudioInteractionProxy, this.alexaClientEventBus, extendedClient).yPL();
        } catch (RemoteException e) {
            Log.w(TAG, "Remote exception while trying to schedule interaction", e);
            this.alexaClientEventBus.zyO(xZV.zZm(extendedClient));
        }
    }

    public synchronized void sendAlexaEvent(ExtendedClient extendedClient, AlexaEvent alexaEvent, List<AlexaContext> list) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": sendAlexaEvent", TAG);
        this.alexaClientEventBus.zyO(new tNI(ApiName.zZm(AlexaServicesMessageType.SEND_ALEXA_EVENT)));
        if (!AvsApiConstants.zZm.contains(Namespace.create(alexaEvent.getAlexaHeader().getNamespace()))) {
            JjI.zZm zZm = JjI.BIo().zZm(this.messageTransformer.convertAlexaEventToMessage(alexaEvent)).zZm(UcG.INSTANCE);
            HashSet hashSet = new HashSet();
            for (AlexaContext alexaContext : list) {
                hashSet.add(ComponentState.create(alexaContext));
            }
            ((WXj.zZm) zZm).jiA = hashSet;
            this.alexaClientEventBus.zyO(zZm.zZm());
        }
    }

    public synchronized void sendTextMessage(ExtendedClient extendedClient, ApiCallback apiCallback, String str, AlexaDialogExtras alexaDialogExtras) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": sendTextMessage", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.alexaClientEventBus.zyO(new Jpe(eOP.zZm(apiCallback.zZm, ApiName.zzR), extendedClient, apiCallback, str, alexaDialogExtras));
    }

    public synchronized void setBaseURLs(ExtendedClient extendedClient, AlexaBaseURLs alexaBaseURLs) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setBaseURLs", TAG);
        this.endpointAuthority.zZm(alexaBaseURLs);
    }

    public synchronized void setContextCachingEnabled(ExtendedClient extendedClient, Set<String> set, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setContextCachingEnabled", TAG);
        this.externalComponentStateAuthority.zZm(extendedClient, Namespace.createMultiple(set), z);
    }

    public synchronized void setDriveModeEnabled(ExtendedClient extendedClient, ApiCallback apiCallback, boolean z) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": setDriveModeEnabled(" + z + ")");
        this.alexaClientEventBus.zyO(new zZD(eOP.zZm(apiCallback.zZm, ApiName.Qle), extendedClient, apiCallback, z));
    }

    public synchronized void setDriveModeState(ExtendedClient extendedClient, ApiCallback apiCallback, DriveModeState driveModeState) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": setDriveModeState(" + driveModeState.name() + ")");
        this.alexaClientEventBus.zyO(new BPW(eOP.zZm(apiCallback.zZm, ApiName.JTe), extendedClient, apiCallback, driveModeState));
    }

    public synchronized void setDriveModeTheme(ExtendedClient extendedClient, ApiCallback apiCallback, boolean z) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": setDriveModeTheme(" + z + ")");
        this.alexaClientEventBus.zyO(new TEg(eOP.zZm(apiCallback.zZm, ApiName.LPk), extendedClient, apiCallback, z));
    }

    public synchronized void setEndpointSoundEnabled(ExtendedClient extendedClient, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setEndpointSoundEnabled", TAG);
        this.attentionSystemAuthority.zZm(z);
    }

    @Deprecated
    public synchronized void setLocales(ExtendedClient extendedClient, List<String> list, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setLocales", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.settingsController.zZm(getLocalesFromLanguageTags(list), z);
    }

    public synchronized void setMediaNotificationContentIntent(ExtendedClient extendedClient, ApiCallback apiCallback, PendingIntent pendingIntent) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setMediaNotificationPendingIntent", TAG);
        this.alexaClientEventBus.zyO(new DxN(eOP.zZm(apiCallback.zZm, ApiName.Qgh), extendedClient, apiCallback, pendingIntent));
    }

    public synchronized void setWakeSoundEnabled(ExtendedClient extendedClient, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setWakeSoundEnabled", TAG);
        this.attentionSystemAuthority.BIo(z);
    }

    public synchronized void setWakeWords(ExtendedClient extendedClient, ApiCallback apiCallback, List<String> list) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setWakeWords", TAG);
        this.alexaClientEventBus.zyO(new EJn(eOP.zZm(apiCallback.zZm, ApiName.noQ), extendedClient, apiCallback, list));
    }

    public synchronized void startRecognizing(ExtendedClient extendedClient, AlexaDialogExtras alexaDialogExtras) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": startRecognizing", TAG);
        if (!verifyLoginStatus()) {
            this.alexaClientEventBus.zyO(kbp.zyO.zZm(alexaDialogExtras.getInvocationType(), bij.AUTHORIZATION_ERROR));
        } else {
            startListening(false, alexaDialogExtras);
        }
    }

    public synchronized void stop(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": stop", TAG);
        pause();
        this.alexaClientEventBus.zyO(AgS.zZm(true));
    }

    public synchronized void stopForegroundAudioTask(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": stopForegroundAudioTask", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaStateAuthority.JTe();
    }

    public synchronized void stopRecognizing(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": stopRecognizing", TAG);
        if (this.multiTurnDialogAuthority.zyO()) {
            this.multiTurnDialogAuthority.BIo().zZm(fuM.zyO.API);
        }
    }

    public synchronized void teardown() {
        String str = TAG;
        Log.i(str, "Tearing down Alexa client " + this + ". Previously torn down? " + this.isTornDown);
        this.metricsAuthority.zZm(this.startTime);
        if (!this.isTornDown) {
            this.attentionSystemLatencyProcessor.BIo();
            this.alexaNotificationManager.yPL();
            this.alertsAuthority.zZm();
            this.geolocationComponent.Mlj();
            this.interactionScheduler.BIo();
            this.visualInteractionScheduler.BIo();
            this.attachmentStore.zQM();
            this.networkAuthority.Qle();
            this.authAuthority.Qle();
            this.alexaStateAuthority.LPk();
            this.externalCapabilityAgentRegistry.jiA();
            this.speechSynthesizerCapabilityAgent.zQM();
            this.audioPlayerCapabilityAgent.jiA();
            this.externalMediaPlayerCapabilityAgent.BIo();
            jcJ jcj = this.soundAuthority;
            jcj.zyO.shutdownNow();
            jcj.BIo.BIo();
            jcj.zQM.BIo();
            this.speechRecognizerCapabilityAgent.zQM();
            this.scoController.tearDown();
            this.externalComponentStateAuthority.BIo();
            this.promptPlayer.BIo();
            this.wakeWordAuthority.JTe();
            this.userSpeechProviderAuthority.BIo();
            this.mediaBrowserPlayerFinder.BIo();
            this.offlinePromptDownloadInitiator.zZm();
            this.externalCapabilityAgentFinder.zQM();
            this.readinessAuthority.zyO();
            this.apiCallAuthority.BIo();
            this.voiceInteractionAuthority.zQM();
            this.textInteractionAuthority.zQM();
            this.metricsAuthority.zZm();
            this.eventBroadcastSender.zZm();
            this.featureFlagConfigurationAuthority.zZm();
            ((fGu) this.instrumentationReceiver).BIo();
            this.volumeChangeHandler.BIo();
            this.avsConnectionStateAuthority.zQM();
            this.connectivityAuthority.LPk();
            this.deviceLocaleChangedBroadcastReceiver.BIo();
            this.deviceTimeZoneChangedBroadCastReceiver.BIo();
            this.packagesChangedBroadcastReceiver.BIo();
            this.authChangeBroadcastReceiver.BIo();
            this.locationProviderChangedBroadcastReceiver.BIo();
            JOD jod = this.uiEventBroadcastReceiver;
            LocalBroadcastManager.getInstance(jod.BIo).unregisterReceiver(jod);
            this.alertsBroadcastReceiver.BIo();
            ktr ktrVar = this.batteryLevelReceiver;
            if (ktrVar.jiA) {
                String str2 = ktr.zZm;
                ktrVar.BIo.unregisterReceiver(ktrVar);
                ktrVar.jiA = false;
            } else {
                String str3 = ktr.zZm;
            }
            DtD dtD = this.powerConnectionReceiver;
            if (dtD.jiA) {
                String str4 = DtD.zZm;
                dtD.BIo.unregisterReceiver(dtD);
                dtD.jiA = false;
            } else {
                String str5 = DtD.zZm;
            }
            this.textAuthority.BIo();
            adp.zZm zzm = this.internalTrustedStatesProvider.zzR;
            if (zzm.zZm) {
                com.amazon.alexa.handsfree.protocols.utils.Log.d(adp.zZm, "Unregistering lock screen broadcast receiver");
                adp.this.BIo.unregisterReceiver(zzm);
                zzm.zZm = false;
            } else {
                com.amazon.alexa.handsfree.protocols.utils.Log.d(adp.zZm, "Cannot unregister because lock screen broadcast receiver hasn't been registered");
            }
            this.alexaClientEventBus.BIo(this);
            this.isTornDown = true;
        }
    }

    public synchronized void temporarilySuppressAllAudio(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": temporarilySuppressAllAudio", TAG);
        this.alexaSuppressionAuthority.zZm();
    }

    public synchronized void unschedule(ExtendedClient extendedClient, ApiCallback apiCallback, AlexaVisualTask alexaVisualTask) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": unschedule " + alexaVisualTask + " for: " + alexaVisualTask.getTaskComponentName());
        this.alexaClientEventBus.zyO(new Ixs(eOP.zZm(apiCallback.zZm, ApiName.dMe), extendedClient, apiCallback, alexaVisualTask));
    }

    public synchronized void unscheduleInteraction(ExtendedClient extendedClient, AlexaAudioInteractionProxy alexaAudioInteractionProxy) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": unscheduleInteraction", TAG);
        try {
            this.alexaClientEventBus.zyO(LBB.zZm(IYE.zZm(alexaAudioInteractionProxy.getIdentifier())));
        } catch (RemoteException e) {
            Log.w(TAG, "Remote exception while trying to unschedule interaction", e);
            this.alexaClientEventBus.zyO(xZV.zZm(extendedClient));
        }
    }

    @Deprecated
    public synchronized void updateWakeWordDetectionState(ExtendedClient extendedClient, boolean z) {
        String str = TAG;
        Log.i(str, extendedClient.getId() + ": updateWakeWordDetectionState: " + z);
        this.alexaClientEventBus.zyO(new tNI(ApiName.zZm(AlexaServicesMessageType.SET_WAKE_WORD_ALLOWED)));
        this.wakeWordAuthority.zZm(extendedClient, z);
    }

    @Subscribe
    public synchronized void on(Jkd jkd) {
        stopListening();
    }

    private void pause() {
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaAudioPlaybackAuthority.BIo();
    }

    private void play() {
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        this.alexaAudioPlaybackAuthority.zQM();
    }

    @Subscribe
    public synchronized void on(OeW oeW) {
        play();
    }

    @Subscribe
    public synchronized void on(BnD bnD) {
        pause();
    }

    public synchronized void stopListening(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": stopListening", TAG);
        this.wakeWordAuthority.zZm(extendedClient);
    }

    public synchronized void clearContextCache(ExtendedClient extendedClient) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": clearContextCache", TAG);
        this.externalComponentStateAuthority.zZm(extendedClient);
    }

    @Subscribe
    public synchronized void on(Seo seo) {
        internalPrevious();
    }

    public synchronized void setContextCachingEnabled(ExtendedClient extendedClient, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setContextCachingEnabled", TAG);
        this.externalComponentStateAuthority.zZm(extendedClient, z);
    }

    public synchronized void setLocales(ExtendedClient extendedClient, List<String> list, ApiCallback apiCallback, boolean z) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": setLocales", TAG);
        if (!verifyLoginStatus()) {
            return;
        }
        this.userInactivityAuthority.zQM();
        AlexaClientEventBus alexaClientEventBus = this.alexaClientEventBus;
        MNR mnr = apiCallback.zZm;
        alexaClientEventBus.zyO(new nOx(eOP.zZm(mnr, ApiName.BIo), extendedClient, apiCallback, getLocalesFromLanguageTags(list), z));
    }

    @Subscribe
    public synchronized void on(BeW beW) {
        internalNext();
    }

    @Subscribe
    public synchronized void on(xZV xzv) {
        ExtendedClient extendedClient = ((uyC) xzv).BIo;
        this.clientConnectionsAuthority.BIo(extendedClient);
        Set<ForceDisconnectMessageSender> BIo = this.disconnectListeners.BIo(extendedClient);
        if (BIo != null && !((uyC) xzv).zQM) {
            for (ForceDisconnectMessageSender forceDisconnectMessageSender : BIo) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Force disconnecting client: ");
                    sb.append(extendedClient.getId());
                    sb.toString();
                    forceDisconnectMessageSender.onForceDisconnect();
                } catch (RemoteException unused) {
                    Log.i(TAG, "Unable to tell client to disconnect. Likely already disconnected");
                }
            }
        }
        Set<ClientConnectionControllerMessageSender> BIo2 = this.connectionControllers.BIo(extendedClient);
        if (BIo != null && !((uyC) xzv).zQM) {
            for (ClientConnectionControllerMessageSender clientConnectionControllerMessageSender : BIo2) {
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Force disconnecting client: ");
                    sb2.append(extendedClient.getId());
                    sb2.toString();
                    clientConnectionControllerMessageSender.onForceDisconnect();
                } catch (RemoteException unused2) {
                    Log.i(TAG, "Unable to tell client to disconnect. Likely already disconnected");
                }
            }
        }
    }

    public synchronized void startListening(ExtendedClient extendedClient, @Nullable AlexaDialogExtras alexaDialogExtras) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": startListening", TAG);
        this.wakeWordAuthority.zZm(extendedClient, alexaDialogExtras);
    }

    public synchronized void sendAlexaEvent(ExtendedClient extendedClient, AlexaEvent alexaEvent, boolean z) {
        sendAlexaEvent(extendedClient, alexaEvent, z, new ApiCallback(null));
    }

    public synchronized void sendAlexaEvent(ExtendedClient extendedClient, AlexaEvent alexaEvent, boolean z, ApiCallback apiCallback) {
        C0179Pya.zZm(extendedClient, new StringBuilder(), ": sendAlexaEvent", TAG);
        eOP zZm = eOP.zZm(apiCallback.zZm, ApiName.zZm(AlexaServicesMessageType.SEND_ALEXA_EVENT));
        UcG createSendMessageCallbackFrom = createSendMessageCallbackFrom(zZm);
        this.alexaClientEventBus.zyO(new NoB(eOP.zZm(apiCallback.zZm, ApiName.zQM), extendedClient, apiCallback));
        Namespace create = Namespace.create(alexaEvent.getAlexaHeader().getNamespace());
        if (AvsApiConstants.zZm.contains(create)) {
            String str = TAG;
            Log.w(str, "Ignoring event as it comes from a disallowed namespace: " + create);
            this.alexaClientEventBus.zyO(NEv.zZm.zZm(zZm, ApiCallFailure.InternalFailure.create("Unable to send event due to disallowed namespace")));
            return;
        }
        JjI.zZm zZm2 = JjI.BIo().zZm(this.messageTransformer.convertAlexaEventToMessage(alexaEvent)).zZm(createSendMessageCallbackFrom);
        if (z) {
            ((WXj.zZm) zZm2).jiA = this.componentStateAuthority.zZm();
        }
        this.alexaClientEventBus.zyO(zZm2.zZm());
    }
}
