package org.apache.oro.text.regex;
/* loaded from: classes4.dex */
public interface MatchResult {
    int begin(int i);

    int beginOffset(int i);

    int end(int i);

    int endOffset(int i);

    String group(int i);

    int groups();

    int length();

    String toString();
}
