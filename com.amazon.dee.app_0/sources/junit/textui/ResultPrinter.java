package junit.textui;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Enumeration;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.runner.BaseTestRunner;
/* loaded from: classes3.dex */
public class ResultPrinter implements TestListener {
    int fColumn = 0;
    PrintStream fWriter;

    public ResultPrinter(PrintStream printStream) {
        this.fWriter = printStream;
    }

    @Override // junit.framework.TestListener
    public void addError(Test test, Throwable th) {
        getWriter().print(ExifInterface.LONGITUDE_EAST);
    }

    @Override // junit.framework.TestListener
    public void addFailure(Test test, AssertionFailedError assertionFailedError) {
        getWriter().print("F");
    }

    protected String elapsedTimeAsString(long j) {
        return NumberFormat.getInstance().format(j / 1000.0d);
    }

    @Override // junit.framework.TestListener
    public void endTest(Test test) {
    }

    public PrintStream getWriter() {
        return this.fWriter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void print(TestResult testResult, long j) {
        printHeader(j);
        printErrors(testResult);
        printFailures(testResult);
        printFooter(testResult);
    }

    public void printDefect(TestFailure testFailure, int i) {
        printDefectHeader(testFailure, i);
        printDefectTrace(testFailure);
    }

    protected void printDefectHeader(TestFailure testFailure, int i) {
        PrintStream writer = getWriter();
        writer.print(i + ") " + testFailure.failedTest());
    }

    protected void printDefectTrace(TestFailure testFailure) {
        getWriter().print(BaseTestRunner.getFilteredTrace(testFailure.trace()));
    }

    protected void printDefects(Enumeration<TestFailure> enumeration, int i, String str) {
        if (i == 0) {
            return;
        }
        if (i == 1) {
            getWriter().println("There was " + i + " " + str + ":");
        } else {
            getWriter().println("There were " + i + " " + str + "s:");
        }
        int i2 = 1;
        while (enumeration.hasMoreElements()) {
            printDefect(enumeration.nextElement(), i2);
            i2++;
        }
    }

    protected void printErrors(TestResult testResult) {
        printDefects(testResult.errors(), testResult.errorCount(), "error");
    }

    protected void printFailures(TestResult testResult) {
        printDefects(testResult.failures(), testResult.failureCount(), "failure");
    }

    protected void printFooter(TestResult testResult) {
        if (testResult.wasSuccessful()) {
            getWriter().println();
            getWriter().print("OK");
            PrintStream writer = getWriter();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" (");
            outline107.append(testResult.runCount());
            outline107.append(" test");
            outline107.append(testResult.runCount() == 1 ? "" : "s");
            outline107.append(")");
            writer.println(outline107.toString());
        } else {
            getWriter().println();
            getWriter().println("FAILURES!!!");
            PrintStream writer2 = getWriter();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Tests run: ");
            outline1072.append(testResult.runCount());
            outline1072.append(",  Failures: ");
            outline1072.append(testResult.failureCount());
            outline1072.append(",  Errors: ");
            outline1072.append(testResult.errorCount());
            writer2.println(outline1072.toString());
        }
        getWriter().println();
    }

    protected void printHeader(long j) {
        getWriter().println();
        GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("Time: "), elapsedTimeAsString(j), getWriter());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void printWaitPrompt() {
        getWriter().println();
        getWriter().println("<RETURN> to continue");
    }

    @Override // junit.framework.TestListener
    public void startTest(Test test) {
        getWriter().print(".");
        int i = this.fColumn;
        this.fColumn = i + 1;
        if (i >= 40) {
            getWriter().println();
            this.fColumn = 0;
        }
    }
}
