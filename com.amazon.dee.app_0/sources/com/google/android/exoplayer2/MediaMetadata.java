package com.google.android.exoplayer2;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.Util;
/* loaded from: classes2.dex */
public final class MediaMetadata {
    @Nullable
    public final String title;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @Nullable
        private String title;

        public MediaMetadata build() {
            return new MediaMetadata(this.title);
        }

        public Builder setTitle(@Nullable String str) {
            this.title = str;
            return this;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MediaMetadata.class == obj.getClass()) {
            return Util.areEqual(this.title, ((MediaMetadata) obj).title);
        }
        return false;
    }

    public int hashCode() {
        String str = this.title;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    private MediaMetadata(@Nullable String str) {
        this.title = str;
    }
}
