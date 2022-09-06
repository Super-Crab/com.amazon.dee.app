package com.amazon.alexa.redesign.view.carousel;
/* loaded from: classes10.dex */
public enum CarouselItemType {
    ChipIconTitle(1),
    ChipTitle(2),
    GridIconTitle(3),
    UNKNOWN(-1);
    
    private final int carouselType;

    CarouselItemType(int i) {
        this.carouselType = i;
    }

    public static CarouselItemType from(int i) {
        CarouselItemType[] values;
        for (CarouselItemType carouselItemType : values()) {
            if (carouselItemType.carouselType == i) {
                return carouselItemType;
            }
        }
        return UNKNOWN;
    }

    public int getCarouselTypeIntValue() {
        return this.carouselType;
    }
}
