package com.amazon.alexa.voice.tta.simba;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SimbaModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaModule;", "", "()V", "provideSimbaClient", "Lcom/amazon/alexa/voice/tta/simba/SimbaClient;", "gson", "Lcom/google/gson/Gson;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes11.dex */
public final class SimbaModule {
    @Provides
    @Singleton
    @NotNull
    public final SimbaClient provideSimbaClient(@NotNull Gson gson) {
        Intrinsics.checkParameterIsNotNull(gson, "gson");
        return new SimbaClient(gson);
    }
}
