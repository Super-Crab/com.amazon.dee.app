package com.amazon.alexa.accessory.notificationpublisher.consumption;

import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class MessageUtils {
    private MessageUtils() {
    }

    public static Message createMessage(int i, int i2, @Nullable Object obj) throws NumberFormatException {
        Message obtain = Message.obtain();
        obtain.arg1 = i;
        obtain.arg2 = i2;
        obtain.what = generateMessageType(i, i2);
        obtain.obj = obj;
        return obtain;
    }

    public static int generateMessageType(int i, int i2) throws NumberFormatException {
        return Integer.decode(String.valueOf(i) + String.valueOf(i2)).intValue();
    }

    public static int getComponentId(@NonNull Message message) {
        return message.arg1;
    }

    public static int getEventId(@NonNull Message message) {
        return message.arg2;
    }

    public static int getMessageType(@NonNull Message message) {
        return message.what;
    }

    @Nullable
    public static Object getPayload(@NonNull Message message) {
        return message.obj;
    }
}
