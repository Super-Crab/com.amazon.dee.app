package com.amazon.deecomms.accessories;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AccessoriesHardwareIntentHandler_MembersInjector implements MembersInjector<AccessoriesHardwareIntentHandler> {
    private final Provider<AlexaAudioPlayer> mAlexaAudioPlayerProvider;
    private final Provider<CallManager> mCallManagerProvider;
    private final Provider<Context> mContextProvider;
    private final Provider<PCCContextProvider> mPccContextProvider;
    private final Provider<SipClientState> mSipClientStateProvider;
    private final Provider<TelephonyManager> mTelephonyManagerProvider;
    private final Provider<VipCallingHandler> mVipCallingHandlerProvider;

    public AccessoriesHardwareIntentHandler_MembersInjector(Provider<Context> provider, Provider<TelephonyManager> provider2, Provider<SipClientState> provider3, Provider<CallManager> provider4, Provider<PCCContextProvider> provider5, Provider<AlexaAudioPlayer> provider6, Provider<VipCallingHandler> provider7) {
        this.mContextProvider = provider;
        this.mTelephonyManagerProvider = provider2;
        this.mSipClientStateProvider = provider3;
        this.mCallManagerProvider = provider4;
        this.mPccContextProvider = provider5;
        this.mAlexaAudioPlayerProvider = provider6;
        this.mVipCallingHandlerProvider = provider7;
    }

    public static MembersInjector<AccessoriesHardwareIntentHandler> create(Provider<Context> provider, Provider<TelephonyManager> provider2, Provider<SipClientState> provider3, Provider<CallManager> provider4, Provider<PCCContextProvider> provider5, Provider<AlexaAudioPlayer> provider6, Provider<VipCallingHandler> provider7) {
        return new AccessoriesHardwareIntentHandler_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectMAlexaAudioPlayer(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, AlexaAudioPlayer alexaAudioPlayer) {
        accessoriesHardwareIntentHandler.mAlexaAudioPlayer = alexaAudioPlayer;
    }

    public static void injectMCallManager(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, CallManager callManager) {
        accessoriesHardwareIntentHandler.mCallManager = callManager;
    }

    public static void injectMContext(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, Context context) {
        accessoriesHardwareIntentHandler.mContext = context;
    }

    public static void injectMPccContextProvider(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, PCCContextProvider pCCContextProvider) {
        accessoriesHardwareIntentHandler.mPccContextProvider = pCCContextProvider;
    }

    public static void injectMSipClientState(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, SipClientState sipClientState) {
        accessoriesHardwareIntentHandler.mSipClientState = sipClientState;
    }

    public static void injectMTelephonyManager(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, TelephonyManager telephonyManager) {
        accessoriesHardwareIntentHandler.mTelephonyManager = telephonyManager;
    }

    public static void injectMVipCallingHandler(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler, VipCallingHandler vipCallingHandler) {
        accessoriesHardwareIntentHandler.mVipCallingHandler = vipCallingHandler;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AccessoriesHardwareIntentHandler accessoriesHardwareIntentHandler) {
        accessoriesHardwareIntentHandler.mContext = this.mContextProvider.mo10268get();
        accessoriesHardwareIntentHandler.mTelephonyManager = this.mTelephonyManagerProvider.mo10268get();
        accessoriesHardwareIntentHandler.mSipClientState = this.mSipClientStateProvider.mo10268get();
        accessoriesHardwareIntentHandler.mCallManager = this.mCallManagerProvider.mo10268get();
        accessoriesHardwareIntentHandler.mPccContextProvider = this.mPccContextProvider.mo10268get();
        accessoriesHardwareIntentHandler.mAlexaAudioPlayer = this.mAlexaAudioPlayerProvider.mo10268get();
        accessoriesHardwareIntentHandler.mVipCallingHandler = this.mVipCallingHandlerProvider.mo10268get();
    }
}
