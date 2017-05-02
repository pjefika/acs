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
public class RequestCapabilityListCapabilityNames extends RequestCapability implements ComandoArris {

    public RequestCapabilityListCapabilityNames(Integer deviceId) {
        super("listCapabilityNamesFor", deviceId);
    }

    @Override
    public String getRequestUrl() {
        return super.getRequestUrl() + "listCapabilityNamesFor?deviceId=" + encode(this.deviceId.toString());
    }
}
