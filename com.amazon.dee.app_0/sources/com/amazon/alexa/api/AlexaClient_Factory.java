package com.amazon.alexa.api;

import android.content.Context;
import com.amazon.alexa.AHr;
import com.amazon.alexa.AYd;
import com.amazon.alexa.Arv;
import com.amazon.alexa.AxK;
import com.amazon.alexa.Boy;
import com.amazon.alexa.BrT;
import com.amazon.alexa.C0234ubm;
import com.amazon.alexa.C0237wXy;
import com.amazon.alexa.Ccz;
import com.amazon.alexa.DJb;
import com.amazon.alexa.DJw;
import com.amazon.alexa.DtD;
import com.amazon.alexa.FQX;
import com.amazon.alexa.Fsz;
import com.amazon.alexa.HTC;
import com.amazon.alexa.HVk;
import com.amazon.alexa.IOV;
import com.amazon.alexa.IYJ;
import com.amazon.alexa.IcB;
import com.amazon.alexa.JOD;
import com.amazon.alexa.JXl;
import com.amazon.alexa.JgM;
import com.amazon.alexa.Jvr;
import com.amazon.alexa.KrR;
import com.amazon.alexa.KvZ;
import com.amazon.alexa.LPk;
import com.amazon.alexa.Lnt;
import com.amazon.alexa.LrI;
import com.amazon.alexa.LuX;
import com.amazon.alexa.MBE;
import com.amazon.alexa.Msx;
import com.amazon.alexa.NDW;
import com.amazon.alexa.NXS;
import com.amazon.alexa.OGT;
import com.amazon.alexa.OPl;
import com.amazon.alexa.QIY;
import com.amazon.alexa.QMR;
import com.amazon.alexa.QeM;
import com.amazon.alexa.Qgh;
import com.amazon.alexa.Qoi;
import com.amazon.alexa.QtV;
import com.amazon.alexa.RfA;
import com.amazon.alexa.SCB;
import com.amazon.alexa.TrI;
import com.amazon.alexa.VEQ;
import com.amazon.alexa.Wyh;
import com.amazon.alexa.XPi;
import com.amazon.alexa.XUD;
import com.amazon.alexa.YKQ;
import com.amazon.alexa.ZIT;
import com.amazon.alexa.adp;
import com.amazon.alexa.aeS;
import com.amazon.alexa.bXh;
import com.amazon.alexa.bjR;
import com.amazon.alexa.blL;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import com.amazon.alexa.brA;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.notifications.AlexaNotificationManager;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.messages.MessageTransformer;
import com.amazon.alexa.dAN;
import com.amazon.alexa.eDG;
import com.amazon.alexa.fjm;
import com.amazon.alexa.gMz;
import com.amazon.alexa.gSO;
import com.amazon.alexa.hlu;
import com.amazon.alexa.hrT;
import com.amazon.alexa.jbU;
import com.amazon.alexa.jcJ;
import com.amazon.alexa.jdJ;
import com.amazon.alexa.kDa;
import com.amazon.alexa.ktr;
import com.amazon.alexa.lEV;
import com.amazon.alexa.lhN;
import com.amazon.alexa.mCQ;
import com.amazon.alexa.oGE;
import com.amazon.alexa.pBR;
import com.amazon.alexa.pZY;
import com.amazon.alexa.pbo;
import com.amazon.alexa.qxC;
import com.amazon.alexa.rte;
import com.amazon.alexa.shl;
import com.amazon.alexa.system.UserInactivityAuthority;
import com.amazon.alexa.szT;
import com.amazon.alexa.tjk;
import com.amazon.alexa.tkT;
import com.amazon.alexa.tol;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.alexa.vYS;
import com.amazon.alexa.vkx;
import com.amazon.alexa.xUA;
import com.amazon.alexa.yED;
import com.amazon.alexa.yYy;
import com.amazon.alexa.yhM;
import com.amazon.alexa.yjS;
import com.amazon.alexa.zDj;
import com.amazon.alexa.zMV;
import com.amazon.alexa.zmg;
import com.google.gson.Gson;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlexaClient_Factory implements Factory<AlexaClient> {
    public final Provider<QIY> alertsAuthorityProvider;
    public final Provider<QtV> alertsBroadcastReceiverProvider;
    public final Provider<LPk> alexaAudioPlaybackAuthorityProvider;
    public final Provider<AlexaClientEventBus> alexaClientEventBusProvider;
    public final Provider<C0237wXy> alexaDisplayWindowComponentStateProvider;
    public final Provider<AlexaHandsFreeDeviceInformation> alexaHandsFreeDeviceInformationProvider;
    public final Provider<tkT> alexaLauncherCapabilityAgentProvider;
    public final Provider<NDW> alexaMediaPlaybackAuthorityProvider;
    public final Provider<AlexaNotificationManager> alexaNotificationManagerProvider;
    public final Provider<vkx> alexaStateAuthorityProvider;
    public final Provider<Qgh> alexaSuppressionAuthorityProvider;
    public final Provider<NXS> alexaUserSpeechAuthorityProvider;
    public final Provider<SCB> androidLocationComponentProvider;
    public final Provider<JXl> apiCallAuthorityProvider;
    public final Provider<TrI> applicationManagerCapabilityAgentProvider;
    public final Provider<shl> attachmentStoreProvider;
    public final Provider<hrT> attentionSystemAuthorityProvider;
    public final Provider<AxK> attentionSystemLatencyProcessorProvider;
    public final Provider<FQX> audioActivityTrackerProvider;
    public final Provider<OPl> audioFocusManagerProvider;
    public final Provider<HVk> audioPlayerCapabilityAgentProvider;
    public final Provider<oGE> audioPlayerComponentStateAuthorityProvider;
    public final Provider<tol> authAuthorityProvider;
    public final Provider<mCQ> authChangeBroadcastReceiverProvider;
    public final Provider<eDG> avsConnectionStateAuthorityProvider;
    public final Provider<ktr> batteryLevelReceiverProvider;
    public final Provider<tjk> becomingNoisyManagerProvider;
    public final Provider<bXh> capabilityAgentManagerProvider;
    public final Provider<pZY> capabilityAuthorityProvider;
    public final Provider<yYy> cardRendererCapabilityAgentProvider;
    public final Provider<IYJ> clientConnectionsAuthorityProvider;
    public final Provider<lhN> componentStateAuthorityProvider;
    public final Provider<qxC> connectivityAuthorityProvider;
    public final Provider<Context> contextProvider;
    public final Provider<IcB> deviceLocaleChangedBroadcastReceiverProvider;
    public final Provider<XPi> deviceTimeZoneChangedBroadCastReceiverProvider;
    public final Provider<xUA> endpointAuthorityProvider;
    public final Provider<pbo> eventBroadcastSenderProvider;
    public final Provider<JgM> externalCapabilityAgentFinderProvider;
    public final Provider<LrI> externalCapabilityAgentRegistryProvider;
    public final Provider<YKQ> externalComponentStateAuthorityProvider;
    public final Provider<DJw> externalMediaPlayerCapabilityAgentProvider;
    public final Provider<Boy> externalMediaPlayerStateProvider;
    public final Provider<hlu> failedInteractionTrackerProvider;
    public final Provider<gSO> featureFlagConfigurationAuthorityProvider;
    public final Provider<Set<CapabilityAgent>> fireOsCapabilityAgentsProvider;
    public final Provider<blL> geolocationGooglePlayComponentProvider;
    public final Provider<Gson> gsonProvider;
    public final Provider<jbU> instrumentationReceiverProvider;
    public final Provider<zMV> interactionModelCapabilityAgentProvider;
    public final Provider<brA> interactionSchedulerProvider;
    public final Provider<C0234ubm> internalCapabilityAgentRegistryProvider;
    public final Provider<lEV> internalComponentStateProvidersProvider;
    public final Provider<adp> internalTrustedStatesProvider;
    public final Provider<pBR> ioComponentsComponentStateProvider;
    public final Provider<MBE> localeAuthorityProvider;
    public final Provider<ZIT> locationProviderChangedBroadcastReceiverProvider;
    public final Provider<Msx> lockscreenManagerProvider;
    public final Provider<Lnt> mediaBrowserPlayerFinderProvider;
    public final Provider<zmg> mediaPlayersAuthorityProvider;
    public final Provider<Qoi> messageProcessingSequencerProvider;
    public final Provider<KvZ> messageReceiverAuthorityProvider;
    public final Provider<MessageTransformer> messageTransformerProvider;
    public final Provider<Fsz> metricsAuthorityProvider;
    public final Provider<Wyh> multiTurnDialogAuthorityProvider;
    public final Provider<yhM> navigationCapabilityAgentProvider;
    public final Provider<dAN> networkAuthorityProvider;
    public final Provider<szT> offlinePromptDownloadInitiatorProvider;
    public final Provider<QMR> packagesChangedBroadcastReceiverProvider;
    public final Provider<Arv> phoneCallControllerComponentStateAuthorityProvider;
    public final Provider<HTC> playbackStateReporterProvider;
    public final Provider<DtD> powerConnectionReceiverProvider;
    public final Provider<BrT> promptPlayerProvider;
    public final Provider<XUD> readinessAuthorityProvider;
    public final Provider<BluetoothScoController> scoControllerProvider;
    public final Provider<aeS> settingsAuthorityProvider;
    public final Provider<AHr> settingsControllerProvider;
    public final Provider<ScheduledExecutorService> sharedExecutorServiceProvider;
    public final Provider<jcJ> soundAuthorityProvider;
    public final Provider<VEQ> soundEffectPlayerProvider;
    public final Provider<DJb> speakerCapabilityAgentProvider;
    public final Provider<LuX> speechRecognizerCapabilityAgentProvider;
    public final Provider<fjm> speechSynthesizerCapabilityAgentProvider;
    public final Provider<QeM> speechSynthesizerComponentStateAuthorityProvider;
    public final Provider<yED> systemCapabilityAgentProvider;
    public final Provider<yjS> textAuthorityProvider;
    public final Provider<Jvr> textCapabilityAgentProvider;
    public final Provider<Ccz> textInteractionAuthorityProvider;
    public final Provider<zDj> timeZoneAuthorityProvider;
    public final Provider<AYd> trustedStatesComponentStateProvider;
    public final Provider<JOD> uiEventBroadcastReceiverProvider;
    public final Provider<vYS> uiManagerProvider;
    public final Provider<UserInactivityAuthority> userInactivityAuthorityProvider;
    public final Provider<OGT> userSpeechProviderAuthorityProvider;
    public final Provider<jdJ> userSpeechProviderRegistryProvider;
    public final Provider<IOV> visualActivityTrackerProvider;
    public final Provider<kDa> visualFocusManagerProvider;
    public final Provider<gMz> visualInteractionSchedulerProvider;
    public final Provider<rte> voiceInteractionAuthorityProvider;
    public final Provider<KrR> volumeChangeHandlerProvider;
    public final Provider<RfA> wakeWordArtifactDownloadProvider;
    public final Provider<bjR> wakeWordAuthorityProvider;

    public AlexaClient_Factory(Provider<ScheduledExecutorService> provider, Provider<IYJ> provider2, Provider<vkx> provider3, Provider<NXS> provider4, Provider<LPk> provider5, Provider<AlexaClientEventBus> provider6, Provider<AlexaNotificationManager> provider7, Provider<QIY> provider8, Provider<tol> provider9, Provider<bXh> provider10, Provider<Context> provider11, Provider<AlexaHandsFreeDeviceInformation> provider12, Provider<Qoi> provider13, Provider<brA> provider14, Provider<gMz> provider15, Provider<FQX> provider16, Provider<IOV> provider17, Provider<lhN> provider18, Provider<OGT> provider19, Provider<jdJ> provider20, Provider<fjm> provider21, Provider<QeM> provider22, Provider<SCB> provider23, Provider<blL> provider24, Provider<LuX> provider25, Provider<HVk> provider26, Provider<oGE> provider27, Provider<yED> provider28, Provider<yYy> provider29, Provider<AHr> provider30, Provider<dAN> provider31, Provider<tjk> provider32, Provider<UserInactivityAuthority> provider33, Provider<DJb> provider34, Provider<VEQ> provider35, Provider<yhM> provider36, Provider<NDW> provider37, Provider<KrR> provider38, Provider<Fsz> provider39, Provider<pbo> provider40, Provider<qxC> provider41, Provider<Qgh> provider42, Provider<BrT> provider43, Provider<zmg> provider44, Provider<Lnt> provider45, Provider<JgM> provider46, Provider<KvZ> provider47, Provider<C0234ubm> provider48, Provider<LrI> provider49, Provider<YKQ> provider50, Provider<lEV> provider51, Provider<pZY> provider52, Provider<MessageTransformer> provider53, Provider<shl> provider54, Provider<vYS> provider55, Provider<Msx> provider56, Provider<DJw> provider57, Provider<Boy> provider58, Provider<HTC> provider59, Provider<jbU> provider60, Provider<jcJ> provider61, Provider<OPl> provider62, Provider<kDa> provider63, Provider<zMV> provider64, Provider<Wyh> provider65, Provider<aeS> provider66, Provider<pBR> provider67, Provider<TrI> provider68, Provider<BluetoothScoController> provider69, Provider<IcB> provider70, Provider<XPi> provider71, Provider<QMR> provider72, Provider<mCQ> provider73, Provider<ZIT> provider74, Provider<JOD> provider75, Provider<QtV> provider76, Provider<ktr> provider77, Provider<DtD> provider78, Provider<bjR> provider79, Provider<Arv> provider80, Provider<Gson> provider81, Provider<rte> provider82, Provider<Ccz> provider83, Provider<AxK> provider84, Provider<XUD> provider85, Provider<tkT> provider86, Provider<hrT> provider87, Provider<Set<CapabilityAgent>> provider88, Provider<AYd> provider89, Provider<hlu> provider90, Provider<JXl> provider91, Provider<gSO> provider92, Provider<eDG> provider93, Provider<C0237wXy> provider94, Provider<Jvr> provider95, Provider<yjS> provider96, Provider<adp> provider97, Provider<szT> provider98, Provider<RfA> provider99, Provider<xUA> provider100, Provider<MBE> provider101, Provider<zDj> provider102) {
        this.sharedExecutorServiceProvider = provider;
        this.clientConnectionsAuthorityProvider = provider2;
        this.alexaStateAuthorityProvider = provider3;
        this.alexaUserSpeechAuthorityProvider = provider4;
        this.alexaAudioPlaybackAuthorityProvider = provider5;
        this.alexaClientEventBusProvider = provider6;
        this.alexaNotificationManagerProvider = provider7;
        this.alertsAuthorityProvider = provider8;
        this.authAuthorityProvider = provider9;
        this.capabilityAgentManagerProvider = provider10;
        this.contextProvider = provider11;
        this.alexaHandsFreeDeviceInformationProvider = provider12;
        this.messageProcessingSequencerProvider = provider13;
        this.interactionSchedulerProvider = provider14;
        this.visualInteractionSchedulerProvider = provider15;
        this.audioActivityTrackerProvider = provider16;
        this.visualActivityTrackerProvider = provider17;
        this.componentStateAuthorityProvider = provider18;
        this.userSpeechProviderAuthorityProvider = provider19;
        this.userSpeechProviderRegistryProvider = provider20;
        this.speechSynthesizerCapabilityAgentProvider = provider21;
        this.speechSynthesizerComponentStateAuthorityProvider = provider22;
        this.androidLocationComponentProvider = provider23;
        this.geolocationGooglePlayComponentProvider = provider24;
        this.speechRecognizerCapabilityAgentProvider = provider25;
        this.audioPlayerCapabilityAgentProvider = provider26;
        this.audioPlayerComponentStateAuthorityProvider = provider27;
        this.systemCapabilityAgentProvider = provider28;
        this.cardRendererCapabilityAgentProvider = provider29;
        this.settingsControllerProvider = provider30;
        this.networkAuthorityProvider = provider31;
        this.becomingNoisyManagerProvider = provider32;
        this.userInactivityAuthorityProvider = provider33;
        this.speakerCapabilityAgentProvider = provider34;
        this.soundEffectPlayerProvider = provider35;
        this.navigationCapabilityAgentProvider = provider36;
        this.alexaMediaPlaybackAuthorityProvider = provider37;
        this.volumeChangeHandlerProvider = provider38;
        this.metricsAuthorityProvider = provider39;
        this.eventBroadcastSenderProvider = provider40;
        this.connectivityAuthorityProvider = provider41;
        this.alexaSuppressionAuthorityProvider = provider42;
        this.promptPlayerProvider = provider43;
        this.mediaPlayersAuthorityProvider = provider44;
        this.mediaBrowserPlayerFinderProvider = provider45;
        this.externalCapabilityAgentFinderProvider = provider46;
        this.messageReceiverAuthorityProvider = provider47;
        this.internalCapabilityAgentRegistryProvider = provider48;
        this.externalCapabilityAgentRegistryProvider = provider49;
        this.externalComponentStateAuthorityProvider = provider50;
        this.internalComponentStateProvidersProvider = provider51;
        this.capabilityAuthorityProvider = provider52;
        this.messageTransformerProvider = provider53;
        this.attachmentStoreProvider = provider54;
        this.uiManagerProvider = provider55;
        this.lockscreenManagerProvider = provider56;
        this.externalMediaPlayerCapabilityAgentProvider = provider57;
        this.externalMediaPlayerStateProvider = provider58;
        this.playbackStateReporterProvider = provider59;
        this.instrumentationReceiverProvider = provider60;
        this.soundAuthorityProvider = provider61;
        this.audioFocusManagerProvider = provider62;
        this.visualFocusManagerProvider = provider63;
        this.interactionModelCapabilityAgentProvider = provider64;
        this.multiTurnDialogAuthorityProvider = provider65;
        this.settingsAuthorityProvider = provider66;
        this.ioComponentsComponentStateProvider = provider67;
        this.applicationManagerCapabilityAgentProvider = provider68;
        this.scoControllerProvider = provider69;
        this.deviceLocaleChangedBroadcastReceiverProvider = provider70;
        this.deviceTimeZoneChangedBroadCastReceiverProvider = provider71;
        this.packagesChangedBroadcastReceiverProvider = provider72;
        this.authChangeBroadcastReceiverProvider = provider73;
        this.locationProviderChangedBroadcastReceiverProvider = provider74;
        this.uiEventBroadcastReceiverProvider = provider75;
        this.alertsBroadcastReceiverProvider = provider76;
        this.batteryLevelReceiverProvider = provider77;
        this.powerConnectionReceiverProvider = provider78;
        this.wakeWordAuthorityProvider = provider79;
        this.phoneCallControllerComponentStateAuthorityProvider = provider80;
        this.gsonProvider = provider81;
        this.voiceInteractionAuthorityProvider = provider82;
        this.textInteractionAuthorityProvider = provider83;
        this.attentionSystemLatencyProcessorProvider = provider84;
        this.readinessAuthorityProvider = provider85;
        this.alexaLauncherCapabilityAgentProvider = provider86;
        this.attentionSystemAuthorityProvider = provider87;
        this.fireOsCapabilityAgentsProvider = provider88;
        this.trustedStatesComponentStateProvider = provider89;
        this.failedInteractionTrackerProvider = provider90;
        this.apiCallAuthorityProvider = provider91;
        this.featureFlagConfigurationAuthorityProvider = provider92;
        this.avsConnectionStateAuthorityProvider = provider93;
        this.alexaDisplayWindowComponentStateProvider = provider94;
        this.textCapabilityAgentProvider = provider95;
        this.textAuthorityProvider = provider96;
        this.internalTrustedStatesProvider = provider97;
        this.offlinePromptDownloadInitiatorProvider = provider98;
        this.wakeWordArtifactDownloadProvider = provider99;
        this.endpointAuthorityProvider = provider100;
        this.localeAuthorityProvider = provider101;
        this.timeZoneAuthorityProvider = provider102;
    }

    public static AlexaClient_Factory create(Provider<ScheduledExecutorService> provider, Provider<IYJ> provider2, Provider<vkx> provider3, Provider<NXS> provider4, Provider<LPk> provider5, Provider<AlexaClientEventBus> provider6, Provider<AlexaNotificationManager> provider7, Provider<QIY> provider8, Provider<tol> provider9, Provider<bXh> provider10, Provider<Context> provider11, Provider<AlexaHandsFreeDeviceInformation> provider12, Provider<Qoi> provider13, Provider<brA> provider14, Provider<gMz> provider15, Provider<FQX> provider16, Provider<IOV> provider17, Provider<lhN> provider18, Provider<OGT> provider19, Provider<jdJ> provider20, Provider<fjm> provider21, Provider<QeM> provider22, Provider<SCB> provider23, Provider<blL> provider24, Provider<LuX> provider25, Provider<HVk> provider26, Provider<oGE> provider27, Provider<yED> provider28, Provider<yYy> provider29, Provider<AHr> provider30, Provider<dAN> provider31, Provider<tjk> provider32, Provider<UserInactivityAuthority> provider33, Provider<DJb> provider34, Provider<VEQ> provider35, Provider<yhM> provider36, Provider<NDW> provider37, Provider<KrR> provider38, Provider<Fsz> provider39, Provider<pbo> provider40, Provider<qxC> provider41, Provider<Qgh> provider42, Provider<BrT> provider43, Provider<zmg> provider44, Provider<Lnt> provider45, Provider<JgM> provider46, Provider<KvZ> provider47, Provider<C0234ubm> provider48, Provider<LrI> provider49, Provider<YKQ> provider50, Provider<lEV> provider51, Provider<pZY> provider52, Provider<MessageTransformer> provider53, Provider<shl> provider54, Provider<vYS> provider55, Provider<Msx> provider56, Provider<DJw> provider57, Provider<Boy> provider58, Provider<HTC> provider59, Provider<jbU> provider60, Provider<jcJ> provider61, Provider<OPl> provider62, Provider<kDa> provider63, Provider<zMV> provider64, Provider<Wyh> provider65, Provider<aeS> provider66, Provider<pBR> provider67, Provider<TrI> provider68, Provider<BluetoothScoController> provider69, Provider<IcB> provider70, Provider<XPi> provider71, Provider<QMR> provider72, Provider<mCQ> provider73, Provider<ZIT> provider74, Provider<JOD> provider75, Provider<QtV> provider76, Provider<ktr> provider77, Provider<DtD> provider78, Provider<bjR> provider79, Provider<Arv> provider80, Provider<Gson> provider81, Provider<rte> provider82, Provider<Ccz> provider83, Provider<AxK> provider84, Provider<XUD> provider85, Provider<tkT> provider86, Provider<hrT> provider87, Provider<Set<CapabilityAgent>> provider88, Provider<AYd> provider89, Provider<hlu> provider90, Provider<JXl> provider91, Provider<gSO> provider92, Provider<eDG> provider93, Provider<C0237wXy> provider94, Provider<Jvr> provider95, Provider<yjS> provider96, Provider<adp> provider97, Provider<szT> provider98, Provider<RfA> provider99, Provider<xUA> provider100, Provider<MBE> provider101, Provider<zDj> provider102) {
        return new AlexaClient_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24, provider25, provider26, provider27, provider28, provider29, provider30, provider31, provider32, provider33, provider34, provider35, provider36, provider37, provider38, provider39, provider40, provider41, provider42, provider43, provider44, provider45, provider46, provider47, provider48, provider49, provider50, provider51, provider52, provider53, provider54, provider55, provider56, provider57, provider58, provider59, provider60, provider61, provider62, provider63, provider64, provider65, provider66, provider67, provider68, provider69, provider70, provider71, provider72, provider73, provider74, provider75, provider76, provider77, provider78, provider79, provider80, provider81, provider82, provider83, provider84, provider85, provider86, provider87, provider88, provider89, provider90, provider91, provider92, provider93, provider94, provider95, provider96, provider97, provider98, provider99, provider100, provider101, provider102);
    }

    public static AlexaClient newAlexaClient(ScheduledExecutorService scheduledExecutorService, IYJ iyj, vkx vkxVar, NXS nxs, LPk lPk, AlexaClientEventBus alexaClientEventBus, AlexaNotificationManager alexaNotificationManager, QIY qiy, tol tolVar, bXh bxh, Context context, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation, Qoi qoi, brA bra, gMz gmz, FQX fqx, IOV iov, lhN lhn, OGT ogt, jdJ jdj, fjm fjmVar, QeM qeM, Lazy<SCB> lazy, Lazy<blL> lazy2, LuX luX, HVk hVk, oGE oge, yED yed, yYy yyy, AHr aHr, dAN dan, tjk tjkVar, UserInactivityAuthority userInactivityAuthority, DJb dJb, VEQ veq, yhM yhm, NDW ndw, KrR krR, Fsz fsz, pbo pboVar, qxC qxc, Qgh qgh, BrT brT, zmg zmgVar, Lnt lnt, JgM jgM, KvZ kvZ, C0234ubm c0234ubm, LrI lrI, YKQ ykq, lEV lev, pZY pzy, MessageTransformer messageTransformer, shl shlVar, vYS vys, Msx msx, DJw dJw, Boy boy, HTC htc, jbU jbu, jcJ jcj, OPl oPl, kDa kda, zMV zmv, Wyh wyh, aeS aes, pBR pbr, TrI trI, BluetoothScoController bluetoothScoController, IcB icB, XPi xPi, QMR qmr, mCQ mcq, ZIT zit, JOD jod, QtV qtV, ktr ktrVar, DtD dtD, bjR bjr, Lazy<Arv> lazy3, Gson gson, rte rteVar, Ccz ccz, AxK axK, XUD xud, tkT tkt, hrT hrt, Set<CapabilityAgent> set, AYd aYd, hlu hluVar, JXl jXl, gSO gso, eDG edg, C0237wXy c0237wXy, Jvr jvr, yjS yjs, adp adpVar, szT szt, RfA rfA, xUA xua, MBE mbe, zDj zdj) {
        return new AlexaClient(scheduledExecutorService, iyj, vkxVar, nxs, lPk, alexaClientEventBus, alexaNotificationManager, qiy, tolVar, bxh, context, alexaHandsFreeDeviceInformation, qoi, bra, gmz, fqx, iov, lhn, ogt, jdj, fjmVar, qeM, lazy, lazy2, luX, hVk, oge, yed, yyy, aHr, dan, tjkVar, userInactivityAuthority, dJb, veq, yhm, ndw, krR, fsz, pboVar, qxc, qgh, brT, zmgVar, lnt, jgM, kvZ, c0234ubm, lrI, ykq, lev, pzy, messageTransformer, shlVar, vys, msx, dJw, boy, htc, jbu, jcj, oPl, kda, zmv, wyh, aes, pbr, trI, bluetoothScoController, icB, xPi, qmr, mcq, zit, jod, qtV, ktrVar, dtD, bjr, lazy3, gson, rteVar, ccz, axK, xud, tkt, hrt, set, aYd, hluVar, jXl, gso, edg, c0237wXy, jvr, yjs, adpVar, szt, rfA, xua, mbe, zdj);
    }

    public static AlexaClient provideInstance(Provider<ScheduledExecutorService> provider, Provider<IYJ> provider2, Provider<vkx> provider3, Provider<NXS> provider4, Provider<LPk> provider5, Provider<AlexaClientEventBus> provider6, Provider<AlexaNotificationManager> provider7, Provider<QIY> provider8, Provider<tol> provider9, Provider<bXh> provider10, Provider<Context> provider11, Provider<AlexaHandsFreeDeviceInformation> provider12, Provider<Qoi> provider13, Provider<brA> provider14, Provider<gMz> provider15, Provider<FQX> provider16, Provider<IOV> provider17, Provider<lhN> provider18, Provider<OGT> provider19, Provider<jdJ> provider20, Provider<fjm> provider21, Provider<QeM> provider22, Provider<SCB> provider23, Provider<blL> provider24, Provider<LuX> provider25, Provider<HVk> provider26, Provider<oGE> provider27, Provider<yED> provider28, Provider<yYy> provider29, Provider<AHr> provider30, Provider<dAN> provider31, Provider<tjk> provider32, Provider<UserInactivityAuthority> provider33, Provider<DJb> provider34, Provider<VEQ> provider35, Provider<yhM> provider36, Provider<NDW> provider37, Provider<KrR> provider38, Provider<Fsz> provider39, Provider<pbo> provider40, Provider<qxC> provider41, Provider<Qgh> provider42, Provider<BrT> provider43, Provider<zmg> provider44, Provider<Lnt> provider45, Provider<JgM> provider46, Provider<KvZ> provider47, Provider<C0234ubm> provider48, Provider<LrI> provider49, Provider<YKQ> provider50, Provider<lEV> provider51, Provider<pZY> provider52, Provider<MessageTransformer> provider53, Provider<shl> provider54, Provider<vYS> provider55, Provider<Msx> provider56, Provider<DJw> provider57, Provider<Boy> provider58, Provider<HTC> provider59, Provider<jbU> provider60, Provider<jcJ> provider61, Provider<OPl> provider62, Provider<kDa> provider63, Provider<zMV> provider64, Provider<Wyh> provider65, Provider<aeS> provider66, Provider<pBR> provider67, Provider<TrI> provider68, Provider<BluetoothScoController> provider69, Provider<IcB> provider70, Provider<XPi> provider71, Provider<QMR> provider72, Provider<mCQ> provider73, Provider<ZIT> provider74, Provider<JOD> provider75, Provider<QtV> provider76, Provider<ktr> provider77, Provider<DtD> provider78, Provider<bjR> provider79, Provider<Arv> provider80, Provider<Gson> provider81, Provider<rte> provider82, Provider<Ccz> provider83, Provider<AxK> provider84, Provider<XUD> provider85, Provider<tkT> provider86, Provider<hrT> provider87, Provider<Set<CapabilityAgent>> provider88, Provider<AYd> provider89, Provider<hlu> provider90, Provider<JXl> provider91, Provider<gSO> provider92, Provider<eDG> provider93, Provider<C0237wXy> provider94, Provider<Jvr> provider95, Provider<yjS> provider96, Provider<adp> provider97, Provider<szT> provider98, Provider<RfA> provider99, Provider<xUA> provider100, Provider<MBE> provider101, Provider<zDj> provider102) {
        return new AlexaClient(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get(), provider10.mo10268get(), provider11.mo10268get(), provider12.mo10268get(), provider13.mo10268get(), provider14.mo10268get(), provider15.mo10268get(), provider16.mo10268get(), provider17.mo10268get(), provider18.mo10268get(), provider19.mo10268get(), provider20.mo10268get(), provider21.mo10268get(), provider22.mo10268get(), DoubleCheck.lazy(provider23), DoubleCheck.lazy(provider24), provider25.mo10268get(), provider26.mo10268get(), provider27.mo10268get(), provider28.mo10268get(), provider29.mo10268get(), provider30.mo10268get(), provider31.mo10268get(), provider32.mo10268get(), provider33.mo10268get(), provider34.mo10268get(), provider35.mo10268get(), provider36.mo10268get(), provider37.mo10268get(), provider38.mo10268get(), provider39.mo10268get(), provider40.mo10268get(), provider41.mo10268get(), provider42.mo10268get(), provider43.mo10268get(), provider44.mo10268get(), provider45.mo10268get(), provider46.mo10268get(), provider47.mo10268get(), provider48.mo10268get(), provider49.mo10268get(), provider50.mo10268get(), provider51.mo10268get(), provider52.mo10268get(), provider53.mo10268get(), provider54.mo10268get(), provider55.mo10268get(), provider56.mo10268get(), provider57.mo10268get(), provider58.mo10268get(), provider59.mo10268get(), provider60.mo10268get(), provider61.mo10268get(), provider62.mo10268get(), provider63.mo10268get(), provider64.mo10268get(), provider65.mo10268get(), provider66.mo10268get(), provider67.mo10268get(), provider68.mo10268get(), provider69.mo10268get(), provider70.mo10268get(), provider71.mo10268get(), provider72.mo10268get(), provider73.mo10268get(), provider74.mo10268get(), provider75.mo10268get(), provider76.mo10268get(), provider77.mo10268get(), provider78.mo10268get(), provider79.mo10268get(), DoubleCheck.lazy(provider80), provider81.mo10268get(), provider82.mo10268get(), provider83.mo10268get(), provider84.mo10268get(), provider85.mo10268get(), provider86.mo10268get(), provider87.mo10268get(), provider88.mo10268get(), provider89.mo10268get(), provider90.mo10268get(), provider91.mo10268get(), provider92.mo10268get(), provider93.mo10268get(), provider94.mo10268get(), provider95.mo10268get(), provider96.mo10268get(), provider97.mo10268get(), provider98.mo10268get(), provider99.mo10268get(), provider100.mo10268get(), provider101.mo10268get(), provider102.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaClient mo10268get() {
        return provideInstance(this.sharedExecutorServiceProvider, this.clientConnectionsAuthorityProvider, this.alexaStateAuthorityProvider, this.alexaUserSpeechAuthorityProvider, this.alexaAudioPlaybackAuthorityProvider, this.alexaClientEventBusProvider, this.alexaNotificationManagerProvider, this.alertsAuthorityProvider, this.authAuthorityProvider, this.capabilityAgentManagerProvider, this.contextProvider, this.alexaHandsFreeDeviceInformationProvider, this.messageProcessingSequencerProvider, this.interactionSchedulerProvider, this.visualInteractionSchedulerProvider, this.audioActivityTrackerProvider, this.visualActivityTrackerProvider, this.componentStateAuthorityProvider, this.userSpeechProviderAuthorityProvider, this.userSpeechProviderRegistryProvider, this.speechSynthesizerCapabilityAgentProvider, this.speechSynthesizerComponentStateAuthorityProvider, this.androidLocationComponentProvider, this.geolocationGooglePlayComponentProvider, this.speechRecognizerCapabilityAgentProvider, this.audioPlayerCapabilityAgentProvider, this.audioPlayerComponentStateAuthorityProvider, this.systemCapabilityAgentProvider, this.cardRendererCapabilityAgentProvider, this.settingsControllerProvider, this.networkAuthorityProvider, this.becomingNoisyManagerProvider, this.userInactivityAuthorityProvider, this.speakerCapabilityAgentProvider, this.soundEffectPlayerProvider, this.navigationCapabilityAgentProvider, this.alexaMediaPlaybackAuthorityProvider, this.volumeChangeHandlerProvider, this.metricsAuthorityProvider, this.eventBroadcastSenderProvider, this.connectivityAuthorityProvider, this.alexaSuppressionAuthorityProvider, this.promptPlayerProvider, this.mediaPlayersAuthorityProvider, this.mediaBrowserPlayerFinderProvider, this.externalCapabilityAgentFinderProvider, this.messageReceiverAuthorityProvider, this.internalCapabilityAgentRegistryProvider, this.externalCapabilityAgentRegistryProvider, this.externalComponentStateAuthorityProvider, this.internalComponentStateProvidersProvider, this.capabilityAuthorityProvider, this.messageTransformerProvider, this.attachmentStoreProvider, this.uiManagerProvider, this.lockscreenManagerProvider, this.externalMediaPlayerCapabilityAgentProvider, this.externalMediaPlayerStateProvider, this.playbackStateReporterProvider, this.instrumentationReceiverProvider, this.soundAuthorityProvider, this.audioFocusManagerProvider, this.visualFocusManagerProvider, this.interactionModelCapabilityAgentProvider, this.multiTurnDialogAuthorityProvider, this.settingsAuthorityProvider, this.ioComponentsComponentStateProvider, this.applicationManagerCapabilityAgentProvider, this.scoControllerProvider, this.deviceLocaleChangedBroadcastReceiverProvider, this.deviceTimeZoneChangedBroadCastReceiverProvider, this.packagesChangedBroadcastReceiverProvider, this.authChangeBroadcastReceiverProvider, this.locationProviderChangedBroadcastReceiverProvider, this.uiEventBroadcastReceiverProvider, this.alertsBroadcastReceiverProvider, this.batteryLevelReceiverProvider, this.powerConnectionReceiverProvider, this.wakeWordAuthorityProvider, this.phoneCallControllerComponentStateAuthorityProvider, this.gsonProvider, this.voiceInteractionAuthorityProvider, this.textInteractionAuthorityProvider, this.attentionSystemLatencyProcessorProvider, this.readinessAuthorityProvider, this.alexaLauncherCapabilityAgentProvider, this.attentionSystemAuthorityProvider, this.fireOsCapabilityAgentsProvider, this.trustedStatesComponentStateProvider, this.failedInteractionTrackerProvider, this.apiCallAuthorityProvider, this.featureFlagConfigurationAuthorityProvider, this.avsConnectionStateAuthorityProvider, this.alexaDisplayWindowComponentStateProvider, this.textCapabilityAgentProvider, this.textAuthorityProvider, this.internalTrustedStatesProvider, this.offlinePromptDownloadInitiatorProvider, this.wakeWordArtifactDownloadProvider, this.endpointAuthorityProvider, this.localeAuthorityProvider, this.timeZoneAuthorityProvider);
    }
}
