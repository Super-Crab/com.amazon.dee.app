package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QuerySessionBooleanRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "booleanValue", "", "(Ljava/lang/String;Z)V", "getBooleanValue", "()Z", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class QuerySessionBooleanRequest implements Query.Request<QuerySessionBooleanRequest> {
    private final boolean booleanValue;
    @NotNull
    private final BundleTransformer<QuerySessionBooleanRequest> bundleTransformer;
    @NotNull
    private final String identifier;

    /* compiled from: QuerySessionBooleanRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanRequest;", "()V", "BOOLEAN_KEY", "", "IDENTIFIER_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "querySessionBooleanRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<QuerySessionBooleanRequest> {
        private static final String BOOLEAN_KEY = "boolean";
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public QuerySessionBooleanRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            return new QuerySessionBooleanRequest(string, bundle.getBoolean(BOOLEAN_KEY));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull QuerySessionBooleanRequest querySessionBooleanRequest) {
            Intrinsics.checkParameterIsNotNull(querySessionBooleanRequest, "querySessionBooleanRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", querySessionBooleanRequest.getIdentifier());
            bundle.putBoolean(BOOLEAN_KEY, querySessionBooleanRequest.getBooleanValue());
            return bundle;
        }
    }

    public QuerySessionBooleanRequest(@NotNull String identifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.identifier = identifier;
        this.booleanValue = z;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ QuerySessionBooleanRequest copy$default(QuerySessionBooleanRequest querySessionBooleanRequest, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = querySessionBooleanRequest.identifier;
        }
        if ((i & 2) != 0) {
            z = querySessionBooleanRequest.booleanValue;
        }
        return querySessionBooleanRequest.copy(str, z);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    public final boolean component2() {
        return this.booleanValue;
    }

    @NotNull
    public final QuerySessionBooleanRequest copy(@NotNull String identifier, boolean z) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new QuerySessionBooleanRequest(identifier, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuerySessionBooleanRequest)) {
                return false;
            }
            QuerySessionBooleanRequest querySessionBooleanRequest = (QuerySessionBooleanRequest) obj;
            return Intrinsics.areEqual(this.identifier, querySessionBooleanRequest.identifier) && this.booleanValue == querySessionBooleanRequest.booleanValue;
        }
        return true;
    }

    public final boolean getBooleanValue() {
        return this.booleanValue;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<QuerySessionBooleanRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    public int hashCode() {
        String str = this.identifier;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.booleanValue;
        if (z) {
            z = true;
        }
        int i = z ? 1 : 0;
        int i2 = z ? 1 : 0;
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuerySessionBooleanRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", booleanValue=");
        return GeneratedOutlineSupport1.outline97(outline107, this.booleanValue, ")");
    }
}
