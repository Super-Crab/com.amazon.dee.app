package com.google.gson.extras.examples.rawcollections;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.PrintStream;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class RawCollectionsExample {

    /* loaded from: classes3.dex */
    static class Event {
        private String name;
        private String source;

        public String toString() {
            return String.format("(name=%s, source=%s)", this.name, this.source);
        }

        private Event(String str, String str2) {
            this.name = str;
            this.source = str2;
        }
    }

    public static void main(String[] strArr) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        arrayList.add("hello");
        arrayList.add(5);
        arrayList.add(new Event("GREETINGS", "guest"));
        String json = gson.toJson(arrayList);
        PrintStream printStream = System.out;
        printStream.println("Using Gson.toJson() on a raw collection: " + json);
        JsonArray asJsonArray = new JsonParser().parse(json).getAsJsonArray();
        System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", (String) gson.fromJson(asJsonArray.get(0), String.class), Integer.valueOf(((Integer) gson.fromJson(asJsonArray.get(1), (Class<Object>) Integer.TYPE)).intValue()), (Event) gson.fromJson(asJsonArray.get(2), Event.class));
    }
}
