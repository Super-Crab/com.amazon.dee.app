package com.amazon.alexa.voice.ui.onedesign.empty;
/* loaded from: classes11.dex */
public enum EmptiableCardType {
    CALENDAR_NOT_LINKED("calendar_not_linked"),
    NO_CALENDAR_EVENT("no_calendar_event"),
    SHOPPING("shopping"),
    TODOS("todo"),
    UNKNOWN("unknown");
    
    private final String cardTypeAsString;

    EmptiableCardType(String str) {
        this.cardTypeAsString = str;
    }

    public static EmptiableCardType from(String str) {
        EmptiableCardType[] values;
        for (EmptiableCardType emptiableCardType : values()) {
            if (emptiableCardType.cardTypeAsString.equals(str)) {
                return emptiableCardType;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.cardTypeAsString;
    }
}
