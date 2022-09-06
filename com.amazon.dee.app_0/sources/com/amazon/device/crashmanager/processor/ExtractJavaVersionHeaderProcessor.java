package com.amazon.device.crashmanager.processor;

import com.amazon.device.utils.det.DetUtil;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
class ExtractJavaVersionHeaderProcessor implements DetUtil.HeaderProcessor {
    private static final Pattern JAVA_APP_VERSION_REGEX = Pattern.compile("^\\S+\\s+v(\\d+)\\s+\\((.+)\\)$");
    private final DetUtil.HeaderProcessor mNextProcessor;

    public ExtractJavaVersionHeaderProcessor(DetUtil.HeaderProcessor headerProcessor) {
        this.mNextProcessor = headerProcessor;
    }

    @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
    public void process(String str, String str2, Writer writer) throws Exception {
        Matcher matcher = JAVA_APP_VERSION_REGEX.matcher(str2);
        if (matcher.matches()) {
            this.mNextProcessor.process("Version", matcher.group(1), writer);
        }
        this.mNextProcessor.process(str, str2, writer);
    }
}
