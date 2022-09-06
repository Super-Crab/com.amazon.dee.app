package org.bouncycastle.util.test;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.bouncycastle.util.Strings;
/* loaded from: classes5.dex */
public class SimpleTestResult implements TestResult {
    private static final String SEPARATOR = Strings.lineSeparator();
    private Throwable exception;
    private String message;
    private boolean success;

    public SimpleTestResult(boolean z, String str) {
        this.success = z;
        this.message = str;
    }

    public SimpleTestResult(boolean z, String str, Throwable th) {
        this.success = z;
        this.message = str;
        this.exception = th;
    }

    public static TestResult failed(Test test, String str) {
        return new SimpleTestResult(false, test.getName() + RealTimeTextConstants.COLON_SPACE + str);
    }

    public static TestResult failed(Test test, String str, Object obj, Object obj2) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(SEPARATOR);
        outline107.append("Expected: ");
        outline107.append(obj);
        outline107.append(SEPARATOR);
        outline107.append("Found   : ");
        outline107.append(obj2);
        return failed(test, outline107.toString());
    }

    public static TestResult failed(Test test, String str, Throwable th) {
        return new SimpleTestResult(false, test.getName() + RealTimeTextConstants.COLON_SPACE + str, th);
    }

    public static String failedMessage(String str, String str2, String str3, String str4) {
        StringBuffer stringBuffer = new StringBuffer(str);
        stringBuffer.append(" failing ");
        stringBuffer.append(str2);
        stringBuffer.append(SEPARATOR);
        stringBuffer.append("    expected: ");
        stringBuffer.append(str3);
        stringBuffer.append(SEPARATOR);
        stringBuffer.append("    got     : ");
        stringBuffer.append(str4);
        return stringBuffer.toString();
    }

    public static TestResult successful(Test test, String str) {
        return new SimpleTestResult(true, test.getName() + RealTimeTextConstants.COLON_SPACE + str);
    }

    @Override // org.bouncycastle.util.test.TestResult
    public Throwable getException() {
        return this.exception;
    }

    @Override // org.bouncycastle.util.test.TestResult
    public boolean isSuccessful() {
        return this.success;
    }

    @Override // org.bouncycastle.util.test.TestResult
    public String toString() {
        return this.message;
    }
}
