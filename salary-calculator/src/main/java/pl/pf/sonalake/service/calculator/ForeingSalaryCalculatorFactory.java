package pl.pf.sonalake.service.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pf.sonalake.api.model.dict.CountryData;
import pl.pf.sonalake.service.calculator.impl.ForeignSalaryCalculator;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Klasa fabrykująca set kalkulatoroów dla krajów opisanych w enum ${@link CountryData}
 *
 * @author pfutro
 */
@Configuration
public class ForeingSalaryCalculatorFactory {

    private final Integer daysInMonth;

    public ForeingSalaryCalculatorFactory(@Value("${month.days}") Integer daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    @Bean
    public Set<ISalaryCalculator> foreignSalaryCalculatorBeans() {
        return Stream.of(CountryData.values())
                .map(CountryData::getCountryCode)
                .map(cc -> new ForeignSalaryCalculator(daysInMonth, cc))
                .collect(Collectors.toSet());
    }

}
