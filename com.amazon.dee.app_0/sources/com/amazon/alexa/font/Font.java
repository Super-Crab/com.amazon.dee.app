package com.amazon.alexa.font;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public enum Font {
    AMAZON_BOOKERLY_BOLD(0),
    AMAZON_BOOKERLY_BOLD_ITALIC(1),
    AMAZON_BOOKERLY_LIGHT(2),
    AMAZON_BOOKERLY_LIGHT_ITALIC(3),
    AMAZON_BOOKERLY_REGULAR(4),
    AMAZON_BOOKERLY_REGULAR_ITALIC(5),
    AMAZON_EMBER_BOLD(6),
    AMAZON_EMBER_BOLD_ITALIC(7),
    AMAZON_EMBER_DISPLAY_BOLD(8),
    AMAZON_EMBER_DISPLAY_LIGHT(9),
    AMAZON_EMBER_DISPLAY_MEDIUM(10),
    AMAZON_EMBER_DISPLAY_REGULAR(11),
    AMAZON_EMBER_LIGHT(12),
    AMAZON_EMBER_LIGHT_ITALIC(13),
    AMAZON_EMBER_MEDIUM(14),
    AMAZON_EMBER_MEDIUM_ITALIC(15),
    AMAZON_EMBER_REGULAR(16),
    AMAZON_EMBER_REGULAR_ITALIC(17),
    AMAZON_EMBER_THIN(18),
    AMAZON_EMBER_THIN_ITALIC(19);
    
    private final int value;

    Font(int i) {
        this.value = i;
    }

    @Nullable
    public static Font fromInt(@NonNull int i) {
        if (i >= values().length || i < 0) {
            return null;
        }
        return values()[i];
    }

    public int getValue() {
        return this.value;
    }
}
