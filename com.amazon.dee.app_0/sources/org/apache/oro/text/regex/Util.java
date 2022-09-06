package org.apache.oro.text.regex;

import java.util.Collection;
import java.util.Vector;
/* loaded from: classes4.dex */
public final class Util {
    public static final int SPLIT_ALL = 0;
    public static final int SUBSTITUTE_ALL = -1;

    private Util() {
    }

    public static Vector split(PatternMatcher patternMatcher, Pattern pattern, String str) {
        return split(patternMatcher, pattern, str, 0);
    }

    public static Vector split(PatternMatcher patternMatcher, Pattern pattern, String str, int i) {
        Vector vector = new Vector(20);
        split(vector, patternMatcher, pattern, str, i);
        return vector;
    }

    public static void split(Collection collection, PatternMatcher patternMatcher, Pattern pattern, String str) {
        split(collection, patternMatcher, pattern, str, 0);
    }

    public static void split(Collection collection, PatternMatcher patternMatcher, Pattern pattern, String str, int i) {
        PatternMatcherInput patternMatcherInput = new PatternMatcherInput(str);
        int i2 = 0;
        while (true) {
            i--;
            if (i == 0 || !patternMatcher.contains(patternMatcherInput, pattern)) {
                break;
            }
            MatchResult match = patternMatcher.getMatch();
            collection.add(str.substring(i2, match.beginOffset(0)));
            i2 = match.endOffset(0);
        }
        collection.add(str.substring(i2, str.length()));
    }

    public static int substitute(StringBuffer stringBuffer, PatternMatcher patternMatcher, Pattern pattern, Substitution substitution, String str, int i) {
        return substitute(stringBuffer, patternMatcher, pattern, substitution, new PatternMatcherInput(str), i);
    }

    public static int substitute(StringBuffer stringBuffer, PatternMatcher patternMatcher, Pattern pattern, Substitution substitution, PatternMatcherInput patternMatcherInput, int i) {
        int beginOffset = patternMatcherInput.getBeginOffset();
        char[] buffer = patternMatcherInput.getBuffer();
        int i2 = 0;
        while (i != 0 && patternMatcher.contains(patternMatcherInput, pattern)) {
            i--;
            i2++;
            stringBuffer.append(buffer, beginOffset, patternMatcherInput.getMatchBeginOffset() - beginOffset);
            substitution.appendSubstitution(stringBuffer, patternMatcher.getMatch(), i2, patternMatcherInput, patternMatcher, pattern);
            beginOffset = patternMatcherInput.getMatchEndOffset();
        }
        stringBuffer.append(buffer, beginOffset, patternMatcherInput.length() - beginOffset);
        return i2;
    }

    public static String substitute(PatternMatcher patternMatcher, Pattern pattern, Substitution substitution, String str) {
        return substitute(patternMatcher, pattern, substitution, str, 1);
    }

    public static String substitute(PatternMatcher patternMatcher, Pattern pattern, Substitution substitution, String str, int i) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        return substitute(stringBuffer, patternMatcher, pattern, substitution, new PatternMatcherInput(str), i) != 0 ? stringBuffer.toString() : str;
    }
}
