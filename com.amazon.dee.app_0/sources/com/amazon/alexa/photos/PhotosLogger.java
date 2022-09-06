package com.amazon.alexa.photos;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class PhotosLogger implements Logger {
    private static final String CUSTOMER_EVENT = "CustomerEvent";
    private static final int MAX_TAG_LENGTH = 23;
    private static final String PREFIX = "AMZP_";
    @NonNull
    private final LogObfuscator logObfuscator;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class LogObfuscator {
        private static final Pattern PATTERN_DIRECTED_ID = Pattern.compile("amzn1.account.\\w*");
        private static final String TAG_OBFUSCATED = "{Data removed by LogObfuscator}";
        private final boolean shouldObfuscate;

        LogObfuscator(boolean z) {
            this.shouldObfuscate = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @NonNull
        public String obfuscate(@NonNull String str) {
            if (str.isEmpty()) {
                return "";
            }
            if (!this.shouldObfuscate) {
                return str;
            }
            Matcher matcher = PATTERN_DIRECTED_ID.matcher(str);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, TAG_OBFUSCATED);
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }
    }

    public PhotosLogger() {
        this(new LogObfuscator(true));
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void d(@NonNull String str, @NonNull String str2) {
        getTag(str);
        getLogMessage(str2);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void e(@NonNull String str, @NonNull String str2) {
        Log.e(getTag(str), getLogMessage(str2));
    }

    @NonNull
    String getLogMessage(@NonNull String str) {
        return this.logObfuscator.obfuscate(str);
    }

    @NonNull
    String getTag(@NonNull String str) {
        String outline72 = GeneratedOutlineSupport1.outline72(PREFIX, str);
        return outline72.length() > 23 ? outline72.substring(0, 23) : outline72;
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void i(@NonNull String str, @NonNull String str2) {
        Log.i(getTag(str), getLogMessage(str2));
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void logCustomerEvent(@NonNull String str) {
        i(CUSTOMER_EVENT, str);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void v(@NonNull String str, @NonNull String str2) {
        getTag(str);
        getLogMessage(str2);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void w(@NonNull String str, @NonNull String str2) {
        Log.w(getTag(str), getLogMessage(str2));
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void wtf(@NonNull String str, @NonNull String str2) {
        Log.wtf(getTag(str), getLogMessage(str2));
    }

    PhotosLogger(@NonNull LogObfuscator logObfuscator) {
        this.logObfuscator = logObfuscator;
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void d(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        getTag(str);
        getLogMessage(str2);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void e(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.e(getTag(str), getLogMessage(str2), th);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void i(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.i(getTag(str), getLogMessage(str2), th);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void v(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        getTag(str);
        getLogMessage(str2);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void w(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.w(getTag(str), getLogMessage(str2), th);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void wtf(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.wtf(getTag(str), getLogMessage(str2), th);
    }
}
