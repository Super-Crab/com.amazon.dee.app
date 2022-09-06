package com.amazon.alexa.fitness.service.hds;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
/* compiled from: HdsThreadHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsThreadHandler;", "", "postRetry", "", "retryFunc", "Lkotlin/Function0;", "", "postUpload", "uploadFunc", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface HdsThreadHandler {
    boolean postRetry(@NotNull Function0<Unit> function0);

    boolean postUpload(@NotNull Function0<Unit> function0);
}
