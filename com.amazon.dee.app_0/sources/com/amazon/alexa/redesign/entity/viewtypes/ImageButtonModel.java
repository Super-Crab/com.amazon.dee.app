package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
/* loaded from: classes10.dex */
public class ImageButtonModel extends ButtonModel {
    public static final String IMAGE_BUTTON_TYPE = "ImageButtonView";
    @NonNull
    private final String imageButtonUrl;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String imageUrl;
        private Action onClickAction;
        private int viewPosition;

        @NonNull
        public ImageButtonModel build() {
            return new ImageButtonModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
            return this;
        }

        @NonNull
        public Builder withImageUrl(@NonNull String str) {
            this.imageUrl = str;
            return this;
        }

        @NonNull
        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public ImageButtonModel(Builder builder) {
        super(builder.viewPosition, builder.onClickAction);
        this.imageButtonUrl = builder.imageUrl;
    }

    public String getButtonImageURL() {
        return this.imageButtonUrl;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ButtonModel, com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return IMAGE_BUTTON_TYPE;
    }
}
