package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.CallInfo;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.CallInfoTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CallInfoRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0019B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/CallInfoRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "callInfo", "Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/CallInfo;", "identifier", "", "(Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/CallInfo;Ljava/lang/String;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getCallInfo", "()Lcom/amazon/alexa/accessory/repositories/nonhfpcalling/CallInfo;", "getIdentifier", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CallInfoRequest implements Query.Request<CallInfoRequest> {
    @NotNull
    private final BundleTransformer<CallInfoRequest> bundleTransformer;
    @NotNull
    private final CallInfo callInfo;
    @NotNull
    private final String identifier;

    /* compiled from: CallInfoRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/CallInfoRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/CallInfoRequest;", "()V", "CALL_INFO_KEY", "", "IDENTIFIER_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<CallInfoRequest> {
        private static final String CALL_INFO_KEY = "callInfo";
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public CallInfoRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            CallInfoTransformer callInfoTransformer = CallInfoTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(CALL_INFO_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(CALL_INFO_KEY)!!");
            CallInfo mo568fromBundle = callInfoTransformer.mo568fromBundle(bundle2);
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            return new CallInfoRequest(mo568fromBundle, string);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull CallInfoRequest t) {
            Intrinsics.checkParameterIsNotNull(t, "t");
            Bundle bundle = new Bundle();
            bundle.putBundle(CALL_INFO_KEY, CallInfoTransformer.INSTANCE.toBundle(t.getCallInfo()));
            bundle.putString("identifier", t.getIdentifier());
            return bundle;
        }
    }

    public CallInfoRequest(@NotNull CallInfo callInfo, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(callInfo, "callInfo");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.callInfo = callInfo;
        this.identifier = identifier;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ CallInfoRequest copy$default(CallInfoRequest callInfoRequest, CallInfo callInfo, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            callInfo = callInfoRequest.callInfo;
        }
        if ((i & 2) != 0) {
            str = callInfoRequest.identifier;
        }
        return callInfoRequest.copy(callInfo, str);
    }

    @NotNull
    public final CallInfo component1() {
        return this.callInfo;
    }

    @NotNull
    public final String component2() {
        return this.identifier;
    }

    @NotNull
    public final CallInfoRequest copy(@NotNull CallInfo callInfo, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(callInfo, "callInfo");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new CallInfoRequest(callInfo, identifier);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CallInfoRequest)) {
                return false;
            }
            CallInfoRequest callInfoRequest = (CallInfoRequest) obj;
            return Intrinsics.areEqual(this.callInfo, callInfoRequest.callInfo) && Intrinsics.areEqual(this.identifier, callInfoRequest.identifier);
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
    public BundleTransformer<CallInfoRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final CallInfo getCallInfo() {
        return this.callInfo;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    public int hashCode() {
        CallInfo callInfo = this.callInfo;
        int i = 0;
        int hashCode = (callInfo != null ? callInfo.hashCode() : 0) * 31;
        String str = this.identifier;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CallInfoRequest(callInfo=");
        outline107.append(this.callInfo);
        outline107.append(", identifier=");
        return GeneratedOutlineSupport1.outline91(outline107, this.identifier, ")");
    }
}
