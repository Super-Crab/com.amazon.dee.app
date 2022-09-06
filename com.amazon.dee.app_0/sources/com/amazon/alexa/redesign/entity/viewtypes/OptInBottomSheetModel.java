package com.amazon.alexa.redesign.entity.viewtypes;

import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.actions.Action;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class OptInBottomSheetModel extends ViewTypeModel {
    public static final String OPT_IN_BOTTOM_SHEET_TYPE = "OptInBottomSheetView";
    private final String bodyText;
    private final String bottomSheetId;
    private final List<ViewTypeModel> buttons;
    private final String header;
    private final String imageUrl;
    private final String subHeader;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String bodyText;
        private String bottomSheetId;
        private List<ViewTypeModel> buttons;
        private String header;
        private String imageUrl;
        private String subHeader;
        private int viewPosition;

        @NonNull
        public OptInBottomSheetModel build() {
            return new OptInBottomSheetModel(this);
        }

        @NonNull
        public Builder withBodyText(@NonNull String str) {
            this.bodyText = str;
            return this;
        }

        @NonNull
        public Builder withBottomSheetId(String str) {
            this.bottomSheetId = str;
            return this;
        }

        @NonNull
        public Builder withButtons(@NonNull List<ViewTypeModel> list) {
            this.buttons = list;
            return this;
        }

        @NonNull
        public Builder withHeader(@NonNull String str) {
            this.header = str;
            return this;
        }

        @NonNull
        public Builder withImageUrl(@NonNull String str) {
            this.imageUrl = str;
            return this;
        }

        @NonNull
        public Builder withSubHeader(@NonNull String str) {
            this.subHeader = str;
            return this;
        }

        @NonNull
        public Builder withViewPosition(int i) {
            this.viewPosition = i;
            return this;
        }
    }

    public OptInBottomSheetModel(Builder builder) {
        super(builder.viewPosition);
        this.imageUrl = builder.imageUrl;
        this.header = builder.header;
        this.subHeader = builder.subHeader;
        this.bodyText = builder.bodyText;
        this.bottomSheetId = builder.bottomSheetId;
        this.buttons = builder.buttons;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Action getAction() {
        return null;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public Map<String, Object> getActionMetricsData() {
        return null;
    }

    @NonNull
    public String getBodyText() {
        return this.bodyText;
    }

    public String getBottomSheetId() {
        return this.bottomSheetId;
    }

    @NonNull
    public List<ViewTypeModel> getButtons() {
        return this.buttons;
    }

    @NonNull
    public String getHeader() {
        return this.header;
    }

    @NonNull
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel
    public String getModelType() {
        return OPT_IN_BOTTOM_SHEET_TYPE;
    }

    @NonNull
    public String getSubHeader() {
        return this.subHeader;
    }
}
