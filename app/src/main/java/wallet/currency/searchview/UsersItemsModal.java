package wallet.currency.searchview;

public class UsersItemsModal {
    public UsersItemsModal(String items_id,String items_name, String items_price, String items_discount,String moderator_id, String items_total, String items_pic) {
        this.items_name = items_name;
        this.items_price = items_price;
        this.items_discount = items_discount;
        this.items_total = items_total;
        this.items_pic = items_pic;
        this.moderator_id = moderator_id;
        this.items_id = items_id;
    }

    private String items_name;

    public String getItems_name() {
        return items_name;
    }

    public String getItems_price() {
        return items_price;
    }

    public String getItems_discount() {
        return items_discount;
    }

    public String getItems_total() {
        return items_total;
    }

    public String getItems_pic() {
        return items_pic;
    }

    private String items_price;

    public String getItems_id() {
        return items_id;
    }

    private String items_id;
    private String items_discount;
    private String items_total;
    private String items_pic;

    public String getModerator_id() {
        return moderator_id;
    }

    private String moderator_id;


}

