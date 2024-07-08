package com.yaksha.training.matrimony.service;

import com.yaksha.training.matrimony.entity.Matrimony;
import com.yaksha.training.matrimony.repository.MatrimonyRepository;
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

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.matrimony.utils.MasterData.asJsonString;
import static com.yaksha.training.matrimony.utils.MasterData.getMatrimony;
import static com.yaksha.training.matrimony.utils.MasterData.getMatrimonyList;
import static com.yaksha.training.matrimony.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.matrimony.utils.TestUtils.businessTestFile;
import static com.yaksha.training.matrimony.utils.TestUtils.currentTest;
import static com.yaksha.training.matrimony.utils.TestUtils.testReport;
import static com.yaksha.training.matrimony.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class MatrimonyServiceTest {

    @Mock
    private MatrimonyRepository matrimonyRepository;

    @InjectMocks
    private MatrimonyService matrimonyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetMatrimonys() throws Exception {
        List<Matrimony> actual = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(actual);

        when(matrimonyRepository.findAllMatrimony(pageable)).thenReturn(matrimonyPage);
        Page<Matrimony> expected = matrimonyService.getMatrimonys(pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveMatrimony() throws Exception {
        Matrimony actual = getMatrimony();
        when(matrimonyRepository.save(actual)).thenReturn(actual);
        Matrimony expected = matrimonyService.saveMatrimony(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetMatrimony() throws Exception {
        Matrimony actual = getMatrimony();
        when(matrimonyRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Matrimony expected = matrimonyService.getMatrimony(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceDeleteMatrimony() throws Exception {
        Matrimony actual = getMatrimony();
        boolean expected = matrimonyService.deleteMatrimony(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchMatrimonyWithNull() throws Exception {
        List<Matrimony> actual = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(actual);

        when(matrimonyRepository.findAllMatrimony(pageable)).thenReturn(matrimonyPage);
        Page<Matrimony> expected = matrimonyService.searchMatrimonys(null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchMatrimonyWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        List<Matrimony> actual = getMatrimonyList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Matrimony> matrimonyPage = new PageImpl<>(actual);

        when(matrimonyRepository.findByNameOrReligionOrOccupation(searchKey, pageable)).thenReturn(matrimonyPage);
        Page<Matrimony> expected = matrimonyService.searchMatrimonys(searchKey, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceUpdateMatchFound() throws Exception {
        Matrimony actual = getMatrimony();
        boolean expected = matrimonyService.updateMatrimonyMatchFound(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }
}
