package com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle;

import androidx.annotation.Nullable;
import com.amazon.alexa.redesign.actions.Action;
/* loaded from: classes10.dex */
public final class ChipIconTitleState {
    @Nullable
    private final Action action;
    private final String imageType;
    private final String imageUrl;
    private final String state;
    private final String title;

    /* loaded from: classes10.dex */
    public static class Builder {
        @Nullable
        Action action;
        String imageType;
        String imageUrl;
        String state;
        String title;

        public ChipIconTitleState build() {
            return new ChipIconTitleState(this);
        }

        public Builder withAction(@Nullable Action action) {
            this.action = action;
            return this;
        }

        public Builder withImageType(String str) {
            this.imageType = str;
            return this;
        }

        public Builder withImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder withState(String str) {
            this.state = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
            return this;
        }
    }

    @Nullable
    public Action getAction() {
        return this.action;
    }

    public String getImageType() {
        return this.imageType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    private ChipIconTitleState(Builder builder) {
        this.title = builder.title;
        this.imageUrl = builder.imageUrl;
        this.imageType = builder.imageType;
        this.state = builder.state;
        this.action = builder.action;
    }
}
