package org.example.backendapi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/request-param")
    public ResponseEntity<?> reuestParam(
            @RequestParam String name,
            @RequestParam String age
    ) {
        System.out.println(name+" "+age);
        return ResponseEntity.ok(name+" "+age);
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/{number}")
    public ResponseEntity<?> demo(@PathVariable Integer number) {
        return ResponseEntity.ok(number);
    }
}
