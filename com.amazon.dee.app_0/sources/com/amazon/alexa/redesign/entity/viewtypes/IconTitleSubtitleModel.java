package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class IconTitleSubtitleModel extends ViewTypeModel {
    public static final String ICON_TITLE_SUBTITLE_TYPE = "IconTitleSubtitleView";
    public static final String ICON_TITLE_TYPE = "IconTitleView";
    private final Action action;
    private final boolean bold;
    private final String iconUrl;
    private final String imageTheme;
    private final String imageType;
    private final boolean italicized;
    private final String subtitle;
    private final String tint;
    private final String title;

    /* loaded from: classes10.dex */
    public static class Builder {
        private boolean bold;
        private String iconUrl;
        private String imageTheme;
        private String imageType;
        private boolean italicized;
        private Action onClickAction;
        private String subtitle;
        private String tint;
        private String title;
        private int viewPosition;

        @NonNull
        public IconTitleSubtitleModel build() {
            return new IconTitleSubtitleModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
            return this;
        }

        @NonNull
        public Builder withBold(boolean z) {
            this.bold = z;
            return this;
        }

        @NonNull
        public Builder withIconUrl(@NonNull String str) {
            this.iconUrl = str;
            return this;
        }

        @NonNull
        public Builder withImageTheme(@NonNull String str) {
            this.imageTheme = str;
            return this;
        }

        @NonNull
        public Builder withImageType(@NonNull String str) {
            this.imageType = str;
            return this;
        }

        @NonNull
        public Builder withItalicized(boolean z) {
            this.italicized = z;
            return this;
        }

        @NonNull
        public Builder withSubtitle(@NonNull String str) {
            this.subtitle = str;
            return this;
        }

        @NonNull
        public Builder withTint(@NonNull String str) {
            this.tint = str;
            return this;
        }

        @NonNull
        public Builder withTitle(@NonNull String str) {
            this.title = str;
            return this;
        }

        @NonNull
        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public IconTitleSubtitleModel(Builder builder) {
        super(builder.viewPosition);
        this.iconUrl = builder.iconUrl;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.action = builder.onClickAction;
        this.imageType = builder.imageType;
        this.italicized = builder.italicized;
        this.tint = builder.tint;
        this.bold = builder.bold;
        this.imageTheme = builder.imageTheme;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return this.action.getMetricData();
    }

    public boolean getBold() {
        return this.bold;
    }

    @NonNull
    public String getIconUrl() {
        return this.iconUrl;
    }

    @NonNull
    public String getImageTheme() {
        return this.imageTheme;
    }

    @NonNull
    public String getImageType() {
        return this.imageType;
    }

    public boolean getItalicized() {
        return this.italicized;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return ICON_TITLE_SUBTITLE_TYPE;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    @NonNull
    public String getTint() {
        return this.tint;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }
}
