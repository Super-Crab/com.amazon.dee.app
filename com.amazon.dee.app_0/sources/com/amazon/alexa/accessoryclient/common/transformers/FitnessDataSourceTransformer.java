package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.io.Source;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.repositories.fitness.FitnessDataSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessDataSourceTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/FitnessDataSourceTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessDataSource;", "()V", "CONTINUATION_TOKEN_KEY", "", "DATA_FORMAT_KEY", "FITNESS_DATA_SOURCE_KEY", "SHA_256_KEY", "TAG", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", FitnessDataSourceTransformer.FITNESS_DATA_SOURCE_KEY, "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FitnessDataSourceTransformer implements BundleTransformer<FitnessDataSource> {
    private static final String CONTINUATION_TOKEN_KEY = "continuationToken";
    private static final String DATA_FORMAT_KEY = "dataFormatNumber";
    private static final String FITNESS_DATA_SOURCE_KEY = "fitnessDataSource";
    public static final FitnessDataSourceTransformer INSTANCE = new FitnessDataSourceTransformer();
    private static final String SHA_256_KEY = "sha256";
    private static final String TAG = "FitnessDataSourceTransformer:";

    private FitnessDataSourceTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public FitnessDataSource mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        SourceTransformer sourceTransformer = SourceTransformer.INSTANCE;
        Bundle bundle2 = bundle.getBundle(FITNESS_DATA_SOURCE_KEY);
        if (bundle2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(FITNESS_DATA_SOURCE_KEY)!!");
        return new FitnessDataSource(sourceTransformer.mo568fromBundle(bundle2), Fitness.FitnessDataFormat.forNumber(bundle.getInt(DATA_FORMAT_KEY)), bundle.getByteArray(SHA_256_KEY), bundle.getByteArray(CONTINUATION_TOKEN_KEY));
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull FitnessDataSource fitnessDataSource) {
        Intrinsics.checkParameterIsNotNull(fitnessDataSource, "fitnessDataSource");
        Bundle bundle = new Bundle();
        bundle.putBundle(FITNESS_DATA_SOURCE_KEY, SourceTransformer.INSTANCE.toBundle((Source) fitnessDataSource));
        Fitness.FitnessDataFormat format = fitnessDataSource.getFormat();
        Intrinsics.checkExpressionValueIsNotNull(format, "fitnessDataSource.format");
        bundle.putInt(DATA_FORMAT_KEY, format.getNumber());
        bundle.putByteArray(SHA_256_KEY, fitnessDataSource.getSha256Checksum());
        bundle.putByteArray(CONTINUATION_TOKEN_KEY, fitnessDataSource.getContinuationToken());
        return bundle;
    }
}
