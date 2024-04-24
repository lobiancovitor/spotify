package br.com.ibmec.cloud.spotify.controller.request;

import br.com.ibmec.cloud.spotify.models.Subscription;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlanRequest {
    private UUID id;
    private String name;
    private double price;
    private List<Subscription> subscriptions;
}
