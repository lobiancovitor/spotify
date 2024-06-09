package br.com.ibmec.cloud.spotify.controller;

import br.com.ibmec.cloud.spotify.controller.request.PlanRequest;
import br.com.ibmec.cloud.spotify.models.Band;
import br.com.ibmec.cloud.spotify.models.Plan;
import br.com.ibmec.cloud.spotify.repository.PlanRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    PlanRepository planRepository;

    @PostMapping
    public ResponseEntity<Plan> create(@Valid @RequestBody PlanRequest request) {
        Plan plan = new Plan();
        plan.setName(request.getName());
        plan.setPrice(request.getPrice());

        this.planRepository.save(plan);

        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Plan> getPlan(@PathVariable UUID id) {
        return this.planRepository.findById(id).map(item -> {
            return new ResponseEntity<>(item, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Plan>> getAll() {
        List<Plan> plans = this.planRepository.findAll();
        if (plans.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }
}
