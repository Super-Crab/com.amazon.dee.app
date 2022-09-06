package com.amazon.alexa.accessory.frames.eventbus;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.frames.contacts.ContactsModule;
import com.amazon.alexa.accessory.frames.metrics.MetricsConstants;
import com.amazon.alexa.accessory.frames.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.frames.topContact.TopContactConstants;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class EventBusManager {
    private static final String CONTACTS_VERIFICATION_EVENTBUS_RESPONSE = "CONTACTS:REDUX_PERSIST:DB:RESPONSE";
    private static final String DELETE_TOP_CONTACT = "Zion:EventBus:DeleteTopContact";
    private static final String SAVE_TOP_CONTACT = "Zion:EventBus:SaveTopContact";
    private static final String TAG = "EventBusManager";
    private static final String ZION_EVENTBUS_MESSAGE = "Zion:EventBus:*";

    private EventBusManager() {
    }

    public static Message createMessage(String str, String str2) {
        Log.d(TAG, "createMessage - with eventType: %s, payload: %s", str, str2);
        return new Message.Builder().setSource(Message.Source.Local).setEventType(str).setPayload(str2).build();
    }

    public static void subscribeToEventBusMessages() {
        Log.d(TAG, "subscribeToEventBusMessages - Subscribing to event bus message");
        MultiFilterSubscriber newSubscriber = DependencyProvider.getEventBus().getNewSubscriber();
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(CONTACTS_VERIFICATION_EVENTBUS_RESPONSE), new MessageHandler() { // from class: com.amazon.alexa.accessory.frames.eventbus.EventBusManager.1
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                try {
                    String str = EventBusManager.TAG;
                    Log.i(str, "subscribeToEventBusMessages - Received Message: " + message.getEventType());
                    String str2 = EventBusManager.TAG;
                    Log.d(str2, "subscribeToEventBusMessages - Payload: " + message.getPayloadAsString());
                    if (TopContactManager.getInstance().getTTSState() != TopContactManager.TTSState.CONTACT_VERIFICATION) {
                        return;
                    }
                    ContactsModule.contactResponse = new JSONObject(message.getPayloadAsString());
                    TopContactManager.getInstance().setTTSState(TopContactManager.TTSState.NONE);
                    ContactsModule.getInstance().verifyAndUpdateContactDetails();
                    if (!TopContactManager.topContactFlowInprogress.get()) {
                        return;
                    }
                    TopContactManager.getInstance().requestAudioFocusIfRequired();
                } catch (JSONException e) {
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_PARSE_EVENT_BUS_MESSAGE_FAILED);
                    String str3 = EventBusManager.TAG;
                    Log.e(str3, "subscribeToEventBusMessages - handle message failed with exception " + e);
                }
            }
        });
        newSubscriber.subscribeFilter(new EventTypeMessageFilter(ZION_EVENTBUS_MESSAGE), new MessageHandler() { // from class: com.amazon.alexa.accessory.frames.eventbus.EventBusManager.2
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public void handle(@NonNull Message message) {
                char c;
                String str = EventBusManager.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("subscribeToEventBusMessages - Received Message: ");
                outline107.append(message.getEventType());
                Log.i(str, outline107.toString());
                String eventType = message.getEventType();
                int hashCode = eventType.hashCode();
                if (hashCode != -27121282) {
                    if (hashCode == 814432464 && eventType.equals(EventBusManager.SAVE_TOP_CONTACT)) {
                        c = 0;
                    }
                    c = 65535;
                } else {
                    if (eventType.equals(EventBusManager.DELETE_TOP_CONTACT)) {
                        c = 1;
                    }
                    c = 65535;
                }
                if (c == 0) {
                    Log.d(EventBusManager.TAG, "save new contact");
                    TopContactManager.contactNameHasChanged.set(true);
                } else if (c != 1) {
                    String str2 = EventBusManager.TAG;
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("subscribeToEventBusMessages - Unsupported Message: ");
                    outline1072.append(message.getEventType());
                    Log.i(str2, outline1072.toString());
                } else {
                    File file = new File(GeneratedOutlineSupport1.outline91(new StringBuilder(), TopContactManager.folderPath, TopContactConstants.TOP_CONTACT_CALLING_AUDIO));
                    if (!file.exists()) {
                        return;
                    }
                    file.delete();
                }
            }
        });
    }

    public static Message createMessage(String str) {
        String str2 = TAG;
        Log.d(str2, "createMessage - with eventType: " + str);
        return new Message.Builder().setSource(Message.Source.Local).setEventType(str).build();
    }
}
