package com.amazon.alexa.voice.tta.search;

import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SearchModule.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/SearchModule;", "", "()V", "providesDefaultTtaResultHandler", "Lcom/amazon/alexa/voice/tta/search/DefaultTtaResultHandler;", "providesFrictionHelper", "Lcom/amazon/alexa/voice/tta/search/FrictionHelper;", "providesTtaResultHandler", "Lcom/amazon/alexa/voice/ui/tta/search/TtaResultHandler;", "ttaResultHandler", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes11.dex */
public final class SearchModule {
    @Provides
    @Singleton
    @NotNull
    public final DefaultTtaResultHandler providesDefaultTtaResultHandler() {
        return new DefaultTtaResultHandler();
    }

    @Provides
    @Singleton
    @NotNull
    public final FrictionHelper providesFrictionHelper() {
        return new FrictionHelper();
    }

    @Provides
    @Singleton
    @NotNull
    public final TtaResultHandler providesTtaResultHandler(@NotNull DefaultTtaResultHandler ttaResultHandler) {
        Intrinsics.checkParameterIsNotNull(ttaResultHandler, "ttaResultHandler");
        return ttaResultHandler;
    }
}
