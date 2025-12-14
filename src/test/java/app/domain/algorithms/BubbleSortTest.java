package app.domain.algorithms;

import app.domain.model.ImportedDataInformation;
import app.domain.model.utils.DateCustom;
import app.domain.model.utils.TimeHour;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BubbleSortTest {

    private static ImportedDataInformation inf1, inf2, inf3;

    @BeforeAll
    static void setUp() {
        inf1 = new ImportedDataInformation("name um", 123456789, "designation um", "vaccine name um", 1, "ABC12-34",
                                           new DateCustom("18/6/2022"), new TimeHour(9, 0), new DateCustom("18/6/2022"),
                                           new TimeHour(8, 55), new DateCustom("18/6/2022"), new TimeHour(9, 1),
                                           new DateCustom("18/6/2022"), new TimeHour(9, 40));
        inf2 = new ImportedDataInformation("name dois", 234567890, "designation dois", "vaccine name dois", 2,
                                           "ABC23-45", new DateCustom("18/6/2022"), new TimeHour(9, 0),
                                           new DateCustom("18/6/2022"), new TimeHour(8, 50),
                                           new DateCustom("18/6/2022"), new TimeHour(9, 1), new DateCustom("18/6/2022"),
                                           new TimeHour(9, 41));
        inf3 = new ImportedDataInformation("name tres", 345678901, "designation tres", "vaccine name tres", 3,
                                           "ABC34-56", new DateCustom("18/6/2022"), new TimeHour(9, 0),
                                           new DateCustom("17/6/2022"), new TimeHour(9, 0), new DateCustom("18/6/2022"),
                                           new TimeHour(9, 1), new DateCustom("17/6/2022"), new TimeHour(9, 39));
    }

    @Test
    void sortByArrivalTime_listMultiple() {
        List<ImportedDataInformation> list = new ArrayList<>();
        list.add(inf1);
        list.add(inf2);
        list.add(inf3);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByArrivalTime(list);
        assertTrue(list.get(0).getName().equals("name tres"));
        assertTrue(list.get(1).getName().equals("name dois"));
        assertTrue(list.get(2).getName().equals("name um"));
    }

    @Test
    void sortByArrivalTime_listOne() {
        List<ImportedDataInformation> list = new ArrayList<>();
        list.add(inf1);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByArrivalTime(list);
        assertTrue(list.get(0).getName().equals("name um"));
    }

    @Test
    void sortByArrivalTime_listEmpty() {
        List<ImportedDataInformation> list = new ArrayList<>();
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByArrivalTime(list);
        assertEquals(list.size(), 0);
    }

    @Test
    void sortByArrivalTime_listAlreadySorted() {
        List<ImportedDataInformation> list = new ArrayList<>();
        list.add(inf3);
        list.add(inf2);
        list.add(inf1);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByArrivalTime(list);
        assertTrue(list.get(0).getName().equals("name tres"));
        assertTrue(list.get(1).getName().equals("name dois"));
        assertTrue(list.get(2).getName().equals("name um"));
    }

    @Test
    void sortByLeavingTime_listMultiple() {
        List<ImportedDataInformation> list = new ArrayList<>();
        list.add(inf1);
        list.add(inf2);
        list.add(inf3);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByLeavingTime(list);
        assertTrue(list.get(0).getName().equals("name tres"));
        assertTrue(list.get(1).getName().equals("name um"));
        assertTrue(list.get(2).getName().equals("name dois"));
    }

    @Test
    void sortByLeavingTime_listOne() {
        List<ImportedDataInformation> list = new ArrayList<>();
        list.add(inf2);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByLeavingTime(list);
        assertTrue(list.get(0).getName().equals("name dois"));
    }

    @Test
    void sortByLeavingTime_listEmpty() {
        List<ImportedDataInformation> list = new ArrayList<>();
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByLeavingTime(list);
        assertEquals(list.size(), 0);
    }

    @Test
    void sortByLeavingTime_alreadySorted() {
        List<ImportedDataInformation> list = new ArrayList<>();
        list.add(inf3);
        list.add(inf1);
        list.add(inf2);
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sortByLeavingTime(list);
        assertTrue(list.get(0).getName().equals("name tres"));
        assertTrue(list.get(1).getName().equals("name um"));
        assertTrue(list.get(2).getName().equals("name dois"));
    }


}