package docukes;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.lu.an;

/**
 * An awesome class containing stuff.
 * 
 * @author aneveux
 *
 */
public class WithCukesAndDoc {

	/**
	 * When I do something with a parameter
	 *
	 * @param param
	 *            A parameter which I need to provide
	 */
	@When("^I do something with \"([^\"]*)\"$")
	public void i_do_something_with(final String path) {
	}

	/**
	 * Then I call something with various parameters
	 *
	 * @param param
	 *            A first parameter
	 *
	 * @param other
	 *            Another parameter I need to provide
	 * @throws an
	 *             error if something's going wrong somewhere...
	 */
	@Then("^I call something with \"(.*)\" and (\"?.*\"?)$")
	public void i_call_something_with_and(final String param, final String other)
			throws Throwable {

	}

	/**
	 * Given a particular thing I want to ensure...
	 *
	 * @param property
	 *            a property... or something.
	 */
	@Given("^this property \"(.*)\"$")
	public void this_property(final String property) {

	}
}
