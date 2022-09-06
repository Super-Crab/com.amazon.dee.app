package com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle;

import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class ChipIconTitleModel extends ViewTypeModel {
    public static final String CHIP_ICON_TITLE_TYPE = "ChipIconTitleView";
    public static final String CHIP_TITLE_TYPE = "ChipTitleView";
    private final Action action;
    private final String carouselItemId;
    private final String carouselItemProvider;
    private final String carouselItemType;
    private final boolean circular;
    private final List<ChipIconTitleState> states;

    /* loaded from: classes10.dex */
    public static class Builder {
        Action action;
        String carouselItemId;
        String carouselItemProvider;
        String carouselItemType;
        boolean circular;
        List<ChipIconTitleState> states;
        int viewPosition;

        public ChipIconTitleModel build() {
            return new ChipIconTitleModel(this);
        }

        public Builder withAction(Action action) {
            this.action = action;
            return this;
        }

        public Builder withCarouselItemId(String str) {
            this.carouselItemId = str;
            return this;
        }

        public Builder withCarouselItemProvider(String str) {
            this.carouselItemProvider = str;
            return this;
        }

        public Builder withCarouselItemType(String str) {
            this.carouselItemType = str;
            return this;
        }

        public Builder withCircular(boolean z) {
            this.circular = z;
            return this;
        }

        public Builder withStates(List<ChipIconTitleState> list) {
            this.states = list;
            return this;
        }

        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public ChipIconTitleModel(Builder builder) {
        super(builder.viewPosition);
        this.states = builder.states;
        this.action = builder.action;
        this.circular = builder.circular;
        this.carouselItemId = builder.carouselItemId;
        this.carouselItemType = builder.carouselItemType;
        this.carouselItemProvider = builder.carouselItemProvider;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Action getAction() {
        return this.action;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return this.action.getMetricData();
    }

    public String getCarouselItemId() {
        return this.carouselItemId;
    }

    public String getCarouselItemProvider() {
        return this.carouselItemProvider;
    }

    public String getCarouselItemType() {
        return this.carouselItemType;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return CHIP_ICON_TITLE_TYPE;
    }

    public List<ChipIconTitleState> getStates() {
        return this.states;
    }

    public boolean isCircular() {
        return this.circular;
    }
}
