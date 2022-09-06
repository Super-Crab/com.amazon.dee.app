package org.checkerframework.checker.i18nformatter;

import com.amazon.alexa.mode.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.lwansbrough.RCTCamera.RCTCameraModule;
import com.sun.mail.imap.IMAPStore;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;
/* loaded from: classes5.dex */
public class I18nFormatUtil {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class I18nConversion {
        public I18nConversionCategory category;
        public int index;

        public I18nConversion(int i, I18nConversionCategory i18nConversionCategory) {
            this.index = i;
            this.category = i18nConversionCategory;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.category.toString());
            sb.append("(index: ");
            return GeneratedOutlineSupport1.outline86(sb, this.index, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class MessageFormatParser {
        private static final int MODIFIER_CURRENCY = 1;
        private static final int MODIFIER_DEFAULT = 0;
        private static final int MODIFIER_INTEGER = 3;
        private static final int MODIFIER_PERCENT = 2;
        private static final int SEG_INDEX = 1;
        private static final int SEG_MODIFIER = 3;
        private static final int SEG_RAW = 0;
        private static final int SEG_TYPE = 2;
        private static final int TYPE_CHOICE = 4;
        private static final int TYPE_DATE = 2;
        private static final int TYPE_NULL = 0;
        private static final int TYPE_NUMBER = 1;
        private static final int TYPE_TIME = 3;
        private static List<Integer> argumentIndices;
        private static List<I18nConversionCategory> categories;
        private static Locale locale;
        public static int maxOffset;
        private static int numFormat;
        private static final String[] TYPE_KEYWORDS = {"", "number", IMAPStore.ID_DATE, "time", Constants.DRIVE_MODE_ALLDONE_CHOICE_PAYLOAD_KEY};
        private static final String[] NUMBER_MODIFIER_KEYWORDS = {"", "currency", "percent", "integer"};
        private static final String[] DATE_TIME_MODIFIER_KEYWORDS = {"", "short", RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_MEDIUM, "long", "full"};

        private MessageFormatParser() {
        }

        private static void applyPattern(String str) {
            StringBuilder[] sbArr = new StringBuilder[4];
            sbArr[0] = new StringBuilder();
            numFormat = 0;
            maxOffset = -1;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (i3 == 0) {
                    if (charAt == '\'') {
                        int i4 = i + 1;
                        if (i4 >= str.length() || str.charAt(i4) != '\'') {
                            z = !z;
                        } else {
                            sbArr[i3].append(charAt);
                            i = i4;
                        }
                    } else if (charAt == '{' && !z) {
                        if (sbArr[1] == null) {
                            sbArr[1] = new StringBuilder();
                        }
                        i3 = 1;
                    } else {
                        sbArr[i3].append(charAt);
                    }
                } else if (z) {
                    sbArr[i3].append(charAt);
                    if (charAt == '\'') {
                        z = false;
                    }
                } else if (charAt != ' ') {
                    if (charAt == '\'') {
                        sbArr[i3].append(charAt);
                        z = true;
                    } else if (charAt != ',') {
                        if (charAt == '{') {
                            i2++;
                            sbArr[i3].append(charAt);
                        } else if (charAt != '}') {
                            sbArr[i3].append(charAt);
                        } else if (i2 == 0) {
                            makeFormat(i, numFormat, sbArr);
                            numFormat++;
                            sbArr[1] = null;
                            sbArr[2] = null;
                            sbArr[3] = null;
                            i3 = 0;
                        } else {
                            i2--;
                            sbArr[i3].append(charAt);
                        }
                    } else if (i3 < 3) {
                        i3++;
                        if (sbArr[i3] == null) {
                            sbArr[i3] = new StringBuilder();
                        }
                    } else {
                        sbArr[i3].append(charAt);
                    }
                } else if (i3 != 2 || sbArr[2].length() > 0) {
                    sbArr[i3].append(charAt);
                }
                i++;
            }
            if (i2 != 0 || i3 == 0) {
                return;
            }
            maxOffset = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern");
        }

        private static final int findKeyword(String str, String[] strArr) {
            for (int i = 0; i < strArr.length; i++) {
                if (str.equals(strArr[i])) {
                    return i;
                }
            }
            String lowerCase = str.trim().toLowerCase(Locale.ROOT);
            if (lowerCase != str) {
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    if (lowerCase.equals(strArr[i2])) {
                        return i2;
                    }
                }
                return -1;
            }
            return -1;
        }

        private static void makeFormat(int i, int i2, StringBuilder[] sbArr) {
            I18nConversionCategory i18nConversionCategory;
            String[] strArr = new String[sbArr.length];
            for (int i3 = 0; i3 < sbArr.length; i3++) {
                StringBuilder sb = sbArr[i3];
                strArr[i3] = sb != null ? sb.toString() : "";
            }
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                if (parseInt >= 0) {
                    int i4 = maxOffset;
                    maxOffset = i2;
                    argumentIndices.add(Integer.valueOf(parseInt));
                    if (strArr[2].length() != 0) {
                        int findKeyword = findKeyword(strArr[2], TYPE_KEYWORDS);
                        if (findKeyword == 0) {
                            i18nConversionCategory = I18nConversionCategory.GENERAL;
                        } else if (findKeyword == 1) {
                            int findKeyword2 = findKeyword(strArr[3], NUMBER_MODIFIER_KEYWORDS);
                            if (findKeyword2 != 0 && findKeyword2 != 1 && findKeyword2 != 2 && findKeyword2 != 3) {
                                try {
                                    new DecimalFormat(strArr[3], DecimalFormatSymbols.getInstance(locale));
                                } catch (IllegalArgumentException e) {
                                    maxOffset = i4;
                                    throw e;
                                }
                            }
                            i18nConversionCategory = I18nConversionCategory.NUMBER;
                        } else if (findKeyword == 2 || findKeyword == 3) {
                            int findKeyword3 = findKeyword(strArr[3], DATE_TIME_MODIFIER_KEYWORDS);
                            if (findKeyword3 < 0 || findKeyword3 >= DATE_TIME_MODIFIER_KEYWORDS.length) {
                                try {
                                    new SimpleDateFormat(strArr[3], locale);
                                } catch (IllegalArgumentException e2) {
                                    maxOffset = i4;
                                    throw e2;
                                }
                            }
                            i18nConversionCategory = I18nConversionCategory.DATE;
                        } else if (findKeyword == 4) {
                            if (strArr[3].length() != 0) {
                                try {
                                    new ChoiceFormat(strArr[3]);
                                    i18nConversionCategory = I18nConversionCategory.NUMBER;
                                } catch (Exception e3) {
                                    maxOffset = i4;
                                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Choice Pattern incorrect: ");
                                    outline107.append(strArr[3]);
                                    throw new IllegalArgumentException(outline107.toString(), e3);
                                }
                            } else {
                                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Choice Pattern requires Subformat Pattern: ");
                                outline1072.append(strArr[3]);
                                throw new IllegalArgumentException(outline1072.toString());
                            }
                        } else {
                            maxOffset = i4;
                            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("unknown format type: ");
                            outline1073.append(strArr[2]);
                            throw new IllegalArgumentException(outline1073.toString());
                        }
                    } else {
                        i18nConversionCategory = I18nConversionCategory.GENERAL;
                    }
                    categories.add(i18nConversionCategory);
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("negative argument number: ", parseInt));
            } catch (NumberFormatException e4) {
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("can't parse argument number: ");
                outline1074.append(strArr[1]);
                throw new IllegalArgumentException(outline1074.toString(), e4);
            }
        }

        public static I18nConversion[] parse(String str) {
            categories = new ArrayList();
            argumentIndices = new ArrayList();
            locale = Locale.getDefault(Locale.Category.FORMAT);
            applyPattern(str);
            I18nConversion[] i18nConversionArr = new I18nConversion[numFormat];
            for (int i = 0; i < numFormat; i++) {
                i18nConversionArr[i] = new I18nConversion(argumentIndices.get(i).intValue(), categories.get(i));
            }
            return i18nConversionArr;
        }
    }

    public static I18nConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException {
        tryFormatSatisfiability(str);
        I18nConversion[] parse = MessageFormatParser.parse(str);
        HashMap hashMap = new HashMap();
        int i = -1;
        for (I18nConversion i18nConversion : parse) {
            int i2 = i18nConversion.index;
            hashMap.put(Integer.valueOf(i2), I18nConversionCategory.intersect(i18nConversion.category, hashMap.containsKey(Integer.valueOf(i2)) ? (I18nConversionCategory) hashMap.get(Integer.valueOf(i2)) : I18nConversionCategory.UNUSED));
            i = Math.max(i, i2);
        }
        I18nConversionCategory[] i18nConversionCategoryArr = new I18nConversionCategory[i + 1];
        for (int i3 = 0; i3 <= i; i3++) {
            i18nConversionCategoryArr[i3] = hashMap.containsKey(Integer.valueOf(i3)) ? (I18nConversionCategory) hashMap.get(Integer.valueOf(i3)) : I18nConversionCategory.UNUSED;
        }
        return i18nConversionCategoryArr;
    }

    @I18nChecksFormat
    public static boolean hasFormat(String str, I18nConversionCategory... i18nConversionCategoryArr) {
        I18nConversionCategory[] formatParameterCategories = formatParameterCategories(str);
        if (formatParameterCategories.length != i18nConversionCategoryArr.length) {
            return false;
        }
        for (int i = 0; i < i18nConversionCategoryArr.length; i++) {
            if (!I18nConversionCategory.isSubsetOf(i18nConversionCategoryArr[i], formatParameterCategories[i])) {
                return false;
            }
        }
        return true;
    }

    @I18nValidFormat
    public static boolean isFormat(String str) {
        try {
            formatParameterCategories(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        MessageFormat.format(str, null);
    }
}
