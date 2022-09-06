package org.apache.oro.text.perl;

import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Substitution;
/* loaded from: classes4.dex */
final class ParsedSubstitutionEntry {
    int _numSubstitutions;
    Pattern _pattern;
    Perl5Substitution _substitution;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParsedSubstitutionEntry(Pattern pattern, Perl5Substitution perl5Substitution, int i) {
        this._numSubstitutions = i;
        this._substitution = perl5Substitution;
        this._pattern = pattern;
    }
}
