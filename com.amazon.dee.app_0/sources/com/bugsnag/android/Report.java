package com.bugsnag.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.bugsnag.android.JsonStream;
import java.io.File;
import java.io.IOException;
/* loaded from: classes.dex */
public class Report implements JsonStream.Streamable {
    @NonNull
    private String apiKey;
    private transient boolean cachingDisabled;
    @Nullable
    private final Error error;
    @Nullable
    private final File errorFile;
    @NonNull
    private final Notifier notifier;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Report(@NonNull String str, @NonNull Error error) {
        this(str, null, error);
    }

    @NonNull
    public String getApiKey() {
        return this.apiKey;
    }

    @NonNull
    public Error getError() {
        return this.error;
    }

    @NonNull
    public Notifier getNotifier() {
        return this.notifier;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCachingDisabled() {
        return this.cachingDisabled;
    }

    public void setApiKey(@NonNull String str) {
        this.apiKey = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCachingDisabled(boolean z) {
        this.cachingDisabled = z;
    }

    @Deprecated
    public void setNotifierName(@NonNull String str) {
        this.notifier.setName(str);
    }

    @Deprecated
    public void setNotifierURL(@NonNull String str) {
        this.notifier.setURL(str);
    }

    @Deprecated
    public void setNotifierVersion(@NonNull String str) {
        this.notifier.setVersion(str);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject();
        jsonStream.mo6745name("apiKey").value(this.apiKey);
        jsonStream.mo6745name("payloadVersion").value("4.0");
        jsonStream.mo6745name("notifier").value((JsonStream.Streamable) this.notifier);
        jsonStream.mo6745name(DefaultDeliveryClient.EVENTS_DIRECTORY).beginArray();
        Error error = this.error;
        if (error != null) {
            jsonStream.value((JsonStream.Streamable) error);
        } else {
            File file = this.errorFile;
            if (file != null) {
                jsonStream.value(file);
            } else {
                Logger.warn("Expected error or errorFile, found empty payload instead");
            }
        }
        jsonStream.endArray();
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Report(@NonNull String str, @Nullable File file) {
        this(str, file, null);
    }

    private Report(@NonNull String str, @Nullable File file, @Nullable Error error) {
        this.error = error;
        this.errorFile = file;
        this.notifier = Notifier.getInstance();
        this.apiKey = str;
    }
}
