package com.amazon.alexa.eventbus.publisher;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Publisher;
import java.util.UUID;
/* loaded from: classes7.dex */
public class BroadcastPublisher implements Publisher {
    public static final String INTENT_ACTION = "EVENT_BUS_MSG";
    public static final String INTENT_PAYLOAD_EVENT_BUS_UUID = "bus";
    public static final String INTENT_PAYLOAD_MESSAGE = "msg";
    @NonNull
    private Context context;
    @NonNull
    private UUID eventBusUUID;
    @Nullable
    private Publisher publisher;

    public BroadcastPublisher(@NonNull Context context, @NonNull UUID uuid, @Nullable Publisher publisher) {
        this.context = context;
        this.eventBusUUID = uuid;
        this.publisher = publisher;
    }

    @VisibleForTesting
    Intent buildIntentForMessage(Message message) {
        Intent intent = new Intent(INTENT_ACTION);
        intent.putExtra(INTENT_PAYLOAD_EVENT_BUS_UUID, this.eventBusUUID);
        intent.putExtra("msg", message);
        return intent;
    }

    @Override // com.amazon.alexa.eventbus.api.Publisher
    public void publish(@NonNull Message message) {
        Publisher publisher = this.publisher;
        if (publisher != null) {
            publisher.publish(message);
        }
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(buildIntentForMessage(message));
    }
}
