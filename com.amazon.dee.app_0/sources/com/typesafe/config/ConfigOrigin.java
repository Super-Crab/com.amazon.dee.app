package com.typesafe.config;

import java.net.URL;
import java.util.List;
/* loaded from: classes3.dex */
public interface ConfigOrigin {
    List<String> comments();

    String description();

    String filename();

    int lineNumber();

    String resource();

    URL url();

    /* renamed from: withComments */
    ConfigOrigin mo10262withComments(List<String> list);

    /* renamed from: withLineNumber */
    ConfigOrigin mo10263withLineNumber(int i);
}
