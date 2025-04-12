import java.util.Arrays;

public class ShakerSort {
	private static interface Sort {
		public void exec(int[] list);
	}

	private static class SortByModeledRange implements Sort {
		@Override
		public void exec(int[] list) {
			if (list.length < 2) return;
			boolean hasChanged = true;
			int[][] ranges = new int[][] {
				new int[] {0, list.length - 1, 1},
				new int[] {list.length - 2, -1, -1}
			};
			for (int r = 0; r < list.length; r++) {
				hasChanged = false;
				int[] range = ranges[r % 2];
				for (int i = range[0]; i != range[1]; i += range[2]) {
					if (list[i] > list[i + 1]) {
						int a = list[i];
						list[i] = list[i + 1];
						list[i + 1] = a;
						hasChanged = true;
					}
				}
				if (!hasChanged) return;
			}
		}
	}

	private static class SortByImperativeRange implements Sort {
		@Override
		public void exec(int[] list) {
			if (list.length < 2) return;
			boolean hasChanged = true;
			int start = 0;
			int end = list.length - 1;
			int dx = 1;
			while (hasChanged) {
				hasChanged = false;
				for (int i = start; i != end; i += dx) {
					if (list[i] > list[i + 1]) {
						int a = list[i];
						list[i] = list[i + 1];
						list[i + 1] = a;
						hasChanged = true;
					}
				}
				dx = -dx;
				start = list.length - 2 - start;
				end = list.length - 2 - end;
			}
		}
	}

	private static class SortByDynamicRange implements Sort {
		@Override
		public void exec(int[] list) {
			boolean hasChanged = true;
			int dx = 1;
			while (hasChanged) {
				hasChanged = false;
				for (int i = (dx > 0) ? 0 : list.length - 2; 0 <= i && i < list.length - 1; i += dx) {
					if (list[i] > list[i + 1]) {
						int a = list[i];
						list[i] = list[i + 1];
						list[i + 1] = a;
						hasChanged = true;
					}
				}
				dx = -dx;
			}
		}
	}

	private static void sortAndPrint(Sort s, int[] list) {
		int[] l = list.clone();
		s.exec(l);
		System.out.println(Arrays.stream(l)
			.mapToObj(i -> Integer.toString(i))
			.reduce("", (result, i) -> result + i + ", "));
	}

	public static void main(String[] args) {
		int[] demo = new int[] {6, 2, 9, 4, 1};

		sortAndPrint(new SortByDynamicRange(), demo);
		sortAndPrint(new SortByImperativeRange(), demo);
		sortAndPrint(new SortByModeledRange(), demo);
	}
}