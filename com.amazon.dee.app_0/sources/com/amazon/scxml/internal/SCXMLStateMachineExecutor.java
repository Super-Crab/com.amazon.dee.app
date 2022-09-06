package com.amazon.scxml.internal;

import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SCXMLStateMachineExecutor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H&¨\u0006\u0015"}, d2 = {"Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "", "evaluateCondition", "", "cond", "", "execute", "", DeviceStateModule.CANCEL, "Lcom/amazon/scxml/internal/Cancel;", "unhandled", "Lcom/amazon/scxml/internal/Executable;", "invocation", "Lcom/amazon/scxml/internal/Invocation;", "raise", "Lcom/amazon/scxml/internal/Raise;", "send", "Lcom/amazon/scxml/internal/Send;", "registerActiveStatusChange", "state", "Lcom/amazon/scxml/internal/State;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SCXMLStateMachineExecutor {
    boolean evaluateCondition(@NotNull String str);

    void execute(@NotNull Cancel cancel);

    void execute(@NotNull Executable executable);

    void execute(@NotNull Invocation invocation);

    void execute(@NotNull Raise raise);

    void execute(@NotNull Send send);

    void registerActiveStatusChange(@NotNull State state);
}
