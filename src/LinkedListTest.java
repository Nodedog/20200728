/*
*
*                       单链表的面试题
*
*
*
* */



public class LinkedListTest {
    static class Node{
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node head;
    public LinkedListTest() {
        this.head = null;
    }


    //1. 删除链表中等于给定值 **val** 的所有节点。
    public void removeAllKey(int key){
        Node prev = this.head;
        Node cur = prev.next;
        if (prev.data == key ){
            this.head = this.head.next;
        }
        while (cur != null){
            if (cur.data == key){
                prev.next = cur.next;
                cur = cur.next;
            }else {
                prev = prev.next;
                cur = cur.next;
            }
        }
    }



    //2. 反转一个单链表。
    public Node exchangList() {
        Node cur = this.head;
        Node newHead = null;
        //prev是cur的上一个节点
        Node prev = null;
        while (cur != null){
            Node curNext = cur.next;
            if (curNext == null){
                newHead = cur;
            }
            //让cur指向prev
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }






    // 3. 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
    public Node middleNode(){
        Node fast = this.head;
        Node slow = this.head;
        //记住固定方法，这个方法不管size是单数还是双数
        //让fast两步两步走 slow 一步一步走  走到fast 和fast。next=null时 slow对应的就是中间节点
        // 或者中间节点的第二个
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }





    //4. 输入一个链表，输出该链表中倒数第k个结点。
    public Node node(int k){

        if(k <= 0 || k > size()) {
            return null;
        }
        Node fast = this.head;
        Node slow = this.head;
        //固定方法
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



    //5. 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    public  Node mergeTwoLists(Node headA, Node headB) {
        Node newhead = new Node(-1);
        Node cur = newhead;
        while (headA != null && headB != null){
            if (headA.data < headB.data){
                cur.next = headA;
                headA = headA.next;
                cur = cur.next;
            }else {
                cur.next = headB;
                headB = headB.next;
                cur = cur.next;
            }
            if (headA != null) {
                cur.next = headA;
            }
            if (headB != null) {
                cur.next = headB;
            }
        }
        return newhead.next;
    }






    //6. 编写代码，以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前 。
    public Node partition(int x){
        //bs、be分别为小于x节点左侧的首和尾、
        //as、ae分别为大于x节点右侧的首和尾
        Node bs = null;
        Node be = null;
        Node as = null;
        Node ae = null;
        Node cur = this.head;
        while (cur != null){
            if (cur.data < x){
                //第一次插入直接将cur赋值给bs，be等于bs
                //第二次以上插入，让be.next为cur，be=be.next往后挪一位
                if (bs == null){
                    bs = cur;
                    be = bs;
                }else {
                    be.next = cur;
                    be = be.next;
                }
            }
            if (cur.data > x){
                if (as == null){
                    as = cur;
                    ae = as;
                }else {
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        //如果小于x的没有 则直接返回as即大于x的右侧节点
        if (bs == null){
            return as;
        }
        //如果小于x左侧结点有则拼接be和as，再将ae的next置为null，最后返回bs
        be.next = as;
        if (as != null){
            ae.next = null;
        }
        return bs;
    }






    // 7. 在一个 排序  的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 [OJ链接]
    public Node removeSame() {
        Node node = new Node(-1);
        Node tmp =  node;
        Node cur = this.head;
        while (cur != null){
            if (cur.next != null && cur.data == cur.next.data){
                while (cur.next != null && cur.data == cur.next.data){
                    cur = cur.next;
                }
                cur = cur.next;
            }else {
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        //防止尾节点也重复 删掉之后 保留的 最后一个节点的next保存的是 下一个节点的地址所以直接将tmp置为null
        tmp.next = null;
        return node.next;
    }




    // 8. 判断链表的回文结构。
    public boolean palinDrome (){
        //1.先找到中间节点
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //2.局部反转 slow后面那部分 反转
        Node cur = slow.next;
        while (cur != null){
            Node curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //3.判断
        while (slow != this.head){
            if (slow.data != this.head.data){
                return false;
            }
            //偶数情况下
            if (this.head.next == slow){
                return true;
            }
            slow = slow.next;
            this.head = this.head.next;
        }
        return true;
    }






    // 9. 输入两个链表，找出它们的第一个公共结点。
    public static Node towNode(Node headA, Node headB) {
        if (headA == null || headB == null){
            return null;
        }
        //定义两个长度
        int lenA = 0;
        int lenB = 0;
        //定义两个节点
        Node pL = headA;
        Node pS = headB;
        //分别计算出pL lenA和pS lenB的长度
        while (pL != null){
            pL = pL.next;
            lenA++;
        }
        while (pS != null){
            pS = pS.next;
            lenB++;
        }
        //因为上面 两个循环 导致pL和pS 为null 所以要重新指想 head
        pL = headA;
        pS = headB;
        int len = lenA - lenB;
        //保证pL一定指向长的那条线
        if (len < 0 ){
            pL = headB;
            pS = headA;
            len = lenB - lenA;
        }
        //让pL走差值步 ，pL和pS从同一起跑线准备走
        while (len > 0){
            pL = pL.next;
            len--;
        }
        //然后pL和pS一起走 走到他两的next相同 也就是到第一个公共节点停下来
        while (pL != pS && pL != null){
            pL = pL.next;
            pS = pS.next;
        }
        //上面循环走完pL和pS指向同一结点，判断长的pL不为空就直接返回pL
        if (pL == pS && pL !=null){
            return pL;
        }
        return null;
    }
}
