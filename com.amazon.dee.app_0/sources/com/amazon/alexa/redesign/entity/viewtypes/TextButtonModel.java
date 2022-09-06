package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
/* loaded from: classes10.dex */
public class TextButtonModel extends ButtonModel {
    public static final String TEXT_BUTTON_TYPE = "TextButtonView";
    @NonNull
    private final String buttonText;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String buttonText;
        private Action onClickAction;
        private int viewPosition;

        @NonNull
        /* renamed from: build */
        public TextButtonModel mo2376build() {
            return new TextButtonModel(this);
        }

        @NonNull
        public Builder withAction(@NonNull Action action) {
            this.onClickAction = action;
            return this;
        }

        @NonNull
        public Builder withButtonText(@NonNull String str) {
            this.buttonText = str;
            return this;
        }

        @NonNull
        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public TextButtonModel(@NonNull Builder builder) {
        super(builder.viewPosition, builder.onClickAction);
        this.buttonText = builder.buttonText;
    }

    @NonNull
    public String getButtonText() {
        return this.buttonText;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ButtonModel, com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return TEXT_BUTTON_TYPE;
    }
}
