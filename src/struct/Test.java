package struct;


class test {
	public test(Object obj) {
		System.out.println(obj);
	}
}

public class Test {
	public static void main(String[] args) {
		test t = new test(test.class);
	}
}
