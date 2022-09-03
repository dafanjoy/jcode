package com.jcode.thread;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap.Node;
import java.util.HashMap.TreeNode;

public class HashMap {
	
	 /**
	  * 
	  * @param initialCapacity 初始化大小，但通过tableSizeFor计算会返回一个比给定整数大且最接近的2的幂次方整数，并赋值给threshold
	  * @param loadFactor  扩容因子 默认为DEFAULT_LOAD_FACTOR 即0.75
	  */
	 public HashMap(int initialCapacity, float loadFactor) {
	        if (initialCapacity < 0)
	            throw new IllegalArgumentException("Illegal initial capacity: " +
	                                               initialCapacity);
	        if (initialCapacity > MAXIMUM_CAPACITY)
	            initialCapacity = MAXIMUM_CAPACITY;
	        if (loadFactor <= 0 || Float.isNaN(loadFactor))
	            throw new IllegalArgumentException("Illegal load factor: " +
	                                               loadFactor);
	        this.loadFactor = loadFactor;
	        this.thres hold = tableSizeFor(initialCapacity);
	        ArrayList<String> list;
	    }


	    public HashMap(int initialCapacity) {
	        this(initialCapacity, DEFAULT_LOAD_FACTOR);
	    }

	    public HashMap() {
	        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
	    }

	    public HashMap(Map<? extends K, ? extends V> m) {
	        this.loadFactor = DEFAULT_LOAD_FACTOR;
	        putMapEntries(m, false);
	    }
	    
	    
	    
	    public V put(K key, V value) {
	    	//对key值做hash
	        return putVal(hash(key), key, value, false, true);
	    }

	    /**
	     * Implements Map.put and related methods
	     *
	     * @param hash hash for key
	     * @param key the key
	     * @param value the value to put
	     * @param onlyIfAbsent if true, don't change existing value
	     * @param evict if false, the table is in creation mode.
	     * @return previous value, or null if none
	     */
	    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
	                   boolean evict) {
	    	//继承 Map.Entry<K,V>的Node键值对数组
	        Node<K,V>[] tab; Node<K,V> p; int n, i;
	        //如果键值对数组为空则创建
	        if ((tab = table) == null || (n = tab.length) == 0)
	            n = (tab = resize()).length;
	        //计算index，即元素在数组中的下标位置，且判读是否为空
	        if ((p = tab[i = (n - 1) & hash]) == null)
	        	//如果为空，则赋值
	            tab[i] = newNode(hash, key, value, null);
	        else {//如果按下标位置已经有值，则代表出现了hash冲突，之前已经有值存储在了次下标位置
	            Node<K,V> e; K k;
	            //如果既不是红黑树也不是链表，那就只有是单个元素（Node<K,V>）了，那且hash值和key值相同，则直接覆盖
	            if (p.hash == hash &&
	                ((k = p.key) == key || (key != null && key.equals(k))))
	                e = p;
	            //如果是红黑树，则按红黑树处理
	            else if (p instanceof TreeNode)
	                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
	            else {
	            	//如果是链表，则按链表进行处理
	                for (int binCount = 0; ; ++binCount) {
	                    if ((e = p.next) == null) {
	                        p.next = newNode(hash, key, value, null);
	                        //如果链表长度》=8怎转换为红黑树处理
	                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
	                            treeifyBin(tab, hash);
	                        break;
	                    }
	                    //如果元素（Node<K,V>）已经存在（key相同），则直接覆盖
	                    if (e.hash == hash &&
	                        ((k = e.key) == key || (key != null && key.equals(k))))
	                        break;
	                    p = e;
	                }
	            }
	            if (e != null) { // existing mapping for key
	                V oldValue = e.value;
	                if (!onlyIfAbsent || oldValue == null)
	                    e.value = value;
	                afterNodeAccess(e);
	                return oldValue;
	            }
	        }
	        ++modCount;
	        //如果数组长度超过阈值，则进行扩容
	        if (++size > threshold)
	            resize();
	        afterNodeInsertion(evict);
	        return null;
	    }
	    
	    
	    
	    final Node<K,V>[] resize() {
	        Node<K,V>[] oldTab = table;
	        int oldCap = (oldTab == null) ? 0 : oldTab.length;
	        int oldThr = threshold;
	        int newCap, newThr = 0; //新的数组大小，新的阈值大小
	        if (oldCap > 0) {
	        	//如果超过最大值了，就给个最大值了
	            if (oldCap >= MAXIMUM_CAPACITY) {
	                threshold = Integer.MAX_VALUE;
	                return oldTab;
	            }
	            // 没超过最大值，就扩充为原来的2倍
	            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
	                     oldCap >= DEFAULT_INITIAL_CAPACITY)
	                newThr = oldThr << 1; // double threshold  //新的阈值
	        }
	        else if (oldThr > 0) //原数组为空且阈值大于0
	            newCap = oldThr; //，就把当前阈值的大小做为数组的初始化大小，这里newThr=0回在下面计算新的阈值大小
	        else {//原数组和默认的阈值都为空
	            newCap = DEFAULT_INITIAL_CAPACITY;//新的数组
	            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);//新的阈值为默认大小*默认扩容因子
	        }
	        if (newThr == 0) {//计算新的阈值大小
	            float ft = (float)newCap * loadFactor;
	            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
	                      (int)ft : Integer.MAX_VALUE);
	        }
	        threshold = newThr;//新的阈值
	        //把原数组中的数据转移到新数组中
	        @SuppressWarnings({"rawtypes","unchecked"})
	            Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
	        table = newTab;
	        if (oldTab != null) {
	            for (int j = 0; j < oldCap; ++j) {
	                Node<K,V> e;
	                if ((e = oldTab[j]) != null) {
	                    oldTab[j] = null;
	                    if (e.next == null)
	                        newTab[e.hash & (newCap - 1)] = e;
	                    else if (e instanceof TreeNode)
	                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
	                    else { // preserve order
	                        Node<K,V> loHead = null, loTail = null;
	                        Node<K,V> hiHead = null, hiTail = null;
	                        Node<K,V> next;
	                        do {
	                            next = e.next;
	                            if ((e.hash & oldCap) == 0) {
	                                if (loTail == null)
	                                    loHead = e;
	                                else
	                                    loTail.next = e;
	                                loTail = e;
	                            }
	                            else {
	                                if (hiTail == null)
	                                    hiHead = e;
	                                else
	                                    hiTail.next = e;
	                                hiTail = e;
	                            }
	                        } while ((e = next) != null);
	                        if (loTail != null) {
	                            loTail.next = null;
	                            newTab[j] = loHead;
	                        }
	                        if (hiTail != null) {
	                            hiTail.next = null;
	                            newTab[j + oldCap] = hiHead;
	                        }
	                    }
	                }
	            }
	        }
	        return newTab;
	    }


}
