package com.qalens.sample.bdd.automationpractice.steps

import com.qalens.sample.bdd.automationpractice.Category
import com.qalens.sample.bdd.automationpractice.Product
import com.qalens.sample.bdd.automationpractice.pom.HomePage
import io.cucumber.java.DataTableType
import io.cucumber.java.ParameterType
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import net.serenitybdd.core.Serenity
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.GivenWhenThen.seeThat
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Enter
import net.serenitybdd.screenplay.actions.Open
import net.thucydides.core.annotations.Managed
import org.hamcrest.Matchers.*
import org.openqa.selenium.WebDriver
open class SearchSteps {

    @Managed
    lateinit var driver:WebDriver;

    lateinit var homePage: HomePage;

    lateinit var user:Actor

    lateinit var search_text:String;

    lateinit var expectedProducts:List<Product>

    @ParameterType(name = "actor",value = ".*")
    fun getActor(name:String):Actor{
        return Actor.named(name)
                .whoCan(BrowseTheWeb.with(driver));
    }
    @Given("{actor} intends to search for product")
    fun openBrowser(user:Actor) {
        user.attemptsTo(Open.browserOn().the(homePage))
        this.user=user;
    }
    @When("he provides {string} for searching products")
    @Given("he has provided {string} as search criteria")
    fun typeSerachText(search_text:String) {
        this.search_text=search_text;
        user.attemptsTo(Enter.theValue(search_text).into(homePage.searchTextBox));
    }
    @When("he searches")
    fun clickOnSearchButton() {
        user.attemptsTo(Click.on(homePage.searchButton));
    }
    @Then("he can see Products containing {string} for {string} in quick results")
    fun verifyProducts(fieldvalue:String,field:String){
        expectedProducts=Serenity.sessionVariableCalled<List<Product>>("currentProducts").filter {it.isAvailableForValueForField(fieldvalue,field) }
        val expected=expectedProducts.map { it.autocompleteEntry() };
        expected.forEach {
            user.should(seeThat("displayed products",homePage.autoCompleteResults(), hasItem(it)))
        }
    }

    @Then("he is shown products containing {string} for {string} in search results")
    fun verifyProductDetails(fieldvalue:String,field:String){
        expectedProducts=Serenity.sessionVariableCalled<List<Product>>("currentProducts").filter {it.isAvailableForValueForField(fieldvalue,field)}
        verifyProductAttributeDetails("name");
    }
    @Then("he is shown exactly following products in quick results")
    fun verifyProductForCompositeSearchInQuickResults(products:List<Product>){
        expectedProducts=products;
        val expected=expectedProducts.map { it.autocompleteEntry() };
        expected.forEach {
            user.should(seeThat("displayed products",homePage.autoCompleteResults(), hasItem(it)))
        }
        user.should(seeThat("count for products ",homePage.autoCompleteResultsCount(),`is`(equalTo(products.count()))))
    }
    @Then("for each product correct {string} is conveyed")
    fun verifyProductAttributeDetails(attribute:String){
        expectedProducts.forEach {
            val searchAtrribute=HomePage.SearchResultAttribute.valueOf(attribute.toUpperCase())
            var verify= true;
            val expected=
                    when(searchAtrribute){
                        HomePage.SearchResultAttribute.NAME->it.name;
                        HomePage.SearchResultAttribute.PRICE->String.format("$%.2f", it.actualPrice)
                        HomePage.SearchResultAttribute.DISCOUNT->{
                            if(it.discountPercent==0.0){
                                verify=false;
                                "";
                            } else { String.format("%d%%", it.discountPercent.toInt())}
                        }
                    }
            if(verify)
                user.should(seeThat("displayed ${attribute} for product with id #${it.id}",homePage.searchResultAttributeForSearchWithProduct(searchAtrribute,it),`is`(equalTo(expected))))
        }
    }
    @Then("he is shown exactly following products in search results")
    fun verifyProductForCompositeSearchInDetails(products:List<Product>){
        expectedProducts=products;
        verifyProductAttributeDetails("name");
        user.should(seeThat("count for products ",homePage.searchResultsCount(),`is`(equalTo(products.count()))))
    }
}