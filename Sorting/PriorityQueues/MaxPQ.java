public class MaxPQ<Key extends Comparable<Key>>{
    private static int N = 0;
    private Key[] pq;
    
    public MaxPQ(){
    }
    
    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN+1];
    }
    public MaxPQ(Key[] a){
    }
    
    public void insert(Key v){
        pq[++N] = v;
        swim(N); 
    }
    public Key max(){
        return pq[1];
    }
    public Key delMax(){
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    private void swim(int k){
        while(k > 1 && less(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }
    private void sink(int k){
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(j, j+1)) j++;
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    
    public void show(){
        for(int i = 1; i <= N; i++){
            System.out.printf("%.4f ",pq[i]);                
        }
        System.out.println();
    }
    public static void main(String[] args){
        int M = 10;
        MaxPQ<Double> pq = new MaxPQ<Double>(M+1);
        for(int i = 0; i < 15; i++){
            pq.insert(StdRandom.uniform());
            if(pq.size() > M)
                System.out.println(pq.delMax());            
        }
        
        pq.show();
    }
}