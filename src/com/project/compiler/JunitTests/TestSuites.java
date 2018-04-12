package com.project.compiler.JunitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TestCasesLex.class,
	TestCasesLexFileMatch.class,
	TestCasesSyntaxFileMatch.class,
	TestCasesSemanticFileMatch.class
})

public class TestSuites {   
}  