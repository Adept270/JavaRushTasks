package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        /*
        Метод AnyObject get(Long key) должен возвращать объект типа AnyObject из мапы cacheMap по ключу key.
        Если такого ключа в cacheMap нет - верни null.
         */

        //напишите тут ваш код
        if (!cacheMap.containsKey(key)) {
            return null;
        }
        return softReference.get();

    }

    public AnyObject put(Long key, AnyObject value) {
        /*
        Метод AnyObject put(Long key, AnyObject value) должен добавлять в мапу пару key : value.
        Метод должен вернуть null, если в cacheMap по такому ключу ранее не было значения.
        Иначе - верни предыдущее значение value по этому ключу. Не забудь вызвать метод clear()
        у объекта типа SoftReference<AnyObject>.
         */
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));

        //напишите тут ваш код
        if (softReference != null) {
            AnyObject o = softReference.get();
            softReference.clear();
            return o;
        }
        else {
            return null;
        }
    }

    public AnyObject remove(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.remove(key);

        /*
        Метод AnyObject remove(Long key) должен удалить из мапы cacheMap пару key : value по ключу key.
        Метод должен вернуть null, если в cacheMap по такому ключу ранее не было значения.
        Иначе - верни предыдущее значение value по этому ключу. Не забудь вызвать метод clear()
        у объекта типа SoftReference<AnyObject>.
         */

        //напишите тут ваш код
        if (softReference != null) {
            AnyObject o = softReference.get();
            softReference.clear();
            return o;
        }
        else {
            return null;
        }
    }
}