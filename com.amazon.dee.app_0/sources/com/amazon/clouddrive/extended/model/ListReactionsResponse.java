package com.amazon.clouddrive.extended.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class ListReactionsResponse {
    private final int count;
    private final String nextToken;
    private final List<Reaction> reactions;

    /* loaded from: classes11.dex */
    public static class Builder {
        private Integer count;
        private List<Reaction> reactions;
        private String token;

        public ListReactionsResponse build() {
            return new ListReactionsResponse(this.reactions, this.token, this.count.intValue());
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Builder{token='");
            GeneratedOutlineSupport1.outline176(outline107, this.token, Chars.QUOTE, ", reactions=");
            outline107.append(this.reactions);
            outline107.append(", count=");
            outline107.append(this.count);
            outline107.append(JsonReaderKt.END_OBJ);
            return outline107.toString();
        }

        public Builder withCount(Integer num) {
            this.count = num;
            return this;
        }

        public Builder withReactions(List<Reaction> list) {
            this.reactions = list;
            return this;
        }

        public Builder withToken(String str) {
            this.token = str;
            return this;
        }
    }

    public ListReactionsResponse(List<Reaction> list, String str, int i) {
        this.nextToken = str;
        this.reactions = list;
        this.count = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListReactionsResponse)) {
            return false;
        }
        ListReactionsResponse listReactionsResponse = (ListReactionsResponse) obj;
        if (getCount() != null ? getCount().equals(listReactionsResponse.getCount()) : listReactionsResponse.getCount() == null) {
            String str = this.nextToken;
            if (str == null ? listReactionsResponse.nextToken == null : str.equals(listReactionsResponse.nextToken)) {
                if (getReactions() != null) {
                    if (getReactions().equals(listReactionsResponse.getReactions())) {
                        return true;
                    }
                } else if (listReactionsResponse.getReactions() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer getCount() {
        return Integer.valueOf(this.count);
    }

    public List<Reaction> getReactions() {
        return this.reactions;
    }

    public String getToken() {
        return this.nextToken;
    }

    public int hashCode() {
        String str = this.nextToken;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        if (getReactions() != null) {
            i = getReactions().hashCode();
        }
        return getCount().intValue() + ((hashCode + i) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListReactionsResponse{nextToken='");
        GeneratedOutlineSupport1.outline176(outline107, this.nextToken, Chars.QUOTE, ", reactions=");
        outline107.append(this.reactions);
        outline107.append(", count=");
        return GeneratedOutlineSupport1.outline85(outline107, this.count, JsonReaderKt.END_OBJ);
    }
}
