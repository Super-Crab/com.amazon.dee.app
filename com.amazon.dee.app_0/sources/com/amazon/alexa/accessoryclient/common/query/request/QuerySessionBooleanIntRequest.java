package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QuerySessionBooleanIntRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001cB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanIntRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "booleanValue", "", "intValue", "", "(Ljava/lang/String;ZI)V", "getBooleanValue", "()Z", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "getIntValue", "()I", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class QuerySessionBooleanIntRequest implements Query.Request<QuerySessionBooleanIntRequest> {
    private final boolean booleanValue;
    @NotNull
    private final BundleTransformer<QuerySessionBooleanIntRequest> bundleTransformer;
    @NotNull
    private final String identifier;
    private final int intValue;

    /* compiled from: QuerySessionBooleanIntRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanIntRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanIntRequest;", "()V", "BOOLEAN_KEY", "", "IDENTIFIER_KEY", "INT_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "querySessionBooleanIntRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<QuerySessionBooleanIntRequest> {
        private static final String BOOLEAN_KEY = "boolean";
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();
        private static final String INT_KEY = "int";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public QuerySessionBooleanIntRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            return new QuerySessionBooleanIntRequest(string, bundle.getBoolean(BOOLEAN_KEY), bundle.getInt(INT_KEY));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull QuerySessionBooleanIntRequest querySessionBooleanIntRequest) {
            Intrinsics.checkParameterIsNotNull(querySessionBooleanIntRequest, "querySessionBooleanIntRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", querySessionBooleanIntRequest.getIdentifier());
            bundle.putBoolean(BOOLEAN_KEY, querySessionBooleanIntRequest.getBooleanValue());
            bundle.putInt(INT_KEY, querySessionBooleanIntRequest.getIntValue());
            return bundle;
        }
    }

    public QuerySessionBooleanIntRequest(@NotNull String identifier, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.identifier = identifier;
        this.booleanValue = z;
        this.intValue = i;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ QuerySessionBooleanIntRequest copy$default(QuerySessionBooleanIntRequest querySessionBooleanIntRequest, String str, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = querySessionBooleanIntRequest.identifier;
        }
        if ((i2 & 2) != 0) {
            z = querySessionBooleanIntRequest.booleanValue;
        }
        if ((i2 & 4) != 0) {
            i = querySessionBooleanIntRequest.intValue;
        }
        return querySessionBooleanIntRequest.copy(str, z, i);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    public final boolean component2() {
        return this.booleanValue;
    }

    public final int component3() {
        return this.intValue;
    }

    @NotNull
    public final QuerySessionBooleanIntRequest copy(@NotNull String identifier, boolean z, int i) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new QuerySessionBooleanIntRequest(identifier, z, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuerySessionBooleanIntRequest)) {
                return false;
            }
            QuerySessionBooleanIntRequest querySessionBooleanIntRequest = (QuerySessionBooleanIntRequest) obj;
            return Intrinsics.areEqual(this.identifier, querySessionBooleanIntRequest.identifier) && this.booleanValue == querySessionBooleanIntRequest.booleanValue && this.intValue == querySessionBooleanIntRequest.intValue;
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
    public BundleTransformer<QuerySessionBooleanIntRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    public final int getIntValue() {
        return this.intValue;
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
        return ((hashCode + i) * 31) + this.intValue;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuerySessionBooleanIntRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", booleanValue=");
        outline107.append(this.booleanValue);
        outline107.append(", intValue=");
        return GeneratedOutlineSupport1.outline86(outline107, this.intValue, ")");
    }
}
