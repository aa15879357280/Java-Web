package org.smart4j.chapter3.test;


import org.junit.Test;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.*;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class getClassSetText {
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
            //System.out.println("cls = " + cls);
        }catch (ClassNotFoundException e){

            throw new RuntimeException(e);
        }
        return cls;
    }
    private static Set<Class<?>> CLASS_SET;
    @Test
    public void test(){

    }
    @Test
    public void test01() throws IllegalAccessException, InstantiationException {

        getClassSet("org.smart4j.chapter3");
    }
    /**
     * 获取应用包名下所有Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }

        return classSet;
    }





    /**
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
            try {
                Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
                System.out.println(packageName.replace(".", "/"));
                //System.out.println("urls = " + urls.toString());
                System.out.println("===="+urls.hasMoreElements());
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    System.out.println("url = " + url);
                    if(url != null) {
                        String protocol = url.getProtocol();
                        System.out.println("protocol = " + protocol);
                        if (protocol.equals("file")) {
                            String packagePath = url.getPath().replaceAll("%20", " ");
                            System.out.println("packagePath = " + packagePath);
                            System.out.println("packageName = " + packageName);
                            addClass(classSet, packagePath,packageName);
                        }else if(protocol.equals("jar")){
                            JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                            if(jarURLConnection != null){
                                JarFile jarFile = jarURLConnection.getJarFile();
                                System.out.println("jarFile = " + jarFile);
                                if(jarFile != null){
                                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                                    while(jarEntries.hasMoreElements()){
                                        JarEntry jarEntry = jarEntries.nextElement();
                                        String jarEntryName = jarEntry.getName();
                                        if(jarEntryName.endsWith(".class")){
                                            String className = jarEntryName.substring(0,jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                            doAddClass(classSet,className);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        return classSet;
    }
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files){
            String fileName = file.getName();
            System.out.println("fileName = " + fileName);
            if(file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                System.out.println("className = " + className);
                if(StringUtil.isNotEmpty(packageName)){
                    className = packageName + "." + className;
                    System.out.println("className = " + className);
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if(StringUtil.isNotEmpty(packagePath)){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if(StringUtil.isNotEmpty(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }
    private static void doAddClass(Set<Class<?>> classSet, String className){
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
