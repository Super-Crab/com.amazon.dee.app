package com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public class ViewInteractionDetails implements InteractionDetails {
    private Long duration;
    private Integer index;
    private final String interactionType = "view";
    private Integer totalNumberOfItems;

    public ViewInteractionDetails(Integer num, Integer num2, Long l) {
        this.index = num;
        this.totalNumberOfItems = num2;
        this.duration = l;
    }

    @Nullable
    public Long getDuration() {
        return this.duration;
    }

    @Nullable
    public Integer getIndex() {
        return this.index;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails
    public String getInteractionType() {
        return "view";
    }

    @Nullable
    public Integer getTotalNumberOfItems() {
        return this.totalNumberOfItems;
    }

    public void setDuration(@Nullable Long l) {
        this.duration = l;
    }

    public void setIndex(@Nullable Integer num) {
        this.index = num;
    }

    public void setTotalNumberOfItems(@Nullable Integer num) {
        this.totalNumberOfItems = num;
    }
}
