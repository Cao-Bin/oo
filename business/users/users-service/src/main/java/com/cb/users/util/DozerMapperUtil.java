package com.cb.users.util;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.DozerEventListener;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by oo on 17-6-4.
 */
public class DozerMapperUtil {
    private static DozerBeanMapper dozerBeanMapper;

    private static DozerBeanMapper initDozerMapper(List<? extends DozerEventListener> eventListeners){
        if(dozerBeanMapper==null){
            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

            dozerBeanMapper = (DozerBeanMapper) mapper;
            if(eventListeners!=null && eventListeners.size()>0){
                dozerBeanMapper.setEventListeners(eventListeners);
            }
        }

        return dozerBeanMapper;
    }

    public static <T> List<T> mapList(Collection originList, Class<T> cls, List<? extends DozerEventListener> eventListeners){
        List<T> lst = new ArrayList<T>();
        if(originList !=null){
            for (Object orginObj: originList){
                T t = null;
                if(orginObj!=null){
                    t = initDozerMapper(eventListeners).map(orginObj,cls);
                }
                lst.add(t);
            }
        }
        return lst;
    }

    public static <T> List<T> mapList(Collection originList,Class<T> cls,DozerEventListener eventListener){
        if(eventListener!=null){
            return mapList(originList,cls, Arrays.asList(eventListener));
        } else {
            return mapList(originList, cls,new ArrayList<DozerEventListener>());
        }
    }

    public static <T> List<T> mapList(Collection originList,Class<T> cls){
        return mapList(originList,cls,new ArrayList<DozerEventListener>());
    }

    public static <T> T map(Object origin,Class<T> cls,List<? extends DozerEventListener> eventListeners){
        T t = null;
        if(origin!=null){
            t = initDozerMapper(eventListeners).map(origin,cls);
        }
        return t;
    }

    public static <T> T map(Object origin,Class<T> cls,DozerEventListener eventListener){
        if(eventListener!=null){
            return map(origin, cls, Arrays.asList(eventListener));
        } else {
            return map(origin, cls, new ArrayList<DozerEventListener>());
        }
    }

    public static <T> T map(Object origin,Class<T> cls){
        return map(origin, cls,new ArrayList<DozerEventListener>());
    }

    public static void map(Object origin,Object target,List<? extends DozerEventListener> eventListeners){
        if(origin!=null && target!=null){
            initDozerMapper(eventListeners).map(origin,target);
        }
    }

    public static void map(Object origin,Object target,DozerEventListener eventListener){
        if(eventListener!=null){
            map(origin, target, Arrays.asList(eventListener));
        } else {
            map(origin, target, new ArrayList<DozerEventListener>());
        }
    }

    public static void map(Object origin,Object target){
        map(origin, target,new ArrayList<DozerEventListener>());
    }

}
