package edu.stevens.cs570.assignments;

/**
 * Created by Ravi Varadarajan on 9/15/2019
 */
public interface Persister<K,T extends Cacheable<K>> {

    /**
     * persist item to be retrieved later
     * @param item
     */
    void persistItem(T item);

    /**
     * Get item for key from persistent store
     * @param key
     * @return
     */
    T getItem(K key);

    /**
     * Remove value associated with key from persistent store
     * @param key
     * @return
     */
    T removeItem(K key);
}
