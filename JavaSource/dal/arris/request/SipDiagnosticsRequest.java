/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris.request;

import dal.arris.ComandoArris;
import dal.arris.RequestCapabilityDiagnosticComplex;
import dal.arris.capability.EnumCapabilityComplex;

/**
 *
 * @author G0042204
 */
public class SipDiagnosticsRequest extends RequestCapabilityDiagnosticComplex implements ComandoArris {

    public SipDiagnosticsRequest(Integer deviceId, String phyReferenceList) {
        super(EnumCapabilityComplex.sipDiagnostics.name(), deviceId, "{\"PhyReferenceList\":\"" + phyReferenceList + "\"}");
    }

}
