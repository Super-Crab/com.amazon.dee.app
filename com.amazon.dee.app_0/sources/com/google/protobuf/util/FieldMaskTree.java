package com.google.protobuf.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.protobuf.Descriptors;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Message;
import com.google.protobuf.util.FieldMaskUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FieldMaskTree {
    private static final String FIELD_PATH_SEPARATOR_REGEX = "\\.";
    private static final Logger logger = Logger.getLogger(FieldMaskTree.class.getName());
    private final Node root = new Node();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class Node {
        final SortedMap<String, Node> children;

        private Node() {
            this.children = new TreeMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree(FieldMask fieldMask) {
        mergeFromFieldMask(fieldMask);
    }

    private void getFieldPaths(Node node, String str, List<String> list) {
        String sb;
        if (node.children.isEmpty()) {
            list.add(str);
            return;
        }
        for (Map.Entry<String, Node> entry : node.children.entrySet()) {
            if (str.isEmpty()) {
                sb = entry.getKey();
            } else {
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ".");
                outline113.append(entry.getKey());
                sb = outline113.toString();
            }
            getFieldPaths(entry.getValue(), sb, list);
        }
    }

    private void merge(Node node, String str, Message message, Message.Builder builder, FieldMaskUtil.MergeOptions mergeOptions) {
        String sb;
        if (message.getDescriptorForType() == builder.getDescriptorForType()) {
            Descriptors.Descriptor descriptorForType = message.getDescriptorForType();
            for (Map.Entry<String, Node> entry : node.children.entrySet()) {
                Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(entry.getKey());
                if (findFieldByName == null) {
                    Logger logger2 = logger;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot find field \"");
                    outline107.append(entry.getKey());
                    outline107.append("\" in message type ");
                    outline107.append(descriptorForType.getFullName());
                    logger2.warning(outline107.toString());
                } else if (!entry.getValue().children.isEmpty()) {
                    if (findFieldByName.isRepeated() || findFieldByName.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        Logger logger3 = logger;
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Field \"");
                        outline1072.append(findFieldByName.getFullName());
                        outline1072.append("\" is not a singluar message field and cannot have sub-fields.");
                        logger3.warning(outline1072.toString());
                    } else if (message.hasField(findFieldByName) || builder.hasField(findFieldByName)) {
                        if (str.isEmpty()) {
                            sb = entry.getKey();
                        } else {
                            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ".");
                            outline113.append(entry.getKey());
                            sb = outline113.toString();
                        }
                        merge(entry.getValue(), sb, (Message) message.getField(findFieldByName), builder.getFieldBuilder(findFieldByName), mergeOptions);
                    }
                } else if (findFieldByName.isRepeated()) {
                    if (mergeOptions.replaceRepeatedFields()) {
                        builder.mo10100setField(findFieldByName, message.getField(findFieldByName));
                    } else {
                        for (Object obj : (List) message.getField(findFieldByName)) {
                            builder.mo10083addRepeatedField(findFieldByName, obj);
                        }
                    }
                } else if (findFieldByName.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    if (!message.hasField(findFieldByName) && mergeOptions.replacePrimitiveFields()) {
                        builder.mo10088clearField(findFieldByName);
                    }
                    builder.mo10100setField(findFieldByName, message.getField(findFieldByName));
                } else if (mergeOptions.replaceMessageFields()) {
                    if (!message.hasField(findFieldByName)) {
                        builder.mo10088clearField(findFieldByName);
                    } else {
                        builder.mo10100setField(findFieldByName, message.getField(findFieldByName));
                    }
                } else if (message.hasField(findFieldByName)) {
                    builder.getFieldBuilder(findFieldByName).mo10096mergeFrom((Message) message.getField(findFieldByName));
                }
            }
            return;
        }
        throw new IllegalArgumentException(String.format("source (%s) and destination (%s) descriptor must be equal", message.getDescriptorForType(), builder.getDescriptorForType()));
    }

    FieldMaskTree addFieldPath(String str) {
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return this;
        }
        Node node = this.root;
        boolean z = false;
        for (String str2 : split) {
            if (!z && node != this.root && node.children.isEmpty()) {
                return this;
            }
            if (node.children.containsKey(str2)) {
                node = node.children.get(str2);
            } else {
                Node node2 = new Node();
                node.children.put(str2, node2);
                z = true;
                node = node2;
            }
        }
        node.children.clear();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void intersectFieldPath(String str, FieldMaskTree fieldMaskTree) {
        if (this.root.children.isEmpty()) {
            return;
        }
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return;
        }
        Node node = this.root;
        for (String str2 : split) {
            if (node != this.root && node.children.isEmpty()) {
                fieldMaskTree.addFieldPath(str);
                return;
            } else if (!node.children.containsKey(str2)) {
                return;
            } else {
                node = node.children.get(str2);
            }
        }
        ArrayList<String> arrayList = new ArrayList();
        getFieldPaths(node, str, arrayList);
        for (String str3 : arrayList) {
            fieldMaskTree.addFieldPath(str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void merge(Message message, Message.Builder builder, FieldMaskUtil.MergeOptions mergeOptions) {
        if (message.getDescriptorForType() == builder.getDescriptorForType()) {
            if (this.root.children.isEmpty()) {
                return;
            }
            merge(this.root, "", message, builder, mergeOptions);
            return;
        }
        throw new IllegalArgumentException("Cannot merge messages of different types.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMaskTree mergeFromFieldMask(FieldMask fieldMask) {
        for (String str : fieldMask.mo9496getPathsList()) {
            addFieldPath(str);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldMask toFieldMask() {
        if (this.root.children.isEmpty()) {
            return FieldMask.getDefaultInstance();
        }
        ArrayList arrayList = new ArrayList();
        getFieldPaths(this.root, "", arrayList);
        return FieldMask.newBuilder().addAllPaths(arrayList).mo10084build();
    }

    public String toString() {
        return FieldMaskUtil.toString(toFieldMask());
    }
}
