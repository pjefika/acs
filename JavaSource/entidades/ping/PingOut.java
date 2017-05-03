/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.ping;

/**
 *
 * @author G0042204
 */
public class PingOut {

    private String pivotColumn;

    private PingHolder[] values;

    public PingOut() {
    }

    public String getPivotColumn() {
        return pivotColumn;
    }

    public void setPivotColumn(String pivotColumn) {
        this.pivotColumn = pivotColumn;
    }

    public PingHolder[] getValues() {
        return values;
    }

    public void setValues(PingHolder[] values) {
        this.values = values;
    }

}
