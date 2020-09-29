package com.hhm.bussplat.util.util;

import java.io.*;

/**
 * @author q
 * @time 2020/5/16 3:40 下午
 */
public class JdkSerializeUtil {
    private JdkSerializeUtil() {
    }

    public static byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        } else if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(object.getClass().getSimpleName() + " requires a Serializable payload " + "but received an object of type [" + object.getClass().getName() + "]");
        } else {
            try {
                ByteArrayOutputStream e = new ByteArrayOutputStream(128);
                Throwable var2 = null;

                byte[] var4;
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(e);
                    oos.writeObject(object);
                    oos.flush();
                    var4 = e.toByteArray();
                } catch (Throwable var14) {
                    var2 = var14;
                    throw var14;
                } finally {
                    if (e != null) {
                        if (var2 != null) {
                            try {
                                e.close();
                            } catch (Throwable var13) {
                                var2.addSuppressed(var13);
                            }
                        } else {
                            e.close();
                        }
                    }

                }

                return var4;
            } catch (Throwable var16) {
                throw new RuntimeException("Failed to serialize object using " + object.getClass().getSimpleName(), var16);
            }
        }
    }

    public static Object deserialize(byte[] data) {
        if (data != null && data.length != 0) {
            try {
                ByteArrayInputStream e = new ByteArrayInputStream(data);
                Throwable var2 = null;

                Object var4;
                try {
                    ObjectInputStream ois = new ObjectInputStream(e);
                    var4 = ois.readObject();
                } catch (Throwable var14) {
                    var2 = var14;
                    throw var14;
                } finally {
                    if (e != null) {
                        if (var2 != null) {
                            try {
                                e.close();
                            } catch (Throwable var13) {
                                var2.addSuppressed(var13);
                            }
                        } else {
                            e.close();
                        }
                    }

                }

                return var4;
            } catch (Throwable var16) {
                throw new RuntimeException("Failed to deserialize payload. Is the byte array a result of corresponding serialization for Class ?", var16);
            }
        } else {
            return null;
        }
    }
}
