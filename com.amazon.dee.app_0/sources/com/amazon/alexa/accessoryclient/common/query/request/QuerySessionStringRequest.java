package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QuerySessionStringRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionStringRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "stringValue", "(Ljava/lang/String;Ljava/lang/String;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getIdentifier", "()Ljava/lang/String;", "getStringValue", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class QuerySessionStringRequest implements Query.Request<QuerySessionStringRequest> {
    @NotNull
    private final BundleTransformer<QuerySessionStringRequest> bundleTransformer;
    @NotNull
    private final String identifier;
    @NotNull
    private final String stringValue;

    /* compiled from: QuerySessionStringRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionStringRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/QuerySessionStringRequest;", "()V", "IDENTIFIER_KEY", "", "STRING_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "querySessionStringRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<QuerySessionStringRequest> {
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();
        private static final String STRING_KEY = "string";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public QuerySessionStringRequest mo568fromBundle(@NotNull Bundle bundle) {
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
            return new QuerySessionStringRequest(string, string2);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull QuerySessionStringRequest querySessionStringRequest) {
            Intrinsics.checkParameterIsNotNull(querySessionStringRequest, "querySessionStringRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", querySessionStringRequest.getIdentifier());
            bundle.putString(STRING_KEY, querySessionStringRequest.getStringValue());
            return bundle;
        }
    }

    public QuerySessionStringRequest(@NotNull String identifier, @NotNull String stringValue) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(stringValue, "stringValue");
        this.identifier = identifier;
        this.stringValue = stringValue;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ QuerySessionStringRequest copy$default(QuerySessionStringRequest querySessionStringRequest, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = querySessionStringRequest.identifier;
        }
        if ((i & 2) != 0) {
            str2 = querySessionStringRequest.stringValue;
        }
        return querySessionStringRequest.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    @NotNull
    public final String component2() {
        return this.stringValue;
    }

    @NotNull
    public final QuerySessionStringRequest copy(@NotNull String identifier, @NotNull String stringValue) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(stringValue, "stringValue");
        return new QuerySessionStringRequest(identifier, stringValue);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof QuerySessionStringRequest)) {
                return false;
            }
            QuerySessionStringRequest querySessionStringRequest = (QuerySessionStringRequest) obj;
            return Intrinsics.areEqual(this.identifier, querySessionStringRequest.identifier) && Intrinsics.areEqual(this.stringValue, querySessionStringRequest.stringValue);
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
    public BundleTransformer<QuerySessionStringRequest> getBundleTransformer() {
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
        String str2 = this.stringValue;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QuerySessionStringRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", stringValue=");
        return GeneratedOutlineSupport1.outline91(outline107, this.stringValue, ")");
    }
}
