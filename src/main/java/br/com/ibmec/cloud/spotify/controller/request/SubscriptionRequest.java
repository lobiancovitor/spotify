package br.com.ibmec.cloud.spotify.controller.request;

import br.com.ibmec.cloud.spotify.models.Plan;
import br.com.ibmec.cloud.spotify.models.User;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SubscriptionRequest {
    private UUID id;
    private Plan plan;
    private boolean active;
    private List<User> users;
}
