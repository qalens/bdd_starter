package com.qalens.sample.bdd.test.steps
import io.cucumber.datatable.DataTable
import io.cucumber.java.DataTableType
import io.cucumber.java.en.Given

class SampleDataTable {
    @DataTableType
    fun productEntry(entry:Map<String,String>):Product{
        return Product(entry.get("name")!!);
    }
    @Given("Following Data {string} color")
    fun followingData(color:String,products:List<Product>) {
        println(color)
        products.forEach {
            println(it.name)
        }
    }
}