package org.apache.logging.log4j.message;

import java.io.Serializable;
/* loaded from: classes4.dex */
public abstract class AbstractMessageFactory implements MessageFactory2, Serializable {
    private static final long serialVersionUID = -1307891137684031187L;

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(CharSequence charSequence) {
        return new SimpleMessage(charSequence);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(Object obj) {
        return new ObjectMessage(obj);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str) {
        return new SimpleMessage(str);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj) {
        return newMessage(str, obj);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2) {
        return newMessage(str, obj, obj2);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3) {
        return newMessage(str, obj, obj2, obj3);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return newMessage(str, obj, obj2, obj3, obj4);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return newMessage(str, obj, obj2, obj3, obj4, obj5);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return newMessage(str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory2
    public Message newMessage(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }
}
