package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
import java.util.Map;
/* loaded from: classes10.dex */
public class ImageModel extends ViewTypeModel {
    public static final String IMAGE_MODEL_TYPE = "ImageView";
    private final Action action;
    private final String imageUri;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String imageUri;
        private Action onClickAction;
        private int viewPosition;

        @NonNull
        public ImageModel build() {
            return new ImageModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
            return this;
        }

        @NonNull
        public Builder withImageUri(@NonNull String str) {
            this.imageUri = str;
            return this;
        }

        @NonNull
        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public ImageModel(@NonNull Builder builder) {
        super(builder.viewPosition);
        this.imageUri = builder.imageUri;
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

    @NonNull
    public String getImageUri() {
        return this.imageUri;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return IMAGE_MODEL_TYPE;
    }
}
