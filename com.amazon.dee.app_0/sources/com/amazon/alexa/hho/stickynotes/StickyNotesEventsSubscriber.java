package com.amazon.alexa.hho.stickynotes;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.Subscriber;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class StickyNotesEventsSubscriber implements Subscriber {
    private static final String TAG = "StickyNotesEventsSubscriber";
    private final Handler handler;
    private final UUID uuid = UUID.randomUUID();

    /* renamed from: com.amazon.alexa.hho.stickynotes.StickyNotesEventsSubscriber$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNotesEventsSubscriber$EventType = new int[EventType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNotesEventsSubscriber$EventType[EventType.DELETE_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNotesEventsSubscriber$EventType[EventType.DOWNLOAD_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$hho$stickynotes$StickyNotesEventsSubscriber$EventType[EventType.PREFETCH_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes8.dex */
    private enum EventType {
        DOWNLOAD_REQUEST("hho::sticky::media_download::request"),
        UPLOAD_REQUEST("hho::sticky::media_upload::request"),
        DELETE_REQUEST("hho::sticky::media_delete::request"),
        PREFETCH_REQUEST("hho::sticky::media_prefetch::request");
        
        private final String typeString;

        EventType(String str) {
            this.typeString = str;
        }

        @Nullable
        public static EventType fromTypeString(String str) {
            EventType[] values;
            for (EventType eventType : values()) {
                if (eventType.typeString.equalsIgnoreCase(str)) {
                    return eventType;
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public interface Handler {
        void handleDeleteRequest(@Nonnull String str);

        void handleDownloadRequest(@Nonnull MediaDownloadRequest mediaDownloadRequest);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StickyNotesEventsSubscriber(Handler handler) {
        this.handler = handler;
    }

    private void onDeleteRequestReceived(@Nonnull Message message) {
        String payloadAsString = message.getPayloadAsString();
        if (Strings.isNullOrEmpty(payloadAsString)) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Delete request without mediaId: ");
            outline107.append(message.toString());
            Log.e(str, outline107.toString());
            return;
        }
        this.handler.handleDeleteRequest(payloadAsString);
    }

    private void onDownloadRequestReceived(@Nonnull Message message, boolean z) {
        String[] split = message.getPayloadAsString().split(ProcessIdUtil.DEFAULT_PROCESSID, 2);
        if (split.length < 2) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid format of DOWNLOAD_REQUEST event payload: ");
            outline107.append(message.getPayloadAsString());
            Log.e(str, outline107.toString());
            return;
        }
        String str2 = split[0];
        String str3 = split[1];
        StickyNoteMediaType fromString = StickyNoteMediaType.fromString(str2.toUpperCase(Locale.ROOT));
        if (fromString == null) {
            GeneratedOutlineSupport1.outline162("Unsupported media type for DOWNLOAD_REQUEST event: ", str2, TAG);
        } else {
            this.handler.handleDownloadRequest(new MediaDownloadRequest(fromString, str3, z));
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    @Nonnull
    public UUID getUUID() {
        return this.uuid;
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public void onMessageReceived(@NonNull Message message) {
        EventType fromTypeString = EventType.fromTypeString(message.getEventType());
        if (fromTypeString == null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Skip message with type: ");
            outline107.append(message.getEventType());
            Log.e(str, outline107.toString());
            return;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Received message: ");
        outline1072.append(message.toString());
        outline1072.toString();
        int ordinal = fromTypeString.ordinal();
        if (ordinal == 0) {
            onDownloadRequestReceived(message, false);
        } else if (ordinal == 2) {
            onDeleteRequestReceived(message);
        } else if (ordinal != 3) {
            String str2 = TAG;
            Log.e(str2, "Unsupported eventType: " + fromTypeString);
        } else {
            onDownloadRequestReceived(message, true);
        }
    }

    @Override // com.amazon.alexa.eventbus.api.Subscriber
    public boolean supportsMessage(@NonNull Message message) {
        return EventType.fromTypeString(message.getEventType()) != null;
    }
}
