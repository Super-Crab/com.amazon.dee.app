package com.amazon.identity.auth.device;

import android.content.res.XmlResourceParser;
import com.amazon.identity.auth.accounts.InvalidSubAuthenticatorDefinitionException;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ak {
    public aj a(String str, String str2, XmlResourceParser xmlResourceParser) throws InvalidSubAuthenticatorDefinitionException {
        if (str != null && xmlResourceParser != null) {
            return new a((byte) 0).a(str, str2, xmlResourceParser);
        }
        throw new IllegalArgumentException("The package name and xml parser parameter cannot be null");
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a {
        private String bk;
        private boolean bp;
        private XmlResourceParser cZ;
        private final List<String> da;
        private boolean db;

        private a() {
            this.da = new ArrayList();
        }

        private void al(String str) throws InvalidSubAuthenticatorDefinitionException, IOException, XmlPullParserException {
            e(2);
            am(str);
        }

        private void am(String str) throws InvalidSubAuthenticatorDefinitionException {
            if (this.cZ.getName().equals(str)) {
                return;
            }
            throw new InvalidSubAuthenticatorDefinitionException();
        }

        private void e(int i) throws InvalidSubAuthenticatorDefinitionException, IOException, XmlPullParserException {
            int nextTag;
            if (i != 2 && i != 3) {
                nextTag = this.cZ.next();
            } else {
                nextTag = this.cZ.nextTag();
            }
            if (nextTag == i) {
                return;
            }
            throw new InvalidSubAuthenticatorDefinitionException();
        }

        public aj a(String str, String str2, XmlResourceParser xmlResourceParser) throws InvalidSubAuthenticatorDefinitionException {
            boolean z;
            this.cZ = xmlResourceParser;
            try {
                e(0);
                al(AccountConstants.SUB_AUTHENTICATOR_ATTRIBUTES_NAME);
                if (this.cZ.getAttributeValue(null, AccountConstants.SUB_AUTHENTICATOR_ACCOUNT_TYPE_ATTRIBUTE).equals(AccountConstants.AMAZON_ACCOUNT_TYPE)) {
                    String attributeValue = this.cZ.getAttributeValue(null, "type");
                    if (attributeValue != null && attributeValue.equals("DMS")) {
                        this.db = true;
                        while (this.cZ.next() != 3) {
                            String name = this.cZ.getName();
                            e(4);
                            if (name.equals(AccountConstants.SUB_AUTHENTICATOR_DEVICE_TYPE_NAME)) {
                                this.bk = this.cZ.getText();
                            } else if (name.equals(AccountConstants.SUB_AUTHENTICATOR_MULTIPLE_ACCOUNT_AWARE)) {
                                String text = this.cZ.getText();
                                if (!"true".equalsIgnoreCase(text) && !"yes".equalsIgnoreCase(text) && !"1".equalsIgnoreCase(text)) {
                                    z = false;
                                    this.bp = z;
                                }
                                z = true;
                                this.bp = z;
                            } else {
                                throw new InvalidSubAuthenticatorDefinitionException();
                            }
                            e(3);
                        }
                    } else {
                        al(AccountConstants.SUB_AUTHENTICATOR_TOKEN_TYPES_NAME);
                        while (this.cZ.nextTag() != 3) {
                            am(AccountConstants.SUB_AUTHENTICATOR_TOKEN_TYPE_NAME);
                            String attributeValue2 = this.cZ.getAttributeValue(null, "name");
                            e(3);
                            this.da.add(attributeValue2);
                        }
                        am(AccountConstants.SUB_AUTHENTICATOR_TOKEN_TYPES_NAME);
                    }
                    if (!this.cZ.getName().equals(AccountConstants.SUB_AUTHENTICATOR_ATTRIBUTES_NAME)) {
                        e(3);
                    }
                    e(1);
                    if (this.db) {
                        return new aj(str, str2, this.bk, this.bp);
                    }
                    return new aj(str, str2, this.da);
                }
                throw new InvalidSubAuthenticatorDefinitionException();
            } catch (IOException e) {
                throw new InvalidSubAuthenticatorDefinitionException(e);
            } catch (XmlPullParserException e2) {
                throw new InvalidSubAuthenticatorDefinitionException(e2);
            }
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }
}
