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
public class RequestCapabilityExecuteInput extends RequestCapabilityExecute implements ComandoArris {

    protected String input;

    public RequestCapabilityExecuteInput(String capability, Integer deviceId, String input) {
        super(capability, deviceId);
        this.input = input;
    }

    @Override
    public String getRequestUrl() {
        return super.getRequestUrl() + "&input=" + this.input;
    }
}
