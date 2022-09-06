package org.junit.internal;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
/* loaded from: classes5.dex */
public class TextListener extends RunListener {
    private final PrintStream writer;

    public TextListener(JUnitSystem jUnitSystem) {
        this(jUnitSystem.out());
    }

    private PrintStream getWriter() {
        return this.writer;
    }

    protected String elapsedTimeAsString(long j) {
        return NumberFormat.getInstance().format(j / 1000.0d);
    }

    protected void printFailure(Failure failure, String str) {
        PrintStream writer = getWriter();
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ") ");
        outline113.append(failure.getTestHeader());
        writer.println(outline113.toString());
        getWriter().print(failure.getTrace());
    }

    protected void printFailures(Result result) {
        List<Failure> failures = result.getFailures();
        if (failures.size() == 0) {
            return;
        }
        int i = 1;
        if (failures.size() == 1) {
            PrintStream writer = getWriter();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("There was ");
            outline107.append(failures.size());
            outline107.append(" failure:");
            writer.println(outline107.toString());
        } else {
            PrintStream writer2 = getWriter();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("There were ");
            outline1072.append(failures.size());
            outline1072.append(" failures:");
            writer2.println(outline1072.toString());
        }
        for (Failure failure : failures) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("");
            outline1073.append(i);
            printFailure(failure, outline1073.toString());
            i++;
        }
    }

    protected void printFooter(Result result) {
        if (result.wasSuccessful()) {
            getWriter().println();
            getWriter().print("OK");
            PrintStream writer = getWriter();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" (");
            outline107.append(result.getRunCount());
            outline107.append(" test");
            outline107.append(result.getRunCount() == 1 ? "" : "s");
            outline107.append(")");
            writer.println(outline107.toString());
        } else {
            getWriter().println();
            getWriter().println("FAILURES!!!");
            PrintStream writer2 = getWriter();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Tests run: ");
            outline1072.append(result.getRunCount());
            outline1072.append(",  Failures: ");
            outline1072.append(result.getFailureCount());
            writer2.println(outline1072.toString());
        }
        getWriter().println();
    }

    protected void printHeader(long j) {
        getWriter().println();
        GeneratedOutlineSupport1.outline178(GeneratedOutlineSupport1.outline107("Time: "), elapsedTimeAsString(j), getWriter());
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFailure(Failure failure) {
        this.writer.append('E');
    }

    @Override // org.junit.runner.notification.RunListener
    public void testIgnored(Description description) {
        this.writer.append('I');
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunFinished(Result result) {
        printHeader(result.getRunTime());
        printFailures(result);
        printFooter(result);
    }

    @Override // org.junit.runner.notification.RunListener
    public void testStarted(Description description) {
        this.writer.append('.');
    }

    public TextListener(PrintStream printStream) {
        this.writer = printStream;
    }
}
