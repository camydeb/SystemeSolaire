package vcamydeb.teccart.systemesolaire;

public class AstreCeleste extends Object {

    private String NomAstre;
    private int TailleAstre;
    private String CouleurAstre;
    private boolean StatusAstre;
    private String NomImageAstre;

    public String getNomAstre() {
        return NomAstre;
    }

    public void setNomAstre(String nomAstre) {
        NomAstre = nomAstre;
    }

    public int getTailleAstre() {
        return TailleAstre;
    }

    public void setTailleAstre(int tailleAstre) {
        TailleAstre = tailleAstre;
    }

    public String getCouleurAstre() {
        return CouleurAstre;
    }

    public void setCouleurAstre(String couleurAstre) {
        CouleurAstre = couleurAstre;
    }

    public boolean isStatusAstre() {
        return StatusAstre;
    }

    public void setStatusAstre(boolean statusAstre) {
        StatusAstre = statusAstre;
    }

    public String getNomImageAstre() {
        return NomImageAstre;
    }

    public void setNomImageAstre(String nomImageAstre) {
        NomImageAstre = nomImageAstre;
    }

    public AstreCeleste(String nomAstre, int tailleAstre, String couleurAstre, boolean statusAstre, String nomImageAstre) {
        NomAstre = nomAstre;
        TailleAstre = tailleAstre;
        CouleurAstre = couleurAstre;
        StatusAstre = statusAstre;
        NomImageAstre = nomImageAstre;
    }
}
