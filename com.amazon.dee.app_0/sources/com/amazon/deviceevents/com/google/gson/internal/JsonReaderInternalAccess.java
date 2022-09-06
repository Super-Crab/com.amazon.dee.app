package com.amazon.deviceevents.com.google.gson.internal;

import com.amazon.deviceevents.com.google.gson.stream.JsonReader;
import java.io.IOException;
/* loaded from: classes12.dex */
public abstract class JsonReaderInternalAccess {
    public static JsonReaderInternalAccess INSTANCE;

    public abstract void promoteNameToValue(JsonReader jsonReader) throws IOException;
}
