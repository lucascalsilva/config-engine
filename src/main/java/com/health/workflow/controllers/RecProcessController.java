package com.health.workflow.controllers;

import com.health.workflow.dto.RecProcessDTO;
import com.health.workflow.dto.RecProcessResponseDTO;
import com.health.workflow.services.RecProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecProcessController {

    private final RecProcessService recProcessService;

    @PostMapping
    public ResponseEntity<RecProcessResponseDTO> createRecProcess(@RequestBody RecProcessDTO recProcess){
        return ResponseEntity.ok(recProcessService.createRecProcess(recProcess));
    }
}
