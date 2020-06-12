package com.qalens.sample.bdd.automationpractice.steps

import com.qalens.sample.bdd.automationpractice.Category
import com.qalens.sample.bdd.automationpractice.Product
import io.cucumber.java.DataTableType
import io.cucumber.java.en.Given
import net.serenitybdd.core.Serenity

open class ProductSteps {

    @DataTableType
    public fun productEntry(entry:Map<String, String>):Product{
        val product=Product(entry.get("name")!!, Category(entry.get("category")!!))
        product.actualPrice= entry.get("price")?.toDouble() ?:0.0
        product.discountPercent= entry.get("discount")?.toDouble() ?:0.0
        product.id= entry.get("id")?.toInt() ?:0
        entry.keys.forEach {
            if(Product.searchableFields.contains(it)){
                product.searchableFieldValues.put(it,entry.get(it)!!)
            }
        }
        return product;
    }
    @Given("Following Products are present for sale")
    fun search(products:List<Product>) {
        Serenity.setSessionVariable("currentProducts").to(products);
    }

}