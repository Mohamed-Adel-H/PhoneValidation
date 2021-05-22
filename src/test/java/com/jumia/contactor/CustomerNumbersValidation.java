package com.jumia.contactor;

import com.jumia.contactor.service.StateValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class CustomerNumbersValidation {

    @Test
    void validCustomerNumber() {
        StateValidator stateValidator = new StateValidator();
        String countryCode = "212";
        String state = "633963130";

        boolean isValid = stateValidator.isValid(countryCode, state);

        assertTrue("Valid Number", isValid);
    }
    
    @Test
    void invalidCustomerNumber() {
        StateValidator stateValidator = new StateValidator();
        String countryCode = "212";
        String state = "00633963130";

        boolean isValid = stateValidator.isValid(countryCode, state);

        assertFalse("Invalid Number", isValid);
    }
}
