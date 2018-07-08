package id.ac.polban.jtk.cometogarut.mvp.model;

/**
 * {"status":"OK","data": {...}}
 * @param <T> : Tipe data
 */
public class RespObj<T>
{
    private String status;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
