import com.fasterxml.jackson.core.JsonProcessingException;
import model.Employee;
import model.PensionPlan;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class EmployeePensionPlan {

    static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        loadSampleData();
        System.out.println("All Employees (sorted):");
        printAllEmployeesJson();

        System.out.println("\nQuarterly Upcoming Enrollees:");
        printUpcomingEnrolleesJson();
    }

    static void loadSampleData() {
        Employee e1 = new Employee(1, "Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50);
        e1.pensionPlan = new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00);

        Employee e2 = new Employee(2, "Benard", "Shaw", LocalDate.of(2022, 9, 3), 197750.00);
        Employee e3 = new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75);
        e3.pensionPlan = new PensionPlan("SM2307", LocalDate.of(2019, 11, 4), 1555.50);

        Employee e4 = new Employee(4, "Wesley", "Schneider", LocalDate.of(2022, 7, 11), 74500.00);
        Employee e5 = new Employee(5, "Anna", "Wiltord", LocalDate.of(2022, 6, 15), 74500.00);
        Employee e6 = new Employee(6, "Yosef", "Tesfalem", LocalDate.of(2022, 10, 31), 74500.00);

        employees.addAll(Arrays.asList(e1, e2, e3, e4, e5, e6));
    }

    static void printAllEmployeesJson() throws Exception {
        List<Employee> sorted = employees.stream()
                .sorted(Comparator.comparingDouble((Employee e) -> -e.salary)
                        .thenComparing(e -> e.lastName))
                .collect(Collectors.toList());
        printAsJson(sorted);
    }

    static void printUpcomingEnrolleesJson() throws Exception {
        LocalDate now = LocalDate.now();
        LocalDate nextQuarterStart = now.plusMonths(3 - (now.getMonthValue() - 1) % 3).withDayOfMonth(1);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

        List<Employee> qualified = employees.stream()
                .filter(e -> !e.isEnrolled())
                .filter(e -> {
                    LocalDate threeYearAnniversary = e.employmentDate.plusYears(3);
                    return !threeYearAnniversary.isBefore(nextQuarterStart) && !threeYearAnniversary.isAfter(nextQuarterEnd);
                })
                .sorted(Comparator.comparing((Employee e) -> e.employmentDate).reversed())
                .collect(Collectors.toList());
        printAsJson(qualified);
    }

    static void printAsJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(obj));
    }
}
