package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
/* loaded from: classes.dex */
public class Notifier implements JsonStream.Streamable {
    private static final String NOTIFIER_NAME = "Android Bugsnag Notifier";
    private static final String NOTIFIER_URL = "https://bugsnag.com";
    private static final String NOTIFIER_VERSION = "4.22.3";
    private static final Notifier instance = new Notifier();
    @NonNull
    private String name = NOTIFIER_NAME;
    @NonNull
    private String version = NOTIFIER_VERSION;
    @NonNull
    private String url = NOTIFIER_URL;

    @NonNull
    public static Notifier getInstance() {
        return instance;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public String getURL() {
        return this.url;
    }

    @NonNull
    public String getVersion() {
        return this.version;
    }

    public void setName(@NonNull String str) {
        this.name = str;
    }

    public void setURL(@NonNull String str) {
        this.url = str;
    }

    public void setVersion(@NonNull String str) {
        this.version = str;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject();
        jsonStream.mo6745name("name").value(this.name);
        jsonStream.mo6745name("version").value(this.version);
        jsonStream.mo6745name("url").value(this.url);
        jsonStream.endObject();
    }
}
