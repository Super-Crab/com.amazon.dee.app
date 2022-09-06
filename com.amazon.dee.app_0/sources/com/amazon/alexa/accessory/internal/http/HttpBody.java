package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.io.Sink;
import java.io.IOException;
/* loaded from: classes.dex */
public interface HttpBody {
    String getContentType();

    void writeTo(Sink sink) throws IOException;
}
