package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.DiagnosticsOuterClass;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.DiagnosticsTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DiagnosticsRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u0019\u001aB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00000\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DiagnosticsRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", DiagnosticsRequest.DIAGNOSTICS_TYPE_KEY, "Lcom/amazon/alexa/accessory/protocol/DiagnosticsOuterClass$DiagnosticsType;", "identifier", "", "(Lcom/amazon/alexa/accessory/protocol/DiagnosticsOuterClass$DiagnosticsType;Ljava/lang/String;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getDiagnosticsType", "()Lcom/amazon/alexa/accessory/protocol/DiagnosticsOuterClass$DiagnosticsType;", "getIdentifier", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DiagnosticsRequest implements Query.Request<DiagnosticsRequest> {
    @NotNull
    private final BundleTransformer<DiagnosticsRequest> bundleTransformer;
    @NotNull
    private final DiagnosticsOuterClass.DiagnosticsType diagnosticsType;
    @NotNull
    private final String identifier;
    public static final Companion Companion = new Companion(null);
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String DIAGNOSTICS_TYPE_KEY = DIAGNOSTICS_TYPE_KEY;
    private static final String DIAGNOSTICS_TYPE_KEY = DIAGNOSTICS_TYPE_KEY;

    /* compiled from: DiagnosticsRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DiagnosticsRequest$Companion;", "", "()V", "DIAGNOSTICS_TYPE_KEY", "", "IDENTIFIER_KEY", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: DiagnosticsRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/DiagnosticsRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/DiagnosticsRequest;", "()V", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "diagnosticsRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<DiagnosticsRequest> {
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public DiagnosticsRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            DiagnosticsTransformer diagnosticsTransformer = DiagnosticsTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(DiagnosticsRequest.DIAGNOSTICS_TYPE_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(DIAGNOSTICS_TYPE_KEY)!!");
            DiagnosticsOuterClass.DiagnosticsType mo568fromBundle = diagnosticsTransformer.mo568fromBundle(bundle2);
            String string = bundle.getString(DiagnosticsRequest.IDENTIFIER_KEY);
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            return new DiagnosticsRequest(mo568fromBundle, string);
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull DiagnosticsRequest diagnosticsRequest) {
            Intrinsics.checkParameterIsNotNull(diagnosticsRequest, "diagnosticsRequest");
            Bundle bundle = new Bundle();
            bundle.putBundle(DiagnosticsRequest.DIAGNOSTICS_TYPE_KEY, DiagnosticsTransformer.INSTANCE.toBundle(diagnosticsRequest.getDiagnosticsType()));
            bundle.putString(DiagnosticsRequest.IDENTIFIER_KEY, diagnosticsRequest.getIdentifier());
            return bundle;
        }
    }

    public DiagnosticsRequest(@NotNull DiagnosticsOuterClass.DiagnosticsType diagnosticsType, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(diagnosticsType, "diagnosticsType");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        this.diagnosticsType = diagnosticsType;
        this.identifier = identifier;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ DiagnosticsRequest copy$default(DiagnosticsRequest diagnosticsRequest, DiagnosticsOuterClass.DiagnosticsType diagnosticsType, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            diagnosticsType = diagnosticsRequest.diagnosticsType;
        }
        if ((i & 2) != 0) {
            str = diagnosticsRequest.identifier;
        }
        return diagnosticsRequest.copy(diagnosticsType, str);
    }

    @NotNull
    public final DiagnosticsOuterClass.DiagnosticsType component1() {
        return this.diagnosticsType;
    }

    @NotNull
    public final String component2() {
        return this.identifier;
    }

    @NotNull
    public final DiagnosticsRequest copy(@NotNull DiagnosticsOuterClass.DiagnosticsType diagnosticsType, @NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(diagnosticsType, "diagnosticsType");
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new DiagnosticsRequest(diagnosticsType, identifier);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof DiagnosticsRequest)) {
                return false;
            }
            DiagnosticsRequest diagnosticsRequest = (DiagnosticsRequest) obj;
            return Intrinsics.areEqual(this.diagnosticsType, diagnosticsRequest.diagnosticsType) && Intrinsics.areEqual(this.identifier, diagnosticsRequest.identifier);
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
    public BundleTransformer<DiagnosticsRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final DiagnosticsOuterClass.DiagnosticsType getDiagnosticsType() {
        return this.diagnosticsType;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    public int hashCode() {
        DiagnosticsOuterClass.DiagnosticsType diagnosticsType = this.diagnosticsType;
        int i = 0;
        int hashCode = (diagnosticsType != null ? diagnosticsType.hashCode() : 0) * 31;
        String str = this.identifier;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DiagnosticsRequest(diagnosticsType=");
        outline107.append(this.diagnosticsType);
        outline107.append(", identifier=");
        return GeneratedOutlineSupport1.outline91(outline107, this.identifier, ")");
    }
}
