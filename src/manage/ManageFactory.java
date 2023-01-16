package manage;


public class ManageFactory {
    public static ManagementI getManagement(ManagementType type) {
        if (type == ManagementType.PUBLISHER) {
            return new PublisherManagement();
        } else if (type == ManagementType.BOOK) {
            return new BookManagement();
        }
        return null;
    }
}
