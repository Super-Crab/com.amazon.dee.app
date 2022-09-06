package com.google.gson.graph;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
/* loaded from: classes3.dex */
public final class GraphAdapterBuilder {
    private final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap();
    private final ConstructorConstructor constructorConstructor = new ConstructorConstructor(this.instanceCreators);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Element<T> {
        private final JsonElement element;
        private final String id;
        private TypeAdapter<T> typeAdapter;
        private T value;

        Element(T t, String str, TypeAdapter<T> typeAdapter, JsonElement jsonElement) {
            this.value = t;
            this.id = str;
            this.typeAdapter = typeAdapter;
            this.element = jsonElement;
        }

        void read(Graph graph) throws IOException {
            if (graph.nextCreate == null) {
                graph.nextCreate = this;
                this.value = this.typeAdapter.fromJsonTree(this.element);
                if (this.value != null) {
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("non-null value deserialized to null: ");
                outline107.append(this.element);
                throw new IllegalStateException(outline107.toString());
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unexpected recursive call to read() for ");
            outline1072.append(this.id);
            throw new IllegalStateException(outline1072.toString());
        }

        void write(JsonWriter jsonWriter) throws IOException {
            this.typeAdapter.write(jsonWriter, this.value);
        }
    }

    /* loaded from: classes3.dex */
    static class Factory implements TypeAdapterFactory, InstanceCreator {
        private final ThreadLocal<Graph> graphThreadLocal = new ThreadLocal<>();
        private final Map<Type, InstanceCreator<?>> instanceCreators;

        Factory(Map<Type, InstanceCreator<?>> map) {
            this.instanceCreators = map;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (!this.instanceCreators.containsKey(typeToken.getType())) {
                return null;
            }
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, typeToken);
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            return new TypeAdapter<T>() { // from class: com.google.gson.graph.GraphAdapterBuilder.Factory.1
                /* JADX WARN: Type inference failed for: r10v5, types: [T, java.lang.Object] */
                @Override // com.google.gson.TypeAdapter
                /* renamed from: read */
                public T mo8353read(JsonReader jsonReader) throws IOException {
                    String nextString;
                    if (jsonReader.peek() != JsonToken.NULL) {
                        Graph graph = (Graph) Factory.this.graphThreadLocal.get();
                        boolean z = false;
                        if (graph == null) {
                            graph = new Graph(new HashMap());
                            z = true;
                            jsonReader.beginObject();
                            nextString = null;
                            while (jsonReader.hasNext()) {
                                String nextName = jsonReader.nextName();
                                if (nextString == null) {
                                    nextString = nextName;
                                }
                                graph.map.put(nextName, new Element(null, nextName, delegateAdapter, (JsonElement) adapter.mo8353read(jsonReader)));
                            }
                            jsonReader.endObject();
                        } else {
                            nextString = jsonReader.nextString();
                        }
                        if (z) {
                            Factory.this.graphThreadLocal.set(graph);
                        }
                        try {
                            Element element = (Element) graph.map.get(nextString);
                            if (element.value == null) {
                                element.typeAdapter = delegateAdapter;
                                element.read(graph);
                            }
                            return element.value;
                        } finally {
                            if (z) {
                                Factory.this.graphThreadLocal.remove();
                            }
                        }
                    }
                    jsonReader.nextNull();
                    return null;
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    if (t == 0) {
                        jsonWriter.nullValue();
                        return;
                    }
                    Graph graph = (Graph) Factory.this.graphThreadLocal.get();
                    boolean z = false;
                    if (graph == null) {
                        z = true;
                        graph = new Graph(new IdentityHashMap());
                    }
                    Element element = (Element) graph.map.get(t);
                    if (element == null) {
                        element = new Element(t, graph.nextName(), delegateAdapter, null);
                        graph.map.put(t, element);
                        graph.queue.add(element);
                    }
                    if (z) {
                        Factory.this.graphThreadLocal.set(graph);
                        try {
                            jsonWriter.beginObject();
                            while (true) {
                                Element element2 = (Element) graph.queue.poll();
                                if (element2 != null) {
                                    jsonWriter.name(element2.id);
                                    element2.write(jsonWriter);
                                } else {
                                    jsonWriter.endObject();
                                    return;
                                }
                            }
                        } finally {
                            Factory.this.graphThreadLocal.remove();
                        }
                    } else {
                        jsonWriter.value(element.id);
                    }
                }
            };
        }

        @Override // com.google.gson.InstanceCreator
        public Object createInstance(Type type) {
            Graph graph = this.graphThreadLocal.get();
            if (graph != null && graph.nextCreate != null) {
                Object createInstance = this.instanceCreators.get(type).createInstance(type);
                graph.nextCreate.value = createInstance;
                graph.nextCreate = null;
                return createInstance;
            }
            throw new IllegalStateException("Unexpected call to createInstance() for " + type);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Graph {
        private final Map<Object, Element<?>> map;
        private Element nextCreate;
        private final Queue<Element> queue;

        public String nextName() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("0x");
            outline107.append(Integer.toHexString(this.map.size() + 1));
            return outline107.toString();
        }

        private Graph(Map<Object, Element<?>> map) {
            this.queue = new LinkedList();
            this.map = map;
        }
    }

    public GraphAdapterBuilder addType(Type type) {
        final ObjectConstructor objectConstructor = this.constructorConstructor.get(TypeToken.get(type));
        return addType(type, new InstanceCreator<Object>() { // from class: com.google.gson.graph.GraphAdapterBuilder.1
            @Override // com.google.gson.InstanceCreator
            public Object createInstance(Type type2) {
                return objectConstructor.construct();
            }
        });
    }

    public void registerOn(GsonBuilder gsonBuilder) {
        TypeAdapterFactory factory = new Factory(this.instanceCreators);
        gsonBuilder.registerTypeAdapterFactory(factory);
        for (Map.Entry<Type, InstanceCreator<?>> entry : this.instanceCreators.entrySet()) {
            gsonBuilder.registerTypeAdapter(entry.getKey(), factory);
        }
    }

    public GraphAdapterBuilder addType(Type type, InstanceCreator<?> instanceCreator) {
        if (type != null && instanceCreator != null) {
            this.instanceCreators.put(type, instanceCreator);
            return this;
        }
        throw new NullPointerException();
    }
}
