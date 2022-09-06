package com.amazon.alexa.drive.navigation;

import com.amazon.alexa.drive.cards.Card;
import com.amazon.alexa.drive.navigation.SavedLocations;
/* loaded from: classes7.dex */
public class NavigationCard extends Card {
    private SavedLocations.Item item;

    public String getAddress() {
        return this.item.getAddress().getAddressLine1() + " " + this.item.getAddress().getCity() + " " + this.item.getAddress().getPostalCode();
    }

    public void setItem(SavedLocations.Item item) {
        this.item = item;
    }
}
