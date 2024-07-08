package com.yaksha.training.matrimony.exception;

import com.yaksha.training.matrimony.controller.MatrimonyController;
import com.yaksha.training.matrimony.entity.Matrimony;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.matrimony.utils.MasterData.getMatrimony;
import static com.yaksha.training.matrimony.utils.TestUtils.currentTest;
import static com.yaksha.training.matrimony.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.matrimony.utils.TestUtils.testReport;
import static com.yaksha.training.matrimony.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MatrimonyExceptionTest {

    @InjectMocks
    private MatrimonyController matrimonyController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matrimonyController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveMatrimonyFullNameAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setId(null);
        matrimony.setFullName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveMatrimonyGenderAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setId(null);
        matrimony.setGender(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form")), exceptionTestFile);
    }


    @Test
    public void testExceptionSaveMatrimonyAddressAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setId(null);
        matrimony.setAddress(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveMatrimonyReligionAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setId(null);
        matrimony.setReligion(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveMatrimonyOccupationAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setId(null);
        matrimony.setOccupation(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateMatrimonyFullNameAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setFullName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateMatrimonyGenderAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setGender(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateMatrimonyAddressAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setAddress(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateMatrimonyReligionAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setReligion(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateMatrimonyOccupationAsNull() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setOccupation(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form")), exceptionTestFile);
    }


}