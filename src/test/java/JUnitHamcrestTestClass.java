import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;

public class JUnitHamcrestTestClass {

	/* Creating instances */
	private static Employee empA;
	private static Employee empB;
	private static List<String> strList = Arrays.asList("Apple", "Apricot", "August");

	@BeforeClass
	public static void init() {

		/* creating objects */
		empA = new Employee(1001L, "Vinod Kumar Kashyap", "Male", Arrays.asList("Best Team", "Star Employee"));
		empB = new Employee(1002L, "Asmi Kashyap", "Female", Arrays.asList("Star Employee"));
	}

	/**
	 * This method will test functionality of <code>is</code> matcher.
	 */
	@Test
	public void isTest() {
		// checks that empA is an object of Employee class
		assertThat(empA, isA(Employee.class));

		// below are 3 versions of "is" method. All are same
		assertThat(2, equalTo(2));
		assertThat(2, is(equalTo(2)));
		assertThat(2, is(2));
	}

	/**
	 * This method will test functionality of <code>beans</code> matcher.
	 */
	@Test
	public void beansTest() {
		// checks that object contains the property
		assertThat(empA, hasProperty("empName"));

		// checks that the object contains the property with a value
		assertThat(empA, hasProperty("empName", equalTo("Vinod Kumar Kashyap")));
	}

	/**
	 * This method will test functionality of <code>collections</code> matcher.
	 */
	@Test
	public void collectionsTest() {
		// checks that object is of checked size
		assertThat(empA.getAwards(), hasSize(2));

		// checks a collection for the element present
		assertThat("Best Team", isIn(empA.getAwards()));

		// checks for empty collection
		assertThat(new ArrayList<String>(), emptyCollectionOf(String.class));
	}

	/**
	 * This method will test functionality of <code>String</code> matcher.
	 */
	@Test
	public void stringTest() {
		assertThat(empA.getEmpName(), containsString("Kumar"));
		assertThat(empA.getEmpName(), endsWith("Kashyap"));
		assertThat(empB.getEmpName(), startsWith("Asmi"));

		// checks by ignoring case
		assertThat(empA.getEmpName(), equalToIgnoringCase("vinod KUMAR Kashyap"));

		// checks that the elements are occurring in the same order
		assertThat(empA.getEmpName(), stringContainsInOrder(Arrays.asList("Vinod", "Kashyap")));
	}

	/**
	 * Other common matchers
	 */
	@Test
	public void otherCommonTest() {
		// all of the conditions should be met to pass the case
		assertThat(empB.getGender(), allOf(startsWith("F"), containsString("ale")));

		// any of the conditions should be met to pass the case
		assertThat(empB.getEmpName(), anyOf(startsWith("Dhwani"), endsWith("yap")));

		// checks that value is not null
		assertThat(empA, is(notNullValue()));

		// checks that object id instance of a Class
		assertThat(empA.getEmpId(), instanceOf(Long.class));

		// checks every item in  list
		assertThat(strList, everyItem(startsWith("A")));
	}
}
