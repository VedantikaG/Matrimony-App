package com.yaksha.training.matrimony.boundary;


import com.yaksha.training.matrimony.entity.Matrimony;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static com.yaksha.training.matrimony.utils.MasterData.getMatrimony;
import static com.yaksha.training.matrimony.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.matrimony.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.matrimony.utils.TestUtils.currentTest;
import static com.yaksha.training.matrimony.utils.TestUtils.testReport;
import static com.yaksha.training.matrimony.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testMatrimonyFullNameNotBlank() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setFullName("");
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyFullNameNotNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setFullName(null);
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyFullNameMinTwo() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setFullName(randomStringWithSize(1));
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyFullNameMaxForty() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setFullName(randomStringWithSize(41));
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyAddressNotBlank() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setAddress("");
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyAddressNotNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setAddress(null);
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyAddressMinTwo() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setAddress(randomStringWithSize(1));
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyAddressMaxHundred() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setAddress(randomStringWithSize(101));
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyReligionNotBlank() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setReligion("");
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyReligionNotNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setReligion(null);
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyOccupationNotBlank() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setOccupation("");
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyOccupationNotNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setOccupation(null);
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testMatrimonyGenderNotNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setGender(null);
        Set<ConstraintViolation<Matrimony>> violations = validator.validate(matrimony);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

}
