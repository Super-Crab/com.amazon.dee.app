package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DataChannelDTO;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.datachannel.Capability;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelHeader;
import com.amazon.deecomms.calling.datachannel.Namespace;
import com.amazon.deecomms.calling.datachannel.PayloadVersion;
import com.amazon.deecomms.calling.enums.DataChannelLabel;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectParameter;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.AckEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.ApplyEffectEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.BeginEffectsSessionEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.CurrentStatusEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.MetricEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.RemoveEffectEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.eventPayloads.SepiaEventName;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectCategory;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectData;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectIcon;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsDataStore;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EffectsMetric;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EventMetadata;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.model.EventMetadataQueue;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
/* loaded from: classes12.dex */
public class DefaultEffectsDataChannelEventHandler implements EffectsDataChannelEventHandler {
    private static final String APPLICATION = "Sepia";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DefaultEffectsDataChannelEventHandler.class);
    private final Call call;
    final EffectsDataStore effectsDataStore;
    private final EventMetadataQueue eventMetadataQueue;
    final Gson gson;
    EffectsDataChannelEventListener listener;
    boolean madeEffectRequest;

    /* renamed from: com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.DefaultEffectsDataChannelEventHandler$1  reason: invalid class name */
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

    public DefaultEffectsDataChannelEventHandler(Call call) {
        this(call, EffectsDataStore.getEffectsInstance(), EventMetadataQueue.getEffectsInstance());
    }

    private boolean isActiveEffectAWorld(List<String> list) {
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

    private void sendDataChannelEvent(@NonNull CommsDataChannelHeader commsDataChannelHeader, JsonElement jsonElement) {
        String json = this.gson.toJson(CommsDataChannelEvent.builder().withHeader(commsDataChannelHeader).withPayload(jsonElement).build());
        this.call.sendData(new DataChannelDTO(DataChannelLabel.IN_CALL_APPLICATION.getName(), json.getBytes(StandardCharsets.UTF_8), false));
        GeneratedOutlineSupport.outline4("[SEPIA] Sent event through DataChannel data=", json, LOG);
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void apply(@NonNull String str) {
        String str2;
        EffectData effectData = this.effectsDataStore.getEffectData();
        List<EffectParameter> emptyList = Collections.emptyList();
        if (effectData != null) {
            String effectsSessionId = effectData.getEffectsSessionId();
            emptyList = effectData.getParameters();
            str2 = effectsSessionId;
        } else {
            str2 = "";
        }
        String uuid = UUID.randomUUID().toString();
        EventMetadataQueue eventMetadataQueue = this.eventMetadataQueue;
        if (eventMetadataQueue != null) {
            eventMetadataQueue.removeStaleDataChannelEvent(0);
            this.eventMetadataQueue.add(EventMetadata.builder().timestamp(System.currentTimeMillis()).requestId(uuid).build());
        }
        sendDataChannelEvent(CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withInstance(APPLICATION).withCapability(Capability.SCREEN_EVENTS).withName(SepiaEventName.APPLY_EFFECT.toString()).build(), this.gson.toJsonTree(ApplyEffectEvent.builder().withRequestId(uuid).withEffectsSessionId(str2).withEffectId(str).withParameters(emptyList).build()));
        this.madeEffectRequest = true;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public boolean processAckEvent(CommsDataChannelEvent commsDataChannelEvent) {
        AckEvent ackEvent = (AckEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) AckEvent.class);
        this.eventMetadataQueue.removeStaleDataChannelEvent(0);
        if (this.eventMetadataQueue.size() > 0) {
            EventMetadata mo3625poll = this.eventMetadataQueue.mo3625poll();
            if (ackEvent.getRequestId() != null && ackEvent.getRequestId().equals(mo3625poll.getRequestId())) {
                LOG.i("[SEPIA] Request Acknowledgment received");
                return true;
            }
            LOG.i("[SEPIA] Received Acknowledgement for another RequestId");
            this.eventMetadataQueue.add(mo3625poll);
        }
        return false;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void processBeginEffectsSessionEvent(CommsDataChannelEvent commsDataChannelEvent) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[SEPIA] Received BeginEffectsSessionEvent: ");
        outline1.append(commsDataChannelEvent.getPayload().toString());
        commsLogger.i(outline1.toString());
        BeginEffectsSessionEvent beginEffectsSessionEvent = (BeginEffectsSessionEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) BeginEffectsSessionEvent.class);
        EffectData.Builder isApplied = EffectData.builder().effectsSessionId(beginEffectsSessionEvent.getEffectsSessionId()).isApplied(beginEffectsSessionEvent.getActiveEffects() != null && !beginEffectsSessionEvent.getActiveEffects().isEmpty());
        ArrayList arrayList = new ArrayList();
        for (EffectCategory effectCategory : beginEffectsSessionEvent.getAvailableEffects()) {
            if (EffectCategory.EffectCategoryName.valueOf(effectCategory.getName().toUpperCase(Locale.getDefault())) == EffectCategory.EffectCategoryName.WORLD || EffectCategory.EffectCategoryName.valueOf(effectCategory.getName().toUpperCase(Locale.getDefault())) == EffectCategory.EffectCategoryName.VIDEO) {
                arrayList.addAll(effectCategory.getIcons());
            }
        }
        this.effectsDataStore.saveEffectData(isApplied.effectIcons(arrayList).build());
        ((EffectsMenuPresenter) this.listener).onEffectsAvailable();
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void processCurrentStatusEvent(CommsDataChannelEvent commsDataChannelEvent) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[SEPIA] Received CurrentStatusEvent: ");
        outline1.append(commsDataChannelEvent.getPayload().toString());
        commsLogger.i(outline1.toString());
        CurrentStatusEvent currentStatusEvent = (CurrentStatusEvent) this.gson.fromJson(commsDataChannelEvent.getPayload(), (Class<Object>) CurrentStatusEvent.class);
        sendAcknowledgment(currentStatusEvent.getRequestId());
        EffectData effectData = this.effectsDataStore.getEffectData();
        List<EffectIcon> emptyList = Collections.emptyList();
        if (effectData != null && effectData.getEffectIcons() != null) {
            emptyList = effectData.getEffectIcons();
        } else {
            LOG.e("[SEPIA] No previous available EffectInfo found!");
        }
        String status = currentStatusEvent.getStatus();
        int ordinal = CurrentStatusEvent.Status.valueOf(status.toUpperCase(Locale.getDefault())).ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                GeneratedOutlineSupport.outline3("[SEPIA] Unknown Status received", status, LOG);
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
        } else if (isActiveEffectAWorld(currentStatusEvent.getActiveEffects())) {
            saveCurrentStatus(emptyList, currentStatusEvent, effectData, true);
            this.listener.refreshMenu();
            this.listener.onApplied();
        }
        this.madeEffectRequest = false;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void registerEffectsDataChannelEventListener(EffectsDataChannelEventListener effectsDataChannelEventListener) {
        this.listener = effectsDataChannelEventListener;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void remove(@NonNull String str) {
        EffectData effectData = this.effectsDataStore.getEffectData();
        String effectsSessionId = effectData != null ? effectData.getEffectsSessionId() : "";
        String uuid = UUID.randomUUID().toString();
        EventMetadataQueue eventMetadataQueue = this.eventMetadataQueue;
        if (eventMetadataQueue != null) {
            eventMetadataQueue.removeStaleDataChannelEvent(0);
            this.eventMetadataQueue.add(EventMetadata.builder().timestamp(System.currentTimeMillis()).requestId(uuid).build());
        }
        sendDataChannelEvent(CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withInstance(APPLICATION).withCapability(Capability.SCREEN_EVENTS).withName(SepiaEventName.REMOVE_EFFECT.toString()).build(), this.gson.toJsonTree(RemoveEffectEvent.builder().requestId(uuid).effectsSessionId(effectsSessionId).effectId(str).build()));
        this.madeEffectRequest = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveCurrentStatus(List<EffectIcon> list, CurrentStatusEvent currentStatusEvent, EffectData effectData, boolean z) {
        if (z) {
            for (EffectIcon effectIcon : list) {
                if (effectIcon.getId().equals(currentStatusEvent.getActiveEffects().get(0))) {
                    effectIcon.setActive(true);
                } else {
                    effectIcon.setActive(false);
                }
            }
        } else {
            for (EffectIcon effectIcon2 : list) {
                if (effectIcon2.isActive()) {
                    effectIcon2.setActive(false);
                }
            }
        }
        this.effectsDataStore.saveEffectData(EffectData.builder().effectsSessionId(effectData.getEffectsSessionId()).effectIcons(list).targets(effectData.getTargets()).isApplied(z).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendAcknowledgment(@NonNull String str) {
        EffectData effectData = this.effectsDataStore.getEffectData();
        sendDataChannelEvent(CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withInstance(APPLICATION).withCapability(Capability.SCREEN_EVENTS).withName(SepiaEventName.ACK_STATUS.toString()).build(), this.gson.toJsonTree(AckEvent.builder().requestId(str).effectsSessionId(effectData != null ? effectData.getEffectsSessionId() : "").build()));
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void sendMetricEvent(@NonNull String str, @NonNull Long l, String str2) {
        EffectData effectData = this.effectsDataStore.getEffectData();
        String effectsSessionId = effectData != null ? effectData.getEffectsSessionId() : "";
        String uuid = UUID.randomUUID().toString();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("effectId", str2);
        sendDataChannelEvent(CommsDataChannelHeader.builder().withNamespace(Namespace.IN_CALL_CAPABILITY).withCapability(Capability.SCREEN_EVENTS).withInstance(APPLICATION).withName(SepiaEventName.SEPIA_METRICS.toString()).build(), this.gson.toJsonTree(MetricEvent.builder().withRequestId(uuid).withEffectsSessionId(effectsSessionId).withMetrics(Arrays.asList(EffectsMetric.builder().withMetricName(str).withValue(Double.valueOf(l.doubleValue())).withType("latency").withDimensions(hashMap).build())).build()));
    }

    @VisibleForTesting
    void setMadeEffectRequest(boolean z) {
        this.madeEffectRequest = z;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers.EffectsDataChannelEventHandler
    public void unregisterEffectsDataChannelEventListener() {
        this.listener = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public DefaultEffectsDataChannelEventHandler(Call call, EffectsDataStore effectsDataStore, EventMetadataQueue eventMetadataQueue) {
        this.madeEffectRequest = false;
        this.call = call;
        this.gson = new GsonBuilder().setVersion(PayloadVersion.LATEST.getValue()).create();
        this.effectsDataStore = effectsDataStore;
        this.eventMetadataQueue = eventMetadataQueue;
    }
}
