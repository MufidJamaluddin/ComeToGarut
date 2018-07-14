package id.ac.polban.jtk.cometogarut.mvp.model;

/**
 * Class pesan, sesuai dg struktur JSON
 * @author Mufid Jamaluddin
 */
public class Message
{
    private String status;
    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
