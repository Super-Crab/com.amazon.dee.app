package io.reactivex.rxjava3.internal.fuseable;

import io.reactivex.rxjava3.annotations.NonNull;
import org.reactivestreams.Publisher;
/* loaded from: classes3.dex */
public interface HasUpstreamPublisher<T> {
    @NonNull
    Publisher<T> source();
}
