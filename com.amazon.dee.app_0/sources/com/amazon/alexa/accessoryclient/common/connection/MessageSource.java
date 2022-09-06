package com.amazon.alexa.accessoryclient.common.connection;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: MessageSource.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/connection/MessageSource;", "", "setMessageSink", "", "messageSink", "Lcom/amazon/alexa/accessoryclient/common/connection/MessageSink;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface MessageSource {
    void setMessageSink(@NotNull MessageSink messageSink);
}
