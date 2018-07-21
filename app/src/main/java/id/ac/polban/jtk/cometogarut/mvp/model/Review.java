package id.ac.polban.jtk.cometogarut.mvp.model;

import android.text.TextUtils;

/**
 * Class Review sesuai dg struktur JSON
 * @author Mufid Jamaluddin
 */
public class Review
{
    private String place_id;
    private String id;
    private String name;
    private String email;
    private String message;
    private String purity_rate;
    private String policy_rate;
    private String security_rate;
    private String facility_rate;
    private String link_photo;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName()
    {
        if(name == null)
            return "";

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail()
    {
        if(email == null)
            return "";

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage()
    {
        if(message == null) return "";

        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getPurity_rate()
    {
        if(TextUtils.isEmpty(purity_rate)) return "0";
        return purity_rate;
    }

    public void setPurity_rate(String purity_rate)
    {
        this.purity_rate = purity_rate;
    }

    public String getPolicy_rate()
    {
        if(TextUtils.isEmpty(policy_rate)) return "0";
        return policy_rate;
    }

    public void setPolicy_rate(String policy_rate) {
        this.policy_rate = policy_rate;
    }

    public String getSecurity_rate()
    {
        if(TextUtils.isEmpty(security_rate)) return "0";
        return security_rate;
    }

    public void setSecurity_rate(String security_rate) {
        this.security_rate = security_rate;
    }

    public String getFacility_rate()
    {
        if(TextUtils.isEmpty(facility_rate)) return "0";
        return facility_rate;
    }

    public void setFacility_rate(String facility_rate) {
        this.facility_rate = facility_rate;
    }

    public String getLink_photo() {
        return link_photo;
    }

    public void setLink_photo(String link_photo) {
        this.link_photo = link_photo;
    }

}
