package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/FitnessSessionTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession;", "()V", "SEQUENCE_ID_KEY", "", "UUID_KEY", "WORKOUT_STATE_ORDINAL_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "fitnessSession", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class FitnessSessionTransformer implements BundleTransformer<FitnessSession> {
    public static final FitnessSessionTransformer INSTANCE = new FitnessSessionTransformer();
    private static final String SEQUENCE_ID_KEY = "sequenceId";
    private static final String UUID_KEY = "uuid";
    private static final String WORKOUT_STATE_ORDINAL_KEY = "workoutState";

    private FitnessSessionTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public FitnessSession mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        return new FitnessSession(UUID.fromString(bundle.getString("uuid")), bundle.getInt("sequenceId"), FitnessSession.WorkoutState.values()[bundle.getInt(WORKOUT_STATE_ORDINAL_KEY)]);
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull FitnessSession fitnessSession) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        Bundle bundle = new Bundle();
        bundle.putString("uuid", fitnessSession.getUuid().toString());
        bundle.putInt("sequenceId", fitnessSession.getSequenceId());
        bundle.putInt(WORKOUT_STATE_ORDINAL_KEY, fitnessSession.getWorkoutState().ordinal());
        return bundle;
    }
}
