package com.google.i18n.phonenumbers.internal;

import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.RegexCache;
import java.util.regex.Matcher;
/* loaded from: classes3.dex */
public final class RegexBasedMatcher implements MatcherApi {
    private final RegexCache regexCache = new RegexCache(100);

    private RegexBasedMatcher() {
    }

    public static MatcherApi create() {
        return new RegexBasedMatcher();
    }

    @Override // com.google.i18n.phonenumbers.internal.MatcherApi
    public boolean matchesNationalNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc, boolean z) {
        Matcher matcher = this.regexCache.getPatternForRegex(phoneNumberDesc.getNationalNumberPattern()).matcher(str);
        return matcher.matches() || (z && matcher.lookingAt());
    }

    @Override // com.google.i18n.phonenumbers.internal.MatcherApi
    public boolean matchesPossibleNumber(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.regexCache.getPatternForRegex(phoneNumberDesc.getPossibleNumberPattern()).matcher(str).matches();
    }
}
