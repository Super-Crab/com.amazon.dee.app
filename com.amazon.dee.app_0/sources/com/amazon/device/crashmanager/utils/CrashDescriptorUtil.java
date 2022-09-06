package com.amazon.device.crashmanager.utils;

import android.text.TextUtils;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.metrics.MetricsConstants;
import com.amazon.device.crashmanager.processor.AnrArtifactProcessor;
import com.amazon.device.crashmanager.processor.JavaCrashArtifactProcessor;
import com.amazon.device.crashmanager.processor.MetricsHeaderProcessor;
import com.amazon.device.crashmanager.processor.NativeCrashArtifactProcessor;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
/* loaded from: classes12.dex */
public class CrashDescriptorUtil {
    static final int ARTIFACT_BUFFER_SIZE = 25600;
    static final int MAX_NUMBER_OF_LINES_TO_SEARCH_FOR_PROCESS_NAME = 10;
    static final int MAX_NUMBER_OF_LINES_TO_SEARCH_FOR_STACK_TRACE = 100;
    private static final DPLogger log = new DPLogger("CrashDescriptorUtil");

    public static String calculateCrashDescriptor(StringBuilder sb) throws Exception {
        if (sb == null || sb.length() == 0) {
            return null;
        }
        return new BigInteger(MessageDigest.getInstance("SHA1").digest(sb.toString().getBytes())).abs().toString(16);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String calculateCrashDescriptorFromTrace(BufferedReader bufferedReader, Writer writer, DetUtil.HeaderProcessor headerProcessor, String str, String str2, char[] cArr, MetricsHeaderProcessor metricsHeaderProcessor, MetricEvent metricEvent) throws Exception {
        char c;
        StringBuilder extractStackTraceForJavaCrash;
        BufferedReader fetchBodyFromReader = fetchBodyFromReader(bufferedReader, cArr);
        switch (str2.hashCode()) {
            case -740666208:
                if (str2.equals(CrashManagerActions.PIN_FAILURE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 64965:
                if (str2.equals(CrashManagerActions.ANR)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 578928031:
                if (str2.equals(CrashManagerActions.STRICT_MODE_VIOLATION)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1017630506:
                if (str2.equals(CrashManagerActions.JAVA_CRASH)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1668572511:
                if (str2.equals(CrashManagerActions.NATIVE_CRASH)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            extractStackTraceForJavaCrash = extractStackTraceForJavaCrash(fetchBodyFromReader, str);
        } else if (c == 1) {
            extractStackTraceForJavaCrash = extractStackTraceForANR(fetchBodyFromReader, metricsHeaderProcessor);
        } else if (c == 2) {
            extractStackTraceForJavaCrash = extractStackTraceForSMV(fetchBodyFromReader);
        } else if (c == 3) {
            extractStackTraceForJavaCrash = extractStackTraceForPinFailures(fetchBodyFromReader, metricEvent);
        } else if (c != 4) {
            return null;
        } else {
            extractStackTraceForJavaCrash = extractStackTraceForNativeCrash(fetchBodyFromReader, writer, headerProcessor);
        }
        return calculateCrashDescriptor(extractStackTraceForJavaCrash);
    }

    public static String extractProcessNameFromNativeCrash(BufferedReader bufferedReader) throws IOException {
        String readLine;
        for (int i = 0; i < 10 && (readLine = bufferedReader.readLine()) != null; i++) {
            Matcher matcher = NativeCrashArtifactProcessor.NATIVE_APP_NAME_REGEX.matcher(readLine);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        log.warn("extractNativeCrashInfoFromCrashBody", "Could not extract 'Process' from native crash.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForANR(BufferedReader bufferedReader, MetricsHeaderProcessor metricsHeaderProcessor) throws Exception {
        String readLine;
        do {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
        } while (!readLine.startsWith("Cmd line: "));
        bufferedReader.readLine();
        String readLine2 = bufferedReader.readLine();
        if (readLine2 == null || !readLine2.startsWith("DALVIK THREADS")) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (metricsHeaderProcessor.getProcessName() != null) {
            sb.append(metricsHeaderProcessor.getProcessName());
        }
        int i = 0;
        while (true) {
            String readLine3 = bufferedReader.readLine();
            if (readLine3 == null) {
                break;
            }
            i++;
            if ((readLine3.isEmpty() || readLine3.charAt(0) == '\n' || readLine3.charAt(0) == '\r') && (i >= 10 || !sb.toString().equals(metricsHeaderProcessor.getProcessName()))) {
                break;
            } else if (AnrArtifactProcessor.REGEX_STACK_TRACE_PATTERN.matcher(readLine3).find()) {
                sb.append(readLine3);
            }
        }
        if (sb.length() > 0) {
            return sb;
        }
        log.warn("extractAnrInfoFromCrashBody", "No stack trace.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForJavaCrash(BufferedReader bufferedReader, String str) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        String readLine = bufferedReader.readLine();
        JavaCrashArtifactProcessor.REGEX_GROUP_ID = 0;
        if (readLine != null) {
            if (readLine.startsWith("***")) {
                JavaCrashArtifactProcessor.REGEX_GROUP_ID = 4;
                for (int i = 0; !bufferedReader.readLine().startsWith("backtrace:") && i < 50; i++) {
                }
                readLine = bufferedReader.readLine();
            }
            do {
                Matcher matcher = JavaCrashArtifactProcessor.REGEX_STACK_TRACE_PATTERN.matcher(readLine);
                if (!matcher.find()) {
                    break;
                }
                sb.append(matcher.group(JavaCrashArtifactProcessor.REGEX_GROUP_ID));
                readLine = bufferedReader.readLine();
            } while (readLine != null);
        }
        if (sb.length() <= 0) {
            log.warn("extractJavaCrashInfoFromCrashBody", "No stack trace.", new Object[0]);
            return null;
        }
        return sb;
    }

    private static StringBuilder extractStackTraceForNativeCrash(BufferedReader bufferedReader, Writer writer, DetUtil.HeaderProcessor headerProcessor) throws Exception {
        String readLine;
        StringBuilder sb = new StringBuilder();
        String extractProcessNameFromNativeCrash = extractProcessNameFromNativeCrash(bufferedReader);
        if (TextUtils.isEmpty(extractProcessNameFromNativeCrash)) {
            return null;
        }
        sb.append(extractProcessNameFromNativeCrash);
        headerProcessor.process("Process", extractProcessNameFromNativeCrash, writer);
        for (int i = 0; i < 100 && (readLine = bufferedReader.readLine()) != null; i++) {
            Matcher matcher = NativeCrashArtifactProcessor.STACKFRAME_IDENTIFIER.matcher(readLine);
            if (matcher.find()) {
                sb.append(matcher.group(1));
                while (true) {
                    String readLine2 = bufferedReader.readLine();
                    if (readLine2 == null) {
                        break;
                    }
                    Matcher matcher2 = NativeCrashArtifactProcessor.STACKFRAME_IDENTIFIER.matcher(readLine2);
                    if (!matcher2.find()) {
                        break;
                    }
                    sb.append(matcher2.group(1));
                }
                return sb;
            }
        }
        log.warn("extractNativeCrashInfoFromCrashBody", "Could not find stack trace in native crash.", new Object[0]);
        return null;
    }

    private static StringBuilder extractStackTraceForPinFailures(BufferedReader bufferedReader, MetricEvent metricEvent) throws Exception {
        String readLine = bufferedReader.readLine();
        if (TextUtils.isEmpty(readLine)) {
            log.warn("extractStackTraceForPinFailures", "Domain and/or Certificate information not present in the PinFailure", new Object[0]);
            metricEvent.incrementCounter(MetricsConstants.COUNTER_EMPTY_PINCRASH, 1.0d);
            return null;
        }
        return new StringBuilder(readLine);
    }

    private static StringBuilder extractStackTraceForSMV(BufferedReader bufferedReader) throws Exception {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || readLine.trim().equals("")) {
                break;
            }
            sb.append(readLine);
        }
        if (sb.length() <= 0) {
            log.warn("extractSmvInfoFromCrashBody", "No stack trace.", new Object[0]);
            return null;
        }
        return sb;
    }

    public static BufferedReader fetchBodyFromReader(BufferedReader bufferedReader, char[] cArr) throws IOException {
        bufferedReader.mark(ARTIFACT_BUFFER_SIZE);
        try {
            bufferedReader.read(cArr, 0, ARTIFACT_BUFFER_SIZE);
            bufferedReader.reset();
            return new BufferedReader(new CharArrayReader(cArr), 1);
        } catch (Throwable th) {
            bufferedReader.reset();
            throw th;
        }
    }
}
