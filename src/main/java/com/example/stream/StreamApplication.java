package com.example.stream;

import com.example.stream.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {

        SpringApplication.run(StreamApplication.class, args);


        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Jack1", "IT", 19902, 12));
        employees.add(new Employee(2, "John1", "IT", 2222, 50));
        employees.add(new Employee(3, "Jane2", "IT", 1212, 17));
        employees.add(new Employee(5, "Ajith Kumar", "Finance", 1111, 43));
        employees.add(new Employee(6, "Rameshwarm swami", "Techsupport", 1111, 34));

        //displayName
        System.out.println("get employee names :" + transform(employees));
        System.out.println("get map employee :" + covertToMap(employees));
        Map<String, List<Employee>> groupbydept = groupbydept(employees);
        System.out.println("group by dept");
        groupbydept.entrySet().stream().forEach(es -> System.out.println("Department:" + es.getKey() + ":emplyee:" + es.getValue()));

        System.out.println("group by dept and display average salary");
        groupbydept.entrySet().stream().forEach(es -> System.out.println("Department:" + es.getKey() + ":emplyee average salary:" + es.getValue().stream().mapToDouble(emp ->
                {
                    return emp.getSalary();
                }).average()
        ));

        System.out.println("find sum of all even employee age :" + sumofalleven(employees));


        System.out.println("find and print employee name greter than 4 char:");

        getempnamesmoreThan5(employees);

        System.out.println("find and print employee age suare");

        getempagesquare(employees);

        getempmaxage(employees);

        System.out.println("concat all names ");
        String allnames = concateallnames(employees);
        System.out.println(allnames);
        System.out.println("remove repeated elements from string ");
        String nonrepeted = removeRepeatedfromString("VABCABCDHVVVVV");
        System.out.println(nonrepeted);
        System.out.println("Convert each employee name to upper and sort ");

        convertToUpperandSort(employees);
        List<Employee> employeeList = convertToLowerandreturn(employees);

        employeeList.stream().forEach(e -> System.out.println(e.toString()));

    }

    private static List<Employee> convertToLowerandreturn(List<Employee> employees) {
        return employees.stream().peek(e -> e.setName(e.getName().toLowerCase())).sorted(Comparator.comparing(Employee::getName)).collect(Collectors.toList());
    }

    private static void convertToUpperandSort(List<Employee> employees) {

        employees.stream().map(employee -> {
            return employee.getName().toUpperCase();
        }).sorted().forEach(System.out::println);
    }

    private static String removeRepeatedfromString(String allnames) {

        StringBuilder sb1 = new StringBuilder();

        allnames.chars().distinct().forEach(c -> sb1.append((char) c));


        return sb1.toString();
    }

    private static String concateallnames(List<Employee> employees) {
        return employees.stream().map(Employee::getName).collect(Collectors.joining(","));
    }

    private static void getempmaxage(List<Employee> employees) {

        System.out.println("get employee maxage");
        Employee myemp = employees.stream().max(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(myemp.getName() + ":" + myemp.getAge());

    }

    private static void getempagesquare(List<Employee> employees) {

        employees.stream().forEach(employee -> {
            int age = employee.getAge();
            System.out.println(employee.getName() + ":" + (age * age));
        });
    }

    private static void getempnamesmoreThan5(List<Employee> employees) {

        employees.stream().filter(employee -> employee.getName().length() > 5).forEach(e -> {
            System.out.println(e.getName());
        });
    }

    private static int sumofalleven(List<Employee> employees) {

        return employees.stream().filter(e -> e.getAge() % 2 == 0).mapToInt(e -> e.getAge()).sum();

    }


    public static List<String> transform(List<Employee> employeeList) {

        return employeeList.stream().map(employee -> employee.getName()).toList();

    }

// Other ways
    //	return employeeList.stream().map(Employee::getName).toList();
// Or
    //	return employeeList.stream().map(Employee::getName).collect(Collectors.toList());

    public static Map<Integer, Employee> covertToMap(List<Employee> employeeList) {

        return employeeList.stream().collect(Collectors.toMap(Employee::getId, e -> e));

    }

    public static Map<String, List<Employee>> groupbydept(List<Employee> employeeList) {

        return employeeList.stream().collect(Collectors.groupingBy(Employee::getDept));

    }


}

