package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
public interface PatternMatcher {
    boolean contains(String str, Pattern pattern);

    boolean contains(PatternMatcherInput patternMatcherInput, Pattern pattern);

    boolean contains(char[] cArr, Pattern pattern);

    MatchResult getMatch();

    boolean matches(String str, Pattern pattern);

    boolean matches(PatternMatcherInput patternMatcherInput, Pattern pattern);

    boolean matches(char[] cArr, Pattern pattern);

    boolean matchesPrefix(String str, Pattern pattern);

    boolean matchesPrefix(PatternMatcherInput patternMatcherInput, Pattern pattern);

    boolean matchesPrefix(char[] cArr, Pattern pattern);

    boolean matchesPrefix(char[] cArr, Pattern pattern, int i);
}
