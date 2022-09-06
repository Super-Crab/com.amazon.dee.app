package com.amazon.alexa.redesign.actions;

import com.amazon.alexa.redesign.entity.viewtypes.OptInBottomSheetModel;
import com.amazon.alexa.redesign.entity.viewtypes.ViewTypeModel;
import com.amazon.alexa.redesign.view.viewtypes.OptInBottomSheetView;
import java.util.Map;
/* loaded from: classes10.dex */
public class BottomSheetAction extends Action {
    private static final String TAG = "BottomSheetAction";
    private ViewTypeModel bottomSheetModel;

    public BottomSheetAction(String str, String str2, ViewTypeModel viewTypeModel) {
        super("BottomSheetAction", str, str2);
        this.bottomSheetModel = viewTypeModel;
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        ViewTypeModel viewTypeModel = this.bottomSheetModel;
        if (viewTypeModel instanceof OptInBottomSheetModel) {
            this.presenter.displayBottomSheet(new OptInBottomSheetView(this.presenter, viewTypeModel, getMetricData(), this.accessibilityLabel));
        }
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(Map<String, Object> map) {
    }
}
