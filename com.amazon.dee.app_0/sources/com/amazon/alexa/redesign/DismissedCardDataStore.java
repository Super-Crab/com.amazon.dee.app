package com.amazon.alexa.redesign;

import com.amazon.alexa.redesign.entity.DismissIdentifier;
import java.util.List;
/* loaded from: classes10.dex */
public interface DismissedCardDataStore {
    void clearDismissedCardDataStore();

    void deleteDismissedCardIds(DismissIdentifier dismissIdentifier, String str);

    List<DismissIdentifier> getDismissedCardIds(String str);

    void putDismissedCardIds(DismissIdentifier dismissIdentifier, String str);
}
