package com.amazon.alexa.redesign.metrics;

import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.ViewRecorder;
import com.amazon.alexa.redesign.view.homeFeed.RecyclerViewItem;
import java.util.Map;
/* loaded from: classes10.dex */
public class HomeFeedViewRecorder extends ViewRecorder {
    public HomeFeedViewRecorder(ViewRecorderViewApi viewRecorderViewApi, HomeContract.HomeMetricsRecorder homeMetricsRecorder) {
        super(viewRecorderViewApi, homeMetricsRecorder);
    }

    @Override // com.amazon.alexa.redesign.utils.ViewRecorder
    protected Map<String, Object> getTopLevelMetricsObject(Object obj) {
        return ((RecyclerViewItem) obj).getTopLevelMetricsObject();
    }

    @Override // com.amazon.alexa.redesign.utils.ViewRecorder
    protected String getUniqueStringFromCard(Object obj) {
        Map<String, Object> topLevelMetricsObject;
        StringBuilder sb = new StringBuilder();
        if (obj != null && (topLevelMetricsObject = getTopLevelMetricsObject(obj)) != null) {
            sb.append(HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "contentId"));
            sb.append(HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "contentType"));
            sb.append(HomeMetricsRecorder.getFieldForEvent(topLevelMetricsObject, "contentProvider"));
        }
        return sb.toString();
    }
}
