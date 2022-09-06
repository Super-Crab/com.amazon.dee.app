package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.BeginEffectsSessionEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.CurrentStatusEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectCategory;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectData;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsDataStore;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EventMetadataQueue;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public class ReactionsDataChannelEventHandler extends DefaultEffectsDataChannelEventHandler {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DefaultEffectsDataChannelEventHandler.class);

    /* renamed from: com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.ReactionsDataChannelEventHandler$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$CurrentStatusEvent$Status = new int[CurrentStatusEvent.Status.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$CurrentStatusEvent$Status[CurrentStatusEvent.Status.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$effects$datachannel$eventPayloads$CurrentStatusEvent$Status[CurrentStatusEvent.Status.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ReactionsDataChannelEventHandler(Call call) {
        super(call, EffectsDataStore.getReactionsInstance(), EventMetadataQueue.getReactionsInstance());
    }

    private boolean isActiveEffectAReaction(List<String> list) {
        if (!list.isEmpty()) {
            String str = list.get(0);
            EffectData effectData = this.effectsDataStore.getEffectData();
            if (effectData != null && effectData.getEffectIcons() != null && !effectData.getEffectIcons().isEmpty()) {
                for (EffectIcon effectIcon : effectData.getEffectIcons()) {
                    if (effectIcon.getId().equals(str)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.DefaultEffectsDataChannelEventHandler, com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void processBeginEffectsSessionEvent(CommsDataChannelEvent commsDataChannelEvent) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[REACTIONS] Received BeginEffectsSessionEvent: ");
        outline1.append(commsDataChannelEvent.getPayload().toString());
        commsLogger.i(outline1.toString());
        BeginEffectsSessionEvent beginEffectsSessionEvent = (BeginEffectsSessionEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) BeginEffectsSessionEvent.class);
        EffectData.Builder isApplied = EffectData.builder().effectsSessionId(beginEffectsSessionEvent.getEffectsSessionId()).isApplied(beginEffectsSessionEvent.getActiveEffects() != null && !beginEffectsSessionEvent.getActiveEffects().isEmpty());
        for (EffectCategory effectCategory : beginEffectsSessionEvent.getAvailableEffects()) {
            if (EffectCategory.EffectCategoryName.valueOf(effectCategory.getName().toUpperCase(Locale.getDefault())) == EffectCategory.EffectCategoryName.REACTION) {
                this.effectsDataStore.saveEffectData(isApplied.effectIcons(effectCategory.getIcons()).build());
                ((ReactionsMenuPresenter) this.listener).onEffectsAvailable();
            }
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.DefaultEffectsDataChannelEventHandler, com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void processCurrentStatusEvent(CommsDataChannelEvent commsDataChannelEvent) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[REACTIONS] Received CurrentStatusEvent: ");
        outline1.append(commsDataChannelEvent.getPayload().toString());
        commsLogger.i(outline1.toString());
        CurrentStatusEvent currentStatusEvent = (CurrentStatusEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) CurrentStatusEvent.class);
        sendAcknowledgment(currentStatusEvent.getRequestId());
        EffectData effectData = this.effectsDataStore.getEffectData();
        List<EffectIcon> emptyList = Collections.emptyList();
        if (effectData != null && effectData.getEffectIcons() != null) {
            emptyList = effectData.getEffectIcons();
        } else {
            LOG.e("[REACTIONS] No previous available EffectInfo found!");
        }
        String status = currentStatusEvent.getStatus();
        int ordinal = CurrentStatusEvent.Status.valueOf(status.toUpperCase(Locale.getDefault())).ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                GeneratedOutlineSupport.outline3("[REACTIONS] Unknown Status received", status, LOG);
            } else if (currentStatusEvent.getActiveEffects().isEmpty()) {
                if (this.madeEffectRequest) {
                    this.listener.refreshMenu();
                    this.listener.onApplyFailed(currentStatusEvent.getMessage());
                }
            } else if (this.madeEffectRequest) {
                this.listener.refreshMenu();
                this.listener.onRemoveFailed(currentStatusEvent.getMessage());
            }
        } else if (currentStatusEvent.getActiveEffects().isEmpty() && effectData != null) {
            saveCurrentStatus(emptyList, currentStatusEvent, effectData, false);
            this.listener.refreshMenu();
            this.listener.onRemoved();
        } else if (isActiveEffectAReaction(currentStatusEvent.getActiveEffects())) {
            saveCurrentStatus(emptyList, currentStatusEvent, effectData, true);
            this.listener.refreshMenu();
            this.listener.onApplied();
        }
        this.madeEffectRequest = false;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.DefaultEffectsDataChannelEventHandler, com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void remove(@NonNull String str) {
    }

    @VisibleForTesting
    ReactionsDataChannelEventHandler(Call call, EffectsDataStore effectsDataStore, EventMetadataQueue eventMetadataQueue) {
        super(call, effectsDataStore, eventMetadataQueue);
    }
}
