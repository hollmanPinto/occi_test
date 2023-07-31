package com.occidente.occidente.controllers;

import com.occidente.occidente.Entities.Groups;
import com.occidente.occidente.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
    @Autowired
    BusService busService;

    @PostMapping(value = "/busCapacidad", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> busCapacidad(@RequestBody Groups grupos){
        try{
            var res = busService.calculoCapacidad(grupos);
            return ResponseEntity.status(HttpStatus.OK.value()).body(res);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body("Ha ocurrido un error interno");
        }
    }
}
