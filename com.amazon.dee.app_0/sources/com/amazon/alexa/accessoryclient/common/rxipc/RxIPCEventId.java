package com.amazon.alexa.accessoryclient.common.rxipc;

import android.os.Bundle;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
/* compiled from: RxIPCEventId.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003J\b\u0010\u000f\u001a\u00020\u0006H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;", "", "action", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;", "(Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;)V", "uuid", "", "(Ljava/lang/String;Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;)V", "getAction", "()Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;", "getUuid", "()Ljava/lang/String;", "toBundle", "Landroid/os/Bundle;", "newAction", "toString", "Action", "Companion", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RxIPCEventId {
    private static final String ACTION_KEY = "action";
    private static final String UUID_KEY = "uuid";
    @NotNull
    private final Action action;
    @NotNull
    private final String uuid;
    public static final Companion Companion = new Companion(null);
    private static final Action[] ACTION_VALUES = Action.values();

    /* compiled from: RxIPCEventId.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;", "", "action", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "INIT", "ON_NEXT", "ON_ERROR", "ON_COMPLETE", "ON_DISPOSE", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum Action {
        INIT("INIT"),
        ON_NEXT("ON_NEXT"),
        ON_ERROR("ON_ERROR"),
        ON_COMPLETE("ON_COMPLETE"),
        ON_DISPOSE("ON_DISPOSE");
        
        private final String action;

        Action(String str) {
            this.action = str;
        }

        @Override // java.lang.Enum
        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline90(GeneratedOutlineSupport1.outline107("Action{action='"), this.action, Chars.QUOTE, JsonReaderKt.END_OBJ);
        }
    }

    /* compiled from: RxIPCEventId.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Companion;", "", "()V", "ACTION_KEY", "", "ACTION_VALUES", "", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;", "[Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId$Action;", "UUID_KEY", ADMConstants.EXTRA_FROM, "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCEventId;", "bundle", "Landroid/os/Bundle;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final RxIPCEventId from(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("uuid");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(UUID_KEY)!!");
            return new RxIPCEventId(string, RxIPCEventId.ACTION_VALUES[bundle.getInt("action")]);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RxIPCEventId(@NotNull String uuid, @NotNull Action action) {
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(action, "action");
        this.uuid = uuid;
        this.action = action;
    }

    @NotNull
    public final Action getAction() {
        return this.action;
    }

    @NotNull
    public final String getUuid() {
        return this.uuid;
    }

    @NotNull
    public final Bundle toBundle(@NotNull Action newAction) {
        Intrinsics.checkParameterIsNotNull(newAction, "newAction");
        Bundle bundle = new Bundle();
        bundle.putString("uuid", this.uuid);
        bundle.putInt("action", newAction.ordinal());
        return bundle;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RxRNEventId{action=");
        outline107.append(this.action.name());
        outline107.append(", uuid='");
        return GeneratedOutlineSupport1.outline90(outline107, this.uuid, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RxIPCEventId(@org.jetbrains.annotations.NotNull com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId.Action r3) {
        /*
            r2 = this;
            java.lang.String r0 = "action"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "UUID.randomUUID().toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r2.<init>(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId.<init>(com.amazon.alexa.accessoryclient.common.rxipc.RxIPCEventId$Action):void");
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("uuid", this.uuid);
        bundle.putInt("action", this.action.ordinal());
        return bundle;
    }
}
