package com.qalens.sample.bdd.automationpractice.pom

import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.core.pages.WebElementFacade
import org.openqa.selenium.By

fun <T> WebElementFacade.get(by: By, block: WebElementFacade.() -> T):T {
    val element: WebElementFacade =find(by);
    return element.block();
}

fun <T> PageObject.get(by:By,block:WebElementFacade.()->T):T{
    val element:WebElementFacade=find(by);
    return element.block();
}