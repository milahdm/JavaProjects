import java.util.ArrayList;


public class BinaryTree<T> implements Sequence<T>, ReverseSequence<T> {

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public BinaryTree() {
        root = null;
    }

    // time: O(n) space: O(log n)
    private Node build_inorder(ArrayList<T> A, int begin, int end)
    {
        if (begin == end) // O(1)
        {
            return null;
        } else if (begin + 1 == end)
        {
            return new Node(A.get(begin), null, null, null); // O(1)
        } else {
            int mid = begin + ((end - begin) / 2);
            Node n = new Node(A.get(mid), null, null, null);
            Node left = build_inorder(A, begin, mid); // O(n/2) , O(log n)
            Node right = build_inorder(A, mid+1, end); // O(n/2) , O(log n)
            n.left = left; // O(1)
            n.right = right;
            if (left != null)
                left.parent = n;
            if (right != null)
                right.parent = n;
            return n;
        }
    }

    // Build a tree from the array, inorder.
    public BinaryTree(ArrayList<T> L) {
        root = build_inorder(L, 0, L.size());
    }

    public BinaryTree(Node r) {
        root = r;
    }

    @Override
    public Iter begin()
    {
        if (root == null)
            return new Iter(null);
        else
            return new Iter(root.first());
    }

    @Override
    public Iter end() {
        return new Iter(null);
    }

    @Override
    public Iter rbegin() {
        if (root == null)
            return new Iter(null);
        else
            return new Iter(root.last());
    }

    @Override
    public Iter rend() {
        return new Iter(null);
    }

    class Node {
        T data;
        Node left, right, parent;

        public String toString() {
            return data.toString();
        }

        public Node(T d, Node l, Node r, Node p) {
            data = d;
            left = l;
            right = r;
            parent = p;
        }

        // Return the first node wrt. inorder in this subtree.
        public Node first() {
            Node n = this; // current node

            while(n.left != null) // go all the way down the left side of the tree. time: O(n)
            {
                n = n.left;
            }

            return n; // return the first leaf node in order
        }

        // Return the last node wrt. inorder in this subtree.
        public Node last() {
            Node n = this; // current node

            while(n.right != null) // go all the way down the right side of the tree. time: O(n)
            {
                n = n.right;
            }

            return n; // return last leaf node on right side
        }

        // Return the first ancestor that is next wrt. inorder
        // or null if there is none.
        public Node nextAncestor() {
            Node n = this;

            if(n.right != null)
            {
                return n.right.first();
            }

            Node current = n;
            Node currParent = n.parent;

            while(currParent != null && current == currParent.right)
            {
                current = currParent;
                currParent = currParent.parent;
            }

            return currParent;

        }

        // Return the first ancestor that is previous wrt. inorder
        // or null if there is none.
        public Node prevAncestor()
        {
            Node n = this;

            if(n.left != null)
            {
                return n.left.last();
            }

            Node current = n;
            Node currParent = n.parent;

            while(currParent != null && current == currParent.left)
            {
                current = currParent;
                currParent = currParent.parent;
            }

            return currParent;
        }

        // Return the next node wrt. inorder in the entire tree.

        public Node next()
        {
            Node n = this;

            if (n.right != null)
            {
                return n.right.first();
            } else {
                return n.nextAncestor();
            }

        }


        public Node previous()
        {
            Node n = this;

            if (n.left != null)
            {
                return n.left.last();
            } else
            {
                return n.prevAncestor();
            }


        }

    }

    class Iter implements Iterator<T>, ReverseIterator<T>
    {
        private Node curr;

        Iter(Node c) {
            curr = c;
        }

        public String toString() {
            if (curr == null) return "null";
            else return curr.toString();
        }

        @Override
        public T get() {
            if (curr != null)
            {
                return curr.data;
            }
            else
            {
                return null;
            }
        }

        @Override
        public void retreat() {
            if (curr != null)
            {
                curr = curr.previous();
            }

        }

        @Override
        public void advance() {
            if (curr != null)
            {
                curr = curr.next();
            }
            else
            {
                throw new IllegalStateException("Can't advance, curr is null.");
            }
        }

        @Override
        public boolean equals(Object other)
        {
            assert other instanceof Iterator;
            return (curr == ((Iter)other).curr);



        }

        @Override
        public Iter clone()
        {
            return new Iter(curr);
        }
    }
}


