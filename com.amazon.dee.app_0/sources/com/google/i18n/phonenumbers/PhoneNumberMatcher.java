package com.google.i18n.phonenumbers;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber;
import java.lang.Character;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes3.dex */
final class PhoneNumberMatcher implements Iterator<PhoneNumberMatch> {
    private static final Pattern LEAD_CLASS;
    private static final Pattern MATCHING_BRACKETS;
    private static final Pattern PATTERN;
    private final PhoneNumberUtil.Leniency leniency;
    private long maxTries;
    private final PhoneNumberUtil phoneUtil;
    private final String preferredRegion;
    private final CharSequence text;
    private static final Pattern PUB_PAGES = Pattern.compile("\\d{1,5}-+\\d{1,5}\\s{0,4}\\(\\d{1,4}");
    private static final Pattern SLASH_SEPARATED_DATES = Pattern.compile("(?:(?:[0-3]?\\d/[01]?\\d)|(?:[01]?\\d/[0-3]?\\d))/(?:[12]\\d)?\\d{2}");
    private static final Pattern TIME_STAMPS = Pattern.compile("[12]\\d{3}[-/]?[01]\\d[-/]?[0-3]\\d +[0-2]\\d$");
    private static final Pattern TIME_STAMPS_SUFFIX = Pattern.compile(":[0-5]\\d");
    private static final Pattern[] INNER_MATCHES = {Pattern.compile("/+(.*)"), Pattern.compile("(\\([^(]*)"), Pattern.compile("(?:\\p{Z}-|-\\p{Z})\\p{Z}*(.+)"), Pattern.compile("[‒-―－]\\p{Z}*(.+)"), Pattern.compile("\\.+\\p{Z}*([^.]+)"), Pattern.compile("\\p{Z}+(\\P{Z}+)")};
    private State state = State.NOT_READY;
    private PhoneNumberMatch lastMatch = null;
    private int searchIndex = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface NumberGroupingChecker {
        boolean checkGroups(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum State {
        NOT_READY,
        READY,
        DONE
    }

    static {
        String outline93 = GeneratedOutlineSupport1.outline93(new StringBuilder(")\\]）］".length() + "(\\[（［".length() + 3), "[^", "(\\[（［", ")\\]）］", "]");
        String limit = limit(0, 3);
        String valueOf = String.valueOf(outline93);
        String valueOf2 = String.valueOf(outline93);
        String valueOf3 = String.valueOf(outline93);
        String valueOf4 = String.valueOf(limit);
        String valueOf5 = String.valueOf(outline93);
        StringBuilder sb = new StringBuilder(valueOf5.length() + valueOf4.length() + ")\\]）］".length() + valueOf3.length() + "(\\[（［".length() + valueOf2.length() + ")\\]）］".length() + valueOf.length() + "(\\[（［".length() + 26);
        GeneratedOutlineSupport1.outline181(sb, "(?:[", "(\\[（［", "])?", "(?:");
        GeneratedOutlineSupport1.outline181(sb, valueOf, "+", "[", ")\\]）］");
        GeneratedOutlineSupport1.outline181(sb, "])?", valueOf2, "+", "(?:[");
        GeneratedOutlineSupport1.outline181(sb, "(\\[（［", "]", valueOf3, "+[");
        GeneratedOutlineSupport1.outline181(sb, ")\\]）］", "])", valueOf4, valueOf5);
        sb.append("*");
        MATCHING_BRACKETS = Pattern.compile(sb.toString());
        String limit2 = limit(0, 2);
        String limit3 = limit(0, 4);
        String limit4 = limit(0, 20);
        String valueOf6 = String.valueOf(limit3);
        String concat = valueOf6.length() != 0 ? "[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]".concat(valueOf6) : new String("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]");
        String valueOf7 = String.valueOf(limit(1, 20));
        String concat2 = valueOf7.length() != 0 ? "\\p{Nd}".concat(valueOf7) : new String("\\p{Nd}");
        String valueOf8 = String.valueOf("+＋".length() != 0 ? "(\\[（［".concat("+＋") : new String("(\\[（［"));
        String outline92 = GeneratedOutlineSupport1.outline92(new StringBuilder(valueOf8.length() + 2), "[", valueOf8, "]");
        LEAD_CLASS = Pattern.compile(outline92);
        String valueOf9 = String.valueOf(outline92);
        String valueOf10 = String.valueOf(concat);
        String valueOf11 = String.valueOf(limit2);
        String valueOf12 = String.valueOf(concat2);
        String valueOf13 = String.valueOf(concat);
        String valueOf14 = String.valueOf(concat2);
        String valueOf15 = String.valueOf(limit4);
        String valueOf16 = String.valueOf(PhoneNumberUtil.EXTN_PATTERNS_FOR_MATCHING);
        StringBuilder sb2 = new StringBuilder(valueOf16.length() + valueOf15.length() + valueOf14.length() + valueOf13.length() + valueOf12.length() + valueOf11.length() + valueOf10.length() + valueOf9.length() + 13);
        GeneratedOutlineSupport1.outline181(sb2, "(?:", valueOf9, valueOf10, ")");
        GeneratedOutlineSupport1.outline181(sb2, valueOf11, valueOf12, "(?:", valueOf13);
        GeneratedOutlineSupport1.outline181(sb2, valueOf14, ")", valueOf15, "(?:");
        sb2.append(valueOf16);
        sb2.append(")?");
        PATTERN = Pattern.compile(sb2.toString(), 66);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhoneNumberMatcher(PhoneNumberUtil phoneNumberUtil, String str, String str2, PhoneNumberUtil.Leniency leniency, long j) {
        if (phoneNumberUtil == null || leniency == null) {
            throw new NullPointerException();
        }
        if (j >= 0) {
            this.phoneUtil = phoneNumberUtil;
            this.text = str == null ? "" : str;
            this.preferredRegion = str2;
            this.leniency = leniency;
            this.maxTries = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean allNumberGroupsAreExactlyPresent(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        String[] split = PhoneNumberUtil.NON_DIGITS_PATTERN.split(sb.toString());
        int length = phoneNumber.hasExtension() ? split.length - 2 : split.length - 1;
        if (split.length == 1 || split[length].contains(phoneNumberUtil.getNationalSignificantNumber(phoneNumber))) {
            return true;
        }
        int length2 = strArr.length - 1;
        while (length2 > 0 && length >= 0) {
            if (!split[length].equals(strArr[length2])) {
                return false;
            }
            length2--;
            length--;
        }
        return length >= 0 && split[length].endsWith(strArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean allNumberGroupsRemainGrouped(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        int i;
        if (phoneNumber.getCountryCodeSource() != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            String num = Integer.toString(phoneNumber.getCountryCode());
            i = num.length() + sb.indexOf(num);
        } else {
            i = 0;
        }
        int i2 = i;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            int indexOf = sb.indexOf(strArr[i3], i2);
            if (indexOf < 0) {
                return false;
            }
            i2 = indexOf + strArr[i3].length();
            if (i3 == 0 && i2 < sb.length() && phoneNumberUtil.getNddPrefixForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()), true) != null && Character.isDigit(sb.charAt(i2))) {
                return sb.substring(i2 - strArr[i3].length()).startsWith(phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            }
        }
        return sb.substring(i2).contains(phoneNumber.getExtension());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkNumberGroupingIsValid(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil, NumberGroupingChecker numberGroupingChecker) {
        StringBuilder normalizeDigits = PhoneNumberUtil.normalizeDigits(str, true);
        if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, normalizeDigits, getNationalNumberGroups(phoneNumberUtil, phoneNumber, null))) {
            return true;
        }
        Phonemetadata.PhoneMetadata alternateFormatsForCountry = MetadataManager.getAlternateFormatsForCountry(phoneNumber.getCountryCode());
        if (alternateFormatsForCountry == null) {
            return false;
        }
        for (Phonemetadata.NumberFormat numberFormat : alternateFormatsForCountry.numberFormats()) {
            if (numberGroupingChecker.checkGroups(phoneNumberUtil, phoneNumber, normalizeDigits, getNationalNumberGroups(phoneNumberUtil, phoneNumber, numberFormat))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean containsMoreThanOneSlashInNationalNumber(Phonenumber.PhoneNumber phoneNumber, String str) {
        int indexOf;
        int indexOf2 = str.indexOf(47);
        if (indexOf2 >= 0 && (indexOf = str.indexOf(47, indexOf2 + 1)) >= 0) {
            if ((phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN || phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN) && PhoneNumberUtil.normalizeDigitsOnly(str.substring(0, indexOf2)).equals(Integer.toString(phoneNumber.getCountryCode()))) {
                return str.substring(indexOf + 1).contains("/");
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean containsOnlyValidXChars(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
        int i = 0;
        while (i < str.length() - 1) {
            char charAt = str.charAt(i);
            if (charAt == 'x' || charAt == 'X') {
                int i2 = i + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 != 'x' && charAt2 != 'X') {
                    if (!PhoneNumberUtil.normalizeDigitsOnly(str.substring(i)).equals(phoneNumber.getExtension())) {
                        return false;
                    }
                } else if (phoneNumberUtil.isNumberMatch(phoneNumber, str.substring(i2)) != PhoneNumberUtil.MatchType.NSN_MATCH) {
                    return false;
                } else {
                    i = i2;
                }
            }
            i++;
        }
        return true;
    }

    private PhoneNumberMatch extractInnerMatch(String str, int i) {
        for (Pattern pattern : INNER_MATCHES) {
            Matcher matcher = pattern.matcher(str);
            boolean z = true;
            while (matcher.find() && this.maxTries > 0) {
                if (z) {
                    PhoneNumberMatch parseAndVerify = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.UNWANTED_END_CHAR_PATTERN, str.substring(0, matcher.start())).toString(), i);
                    if (parseAndVerify != null) {
                        return parseAndVerify;
                    }
                    this.maxTries--;
                    z = false;
                }
                PhoneNumberMatch parseAndVerify2 = parseAndVerify(trimAfterFirstMatch(PhoneNumberUtil.UNWANTED_END_CHAR_PATTERN, matcher.group(1)).toString(), matcher.start(1) + i);
                if (parseAndVerify2 != null) {
                    return parseAndVerify2;
                }
                this.maxTries--;
            }
        }
        return null;
    }

    private PhoneNumberMatch extractMatch(CharSequence charSequence, int i) {
        if (SLASH_SEPARATED_DATES.matcher(charSequence).find()) {
            return null;
        }
        if (TIME_STAMPS.matcher(charSequence).find()) {
            if (TIME_STAMPS_SUFFIX.matcher(this.text.toString().substring(charSequence.length() + i)).lookingAt()) {
                return null;
            }
        }
        String charSequence2 = charSequence.toString();
        PhoneNumberMatch parseAndVerify = parseAndVerify(charSequence2, i);
        return parseAndVerify != null ? parseAndVerify : extractInnerMatch(charSequence2, i);
    }

    private PhoneNumberMatch find(int i) {
        Matcher matcher = PATTERN.matcher(this.text);
        while (this.maxTries > 0 && matcher.find(i)) {
            int start = matcher.start();
            CharSequence trimAfterFirstMatch = trimAfterFirstMatch(PhoneNumberUtil.SECOND_NUMBER_START_PATTERN, this.text.subSequence(start, matcher.end()));
            PhoneNumberMatch extractMatch = extractMatch(trimAfterFirstMatch, start);
            if (extractMatch != null) {
                return extractMatch;
            }
            i = start + trimAfterFirstMatch.length();
            this.maxTries--;
        }
        return null;
    }

    private static String[] getNationalNumberGroups(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, Phonemetadata.NumberFormat numberFormat) {
        if (numberFormat == null) {
            String format = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
            int indexOf = format.indexOf(59);
            if (indexOf < 0) {
                indexOf = format.length();
            }
            return format.substring(format.indexOf(45) + 1, indexOf).split(ProcessIdUtil.DEFAULT_PROCESSID);
        }
        return phoneNumberUtil.formatNsnUsingPattern(phoneNumberUtil.getNationalSignificantNumber(phoneNumber), numberFormat, PhoneNumberUtil.PhoneNumberFormat.RFC3966).split(ProcessIdUtil.DEFAULT_PROCESSID);
    }

    private static boolean isInvalidPunctuationSymbol(char c) {
        return c == '%' || Character.getType(c) == 26;
    }

    static boolean isLatinLetter(char c) {
        if (Character.isLetter(c) || Character.getType(c) == 6) {
            Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
            return of.equals(Character.UnicodeBlock.BASIC_LATIN) || of.equals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_A) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_B) || of.equals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isNationalPrefixPresentIfRequired(Phonenumber.PhoneNumber phoneNumber, PhoneNumberUtil phoneNumberUtil) {
        Phonemetadata.PhoneMetadata metadataForRegion;
        if (phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY && (metadataForRegion = phoneNumberUtil.getMetadataForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()))) != null) {
            Phonemetadata.NumberFormat chooseFormattingPatternForNumber = phoneNumberUtil.chooseFormattingPatternForNumber(metadataForRegion.numberFormats(), phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            if (chooseFormattingPatternForNumber != null && chooseFormattingPatternForNumber.getNationalPrefixFormattingRule().length() > 0 && !chooseFormattingPatternForNumber.isNationalPrefixOptionalWhenFormatting() && !PhoneNumberUtil.formattingRuleHasFirstGroupOnly(chooseFormattingPatternForNumber.getNationalPrefixFormattingRule())) {
                return phoneNumberUtil.maybeStripNationalPrefixAndCarrierCode(new StringBuilder(PhoneNumberUtil.normalizeDigitsOnly(phoneNumber.getRawInput())), metadataForRegion, null);
            }
            return true;
        }
        return true;
    }

    private static String limit(int i, int i2) {
        if (i >= 0 && i2 > 0 && i2 >= i) {
            StringBuilder sb = new StringBuilder(25);
            sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
            sb.append(i);
            sb.append(",");
            sb.append(i2);
            sb.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            return sb.toString();
        }
        throw new IllegalArgumentException();
    }

    private PhoneNumberMatch parseAndVerify(String str, int i) {
        try {
            if (MATCHING_BRACKETS.matcher(str).matches() && !PUB_PAGES.matcher(str).find()) {
                if (this.leniency.compareTo(PhoneNumberUtil.Leniency.VALID) >= 0) {
                    if (i > 0 && !LEAD_CLASS.matcher(str).lookingAt()) {
                        char charAt = this.text.charAt(i - 1);
                        if (isInvalidPunctuationSymbol(charAt) || isLatinLetter(charAt)) {
                            return null;
                        }
                    }
                    int length = str.length() + i;
                    if (length < this.text.length()) {
                        char charAt2 = this.text.charAt(length);
                        if (isInvalidPunctuationSymbol(charAt2) || isLatinLetter(charAt2)) {
                            return null;
                        }
                    }
                }
                Phonenumber.PhoneNumber parseAndKeepRawInput = this.phoneUtil.parseAndKeepRawInput(str, this.preferredRegion);
                if ((!this.phoneUtil.getRegionCodeForCountryCode(parseAndKeepRawInput.getCountryCode()).equals("IL") || this.phoneUtil.getNationalSignificantNumber(parseAndKeepRawInput).length() != 4 || (i != 0 && (i <= 0 || this.text.charAt(i - 1) == '*'))) && this.leniency.verify(parseAndKeepRawInput, str, this.phoneUtil)) {
                    parseAndKeepRawInput.clearCountryCodeSource();
                    parseAndKeepRawInput.clearRawInput();
                    parseAndKeepRawInput.clearPreferredDomesticCarrierCode();
                    return new PhoneNumberMatch(i, str, parseAndKeepRawInput);
                }
            }
        } catch (NumberParseException unused) {
        }
        return null;
    }

    private static CharSequence trimAfterFirstMatch(Pattern pattern, CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence);
        return matcher.find() ? charSequence.subSequence(0, matcher.start()) : charSequence;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.state == State.NOT_READY) {
            this.lastMatch = find(this.searchIndex);
            PhoneNumberMatch phoneNumberMatch = this.lastMatch;
            if (phoneNumberMatch == null) {
                this.state = State.DONE;
            } else {
                this.searchIndex = phoneNumberMatch.end();
                this.state = State.READY;
            }
        }
        return this.state == State.READY;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    /* renamed from: next */
    public PhoneNumberMatch mo8354next() {
        if (hasNext()) {
            PhoneNumberMatch phoneNumberMatch = this.lastMatch;
            this.lastMatch = null;
            this.state = State.NOT_READY;
            return phoneNumberMatch;
        }
        throw new NoSuchElementException();
    }
}
