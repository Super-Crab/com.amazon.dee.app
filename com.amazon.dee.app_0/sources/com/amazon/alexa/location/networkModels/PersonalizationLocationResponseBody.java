package com.amazon.alexa.location.networkModels;

import com.amazon.alexa.location.models.PersonalizationLocationItem;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class PersonalizationLocationResponseBody {
    private List<PersonalizationLocationItem> location;

    public PersonalizationLocationResponseBody(List<PersonalizationLocationItem> list) {
        this.location = new ArrayList();
        this.location = list;
    }

    public List<PersonalizationLocationItem> getPersonalizationLocationItems() {
        return this.location;
    }
}
