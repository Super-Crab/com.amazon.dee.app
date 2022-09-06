package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import javax.mail.FetchProfile;
/* loaded from: classes3.dex */
public abstract class FetchItem {
    private FetchProfile.Item fetchProfileItem;
    private String name;

    public FetchItem(String str, FetchProfile.Item item) {
        this.name = str;
        this.fetchProfileItem = item;
    }

    public FetchProfile.Item getFetchProfileItem() {
        return this.fetchProfileItem;
    }

    public String getName() {
        return this.name;
    }

    public abstract Object parseItem(FetchResponse fetchResponse) throws ParsingException;
}
