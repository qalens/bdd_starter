package com.qalens.sample.bdd.automationpractice

class Product(val name:String, val category:Category) {
    var actualPrice:Double = 0.0;
    var discountPercent:Double = 0.0;
    var id:Int=0;
    fun getDiscountedPrice():Double{
        return actualPrice+(actualPrice * discountPercent);
    }
    var searchableFieldValues= mutableMapOf<String,String>()
    fun autocompleteEntry():String {
        return "${category.name} > ${name}"
    }
    fun isAvailableForValueForField(fieldValue:String,field:String):Boolean{
        return searchableFieldValues.get(field)?.contains(fieldValue)?:false;
    }
    companion object{
        val searchableFields= listOf("name","description","colour","category")
    }
}