package com.qalens.sample.bdd.test

import io.cucumber.core.api.TypeRegistry
import io.cucumber.core.api.TypeRegistryConfigurer
import io.cucumber.cucumberexpressions.ParameterType
import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import net.serenitybdd.screenplay.Actor
import org.junit.runner.RunWith
@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
        features= arrayOf("src/test/resources/test"),
        glue = arrayOf("com.qalens.sample.bdd.test.steps")

)
class TestRunner
