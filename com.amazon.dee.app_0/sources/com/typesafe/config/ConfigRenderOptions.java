package com.typesafe.config;
/* loaded from: classes3.dex */
public final class ConfigRenderOptions {
    private final boolean comments;
    private final boolean formatted;
    private final boolean json;
    private final boolean originComments;

    private ConfigRenderOptions(boolean z, boolean z2, boolean z3, boolean z4) {
        this.originComments = z;
        this.comments = z2;
        this.formatted = z3;
        this.json = z4;
    }

    public static ConfigRenderOptions concise() {
        return new ConfigRenderOptions(false, false, false, true);
    }

    public static ConfigRenderOptions defaults() {
        return new ConfigRenderOptions(true, true, true, true);
    }

    public boolean getComments() {
        return this.comments;
    }

    public boolean getFormatted() {
        return this.formatted;
    }

    public boolean getJson() {
        return this.json;
    }

    public boolean getOriginComments() {
        return this.originComments;
    }

    public ConfigRenderOptions setComments(boolean z) {
        return z == this.comments ? this : new ConfigRenderOptions(this.originComments, z, this.formatted, this.json);
    }

    public ConfigRenderOptions setFormatted(boolean z) {
        return z == this.formatted ? this : new ConfigRenderOptions(this.originComments, this.comments, z, this.json);
    }

    public ConfigRenderOptions setJson(boolean z) {
        return z == this.json ? this : new ConfigRenderOptions(this.originComments, this.comments, this.formatted, z);
    }

    public ConfigRenderOptions setOriginComments(boolean z) {
        return z == this.originComments ? this : new ConfigRenderOptions(z, this.comments, this.formatted, this.json);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ConfigRenderOptions(");
        if (this.originComments) {
            sb.append("originComments,");
        }
        if (this.comments) {
            sb.append("comments,");
        }
        if (this.formatted) {
            sb.append("formatted,");
        }
        if (this.json) {
            sb.append("json,");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setLength(sb.length() - 1);
        }
        sb.append(")");
        return sb.toString();
    }
}
