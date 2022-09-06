package com.bugsnag.android;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.annotation.NonNull;
import com.amazon.device.messaging.ADMConstants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ErrorReader {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bugsnag.android.ErrorReader$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$util$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$android$util$JsonToken[JsonToken.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$util$JsonToken[JsonToken.NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$util$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$util$JsonToken[JsonToken.BOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$util$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    ErrorReader() {
    }

    private static <T> T coerceSerializableFromJSON(JsonReader jsonReader) throws IOException {
        int i = AnonymousClass1.$SwitchMap$android$util$JsonToken[jsonReader.peek().ordinal()];
        if (i != 1) {
            if (i == 2) {
                return (T) deserializeNumber(jsonReader);
            }
            if (i == 3) {
                return (T) jsonObjectToMap(jsonReader);
            }
            if (i == 4) {
                return (T) Boolean.valueOf(jsonReader.nextBoolean());
            }
            if (i == 5) {
                return (T) jsonArrayToList(jsonReader);
            }
            return null;
        }
        return (T) jsonReader.nextString();
    }

    private static <T> T deserializeNumber(JsonReader jsonReader) throws IOException {
        try {
            try {
                return (T) Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException unused) {
                return (T) Double.valueOf(jsonReader.nextDouble());
            }
        } catch (NumberFormatException unused2) {
            return (T) Long.valueOf(jsonReader.nextLong());
        }
    }

    private static <T> List<T> jsonArrayToList(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            Object coerceSerializableFromJSON = coerceSerializableFromJSON(jsonReader);
            if (coerceSerializableFromJSON != null) {
                arrayList.add(coerceSerializableFromJSON);
            }
        }
        jsonReader.endArray();
        return arrayList;
    }

    private static Map<String, Object> jsonObjectToMap(JsonReader jsonReader) throws IOException {
        HashMap hashMap = new HashMap();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            Object coerceSerializableFromJSON = coerceSerializableFromJSON(jsonReader);
            if (coerceSerializableFromJSON != null) {
                hashMap.put(nextName, coerceSerializableFromJSON);
            }
        }
        jsonReader.endObject();
        return hashMap;
    }

    private static Breadcrumb readBreadcrumb(JsonReader jsonReader) throws IOException {
        HashMap hashMap = new HashMap();
        jsonReader.beginObject();
        String str = null;
        Date date = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            switch (nextName.hashCode()) {
                case -450957489:
                    if (nextName.equals("metaData")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3373707:
                    if (nextName.equals("name")) {
                        c = 0;
                        break;
                    }
                    break;
                case 3575610:
                    if (nextName.equals("type")) {
                        c = 2;
                        break;
                    }
                    break;
                case 55126294:
                    if (nextName.equals("timestamp")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                try {
                    date = DateUtils.fromIso8601(jsonReader.nextString());
                } catch (Exception e) {
                    throw new IOException("Failed to parse breadcrumb timestamp: ", e);
                }
            } else if (c == 2) {
                str2 = jsonReader.nextString().toUpperCase(Locale.US);
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    hashMap.put(jsonReader.nextName(), jsonReader.nextString());
                }
                jsonReader.endObject();
            }
        }
        jsonReader.endObject();
        if (str == null || date == null || str2 == null) {
            return null;
        }
        return new Breadcrumb(str, BreadcrumbType.valueOf(str2), date, hashMap);
    }

    private static Breadcrumbs readBreadcrumbs(Configuration configuration, JsonReader jsonReader) throws IOException {
        Breadcrumbs breadcrumbs = new Breadcrumbs(configuration);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            Breadcrumb readBreadcrumb = readBreadcrumb(jsonReader);
            if (readBreadcrumb != null) {
                breadcrumbs.add(readBreadcrumb);
            }
        }
        jsonReader.endArray();
        return breadcrumbs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r18v1, types: [boolean] */
    public static Error readError(@NonNull Configuration configuration, @NonNull File file) throws IOException {
        JsonReader jsonReader;
        Severity severity;
        List list;
        ArrayList<String> arrayList;
        boolean z;
        MetaData metaData;
        Session session;
        ThreadState threadState;
        Exceptions exceptions;
        User user;
        String str;
        String str2;
        Map<String, Object> map;
        Map<String, Object> map2;
        Breadcrumbs breadcrumbs;
        char c;
        try {
            Severity severity2 = Severity.ERROR;
            List emptyList = Collections.emptyList();
            jsonReader = new JsonReader(new BufferedReader(new FileReader(file)));
            try {
                jsonReader.beginObject();
                severity = severity2;
                list = emptyList;
                arrayList = null;
                z = false;
                metaData = null;
                session = null;
                threadState = null;
                exceptions = null;
                user = null;
                str = null;
                str2 = null;
                map = null;
                map2 = null;
                breadcrumbs = null;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            jsonReader = null;
        }
        while (true) {
            ?? hasNext = jsonReader.hasNext();
            if (hasNext != 0) {
                try {
                    String nextName = jsonReader.nextName();
                    switch (nextName.hashCode()) {
                        case -1890362749:
                            if (nextName.equals("unhandled")) {
                                c = '\f';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1337936983:
                            if (nextName.equals("threads")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1335157162:
                            if (nextName.equals("device")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1314244092:
                            if (nextName.equals("exceptions")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -450957489:
                            if (nextName.equals("metaData")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case -68904783:
                            if (nextName.equals("groupingHash")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case -51457840:
                            if (nextName.equals("breadcrumbs")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 96801:
                            if (nextName.equals(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT)) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 3599307:
                            if (nextName.equals("user")) {
                                c = '\r';
                                break;
                            }
                            c = 65535;
                            break;
                        case 358603558:
                            if (nextName.equals("projectPackages")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 398106529:
                            if (nextName.equals("severityReason")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case 951530927:
                            if (nextName.equals("context")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1478300413:
                            if (nextName.equals("severity")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1984987798:
                            if (nextName.equals("session")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            map = jsonObjectToMap(jsonReader);
                            break;
                        case 1:
                            breadcrumbs = readBreadcrumbs(configuration, jsonReader);
                            break;
                        case 2:
                            str = jsonReader.nextString();
                            break;
                        case 3:
                            map2 = jsonObjectToMap(jsonReader);
                            break;
                        case 4:
                            list = jsonArrayToList(jsonReader);
                            break;
                        case 5:
                            exceptions = readExceptions(configuration, jsonReader);
                            break;
                        case 6:
                            str2 = jsonReader.nextString();
                            break;
                        case 7:
                            metaData = new MetaData(jsonObjectToMap(jsonReader));
                            break;
                        case '\b':
                            session = readSession(jsonReader);
                            break;
                        case '\t':
                            severity = Severity.fromString(jsonReader.nextString());
                            break;
                        case '\n':
                            arrayList = readSeverityReason(jsonReader);
                            break;
                        case 11:
                            threadState = readThreadState(jsonReader);
                            break;
                        case '\f':
                            z = jsonReader.nextBoolean();
                            break;
                        case '\r':
                            user = readUser(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } else {
                jsonReader.endObject();
                try {
                    if (arrayList != null && exceptions != null) {
                        MetaData metaData2 = metaData;
                        Error error = new Error(configuration, exceptions.getException(), new HandledState(arrayList.get(0), severity, z, arrayList.size() > 1 ? arrayList.get(1) : null), severity, session, threadState);
                        error.getExceptions().setExceptionType(exceptions.getExceptionType());
                        error.setProjectPackages((String[]) list.toArray(new String[0]));
                        error.setUser(user);
                        error.setContext(str);
                        error.setGroupingHash(str2);
                        error.setAppData(map);
                        error.setMetaData(metaData2);
                        error.setDeviceData(map2);
                        error.setBreadcrumbs(breadcrumbs);
                        try {
                            jsonReader.close();
                        } catch (Exception unused) {
                        }
                        return error;
                    }
                    throw new IOException("File did not contain a valid error");
                } catch (Throwable th4) {
                    th = th4;
                    jsonReader = hasNext;
                }
            }
            th = th3;
            if (jsonReader != null) {
                try {
                    jsonReader.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static BugsnagException readException(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String str = null;
        String str2 = "android";
        List arrayList = new ArrayList();
        String str3 = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            switch (nextName.hashCode()) {
                case 3575610:
                    if (nextName.equals("type")) {
                        c = 3;
                        break;
                    }
                    break;
                case 954925063:
                    if (nextName.equals("message")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1610083408:
                    if (nextName.equals("errorClass")) {
                        c = 0;
                        break;
                    }
                    break;
                case 2055832509:
                    if (nextName.equals("stacktrace")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c == 1) {
                str3 = jsonReader.nextString();
            } else if (c == 2) {
                arrayList = readStackFrames(jsonReader);
            } else if (c != 3) {
                jsonReader.skipValue();
            } else {
                str2 = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        BugsnagException bugsnagException = new BugsnagException(str, str3, arrayList);
        bugsnagException.setType(str2);
        return bugsnagException;
    }

    private static Exceptions readExceptions(Configuration configuration, JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        BugsnagException readException = readException(jsonReader);
        BugsnagException bugsnagException = readException;
        while (jsonReader.hasNext()) {
            BugsnagException readException2 = readException(jsonReader);
            bugsnagException.initCause(readException2);
            bugsnagException = readException2;
        }
        jsonReader.endArray();
        Exceptions exceptions = new Exceptions(configuration, readException);
        exceptions.setExceptionType(readException.getType());
        return exceptions;
    }

    private static Session readSession(JsonReader jsonReader) throws IOException {
        boolean z;
        boolean z2;
        jsonReader.beginObject();
        String str = null;
        Date date = null;
        User user = null;
        int i = 0;
        int i2 = 0;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            int hashCode = nextName.hashCode();
            if (hashCode == -2128794476) {
                if (nextName.equals("startedAt")) {
                    z = true;
                }
                z = true;
            } else if (hashCode == -1291329255) {
                if (nextName.equals(DefaultDeliveryClient.EVENTS_DIRECTORY)) {
                    z = true;
                }
                z = true;
            } else if (hashCode != 3355) {
                if (hashCode == 3599307 && nextName.equals("user")) {
                    z = true;
                }
                z = true;
            } else {
                if (nextName.equals("id")) {
                    z = false;
                }
                z = true;
            }
            if (!z) {
                str = jsonReader.nextString();
            } else if (z) {
                try {
                    date = DateUtils.fromIso8601(jsonReader.nextString());
                } catch (Exception e) {
                    throw new IOException("Unable to parse session startedAt: ", e);
                }
            } else if (z) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String nextName2 = jsonReader.nextName();
                    int hashCode2 = nextName2.hashCode();
                    if (hashCode2 != -1890362749) {
                        if (hashCode2 == 692803388 && nextName2.equals("handled")) {
                            z2 = true;
                        }
                        z2 = true;
                    } else {
                        if (nextName2.equals("unhandled")) {
                            z2 = false;
                        }
                        z2 = true;
                    }
                    if (!z2) {
                        i = jsonReader.nextInt();
                    } else if (!z2) {
                        jsonReader.skipValue();
                    } else {
                        i2 = jsonReader.nextInt();
                    }
                }
                jsonReader.endObject();
            } else if (!z) {
                jsonReader.skipValue();
            } else {
                user = readUser(jsonReader);
            }
        }
        jsonReader.endObject();
        if (str != null && date != null) {
            return new Session(str, date, user, i, i2);
        }
        throw new IOException("Session data missing required fields");
    }

    private static ArrayList<String> readSeverityReason(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 3575610) {
                if (hashCode == 405645655 && nextName.equals("attributes")) {
                    c = 1;
                }
            } else if (nextName.equals("type")) {
                c = 0;
            }
            if (c == 0) {
                str = jsonReader.nextString();
            } else if (c != 1) {
                jsonReader.skipValue();
            } else {
                jsonReader.beginObject();
                jsonReader.nextName();
                str2 = jsonReader.nextString();
                jsonReader.endObject();
            }
        }
        jsonReader.endObject();
        ArrayList<String> arrayList = new ArrayList<>();
        if (str != null) {
            arrayList.add(str);
            if (str2 != null) {
                arrayList.add(str2);
            }
            return arrayList;
        }
        throw new IOException("Severity Reason type is required");
    }

    private static Map<String, Object> readStackFrame(JsonReader jsonReader) throws IOException {
        HashMap hashMap = new HashMap();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            try {
                String nextName = jsonReader.nextName();
                int i = AnonymousClass1.$SwitchMap$android$util$JsonToken[jsonReader.peek().ordinal()];
                if (i == 1) {
                    hashMap.put(nextName, jsonReader.nextString());
                } else if (i != 2) {
                    jsonReader.skipValue();
                } else {
                    hashMap.put(nextName, deserializeNumber(jsonReader));
                }
            } catch (IllegalStateException e) {
                Logger.warn("Failed to read stackframe", e);
            }
        }
        jsonReader.endObject();
        return hashMap;
    }

    private static List<Map<String, Object>> readStackFrames(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(readStackFrame(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    private static CachedThread readThread(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        long j = 0;
        boolean z = false;
        String str = null;
        String str2 = null;
        List<Map<String, Object>> list = null;
        while (true) {
            long j2 = j;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -767067472:
                        if (nextName.equals("errorReportingThread")) {
                            c = 4;
                            break;
                        }
                        break;
                    case 3355:
                        if (nextName.equals("id")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 3373707:
                        if (nextName.equals("name")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 3575610:
                        if (nextName.equals("type")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 2055832509:
                        if (nextName.equals("stacktrace")) {
                            c = 3;
                            break;
                        }
                        break;
                }
                if (c != 0) {
                    if (c == 1) {
                        str = jsonReader.nextString();
                    } else if (c == 2) {
                        str2 = jsonReader.nextString();
                    } else if (c == 3) {
                        list = readStackFrames(jsonReader);
                    } else if (c != 4) {
                        jsonReader.skipValue();
                    } else {
                        z = jsonReader.nextBoolean();
                    }
                }
            }
            jsonReader.endObject();
            if (str2 != null && list != null) {
                return new CachedThread(j2, str, str2, z, list);
            }
            return null;
            j = jsonReader.nextLong();
        }
    }

    private static ThreadState readThreadState(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            CachedThread readThread = readThread(jsonReader);
            if (readThread != null) {
                arrayList.add(readThread);
            }
        }
        jsonReader.endArray();
        return new ThreadState((CachedThread[]) arrayList.toArray(new CachedThread[0]));
    }

    private static User readUser(JsonReader jsonReader) throws IOException {
        User user = new User();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            char c = 65535;
            int hashCode = nextName.hashCode();
            if (hashCode != 3355) {
                if (hashCode != 3373707) {
                    if (hashCode == 96619420 && nextName.equals("email")) {
                        c = 2;
                    }
                } else if (nextName.equals("name")) {
                    c = 0;
                }
            } else if (nextName.equals("id")) {
                c = 1;
            }
            if (c == 0) {
                user.setName(jsonReader.nextString());
            } else if (c == 1) {
                user.setId(jsonReader.nextString());
            } else if (c != 2) {
                jsonReader.skipValue();
            } else {
                user.setEmail(jsonReader.nextString());
            }
        }
        jsonReader.endObject();
        return user;
    }
}
