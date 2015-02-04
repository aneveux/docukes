/*
 * Docukes is a simple framework allowing to generate documentation out of cucumber java classes.
 * Copyright (C) 2015 Antoine Neveux or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
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
