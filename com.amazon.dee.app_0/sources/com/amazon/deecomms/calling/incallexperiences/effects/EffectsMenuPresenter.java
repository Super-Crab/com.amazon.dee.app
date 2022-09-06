package com.amazon.deecomms.calling.incallexperiences.effects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelEventListenerImpl;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract;
import com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuViewContract;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.SepiaEventName;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.DefaultEffectsDataChannelEventHandler;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsDataStore;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsMetric;
import com.amazon.deecomms.calling.incallexperiences.effects.ui.EffectsBottomSheetDialogFragmentView;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetMpuEnabled;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
/* loaded from: classes12.dex */
public class EffectsMenuPresenter implements EffectsMenuPresenterContract, AmcIncallDataChannelApplication, EffectsDataChannelEventListener {
    private static final String APPLICATION_NAME = "Sepia";
    private static final long DATA_CHANNEL_ACK_TIMEOUT_MS = 2000;
    private static final String EFFECTS_BOTTOMSHEET_TAG = "EffectsBottomSheet";
    private final String callProvider;
    private final String calleeCommsId;
    private final String callerCommsId;
    private final CapabilitiesManager capabilitiesManager;
    private final GetMpuEnabled captionsChecker;
    private long clickedTimestamp;
    private Context context;
    public final Runnable dataChannelAckTimeoutRunnable;
    private DefaultEffectsDataChannelEventHandler dataChannelEventHandler;
    private AmcIncallDataChannelEventListenerImpl dataChannelEventListener;
    private EffectsBottomSheetDialogFragmentView effectsBottomSheetDialogFragmentView;
    private EffectsDataStore effectsDataStore;
    private EffectsState effectsState;
    private final boolean isGroupCall;
    private boolean isScribeEnabled;
    private final CommsLogger log;
    private Optional<ReactionsMenuPresenter> reactionsMenuPresenter;
    private EffectIcon selectedEffectIcon;
    private SipClientState sipClientState;
    private int tapCount;
    private Handler uiHandler;

    /* renamed from: com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$SepiaEventName = new int[SepiaEventName.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$SepiaEventName[SepiaEventName.ACK_STATUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$SepiaEventName[SepiaEventName.CURRENT_STATUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$SepiaEventName[SepiaEventName.BEGIN_EFFECTS_SESSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public EffectsMenuPresenter(Context context, SipClientState sipClientState, boolean z, String str, String str2, ReactionsMenuPresenter reactionsMenuPresenter, EffectsState effectsState, CapabilitiesManager capabilitiesManager) {
        this(context, sipClientState, z, new EffectsBottomSheetDialogFragmentView(context), new DefaultEffectsDataChannelEventHandler(sipClientState.getCurrentActiveCall()), EffectsDataStore.getEffectsInstance(), new Handler(Looper.getMainLooper()), new GetMpuEnabled(), str, str2, sipClientState.getCurrentActiveCall().getProvider(), reactionsMenuPresenter, effectsState, capabilitiesManager, false);
        this.effectsBottomSheetDialogFragmentView.setPresenter(this);
        this.dataChannelEventHandler.registerEffectsDataChannelEventListener(this);
        this.dataChannelEventListener = AmcIncallDataChannelEventListenerImpl.getInstance(sipClientState.getCurrentActiveCall());
        this.dataChannelEventListener.registerApplication(this);
    }

    private EffectIcon findActiveEffect() {
        if (this.effectsDataStore.getEffectData() != null) {
            for (EffectIcon effectIcon : this.effectsDataStore.getEffectData().getEffectIcons()) {
                if (effectIcon.isActive()) {
                    return effectIcon;
                }
            }
            return null;
        }
        return null;
    }

    private EffectIcon getEffectIcon(int i) {
        return this.effectsDataStore.getEffectData().getEffectIcons().get(i);
    }

    private void onClearButtonPressed() {
        EffectIcon findActiveEffect = findActiveEffect();
        if (findActiveEffect != null) {
            this.tapCount = 1;
            this.dataChannelEventHandler.remove(findActiveEffect.getId());
            this.uiHandler.postDelayed(this.dataChannelAckTimeoutRunnable, 2000L);
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public boolean areEffectsAvailable() {
        return (this.effectsDataStore.getEffectData() == null || this.effectsDataStore.getEffectData().getEffectIcons() == null || this.effectsDataStore.getEffectData().getEffectIcons().isEmpty()) ? false : true;
    }

    public void fetchScribeSetting() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                try {
                    EffectsMenuPresenter.this.isScribeEnabled = EffectsMenuPresenter.this.captionsChecker.getMpuEnabled(EffectsMenuPresenter.this.sipClientState.getSide().equalsIgnoreCase(Call.Side.Local.toString()) ? EffectsMenuPresenter.this.calleeCommsId : EffectsMenuPresenter.this.callerCommsId, EffectsMenuPresenter.this.callProvider).isEnabled();
                    return true;
                } catch (ServiceException e) {
                    CommsLogger commsLogger = EffectsMenuPresenter.this.log;
                    StringBuilder outline1 = GeneratedOutlineSupport.outline1("[REACTIONS] Error getting Scribe status!");
                    outline1.append(e.getStackTrace());
                    commsLogger.e(outline1.toString());
                    return false;
                }
            }
        }.execute(new Void[0]);
    }

    @Override // com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication
    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    public EffectsMenuViewContract getEffectsMenuView() {
        return this.effectsBottomSheetDialogFragmentView;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public boolean isEffectsMenuOpen() {
        return this.effectsBottomSheetDialogFragmentView.isShowing();
    }

    public /* synthetic */ void lambda$new$0$EffectsMenuPresenter() {
        EffectIcon effectIcon = this.selectedEffectIcon;
        if (effectIcon != null) {
            if (effectIcon.isActive()) {
                this.effectsBottomSheetDialogFragmentView.showToast(this.context, R.string.effects_failed_to_remove);
                this.log.i("[EFFECTS] No ACK received for Remove effect. Timeout!!");
            } else {
                this.effectsBottomSheetDialogFragmentView.showToast(this.context, R.string.effects_failed_to_apply);
                this.log.i("[EFFECTS] No ACK received for Apply effect. Timeout!!");
            }
        }
        this.tapCount = 0;
    }

    public /* synthetic */ void lambda$onApplied$1$EffectsMenuPresenter() {
        this.effectsState.setEffectActive(true);
        if (this.reactionsMenuPresenter.isPresent()) {
            this.reactionsMenuPresenter.get().onEffectsStateChanged();
        }
    }

    public /* synthetic */ void lambda$onApplyFailed$3$EffectsMenuPresenter(String str) {
        this.effectsBottomSheetDialogFragmentView.showToast(this.context, str);
        this.effectsState.setEffectActive(false);
        this.tapCount = 0;
    }

    public /* synthetic */ void lambda$onRemoveFailed$4$EffectsMenuPresenter(String str) {
        this.effectsBottomSheetDialogFragmentView.showToast(this.context, str);
        this.tapCount = 0;
    }

    public /* synthetic */ void lambda$onRemoved$2$EffectsMenuPresenter() {
        this.effectsState.setEffectActive(false);
        this.tapCount = 0;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onApplied() {
        if (this.selectedEffectIcon != null) {
            this.dataChannelEventHandler.sendMetricEvent(EffectsMetric.MetricName.SEPIA_UPL_APPLY_EFFECT.toString(), Long.valueOf(System.currentTimeMillis() - this.clickedTimestamp), this.selectedEffectIcon.getId());
        }
        this.log.i("[EFFECTS] Received confirmation for Apply effect ");
        if (this.effectsDataStore.isEffectActive()) {
            this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$EffectsMenuPresenter$Jp5QN4qo_1PPuxoBWQL0wV63DuI
                @Override // java.lang.Runnable
                public final void run() {
                    EffectsMenuPresenter.this.lambda$onApplied$1$EffectsMenuPresenter();
                }
            });
        }
        this.tapCount = 0;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onApplyFailed(final String str) {
        if (this.selectedEffectIcon != null) {
            this.dataChannelEventHandler.sendMetricEvent(EffectsMetric.MetricName.SEPIA_UPL_APPLY_EFFECT_FAILED.toString(), Long.valueOf(System.currentTimeMillis() - this.clickedTimestamp), this.selectedEffectIcon.getId());
        }
        if (!this.capabilitiesManager.isSepiaMessagingImprovementsEnabled()) {
            str = this.context.getString(R.string.effects_failed_to_apply);
        }
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$EffectsMenuPresenter$ss9RqxEwE3Ug6Y67LOpYeBpy9m4
            @Override // java.lang.Runnable
            public final void run() {
                EffectsMenuPresenter.this.lambda$onApplyFailed$3$EffectsMenuPresenter(str);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication
    public void onDataChannelEventReceived(CommsDataChannelEvent commsDataChannelEvent) {
        SepiaEventName lookupByEventName = SepiaEventName.lookupByEventName(commsDataChannelEvent.getHeader().getName());
        if (lookupByEventName != null) {
            int ordinal = lookupByEventName.ordinal();
            if (ordinal == 2) {
                if (!this.dataChannelEventHandler.processAckEvent(commsDataChannelEvent)) {
                    return;
                }
                this.uiHandler.removeCallbacks(this.dataChannelAckTimeoutRunnable);
            } else if (ordinal == 3) {
                this.dataChannelEventHandler.processCurrentStatusEvent(commsDataChannelEvent);
            } else if (ordinal != 4) {
                CommsLogger commsLogger = this.log;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("[EFFECTS] Received unsupported CommsDataChannelEvent: ");
                outline1.append(commsDataChannelEvent.toString());
                commsLogger.e(outline1.toString());
            } else {
                this.dataChannelEventHandler.processBeginEffectsSessionEvent(commsDataChannelEvent);
            }
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public void onEffectButtonTapped(@Nullable EffectIcon effectIcon) {
        if (this.tapCount == 0) {
            this.clickedTimestamp = System.currentTimeMillis();
            if (effectIcon != null) {
                CommsLogger commsLogger = this.log;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("[EFFECTS] Clicked effect: ");
                outline1.append(effectIcon.getName());
                commsLogger.i(outline1.toString());
                this.tapCount = 1;
                setCurrentEffect(effectIcon);
                if (effectIcon.isActive()) {
                    this.dataChannelEventHandler.remove(effectIcon.getId());
                } else {
                    this.dataChannelEventHandler.apply(effectIcon.getId());
                }
                this.uiHandler.postDelayed(this.dataChannelAckTimeoutRunnable, 2000L);
                return;
            }
            onClearButtonPressed();
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public void onEffectsAvailable() {
        this.log.i("[EFFECTS] Effects are available");
        this.effectsBottomSheetDialogFragmentView.showButton();
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public void onEffectsNotAvailable() {
        this.log.i("[EFFECTS] Effects aren't available. Showing gray effects button");
        if (this.isGroupCall) {
            this.effectsBottomSheetDialogFragmentView.hideButton();
        } else {
            this.effectsBottomSheetDialogFragmentView.showGrayedOutButton();
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public void onMenuButtonTapped() {
        this.log.i("[EFFECTS] Clicked effect menu button.");
        if (this.sipClientState.isRTTSettingEnabled()) {
            this.effectsBottomSheetDialogFragmentView.showToast(this.context, R.string.effects_disabled_when_rtt_on);
        } else if (this.isScribeEnabled) {
            this.effectsBottomSheetDialogFragmentView.showToast(this.context, R.string.effects_disabled_when_captions_on);
        } else if (areEffectsAvailable()) {
            if (this.reactionsMenuPresenter.isPresent()) {
                this.reactionsMenuPresenter.get().hideMenu();
            }
            this.effectsBottomSheetDialogFragmentView.showMenu(((AppCompatActivity) this.context).getSupportFragmentManager().beginTransaction(), EFFECTS_BOTTOMSHEET_TAG);
        } else if (this.sipClientState.isEnhancedProcessedCall()) {
            if (this.effectsDataStore.getEffectData() != null) {
                return;
            }
            this.effectsBottomSheetDialogFragmentView.showToast(this.context, R.string.effects_something_went_wrong);
        } else {
            this.effectsBottomSheetDialogFragmentView.showToast(this.context, R.string.u_need_some_opt_in);
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onRemoveFailed(final String str) {
        if (this.selectedEffectIcon != null && this.effectsState.isEffectActive()) {
            this.dataChannelEventHandler.sendMetricEvent(EffectsMetric.MetricName.SEPIA_UPL_REMOVE_EFFECT_FAILED.toString(), Long.valueOf(System.currentTimeMillis() - this.clickedTimestamp), this.selectedEffectIcon.getId());
        }
        if (!this.capabilitiesManager.isSepiaMessagingImprovementsEnabled()) {
            str = this.context.getString(R.string.effects_failed_to_remove);
        }
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$EffectsMenuPresenter$DIzGoecjI9EtlGN1UP_eubgXpFo
            @Override // java.lang.Runnable
            public final void run() {
                EffectsMenuPresenter.this.lambda$onRemoveFailed$4$EffectsMenuPresenter(str);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onRemoved() {
        if (this.selectedEffectIcon != null && this.effectsState.isEffectActive()) {
            this.dataChannelEventHandler.sendMetricEvent(EffectsMetric.MetricName.SEPIA_UPL_REMOVE_EFFECT.toString(), Long.valueOf(System.currentTimeMillis() - this.clickedTimestamp), this.selectedEffectIcon.getId());
        }
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$EffectsMenuPresenter$XC12HIqivy13uCWR1w2CSbaBgGI
            @Override // java.lang.Runnable
            public final void run() {
                EffectsMenuPresenter.this.lambda$onRemoved$2$EffectsMenuPresenter();
            }
        });
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void refreshMenu() {
        EffectsBottomSheetDialogFragmentView effectsBottomSheetDialogFragmentView = this.effectsBottomSheetDialogFragmentView;
        if (effectsBottomSheetDialogFragmentView != null) {
            effectsBottomSheetDialogFragmentView.refreshMenu();
        }
    }

    @VisibleForTesting
    void setCurrentEffect(@NonNull EffectIcon effectIcon) {
        this.selectedEffectIcon = effectIcon;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public boolean shouldShowMenuButton() {
        return (this.callProvider.equals(CallProvider.A2A) || this.callProvider.equals(CallProvider.Alexa)) && ImmutableSet.of("US", "CA", "MX").contains(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false)) && !this.isGroupCall;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.contracts.EffectsMenuPresenterContract
    public void tearDownMenu() {
        this.effectsState.setEffectActive(false);
        this.uiHandler.removeCallbacks(this.dataChannelAckTimeoutRunnable);
        EffectsBottomSheetDialogFragmentView effectsBottomSheetDialogFragmentView = this.effectsBottomSheetDialogFragmentView;
        if (effectsBottomSheetDialogFragmentView != null && effectsBottomSheetDialogFragmentView.isShowing()) {
            this.effectsBottomSheetDialogFragmentView.hideMenu();
        }
        AmcIncallDataChannelEventListenerImpl amcIncallDataChannelEventListenerImpl = this.dataChannelEventListener;
        if (amcIncallDataChannelEventListenerImpl != null) {
            amcIncallDataChannelEventListenerImpl.unregisterDataChannelEventListener();
        }
        this.dataChannelEventHandler.unregisterEffectsDataChannelEventListener();
        this.effectsDataStore.deleteEffectData();
    }

    @VisibleForTesting
    EffectsMenuPresenter(Context context, SipClientState sipClientState, boolean z, EffectsBottomSheetDialogFragmentView effectsBottomSheetDialogFragmentView, DefaultEffectsDataChannelEventHandler defaultEffectsDataChannelEventHandler, EffectsDataStore effectsDataStore, Handler handler, GetMpuEnabled getMpuEnabled, String str, String str2, String str3, ReactionsMenuPresenter reactionsMenuPresenter, EffectsState effectsState, CapabilitiesManager capabilitiesManager, boolean z2) {
        this.tapCount = 0;
        this.log = CommsLogger.getLogger(EffectsMenuPresenter.class);
        this.dataChannelAckTimeoutRunnable = new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$EffectsMenuPresenter$gsUQBoLn1LW8UdOQHRcbPNdCi5o
            @Override // java.lang.Runnable
            public final void run() {
                EffectsMenuPresenter.this.lambda$new$0$EffectsMenuPresenter();
            }
        };
        this.context = context;
        this.sipClientState = sipClientState;
        this.isGroupCall = z;
        this.effectsBottomSheetDialogFragmentView = effectsBottomSheetDialogFragmentView;
        this.dataChannelEventHandler = defaultEffectsDataChannelEventHandler;
        this.effectsDataStore = effectsDataStore;
        this.uiHandler = handler;
        this.captionsChecker = getMpuEnabled;
        this.calleeCommsId = str2;
        this.callProvider = str3;
        this.reactionsMenuPresenter = Optional.fromNullable(reactionsMenuPresenter);
        this.effectsState = effectsState;
        this.isScribeEnabled = z2;
        this.callerCommsId = str;
        this.capabilitiesManager = capabilitiesManager;
    }
}
