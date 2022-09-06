package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class TitleSubtitleModel extends ViewTypeModel {
    public static final String TITLE_SUBTITLE_TYPE = "TitleSubtitleView";
    private final Action action;
    private final String subtitle;
    private final String title;

    /* loaded from: classes10.dex */
    public static class Builder {
        private Action onClickAction;
        private String subtitle;
        private String title;
        private int viewPosition;

        @NonNull
        public TitleSubtitleModel build() {
            return new TitleSubtitleModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
            return this;
        }

        @NonNull
        public Builder withSubtitle(@NonNull String str) {
            this.subtitle = str;
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

    public TitleSubtitleModel(Builder builder) {
        super(builder.viewPosition);
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.action = builder.onClickAction;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return this.action.getMetricData();
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return TITLE_SUBTITLE_TYPE;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }
}
