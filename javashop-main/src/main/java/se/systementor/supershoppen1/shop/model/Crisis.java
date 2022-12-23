package se.systementor.supershoppen1.shop.model;


import com.fasterxml.jackson.annotation.JsonProperty;
public class Crisis {

    @JsonProperty("ID")
    private int ID;
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Text")
    private String Text;
    @JsonProperty("ImageUrl")
    private String ImageUrl;
    @JsonProperty("LinkUrl")
    private String LinkUrl;
    @JsonProperty("Displaymode")
    private int Displaymode;
    @JsonProperty("Emergency")
    private boolean Emergency;

    public Crisis(int ID, String title, String text, String imageUrl, String linkUrl, int displaymode, boolean emergency) {
        this.ID = ID;
        Title = title;
        Text = text;
        ImageUrl = imageUrl;
        LinkUrl = linkUrl;
        Displaymode = displaymode;
        Emergency = emergency;
    }

    public Crisis() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLinkUrl() {
        return LinkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        LinkUrl = linkUrl;
    }

    public int getDisplaymode() {
        return Displaymode;
    }

    public void setDisplaymode(int displaymode) {
        Displaymode = displaymode;
    }

    public String getEmergency() {
        if(Emergency == true) {
            return "BRÅDSKANDE";
        }
        return null;
    }

    public void setEmergency(boolean emergency) {
        Emergency = emergency;
    }
}
