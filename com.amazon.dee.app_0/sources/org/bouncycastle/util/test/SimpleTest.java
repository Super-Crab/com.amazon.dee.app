package org.bouncycastle.util.test;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.util.Arrays;
/* loaded from: classes5.dex */
public abstract class SimpleTest implements Test {
    public static void runTest(Test test) {
        runTest(test, System.out);
    }

    public static void runTest(Test test, PrintStream printStream) {
        TestResult perform = test.perform();
        if (perform.getException() != null) {
            perform.getException().printStackTrace(printStream);
        }
        printStream.println(perform);
    }

    public static void runTests(Test[] testArr) {
        runTests(testArr, System.out);
    }

    public static void runTests(Test[] testArr, PrintStream printStream) {
        Vector vector = new Vector();
        for (int i = 0; i != testArr.length; i++) {
            TestResult perform = testArr[i].perform();
            if (!perform.isSuccessful()) {
                vector.addElement(perform);
            }
            if (perform.getException() != null) {
                perform.getException().printStackTrace(printStream);
            }
            printStream.println(perform);
        }
        printStream.println("-----");
        if (vector.isEmpty()) {
            printStream.println("All tests successful.");
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Completed with ");
        outline107.append(vector.size());
        outline107.append(" FAILURES:");
        printStream.println(outline107.toString());
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            PrintStream printStream2 = System.out;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("=>  ");
            outline1072.append((TestResult) elements.nextElement());
            printStream2.println(outline1072.toString());
        }
    }

    private TestResult success() {
        return SimpleTestResult.successful(this, "Okay");
    }

    protected boolean areEqual(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return Arrays.areEqual(bArr, i, i2, bArr2, i3, i4);
    }

    protected boolean areEqual(byte[] bArr, byte[] bArr2) {
        return Arrays.areEqual(bArr, bArr2);
    }

    protected boolean areEqual(byte[][] bArr, byte[][] bArr2) {
        if (bArr == null && bArr2 == null) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (!areEqual(bArr[i], bArr2[i])) {
                return false;
            }
        }
        return true;
    }

    protected void fail(String str) {
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }

    protected void fail(String str, Object obj, Object obj2) {
        throw new TestFailedException(SimpleTestResult.failed(this, str, obj, obj2));
    }

    protected void fail(String str, Throwable th) {
        throw new TestFailedException(SimpleTestResult.failed(this, str, th));
    }

    @Override // org.bouncycastle.util.test.Test
    public abstract String getName();

    protected void isEquals(int i, int i2) {
        if (i == i2) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
    }

    protected void isEquals(long j, long j2) {
        if (j == j2) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
    }

    protected void isEquals(Object obj, Object obj2) {
        if (obj.equals(obj2)) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
    }

    protected void isEquals(String str, long j, long j2) {
        if (j == j2) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }

    protected void isEquals(String str, Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return;
        }
        if (obj == null) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
        if (obj2 == null) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
        if (!obj.equals(obj2)) {
            throw new TestFailedException(SimpleTestResult.failed(this, str));
        }
    }

    protected void isEquals(String str, boolean z, boolean z2) {
        if (z == z2) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }

    protected void isTrue(String str, boolean z) {
        if (z) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, str));
    }

    protected void isTrue(boolean z) {
        if (z) {
            return;
        }
        throw new TestFailedException(SimpleTestResult.failed(this, "no message"));
    }

    @Override // org.bouncycastle.util.test.Test
    public TestResult perform() {
        try {
            performTest();
            return success();
        } catch (TestFailedException e) {
            return e.getResult();
        } catch (Exception e2) {
            return SimpleTestResult.failed(this, "Exception: " + e2, e2);
        }
    }

    public abstract void performTest() throws Exception;
}
