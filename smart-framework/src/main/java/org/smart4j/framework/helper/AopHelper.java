package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.proxy.AspectProxy;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;
import org.smart4j.framework.proxy.TransactionProxy;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 方法拦截助手类
 */
public final class AopHelper {
    private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static {
        try{
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for(Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()){
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        }catch (Exception e){
            logger.error("aop failure", e);
        }
    }



    /**
     * 获取被代理类
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> createTargetClassSet(Aspect aspect)throws Exception{
        Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap)throws  Exception{
      Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
      for(Map.Entry<Class<?>, Set<Class<?>>> proxyEntry:proxyMap.entrySet()){
          Class<?> proxyClass = proxyEntry.getKey();
          Set<Class<?>> targetClassSet = proxyEntry.getValue();
          for(Class<?> targetClass:targetClassSet){
              Proxy proxy = (Proxy) proxyClass.newInstance();
              if(targetMap.containsKey(targetClass)){
                  targetMap.get(targetClass).add(proxy);
              }else {
                  List<Proxy> proxyList = new ArrayList<Proxy>();
                  proxyList.add(proxy);
                  targetMap.put(targetClass, proxyList);
              }
          }
      }
      return targetMap;
    }

    /**
     * 获取切面和被代理类映射
     * @return
     * @throws Exception
     */
    private static Map<Class<?>, Set<Class<?>>> createProxyMap()throws Exception{
        Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectProxy(proxyMap);
        addTrancationProxy(proxyMap);
        return proxyMap;
    }

    /**
     * 获取普通切面映射
     * @param proxyMap
     * @throws Exception
     */
    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap)throws Exception{
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> proxyClass : proxyClassSet){
            if(proxyClass.isAnnotationPresent(Aspect.class)){
                Aspect aspect = proxyClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass,targetClassSet);
            }
        }
    }

    /**
     * 获取事务切面映射
     * @param proxyMap
     */
    private static void addTrancationProxy(Map<Class<?>, Set<Class<?>>> proxyMap){
        Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClassSet);
    }
}
