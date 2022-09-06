package com.google.android.exoplayer2.text.ssa;

import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Ints;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class SsaStyle {
    public static final int SSA_ALIGNMENT_BOTTOM_CENTER = 2;
    public static final int SSA_ALIGNMENT_BOTTOM_LEFT = 1;
    public static final int SSA_ALIGNMENT_BOTTOM_RIGHT = 3;
    public static final int SSA_ALIGNMENT_MIDDLE_CENTER = 5;
    public static final int SSA_ALIGNMENT_MIDDLE_LEFT = 4;
    public static final int SSA_ALIGNMENT_MIDDLE_RIGHT = 6;
    public static final int SSA_ALIGNMENT_TOP_CENTER = 8;
    public static final int SSA_ALIGNMENT_TOP_LEFT = 7;
    public static final int SSA_ALIGNMENT_TOP_RIGHT = 9;
    public static final int SSA_ALIGNMENT_UNKNOWN = -1;
    private static final String TAG = "SsaStyle";
    public final int alignment;
    public final boolean bold;
    public final float fontSize;
    public final boolean italic;
    public final String name;
    @Nullable
    @ColorInt
    public final Integer primaryColor;

    /* loaded from: classes2.dex */
    static final class Format {
        public final int alignmentIndex;
        public final int boldIndex;
        public final int fontSizeIndex;
        public final int italicIndex;
        public final int length;
        public final int nameIndex;
        public final int primaryColorIndex;

        private Format(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.nameIndex = i;
            this.alignmentIndex = i2;
            this.primaryColorIndex = i3;
            this.fontSizeIndex = i4;
            this.boldIndex = i5;
            this.italicIndex = i6;
            this.length = i7;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Nullable
        public static Format fromFormatLine(String str) {
            boolean z;
            String[] split = TextUtils.split(str.substring(7), ",");
            int i = -1;
            int i2 = -1;
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            int i6 = -1;
            for (int i7 = 0; i7 < split.length; i7++) {
                String lowerInvariant = Util.toLowerInvariant(split[i7].trim());
                switch (lowerInvariant.hashCode()) {
                    case -1178781136:
                        if (lowerInvariant.equals(TtmlNode.ITALIC)) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case -70925746:
                        if (lowerInvariant.equals("primarycolour")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case 3029637:
                        if (lowerInvariant.equals(TtmlNode.BOLD)) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case 3373707:
                        if (lowerInvariant.equals("name")) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case 366554320:
                        if (lowerInvariant.equals("fontsize")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    case 1767875043:
                        if (lowerInvariant.equals("alignment")) {
                            z = true;
                            break;
                        }
                        z = true;
                        break;
                    default:
                        z = true;
                        break;
                }
                if (!z) {
                    i = i7;
                } else if (z) {
                    i2 = i7;
                } else if (z) {
                    i3 = i7;
                } else if (z) {
                    i4 = i7;
                } else if (z) {
                    i5 = i7;
                } else if (z) {
                    i6 = i7;
                }
            }
            if (i != -1) {
                return new Format(i, i2, i3, i4, i5, i6, split.length);
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    static final class Overrides {
        private static final String TAG = "SsaStyle.Overrides";
        public final int alignment;
        @Nullable
        public final PointF position;
        private static final Pattern BRACES_PATTERN = Pattern.compile("\\{([^}]*)\\}");
        private static final String PADDED_DECIMAL_PATTERN = "\\s*\\d+(?:\\.\\d+)?\\s*";
        private static final Pattern POSITION_PATTERN = Pattern.compile(Util.formatInvariant("\\\\pos\\((%1$s),(%1$s)\\)", PADDED_DECIMAL_PATTERN));
        private static final Pattern MOVE_PATTERN = Pattern.compile(Util.formatInvariant("\\\\move\\(%1$s,%1$s,(%1$s),(%1$s)(?:,%1$s,%1$s)?\\)", PADDED_DECIMAL_PATTERN));
        private static final Pattern ALIGNMENT_OVERRIDE_PATTERN = Pattern.compile("\\\\an(\\d+)");

        private Overrides(int i, @Nullable PointF pointF) {
            this.alignment = i;
            this.position = pointF;
        }

        private static int parseAlignmentOverride(String str) {
            Matcher matcher = ALIGNMENT_OVERRIDE_PATTERN.matcher(str);
            if (matcher.find()) {
                return SsaStyle.parseAlignment((String) Assertions.checkNotNull(matcher.group(1)));
            }
            return -1;
        }

        public static Overrides parseFromDialogue(String str) {
            Matcher matcher = BRACES_PATTERN.matcher(str);
            PointF pointF = null;
            int i = -1;
            while (matcher.find()) {
                String str2 = (String) Assertions.checkNotNull(matcher.group(1));
                try {
                    PointF parsePosition = parsePosition(str2);
                    if (parsePosition != null) {
                        pointF = parsePosition;
                    }
                } catch (RuntimeException unused) {
                }
                try {
                    int parseAlignmentOverride = parseAlignmentOverride(str2);
                    if (parseAlignmentOverride != -1) {
                        i = parseAlignmentOverride;
                    }
                } catch (RuntimeException unused2) {
                }
            }
            return new Overrides(i, pointF);
        }

        @Nullable
        private static PointF parsePosition(String str) {
            String group;
            String group2;
            Matcher matcher = POSITION_PATTERN.matcher(str);
            Matcher matcher2 = MOVE_PATTERN.matcher(str);
            boolean find = matcher.find();
            boolean find2 = matcher2.find();
            if (find) {
                if (find2) {
                    Log.i(TAG, "Override has both \\pos(x,y) and \\move(x1,y1,x2,y2); using \\pos values. override='" + str + "'");
                }
                group = matcher.group(1);
                group2 = matcher.group(2);
            } else if (!find2) {
                return null;
            } else {
                group = matcher2.group(1);
                group2 = matcher2.group(2);
            }
            return new PointF(Float.parseFloat(((String) Assertions.checkNotNull(group)).trim()), Float.parseFloat(((String) Assertions.checkNotNull(group2)).trim()));
        }

        public static String stripStyleOverrides(String str) {
            return BRACES_PATTERN.matcher(str).replaceAll("");
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SsaAlignment {
    }

    private SsaStyle(String str, int i, @Nullable @ColorInt Integer num, float f, boolean z, boolean z2) {
        this.name = str;
        this.alignment = i;
        this.primaryColor = num;
        this.fontSize = f;
        this.bold = z;
        this.italic = z2;
    }

    @Nullable
    public static SsaStyle fromStyleLine(String str, Format format) {
        Assertions.checkArgument(str.startsWith("Style:"));
        String[] split = TextUtils.split(str.substring(6), ",");
        int length = split.length;
        int i = format.length;
        boolean z = false;
        if (length != i) {
            Log.w(TAG, Util.formatInvariant("Skipping malformed 'Style:' line (expected %s values, found %s): '%s'", Integer.valueOf(i), Integer.valueOf(split.length), str));
            return null;
        }
        try {
            String trim = split[format.nameIndex].trim();
            int i2 = format.alignmentIndex;
            int parseAlignment = i2 != -1 ? parseAlignment(split[i2].trim()) : -1;
            int i3 = format.primaryColorIndex;
            Integer parseColor = i3 != -1 ? parseColor(split[i3].trim()) : null;
            int i4 = format.fontSizeIndex;
            float parseFontSize = i4 != -1 ? parseFontSize(split[i4].trim()) : -3.4028235E38f;
            int i5 = format.boldIndex;
            boolean parseBoldOrItalic = i5 != -1 ? parseBoldOrItalic(split[i5].trim()) : false;
            int i6 = format.italicIndex;
            if (i6 != -1) {
                z = parseBoldOrItalic(split[i6].trim());
            }
            return new SsaStyle(trim, parseAlignment, parseColor, parseFontSize, parseBoldOrItalic, z);
        } catch (RuntimeException e) {
            Log.w(TAG, "Skipping malformed 'Style:' line: '" + str + "'", e);
            return null;
        }
    }

    private static boolean isValidAlignment(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int parseAlignment(String str) {
        try {
            int parseInt = Integer.parseInt(str.trim());
            if (isValidAlignment(parseInt)) {
                return parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        GeneratedOutlineSupport1.outline168("Ignoring unknown alignment: ", str, TAG);
        return -1;
    }

    private static boolean parseBoldOrItalic(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt == 1 || parseInt == -1;
        } catch (NumberFormatException e) {
            Log.w(TAG, "Failed to parse bold/italic: '" + str + "'", e);
            return false;
        }
    }

    @Nullable
    @ColorInt
    public static Integer parseColor(String str) {
        long parseLong;
        try {
            if (str.startsWith("&H")) {
                parseLong = Long.parseLong(str.substring(2), 16);
            } else {
                parseLong = Long.parseLong(str);
            }
            Assertions.checkArgument(parseLong <= BodyPartID.bodyIdMax);
            return Integer.valueOf(Color.argb(Ints.checkedCast(((parseLong >> 24) & 255) ^ 255), Ints.checkedCast(parseLong & 255), Ints.checkedCast((parseLong >> 8) & 255), Ints.checkedCast((parseLong >> 16) & 255)));
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Failed to parse color expression: '" + str + "'", e);
            return null;
        }
    }

    private static float parseFontSize(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            Log.w(TAG, "Failed to parse font size: '" + str + "'", e);
            return -3.4028235E38f;
        }
    }
}
