package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Exceptions implements JsonStream.Streamable {
    private final BugsnagException exception;
    private String exceptionType;
    private String[] projectPackages;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Exceptions(Configuration configuration, BugsnagException bugsnagException) {
        this.exception = bugsnagException;
        this.exceptionType = bugsnagException.getType();
        this.projectPackages = configuration.getProjectPackages();
    }

    private void exceptionToStream(@NonNull JsonStream jsonStream, String str, String str2, StackTraceElement[] stackTraceElementArr) throws IOException {
        jsonStream.beginObject();
        jsonStream.mo6745name("errorClass").value(str);
        jsonStream.mo6745name("message").value(str2);
        jsonStream.mo6745name("type").value(this.exceptionType);
        jsonStream.mo6745name("stacktrace").value((JsonStream.Streamable) new Stacktrace(stackTraceElementArr, this.projectPackages));
        jsonStream.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BugsnagException getException() {
        return this.exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getExceptionType() {
        return this.exceptionType;
    }

    String[] getProjectPackages() {
        return this.projectPackages;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExceptionType(@NonNull String str) {
        this.exceptionType = str;
        this.exception.setType(this.exceptionType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setProjectPackages(String[] strArr) {
        this.projectPackages = strArr;
        this.exception.setProjectPackages(strArr);
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.beginArray();
        for (Throwable th = this.exception; th != null; th = th.getCause()) {
            if (th instanceof JsonStream.Streamable) {
                ((JsonStream.Streamable) th).toStream(jsonStream);
            } else {
                exceptionToStream(jsonStream, th.getClass().getName(), th.getLocalizedMessage(), th.getStackTrace());
            }
        }
        jsonStream.endArray();
    }
}
