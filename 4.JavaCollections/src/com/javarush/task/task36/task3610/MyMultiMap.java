package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int result = 0;

        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            for (V listValue : entry.getValue()) {
                result++;
            }
        }

        return result;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        /*List<V> valueList = null;
        if (map.containsKey(key)) {
            for (Map.Entry<K, List<V>> entry : map.entrySet()) {
                if (entry.getKey() == key) {
                    valueList = entry.getValue();
                    if (valueList.size() < repeatCount) {
                        valueList.add(value);
                    } else if (valueList.size() == repeatCount) {
                        valueList.remove(0);
                        valueList.add(value);
                    }
                    map.put(key, valueList);
                    break;
                }
            }
        } else {
            List<V> vList = new ArrayList<>();
            vList.add(value);
            map.put(key, vList);
        }
        try {
            return valueList.size() == 1 ? null : valueList.get(valueList.size() - 2);
        } catch (NullPointerException e) {
            return null;
        }*/

        List<V> values = map.get(key);
        V oldValue = null;

        if (values == null) {
            values = new ArrayList<>();
        } else {
            oldValue = values.get(values.size() - 1);
            if (values.size() == repeatCount) {
                values.remove(0);
            }
        }
        values.add(value);
        map.put(key, values);
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        /*V result = null;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getKey() == key) {
                List<V> valueList = entry.getValue();
                if (valueList.size() == 0) {
                    map.remove(entry.getKey(), entry.getValue());
                } else {
                    result = valueList.get(0);
                    valueList.remove(0);
                    map.put((K) key, valueList);
                }
            }
        }
        return result;*/
        List<V> values = map.get(key);
        if (values == null) {
            return null;
        }
        V storeValue = values.get(0);
        values.remove(0);

        if (values.size() == 0) {
            map.remove(key);
        }
        return storeValue;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        // должен вернуть ArrayList<V> всех значений. Порядок значений в листе не имеет значения.
        List<V> resultList = new ArrayList<>();
        for (List<V> vList : map.values()) {
            resultList.addAll(vList);
        }
        return resultList;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
       /* boolean result = false;
        for (Entry<K, List<V>> entry : map.entrySet()) {
            for (V v : entry.getValue()) {
                if (v.equals(value)) {
                    result = true;
                }
            }
        }
        return result;*/
       return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}