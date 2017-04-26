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
public abstract class RequestCapabilityDiagnostic extends RequestCapability implements ComandoArris {

    private String input;

    public RequestCapabilityDiagnostic(String capability, Integer deviceId) {
        super(capability, deviceId);
    }

    @Override
    public String getRequestUrl() {
        return super.getRequestUrl() + "diagnostic?diagnostic=\"" + this.capability + "\"&deviceId=" + this.deviceId;
    }

}
