package org.apache.commons.net.nntp;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.io.DotTerminatedMessageReader;
import org.apache.commons.net.io.DotTerminatedMessageWriter;
import org.apache.commons.net.io.Util;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes4.dex */
public class NNTPClient extends NNTP {
    private void __parseArticlePointer(String str, ArticlePointer articlePointer) throws MalformedServerReplyException {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (stringTokenizer.countTokens() >= 3) {
            stringTokenizer.nextToken();
            try {
                articlePointer.articleNumber = Integer.parseInt(stringTokenizer.nextToken());
                articlePointer.articleId = stringTokenizer.nextToken();
                return;
            } catch (NumberFormatException unused) {
            }
        }
        throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Could not parse article pointer.\nServer reply: ", str));
    }

    private void __parseGroupReply(String str, NewsgroupInfo newsgroupInfo) throws MalformedServerReplyException {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (stringTokenizer.countTokens() >= 5) {
            stringTokenizer.nextToken();
            String nextToken = stringTokenizer.nextToken();
            String nextToken2 = stringTokenizer.nextToken();
            String nextToken3 = stringTokenizer.nextToken();
            newsgroupInfo._setNewsgroup(stringTokenizer.nextToken());
            try {
                newsgroupInfo._setArticleCount(Integer.parseInt(nextToken));
                newsgroupInfo._setFirstArticle(Integer.parseInt(nextToken2));
                newsgroupInfo._setLastArticle(Integer.parseInt(nextToken3));
                newsgroupInfo._setPostingPermission(0);
                return;
            } catch (NumberFormatException unused) {
            }
        }
        throw new MalformedServerReplyException(GeneratedOutlineSupport1.outline71("Could not parse newsgroup info.\nServer reply: ", str));
    }

    private NewsgroupInfo __parseNewsgroupListEntry(String str) {
        NewsgroupInfo newsgroupInfo = new NewsgroupInfo();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        if (stringTokenizer.countTokens() < 4) {
            return null;
        }
        newsgroupInfo._setNewsgroup(stringTokenizer.nextToken());
        String nextToken = stringTokenizer.nextToken();
        String nextToken2 = stringTokenizer.nextToken();
        String nextToken3 = stringTokenizer.nextToken();
        try {
            int parseInt = Integer.parseInt(nextToken);
            int parseInt2 = Integer.parseInt(nextToken2);
            newsgroupInfo._setFirstArticle(parseInt2);
            newsgroupInfo._setLastArticle(parseInt);
            if (parseInt2 == 0 && parseInt == 0) {
                newsgroupInfo._setArticleCount(0);
            } else {
                newsgroupInfo._setArticleCount((parseInt - parseInt2) + 1);
            }
            char charAt = nextToken3.charAt(0);
            if (charAt != 'M') {
                if (charAt != 'N') {
                    if (charAt == 'Y' || charAt == 'y') {
                        newsgroupInfo._setPostingPermission(2);
                    } else if (charAt != 'm') {
                        if (charAt != 'n') {
                            newsgroupInfo._setPostingPermission(0);
                        }
                    }
                    return newsgroupInfo;
                }
                newsgroupInfo._setPostingPermission(3);
                return newsgroupInfo;
            }
            newsgroupInfo._setPostingPermission(1);
            return newsgroupInfo;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private NewsgroupInfo[] __readNewsgroupListing() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new DotTerminatedMessageReader(this._reader_));
        Vector vector = new Vector(2048);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                NewsgroupInfo __parseNewsgroupListEntry = __parseNewsgroupListEntry(readLine);
                if (__parseNewsgroupListEntry != null) {
                    vector.addElement(__parseNewsgroupListEntry);
                } else {
                    throw new MalformedServerReplyException(readLine);
                }
            } else {
                int size = vector.size();
                if (size < 1) {
                    return new NewsgroupInfo[0];
                }
                NewsgroupInfo[] newsgroupInfoArr = new NewsgroupInfo[size];
                vector.copyInto(newsgroupInfoArr);
                return newsgroupInfoArr;
            }
        }
    }

    private Reader __retrieve(int i, String str, ArticlePointer articlePointer) throws IOException {
        if (str != null) {
            if (!NNTPReply.isPositiveCompletion(sendCommand(i, str))) {
                return null;
            }
        } else if (!NNTPReply.isPositiveCompletion(sendCommand(i))) {
            return null;
        }
        if (articlePointer != null) {
            __parseArticlePointer(getReplyString(), articlePointer);
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    private Reader __retrieveArticleInfo(String str) throws IOException {
        if (!NNTPReply.isPositiveCompletion(xover(str))) {
            return null;
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    private Reader __retrieveHeader(String str, String str2) throws IOException {
        if (!NNTPReply.isPositiveCompletion(xhdr(str, str2))) {
            return null;
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    public boolean authenticate(String str, String str2) throws IOException {
        if (authinfoUser(str) == 381 && authinfoPass(str2) == 281) {
            this._isAllowedToPost = true;
            return true;
        }
        return false;
    }

    public boolean completePendingCommand() throws IOException {
        return NNTPReply.isPositiveCompletion(getReply());
    }

    public Writer forwardArticle(String str) throws IOException {
        if (!NNTPReply.isPositiveIntermediate(ihave(str))) {
            return null;
        }
        return new DotTerminatedMessageWriter(this._writer_);
    }

    public String listHelp() throws IOException {
        if (!NNTPReply.isInformational(help())) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        DotTerminatedMessageReader dotTerminatedMessageReader = new DotTerminatedMessageReader(this._reader_);
        Util.copyReader(dotTerminatedMessageReader, stringWriter);
        dotTerminatedMessageReader.close();
        stringWriter.close();
        return stringWriter.toString();
    }

    public String[] listNewNews(NewGroupsOrNewsQuery newGroupsOrNewsQuery) throws IOException {
        if (!NNTPReply.isPositiveCompletion(newnews(newGroupsOrNewsQuery.getNewsgroups(), newGroupsOrNewsQuery.getDate(), newGroupsOrNewsQuery.getTime(), newGroupsOrNewsQuery.isGMT(), newGroupsOrNewsQuery.getDistributions()))) {
            return null;
        }
        Vector vector = new Vector();
        BufferedReader bufferedReader = new BufferedReader(new DotTerminatedMessageReader(this._reader_));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            vector.addElement(readLine);
        }
        int size = vector.size();
        if (size < 1) {
            return new String[0];
        }
        String[] strArr = new String[size];
        vector.copyInto(strArr);
        return strArr;
    }

    public NewsgroupInfo[] listNewNewsgroups(NewGroupsOrNewsQuery newGroupsOrNewsQuery) throws IOException {
        if (!NNTPReply.isPositiveCompletion(newgroups(newGroupsOrNewsQuery.getDate(), newGroupsOrNewsQuery.getTime(), newGroupsOrNewsQuery.isGMT(), newGroupsOrNewsQuery.getDistributions()))) {
            return null;
        }
        return __readNewsgroupListing();
    }

    public NewsgroupInfo[] listNewsgroups() throws IOException {
        if (!NNTPReply.isPositiveCompletion(list())) {
            return null;
        }
        return __readNewsgroupListing();
    }

    public boolean logout() throws IOException {
        return NNTPReply.isPositiveCompletion(quit());
    }

    public Writer postArticle() throws IOException {
        if (!NNTPReply.isPositiveIntermediate(post())) {
            return null;
        }
        return new DotTerminatedMessageWriter(this._writer_);
    }

    public Reader retrieveArticle(String str, ArticlePointer articlePointer) throws IOException {
        return __retrieve(0, str, articlePointer);
    }

    public Reader retrieveArticleBody(String str, ArticlePointer articlePointer) throws IOException {
        return __retrieve(1, str, articlePointer);
    }

    public Reader retrieveArticleHeader(String str, ArticlePointer articlePointer) throws IOException {
        return __retrieve(3, str, articlePointer);
    }

    public Reader retrieveArticleInfo(int i) throws IOException {
        return __retrieveArticleInfo(Integer.toString(i));
    }

    public Reader retrieveHeader(String str, int i) throws IOException {
        return __retrieveHeader(str, Integer.toString(i));
    }

    public boolean selectArticle(String str, ArticlePointer articlePointer) throws IOException {
        if (str != null) {
            if (!NNTPReply.isPositiveCompletion(stat(str))) {
                return false;
            }
        } else if (!NNTPReply.isPositiveCompletion(stat())) {
            return false;
        }
        if (articlePointer != null) {
            __parseArticlePointer(getReplyString(), articlePointer);
            return true;
        }
        return true;
    }

    public boolean selectNewsgroup(String str, NewsgroupInfo newsgroupInfo) throws IOException {
        if (!NNTPReply.isPositiveCompletion(group(str))) {
            return false;
        }
        if (newsgroupInfo == null) {
            return true;
        }
        __parseGroupReply(getReplyString(), newsgroupInfo);
        return true;
    }

    public boolean selectNextArticle(ArticlePointer articlePointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(next())) {
            return false;
        }
        if (articlePointer == null) {
            return true;
        }
        __parseArticlePointer(getReplyString(), articlePointer);
        return true;
    }

    public boolean selectPreviousArticle(ArticlePointer articlePointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(last())) {
            return false;
        }
        if (articlePointer == null) {
            return true;
        }
        __parseArticlePointer(getReplyString(), articlePointer);
        return true;
    }

    public Reader retrieveArticle(String str) throws IOException {
        return retrieveArticle(str, (ArticlePointer) null);
    }

    public Reader retrieveArticleBody(String str) throws IOException {
        return retrieveArticleBody(str, (ArticlePointer) null);
    }

    public Reader retrieveArticleHeader(String str) throws IOException {
        return retrieveArticleHeader(str, (ArticlePointer) null);
    }

    public Reader retrieveArticleInfo(int i, int i2) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append(ProcessIdUtil.DEFAULT_PROCESSID);
        stringBuffer.append(i2);
        return __retrieveArticleInfo(new String(stringBuffer.toString()));
    }

    public Reader retrieveHeader(String str, int i, int i2) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append(ProcessIdUtil.DEFAULT_PROCESSID);
        stringBuffer.append(i2);
        return __retrieveHeader(str, new String(stringBuffer.toString()));
    }

    public NewsgroupInfo[] listNewsgroups(String str) throws IOException {
        if (!NNTPReply.isPositiveCompletion(listActive(str))) {
            return null;
        }
        return __readNewsgroupListing();
    }

    public Reader retrieveArticle() throws IOException {
        return retrieveArticle((String) null);
    }

    public Reader retrieveArticleBody() throws IOException {
        return retrieveArticleBody((String) null);
    }

    public Reader retrieveArticleHeader() throws IOException {
        return retrieveArticleHeader((String) null);
    }

    public boolean selectNewsgroup(String str) throws IOException {
        return selectNewsgroup(str, null);
    }

    public boolean selectNextArticle() throws IOException {
        return selectNextArticle(null);
    }

    public boolean selectPreviousArticle() throws IOException {
        return selectPreviousArticle(null);
    }

    public Reader retrieveArticle(int i, ArticlePointer articlePointer) throws IOException {
        return __retrieve(0, i, articlePointer);
    }

    public Reader retrieveArticleBody(int i, ArticlePointer articlePointer) throws IOException {
        return __retrieve(1, i, articlePointer);
    }

    public Reader retrieveArticleHeader(int i, ArticlePointer articlePointer) throws IOException {
        return __retrieve(3, i, articlePointer);
    }

    public boolean selectArticle(String str) throws IOException {
        return selectArticle(str, (ArticlePointer) null);
    }

    private Reader __retrieve(int i, int i2, ArticlePointer articlePointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(sendCommand(i, Integer.toString(i2)))) {
            return null;
        }
        if (articlePointer != null) {
            __parseArticlePointer(getReplyString(), articlePointer);
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    public Reader retrieveArticle(int i) throws IOException {
        return retrieveArticle(i, (ArticlePointer) null);
    }

    public Reader retrieveArticleBody(int i) throws IOException {
        return retrieveArticleBody(i, (ArticlePointer) null);
    }

    public Reader retrieveArticleHeader(int i) throws IOException {
        return retrieveArticleHeader(i, (ArticlePointer) null);
    }

    public boolean selectArticle(ArticlePointer articlePointer) throws IOException {
        return selectArticle((String) null, articlePointer);
    }

    public boolean selectArticle(int i, ArticlePointer articlePointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(stat(i))) {
            return false;
        }
        if (articlePointer == null) {
            return true;
        }
        __parseArticlePointer(getReplyString(), articlePointer);
        return true;
    }

    public boolean selectArticle(int i) throws IOException {
        return selectArticle(i, (ArticlePointer) null);
    }
}
