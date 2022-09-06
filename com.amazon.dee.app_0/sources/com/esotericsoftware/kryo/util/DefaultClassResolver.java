package com.esotericsoftware.kryo.util;

import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsCapabilityAgentConstants;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.ClassResolver;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
/* loaded from: classes2.dex */
public class DefaultClassResolver implements ClassResolver {
    public static final byte NAME = -1;
    protected IdentityObjectIntMap<Class> classToNameId;
    protected Kryo kryo;
    private Class memoizedClass;
    private Registration memoizedClassIdValue;
    private Registration memoizedClassValue;
    protected IntMap<Class> nameIdToClass;
    protected ObjectMap<String, Class> nameToClass;
    protected int nextNameId;
    protected final IntMap<Registration> idToRegistration = new IntMap<>();
    protected final ObjectMap<Class, Registration> classToRegistration = new ObjectMap<>();
    private int memoizedClassId = -1;

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration getRegistration(Class cls) {
        if (cls == this.memoizedClass) {
            return this.memoizedClassValue;
        }
        Registration registration = this.classToRegistration.get(cls);
        if (registration != null) {
            this.memoizedClass = cls;
            this.memoizedClassValue = registration;
        }
        return registration;
    }

    protected Class<?> getTypeByName(String str) {
        ObjectMap<String, Class> objectMap = this.nameToClass;
        if (objectMap != null) {
            return objectMap.get(str);
        }
        return null;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration readClass(Input input) {
        int readVarInt = input.readVarInt(true);
        if (readVarInt == 0) {
            if (Log.TRACE || (Log.DEBUG && this.kryo.getDepth() == 1)) {
                Util.log(ExternalNotificationsCapabilityAgentConstants.READ_EVENT_NAME, null);
            }
            return null;
        } else if (readVarInt != 1) {
            if (readVarInt == this.memoizedClassId) {
                return this.memoizedClassIdValue;
            }
            int i = readVarInt - 2;
            Registration registration = this.idToRegistration.get(i);
            if (registration != null) {
                if (Log.TRACE) {
                    StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Read class ", i, RealTimeTextConstants.COLON_SPACE);
                    outline109.append(Util.className(registration.getType()));
                    Log.trace("kryo", outline109.toString());
                }
                this.memoizedClassId = readVarInt;
                this.memoizedClassIdValue = registration;
                return registration;
            }
            throw new KryoException(GeneratedOutlineSupport1.outline49("Encountered unregistered class ID: ", i));
        } else {
            return readName(input);
        }
    }

    protected Registration readName(Input input) {
        int readVarInt = input.readVarInt(true);
        if (this.nameIdToClass == null) {
            this.nameIdToClass = new IntMap<>();
        }
        Class cls = this.nameIdToClass.get(readVarInt);
        if (cls == null) {
            String readString = input.readString();
            cls = getTypeByName(readString);
            if (cls == null) {
                try {
                    cls = Class.forName(readString, false, this.kryo.getClassLoader());
                    if (this.nameToClass == null) {
                        this.nameToClass = new ObjectMap<>();
                    }
                    this.nameToClass.put(readString, cls);
                } catch (ClassNotFoundException e) {
                    throw new KryoException(GeneratedOutlineSupport1.outline72("Unable to find class: ", readString), e);
                }
            }
            this.nameIdToClass.put(readVarInt, cls);
            if (Log.TRACE) {
                Log.trace("kryo", "Read class name: " + readString);
            }
        } else if (Log.TRACE) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Read class name reference ", readVarInt, RealTimeTextConstants.COLON_SPACE);
            outline109.append(Util.className(cls));
            Log.trace("kryo", outline109.toString());
        }
        return this.kryo.getRegistration(cls);
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration register(Registration registration) {
        if (registration != null) {
            if (registration.getId() != -1) {
                if (Log.TRACE) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Register class ID ");
                    outline107.append(registration.getId());
                    outline107.append(RealTimeTextConstants.COLON_SPACE);
                    outline107.append(Util.className(registration.getType()));
                    outline107.append(" (");
                    outline107.append(registration.getSerializer().getClass().getName());
                    outline107.append(")");
                    Log.trace("kryo", outline107.toString());
                }
                this.idToRegistration.put(registration.getId(), registration);
            } else if (Log.TRACE) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Register class name: ");
                outline1072.append(Util.className(registration.getType()));
                outline1072.append(" (");
                outline1072.append(registration.getSerializer().getClass().getName());
                outline1072.append(")");
                Log.trace("kryo", outline1072.toString());
            }
            this.classToRegistration.put(registration.getType(), registration);
            if (registration.getType().isPrimitive()) {
                this.classToRegistration.put(Util.getWrapperClass(registration.getType()), registration);
            }
            return registration;
        }
        throw new IllegalArgumentException("registration cannot be null.");
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration registerImplicit(Class cls) {
        return register(new Registration(cls, this.kryo.getDefaultSerializer(cls), -1));
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public void reset() {
        if (!this.kryo.isRegistrationRequired()) {
            IdentityObjectIntMap<Class> identityObjectIntMap = this.classToNameId;
            if (identityObjectIntMap != null) {
                identityObjectIntMap.clear();
            }
            IntMap<Class> intMap = this.nameIdToClass;
            if (intMap != null) {
                intMap.clear();
            }
            this.nextNameId = 0;
        }
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public void setKryo(Kryo kryo) {
        this.kryo = kryo;
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration writeClass(Output output, Class cls) {
        if (cls == null) {
            if (Log.TRACE || (Log.DEBUG && this.kryo.getDepth() == 1)) {
                Util.log("Write", null);
            }
            output.writeVarInt(0, true);
            return null;
        }
        Registration registration = this.kryo.getRegistration(cls);
        if (registration.getId() == -1) {
            writeName(output, cls, registration);
        } else {
            if (Log.TRACE) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Write class ");
                outline107.append(registration.getId());
                outline107.append(RealTimeTextConstants.COLON_SPACE);
                outline107.append(Util.className(cls));
                Log.trace("kryo", outline107.toString());
            }
            output.writeVarInt(registration.getId() + 2, true);
        }
        return registration;
    }

    protected void writeName(Output output, Class cls, Registration registration) {
        int i;
        output.writeVarInt(1, true);
        IdentityObjectIntMap<Class> identityObjectIntMap = this.classToNameId;
        if (identityObjectIntMap != null && (i = identityObjectIntMap.get(cls, -1)) != -1) {
            if (Log.TRACE) {
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Write class name reference ", i, RealTimeTextConstants.COLON_SPACE);
                outline109.append(Util.className(cls));
                Log.trace("kryo", outline109.toString());
            }
            output.writeVarInt(i, true);
            return;
        }
        if (Log.TRACE) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Write class name: ");
            outline107.append(Util.className(cls));
            Log.trace("kryo", outline107.toString());
        }
        int i2 = this.nextNameId;
        this.nextNameId = i2 + 1;
        if (this.classToNameId == null) {
            this.classToNameId = new IdentityObjectIntMap<>();
        }
        this.classToNameId.put(cls, i2);
        output.writeVarInt(i2, true);
        output.writeString(cls.getName());
    }

    @Override // com.esotericsoftware.kryo.ClassResolver
    public Registration getRegistration(int i) {
        return this.idToRegistration.get(i);
    }
}
