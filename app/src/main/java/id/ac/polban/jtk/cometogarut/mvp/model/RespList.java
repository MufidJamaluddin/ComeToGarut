package id.ac.polban.jtk.cometogarut.mvp.model;

import java.util.List;

/**
 * {"status":"OK","data": [{object1}, {object2}...]}
 * @param <T> : Tipe object isi list data
 * @author Mufid Jamaluddin
 */
public class RespList<T>
{
    private String status;
    private List<T> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
