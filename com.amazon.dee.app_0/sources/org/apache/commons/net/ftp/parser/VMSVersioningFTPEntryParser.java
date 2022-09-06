package org.apache.commons.net.ftp.parser;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
/* loaded from: classes4.dex */
public class VMSVersioningFTPEntryParser extends VMSFTPEntryParser {
    private static final String PRE_PARSE_REGEX = "(.*);([0-9]+)\\s*.*";
    private Perl5Matcher _preparse_matcher_;
    private Pattern _preparse_pattern_;

    /* loaded from: classes4.dex */
    private class NameVersion {
        String name;
        int versionNumber;

        NameVersion(String str, String str2) {
            this.name = str;
            this.versionNumber = Integer.parseInt(str2);
        }
    }

    public VMSVersioningFTPEntryParser() {
        this(null);
    }

    @Override // org.apache.commons.net.ftp.parser.VMSFTPEntryParser
    protected boolean isVersioning() {
        return true;
    }

    @Override // org.apache.commons.net.ftp.FTPFileEntryParserImpl, org.apache.commons.net.ftp.FTPFileEntryParser
    public List preParse(List list) {
        List preParse = super.preParse(list);
        HashMap hashMap = new HashMap();
        ListIterator listIterator = preParse.listIterator();
        while (listIterator.hasNext()) {
            if (this._preparse_matcher_.matches(((String) listIterator.next()).trim(), this._preparse_pattern_)) {
                MatchResult match = this._preparse_matcher_.getMatch();
                String group = match.group(1);
                NameVersion nameVersion = new NameVersion(group, match.group(2));
                NameVersion nameVersion2 = (NameVersion) hashMap.get(group);
                if (nameVersion2 != null && nameVersion.versionNumber < nameVersion2.versionNumber) {
                    listIterator.remove();
                } else {
                    hashMap.put(group, nameVersion);
                }
            }
        }
        while (listIterator.hasPrevious()) {
            if (this._preparse_matcher_.matches(((String) listIterator.previous()).trim(), this._preparse_pattern_)) {
                MatchResult match2 = this._preparse_matcher_.getMatch();
                String group2 = match2.group(1);
                NameVersion nameVersion3 = new NameVersion(group2, match2.group(2));
                NameVersion nameVersion4 = (NameVersion) hashMap.get(group2);
                if (nameVersion4 != null && nameVersion3.versionNumber < nameVersion4.versionNumber) {
                    listIterator.remove();
                }
            }
        }
        return preParse;
    }

    public VMSVersioningFTPEntryParser(FTPClientConfig fTPClientConfig) {
        configure(fTPClientConfig);
        try {
            this._preparse_matcher_ = new Perl5Matcher();
            this._preparse_pattern_ = new Perl5Compiler().compile(PRE_PARSE_REGEX);
        } catch (MalformedPatternException unused) {
            throw new IllegalArgumentException("Unparseable regex supplied:  (.*);([0-9]+)\\s*.*");
        }
    }
}
