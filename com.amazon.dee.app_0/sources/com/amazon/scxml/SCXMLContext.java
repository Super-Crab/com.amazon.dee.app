package com.amazon.scxml;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SCXMLContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/amazon/scxml/SCXMLContext;", "TUserContext", "", "userContext", "_event", "Lcom/amazon/scxml/SCXMLEvent;", "(Ljava/lang/Object;Lcom/amazon/scxml/SCXMLEvent;)V", "get_event", "()Lcom/amazon/scxml/SCXMLEvent;", "getUserContext", "()Ljava/lang/Object;", "Ljava/lang/Object;", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SCXMLContext<TUserContext> {
    @Nullable
    private final SCXMLEvent _event;
    private final TUserContext userContext;

    public SCXMLContext(TUserContext tusercontext, @Nullable SCXMLEvent sCXMLEvent) {
        this.userContext = tusercontext;
        this._event = sCXMLEvent;
    }

    public final TUserContext getUserContext() {
        return this.userContext;
    }

    @Nullable
    public final SCXMLEvent get_event() {
        return this._event;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXMLContext(userContext = [");
        outline107.append(this.userContext);
        outline107.append("], event = [");
        outline107.append(this._event);
        outline107.append("])");
        return outline107.toString();
    }
}
