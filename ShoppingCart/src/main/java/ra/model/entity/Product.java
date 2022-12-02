package ra.model.entity;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String productImage;
    private String descriptions;
    private boolean productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String productImage, String descriptions, boolean productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productImage = productImage;
        this.descriptions = descriptions;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }
}
