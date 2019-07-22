package array;

import java.util.Arrays;

public class TestMain {

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(arr.length);

		// 测试 for-each 循环
		for (int i : arr) {
			System.out.print(i + " ");
		}

		System.out.println();

		// 打印数组
		System.out.println(Arrays.toString(arr));

		int[] newArr = arr; // 两个变量引用同一个数组
		arr[10] = 678;
		System.out.println(newArr[10]);

		System.out.println();

		// 拷贝数组
		int[] copyArr = Arrays.copyOf(arr, 9); // 9表示拷贝的新数组长度，这个方法主要用在增加数组的大小
		System.out.println(Arrays.toString(copyArr));

		System.out.println();

		// 数组排序
		// 使用foreach时，是赋值给了一个临时变量，采用值传递，临时变量的改变并不会改变arr[i]本身
		// foreach 循环仅可用于遍历输出数组，但不能用于修改数组
		int[] randomArr = new int[10];
		for (int i = 0; i < randomArr.length; i++) {
			randomArr[i] = (int) (Math.random() * 100);
		}
		System.out.println("排序前的数组：");
		System.out.println(Arrays.toString(randomArr));
		System.out.println("排序后的数组：");
		Arrays.sort(randomArr); // 优化后的快速排序
		System.out.println(Arrays.toString(randomArr));

		System.out.println();

		// 多维数组
		int[][] multipartArr = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 0 } };

		// 遍历多维数组
		for (int i = 0; i < multipartArr.length; i++) {
			for (int j = 0; j < multipartArr[i].length; j++) {
				System.out.print(multipartArr[i][j] + " ");
			}
		}
		
		System.out.println();
		// 使用foreach循环
		for (int[] i : multipartArr) {
			for (int j : i) {
				System.out.print(j + " ");
			}
		}
		
		System.out.println();
		//第三种方法
		System.out.println(Arrays.deepToString(multipartArr));
	}

}
