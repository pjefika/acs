/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.arris.request;

import dal.arris.ComandoArris;
import dal.arris.RequestCapabilityDiagnosticComplex;

/**
 *
 * @author G0042204
 */
public class SipDiagnosticsRequest extends RequestCapabilityDiagnosticComplex implements ComandoArris {

    public SipDiagnosticsRequest(Integer deviceId) {
        super("sipDiagnostics", deviceId, "{\"PhyReferenceList\":\"" + 1 + "\"}");
    }

}
