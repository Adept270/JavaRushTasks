package com.javarush.task.task34.task3408;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        if (!cache.containsKey(key)) {
            cache.put(key, clazz.getConstructor(key.getClass()).newInstance(key));
        }

        return cache.get(key);
    }

    public boolean put(V obj) throws Exception {
        //TODO add your code here
        /**
         * 3. Реализуй логику метода put:
         3.1. Используя рефлексию получи ссылку на метод, описанный в пункте б).
         3.2. Используя рефлексию разреши к нему доступ.
         3.3. Используя рефлексию вызови метод getKey у объекта obj, таким образом ты получишь ключ key.
         3.4. Добавь в кэш пару <key, obj>.
         3.5. Верни true, если метод отработал корректно, false в противном случае. Исключения игнорируй
         */

        Method method = null;

        try {
            method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);

            cache.put((K) method.invoke(obj), obj);
            return cache.containsKey((K) method.invoke(obj));

        } catch (Exception ignore) {

        }



        return false;
    }

    public int size() {
        return cache.size();
    }
}
