package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.LogLevel;
import com.bugsnag.android.JsonStream;
import java.io.IOException;
/* loaded from: classes.dex */
public enum Severity implements JsonStream.Streamable {
    ERROR("error"),
    WARNING("warning"),
    INFO(LogLevel.INFO);
    
    private final String name;

    Severity(String str) {
        this.name = str;
    }

    static Severity fromChar(char c) {
        if (c != 'e') {
            if (c == 'i') {
                return INFO;
            }
            if (c == 'w') {
                return WARNING;
            }
            return null;
        }
        return ERROR;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Severity fromString(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == 3237038) {
            if (str.equals(LogLevel.INFO)) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 96784904) {
            if (hashCode == 1124446108 && str.equals("warning")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("error")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return WARNING;
            }
            if (c == 2) {
                return INFO;
            }
            return null;
        }
        return ERROR;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        jsonStream.value(this.name);
    }
}
