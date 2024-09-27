package Multimedia;

public abstract class ElementoMultimediale {
    private String titolo;
    public boolean riproducibile = false;
    protected int durata;

    public ElementoMultimediale(String titolo){
        this.titolo = titolo;
    }

    public ElementoMultimediale(String titolo, int durata){
        this.titolo = titolo;
        if(durata > 0){
            this.durata = durata;
            this.riproducibile = true;
        }
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }
}
