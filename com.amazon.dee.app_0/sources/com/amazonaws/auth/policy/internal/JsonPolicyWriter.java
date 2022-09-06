package com.amazonaws.auth.policy.internal;

import com.amazonaws.auth.policy.Action;
import com.amazonaws.auth.policy.Condition;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes13.dex */
public class JsonPolicyWriter {
    private static final Log log = LogFactory.getLog("com.amazonaws.auth.policy");
    private AwsJsonWriter jsonWriter;
    private final Writer writer = new StringWriter();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class ConditionsByKey {
        private Map<String, List<String>> conditionsByKey = new HashMap();

        public void addValuesToKey(String str, List<String> list) {
            List<String> conditionsByKey = getConditionsByKey(str);
            if (conditionsByKey == null) {
                this.conditionsByKey.put(str, new ArrayList(list));
            } else {
                conditionsByKey.addAll(list);
            }
        }

        public boolean containsKey(String str) {
            return this.conditionsByKey.containsKey(str);
        }

        public Map<String, List<String>> getConditionsByKey() {
            return this.conditionsByKey;
        }

        public Set<String> keySet() {
            return this.conditionsByKey.keySet();
        }

        public void setConditionsByKey(Map<String, List<String>> map) {
            this.conditionsByKey = map;
        }

        public List<String> getConditionsByKey(String str) {
            return this.conditionsByKey.get(str);
        }
    }

    public JsonPolicyWriter() {
        this.jsonWriter = null;
        this.jsonWriter = JsonUtils.getJsonWriter(this.writer);
    }

    private Map<String, ConditionsByKey> groupConditionsByTypeAndKey(List<Condition> list) {
        HashMap hashMap = new HashMap();
        for (Condition condition : list) {
            String type = condition.getType();
            String conditionKey = condition.getConditionKey();
            if (!hashMap.containsKey(type)) {
                hashMap.put(type, new ConditionsByKey());
            }
            ((ConditionsByKey) hashMap.get(type)).addValuesToKey(conditionKey, condition.getValues());
        }
        return hashMap;
    }

    private Map<String, List<String>> groupPrincipalByScheme(List<Principal> list) {
        HashMap hashMap = new HashMap();
        for (Principal principal : list) {
            String provider = principal.getProvider();
            if (!hashMap.containsKey(provider)) {
                hashMap.put(provider, new ArrayList());
            }
            ((List) hashMap.get(provider)).add(principal.getId());
        }
        return hashMap;
    }

    private boolean isNotNull(Object obj) {
        return obj != null;
    }

    private String jsonStringOf(Policy policy) throws IOException {
        this.jsonWriter.beginObject();
        writeJsonKeyValue("Version", policy.getVersion());
        if (isNotNull(policy.getId())) {
            writeJsonKeyValue("Id", policy.getId());
        }
        writeJsonArrayStart(JsonDocumentFields.STATEMENT);
        for (Statement statement : policy.getStatements()) {
            this.jsonWriter.beginObject();
            if (isNotNull(statement.getId())) {
                writeJsonKeyValue(JsonDocumentFields.STATEMENT_ID, statement.getId());
            }
            writeJsonKeyValue(JsonDocumentFields.STATEMENT_EFFECT, statement.getEffect().toString());
            List<Principal> principals = statement.getPrincipals();
            if (isNotNull(principals) && !principals.isEmpty()) {
                writePrincipals(principals);
            }
            List<Action> actions = statement.getActions();
            if (isNotNull(actions) && !actions.isEmpty()) {
                writeActions(actions);
            }
            List<Resource> resources = statement.getResources();
            if (isNotNull(resources) && !resources.isEmpty()) {
                writeResources(resources);
            }
            List<Condition> conditions = statement.getConditions();
            if (isNotNull(conditions) && !conditions.isEmpty()) {
                writeConditions(conditions);
            }
            this.jsonWriter.endObject();
        }
        writeJsonArrayEnd();
        this.jsonWriter.endObject();
        this.jsonWriter.flush();
        return this.writer.toString();
    }

    private void writeActions(List<Action> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (Action action : list) {
            arrayList.add(action.getActionName());
        }
        writeJsonArray("Action", arrayList);
    }

    private void writeConditions(List<Condition> list) throws IOException {
        Map<String, ConditionsByKey> groupConditionsByTypeAndKey = groupConditionsByTypeAndKey(list);
        writeJsonObjectStart(JsonDocumentFields.CONDITION);
        for (Map.Entry<String, ConditionsByKey> entry : groupConditionsByTypeAndKey.entrySet()) {
            ConditionsByKey conditionsByKey = groupConditionsByTypeAndKey.get(entry.getKey());
            writeJsonObjectStart(entry.getKey());
            for (String str : conditionsByKey.keySet()) {
                writeJsonArray(str, conditionsByKey.getConditionsByKey(str));
            }
            writeJsonObjectEnd();
        }
        writeJsonObjectEnd();
    }

    private void writeJsonArray(String str, List<String> list) throws IOException {
        writeJsonArrayStart(str);
        for (String str2 : list) {
            this.jsonWriter.value(str2);
        }
        writeJsonArrayEnd();
    }

    private void writeJsonArrayEnd() throws IOException {
        this.jsonWriter.endArray();
    }

    private void writeJsonArrayStart(String str) throws IOException {
        this.jsonWriter.name(str);
        this.jsonWriter.beginArray();
    }

    private void writeJsonKeyValue(String str, String str2) throws IOException {
        this.jsonWriter.name(str);
        this.jsonWriter.value(str2);
    }

    private void writeJsonObjectEnd() throws IOException {
        this.jsonWriter.endObject();
    }

    private void writeJsonObjectStart(String str) throws IOException {
        this.jsonWriter.name(str);
        this.jsonWriter.beginObject();
    }

    private void writePrincipals(List<Principal> list) throws IOException {
        if (list.size() == 1 && list.get(0).equals(Principal.All)) {
            writeJsonKeyValue(JsonDocumentFields.PRINCIPAL, Principal.All.getId());
            return;
        }
        writeJsonObjectStart(JsonDocumentFields.PRINCIPAL);
        Map<String, List<String>> groupPrincipalByScheme = groupPrincipalByScheme(list);
        for (Map.Entry<String, List<String>> entry : groupPrincipalByScheme.entrySet()) {
            List<String> list2 = groupPrincipalByScheme.get(entry.getKey());
            if (list2.size() == 1) {
                writeJsonKeyValue(entry.getKey(), list2.get(0));
            } else {
                writeJsonArray(entry.getKey(), list2);
            }
        }
        writeJsonObjectEnd();
    }

    private void writeResources(List<Resource> list) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (Resource resource : list) {
            arrayList.add(resource.getId());
        }
        writeJsonArray(JsonDocumentFields.RESOURCE, arrayList);
    }

    public String writePolicyToString(Policy policy) {
        try {
            if (isNotNull(policy)) {
                try {
                    String jsonStringOf = jsonStringOf(policy);
                    try {
                        this.writer.close();
                    } catch (Exception unused) {
                    }
                    return jsonStringOf;
                } catch (Exception e) {
                    throw new IllegalArgumentException("Unable to serialize policy to JSON string: " + e.getMessage(), e);
                }
            }
            throw new IllegalArgumentException("Policy cannot be null");
        } catch (Throwable th) {
            try {
                this.writer.close();
            } catch (Exception unused2) {
            }
            throw th;
        }
    }
}
