/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

/**
 *
 * @author G0042204
 */
public abstract class RequestCapability extends RequestArris implements ComandoArris {

    protected String capability;

    protected Integer deviceId;

    public RequestCapability(String capability, Integer deviceId) {
        this.capability = capability;
        this.deviceId = deviceId;
    }

    @Override
    public String getRequestUrl() {
        return super.getEndPoint().getUrl() + "capability/";
    }

    @Override
    public String getCapability() {
        return capability;
    }

    @Override
    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String getResult() {
        return result;
    }

}
