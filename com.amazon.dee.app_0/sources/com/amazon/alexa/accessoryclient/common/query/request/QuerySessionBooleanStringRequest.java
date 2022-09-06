package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QuerySessionBooleanStringRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanStringRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "booleanValue", "", "stringValue", "(Ljava/lang/String;ZLjava/lang/String;)V", "getBooleanValue", "()Z", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "getStringValue", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class QuerySessionBooleanStringRequest implements Query.Request<QuerySessionBooleanStringRequest> {
    private final boolean booleanValue;
    @NotNull
    private final BundleTransformer<QuerySessionBooleanStringRequest> bundleTransformer;
    @NotNull
    private final String identifier;
    @NotNull
    private final String stringValue;

    /* compiled from: QuerySessionBooleanStringRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanStringRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionBooleanStringRequest;", "()V", "BOOLEAN_KEY", "", "IDENTIFIER_KEY", "STRING_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "querySessionBooleanStringRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<QuerySessionBooleanStringRequest> {
        private static final String BOOLEAN_KEY = "boolean";
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();
        private static final String STRING_KEY = "string";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public QuerySessionBooleanStringRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            boolean z = bundle.getBoolean(BOOLEAN_KEY);
            String string2 = bundle.getString(STRING_KEY);
            if (string2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string2, "bundle.getString(STRING_KEY)!!");
            return new QuerySessionBooleanStringRequest(string, z, string2);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull QuerySessionBooleanStringRequest querySessionBooleanStringRequest) {
            Intrinsics.checkParameterIsNotNull(querySessionBooleanStringRequest, "querySessionBooleanStringRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", querySessionBooleanStringRequest.getIdentifier());
            bundle.putBoolean(BOOLEAN_KEY, querySessionBooleanStringRequest.getBooleanValue());
            bundle.putString(STRING_KEY, querySessionBooleanStringRequest.getStringValue());
            return bundle;
        }
    }

    public QuerySessionBooleanStringRequest(@NotNull String identifier, boolean z, @NotNull String stringValue) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(stringValue, "stringValue");
        this.identifier = identifier;
        this.booleanValue = z;
        this.stringValue = stringValue;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ QuerySessionBooleanStringRequest copy$default(QuerySessionBooleanStringRequest querySessionBooleanStringRequest, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = querySessionBooleanStringRequest.identifier;
        }
        if ((i & 2) != 0) {
            z = querySessionBooleanStringRequest.booleanValue;
        }
        if ((i & 4) != 0) {
            str2 = querySessionBooleanStringRequest.stringValue;
        }
        return querySessionBooleanStringRequest.copy(str, z, str2);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    public final boolean component2() {
        return this.booleanValue;
    }

    @NotNull
    public final String component3() {
        return this.stringValue;
    }

    @NotNull
    public final QuerySessionBooleanStringRequest copy(@NotNull String identifier, boolean z, @NotNull String stringValue) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(stringValue, "stringValue");
        return new QuerySessionBooleanStringRequest(identifier, z, stringValue);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuerySessionBooleanStringRequest)) {
                return false;
            }
            QuerySessionBooleanStringRequest querySessionBooleanStringRequest = (QuerySessionBooleanStringRequest) obj;
            return Intrinsics.areEqual(this.identifier, querySessionBooleanStringRequest.identifier) && this.booleanValue == querySessionBooleanStringRequest.booleanValue && Intrinsics.areEqual(this.stringValue, querySessionBooleanStringRequest.stringValue);
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
    public BundleTransformer<QuerySessionBooleanStringRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    @NotNull
    public final String getStringValue() {
        return this.stringValue;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.booleanValue;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = (hashCode + i2) * 31;
        String str2 = this.stringValue;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return i4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuerySessionBooleanStringRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", booleanValue=");
        outline107.append(this.booleanValue);
        outline107.append(", stringValue=");
        return GeneratedOutlineSupport1.outline91(outline107, this.stringValue, ")");
    }
}
