/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris;

import dal.arris.capability.EnumCapability;

/**
 *
 * @author G0042204
 */
public class RequestCapabilityDiagnosticSimple extends RequestCapabilityDiagnostic {

    public RequestCapabilityDiagnosticSimple(EnumCapability cap, Integer deviceId) {
        super(cap.name(), deviceId);
    }

}
