package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.io.Source;
import java.io.IOException;
/* loaded from: classes.dex */
public interface HttpResponse {

    /* loaded from: classes.dex */
    public interface Factory<T> {
        /* renamed from: create */
        T mo309create(Source source) throws IOException;
    }
}
