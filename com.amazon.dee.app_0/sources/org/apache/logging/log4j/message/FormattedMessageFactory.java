package org.apache.logging.log4j.message;
/* loaded from: classes4.dex */
public class FormattedMessageFactory extends AbstractMessageFactory {
    private static final long serialVersionUID = 1;

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str, Object... objArr) {
        return new FormattedMessage(str, objArr);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj) {
        return new FormattedMessage(str, obj);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2) {
        return new FormattedMessage(str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3) {
        return new FormattedMessage(str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return new FormattedMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }
}
