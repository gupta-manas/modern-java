package demo.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class StreamsDemo {

	static List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.VEG),
			new Dish("rice", true, 350, Dish.Type.VEG),
			new Dish("season fruit", true, 120, Dish.Type.VEG),
			new Dish("pizza", true, 550, Dish.Type.VEG),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH) 
			);

	static Trader raoul = new Trader("Raoul", "Cambridge");
	static Trader mario = new Trader("Mario","Milan");
	static Trader alan = new Trader("Alan","Cambridge");
	static Trader brian = new Trader("Brian","Cambridge");

	static List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2010, 710),
			new Transaction(mario, 2011, 700),
			new Transaction(alan, 2012, 950)
			);

	static List<Integer> numbers= Arrays.asList(1, 2, 3, 1, 17, 32, 0, 23);

	public static void main(String... args) {

		//Find 3 veg dishes
		/*List<String> names = menu.stream()                                   
	            .filter(dish -> dish.getCalories() > 300) 
	            .filter(Dish::isVegetarian)
	            .map(Dish::getName)                         
	            .limit(3)                                   
	            .collect(toList());

		System.out.println(names);
		 */

		//Find greatest number in a list
		/*Optional<Integer> max= numbers.stream()
				.reduce(Integer::max);

		System.out.println(max.get());
		 */
		System.out.println("----------DISHES STREAM-----------");
		System.out.println("\nGrouping veg and non-veg dishes:");
		Map<Dish.Type, List<Dish>> dishesByType= menu.stream()
				.collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);
		
		System.out.println("\nFind number of dishes");
		System.out.println(menu.stream().count());

		System.out.println("----------TRANSACTION STREAM-----------");
		System.out.println("\nGrouping transactions based on txn year");
		Map<Integer, List<Transaction>> groupingTxnByYear= transactions.stream()
				.collect(groupingBy(Transaction::getYear));
		System.out.println(groupingTxnByYear);
		
		System.out.println("\nFind all transactions in the year 2011 and sort them by value (small to high).");
		System.out.println(transactions.stream()
				.filter(txn -> txn.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList()));

		System.out.println("\nWhat are all the unique cities where the traders work?");
		System.out.println(
				transactions.stream()
				.map(txn -> txn.getTrader().getCity())
				.distinct()
				.collect(toList())
				);

		System.out.println("\nFind all traders from Cambridge and sort them by name.");
		System.out.println(
				transactions.stream()
				.map(Transaction::getTrader)
				.filter(trader -> trader.getCity().equals("Cambridge"))
				.distinct()
				.sorted(comparing(Trader::getName))
				.collect(toList())
				);

		System.out.println("\nReturn a string of all traders’ names sorted alphabetically.");
		System.out.println(
				transactions.stream()
				.map(txn -> txn.getTrader().getName())
				.distinct()
				.sorted()
				.collect(joining())
				);

		System.out.println("\nAre any traders based in Milan?");
		System.out.println(
				transactions.stream()
				.anyMatch(txn -> txn.getTrader().getCity().equals("Milan"))
				);

		System.out.println("\nPrint the values of all transactions from the traders living in Cambridge.");
		transactions.stream()
		.filter(txn -> txn.getTrader().getCity().equals("Cambridge"))
		.map(txn -> txn.getValue())
		.forEach(System.out::println);

		System.out.println("\nWhat’s the highest value of all the transactions?");
		System.out.println(
				transactions.stream()
				.map(Transaction::getValue)
				.reduce(0, Integer::max)
				);

		System.out.println("\nFind the transaction with the smallest value.");
		transactions.stream()
		.min(comparing(Transaction::getValue))
		.ifPresent(System.out::println);
	}

}
