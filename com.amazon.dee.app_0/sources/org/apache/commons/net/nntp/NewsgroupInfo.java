package org.apache.commons.net.nntp;
/* loaded from: classes4.dex */
public final class NewsgroupInfo {
    public static final int MODERATED_POSTING_PERMISSION = 1;
    public static final int PERMITTED_POSTING_PERMISSION = 2;
    public static final int PROHIBITED_POSTING_PERMISSION = 3;
    public static final int UNKNOWN_POSTING_PERMISSION = 0;
    private int __estimatedArticleCount;
    private int __firstArticle;
    private int __lastArticle;
    private String __newsgroup;
    private int __postingPermission;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setArticleCount(int i) {
        this.__estimatedArticleCount = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setFirstArticle(int i) {
        this.__firstArticle = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setLastArticle(int i) {
        this.__lastArticle = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setNewsgroup(String str) {
        this.__newsgroup = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _setPostingPermission(int i) {
        this.__postingPermission = i;
    }

    public int getArticleCount() {
        return this.__estimatedArticleCount;
    }

    public int getFirstArticle() {
        return this.__firstArticle;
    }

    public int getLastArticle() {
        return this.__lastArticle;
    }

    public String getNewsgroup() {
        return this.__newsgroup;
    }

    public int getPostingPermission() {
        return this.__postingPermission;
    }
}
