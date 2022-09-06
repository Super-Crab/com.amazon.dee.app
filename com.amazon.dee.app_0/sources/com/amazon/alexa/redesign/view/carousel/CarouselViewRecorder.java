package com.amazon.alexa.redesign.view.carousel;

import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.entity.viewtypes.GridIconTitleModel;
import com.amazon.alexa.redesign.entity.viewtypes.chipIconTitle.ChipIconTitleModel;
import com.amazon.alexa.redesign.metrics.ViewRecorderViewApi;
import com.amazon.alexa.redesign.utils.ViewRecorder;
import java.util.Map;
/* loaded from: classes10.dex */
public class CarouselViewRecorder extends ViewRecorder {
    public CarouselViewRecorder(ViewRecorderViewApi viewRecorderViewApi, HomeContract.HomeMetricsRecorder homeMetricsRecorder) {
        super(viewRecorderViewApi, homeMetricsRecorder);
    }

    @Override // com.amazon.alexa.redesign.utils.ViewRecorder
    protected Map<String, Object> getTopLevelMetricsObject(Object obj) {
        return this.view.getTopLevelMetricsObject(obj);
    }

    @Override // com.amazon.alexa.redesign.utils.ViewRecorder
    protected String getUniqueStringFromCard(Object obj) {
        if (obj instanceof GridIconTitleModel) {
            return ((GridIconTitleModel) obj).getCarouselItemId();
        }
        return obj instanceof ChipIconTitleModel ? ((ChipIconTitleModel) obj).getCarouselItemId() : "";
    }
}
