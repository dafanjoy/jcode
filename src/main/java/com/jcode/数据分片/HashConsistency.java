package com.jcode.数据分片;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class HashConsistency {
	
	 // 真实节点列表
    private static List<Node> realNodes = new ArrayList<Node>();
 
    // 虚拟节点，key是Hash值，value是虚拟节点信息
    private static SortedMap<Integer, String> virtualMap = new TreeMap<Integer, String>();
 
    static {
    	//初始化真实节点列表
        realNodes.add(new Node("192.168.1.1", 5));
        realNodes.add(new Node("192.168.1.2", 10));
        realNodes.add(new Node("192.168.1.3", 20));
        realNodes.add(new Node("192.168.1.4", 5));
        for (Node node : realNodes) { //添加虚拟节点
            for (int i = 0; i < node.getLoad(); i++) {
                String server = node.getHost();
                String virtualNode = server + "&&VN" + i;
                int hash = getHash(virtualNode);
                virtualMap.put(hash, virtualNode);
            }
        }
    }
    
    /**
     * FNV1_32_HASH算法
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
 
    /**
     * 获取被分配的节点名
     * 
     * @param node
     * @return
     */
    public static Node getNode(String key) {
        int hash = getHash(key);//
        Integer keyNode = null;
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualMap.tailMap(hash);
        if (subMap.isEmpty()) {//在这里形成一个环形结构
        	 //如果没有比该key的hash值大的，则从第一个node开始
        	keyNode = virtualMap.firstKey();
        } else {
        	//获取第一个key值，也就是顺时针第一个节点
        	keyNode = subMap.firstKey();
        }
        String virtualNode = virtualMap.get(keyNode);//获取虚拟节点
        String realNodeName = virtualNode.substring(0, virtualNode.indexOf("&&"));
        for (Node node : realNodes) {//根据虚拟节点获取真实节点
            if (node.getHost().equals(realNodeName)) {
                return node;
            }
        }
        return null;
    }
 
    /**
     * 添加节点
     * 
     * @param node
     */
    public static void addNode(Node node) {
        if (!realNodes.contains(node)) {
            realNodes.add(node);
            System.out.println("真实节点[" + node + "] 上线添加");
            for (int i = 0; i < node.getLoad(); i++) {
                String virtualNode = node.getHost() + "&&VN" + i;
                int hash = getHash(virtualNode);
                virtualMap.put(hash, virtualNode);
                System.out.println("虚拟节点[" + virtualNode + "] hash:" + hash + "，被添加");
            }
        }
    }
 
    /**
     * 删除节点
     * 
     * @param node
     */
    public static void delNode(Node node) {
        String host = node.getHost();
        Iterator<Node> it = realNodes.iterator();
        while(it.hasNext()) {
        	Node nodeNext = it.next();
            if(nodeNext.getHost().equals(host)) {
                it.remove();
                System.out.println("真实节点[" + node + "] 下线移除");
                for (int i = 0; i < node.getLoad(); i++) {
                    String virtualNode = node.getHost() + "&&VN" + i;
                    int hash = getHash(virtualNode);
                    virtualMap.remove(hash);
              
                    System.out.println("虚拟节点[" + virtualNode + "] hash:" + hash + "，被移除");
                }
            }
        }
    }
 

 
    public static void main(String[] args) {
 
        // 模拟客户端的请求
        String[] nodes = { "127.0.0.1", "10.9.3.253", "192.168.10.1","127.1.0.1", "10.19.3.253", "192.168.120.1","192.168.10.1","127.1.0.1","192.168.10.1","127.1.0.1" };
 
        for (String node : nodes) {
            System.out.println("[" + node + "]的hash值为" + getHash(node) + ", 被路由到结点[" + getNode(node) + "]");
        }
 
        // 添加一个节点(模拟服务器上线)
        addNode(new Node("192.168.1.7", 10));
        // 删除一个节点（模拟服务器下线）
        delNode(new Node("192.168.1.1", 5));
 
        for (String node : nodes) {
            System.out.println("[" + node + "]的hash值为" + getHash(node) + ", 被路由到结点[" + getNode(node) + "]");
        }
    }
}
 
	/**
	 * 节点信息
	 *
	 */
	class Node {
	 
	    private String host;//IP信息
	 
	    private int load;//负载因子
	
		public String getHost() {
	        return host;
	    }
	 
	    public void setHost(String host) {
	        this.host = host;
	    }
	    
	    public int getLoad() {
			return load;
		}
	
		public void setLoad(int load) {
			this.load = load;
		}
	 
	
	    public Node(String host, int load) {
	        super();
	        this.host = host;
	        this.load = load;
	    }
	 
	    @Override
	    public String toString() {
	        return "Node [host=" + host + ", 负载因子=" + load + "]";
	    }
	}

