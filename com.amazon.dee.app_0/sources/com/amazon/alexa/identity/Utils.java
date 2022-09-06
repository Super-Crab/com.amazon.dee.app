package com.amazon.alexa.identity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class Utils {
    private static final int BUFFER_SIZE = 2048;
    private static final int MAX_TAG_LENGTH = 23;
    public static final String MOBILYTICS_IDENTIFIER = "e71ec482-0a7b-4d7b-8448-56400f4e8bd9";
    public static final String OWNER_IDENTIFIER_KEY = "ownerIdentifier";
    private static final String SHA_256 = "SHA-256";
    public static final String V2_LOG_PREFIX = "IdentityV2 : ";
    private static final String TAG = tag(Utils.class);
    public static final Map<String, Object> CUSTOM_ENTRIES = Collections.singletonMap("ownerIdentifier", "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");

    private Utils() {
    }

    public static long calculateDurationInHours(@NonNull DateTime dateTime, @NonNull DateTime dateTime2) {
        return new Duration(dateTime, dateTime2).getStandardHours();
    }

    public static long calculateDurationInMinutes(@NonNull DateTime dateTime, @NonNull DateTime dateTime2) {
        return new Duration(dateTime, dateTime2).getStandardMinutes();
    }

    public static long calculateDurationInSeconds(@NonNull DateTime dateTime, @NonNull DateTime dateTime2) {
        return new Duration(dateTime, dateTime2).getStandardSeconds();
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static int compareDottedStrings(String str, String str2) {
        String[] split = str.split(ArcusConfig.PATH_SEPARATOR);
        String[] split2 = str2.split(ArcusConfig.PATH_SEPARATOR);
        int i = 0;
        while (true) {
            if (i < split.length || i < split2.length) {
                if (i < split.length && i < split2.length) {
                    if (Integer.parseInt(split[i]) < Integer.parseInt(split2[i])) {
                        return -1;
                    }
                    if (Integer.parseInt(split[i]) > Integer.parseInt(split2[i])) {
                        return 1;
                    }
                } else if (i < split.length) {
                    if (Integer.parseInt(split[i]) != 0) {
                        return 1;
                    }
                } else if (i < split2.length && Integer.parseInt(split2[i]) != 0) {
                    return -1;
                }
                i++;
            } else {
                return 0;
            }
        }
    }

    public static File copyContentStreamToStagingFile(Context context, Uri uri, String str) throws IOException {
        InputStream inputStream;
        File file;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            try {
                String str2 = context.getCacheDir() + "/staged/";
                File file2 = new File(str2);
                if (!file2.mkdirs()) {
                    Log.e(TAG, safeFormat("Failed to creating staging directory %s", str2));
                }
                file = new File(file2, str);
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            copyStream(inputStream, fileOutputStream);
            closeQuietly(inputStream);
            closeQuietly(fileOutputStream);
            return file;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            if (inputStream != null) {
                closeQuietly(inputStream);
            }
            if (fileOutputStream2 != null) {
                closeQuietly(fileOutputStream2);
            }
            throw th;
        }
    }

    public static long copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[2048];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                j += read;
            } else {
                return j;
            }
        }
    }

    public static boolean isSafeLoggable(String str, int i) {
        return Log.isLoggable(safeTag(str), i);
    }

    public static ArrayList<String> objectArrayToStringArray(ArrayList<Object> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            Iterator<Object> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Object next = it2.next();
                if (next instanceof String) {
                    arrayList2.add((String) next);
                }
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void publishCookieEvent(@NonNull String str, EventBus eventBus, String str2) {
        publishIdentityEventInternal(str, eventBus, str2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void publishDelegationUpdateEvent(@NonNull String str, @NonNull EventBus eventBus, @NonNull String str2, @NonNull String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str3);
            publishIdentityEvent(str, eventBus, str2, jSONObject.toString());
        } catch (JSONException unused) {
            throw new IllegalArgumentException("Invalid input supplied.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void publishIdentityEvent(@NonNull String str, EventBus eventBus, String str2, @Nullable UserIdentity userIdentity) {
        Message.Builder eventType = new Message.Builder().setEventType(str2);
        if (userIdentity != null) {
            eventType.setPayload(userIdentity.toString());
        }
        eventBus.publish(eventType.build());
        String.format("Identity event %s published by %s", str2, str);
    }

    private static void publishIdentityEventInternal(@NonNull String str, EventBus eventBus, String str2, String str3) {
        Message.Builder eventType = new Message.Builder().setEventType(str2);
        if (str3 != null) {
            eventType.setPayload(str3);
        }
        eventBus.publish(eventType.build());
        String.format("Identity event %s published by %s", str2, str);
    }

    public static String readFromReader(Reader reader) {
        int read;
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[2048];
        do {
            try {
                read = reader.read(cArr, 0, cArr.length);
                if (read > 0) {
                    sb.append(cArr, 0, read);
                    continue;
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        } while (read > 0);
        reader.close();
        return sb.toString();
    }

    public static MobilyticsOperationalEvent recordOperationalDiagnosticEvent(Mobilytics mobilytics, String str, String str2, String str3) {
        return mobilytics.createOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, str3, "e71ec482-0a7b-4d7b-8448-56400f4e8bd9");
    }

    public static String safeFormat(String str, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return str;
        }
        try {
            return String.format(str, objArr);
        } catch (IllegalFormatException unused) {
            return "Formatting error";
        }
    }

    public static String safeTag(String str) {
        return str.length() > 23 ? str.substring(0, 23) : str;
    }

    public static String sha256Hex(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x", Integer.valueOf(digest[i] & 255)));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException unused) {
            return "";
        }
    }

    public static void showToast(final Activity activity, final String str) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.identity.Utils.1
            @Override // java.lang.Runnable
            public void run() {
                Toast makeText = Toast.makeText(activity, str, 1);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        });
    }

    public static String tag(@NonNull String str) {
        return safeTag(str);
    }

    public static String tag(@NonNull Class cls) {
        return safeTag(cls.getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void publishIdentityEvent(@NonNull String str, EventBus eventBus, String str2, @NonNull String str3) {
        publishIdentityEventInternal(str, eventBus, str2, str3);
    }
}
