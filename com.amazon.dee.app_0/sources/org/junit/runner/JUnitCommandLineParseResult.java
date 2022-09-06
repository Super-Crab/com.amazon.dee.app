package org.junit.runner;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.internal.Classes;
import org.junit.runner.FilterFactory;
import org.junit.runners.model.InitializationError;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class JUnitCommandLineParseResult {
    private final List<String> filterSpecs = new ArrayList();
    private final List<Class<?>> classes = new ArrayList();
    private final List<Throwable> parserErrors = new ArrayList();

    /* loaded from: classes5.dex */
    public static class CommandLineParserError extends Exception {
        private static final long serialVersionUID = 1;

        public CommandLineParserError(String str) {
            super(str);
        }
    }

    JUnitCommandLineParseResult() {
    }

    private Request applyFilterSpecs(Request request) {
        try {
            for (String str : this.filterSpecs) {
                request = request.filterWith(FilterFactories.createFilterFromFilterSpec(request, str));
            }
            return request;
        } catch (FilterFactory.FilterNotCreatedException e) {
            return errorReport(e);
        }
    }

    private String[] copyArray(String[] strArr, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i != i2) {
            arrayList.add(strArr[i]);
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private Request errorReport(Throwable th) {
        return Request.errorReport(JUnitCommandLineParseResult.class, th);
    }

    public static JUnitCommandLineParseResult parse(String[] strArr) {
        JUnitCommandLineParseResult jUnitCommandLineParseResult = new JUnitCommandLineParseResult();
        jUnitCommandLineParseResult.parseArgs(strArr);
        return jUnitCommandLineParseResult;
    }

    private void parseArgs(String[] strArr) {
        parseParameters(parseOptions(strArr));
    }

    public Request createRequest(Computer computer) {
        if (this.parserErrors.isEmpty()) {
            List<Class<?>> list = this.classes;
            return applyFilterSpecs(Request.classes(computer, (Class[]) list.toArray(new Class[list.size()])));
        }
        return errorReport(new InitializationError(this.parserErrors));
    }

    public List<Class<?>> getClasses() {
        return Collections.unmodifiableList(this.classes);
    }

    public List<String> getFilterSpecs() {
        return Collections.unmodifiableList(this.filterSpecs);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x007c, code lost:
        return new java.lang.String[0];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    java.lang.String[] parseOptions(java.lang.String... r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r8.length
            if (r1 == r2) goto L7a
            r2 = r8[r1]
            java.lang.String r3 = "--"
            boolean r4 = r2.equals(r3)
            if (r4 == 0) goto L17
            int r1 = r1 + 1
            int r0 = r8.length
            java.lang.String[] r8 = r7.copyArray(r8, r1, r0)
            return r8
        L17:
            boolean r3 = r2.startsWith(r3)
            if (r3 == 0) goto L74
            java.lang.String r3 = "--filter="
            boolean r3 = r2.startsWith(r3)
            java.lang.String r4 = "--filter"
            if (r3 != 0) goto L41
            boolean r3 = r2.equals(r4)
            if (r3 == 0) goto L2e
            goto L41
        L2e:
            java.util.List<java.lang.Throwable> r3 = r7.parserErrors
            org.junit.runner.JUnitCommandLineParseResult$CommandLineParserError r4 = new org.junit.runner.JUnitCommandLineParseResult$CommandLineParserError
            java.lang.String r5 = "JUnit knows nothing about the "
            java.lang.String r6 = " option"
            java.lang.String r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline75(r5, r2, r6)
            r4.<init>(r2)
            r3.add(r4)
            goto L71
        L41:
            boolean r3 = r2.equals(r4)
            if (r3 == 0) goto L60
            int r1 = r1 + 1
            int r3 = r8.length
            if (r1 >= r3) goto L4f
            r2 = r8[r1]
            goto L6c
        L4f:
            java.util.List<java.lang.Throwable> r8 = r7.parserErrors
            org.junit.runner.JUnitCommandLineParseResult$CommandLineParserError r1 = new org.junit.runner.JUnitCommandLineParseResult$CommandLineParserError
            java.lang.String r3 = " value not specified"
            java.lang.String r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r2, r3)
            r1.<init>(r2)
            r8.add(r1)
            goto L7a
        L60:
            r3 = 61
            int r3 = r2.indexOf(r3)
            int r3 = r3 + 1
            java.lang.String r2 = r2.substring(r3)
        L6c:
            java.util.List<java.lang.String> r3 = r7.filterSpecs
            r3.add(r2)
        L71:
            int r1 = r1 + 1
            goto L2
        L74:
            int r0 = r8.length
            java.lang.String[] r8 = r7.copyArray(r8, r1, r0)
            return r8
        L7a:
            java.lang.String[] r8 = new java.lang.String[r0]
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.junit.runner.JUnitCommandLineParseResult.parseOptions(java.lang.String[]):java.lang.String[]");
    }

    void parseParameters(String[] strArr) {
        for (String str : strArr) {
            try {
                this.classes.add(Classes.getClass(str));
            } catch (ClassNotFoundException e) {
                this.parserErrors.add(new IllegalArgumentException(GeneratedOutlineSupport1.outline75("Could not find class [", str, "]"), e));
            }
        }
    }
}
