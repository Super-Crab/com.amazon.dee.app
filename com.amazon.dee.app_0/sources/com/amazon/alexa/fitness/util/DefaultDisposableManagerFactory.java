package com.amazon.alexa.fitness.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DisposableManagerFactory.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/util/DefaultDisposableManagerFactory;", "Lcom/amazon/alexa/fitness/util/DisposableManagerFactory;", "()V", "createDisposableManager", "Lcom/amazon/alexa/fitness/util/DisposableManager;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DefaultDisposableManagerFactory implements DisposableManagerFactory {
    @Override // com.amazon.alexa.fitness.util.DisposableManagerFactory
    @NotNull
    public DisposableManager createDisposableManager() {
        return new CompositeDisposableManager();
    }
}
