package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Cloudpairing;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.CloudPairingKeysTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.SeedTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReplaceCloudPairingKeysRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/ReplaceCloudPairingKeysRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "seed", "Lcom/amazon/alexa/accessory/protocol/Cloudpairing$Seed;", "cloudPairingKeys", "Lcom/amazon/alexa/accessory/protocol/Cloudpairing$CloudPairingKeys;", "(Ljava/lang/String;Lcom/amazon/alexa/accessory/protocol/Cloudpairing$Seed;Lcom/amazon/alexa/accessory/protocol/Cloudpairing$CloudPairingKeys;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getCloudPairingKeys", "()Lcom/amazon/alexa/accessory/protocol/Cloudpairing$CloudPairingKeys;", "getIdentifier", "()Ljava/lang/String;", "getSeed", "()Lcom/amazon/alexa/accessory/protocol/Cloudpairing$Seed;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReplaceCloudPairingKeysRequest implements Query.Request<ReplaceCloudPairingKeysRequest> {
    @NotNull
    private final BundleTransformer<ReplaceCloudPairingKeysRequest> bundleTransformer;
    @NotNull
    private final Cloudpairing.CloudPairingKeys cloudPairingKeys;
    @NotNull
    private final String identifier;
    @NotNull
    private final Cloudpairing.Seed seed;

    /* compiled from: ReplaceCloudPairingKeysRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/ReplaceCloudPairingKeysRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/ReplaceCloudPairingKeysRequest;", "()V", "CLOUD_PAIRING_KEYS_KEY", "", "IDENTIFIER_KEY", "SEED_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "replaceCloudPairingKeysRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<ReplaceCloudPairingKeysRequest> {
        private static final String CLOUD_PAIRING_KEYS_KEY = "cloudpairingkeys";
        private static final String IDENTIFIER_KEY = "identifier";
        public static final Transformer INSTANCE = new Transformer();
        private static final String SEED_KEY = "seed";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public ReplaceCloudPairingKeysRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            SeedTransformer seedTransformer = SeedTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(SEED_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(SEED_KEY)!!");
            Cloudpairing.Seed mo568fromBundle = seedTransformer.mo568fromBundle(bundle2);
            CloudPairingKeysTransformer cloudPairingKeysTransformer = CloudPairingKeysTransformer.INSTANCE;
            Bundle bundle3 = bundle.getBundle(CLOUD_PAIRING_KEYS_KEY);
            if (bundle3 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle3, "bundle.getBundle(CLOUD_PAIRING_KEYS_KEY)!!");
            return new ReplaceCloudPairingKeysRequest(string, mo568fromBundle, cloudPairingKeysTransformer.mo568fromBundle(bundle3));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull ReplaceCloudPairingKeysRequest replaceCloudPairingKeysRequest) {
            Intrinsics.checkParameterIsNotNull(replaceCloudPairingKeysRequest, "replaceCloudPairingKeysRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", replaceCloudPairingKeysRequest.getIdentifier());
            bundle.putBundle(SEED_KEY, SeedTransformer.INSTANCE.toBundle(replaceCloudPairingKeysRequest.getSeed()));
            bundle.putBundle(CLOUD_PAIRING_KEYS_KEY, CloudPairingKeysTransformer.INSTANCE.toBundle(replaceCloudPairingKeysRequest.getCloudPairingKeys()));
            return bundle;
        }
    }

    public ReplaceCloudPairingKeysRequest(@NotNull String identifier, @NotNull Cloudpairing.Seed seed, @NotNull Cloudpairing.CloudPairingKeys cloudPairingKeys) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(seed, "seed");
        Intrinsics.checkParameterIsNotNull(cloudPairingKeys, "cloudPairingKeys");
        this.identifier = identifier;
        this.seed = seed;
        this.cloudPairingKeys = cloudPairingKeys;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ ReplaceCloudPairingKeysRequest copy$default(ReplaceCloudPairingKeysRequest replaceCloudPairingKeysRequest, String str, Cloudpairing.Seed seed, Cloudpairing.CloudPairingKeys cloudPairingKeys, int i, Object obj) {
        if ((i & 1) != 0) {
            str = replaceCloudPairingKeysRequest.identifier;
        }
        if ((i & 2) != 0) {
            seed = replaceCloudPairingKeysRequest.seed;
        }
        if ((i & 4) != 0) {
            cloudPairingKeys = replaceCloudPairingKeysRequest.cloudPairingKeys;
        }
        return replaceCloudPairingKeysRequest.copy(str, seed, cloudPairingKeys);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    @NotNull
    public final Cloudpairing.Seed component2() {
        return this.seed;
    }

    @NotNull
    public final Cloudpairing.CloudPairingKeys component3() {
        return this.cloudPairingKeys;
    }

    @NotNull
    public final ReplaceCloudPairingKeysRequest copy(@NotNull String identifier, @NotNull Cloudpairing.Seed seed, @NotNull Cloudpairing.CloudPairingKeys cloudPairingKeys) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(seed, "seed");
        Intrinsics.checkParameterIsNotNull(cloudPairingKeys, "cloudPairingKeys");
        return new ReplaceCloudPairingKeysRequest(identifier, seed, cloudPairingKeys);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ReplaceCloudPairingKeysRequest)) {
                return false;
            }
            ReplaceCloudPairingKeysRequest replaceCloudPairingKeysRequest = (ReplaceCloudPairingKeysRequest) obj;
            return Intrinsics.areEqual(this.identifier, replaceCloudPairingKeysRequest.identifier) && Intrinsics.areEqual(this.seed, replaceCloudPairingKeysRequest.seed) && Intrinsics.areEqual(this.cloudPairingKeys, replaceCloudPairingKeysRequest.cloudPairingKeys);
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
    public BundleTransformer<ReplaceCloudPairingKeysRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    @NotNull
    public final Cloudpairing.CloudPairingKeys getCloudPairingKeys() {
        return this.cloudPairingKeys;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    @NotNull
    public final Cloudpairing.Seed getSeed() {
        return this.seed;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Cloudpairing.Seed seed = this.seed;
        int hashCode2 = (hashCode + (seed != null ? seed.hashCode() : 0)) * 31;
        Cloudpairing.CloudPairingKeys cloudPairingKeys = this.cloudPairingKeys;
        if (cloudPairingKeys != null) {
            i = cloudPairingKeys.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReplaceCloudPairingKeysRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", seed=");
        outline107.append(this.seed);
        outline107.append(", cloudPairingKeys=");
        outline107.append(this.cloudPairingKeys);
        outline107.append(")");
        return outline107.toString();
    }
}
