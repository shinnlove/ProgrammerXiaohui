package chapter6.part3;

import java.util.HashMap;


public class LRUCache {

    // 两个指针来确认链表头部和尾部位置
    private Node head;
    private Node end;
    //缓存存储上限
    private int limit;

    // 使用hashMap来做O(1)时间复杂度的访问
    private HashMap<String, Node> hashMap;

    public LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<String, Node>();
    }

    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null){
            return null;
        }
        // 一旦refresh一个node，这个node就是移到尾部
        refreshNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            //如果key不存在，插入key-value
            if (hashMap.size() >= limit) {
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        }else {
            //如果key存在，刷新key-value
            node.value = value;
            refreshNode(node);
        }
    }

    public void remove(String key) {
        Node node = hashMap.get(key);
        if(node == null){
            return;
        }
        removeNode(node);
        hashMap.remove(key);
    }

    /**
     * 刷新被访问的节点位置
     * 
     * 如果当前要刷新的节点为A，
     * 如果是尾结点，则不需要动；
     * 如果是头结点，next给到头结点，变成尾结点；
     * 如果是中间节点，则把A的next给到A.before.next、把A.before给到A.next.before，让他们接起来；（这一步就是移除节点、操作头尾节点时处理下指针）
     * 然后在尾部插入节点，就是把A给到end.next、end给到A.before、A的next置为null、最后把A给到end；（特别注意考虑头尾节点空不空情况）
     * 
     * @param  node 被访问的节点
     */
    private void refreshNode(Node node) {
        //如果访问的是尾节点，无需移动节点
        if (node == end) {
            return;
        }
        //移除节点
        removeNode(node);
        //重新插入节点
        addNode(node);
    }

    /**
     * 删除节点
     * @param  node 要删除的节点
     */
    private String removeNode(Node node) {
        if(node == head && node == end){
            //移除唯一的节点
            head = null;
            end = null;
        }else if(node == end){
            //移除尾节点
            end = end.pre;
            end.next = null;
        }else if(node == head){
            //移除头节点
            head = head.next;
            head.pre = null;
        }else {
            //移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     * @param  node 要插入的节点
     */
    private void addNode(Node node) {
        if(end != null) {
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if(head == null){
            head = node;
        }
    }

    class Node {
        Node(String key, String value){
            this.key = key;
            this.value = value;
        }
        public Node pre;
        public Node next;
        public String key;
        public String value;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", "用户1信息");
        lruCache.put("002", "用户2信息");
        lruCache.put("003", "用户3信息");
        lruCache.put("004", "用户4信息");
        lruCache.put("005", "用户5信息");
        lruCache.get("002");
        lruCache.put("004", "用户4信息更新");
        lruCache.put("006", "用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
