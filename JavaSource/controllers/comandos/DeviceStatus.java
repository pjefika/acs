package controllers.comandos;

import dal.arris.RequestCapabilityExecute;
import dal.arris.capability.EnumCapabilitySimple;
import entidades.deviceStatus.DeviceStatusHolder;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.GsonUtil;
import util.JSFUtil;

@ManagedBean
@RequestScoped
public class DeviceStatus extends AcsAbstractBean {

    private DeviceStatusHolder deviceStatusHolder;

    public DeviceStatus() {
    }

    public void getDeviceStatus(Integer deviceId) {
        try {
            String response = dao.request(new RequestCapabilityExecute(EnumCapabilitySimple.DeviceStatus.name(), deviceId)).getResult();
            System.out.println(response);
            deviceStatusHolder = (DeviceStatusHolder) GsonUtil.convert(response, DeviceStatusHolder.class);
        } catch (Exception e) {
            JSFUtil.addErrorMessage(e.getMessage());
        }
    }

    public DeviceStatusHolder getDeviceStatusHolder() {
        return deviceStatusHolder;
    }

    public void setDeviceStatusHolder(DeviceStatusHolder deviceStatusHolder) {
        this.deviceStatusHolder = deviceStatusHolder;
    }

}
