package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
public interface PatternCompiler {
    Pattern compile(String str) throws MalformedPatternException;

    Pattern compile(String str, int i) throws MalformedPatternException;

    Pattern compile(char[] cArr) throws MalformedPatternException;

    Pattern compile(char[] cArr, int i) throws MalformedPatternException;
}
