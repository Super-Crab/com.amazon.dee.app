package org.apache.commons.net.nntp;

import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
/* loaded from: classes4.dex */
public class Article implements Threadable {
    private String articleId;
    private int articleNumber;
    private String date;
    private String from;
    public Article kid;
    public Article next;
    private StringBuffer references;
    private String simplifiedSubject;
    private String subject;
    private boolean isReply = false;
    private StringBuffer header = new StringBuffer();

    private void flushSubjectCache() {
        this.simplifiedSubject = null;
    }

    public static void printThread(Article article, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            System.out.print("==>");
        }
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(article.getSubject());
        stringBuffer.append(DeviceDatabaseUtils.DELIMITER);
        stringBuffer.append(article.getFrom());
        printStream.println(stringBuffer.toString());
        Article article2 = article.kid;
        if (article2 != null) {
            printThread(article2, i + 1);
        }
        Article article3 = article.next;
        if (article3 != null) {
            printThread(article3, i);
        }
    }

    private void simplifySubject() {
        boolean z;
        String subject = getSubject();
        int length = subject.length();
        int i = 0;
        for (boolean z2 = false; !z2; z2 = z) {
            while (i < length && subject.charAt(i) == ' ') {
                i++;
            }
            int i2 = length - 2;
            z = true;
            if (i < i2 && (subject.charAt(i) == 'r' || subject.charAt(i) == 'R')) {
                int i3 = i + 1;
                if (subject.charAt(i3) == 'e' || subject.charAt(i3) == 'E') {
                    int i4 = i + 2;
                    if (subject.charAt(i4) == ':') {
                        i += 3;
                        this.isReply = true;
                        z = false;
                    } else if (i < i2 && (subject.charAt(i4) == '[' || subject.charAt(i4) == '(')) {
                        int i5 = i + 3;
                        while (i5 < length && subject.charAt(i5) >= '0' && subject.charAt(i5) <= '9') {
                            i5++;
                        }
                        if (i5 < length - 1 && ((subject.charAt(i5) == ']' || subject.charAt(i5) == ')') && subject.charAt(i5 + 1) == ':')) {
                            this.isReply = true;
                            z = false;
                            i = i5 + 2;
                        }
                    }
                }
            }
            if (this.simplifiedSubject == "(no subject)") {
                this.simplifiedSubject = "";
            }
            int i6 = length;
            while (i6 > i && subject.charAt(i6 - 1) < ' ') {
                i6--;
            }
            if (i == 0 && i6 == length) {
                this.simplifiedSubject = subject;
            } else {
                this.simplifiedSubject = subject.substring(i, i6);
            }
        }
    }

    public void addHeaderField(String str, String str2) {
        this.header.append(str);
        this.header.append(RealTimeTextConstants.COLON_SPACE);
        this.header.append(str2);
        this.header.append('\n');
    }

    public void addReference(String str) {
        if (this.references == null) {
            this.references = new StringBuffer();
            this.references.append("References: ");
        }
        this.references.append(str);
        this.references.append(DeviceDatabaseUtils.DELIMITER);
    }

    public String getArticleId() {
        return this.articleId;
    }

    public int getArticleNumber() {
        return this.articleNumber;
    }

    public String getDate() {
        return this.date;
    }

    public String getFrom() {
        return this.from;
    }

    public String[] getReferences() {
        if (this.references == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(this.references.substring(this.references.toString().indexOf(58)), DeviceDatabaseUtils.DELIMITER);
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray();
    }

    public String getSubject() {
        return this.subject;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public boolean isDummy() {
        return getSubject() == null;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public Threadable makeDummy() {
        return new Article();
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public String messageThreadId() {
        return this.articleId;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public String[] messageThreadReferences() {
        return getReferences();
    }

    public void setArticleId(String str) {
        this.articleId = str;
    }

    public void setArticleNumber(int i) {
        this.articleNumber = i;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public void setChild(Threadable threadable) {
        this.kid = (Article) threadable;
        flushSubjectCache();
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public void setNext(Threadable threadable) {
        this.next = (Article) threadable;
        flushSubjectCache();
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public String simplifiedSubject() {
        if (this.simplifiedSubject == null) {
            simplifySubject();
        }
        return this.simplifiedSubject;
    }

    @Override // org.apache.commons.net.nntp.Threadable
    public boolean subjectIsReply() {
        if (this.simplifiedSubject == null) {
            simplifySubject();
        }
        return this.isReply;
    }
}
