package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.StateTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0019\u001aB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/StateRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "state", "Lcom/amazon/alexa/accessory/protocol/StateOuterClass$State;", "identifier", "", "(Lcom/amazon/alexa/accessory/protocol/StateOuterClass$State;Ljava/lang/String;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "getState", "()Lcom/amazon/alexa/accessory/protocol/StateOuterClass$State;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class StateRequest implements Query.Request<StateRequest> {
    public static final Companion Companion = new Companion(null);
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String STATE_KEY = "state";
    @NotNull
    private final BundleTransformer<StateRequest> bundleTransformer;
    @NotNull
    private final String identifier;
    @NotNull
    private final StateOuterClass.State state;

    /* compiled from: StateRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/StateRequest$Companion;", "", "()V", "IDENTIFIER_KEY", "", "STATE_KEY", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: StateRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/StateRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/StateRequest;", "()V", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "stateRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<StateRequest> {
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public StateRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            StateTransformer stateTransformer = StateTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(StateRequest.STATE_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(STATE_KEY)!!");
            StateOuterClass.State mo568fromBundle = stateTransformer.mo568fromBundle(bundle2);
            String string = bundle.getString(StateRequest.IDENTIFIER_KEY);
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            return new StateRequest(mo568fromBundle, string);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull StateRequest stateRequest) {
            Intrinsics.checkParameterIsNotNull(stateRequest, "stateRequest");
            Bundle bundle = new Bundle();
            bundle.putBundle(StateRequest.STATE_KEY, StateTransformer.INSTANCE.toBundle(stateRequest.getState()));
            bundle.putString(StateRequest.IDENTIFIER_KEY, stateRequest.getIdentifier());
            return bundle;
        }
    }

    public StateRequest(@NotNull StateOuterClass.State state, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.state = state;
        this.identifier = identifier;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ StateRequest copy$default(StateRequest stateRequest, StateOuterClass.State state, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            state = stateRequest.state;
        }
        if ((i & 2) != 0) {
            str = stateRequest.identifier;
        }
        return stateRequest.copy(state, str);
    }

    @NotNull
    public final StateOuterClass.State component1() {
        return this.state;
    }

    @NotNull
    public final String component2() {
        return this.identifier;
    }

    @NotNull
    public final StateRequest copy(@NotNull StateOuterClass.State state, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new StateRequest(state, identifier);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof StateRequest)) {
                return false;
            }
            StateRequest stateRequest = (StateRequest) obj;
            return Intrinsics.areEqual(this.state, stateRequest.state) && Intrinsics.areEqual(this.identifier, stateRequest.identifier);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<StateRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    @NotNull
    public final StateOuterClass.State getState() {
        return this.state;
    }

    public int hashCode() {
        StateOuterClass.State state = this.state;
        int i = 0;
        int hashCode = (state != null ? state.hashCode() : 0) * 31;
        String str = this.identifier;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StateRequest(state=");
        outline107.append(this.state);
        outline107.append(", identifier=");
        return GeneratedOutlineSupport1.outline91(outline107, this.identifier, ")");
    }
}
