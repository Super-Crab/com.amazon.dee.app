package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class CachedThread implements JsonStream.Streamable {
    private final long id;
    private final boolean isErrorReportingThread;
    private final String name;
    private Stacktrace stacktrace;
    private final String type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedThread(Configuration configuration, long j, String str, String str2, boolean z, StackTraceElement[] stackTraceElementArr) {
        this(j, str, str2, z, new Stacktrace(stackTraceElementArr, configuration.getProjectPackages()));
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginObject();
        jsonStream.mo6745name("id").value(this.id);
        jsonStream.mo6745name("name").value(this.name);
        jsonStream.mo6745name("type").value(this.type);
        jsonStream.mo6745name("stacktrace").value((JsonStream.Streamable) this.stacktrace);
        if (this.isErrorReportingThread) {
            jsonStream.mo6745name("errorReportingThread").value(true);
        }
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CachedThread(long j, String str, String str2, boolean z, List<Map<String, Object>> list) {
        this(j, str, str2, z, new Stacktrace(list));
    }

    private CachedThread(long j, String str, String str2, boolean z, Stacktrace stacktrace) {
        this.id = j;
        this.name = str;
        this.type = str2;
        this.isErrorReportingThread = z;
        this.stacktrace = stacktrace;
    }
}
