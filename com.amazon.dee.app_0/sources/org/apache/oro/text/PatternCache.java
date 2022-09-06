package org.apache.oro.text;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
/* loaded from: classes4.dex */
public interface PatternCache {
    Pattern addPattern(String str) throws MalformedPatternException;

    Pattern addPattern(String str, int i) throws MalformedPatternException;

    int capacity();

    Pattern getPattern(String str) throws MalformedCachePatternException;

    Pattern getPattern(String str, int i) throws MalformedCachePatternException;

    int size();
}
