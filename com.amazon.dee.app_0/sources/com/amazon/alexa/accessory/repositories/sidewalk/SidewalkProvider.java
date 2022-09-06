package com.amazon.alexa.accessory.repositories.sidewalk;

import com.amazon.alexa.accessory.io.SizedSource;
/* loaded from: classes6.dex */
public interface SidewalkProvider {
    void dispose();

    void initialize();

    void provideData(SizedSource sizedSource);
}
