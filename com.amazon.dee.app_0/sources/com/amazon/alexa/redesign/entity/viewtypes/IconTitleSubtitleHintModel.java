package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class IconTitleSubtitleHintModel extends ViewTypeModel {
    public static final String ICON_TITLE_SUBTITLE_HINT_TYPE = "IconTitleSubtitleHintView";
    private final Action action;
    private final String hint;
    private final String iconUrl;
    private final String imageTheme;
    private final String imageType;
    private final String subtitle;
    private final String tint;
    private final String title;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String hint;
        private String iconUrl;
        private String imageTheme;
        private String imageType;
        private Action onClickAction;
        private String subtitle;
        private String tint;
        private String title;
        private int viewPosition;

        @NonNull
        public IconTitleSubtitleHintModel build() {
            return new IconTitleSubtitleHintModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
            return this;
        }

        @NonNull
        public Builder withHint(@NonNull String str) {
            this.hint = str;
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

    public IconTitleSubtitleHintModel(Builder builder) {
        super(builder.viewPosition);
        this.iconUrl = builder.iconUrl;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.action = builder.onClickAction;
        this.imageType = builder.imageType;
        this.tint = builder.tint;
        this.imageTheme = builder.imageTheme;
        this.hint = builder.hint;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return this.action.getMetricData();
    }

    public String getHint() {
        return this.hint;
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

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return ICON_TITLE_SUBTITLE_HINT_TYPE;
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
