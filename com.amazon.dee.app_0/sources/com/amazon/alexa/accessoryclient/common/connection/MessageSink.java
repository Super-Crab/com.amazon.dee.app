package com.amazon.alexa.accessoryclient.common.connection;

import android.os.Message;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MessageSink.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/connection/MessageSink;", "", "dispose", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleMessage", "message", "Landroid/os/Message;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MessageSink {
    void dispose(@NotNull Exception exc);

    void handleMessage(@NotNull Message message);
}
