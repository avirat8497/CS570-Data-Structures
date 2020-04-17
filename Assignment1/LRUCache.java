package edu.stevens.cs570.assignments;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ravi Varadarajan on 2/22/2018.
 */
public class LRUCache<K,T extends Cacheable<K>> {

    // list in LRU order
    private final List<T> lruList;
     lruList = new ArrayList<>();
    public static List<T> convertALtoLL(List<T> lruList)
    {
        List<T> lruList1 = new LinkedList<>(lruList);
        return lruList1;
    }


    /**
     * To be implemented!!
     * Iterator class only for only keys of cached items; order should be in LRU order, most recently used first
     *
     * @param <K>
     */
    private class CacheKeyIterator<K> implements Iterator<K> {

        @Override
        public boolean hasNext() {
            return index<lruList.size();
        }

        @Override
        public K next() {
            if(index < lruList.size()){
                return (K) lruList.get(index++);
            }
            else{

                return null;
            }

        }
    }

    /**
     * Constructor
     *
     * @param size      initial size of the cache which can change later
     * @param persister persister instance to use for accessing/modifying evicted items
     */
    public LRUCache(int size, Persister<? extends K, ? extends T> persister) {
        int size = 0;
        lruList = null;
    }

    public void modifySize(int newSize) {
        this.size  = size;


    }

    /**
     * Get item with the key (need to get item even if evicted)
     *
     * @param key
     * @return
     */
    public T getItem(K key){
        if T.get(key) == null;
        return -1 ;

    }

    /**
     * Add/Modify item with the key
     *
     * @param item item to be put
     */
    public void putItem(T item) {
    }

    /**
     * Remove an item with the key from cache as well as from persistent store
     *
     * @param key
     * @return item removed or null if it does not exist
     */
    public T removeItem(K key) {
        return null;
    }

    /**
     * Get cache keys
     *
     * @return
     */
    public Iterator<K> getCacheKeys() {
        return new CacheKeyIterator<>();
    }

    /**
     * Get fault rate (proportion of accesses (only for retrievals and modifications) not in cache)
     *
     * @return
     */
    public double getFaultRatePercent() {
        return 0.0;
    }

    /**
     * Reset fault rate stats counters
     */
    public void resetFaultRateStats() {
    }

    public static void main(String[] args) {
        LRUCache<String, SimpleCacheItem> cache = new LRUCache<>(20, new SimpleFakePersister<>());
        for (int i = 0; i < 100; i++) {
            cache.putItem(new SimpleCacheItem("name" + i, (int) (Math.random() * 200000)));
            String name = "name" + (int) (Math.random() * i);
            SimpleCacheItem cacheItem = cache.getItem(name);
            if (cacheItem != null) {
                System.out.println("Salary for " + name + "=" + cacheItem.getAnnualSalary());
            }
            cache.putItem(new SimpleCacheItem("name" + (int) (Math.random() * i), (int) (Math.random() * 200000)));
            name = "name" + (int) (Math.random() * i);
            //cache.removeItem(name);
            System.out.println("Fault rate percent=" + cache.getFaultRatePercent());
        }
        for (int i = 0; i < 100; i++) {
            String name = "name" + (int) (Math.random() * 100);
            SimpleCacheItem cacheItem = cache.getItem(name);
            if (cacheItem != null) {
                System.out.println("Salary for " + name + "=" + cacheItem.getAnnualSalary());
            }
            System.out.println("Fault rate percent=" + cache.getFaultRatePercent());
        }
        for (int i = 0; i < 30; i++) {
            String name = "name" + (int) (Math.random() * i);
            cache.removeItem(name);
        }
        cache.resetFaultRateStats();
        cache.modifySize(50);
        for (int i = 0; i < 100; i++) {
            cache.putItem(new SimpleCacheItem("name" + i, (int) (Math.random() * 200000)));
            String name = "name" + (int) (Math.random() * i);
            SimpleCacheItem cacheItem = cache.getItem(name);
            if (cacheItem != null) {
                System.out.println("Salary for " + name + "=" + cacheItem.getAnnualSalary());
            }
            cache.putItem(new SimpleCacheItem("name" + (int) (Math.random() * i), (int) (Math.random() * 200000)));
            name = "name" + (int) (Math.random() * i);
            cache.removeItem(name);
            System.out.println("Fault rate percent=" + cache.getFaultRatePercent());
        }
    }

}


