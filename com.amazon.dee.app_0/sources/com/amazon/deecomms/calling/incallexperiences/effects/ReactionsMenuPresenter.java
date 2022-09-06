package com.amazon.deecomms.calling.incallexperiences.effects;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelEventListener;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelEventListenerImpl;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.datachannel.PayloadVersion;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.SepiaEventName;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.ReactionsDataChannelEventHandler;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsDataStore;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsMetric;
import com.amazon.deecomms.calling.incallexperiences.reactions.ui.ReactionsMenuButtonFragment;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetMpuEnabled;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class ReactionsMenuPresenter implements EffectsDataChannelEventListener, AmcIncallDataChannelApplication {
    private static final String APPLICATION_NAME = "Sepia";
    private static final long DATA_CHANNEL_ACK_TIMEOUT_MS = 2000;
    private static final long DISABLED_MENU_TIMEOUT_MS = 10000;
    private static final CommsLogger LOG = CommsLogger.getLogger(ReactionsMenuPresenter.class);
    private final String callProvider;
    private final String calleeCommsId;
    private final String callerCommsId;
    private final CapabilitiesManager capabilitiesManager;
    private final GetMpuEnabled captionsChecker;
    private long clickedTimestamp;
    private final Context context;
    private String currentEffectId;
    private final Runnable dataChannelAckTimeoutRunnable;
    private final EffectsDataChannelEventHandler dataChannelEventHandler;
    private AmcIncallDataChannelEventListenerImpl dataChannelEventListener;
    private EffectsState effectsState;
    private final Gson gson;
    private final boolean isGroupCall;
    private boolean isScribeEnabled;
    private final EffectsDataStore reactionsDataStore;
    private final Runnable reactionsMenuDisabledTimeoutRunnable;
    private final ReactionsMenuButtonFragment reactionsMenuViewContract;
    private EffectIcon selectedEffect;
    private final SipClientState sipClientState;
    private int tapCount;
    private final Handler uiHandler;

    /* renamed from: com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter$2  reason: invalid class name */
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

    public ReactionsMenuPresenter(@NonNull Context context, @NonNull SipClientState sipClientState, @NonNull boolean z, @NonNull String str, @NonNull String str2, @NonNull EffectsState effectsState, CapabilitiesManager capabilitiesManager) {
        this(context, sipClientState, z, new ReactionsMenuButtonFragment(context), new ReactionsDataChannelEventHandler(sipClientState.getCurrentActiveCall()), EffectsDataStore.getReactionsInstance(), new Handler(Looper.getMainLooper()), AmcIncallDataChannelEventListenerImpl.getInstance(sipClientState.getCurrentActiveCall()), new GetMpuEnabled(), str, str2, sipClientState.getCurrentActiveCall().getProvider(), effectsState, capabilitiesManager, false);
        this.reactionsMenuViewContract.setPresenter(this);
        this.dataChannelEventHandler.registerEffectsDataChannelEventListener(this);
        this.dataChannelEventListener.registerApplication(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$1(ReactionsMenuButtonFragment reactionsMenuButtonFragment) {
        LOG.i("[REACTIONS] Did not receive confirmation that the Reaction was removed. Re-enabling menu.");
        reactionsMenuButtonFragment.enableMenu();
    }

    public boolean areEffectsAvailable() {
        return (this.reactionsDataStore.getEffectData() == null || this.reactionsDataStore.getEffectData().getEffectIcons() == null || this.reactionsDataStore.getEffectData().getEffectIcons().isEmpty()) ? false : true;
    }

    public void fetchScribeSetting() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                try {
                    ReactionsMenuPresenter.this.isScribeEnabled = ReactionsMenuPresenter.this.captionsChecker.getMpuEnabled(ReactionsMenuPresenter.this.sipClientState.getSide().equalsIgnoreCase(Call.Side.Local.toString()) ? ReactionsMenuPresenter.this.calleeCommsId : ReactionsMenuPresenter.this.callerCommsId, ReactionsMenuPresenter.this.callProvider).isEnabled();
                    return true;
                } catch (ServiceException e) {
                    CommsLogger commsLogger = ReactionsMenuPresenter.LOG;
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

    public ReactionsMenuButtonFragment getReactionsMenuView() {
        return this.reactionsMenuViewContract;
    }

    public ReactionsMenuButtonFragment getViewContract() {
        return this.reactionsMenuViewContract;
    }

    public void hideMenu() {
        this.reactionsMenuViewContract.hideMenu();
    }

    public boolean isEffectsMenuOpen() {
        return this.reactionsMenuViewContract.isVisible();
    }

    public /* synthetic */ void lambda$new$0$ReactionsMenuPresenter(ReactionsMenuButtonFragment reactionsMenuButtonFragment, Context context) {
        if (this.currentEffectId == null || this.selectedEffect == null) {
            return;
        }
        reactionsMenuButtonFragment.showToast(context, R.string.effects_failed_to_apply);
        LOG.i("[REACTIONS] No ACK received for Apply effect. Timeout!!");
        this.tapCount = 0;
    }

    public /* synthetic */ void lambda$onApplyFailed$2$ReactionsMenuPresenter(String str) {
        this.reactionsMenuViewContract.showToast(this.context, str);
        this.tapCount = 0;
    }

    public /* synthetic */ void lambda$onRemoveFailed$3$ReactionsMenuPresenter(String str) {
        this.reactionsMenuViewContract.showToast(this.context, str);
        this.tapCount = 0;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onApplied() {
        if (this.selectedEffect != null) {
            this.dataChannelEventHandler.sendMetricEvent(EffectsMetric.MetricName.SEPIA_UPL_APPLY_REACTION.toString(), Long.valueOf(System.currentTimeMillis() - this.clickedTimestamp), this.selectedEffect.getId());
        }
        LOG.i("[REACTIONS] Received confirmation for Apply reaction. Reaction playing.");
        this.reactionsMenuViewContract.disableMenu();
        this.uiHandler.postDelayed(this.reactionsMenuDisabledTimeoutRunnable, 10000L);
        this.tapCount = 0;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onApplyFailed(final String str) {
        if (this.selectedEffect != null) {
            this.dataChannelEventHandler.sendMetricEvent(EffectsMetric.MetricName.SEPIA_UPL_APPLY_REACTION_FAILED.toString(), Long.valueOf(System.currentTimeMillis() - this.clickedTimestamp), this.selectedEffect.getId());
        }
        if (!this.capabilitiesManager.isSepiaMessagingImprovementsEnabled()) {
            str = this.context.getString(R.string.reaction_failed_to_apply);
        }
        LOG.i("[REACTIONS] Reaction failed to apply");
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$ReactionsMenuPresenter$WbonaD1PB2sHPtwvpbVqvL-ej04
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuPresenter.this.lambda$onApplyFailed$2$ReactionsMenuPresenter(str);
            }
        });
    }

    public void onBackButtonPressed() {
        this.reactionsMenuViewContract.hideMenu();
        this.reactionsMenuViewContract.showButton();
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
                CommsLogger commsLogger = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("[REACTIONS] Received unsupported CommsDataChannelEvent: ");
                outline1.append(commsDataChannelEvent.toString());
                commsLogger.e(outline1.toString());
            } else {
                this.dataChannelEventHandler.processBeginEffectsSessionEvent(commsDataChannelEvent);
            }
        }
    }

    public void onEffectButtonTapped(@NonNull EffectIcon effectIcon) {
        if (this.tapCount == 0) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("[REACTIONS] Clicked reaction: ");
            outline1.append(effectIcon.getName());
            commsLogger.i(outline1.toString());
            this.clickedTimestamp = System.currentTimeMillis();
            this.tapCount = 1;
            setCurrentEffect(effectIcon);
            this.dataChannelEventHandler.apply(effectIcon.getId());
            this.uiHandler.postDelayed(this.dataChannelAckTimeoutRunnable, 2000L);
        }
    }

    public void onEffectsAvailable() {
        LOG.i("[REACTIONS] Reactions are available. Loading thumbnails...");
        ArrayList arrayList = new ArrayList();
        for (EffectIcon effectIcon : this.reactionsDataStore.getEffectData().getEffectIcons()) {
            arrayList.add(effectIcon.getThumbnail());
        }
        this.reactionsMenuViewContract.showButton();
    }

    public void onEffectsStateChanged() {
        if (this.effectsState.isEffectActive()) {
            this.reactionsMenuViewContract.hideMenu();
            this.reactionsMenuViewContract.hideButton();
        }
    }

    public void onMenuButtonTapped() {
        LOG.i("[REACTIONS] Clicked reactions menu button.");
        if (this.sipClientState.isRTTSettingEnabled()) {
            this.reactionsMenuViewContract.showToast(this.context, R.string.effects_disabled_when_rtt_on);
        } else if (this.isScribeEnabled) {
            this.reactionsMenuViewContract.showToast(this.context, R.string.effects_disabled_when_captions_on);
        } else if (areEffectsAvailable()) {
            this.reactionsMenuViewContract.showMenu();
        } else if (this.sipClientState.isEnhancedProcessedCall()) {
            if (this.reactionsDataStore.getEffectData() != null) {
                return;
            }
            this.reactionsMenuViewContract.showToast(this.context, R.string.effects_something_went_wrong);
        } else {
            this.reactionsMenuViewContract.showToast(this.context, R.string.u_need_some_opt_in);
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onRemoveFailed(final String str) {
        if (!this.capabilitiesManager.isSepiaMessagingImprovementsEnabled()) {
            str = this.context.getString(R.string.reaction_failed_to_remove);
        }
        LOG.i("[REACTIONS] Reaction failed to remove");
        this.uiHandler.post(new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$ReactionsMenuPresenter$GNomEgHcdPNLidZDyFrJHEuIxwA
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuPresenter.this.lambda$onRemoveFailed$3$ReactionsMenuPresenter(str);
            }
        });
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void onRemoved() {
        LOG.i("[REACTIONS] Received confirmation for Remove reaction. Re-enabling menu.");
        this.uiHandler.removeCallbacks(this.reactionsMenuDisabledTimeoutRunnable);
        this.reactionsMenuViewContract.enableMenu();
        setCurrentEffect(null);
        this.tapCount = 0;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener
    public void refreshMenu() {
        this.reactionsMenuViewContract.refreshMenu();
    }

    @VisibleForTesting
    void setCurrentEffect(@Nullable EffectIcon effectIcon) {
        this.currentEffectId = effectIcon == null ? null : effectIcon.getId();
        this.selectedEffect = effectIcon;
    }

    public boolean shouldShowMenuButton() {
        return (this.callProvider.equals(CallProvider.A2A) || this.callProvider.equals(CallProvider.Alexa)) && ImmutableSet.of("US", "CA", "MX").contains(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false)) && !this.isGroupCall && !this.effectsState.isEffectActive();
    }

    public void tearDownMenu() {
        this.reactionsMenuViewContract.hideMenu();
        this.reactionsMenuViewContract.hideButton();
        this.dataChannelEventHandler.unregisterEffectsDataChannelEventListener();
        this.dataChannelEventListener.unregisterDataChannelEventListener();
        this.reactionsDataStore.deleteEffectData();
    }

    @VisibleForTesting
    ReactionsMenuPresenter(final Context context, SipClientState sipClientState, boolean z, final ReactionsMenuButtonFragment reactionsMenuButtonFragment, EffectsDataChannelEventHandler effectsDataChannelEventHandler, EffectsDataStore effectsDataStore, Handler handler, DataChannelEventListener dataChannelEventListener, GetMpuEnabled getMpuEnabled, String str, String str2, String str3, EffectsState effectsState, CapabilitiesManager capabilitiesManager, boolean z2) {
        this.context = context;
        this.sipClientState = sipClientState;
        this.isGroupCall = z;
        this.reactionsMenuViewContract = reactionsMenuButtonFragment;
        this.dataChannelEventHandler = effectsDataChannelEventHandler;
        this.reactionsDataStore = effectsDataStore;
        this.uiHandler = handler;
        this.dataChannelEventListener = (AmcIncallDataChannelEventListenerImpl) dataChannelEventListener;
        this.gson = new GsonBuilder().setVersion(PayloadVersion.LATEST.getValue()).create();
        this.captionsChecker = getMpuEnabled;
        this.callerCommsId = str;
        this.calleeCommsId = str2;
        this.callProvider = str3;
        this.effectsState = effectsState;
        this.capabilitiesManager = capabilitiesManager;
        this.isScribeEnabled = z2;
        this.dataChannelAckTimeoutRunnable = new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$ReactionsMenuPresenter$pGYJPhGhCfSqvR5Ig1DHKvOlO-o
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuPresenter.this.lambda$new$0$ReactionsMenuPresenter(reactionsMenuButtonFragment, context);
            }
        };
        this.reactionsMenuDisabledTimeoutRunnable = new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.effects.-$$Lambda$ReactionsMenuPresenter$W6nAinHPTO9NRf544Xlxx4wJjmM
            @Override // java.lang.Runnable
            public final void run() {
                ReactionsMenuPresenter.lambda$new$1(ReactionsMenuButtonFragment.this);
            }
        };
    }
}
