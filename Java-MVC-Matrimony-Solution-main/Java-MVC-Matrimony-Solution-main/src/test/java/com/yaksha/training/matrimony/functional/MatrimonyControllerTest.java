package com.yaksha.training.matrimony.functional;

import com.yaksha.training.matrimony.controller.MatrimonyController;
import com.yaksha.training.matrimony.entity.Matrimony;
import com.yaksha.training.matrimony.service.MatrimonyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.matrimony.utils.MasterData.asJsonString;
import static com.yaksha.training.matrimony.utils.MasterData.getMatrimony;
import static com.yaksha.training.matrimony.utils.MasterData.getMatrimonyList;
import static com.yaksha.training.matrimony.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.matrimony.utils.TestUtils.businessTestFile;
import static com.yaksha.training.matrimony.utils.TestUtils.currentTest;
import static com.yaksha.training.matrimony.utils.TestUtils.testReport;
import static com.yaksha.training.matrimony.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MatrimonyControllerTest {

    @Mock
    private MatrimonyService matrimonyService;

    @InjectMocks
    private MatrimonyController matrimonyController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matrimonyController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListMatrimonysHome() throws Exception {
        List<Matrimony> expected = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(expected);
        when(matrimonyService.getMatrimonys(pageable)).thenReturn(matrimonyPage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-matrimonys")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("matrimonys")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerListMatrimonys() throws Exception {
        List<Matrimony> expected = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(expected);
        when(matrimonyService.getMatrimonys(pageable)).thenReturn(matrimonyPage);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-matrimonys")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("matrimonys")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/addMatrimonyForm")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form"), businessTestFile);
    }

    @Test
    public void testControllerSaveMatrimony() throws Exception {
        Matrimony matrimony = getMatrimony();
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/matrimony/list"), businessTestFile);
    }

    @Test
    public void testControllerSaveMatrimonyHasError() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setId(null);
        matrimony.setFullName(null);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-matrimony-form"), businessTestFile);
    }

    @Test
    public void testControllerUpdateMatrimonyHasError() throws Exception {
        Matrimony matrimony = getMatrimony();
        matrimony.setGender(null);
        MvcResult result = this.mockMvc.perform(post("/saveMatrimony")
                .flashAttr("matrimony", matrimony)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Matrimony matrimony = getMatrimony();
        when(matrimonyService.getMatrimony(matrimony.getId())).thenReturn(matrimony);
        MvcResult result = this.mockMvc.perform(get("/updateMatrimonyForm")
                .param("matrimonyId", matrimony.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-matrimony-form"), businessTestFile);
    }

    @Test
    public void testControllerDeleteMatrimony() throws Exception {
        Matrimony matrimony = getMatrimony();
        MvcResult result = this.mockMvc.perform(get("/delete")
                .param("matrimonyId", matrimony.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/matrimony/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchMatrimonys() throws Exception {
        String key = randomStringWithSize(2);
        List<Matrimony> expected = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(expected);
        when(matrimonyService.searchMatrimonys(key, pageable)).thenReturn(matrimonyPage);
        MvcResult result = this.mockMvc.perform(post("/search")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-matrimonys")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("matrimonys")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerSearchMatrimonysWithNullKey() throws Exception {
        List<Matrimony> expected = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(expected);
        when(matrimonyService.searchMatrimonys(null, pageable)).thenReturn(matrimonyPage);
        MvcResult result = this.mockMvc.perform(post("/search")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-matrimonys")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("matrimonys")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

    @Test
    public void testControllerUpdateMatchFound() throws Exception {
        Matrimony matrimony = getMatrimony();
        MvcResult result = this.mockMvc.perform(get("/updateMatchFound")
                .param("id", matrimony.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/matrimony/list"), businessTestFile);
    }

}
