package me.qinchao.compiler;

/**
 * Created by sulvto on 16-4-22.
 */
public class DynamicClassLoader extends ClassLoader {
    public DynamicClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String fullName, byte[] classData) {
        return this.defineClass(fullName, classData, 0, classData.length);
    }
}