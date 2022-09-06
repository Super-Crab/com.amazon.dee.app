package com.amazon.device.utils.det;

import android.os.Build;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
/* loaded from: classes12.dex */
public class DetUtil {
    private static final String TAG = "DetUtil";
    private static final String TRACE_HASH_CODE_HEADER_KEY = "TraceHashCode";
    private int mDetUploadTimeOut;
    private static final Pattern HEADER_VALUE_REGEX = Pattern.compile("^(.+?): (.+)$");
    private static final SimpleDateFormat sDetDateFormat = new SimpleDateFormat("yyMMdd:HHmmss", Locale.US);

    /* loaded from: classes12.dex */
    public interface BodyWriter {
        void write(BufferedReader bufferedReader, Writer writer) throws Exception;
    }

    /* loaded from: classes12.dex */
    public static class DefaultHeaderProcessor implements HeaderProcessor {
        private final HeaderProcessor mDefaultHeaderProcessor;
        private final Map<String, HeaderProcessor> mHeaderProcessors;

        public DefaultHeaderProcessor(Map<String, HeaderProcessor> map, HeaderProcessor headerProcessor) {
            this.mHeaderProcessors = map;
            this.mDefaultHeaderProcessor = headerProcessor;
        }

        @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
        public void process(String str, String str2, Writer writer) throws Exception {
            HeaderProcessor headerProcessor = this.mHeaderProcessors.get(str);
            if (headerProcessor != null) {
                headerProcessor.process(str, str2, writer);
            } else {
                this.mDefaultHeaderProcessor.process(str, str2, writer);
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface HeaderProcessor {
        void process(String str, String str2, Writer writer) throws Exception;
    }

    /* loaded from: classes12.dex */
    public static class HeaderWriter implements HeaderProcessor {
        private static final String HEADER_DELIMETER = ": ";
        private static final String NEW_LINE = "\n";

        @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
        public void process(String str, String str2, Writer writer) throws Exception {
            if (str == null) {
                str = "null";
            }
            writer.write(str);
            writer.write(": ");
            if (str2 == null) {
                str2 = "null";
            }
            writer.write(str2);
            writer.write("\n");
        }
    }

    /* loaded from: classes12.dex */
    public static class PlainBodyWriter implements BodyWriter {
        @Override // com.amazon.device.utils.det.DetUtil.BodyWriter
        public void write(BufferedReader bufferedReader, Writer writer) throws Exception {
            char[] cArr = new char[8192];
            while (true) {
                int read = bufferedReader.read(cArr, 0, 8192);
                if (read == -1) {
                    return;
                }
                writer.write(cArr, 0, read);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class ReplaceHeader implements HeaderProcessor {
        private String mNewHeader;
        private HeaderProcessor mNextProcessor;

        public ReplaceHeader(String str, HeaderProcessor headerProcessor) {
            this.mNewHeader = str;
            this.mNextProcessor = headerProcessor;
        }

        @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
        public void process(String str, String str2, Writer writer) throws Exception {
            this.mNextProcessor.process(this.mNewHeader, str2, writer);
        }
    }

    /* loaded from: classes12.dex */
    public static class ReplaceValue implements HeaderProcessor {
        private HeaderProcessor mNextProcessor;
        private Map<String, String> mReplaceValueMap;

        public ReplaceValue(Map<String, String> map, HeaderProcessor headerProcessor) {
            this.mReplaceValueMap = map;
            this.mNextProcessor = headerProcessor;
        }

        @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
        public void process(String str, String str2, Writer writer) throws Exception {
            String str3 = this.mReplaceValueMap.get(str2);
            if (str3 == null) {
                this.mNextProcessor.process(str, str2, writer);
            } else {
                this.mNextProcessor.process(str, str3, writer);
            }
        }
    }

    static {
        sDetDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
    }

    public DetUtil() {
        this.mDetUploadTimeOut = (int) TimeUnit.MILLISECONDS.convert(120L, TimeUnit.SECONDS);
    }

    public void addEventsSectionHeader(Writer writer) throws Exception {
        writer.write("\n[Events]\n");
    }

    public void addMetadataSectionHeader(HeaderProcessor headerProcessor, String str, Map<String, String> map, Writer writer) throws Exception {
        writer.write("[Metadata]\n");
        headerProcessor.process("DeviceType", str, writer);
        headerProcessor.process("SystemVersion", Build.FINGERPRINT, writer);
        headerProcessor.process("BuildType", Build.TYPE, writer);
        headerProcessor.process("BuildTags", Build.TAGS, writer);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    headerProcessor.process(entry.getKey(), entry.getValue(), writer);
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("addMetadataSectionHeader: Extra info key or value set to null. Skipping. Key :");
                    outline107.append(entry.getKey());
                    outline107.append(" Value :");
                    outline107.append(entry.getValue());
                    outline107.toString();
                }
            }
        }
    }

    public byte[] compressMessage(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (bArr.length * 0.19999999f));
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            gZIPOutputStream.close();
            throw th;
        }
    }

    protected String createDetMfbsHeader(Long l) throws Exception {
        StringBuilder outline106 = GeneratedOutlineSupport1.outline106(150, "\nFiles: messages.0", "\n------------------", "\nMFBS/1.0 1", "\n");
        GeneratedOutlineSupport1.outline181(outline106, "\nContent-Type: ", com.amazon.deecomms.common.Constants.TEXT_PLAIN_MEDIA_TYPE, "\nContent-Encoding: ", "GZIP");
        outline106.append("\nContent-Length: ");
        outline106.append(l.toString());
        outline106.append("\nContent-Name: Content");
        outline106.append("\n\n");
        return outline106.toString();
    }

    public String formatDate(long j) {
        return sDetDateFormat.format(new Date(j));
    }

    public String processHeaders(BufferedReader bufferedReader, Writer writer, HeaderProcessor headerProcessor) throws Exception {
        String str = null;
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null || "".equals(readLine.trim())) {
                return str;
            }
            Matcher matcher = HEADER_VALUE_REGEX.matcher(readLine);
            if (matcher.matches()) {
                String group = matcher.group(1);
                String group2 = matcher.group(2);
                headerProcessor.process(group, group2, writer);
                if (group.equals(TRACE_HASH_CODE_HEADER_KEY)) {
                    str = group2;
                }
            }
        }
    }

    public DetUtil(int i) {
        this.mDetUploadTimeOut = (int) TimeUnit.MILLISECONDS.convert(120L, TimeUnit.SECONDS);
        this.mDetUploadTimeOut = i;
    }
}
