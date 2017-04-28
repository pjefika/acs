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
public class RequestCapabilityDiagnostic extends RequestCapability implements ComandoArris {

    public RequestCapabilityDiagnostic(String capability, Integer deviceId) {
        super(capability, deviceId);
    }

    @Override
    public String getRequestUrl() {
        return super.getRequestUrl() + "diagnostic?diagnostic=" + encode(this.capability) + "&deviceId=" + encode(this.deviceId.toString());
    }
}
