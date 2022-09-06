package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QuerySessionStringIntRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionStringIntRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "stringValue", "intValue", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "getIntValue", "()I", "getStringValue", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class QuerySessionStringIntRequest implements Query.Request<QuerySessionStringIntRequest> {
    @NotNull
    private final BundleTransformer<QuerySessionStringIntRequest> bundleTransformer;
    @NotNull
    private final String identifier;
    private final int intValue;
    @NotNull
    private final String stringValue;

    /* compiled from: QuerySessionStringIntRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionStringIntRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionStringIntRequest;", "()V", "IDENTIFIER_KEY", "", "INT_KEY", "STRING_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "querySessionStringIntRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<QuerySessionStringIntRequest> {
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();
        private static final String INT_KEY = "int";
        private static final String STRING_KEY = "string";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public QuerySessionStringIntRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            String string2 = bundle.getString(STRING_KEY);
            if (string2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string2, "bundle.getString(STRING_KEY)!!");
            return new QuerySessionStringIntRequest(string, string2, bundle.getInt(INT_KEY));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull QuerySessionStringIntRequest querySessionStringIntRequest) {
            Intrinsics.checkParameterIsNotNull(querySessionStringIntRequest, "querySessionStringIntRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", querySessionStringIntRequest.getIdentifier());
            bundle.putString(STRING_KEY, querySessionStringIntRequest.getStringValue());
            bundle.putInt(INT_KEY, querySessionStringIntRequest.getIntValue());
            return bundle;
        }
    }

    public QuerySessionStringIntRequest(@NotNull String identifier, @NotNull String stringValue, int i) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(stringValue, "stringValue");
        this.identifier = identifier;
        this.stringValue = stringValue;
        this.intValue = i;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ QuerySessionStringIntRequest copy$default(QuerySessionStringIntRequest querySessionStringIntRequest, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = querySessionStringIntRequest.identifier;
        }
        if ((i2 & 2) != 0) {
            str2 = querySessionStringIntRequest.stringValue;
        }
        if ((i2 & 4) != 0) {
            i = querySessionStringIntRequest.intValue;
        }
        return querySessionStringIntRequest.copy(str, str2, i);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    @NotNull
    public final String component2() {
        return this.stringValue;
    }

    public final int component3() {
        return this.intValue;
    }

    @NotNull
    public final QuerySessionStringIntRequest copy(@NotNull String identifier, @NotNull String stringValue, int i) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(stringValue, "stringValue");
        return new QuerySessionStringIntRequest(identifier, stringValue, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuerySessionStringIntRequest)) {
                return false;
            }
            QuerySessionStringIntRequest querySessionStringIntRequest = (QuerySessionStringIntRequest) obj;
            return Intrinsics.areEqual(this.identifier, querySessionStringIntRequest.identifier) && Intrinsics.areEqual(this.stringValue, querySessionStringIntRequest.stringValue) && this.intValue == querySessionStringIntRequest.intValue;
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
    public BundleTransformer<QuerySessionStringIntRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    public final int getIntValue() {
        return this.intValue;
    }

    @NotNull
    public final String getStringValue() {
        return this.stringValue;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.stringValue;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode + i) * 31) + this.intValue;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuerySessionStringIntRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", stringValue=");
        outline107.append(this.stringValue);
        outline107.append(", intValue=");
        return GeneratedOutlineSupport1.outline86(outline107, this.intValue, ")");
    }
}
