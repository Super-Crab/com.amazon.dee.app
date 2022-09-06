package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class HeaderModel extends ViewTypeModel {
    public static final String HEADER_TYPE = "HeaderView";
    private final Action action;
    private final String title;

    /* loaded from: classes10.dex */
    public static class Builder {
        private Action onClickAction;
        private String title;
        private int viewPosition;

        @NonNull
        public HeaderModel build() {
            return new HeaderModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
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

    public HeaderModel(Builder builder) {
        super(builder.viewPosition);
        this.title = builder.title;
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
        return HEADER_TYPE;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }
}
