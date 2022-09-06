package com.amazonaws.transform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes13.dex */
public class StaxUnmarshallerContext {
    private int currentEventType;
    private final Map<String, String> headers;
    private Map<String, String> metadata;
    private List<MetadataExpression> metadataExpressions;
    public final Deque<String> stack;
    private String stackString;
    private final XmlPullParser xpp;

    /* loaded from: classes13.dex */
    private static class MetadataExpression {
        public String expression;
        public String key;
        public int targetDepth;

        public MetadataExpression(String str, int i, String str2) {
            this.expression = str;
            this.targetDepth = i;
            this.key = str2;
        }
    }

    public StaxUnmarshallerContext(XmlPullParser xmlPullParser) {
        this(xmlPullParser, null);
    }

    private void updateContext() {
        int i = this.currentEventType;
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.stack.pop();
            this.stackString = this.stack.isEmpty() ? "" : this.stack.peek();
            return;
        }
        this.stackString += "/" + this.xpp.getName();
        this.stack.push(this.stackString);
    }

    public int getCurrentDepth() {
        return this.stack.size();
    }

    public String getHeader(String str) {
        Map<String, String> map = this.headers;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public boolean isStartOfDocument() {
        return this.currentEventType == 0;
    }

    public int nextEvent() throws XmlPullParserException, IOException {
        this.currentEventType = this.xpp.next();
        if (this.currentEventType == 4) {
            this.currentEventType = this.xpp.next();
        }
        updateContext();
        if (this.currentEventType == 2) {
            Iterator<MetadataExpression> it2 = this.metadataExpressions.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                MetadataExpression next = it2.next();
                if (testExpression(next.expression, next.targetDepth)) {
                    this.metadata.put(next.key, readText());
                    break;
                }
            }
        }
        return this.currentEventType;
    }

    public String readText() throws XmlPullParserException, IOException {
        String nextText = this.xpp.nextText();
        if (this.xpp.getEventType() != 3) {
            this.xpp.next();
        }
        this.currentEventType = this.xpp.getEventType();
        updateContext();
        return nextText;
    }

    public void registerMetadataExpression(String str, int i, String str2) {
        this.metadataExpressions.add(new MetadataExpression(str, i, str2));
    }

    public boolean testExpression(String str) {
        return testExpression(str, getCurrentDepth());
    }

    public StaxUnmarshallerContext(XmlPullParser xmlPullParser, Map<String, String> map) {
        this.stack = new LinkedList();
        this.stackString = "";
        this.metadata = new HashMap();
        this.metadataExpressions = new ArrayList();
        this.xpp = xmlPullParser;
        this.headers = map;
    }

    public boolean testExpression(String str, int i) {
        if (".".equals(str)) {
            return true;
        }
        int i2 = i;
        int i3 = -1;
        while (true) {
            i3 = str.indexOf("/", i3 + 1);
            if (i3 <= -1) {
                break;
            } else if (str.charAt(i3 + 1) != '@') {
                i2++;
            }
        }
        if (getCurrentDepth() == i2) {
            if (this.stackString.endsWith("/" + str)) {
                return true;
            }
        }
        return false;
    }
}
