package com.amazon.alexa.redesign.entity.ranking;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.gson.Gson;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes10.dex */
public class RankingRules {
    private final HashMap<String, Rule> rules = new HashMap<>();

    public RankingRules(@Nullable JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        Gson gson = new Gson();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                Rule rule = (Rule) gson.fromJson(jSONArray.getJSONObject(i).toString(), (Class<Object>) Rule.class);
                rule.setIndex(i);
                this.rules.put(rule.getContentProvider() + rule.getContentType(), rule);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isAfter(Rule rule, Rule rule2) throws IllegalArgumentException {
        if (rule2 == null || rule == null) {
            throw new IllegalArgumentException("Could not find rule.");
        }
        return rule.getIndex() > rule2.getIndex();
    }

    @Nullable
    public Rule getRule(String str, String str2) {
        HashMap<String, Rule> hashMap = this.rules;
        return hashMap.get(str + str2);
    }

    @VisibleForTesting
    HashMap getRules() {
        return this.rules;
    }
}
