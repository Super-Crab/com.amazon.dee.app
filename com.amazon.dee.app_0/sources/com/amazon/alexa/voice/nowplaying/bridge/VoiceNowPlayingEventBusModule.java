package com.amazon.alexa.voice.nowplaying.bridge;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.alexa.voice.nowplaying.AudioPlaybackController;
import java.util.UUID;
import javax.inject.Inject;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class VoiceNowPlayingEventBusModule {
    private final AudioPlaybackController audioPlaybackController;
    @Inject
    EventBus eventBus;
    private MultiFilterSubscriber multiFilterSubscriber;
    private UUID subscribedID = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class EcToVoxMessageFilter implements MessageFilter {
        EcToVoxMessageFilter() {
        }

        private boolean isMessageFromEntertainmentChannel(@NonNull Message message) {
            return message.getEventType() != null && MessageEventType.NP_MESSAGE_EC_TO_VOX.equals(message.getEventType());
        }

        @Override // com.amazon.alexa.eventbus.api.MessageFilter
        public boolean isMatch(@NonNull Message message) {
            return isMessageFromEntertainmentChannel(message);
        }
    }

    /* loaded from: classes11.dex */
    private static final class MessageEventType {
        public static final String NP_MESSAGE_EC_TO_VOX = "vox::npMessageFromEC";
        public static final String NP_MESSAGE_VOX_TO_EC = "vox::npMessageToEC";

        private MessageEventType() {
        }
    }

    public VoiceNowPlayingEventBusModule(AudioPlaybackController audioPlaybackController) {
        this.audioPlaybackController = audioPlaybackController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEntertainmentToVoiceMessage(@NonNull Message message) {
        VoiceBridgePayloadUtil.translatePayloadToPlayerAction(message.getPayloadAsString(), this.audioPlaybackController);
    }

    private UUID subscribeToEcMessages() {
        this.multiFilterSubscriber = this.eventBus.getSubscriber();
        return this.multiFilterSubscriber.subscribe(new EcToVoxMessageFilter(), new MessageHandler() { // from class: com.amazon.alexa.voice.nowplaying.bridge.-$$Lambda$VoiceNowPlayingEventBusModule$QKlOH-nMtUC_zEjyOMmhToou5zk
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                VoiceNowPlayingEventBusModule.this.handleEntertainmentToVoiceMessage(message);
            }
        });
    }

    public void broadcastVoxPlaybackInfo(JSONObject jSONObject) {
        this.eventBus.publish(new Message.Builder().setDate(System.currentTimeMillis()).setEventType(MessageEventType.NP_MESSAGE_VOX_TO_EC).setPayload(jSONObject.toString()).setDate(System.currentTimeMillis()).setSource(Message.Source.Local).build());
    }

    public void initialize() {
        injectDependencies();
        this.subscribedID = subscribeToEcMessages();
    }

    @VisibleForTesting
    void injectDependencies() {
        VoiceDependencies.inject(this);
    }

    public void release() {
        UUID uuid = this.subscribedID;
        if (uuid != null) {
            this.multiFilterSubscriber.unsubscribe(uuid);
        }
    }
}
