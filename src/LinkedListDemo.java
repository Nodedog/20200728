public class LinkedListDemo {

    static class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node head;
    public LinkedListDemo() {
        this.head = null;
    }


    //头插法   时间复杂度 O（1）
    public void addFirst(int data){
        Node node = new Node(data);

        if (this.head == null){
            this.head = node;
            return;
        }
        node.next = this.head;//将head保存的地址给了 新定义的node的next存放
        this.head = node;//将node定义为 头 head
    }


    //打印单链表
    public void display(){
        Node cur =this.head;
        while (cur != null){
            System.out.println(cur.data + "");
            cur = cur.next;
        }
    }



    //尾插法  时间复杂度 是 O(n)
    public void addLast(int data) {
        Node node = new Node(data);
        if (this.head == null){
            this.head = node;
            return;
        }
        Node cur = this.head;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = node;
    }




    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index,int data){
        Node node = new Node(data);
        if (index < 0 || index > size()){
            return false;
        }
        if (index == 0){
            addFirst(data);
            return true;
        }
        if (index == size()){
            addLast(data);
            return true;
        }

        //任意位置时，
        Node cur = this.head;
        int count = 0;
        while (count == index - 1){
            cur = cur.next;
            count++;
        }
        node.next = cur.next;
        cur.next = node;
        return true;

    }

    //单链表长度
    private int size() {
        int count = 0;
        Node cur = this.head;
        while (cur != null){
            cur = cur.next;
            count++;
        }
        return count;
    }




    //查找是否包含关键字key是否在单链表当中
    public boolean contains(int key){
        Node cur = this.head;
        while (cur != null){
            if (cur.data == key){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    //删除第一次出现关键字为key的节点
    public void remove(int key){
        if (this.head.data == key){
            this.head = this.head.next;
            return;
        }
        Node prev = findKey(key);
        if (prev == null){
            System.out.println("没有key");
            return;
        }
        Node cur = prev.next;
        prev.next = cur.next;

    }

    private Node findKey(int key) {
        Node prev = this.head;
        while (prev.next != null){
            if (prev.next.data == key){
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }


    //删除所有值为key的节点
    public void removeAllKey(int key){
        Node prev = this.head;
        Node cur = prev.next;
        //思路1:判断头节点的data是否为key 如果是直接 this.head = this.head.next;
        if (prev.data == key ){
            this.head = this.head.next;
        }
        while (cur != null){
            if(cur.data == key){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = cur;
                cur = cur.next;
            }
        }
    }






    //找出倒数第K个节点
    public Node FindKthToTail(int k) {
        if(k <= 0 || k > size()) {
            return null;
        }
        Node fast = this.head;
        Node slow = this.head;
        //1、让fast先走k-1步
        for (int i = 0; i < k-1 ; i++) {
            fast = fast.next;
        }
        //2、让两个引用 一起走  直到 fast.next == null
        // slow 所指的位置就是倒数第K个节点
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    //清空单链表
    public void clear(){
        //this.head = null;
        while(this.head.next != null){
            Node del = this.head.next;
            this.head.next = del.next;
        }
        this.head = null;
    }
}
