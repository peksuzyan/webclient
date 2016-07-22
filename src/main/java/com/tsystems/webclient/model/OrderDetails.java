package com.tsystems.webclient.model;

import com.tsystems.webclient.controller.RestClient;
import com.tsystems.webclient.controller.RestClientImpl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "orderDetails")
@SessionScoped
public class OrderDetails implements Serializable {

    /**
     * ID of an actual order.
     * It's got from remote server.
     */
    private int orderId;

    /**
     * Number of an actual truck.
     * It's got from remote server.
     */
    private String truckNumber;

    /**
     * Drivers email and full name.
     * It's got from remote server.
     */
    private Map<String, String> drivers;

    /**
     * Cargoes ID and destination point.
     * It's got from remote server.
     */
    private Map<Integer, String> cargoes;

    /**
     * Route points of an actual order.
     * It's got from remote server.
     */
    private List<String> routePoints;

    /**
     * Implementation instance of the REST client.
     */
    private RestClient restClient = new RestClientImpl();

    /**
     * Actual driver status of a current user.
     */
    private String status;

    /**
     * Actual id of current cargo.
     */
    private int cargoId;

    /**
     * Updates order details is got by REST client implementation.
     * @param orderDetails orderDetails
     */
    private void init(OrderDetails orderDetails) {
        if (orderDetails == null) return;
        this.orderId = orderDetails.getOrderId();
        this.truckNumber = orderDetails.getTruckNumber();
        this.drivers = orderDetails.getDrivers();
        this.cargoes = orderDetails.getCargoes();
        this.routePoints = orderDetails.getRoutePoints();
    }

    public void showOrderDetails() {
        try {
            init(restClient.sendRequestToGettingOrderDetails());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeStatus() {
        try {
            restClient.sendRequestToChangingStatus(status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void completeCargo() {
        try {
            restClient.sendRequestToCompletingCargo(cargoId);
            cargoes.remove(cargoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets cargoId.
     *
     * @return cargoId cargoId
     */
    public int getCargoId() {
        return cargoId;
    }

    /**
     * Sets cargoId.
     *
     * @param cargoId cargoId
     */
    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    /**
     * Default constructor.
     */
    public OrderDetails() {}

    /**
     * Gets orderId.
     *
     * @return orderId orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets orderId.
     *
     * @param orderId orderId
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets truckNumber.
     *
     * @return truckNumber truckNumber
     */
    public String getTruckNumber() {
        return truckNumber;
    }

    /**
     * Sets truckNumber.
     *
     * @param truckNumber truckNumber
     */
    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    /**
     * Gets drivers.
     *
     * @return drivers drivers
     */
    public Map<String, String> getDrivers() {
        return drivers;
    }

    /**
     * Sets drivers.
     *
     * @param drivers drivers
     */
    public void setDrivers(Map<String, String> drivers) {
        this.drivers = drivers;
    }

    /**
     * Gets cargoes.
     *
     * @return cargoes cargoes
     */
    public Map<Integer, String> getCargoes() {
        return cargoes;
    }

    /**
     * Sets cargoes.
     *
     * @param cargoes cargoes
     */
    public void setCargoes(Map<Integer, String> cargoes) {
        this.cargoes = cargoes;
    }

    /**
     * Gets routePoints.
     *
     * @return routePoints routePoints
     */
    public List<String> getRoutePoints() {
        return routePoints;
    }

    /**
     * Sets routePoints.
     *
     * @param routePoints routePoints
     */
    public void setRoutePoints(List<String> routePoints) {
        this.routePoints = routePoints;
    }

    /**
     * Gets status.
     *
     * @return status status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
