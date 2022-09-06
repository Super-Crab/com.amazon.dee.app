package com.amazon.org.codehaus.jackson.impl;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import java.io.IOException;
/* loaded from: classes13.dex */
public interface Indenter {
    boolean isInline();

    void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;
}
