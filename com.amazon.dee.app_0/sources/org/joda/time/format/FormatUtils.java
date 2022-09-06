package org.joda.time.format;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Writer;
/* loaded from: classes5.dex */
public class FormatUtils {
    private static final double LOG_10 = Math.log(10.0d);

    private FormatUtils() {
    }

    public static void appendPaddedInteger(Appendable appendable, int i, int i2) throws IOException {
        if (i < 0) {
            appendable.append('-');
            if (i == Integer.MIN_VALUE) {
                while (i2 > 10) {
                    appendable.append('0');
                    i2--;
                }
                appendable.append("2147483648");
                return;
            }
            i = -i;
        }
        if (i < 10) {
            while (i2 > 1) {
                appendable.append('0');
                i2--;
            }
        } else if (i >= 100) {
            int log = i < 1000 ? 3 : i < 10000 ? 4 : ((int) (Math.log(i) / LOG_10)) + 1;
            while (i2 > log) {
                appendable.append('0');
                i2--;
            }
            appendable.append(Integer.toString(i));
            return;
        } else {
            while (i2 > 2) {
                appendable.append('0');
                i2--;
            }
            int i3 = ((i + 1) * 13421772) >> 27;
            appendable.append((char) (i3 + 48));
            i = (i - (i3 << 3)) - (i3 << 1);
        }
        appendable.append((char) (i + 48));
    }

    public static void appendPaddedInteger(Appendable appendable, long j, int i) throws IOException {
        int i2 = (int) j;
        if (i2 == j) {
            appendPaddedInteger(appendable, i2, i);
            return;
        }
        if (i > 19) {
            if (j < 0) {
                appendable.append('-');
                if (j == Long.MIN_VALUE) {
                    while (i > 19) {
                        appendable.append('0');
                        i--;
                    }
                    appendable.append("9223372036854775808");
                    return;
                }
                j = -j;
            }
            int log = ((int) (Math.log(j) / LOG_10)) + 1;
            while (i > log) {
                appendable.append('0');
                i--;
            }
        }
        appendable.append(Long.toString(j));
    }

    public static void appendPaddedInteger(StringBuffer stringBuffer, int i, int i2) {
        try {
            appendPaddedInteger((Appendable) stringBuffer, i, i2);
        } catch (IOException unused) {
        }
    }

    public static void appendPaddedInteger(StringBuffer stringBuffer, long j, int i) {
        try {
            appendPaddedInteger((Appendable) stringBuffer, j, i);
        } catch (IOException unused) {
        }
    }

    public static void appendUnpaddedInteger(Appendable appendable, int i) throws IOException {
        if (i < 0) {
            appendable.append('-');
            if (i == Integer.MIN_VALUE) {
                appendable.append("2147483648");
                return;
            }
            i = -i;
        }
        if (i >= 10) {
            if (i >= 100) {
                appendable.append(Integer.toString(i));
                return;
            }
            int i2 = ((i + 1) * 13421772) >> 27;
            appendable.append((char) (i2 + 48));
            i = (i - (i2 << 3)) - (i2 << 1);
        }
        appendable.append((char) (i + 48));
    }

    public static void appendUnpaddedInteger(Appendable appendable, long j) throws IOException {
        int i = (int) j;
        if (i == j) {
            appendUnpaddedInteger(appendable, i);
        } else {
            appendable.append(Long.toString(j));
        }
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, int i) {
        try {
            appendUnpaddedInteger((Appendable) stringBuffer, i);
        } catch (IOException unused) {
        }
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, long j) {
        try {
            appendUnpaddedInteger((Appendable) stringBuffer, j);
        } catch (IOException unused) {
        }
    }

    public static int calculateDigitCount(long j) {
        if (j < 0) {
            if (j == Long.MIN_VALUE) {
                return 20;
            }
            return calculateDigitCount(-j) + 1;
        } else if (j < 10) {
            return 1;
        } else {
            if (j < 100) {
                return 2;
            }
            if (j < 1000) {
                return 3;
            }
            if (j >= 10000) {
                return 1 + ((int) (Math.log(j) / LOG_10));
            }
            return 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String createErrorMessage(String str, int i) {
        StringBuilder outline115;
        int i2 = i + 32;
        String concat = str.length() <= i2 + 3 ? str : str.substring(0, i2).concat("...");
        if (i <= 0) {
            outline115 = GeneratedOutlineSupport1.outline113("Invalid format: \"", concat);
        } else if (i >= str.length()) {
            outline115 = GeneratedOutlineSupport1.outline115("Invalid format: \"", concat, "\" is too short");
            return outline115.toString();
        } else {
            outline115 = GeneratedOutlineSupport1.outline115("Invalid format: \"", concat, "\" is malformed at \"");
            outline115.append(concat.substring(i));
        }
        outline115.append('\"');
        return outline115.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseTwoDigits(CharSequence charSequence, int i) {
        int charAt = charSequence.charAt(i) - '0';
        return (charSequence.charAt(i + 1) + ((charAt << 3) + (charAt << 1))) - 48;
    }

    public static void writePaddedInteger(Writer writer, int i, int i2) throws IOException {
        if (i < 0) {
            writer.write(45);
            if (i == Integer.MIN_VALUE) {
                while (i2 > 10) {
                    writer.write(48);
                    i2--;
                }
                writer.write("2147483648");
                return;
            }
            i = -i;
        }
        if (i < 10) {
            while (i2 > 1) {
                writer.write(48);
                i2--;
            }
        } else if (i >= 100) {
            int log = i < 1000 ? 3 : i < 10000 ? 4 : ((int) (Math.log(i) / LOG_10)) + 1;
            while (i2 > log) {
                writer.write(48);
                i2--;
            }
            writer.write(Integer.toString(i));
            return;
        } else {
            while (i2 > 2) {
                writer.write(48);
                i2--;
            }
            int i3 = ((i + 1) * 13421772) >> 27;
            writer.write(i3 + 48);
            i = (i - (i3 << 3)) - (i3 << 1);
        }
        writer.write(i + 48);
    }

    public static void writePaddedInteger(Writer writer, long j, int i) throws IOException {
        int i2 = (int) j;
        if (i2 == j) {
            writePaddedInteger(writer, i2, i);
            return;
        }
        if (i > 19) {
            if (j < 0) {
                writer.write(45);
                if (j == Long.MIN_VALUE) {
                    while (i > 19) {
                        writer.write(48);
                        i--;
                    }
                    writer.write("9223372036854775808");
                    return;
                }
                j = -j;
            }
            int log = ((int) (Math.log(j) / LOG_10)) + 1;
            while (i > log) {
                writer.write(48);
                i--;
            }
        }
        writer.write(Long.toString(j));
    }

    public static void writeUnpaddedInteger(Writer writer, int i) throws IOException {
        if (i < 0) {
            writer.write(45);
            if (i == Integer.MIN_VALUE) {
                writer.write("2147483648");
                return;
            }
            i = -i;
        }
        if (i >= 10) {
            if (i >= 100) {
                writer.write(Integer.toString(i));
                return;
            }
            int i2 = ((i + 1) * 13421772) >> 27;
            writer.write(i2 + 48);
            i = (i - (i2 << 3)) - (i2 << 1);
        }
        writer.write(i + 48);
    }

    public static void writeUnpaddedInteger(Writer writer, long j) throws IOException {
        int i = (int) j;
        if (i == j) {
            writeUnpaddedInteger(writer, i);
        } else {
            writer.write(Long.toString(j));
        }
    }
}
