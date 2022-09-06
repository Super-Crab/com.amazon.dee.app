package com.amazon.alexa.accessoryclient.common.rxipc;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId;
import com.amazon.alexa.accessoryclient.common.transformers.BundleUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: RxIPCEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\tH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEvent;", "", "eventId", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;", "data", "Landroid/os/Bundle;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;Landroid/os/Bundle;)V", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;)V", "error", "", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;Ljava/lang/String;)V", "getData", "()Landroid/os/Bundle;", "getError", "()Ljava/lang/String;", "rxIPCEventId", "getRxIPCEventId", "()Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;", "toBundle", "action", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;", "toString", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RxIPCEvent {
    public static final Companion Companion = new Companion(null);
    private static final String DATA_KEY = "data";
    private static final String ERROR_KEY = "error";
    private static final String RX_RN_EVENT_ID_KEY = "eventId";
    @NotNull
    private final Bundle data;
    @Nullable
    private final String error;
    @NotNull
    private final RxIPCEventId rxIPCEventId;

    /* compiled from: RxIPCEvent.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEvent$Companion;", "", "()V", "DATA_KEY", "", "ERROR_KEY", "RX_RN_EVENT_ID_KEY", "fromBundle", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEvent;", "bundle", "Landroid/os/Bundle;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final RxIPCEvent fromBundle(@NotNull Bundle bundle) {
            RxIPCEvent rxIPCEvent;
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            if (bundle.getString("error") != null) {
                RxIPCEventId.Companion companion = RxIPCEventId.Companion;
                Bundle bundle2 = bundle.getBundle("eventId");
                if (bundle2 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(RX_RN_EVENT_ID_KEY)!!");
                RxIPCEventId from = companion.from(bundle2);
                String string = bundle.getString("error");
                if (string == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(ERROR_KEY)!!");
                rxIPCEvent = new RxIPCEvent(from, string);
            } else {
                RxIPCEventId.Companion companion2 = RxIPCEventId.Companion;
                Bundle bundle3 = bundle.getBundle("eventId");
                if (bundle3 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(bundle3, "bundle.getBundle(RX_RN_EVENT_ID_KEY)!!");
                RxIPCEventId from2 = companion2.from(bundle3);
                Bundle bundle4 = bundle.getBundle("data");
                if (bundle4 == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(bundle4, "bundle.getBundle(DATA_KEY)!!");
                rxIPCEvent = new RxIPCEvent(from2, bundle4);
            }
            return rxIPCEvent;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RxIPCEvent(@NotNull RxIPCEventId eventId, @NotNull Bundle data) {
        Intrinsics.checkParameterIsNotNull(eventId, "eventId");
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.rxIPCEventId = eventId;
        this.data = data;
        this.error = null;
    }

    @NotNull
    public final Bundle getData() {
        return this.data;
    }

    @Nullable
    public final String getError() {
        return this.error;
    }

    @NotNull
    public final RxIPCEventId getRxIPCEventId() {
        return this.rxIPCEventId;
    }

    @NotNull
    public final Bundle toBundle(@NotNull RxIPCEventId.Action action) {
        Intrinsics.checkParameterIsNotNull(action, "action");
        Bundle bundle = new Bundle();
        bundle.putBundle("eventId", this.rxIPCEventId.toBundle(action));
        bundle.putBundle("data", this.data);
        String str = this.error;
        if (str != null) {
            bundle.putString("error", str);
        }
        return bundle;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxRNEvent{id='");
        outline107.append(this.rxIPCEventId);
        outline107.append(Chars.QUOTE);
        outline107.append(", bundle=");
        outline107.append(BundleUtils.bundleToString(this.data));
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public RxIPCEvent(@NotNull RxIPCEventId eventId) {
        Intrinsics.checkParameterIsNotNull(eventId, "eventId");
        this.rxIPCEventId = eventId;
        this.data = new Bundle();
        this.error = null;
    }

    @NotNull
    public final Bundle toBundle() {
        return toBundle(this.rxIPCEventId.getAction());
    }

    public RxIPCEvent(@NotNull RxIPCEventId eventId, @NotNull String error) {
        Intrinsics.checkParameterIsNotNull(eventId, "eventId");
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.rxIPCEventId = eventId;
        this.data = new Bundle();
        this.error = error;
    }
}
