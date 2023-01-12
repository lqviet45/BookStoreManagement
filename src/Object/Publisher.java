package Object;

/**
 *
 * @author DELL
 */
public class Publisher {
    private String publisherID, publisherName, publisherPhone;

    public Publisher() {
    }

    public Publisher(String publisherID, String publisherName, String publisherPhone) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.publisherPhone = publisherPhone;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    @Override
    public String toString() {
        return getPublisherID() + " - " + getPublisherName() + " - " + getPublisherPhone();
    }
    
    
}
