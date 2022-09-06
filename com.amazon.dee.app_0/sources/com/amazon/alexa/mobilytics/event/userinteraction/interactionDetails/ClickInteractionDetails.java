package com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public class ClickInteractionDetails implements InteractionDetails {
    private String actionType;
    private String elementType;
    private Integer index;
    private final String interactionType = "click";
    private Integer totalNumberOfItems;

    public ClickInteractionDetails(@Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable Integer num2) {
        this.elementType = str;
        this.actionType = str2;
        this.index = num;
        this.totalNumberOfItems = num2;
    }

    public String getActionType() {
        return this.actionType;
    }

    public String getElementType() {
        return this.elementType;
    }

    public Integer getIndex() {
        return this.index;
    }

    @Override // com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails
    public String getInteractionType() {
        return "click";
    }

    public Integer getTotalNumberOfItems() {
        return this.totalNumberOfItems;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public void setElementType(String str) {
        this.elementType = str;
    }

    public void setIndex(@Nullable Integer num) {
        this.index = num;
    }

    public void setTotalNumberOfItems(@Nullable Integer num) {
        this.totalNumberOfItems = num;
    }

    public ClickInteractionDetails(@Nullable String str, @Nullable String str2) {
        this.elementType = str;
        this.actionType = str2;
    }
}
