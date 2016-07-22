package com.tsystems.webclient.controller;

import com.tsystems.webclient.model.OrderDetails;

import java.io.IOException;

/**
 * Contract based on REST-ful interaction.
 */
public interface RestClient {

    /**
     * Sends request to getting order details information.
     * @return order details
     * @throws IOException
     */
    public OrderDetails sendRequestToGettingOrderDetails() throws IOException;

    /**
     * Sends request to changing driver status.
     * @param status driver status
     * @return is changed or not
     * @throws IOException
     */
    public boolean sendRequestToChangingStatus(String status) throws IOException;

    /**
     * Sends request to completing cargo by his ID.
     * @param cargoId cargo ID
     * @return is completed or not
     * @throws IOException
     */
    public boolean sendRequestToCompletingCargo(int cargoId) throws IOException;

}
