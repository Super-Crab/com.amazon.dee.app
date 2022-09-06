package com.amazon.alexa.redesign.entity.viewtypes;

import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class GridIconTitleModel extends ViewTypeModel {
    public static final String GRID_ICON_TITLE_TYPE = "GridIconTitleView";
    private final Action action;
    private final String carouselItemId;
    private final String carouselItemProvider;
    private final String carouselItemType;
    private final String imageType;
    private final String imageUrl;
    private final String title;

    /* loaded from: classes10.dex */
    public static class Builder {
        Action action;
        String carouselItemId;
        String carouselItemProvider;
        String carouselItemType;
        String imageType;
        String imageUrl;
        String title;
        int viewPosition;

        public GridIconTitleModel build() {
            return new GridIconTitleModel(this);
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

        public Builder withImageType(String str) {
            this.imageType = str;
            return this;
        }

        public Builder withImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
            return this;
        }

        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public GridIconTitleModel(Builder builder) {
        super(builder.viewPosition);
        this.title = builder.title;
        this.imageUrl = builder.imageUrl;
        this.imageType = builder.imageType;
        this.action = builder.action;
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

    public String getImageType() {
        return this.imageType;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return GRID_ICON_TITLE_TYPE;
    }

    public String getTitle() {
        return this.title;
    }
}
