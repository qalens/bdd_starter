package com.qalens.sample.bdd.automationpractice

import io.cucumber.core.api.TypeRegistry
import io.cucumber.core.api.TypeRegistryConfigurer
import io.cucumber.cucumberexpressions.ParameterType
import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import net.serenitybdd.screenplay.Actor
import net.thucydides.core.annotations.WithTag
import net.thucydides.core.annotations.WithTags
import org.junit.runner.RunWith
@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        features= arrayOf("src/test/resources/automationpractice")
        ,glue = arrayOf("com.qalens.sample.bdd.automationpractice")
)
class AutomationPracticeRunner
