package br.com.ibmec.cloud.spotify.controller.request;

import br.com.ibmec.cloud.spotify.models.Plan;
import br.com.ibmec.cloud.spotify.models.User;
import lombok.Data;

import java.util.UUID;

@Data
public class SubscriptionRequest {
    private UUID id;
    private Plan plan;
    private User user;
    private boolean active;
}
