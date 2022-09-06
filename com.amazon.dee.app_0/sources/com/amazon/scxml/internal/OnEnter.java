package com.amazon.scxml.internal;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: OnEnter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/amazon/scxml/internal/OnEnter;", "Lcom/amazon/scxml/logging/Loggable;", "executables", "", "Lcom/amazon/scxml/internal/Executable;", "(Ljava/util/List;)V", "sourceState", "Lcom/amazon/scxml/internal/State;", "getSourceState", "()Lcom/amazon/scxml/internal/State;", "setSourceState", "(Lcom/amazon/scxml/internal/State;)V", "execute", "", "machine", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class OnEnter extends Loggable {
    private final List<Executable> executables;
    @Nullable
    private State sourceState;

    /* JADX WARN: Multi-variable type inference failed */
    public OnEnter(@NotNull List<? extends Executable> executables) {
        Intrinsics.checkParameterIsNotNull(executables, "executables");
        this.executables = executables;
    }

    public final void execute(@NotNull SCXMLStateMachineExecutor machine) {
        Intrinsics.checkParameterIsNotNull(machine, "machine");
        Object obj = this.sourceState;
        if (obj == null) {
            obj = WebConstants.UriConstants.QUESTIONMARK_KEY;
        }
        String valueOf = String.valueOf(obj);
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "SCXML:execute " + valueOf + ".ON_ENTER");
        for (Executable executable : this.executables) {
            executable.execute(machine);
        }
        Logger log2 = Loggable.Companion.getLog();
        String tag2 = getTAG();
        log2.i(tag2, "SCXML:execute " + valueOf + ".ON_ENTER DONE");
    }

    @Nullable
    public final State getSourceState() {
        return this.sourceState;
    }

    public final void setSourceState(@Nullable State state) {
        this.sourceState = state;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OnEnter(sourceState=[");
        outline107.append(this.sourceState);
        outline107.append("], executables=");
        return GeneratedOutlineSupport1.outline94(outline107, this.executables, ')');
    }
}
