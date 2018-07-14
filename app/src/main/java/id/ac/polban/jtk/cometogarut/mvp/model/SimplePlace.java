package id.ac.polban.jtk.cometogarut.mvp.model;

/**
 * Class hasil pencarian sesuai dg struktur JSON
 * @author Mufid Jamaluddin
 */
public class SimplePlace
{
    private Integer id;
    private String name;
    private String link_photo;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLink_photo()
    {
        return link_photo;
    }

    public void setLink_photo(String link_photo)
    {
        this.link_photo = link_photo;
    }
}