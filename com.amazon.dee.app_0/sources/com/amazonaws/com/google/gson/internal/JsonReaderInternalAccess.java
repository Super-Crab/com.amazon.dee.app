package com.amazonaws.com.google.gson.internal;

import com.amazonaws.com.google.gson.stream.JsonReader;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class JsonReaderInternalAccess {
    public static JsonReaderInternalAccess INSTANCE;

    public abstract void promoteNameToValue(JsonReader jsonReader) throws IOException;
}
