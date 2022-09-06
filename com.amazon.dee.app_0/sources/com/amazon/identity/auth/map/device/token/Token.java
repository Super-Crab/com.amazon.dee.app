package com.amazon.identity.auth.map.device.token;

import android.text.format.Time;
import java.util.Map;
/* loaded from: classes12.dex */
public interface Token {
    public static final String KEY_TOKEN = "token";

    Map<String, String> getData();

    String getDirectedId();

    Time getLocalTimestamp();

    String getType();

    String toString();
}
