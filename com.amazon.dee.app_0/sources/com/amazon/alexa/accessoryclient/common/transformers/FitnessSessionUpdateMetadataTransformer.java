package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSessionUpdate;
import com.amazon.alexa.accessoryclient.common.api.FitnessSessionUpdateMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionUpdateMetadataTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/FitnessSessionUpdateMetadataTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/api/FitnessSessionUpdateMetadata;", "()V", "FITNESS_SESSION_KEY", "", "METADATA_UUID", "ORIGIN_ORDINAL_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "fitnessSessionUpdateMetadata", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FitnessSessionUpdateMetadataTransformer implements BundleTransformer<FitnessSessionUpdateMetadata> {
    private static final String FITNESS_SESSION_KEY = "fitnessSession";
    public static final FitnessSessionUpdateMetadataTransformer INSTANCE = new FitnessSessionUpdateMetadataTransformer();
    private static final String METADATA_UUID = "metadataUuid";
    private static final String ORIGIN_ORDINAL_KEY = "origin";

    private FitnessSessionUpdateMetadataTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public FitnessSessionUpdateMetadata mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        FitnessSessionUpdate.Origin origin = FitnessSessionUpdate.Origin.values()[bundle.getInt("origin")];
        FitnessSessionTransformer fitnessSessionTransformer = FitnessSessionTransformer.INSTANCE;
        Bundle bundle2 = bundle.getBundle(FITNESS_SESSION_KEY);
        if (bundle2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(FITNESS_SESSION_KEY)!!");
        FitnessSession mo568fromBundle = fitnessSessionTransformer.mo568fromBundle(bundle2);
        String string = bundle.getString(METADATA_UUID);
        if (string == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(METADATA_UUID)!!");
        return new FitnessSessionUpdateMetadata(origin, mo568fromBundle, string);
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull FitnessSessionUpdateMetadata fitnessSessionUpdateMetadata) {
        Intrinsics.checkParameterIsNotNull(fitnessSessionUpdateMetadata, "fitnessSessionUpdateMetadata");
        Bundle bundle = new Bundle();
        bundle.putInt("origin", fitnessSessionUpdateMetadata.getOrigin().ordinal());
        bundle.putBundle(FITNESS_SESSION_KEY, FitnessSessionTransformer.INSTANCE.toBundle(fitnessSessionUpdateMetadata.getFitnessSession()));
        bundle.putString(METADATA_UUID, fitnessSessionUpdateMetadata.getMetadataUuid());
        return bundle;
    }
}
