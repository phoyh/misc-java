import java.util.ArrayList;

public class HeapSort {
	static class Heap<T extends Comparable<T>> {
		ArrayList<T> vals;
		Heap() {
			vals = new ArrayList<T>();
		}
		void add(T val) {
			int idx = vals.size();
			vals.add(val);
			while (idx > 0) {
				int pidx = (idx - 1) / 2;
				if (vals.get(pidx).compareTo(val) <= 0) return;
				vals.set(idx, vals.get(pidx));
				vals.set(pidx, val);
				idx = pidx;
			}
		}
		T pop() {
			T result = vals.get(0);
			if (vals.size() == 1) {
				vals.clear();
				return result;
			}
			T val = vals.remove(vals.size() - 1);
			vals.set(0, val);
			int idx = 0;
			while (true) {
				int cidx = idx * 2 + 1;
				if (cidx >= vals.size()) return result;
				if (cidx + 1 < vals.size() && vals.get(cidx + 1).compareTo(vals.get(cidx)) < 0) cidx++;
				if (vals.get(cidx).compareTo(val) >= 0) return result;
				vals.set(idx, vals.get(cidx));
				vals.set(cidx, val);
				idx = cidx;
			}
		}
		int size() {
			return vals.size();
		}
	}

	public static void main(String[] args) {
		Heap<Integer> h = new Heap<Integer>();
		h.add(4);
		h.add(1);
		h.add(5);
		h.add(-3);
		h.add(8);
		h.add(3);
		while (h.size() > 0) System.out.println(h.pop());
	}
}
