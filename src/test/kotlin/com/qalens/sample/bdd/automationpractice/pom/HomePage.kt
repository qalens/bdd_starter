package com.qalens.sample.bdd.automationpractice.pom

import com.qalens.sample.bdd.automationpractice.Product
import net.serenitybdd.core.annotations.findby.FindBy
import net.serenitybdd.core.pages.WebElementFacade
import net.serenitybdd.screenplay.Question
import net.thucydides.core.annotations.DefaultUrl
import net.thucydides.core.pages.PageObject
import org.openqa.selenium.By

@DefaultUrl("http://automationpractice.com/index.php")
class HomePage: PageObject() {

    @FindBy(css=".button-search")
    lateinit var searchButton:WebElementFacade

    @FindBy(id="search_query_top")
    lateinit var searchTextBox:WebElementFacade

    fun autoCompleteResults(): Question<MutableList<String>> {
        return Question { actor ->
            findAll(By.cssSelector(".ac_results li")).map {
                it.text
            }
        }
    }

    fun autoCompleteResultsCount(): Question<Int> {
        return Question { actor ->
            findAll(By.cssSelector(".ac_results li")).size;
        }
    }

    fun searchResultAttributeForSearchWithProduct(attribute:SearchResultAttribute, product:Product):Question<String>{
        return Question { actor ->
            get(By.xpath("//a[@data-id-product=${product.id}]/ancestor::*[@class=\"product-container\"]")) {
                when(attribute){
                    SearchResultAttribute.NAME->
                        get(By.cssSelector(".right-block .product-name")){
                            text
                        }
                        SearchResultAttribute.PRICE->{
                            val selector= if(product.discountPercent==0.0){
                                By.cssSelector(".right-block .product-price")
                            } else {
                                By.cssSelector(".right-block .old-price")
                            }
                            get(selector){
                                text
                            }
                        }
                    SearchResultAttribute.DISCOUNT->
                        get(By.cssSelector(".right-block .price-percent-reduction")) {
                            text
                        }
                }

            }

        }
    }

    fun searchResultsCount(): Question<out Int>? {
        return Question { actor ->
            findAll(By.cssSelector(".product-container")).count();
        }
    }

    enum class SearchResultAttribute {
        NAME,
        PRICE,
        DISCOUNT,

    }
}


