package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel;
/* loaded from: classes10.dex */
public class BorderedTextButtonModel extends TextButtonModel {
    public static final String BORDERED_TEXT_BUTTON_TYPE = "BorderedTextButtonView";

    /* loaded from: classes10.dex */
    public static class Builder extends TextButtonModel.Builder {
        @Override // com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel.Builder
        @NonNull
        /* renamed from: build */
        public BorderedTextButtonModel mo2376build() {
            return new BorderedTextButtonModel(this);
        }
    }

    public BorderedTextButtonModel(@NonNull Builder builder) {
        super(builder);
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.TextButtonModel, com.amazon.alexa.redesign.entity.viewtypes.ButtonModel, com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return BORDERED_TEXT_BUTTON_TYPE;
    }
}
