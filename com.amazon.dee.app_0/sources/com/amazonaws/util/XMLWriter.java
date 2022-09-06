package com.amazonaws.util;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazonaws.AmazonClientException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Stack;
/* loaded from: classes.dex */
public class XMLWriter {
    private static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private Stack<String> elementStack;
    private boolean rootElement;
    private final Writer writer;
    private final String xmlns;

    public XMLWriter(Writer writer) {
        this(writer, null);
    }

    private void append(String str) {
        try {
            this.writer.append((CharSequence) str);
        } catch (IOException e) {
            throw new AmazonClientException("Unable to write XML document", e);
        }
    }

    private String escapeXMLEntities(String str) {
        if (str.contains(WebConstants.UriConstants.AMPERSAND_KEY)) {
            str = str.replace("&quot;", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED).replace("&apos;", "'").replace("&lt;", Config.Compare.LESS_THAN).replace("&gt;", Config.Compare.GREATER_THAN).replace("&amp;", WebConstants.UriConstants.AMPERSAND_KEY);
        }
        return str.replace(WebConstants.UriConstants.AMPERSAND_KEY, "&amp;").replace(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED, "&quot;").replace("'", "&apos;").replace(Config.Compare.LESS_THAN, "&lt;").replace(Config.Compare.GREATER_THAN, "&gt;");
    }

    public XMLWriter endElement() {
        append(GeneratedOutlineSupport1.outline75("</", this.elementStack.pop(), Config.Compare.GREATER_THAN));
        return this;
    }

    public XMLWriter startElement(String str) {
        append(GeneratedOutlineSupport1.outline72(Config.Compare.LESS_THAN, str));
        if (this.rootElement && this.xmlns != null) {
            append(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(" xmlns=\""), this.xmlns, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED));
            this.rootElement = false;
        }
        append(Config.Compare.GREATER_THAN);
        this.elementStack.push(str);
        return this;
    }

    public XMLWriter value(String str) {
        append(escapeXMLEntities(str));
        return this;
    }

    public XMLWriter(Writer writer, String str) {
        this.elementStack = new Stack<>();
        this.rootElement = true;
        this.writer = writer;
        this.xmlns = str;
        append(PROLOG);
    }

    public XMLWriter value(Date date) {
        append(escapeXMLEntities(StringUtils.fromDate(date)));
        return this;
    }

    public XMLWriter value(Object obj) {
        append(escapeXMLEntities(obj.toString()));
        return this;
    }
}
