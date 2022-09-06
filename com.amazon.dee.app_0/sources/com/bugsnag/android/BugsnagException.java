package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class BugsnagException extends Throwable implements JsonStream.Streamable {
    private static final long serialVersionUID = 5068182621179433346L;
    private final List<Map<String, Object>> customStackframes;
    private String message;
    private String name;
    private String[] projectPackages;
    private JsonStream.Streamable streamable;
    private String type;

    public BugsnagException(@NonNull String str, @NonNull String str2, @NonNull StackTraceElement[] stackTraceElementArr) {
        super(str2);
        this.type = "android";
        setStackTrace(stackTraceElementArr);
        this.name = str;
        this.customStackframes = null;
    }

    @Override // java.lang.Throwable
    @NonNull
    public String getMessage() {
        String str = this.message;
        return str != null ? str : super.getMessage();
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String getType() {
        return this.type;
    }

    public void setMessage(@NonNull String str) {
        this.message = str;
    }

    public void setName(@NonNull String str) {
        this.name = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProjectPackages(String[] strArr) {
        this.projectPackages = strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setType(@NonNull String str) {
        this.type = str;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        Stacktrace stacktrace;
        JsonStream.Streamable streamable = this.streamable;
        if (streamable != null) {
            streamable.toStream(jsonStream);
            return;
        }
        List<Map<String, Object>> list = this.customStackframes;
        if (list != null) {
            stacktrace = new Stacktrace(list);
        } else {
            stacktrace = new Stacktrace(getStackTrace(), this.projectPackages);
        }
        jsonStream.beginObject();
        jsonStream.mo6745name("errorClass").value(getName());
        jsonStream.mo6745name("message").value(getLocalizedMessage());
        jsonStream.mo6745name("type").value(this.type);
        jsonStream.mo6745name("stacktrace").value((JsonStream.Streamable) stacktrace);
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BugsnagException(@NonNull Throwable th) {
        super(th.getMessage());
        this.type = "android";
        if (th instanceof JsonStream.Streamable) {
            this.streamable = (JsonStream.Streamable) th;
            this.name = "";
        } else {
            this.name = th.getClass().getName();
        }
        setStackTrace(th.getStackTrace());
        initCause(th.getCause());
        this.customStackframes = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BugsnagException(@NonNull String str, @NonNull String str2, @NonNull List<Map<String, Object>> list) {
        super(str2);
        this.type = "android";
        setStackTrace(new StackTraceElement[0]);
        this.name = str;
        this.customStackframes = list;
    }
}
