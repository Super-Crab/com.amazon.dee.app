package org.apache.oro.text;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
/* loaded from: classes4.dex */
public final class MatchActionInfo {
    public char[] charLine;
    public Pattern fieldSeparator;
    public List fields;
    public BufferedReader input;
    public String line;
    public int lineNumber;
    public MatchResult match;
    public PatternMatcher matcher;
    public PrintWriter output;
    public Pattern pattern;
}
