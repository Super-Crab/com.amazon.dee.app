package com.amazon.dee.app.services.toolbar;

import java.util.Arrays;
import java.util.Collections;
/* loaded from: classes12.dex */
class VisibilityOptions {
    private final String[] blacklist;
    private final Boolean rootRoutesOnly;
    private final String[] whitelist;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private Boolean rootRoutesOnly = null;
        private String[] blacklist = null;
        private String[] whitelist = null;

        public VisibilityOptions build() {
            return new VisibilityOptions(this);
        }

        public Builder withBlacklist(String[] strArr) {
            this.blacklist = strArr;
            return this;
        }

        public Builder withRootRoutesOnly(boolean z) {
            this.rootRoutesOnly = Boolean.valueOf(z);
            return this;
        }

        public Builder withWhitelist(String[] strArr) {
            this.whitelist = strArr;
            return this;
        }
    }

    VisibilityOptions(Builder builder) {
        this.rootRoutesOnly = builder.rootRoutesOnly;
        this.blacklist = builder.blacklist;
        this.whitelist = builder.whitelist;
    }

    public Iterable<String> getBlacklist() {
        String[] strArr = this.blacklist;
        return strArr == null ? Collections.emptyList() : Arrays.asList(strArr);
    }

    public Boolean getRootRoutesOnly() {
        return this.rootRoutesOnly;
    }

    public Iterable<String> getWhitelist() {
        String[] strArr = this.whitelist;
        return strArr == null ? Collections.emptyList() : Arrays.asList(strArr);
    }

    public boolean hasBlacklist() {
        return this.blacklist != null;
    }

    public boolean hasWhitelist() {
        return this.whitelist != null;
    }
}
