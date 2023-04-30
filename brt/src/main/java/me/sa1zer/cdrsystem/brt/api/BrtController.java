package me.sa1zer.cdrsystem.brt.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brt/")
public class BrtController {

    @GetMapping("healthCheck")
    public ResponseEntity<?> healthCheck() {
        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
    }
}
