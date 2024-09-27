package Multimedia;

public class Immagine extends ElementoMultimediale implements hasLuminosity{
    private int luminosita = 50;
    public Immagine(String titolo) {
        super(titolo);
    }

    @Override
    public void play() {
        System.out.println(this.getTitolo() + super.asterisk(this.getLuminosita()));
    }

    public int getLuminosita() {
        return luminosita;
    }

    @Override
    public void alzaLuminosita(int luminosita) {
        if(luminosita > 100 - this.luminosita){
            this.luminosita = 100;
        }else{
            this.luminosita += luminosita;
        }
        System.out.println("La luminosità dell'immagine è adesso del: " + this.getLuminosita() + " %");
    }

    @Override
    public void abbassaLuminosita(int luminosita) {
        if(luminosita > this.luminosita){
            this.luminosita = 0;
        }else{
            this.luminosita -= luminosita;
        }
        System.out.println("La luminosità dell'immagine è adesso del: " + this.getLuminosita() + " %");
    }
}
