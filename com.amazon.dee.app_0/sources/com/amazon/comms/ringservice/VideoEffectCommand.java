package com.amazon.comms.ringservice;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes12.dex */
public class VideoEffectCommand {
    private static final String COMMAND_KEY = "name";
    private static final String COMMAND_KEY_VALUE_SEPARATOR = ":";
    private static final String COMMAND_PREFIX = "[{Format:1.0}, {";
    private static final String COMMAND_SUFFIX = "}]";
    private static final String COMMAND_TUPLE_SEPARATOR = ", ";
    private static final String ENABLE_KEY = "enable";
    private final String command;
    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    private final DecimalFormat decimalFormat = (DecimalFormat) this.numberFormat;
    private final Map<String, String> parameters = new TreeMap();

    public VideoEffectCommand(String str) {
        this.command = str;
        this.decimalFormat.applyPattern("0.0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoEffectCommand add(String str, String str2) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str));
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str2));
        this.parameters.put(str, str2);
        return this;
    }

    public String getCommand() {
        return this.command;
    }

    public DecimalFormat getDecimalFormat() {
        return this.decimalFormat;
    }

    public NumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    VideoEffectCommand remove(String str) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str));
        this.parameters.remove(str);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean z = !this.parameters.isEmpty();
        GeneratedOutlineSupport1.outline180(sb, COMMAND_PREFIX, "name", ":");
        GeneratedOutlineSupport1.outline181(sb, this.command, COMMAND_TUPLE_SEPARATOR, ENABLE_KEY, ":");
        sb.append(z);
        if (z) {
            sb.append(COMMAND_TUPLE_SEPARATOR);
            sb.append(Joiner.on(COMMAND_TUPLE_SEPARATOR).withKeyValueSeparator(":").join(this.parameters));
        }
        sb.append(COMMAND_SUFFIX);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoEffectCommand add(String str, int i) {
        return add(str, Integer.toString(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoEffectCommand add(String str, float f) {
        return add(str, this.decimalFormat.format(f));
    }
}
