package com.fasterxml.jackson.core;

import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class TreeCodec {
    /* renamed from: createArrayNode */
    public abstract TreeNode mo7040createArrayNode();

    /* renamed from: createObjectNode */
    public abstract TreeNode mo7041createObjectNode();

    /* renamed from: missingNode */
    public TreeNode mo7042missingNode() {
        return null;
    }

    /* renamed from: nullNode */
    public TreeNode mo7043nullNode() {
        return null;
    }

    public abstract <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException, JsonProcessingException;

    public abstract JsonParser treeAsTokens(TreeNode treeNode);

    public abstract void writeTree(JsonGenerator jsonGenerator, TreeNode treeNode) throws IOException, JsonProcessingException;
}
