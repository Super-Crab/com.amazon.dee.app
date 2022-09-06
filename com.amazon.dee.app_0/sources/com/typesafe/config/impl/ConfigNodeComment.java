package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNodeComment extends ConfigNodeSingleToken {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeComment(Token token) {
        super(token);
        if (Tokens.isComment(this.token)) {
            return;
        }
        throw new ConfigException.BugOrBroken("Tried to create a ConfigNodeComment from a non-comment token");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String commentText() {
        return Tokens.getCommentText(this.token);
    }
}
