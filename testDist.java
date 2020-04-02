package p01;

public class testDist {
	public static void main(String[] args) {
		int[] a = {0,0};
		int[] c = {1,0};
		int[] b = {1,0};
		System.out.println(drawLine(a,b,c));
	}
	public static boolean drawLine(int[] a,int[] b, int[] c) {
		int crossproduct = (c[1] - a[1]) * (b[0] - a[0]) - (c[0] - a[0]) * (b[1] - a[1]);
		if (Math.abs(crossproduct) != 0) {
			System.out.println("0");
			return false;
		}
		int dotproduct = (c[0] - a[0]) * (b[0] - a[0]) + (c[1] - a[1])*(b[1] - a[1]);
		if(dotproduct < 0){
			System.out.println("1");
			return false;
		}
		int squaredlengthba = (b[0] - a[0])*(b[0] - a[0]) + (b[1] - a[1])*(b[1] - a[1]);
		if(dotproduct > squaredlengthba){
			System.out.println("2");
			return false;
		}
		return true;   
	}

}
