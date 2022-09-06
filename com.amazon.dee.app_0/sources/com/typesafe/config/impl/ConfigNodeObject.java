package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigSyntax;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNodeObject extends ConfigNodeComplexValue {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNodeObject(Collection<AbstractConfigNode> collection) {
        super(collection);
    }

    private Collection<AbstractConfigNode> indentation() {
        ArrayList<AbstractConfigNode> arrayList;
        String str;
        int i;
        ArrayList arrayList2 = new ArrayList();
        if (this.children.isEmpty()) {
            return arrayList2;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.children.size(); i2++) {
            if (!z) {
                if ((this.children.get(i2) instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) this.children.get(i2)).token())) {
                    arrayList2.add(new ConfigNodeSingleToken(Tokens.newLine(null)));
                    z = true;
                }
            } else if ((this.children.get(i2) instanceof ConfigNodeSingleToken) && Tokens.isIgnoredWhitespace(((ConfigNodeSingleToken) this.children.get(i2)).token()) && (i = i2 + 1) < this.children.size() && ((this.children.get(i) instanceof ConfigNodeField) || (this.children.get(i) instanceof ConfigNodeInclude))) {
                arrayList2.add(this.children.get(i2));
                return arrayList2;
            }
        }
        if (arrayList2.isEmpty()) {
            arrayList2.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace(null, " ")));
        } else {
            ArrayList<AbstractConfigNode> arrayList3 = this.children;
            AbstractConfigNode abstractConfigNode = arrayList3.get(arrayList3.size() - 1);
            if ((abstractConfigNode instanceof ConfigNodeSingleToken) && ((ConfigNodeSingleToken) abstractConfigNode).token() == Tokens.CLOSE_CURLY) {
                AbstractConfigNode abstractConfigNode2 = this.children.get(arrayList.size() - 2);
                if (abstractConfigNode2 instanceof ConfigNodeSingleToken) {
                    ConfigNodeSingleToken configNodeSingleToken = (ConfigNodeSingleToken) abstractConfigNode2;
                    if (Tokens.isIgnoredWhitespace(configNodeSingleToken.token())) {
                        str = configNodeSingleToken.token().tokenText();
                        arrayList2.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace(null, GeneratedOutlineSupport1.outline72(str, "  "))));
                    }
                }
                str = "";
                arrayList2.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace(null, GeneratedOutlineSupport1.outline72(str, "  "))));
            }
        }
        return arrayList2;
    }

    protected ConfigNodeObject addValueOnPath(ConfigNodePath configNodePath, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        Path value = configNodePath.value();
        ArrayList arrayList = new ArrayList(this.children);
        ArrayList arrayList2 = new ArrayList(indentation());
        ConfigNodeComplexValue indentText = (!(abstractConfigNodeValue instanceof ConfigNodeComplexValue) || arrayList2.isEmpty()) ? abstractConfigNodeValue : ((ConfigNodeComplexValue) abstractConfigNodeValue).indentText((AbstractConfigNode) arrayList2.get(arrayList2.size() - 1));
        boolean z = false;
        boolean z2 = arrayList2.size() <= 0 || !(arrayList2.get(0) instanceof ConfigNodeSingleToken) || !Tokens.isNewline(((ConfigNodeSingleToken) arrayList2.get(0)).token());
        if (value.length() > 1) {
            for (int size = this.children.size() - 1; size >= 0; size--) {
                if (this.children.get(size) instanceof ConfigNodeField) {
                    ConfigNodeField configNodeField = (ConfigNodeField) this.children.get(size);
                    Path value2 = configNodeField.path().value();
                    if (value.startsWith(value2) && (configNodeField.value() instanceof ConfigNodeObject)) {
                        arrayList.set(size, configNodeField.replaceValue(((ConfigNodeObject) configNodeField.value()).addValueOnPath(configNodePath.subPath(value2.length()), abstractConfigNodeValue, configSyntax)));
                        return new ConfigNodeObject(arrayList);
                    }
                }
            }
        }
        if (!this.children.isEmpty() && (this.children.get(0) instanceof ConfigNodeSingleToken) && ((ConfigNodeSingleToken) this.children.get(0)).token() == Tokens.OPEN_CURLY) {
            z = true;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(arrayList2);
        arrayList3.add(configNodePath.first());
        arrayList3.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace(null, " ")));
        arrayList3.add(new ConfigNodeSingleToken(Tokens.COLON));
        arrayList3.add(new ConfigNodeSingleToken(Tokens.newIgnoredWhitespace(null, " ")));
        if (value.length() == 1) {
            arrayList3.add(indentText);
        } else {
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(new ConfigNodeSingleToken(Tokens.OPEN_CURLY));
            if (arrayList2.isEmpty()) {
                arrayList4.add(new ConfigNodeSingleToken(Tokens.newLine(null)));
            }
            arrayList4.addAll(arrayList2);
            arrayList4.add(new ConfigNodeSingleToken(Tokens.CLOSE_CURLY));
            arrayList3.add(new ConfigNodeObject(arrayList4).addValueOnPath(configNodePath.subPath(1), indentText, configSyntax));
        }
        if (configSyntax == ConfigSyntax.JSON || z || z2) {
            int size2 = arrayList.size() - 1;
            while (true) {
                if (size2 < 0) {
                    break;
                } else if ((configSyntax == ConfigSyntax.JSON || z2) && (arrayList.get(size2) instanceof ConfigNodeField)) {
                    int i = size2 + 1;
                    if (i >= arrayList.size() || !(arrayList.get(i) instanceof ConfigNodeSingleToken) || ((ConfigNodeSingleToken) arrayList.get(i)).token() != Tokens.COMMA) {
                        arrayList.add(i, new ConfigNodeSingleToken(Tokens.COMMA));
                    }
                } else {
                    if (z && (arrayList.get(size2) instanceof ConfigNodeSingleToken) && ((ConfigNodeSingleToken) arrayList.get(size2)).token == Tokens.CLOSE_CURLY) {
                        int i2 = size2 - 1;
                        AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i2);
                        boolean z3 = abstractConfigNode instanceof ConfigNodeSingleToken;
                        if (z3 && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                            arrayList.add(i2, new ConfigNodeField(arrayList3));
                        } else if (z3 && Tokens.isIgnoredWhitespace(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                            int i3 = size2 - 2;
                            AbstractConfigNode abstractConfigNode2 = (AbstractConfigNode) arrayList.get(i3);
                            if (z2) {
                                arrayList.add(i2, new ConfigNodeField(arrayList3));
                            } else if ((abstractConfigNode2 instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode2).token())) {
                                arrayList.add(i3, new ConfigNodeField(arrayList3));
                                size2 -= 2;
                            } else {
                                arrayList.add(size2, new ConfigNodeField(arrayList3));
                            }
                        } else {
                            arrayList.add(size2, new ConfigNodeField(arrayList3));
                        }
                        size2--;
                    }
                    size2--;
                }
            }
        }
        if (!z) {
            if (!arrayList.isEmpty() && (arrayList.get(arrayList.size() - 1) instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) arrayList.get(arrayList.size() - 1)).token())) {
                arrayList.add(arrayList.size() - 1, new ConfigNodeField(arrayList3));
            } else {
                arrayList.add(new ConfigNodeField(arrayList3));
            }
        }
        return new ConfigNodeObject(arrayList);
    }

    protected ConfigNodeObject changeValueOnPath(Path path, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        ArrayList arrayList = new ArrayList(this.children);
        boolean z = false;
        AbstractConfigNodeValue abstractConfigNodeValue2 = abstractConfigNodeValue;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) instanceof ConfigNodeSingleToken) {
                Token token = ((ConfigNodeSingleToken) arrayList.get(size)).token();
                if (configSyntax == ConfigSyntax.JSON && !z && token == Tokens.COMMA) {
                    arrayList.remove(size);
                }
            } else if (arrayList.get(size) instanceof ConfigNodeField) {
                ConfigNodeField configNodeField = (ConfigNodeField) arrayList.get(size);
                Path value = configNodeField.path().value();
                if ((abstractConfigNodeValue2 == null && value.equals(path)) || (value.startsWith(path) && !value.equals(path))) {
                    arrayList.remove(size);
                    for (int i = size; i < arrayList.size() && (arrayList.get(i) instanceof ConfigNodeSingleToken); i = (i - 1) + 1) {
                        Token token2 = ((ConfigNodeSingleToken) arrayList.get(i)).token();
                        if (Tokens.isIgnoredWhitespace(token2) || token2 == Tokens.COMMA) {
                            arrayList.remove(i);
                        }
                    }
                } else if (value.equals(path)) {
                    int i2 = size - 1;
                    AbstractConfigNode abstractConfigNode = i2 > 0 ? (AbstractConfigNode) arrayList.get(i2) : null;
                    arrayList.set(size, configNodeField.replaceValue((!(abstractConfigNodeValue instanceof ConfigNodeComplexValue) || !(abstractConfigNode instanceof ConfigNodeSingleToken) || !Tokens.isIgnoredWhitespace(((ConfigNodeSingleToken) abstractConfigNode).token())) ? abstractConfigNodeValue : ((ConfigNodeComplexValue) abstractConfigNodeValue).indentText(abstractConfigNode)));
                    z = true;
                    abstractConfigNodeValue2 = null;
                } else {
                    if (path.startsWith(value) && (configNodeField.value() instanceof ConfigNodeObject)) {
                        arrayList.set(size, configNodeField.replaceValue(((ConfigNodeObject) configNodeField.value()).changeValueOnPath(path.subPath(value.length()), abstractConfigNodeValue2, configSyntax)));
                        if (abstractConfigNodeValue2 != null && !configNodeField.equals(this.children.get(size))) {
                            abstractConfigNodeValue2 = null;
                        }
                    }
                    z = true;
                }
            }
        }
        return new ConfigNodeObject(arrayList);
    }

    public boolean hasValue(Path path) {
        Iterator<AbstractConfigNode> it2 = this.children.iterator();
        while (it2.hasNext()) {
            AbstractConfigNode next = it2.next();
            if (next instanceof ConfigNodeField) {
                ConfigNodeField configNodeField = (ConfigNodeField) next;
                Path value = configNodeField.path().value();
                if (value.equals(path) || value.startsWith(path) || (path.startsWith(value) && (configNodeField.value() instanceof ConfigNodeObject) && ((ConfigNodeObject) configNodeField.value()).hasValue(path.subPath(value.length())))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.typesafe.config.impl.ConfigNodeComplexValue
    /* renamed from: newNode */
    protected /* bridge */ /* synthetic */ ConfigNodeComplexValue mo10213newNode(Collection collection) {
        return mo10213newNode((Collection<AbstractConfigNode>) collection);
    }

    public ConfigNodeObject removeValueOnPath(String str, ConfigSyntax configSyntax) {
        return changeValueOnPath(PathParser.parsePathNode(str, configSyntax).value(), null, configSyntax);
    }

    public ConfigNodeObject setValueOnPath(String str, AbstractConfigNodeValue abstractConfigNodeValue) {
        return setValueOnPath(str, abstractConfigNodeValue, ConfigSyntax.CONF);
    }

    @Override // com.typesafe.config.impl.ConfigNodeComplexValue
    /* renamed from: newNode  reason: collision with other method in class */
    protected ConfigNodeObject mo10213newNode(Collection<AbstractConfigNode> collection) {
        return new ConfigNodeObject(collection);
    }

    public ConfigNodeObject setValueOnPath(String str, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        return setValueOnPath(PathParser.parsePathNode(str, configSyntax), abstractConfigNodeValue, configSyntax);
    }

    private ConfigNodeObject setValueOnPath(ConfigNodePath configNodePath, AbstractConfigNodeValue abstractConfigNodeValue, ConfigSyntax configSyntax) {
        ConfigNodeObject changeValueOnPath = changeValueOnPath(configNodePath.value(), abstractConfigNodeValue, configSyntax);
        return !changeValueOnPath.hasValue(configNodePath.value()) ? changeValueOnPath.addValueOnPath(configNodePath, abstractConfigNodeValue, configSyntax) : changeValueOnPath;
    }
}
