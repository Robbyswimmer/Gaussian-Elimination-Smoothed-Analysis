import java.util.*;


public class CuthillMckee {
    List<Double> list = new ArrayList<Double>();

    public int findIndex(int[] a, int x) {

        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    private class reordering{

        private double[][] matrix;

        reordering(double[][] m) {
            this.matrix = m;
        }

        public double[] degreeGenerator() {
            double[] degrees = new double[matrix.length];

            for (int i = 0; i < matrix.length; i++) {
                double count = 0;

                for (int j = 0; j < matrix[0].length; j++) {
                    count += matrix[i][j];
                }
                degrees[i] = count;
            }
            return degrees;
        }

        public List<Integer> CuthillMcKee() {
            double[] degrees = degreeGenerator();

            Queue<Integer> q = new LinkedList<Integer>();
            List<Integer> R = new ArrayList<Integer>();
            Map<Integer, Double> map = new HashMap<Integer, Double>();

            for (int i = 0; i < degrees.length; i++) { map.put(i, degrees[i]); }

            while (map.size() > 0) {
                int minNodeIndex = 0;
                for (int j = 0; j < map.size(); j++) {
                    if (map.get(j) < map.get(minNodeIndex)) {
                        minNodeIndex = j;
                    }
                }
                ((LinkedList<Integer>) q).push(minNodeIndex);
                map.put(0, 0.0);
                while (!q.isEmpty()) {
                    List<Integer> toSort = new ArrayList<Integer>();

                    for (int i = 0; i < matrix[0].length; i++) {
                        if (i != q.peek() && matrix[q.peek()][i] == 1) {
                            toSort.add(i);
                            map.put(i, 0.0);
                        }
                    }
                    Collections.sort(toSort);
                    for (int i = 0; i < toSort.size(); i++) {
                        ((LinkedList<Integer>) q).push(toSort.get(i));
                    }
                    R.add(q.remove());
                }
            }
            return R;
        }

        public List<Integer> reverseCuthill() {
            List<Integer> cuthill = new ArrayList<Integer>(CuthillMcKee());
            int n = cuthill.size();
            if (n % 2 == 0) { n -= 1; }
            n /= 2;
            for (int i = 0; i <= n; i++) {
                int j = cuthill.get(cuthill.size() - 1 - i);
                int temp = cuthill.get(cuthill.size() - 1 - i);
                cuthill.add(i, temp);
                cuthill.add(j, i);
            }
            return cuthill;
        }
    }
}
