package jom.com.softserve.s3.task3;

public class Operation {
	public static double addAtoB(int a, int b) {
//		return execute(a, b, (x, y) -> Operation.addAtoB(x, y));
		return a + b;
	}

	public static double subtractBfromA(int a, int b) {
//		return execute(a, b, (x, y) -> Operation.subtractBfromA(x, y));
		return a - b;
	}

	public static double multiplyAbyB(int a, int b) {
//		return execute(a, b, (x, y) -> Operation.multiplyAbyB(x, y));
		return a * b;
	}

	public static double divideAbyB(int a, int b) {
//		return execute(a, b, (x, y) -> {
//			if (y != 0) {
//				return Operation.divideAbyB(x, y);
//			} else {
//				throw new ArithmeticException("Division by zero is not allowed.");
//			}
//		});
		if (b != 0) {
			return (double) (a / b); // Perform integer division and cast to double
		} else {
			throw new ArithmeticException("Division by zero is not allowed.");
		}
	}


	public static double execute(int a,int b,Strategy strategy){
		double result = strategy.doOperation(a,b);
		System.out.println(result);
		return result;
	}
}
