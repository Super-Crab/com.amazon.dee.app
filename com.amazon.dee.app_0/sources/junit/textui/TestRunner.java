package junit.textui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;
import junit.runner.Version;
/* loaded from: classes3.dex */
public class TestRunner extends BaseTestRunner {
    public static final int EXCEPTION_EXIT = 2;
    public static final int FAILURE_EXIT = 1;
    public static final int SUCCESS_EXIT = 0;
    private ResultPrinter fPrinter;

    public TestRunner() {
        this(System.out);
    }

    public static void main(String[] strArr) {
        try {
            if (!new TestRunner().start(strArr).wasSuccessful()) {
                System.exit(1);
            }
            System.exit(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }
    }

    public static void run(Class<? extends TestCase> cls) {
        run(new TestSuite(cls));
    }

    public static void runAndWait(Test test) {
        new TestRunner().doRun(test, true);
    }

    protected TestResult createTestResult() {
        return new TestResult();
    }

    public TestResult doRun(Test test) {
        return doRun(test, false);
    }

    protected void pause(boolean z) {
        if (!z) {
            return;
        }
        this.fPrinter.printWaitPrompt();
        try {
            System.in.read();
        } catch (Exception unused) {
        }
    }

    @Override // junit.runner.BaseTestRunner
    protected void runFailed(String str) {
        System.err.println(str);
        System.exit(1);
    }

    protected TestResult runSingleMethod(String str, String str2, boolean z) throws Exception {
        return doRun(TestSuite.createTest(loadSuiteClass(str).asSubclass(TestCase.class), str2), z);
    }

    public void setPrinter(ResultPrinter resultPrinter) {
        this.fPrinter = resultPrinter;
    }

    public TestResult start(String[] strArr) throws Exception {
        int i = 0;
        boolean z = false;
        String str = "";
        String str2 = str;
        while (i < strArr.length) {
            if (strArr[i].equals("-wait")) {
                z = true;
            } else if (strArr[i].equals("-c")) {
                i++;
                str = extractClassName(strArr[i]);
            } else if (strArr[i].equals("-m")) {
                i++;
                String str3 = strArr[i];
                int lastIndexOf = str3.lastIndexOf(46);
                String substring = str3.substring(0, lastIndexOf);
                str2 = str3.substring(lastIndexOf + 1);
                str = substring;
            } else if (strArr[i].equals("-v")) {
                PrintStream printStream = System.err;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JUnit ");
                outline107.append(Version.id());
                outline107.append(" by Kent Beck and Erich Gamma");
                printStream.println(outline107.toString());
            } else {
                str = strArr[i];
            }
            i++;
        }
        if (!str.equals("")) {
            try {
                if (!str2.equals("")) {
                    return runSingleMethod(str, str2, z);
                }
                return doRun(getTest(str), z);
            } catch (Exception e) {
                throw new Exception(GeneratedOutlineSupport1.outline68("Could not create and run test suite: ", e));
            }
        }
        throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
    }

    @Override // junit.runner.BaseTestRunner
    public void testEnded(String str) {
    }

    @Override // junit.runner.BaseTestRunner
    public void testFailed(int i, Test test, Throwable th) {
    }

    @Override // junit.runner.BaseTestRunner
    public void testStarted(String str) {
    }

    public TestRunner(PrintStream printStream) {
        this(new ResultPrinter(printStream));
    }

    public static TestResult run(Test test) {
        return new TestRunner().doRun(test);
    }

    public TestResult doRun(Test test, boolean z) {
        TestResult createTestResult = createTestResult();
        createTestResult.addListener(this.fPrinter);
        long currentTimeMillis = System.currentTimeMillis();
        test.run(createTestResult);
        this.fPrinter.print(createTestResult, System.currentTimeMillis() - currentTimeMillis);
        pause(z);
        return createTestResult;
    }

    public TestRunner(ResultPrinter resultPrinter) {
        this.fPrinter = resultPrinter;
    }
}
