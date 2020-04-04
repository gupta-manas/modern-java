package demo.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;

public class LambdaDemo {
	
	int a;

	public static void main(String[] args) {
		
		List<MyBean> myBeans= new ArrayList<>();
		
		myBeans.add(new MyBean(0, "test0023", false));
		myBeans.add(new MyBean(3, "test1122", true));
		myBeans.add(new MyBean(1, "33test4", false));
		myBeans.add(new MyBean(2, "test00", false));
		myBeans.add(new MyBean(3, "1test0", true));
		
		List<MyBean> evenNumList= myBeans.stream()
				.filter((bean) -> bean.getNum() % 2 == 0)
				.collect(Collectors.toList());
		
		List<MyBean> trueList= myBeans.stream()
				.filter((bean) -> bean.isBoolVal() == true)
				.collect(Collectors.toList());
		
		System.out.println("Beans with even num:\n" + evenNumList);
		System.out.println("Beans with true bolean value:\n" + trueList);
		
		System.out.println("----------------------------------------");
		System.out.println("myBean before sorting: \n");
		System.out.println(myBeans);
		myBeans.sort(comparing(MyBean::getNum));
		
		System.out.println("\nmyBean after sorting by num: \n");
		System.out.println(myBeans);
		
	}
	
	void scopeTest() {
		int b= 0;
		Function<String, Integer> stringToLength= (str) -> {
			
			//capturing instance variable
			System.out.println(a);
			
			//capturing local variable (b should be final or effectively final)
			System.out.println(b);
			
			return str.length();
			
		};
	}

}
