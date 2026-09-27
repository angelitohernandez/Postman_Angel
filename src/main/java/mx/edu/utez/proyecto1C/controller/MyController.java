package mx.edu.utez.proyecto1C.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services")
public class MyController {

    private final String Practica = "Fizzbuzz y Fibonacci";

    @GetMapping("/fizzbuzz/{n}")
    public String fizzBuzz(@PathVariable int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
        return Practica;
    }


    @GetMapping("/fibonacci/{n}")
    public String fibonacci(@PathVariable int n) {
        if (n <= 0) {
            return Practica;
        }

        long a = 0;
        long b = 1;

        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                System.out.println(a);
            } else if (i == 2) {
                System.out.println(b);
            } else {
                long siguiente = a + b;
                System.out.println(siguiente);
                a = b;
                b = siguiente;
            }
        }
        return Practica;
    }
}
