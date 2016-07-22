package com.tsystems.webclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.tsystems.webclient.model.OrderDetails;

import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.BindingProvider;
import java.io.IOException;
import java.util.Map;

/**
 * Implementation REST-ful interaction on the client side.
 */
public class RestClientImpl implements RestClient {

    /**
     * URL to request on getting order details.
     */
    private static final String gettingOrderDetailsUrl = "http://localhost:8080/rest/driver";

    /**
     * URL to request on completing cargo by ID.
     */
    private static final String completingCargoByIdUrl = "http://localhost:8080/rest/cargo";

    /**
     * URL to request on changing driver status of current user by email.
     */
    private static final String changingStatusByEmailUrl = "http://localhost:8080/rest/driver";

    /**
     * Current session attributes.
     */
    private Map<String, Object> attributes =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    /**
     * Sends request to getting order details information.
     * @return order details
     * @throws IOException
     */
    @Override
    public OrderDetails sendRequestToGettingOrderDetails() throws IOException {
        Client client = Client.create();
        WebResource webResource = client.resource(gettingOrderDetailsUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        ClientResponse clientResponse =
                build(webResource).get(ClientResponse.class);
        if (clientResponse.getStatus() != 200) return null;
        String output = clientResponse.getEntity(String.class);
        return objectMapper.readValue(output, OrderDetails.class);
    }

    /**
     * Sends request to changing driver status.
     * @param status driver status
     * @return is changed or not
     * @throws IOException
     */
    @Override
    public boolean sendRequestToChangingStatus(String status) throws IOException {
        Client client = Client.create();
        WebResource webResource = client.resource(changingStatusByEmailUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        ClientResponse clientResponse =
                build(webResource).post(ClientResponse.class, status);
        if (clientResponse.getStatus() != 200) return false;
        String output = clientResponse.getEntity(String.class);
        return objectMapper.readValue(output, Boolean.class);
    }

    /**
     * Sends request to completing cargo by his ID.
     * @param cargoId cargo ID
     * @return is completed or not
     * @throws IOException
     */
    @Override
    public boolean sendRequestToCompletingCargo(int cargoId) throws IOException {
        Client client = Client.create();
        WebResource webResource = client.resource(completingCargoByIdUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        ClientResponse clientResponse =
                build(webResource).post(ClientResponse.class, String.valueOf(cargoId));
        if (clientResponse.getStatus() != 200) return false;
        String output = clientResponse.getEntity(String.class);
        return objectMapper.readValue(output, Boolean.class);
    }

    /**
     * Builds up a web resource by adding media type and credentials to request headers.
     * @param webResource webResource
     * @return webResource
     */
    private WebResource.Builder build(WebResource webResource) {
        return webResource
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .header(BindingProvider.USERNAME_PROPERTY,
                        attributes.get(BindingProvider.USERNAME_PROPERTY))
                .header(BindingProvider.PASSWORD_PROPERTY,
                        attributes.get(BindingProvider.PASSWORD_PROPERTY));
    }

}
