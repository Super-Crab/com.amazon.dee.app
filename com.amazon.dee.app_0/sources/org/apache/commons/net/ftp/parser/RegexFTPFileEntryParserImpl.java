package org.apache.commons.net.ftp.parser;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
/* loaded from: classes4.dex */
public abstract class RegexFTPFileEntryParserImpl extends FTPFileEntryParserImpl {
    protected PatternMatcher _matcher_;
    private Pattern pattern;
    private MatchResult result = null;

    public RegexFTPFileEntryParserImpl(String str) {
        this.pattern = null;
        this._matcher_ = null;
        try {
            this._matcher_ = new Perl5Matcher();
            this.pattern = new Perl5Compiler().compile(str);
        } catch (MalformedPatternException unused) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline71("Unparseable regex supplied:  ", str));
        }
    }

    public int getGroupCnt() {
        MatchResult matchResult = this.result;
        if (matchResult == null) {
            return 0;
        }
        return matchResult.groups();
    }

    public String getGroupsAsString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= this.result.groups(); i++) {
            stringBuffer.append(i);
            stringBuffer.append(") ");
            stringBuffer.append(this.result.group(i));
            stringBuffer.append(System.getProperty("line.separator"));
        }
        return stringBuffer.toString();
    }

    public String group(int i) {
        MatchResult matchResult = this.result;
        if (matchResult == null) {
            return null;
        }
        return matchResult.group(i);
    }

    public boolean matches(String str) {
        this.result = null;
        if (this._matcher_.matches(str.trim(), this.pattern)) {
            this.result = this._matcher_.getMatch();
        }
        return this.result != null;
    }
}
