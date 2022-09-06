package com.amazon.alexa.voice.ui.onedesign.empty;

import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class EmptyResourceBundleFactory implements Factory<EmptyResourceBundle, EmptiableCardType> {

    /* renamed from: com.amazon.alexa.voice.ui.onedesign.empty.EmptyResourceBundleFactory$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$onedesign$empty$EmptiableCardType = new int[EmptiableCardType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$empty$EmptiableCardType[EmptiableCardType.CALENDAR_NOT_LINKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$empty$EmptiableCardType[EmptiableCardType.NO_CALENDAR_EVENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$empty$EmptiableCardType[EmptiableCardType.SHOPPING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$empty$EmptiableCardType[EmptiableCardType.TODOS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.Factory
    public EmptyResourceBundle create(EmptiableCardType emptiableCardType) {
        int ordinal = emptiableCardType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new EmptyResourceBundle(R.string.voice_ui_od_calendar_title, R.drawable.ic_voice_ui_od_calendar_light, R.string.voice_ui_od_calendar_empty_no_events, R.string.voice_ui_od_calendar_empty_user_hint);
            }
            if (ordinal == 2) {
                return new EmptyResourceBundle(R.string.voice_ui_od_list_shopping_title, R.drawable.ic_od_lists_72dp, R.string.voice_ui_od_list_empty_no_items, R.string.voice_ui_od_list_shopping_empty_user_hint);
            }
            if (ordinal == 3) {
                return new EmptyResourceBundle(R.string.voice_ui_od_list_todo_title, R.drawable.ic_od_lists_72dp, R.string.voice_ui_od_list_empty_no_items, R.string.voice_ui_od_list_todo_empty_user_hint);
            }
            throw new IllegalStateException("Tried to create EmptyResourceBundle for unsupported card type: " + emptiableCardType);
        }
        return new EmptyResourceBundle(R.string.voice_ui_od_calendar_title, R.drawable.ic_voice_ui_od_calendar_light, R.string.voice_ui_od_calendar_no_linked_calendar, R.string.voice_ui_od_empty);
    }
}
