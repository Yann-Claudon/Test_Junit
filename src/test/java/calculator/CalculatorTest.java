package calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {

	private static Instant startedAt;
	private Calculator calculatorUnderTest;

	@BeforeAll
	static public void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}

	@BeforeEach
	public void initCalculator() {
		System.out.println("Appel avant chaque test");
		calculatorUnderTest = new Calculator();
	}

	@AfterEach
	public void undefCalculator() {
		System.out.println("Appel après chaque test");
		calculatorUnderTest = null;
	}

	@AfterAll
	static public void showTestDuration() {
		System.out.println("Appel après tous les tests");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}


	@Test
	public void testAddTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;
		//Calculator calculator = new Calculator();

		// Act
		//int somme = calculator.add(a, b);
		int somme = calculatorUnderTest.add(a, b);

		// Assert
		//assertEquals(5, somme);
		// Assert avec AssertJ
		assertThat(somme).isEqualTo(5);
	}

	@Test
	public void testMultiplyTwoPositiveNumbers() {
		// Arrange
		int a = 42;
		int b = 11;
		//Calculator calculator = new Calculator();

		// Act
		//int multiplication = calculator.multiply(a, b);
		int produit = calculatorUnderTest.multiply(a, b);

		// Assert
		//assertEquals(462, produit);
		// Assert avec AssertJ
		assertThat(produit).isEqualTo(462);
	}

	@ParameterizedTest(name = "{0} + {1} should equal to {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2,
			int expectResult) {
		// Arrange -- Tout est prêt !
		// Act
		int actualResult = calculatorUnderTest.add(arg1, arg2);
		// Assert
		assertEquals(expectResult, actualResult);
	}

	@Timeout(1)
	@Test
	public void longCalcul_shouldComputeInLessThan1Second() {
		// Arrange
		// Act
		calculatorUnderTest.longCalculation();
		// Assert
		// ...
	}

	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {
		// GIVEN
		int number = 95897;
		// WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		// THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(5, 7, 8, 9);
	}

}
