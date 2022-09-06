package com.amazon.alexa.accessory.repositories.sidewalk;

import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.io.Source;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes6.dex */
public interface SidewalkRepository {
    Single<Source> getInputStream();

    Single<Sink> getOutputStream();
}
