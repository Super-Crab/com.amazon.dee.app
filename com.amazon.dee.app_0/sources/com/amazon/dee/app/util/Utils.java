package com.amazon.dee.app.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Duration;
/* loaded from: classes12.dex */
public final class Utils {
    private static final int BUFFER_SIZE = 2048;
    public static final int MAX_TAG_LENGTH = 23;
    private static final String SHA_256 = "SHA-256";
    private static final String TAG = "Utils";

    private Utils() {
    }

    public static long calculateDurationHours(@NonNull DateTime dateTime, @NonNull DateTime dateTime2) {
        return new Duration(dateTime, dateTime2).getStandardHours();
    }

    public static long calculateDurationMinutes(@NonNull DateTime dateTime, @NonNull DateTime dateTime2) {
        return new Duration(dateTime, dateTime2).getStandardMinutes();
    }

    public static long calculateDurationSeconds(@NonNull DateTime dateTime, @NonNull DateTime dateTime2) {
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
                    Log.e(TAG, String.format("Failed to creating staging directory %s", str2));
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
            closeQuietly(inputStream);
            closeQuietly(fileOutputStream2);
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

    public static Map<String, Object> getCustomEntriesWithOwnerIdentifier(String str) {
        return GeneratedOutlineSupport1.outline133("ownerIdentifier", str);
    }

    public static boolean isSafeLoggable(String str, int i) {
        if (str.length() >= 23) {
            return Log.isLoggable(str.substring(0, 23), i);
        }
        return Log.isLoggable(str, i);
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

    public static String safeFormat(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }

    public static String safeTag(String str) {
        return str.length() > 23 ? str.substring(0, 23) : str;
    }

    public static String sha256Hex(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
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
        activity.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.util.Utils.1
            @Override // java.lang.Runnable
            public void run() {
                Toast makeText = Toast.makeText(activity, str, 1);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
        });
    }
}
