package project.green.shop.security;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.Assert;

import project.green.shop.security.TestMain2.TestMain3;
import project.green.shop.security.TestMain2.TestMain4;

public class TestMain {

	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	public static void main(String[] args) {

		try {

			/*
			 * List list2 = new ArrayList<>(); list2.add("U"); list2.add("D");
			 * list2.add("O"); list2.add("P");
			 * 
			 * Set<Object> list = new TreeSet<Object>(); list.add("C"); list.add("B");
			 * list.add("A");
			 * 
			 * String b = "22"; String c = "333333"; String a = "yuri"; int i = 5; for
			 * (Object o : list2) { list.add(o); } char[] t = c.toCharArray(); Integer x =
			 * Integer.valueOf(i); int q = Integer.parseInt(c); StringBuilder stringBuilder
			 * = new StringBuilder(); // Custumer custum = new Custumer();
			 * stringBuilder.append(b); stringBuilder.append(2014);
			 * stringBuilder.append(list); // stringBuilder.append(custum); StringBuilder d
			 * = stringBuilder; String e = new String("you"); list.forEach((element) ->
			 * System.out.println(element));
			 * 
			 * List<TestMain2> list3 = new ArrayList<TestMain2>(); list3.add(new
			 * TestMain2("Quân", 753)); list3.add(new TestMain2("Tuấn", 242)); list3.add(new
			 * TestMain2("Huy", 121));
			 * 
			 * List<TestMain3> list4 = new ArrayList<TestMain3>(); list4.add(new
			 * TestMain2().new TestMain3(1, 552)); list4.add(new TestMain2().new
			 * TestMain3(3, 667)); list4.add(new TestMain2().new TestMain3(2, 10000));
			 */

			List<TestMain4> list = new ArrayList<TestMain4>();
			list.add(new TestMain2().new TestMain4("Tuấn", 180000, LocalDate.now()));
			list.add(new TestMain2().new TestMain4("Quân", 270000, LocalDate.of(2022, 01, 15)));
			list.add(new TestMain2().new TestMain4("Hoàng", 300000, LocalDate.of(2022, 01, 26)));
			list.add(new TestMain2().new TestMain4("Huy", 150000, LocalDate.of(2022, 02, 15)));
			list.add(new TestMain2().new TestMain4("Nam", 230000, LocalDate.of(2022, 01, 9)));
			list.add(new TestMain2().new TestMain4("Nam", 230000, LocalDate.of(2022, 01, 9)));
			int total = 0;
			// Dùng vòng lặp for
			/*
			 * for (int i = 0; i < list.size(); i++) if (list.get(i).born.getMonthValue() ==
			 * 1) { total = total + list.get(i).getPrice(); }
			 */
			//System.out.println("Tổng lương người sinh tháng 1 khi dùng for " + total);

			// Dùng Stream

			//total = list.stream().filter(p -> p.born.getMonthValue() == 1).mapToInt(k -> k.getPrice()).sum();
			//System.out.println("Khi dùng Stream " + total);
			//list.stream().filter(o->"Hoàng".equals(o.getName())).forEach(u->System.out.println(u.getName()));
			 //Stream<TestMain4>list2 =  list.stream();
			// Set<TestMain4> list3 = list.stream().collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(TestMain4::getName))));
			 //list3.forEach(o->System.out.println(o.name));
			 System.out.println(list.getClass().getFields());
			 
			// list2.forEach(k->total+k.getPrice());System.out.println(total);
			/*
			 * list.forEach(p->{if (p.born.getMonthValue()== 1 ) total = p.price;
			 * System.out.println(p.price);});
			 */
			/*
			 * System.out.println(list4); Collections.sort(list3, (p1, p2) -> { return
			 * Integer.valueOf(p1.price).compareTo(Integer.valueOf(p2.price));});
			 * list3.forEach(p->System.out.println(p.getName()));
			 * 
			 * Collections.sort(list4,(k1,k2) ->{return
			 * Integer.valueOf(k2.price1).compareTo(Integer.valueOf(k1.price1));});
			 * list4.forEach(k->System.out.println(k.price1));
			 * System.out.println(list4.get(1).price1); Collections.reverse(list4);
			 * list4.forEach(k->System.out.println(k.price1));
			 *
			 * /* for (char u : t) { System.out.println(u); }
			 * System.out.println(list.getClass()); System.out.println(list);
			 * 
			 * Iterator itor = list.iterator();
			 * 
			 * while (itor.hasNext()) { System.out.println(itor.next()); }
			 * 
			 * LocalDate time = LocalDate.now(); DateTimeFormatter dateFormat =
			 * DateTimeFormatter.ofPattern("MM-dd-yyyy"); System.out.println(time);
			 * System.out.println(time.format(dateFormat)); int[] a = { 1, 2, 3, 4, 5, 6, 7
			 * }; // assert a.length >= 6;
			 * System.out.println("Chuong trinh hoat dong binh thuong " + a.length);
			 */
			/*
			 * StringTokenizer st = new StringTokenizer("Toi ten la   VietTut");
			 * System.out.println("Tổng số token: " + st.countTokens()); while
			 * (st.hasMoreTokens()) { System.out.println(st.nextToken()); }
			 */
			/*
			 * Class<?> reflect =
			 * Class.forName("project.green.shop.security.MyUserDetails"); //MyUserDetails
			 * user = (MyUserDetails) reflect.newInstance(); java.lang.reflect.Method[]
			 * method = reflect.getMethods(); String getInfo =reflect.getCanonicalName();
			 * System.out.println(getInfo); for(java.lang.reflect.Method me :method) {
			 * System.out.println(me.getName()); }
			 */
			throw new IllegalAccessException("YOU DONE HERE !!!");
		} catch (Exception E) {
			System.err.println(E.getMessage());
		}

	}

}
