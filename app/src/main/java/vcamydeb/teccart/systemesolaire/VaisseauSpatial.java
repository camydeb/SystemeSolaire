package vcamydeb.teccart.systemesolaire;

public class VaisseauSpatial extends Object {

    private String nomImage;
    private int height;
    private int width;

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public VaisseauSpatial(String nomImage , int height, int width) {
        this.nomImage = nomImage;
        this.height = height;
        this.width = width;
    }
}
