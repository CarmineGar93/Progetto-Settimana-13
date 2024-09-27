package Multimedia;

public class Video extends ElementoMultimediale implements hasVolume, hasLuminosity{
    private int volume = 50;
    private int luminosita = 50;
    public Video(String titolo, int durata) {
        super(titolo, durata);
    }

    public int getVolume() {
        return volume;
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
        System.out.println("La luminosità del video è adesso del: " + this.getLuminosita() + " %");
    }

    @Override
    public void abbassaLuminosita(int luminosita) {
        if(luminosita > this.luminosita){
            this.luminosita = 0;
        }else{
            this.luminosita -= luminosita;
        }
        System.out.println("La luminosità del video è adesso del: " + this.getLuminosita() + " %");
    }

    @Override
    public void play() {
        for(int i = 0; i < this.getDurata(); i++){
            System.out.println(this.getTitolo() + super.esclamation(this.getVolume()) + super.asterisk(this.getLuminosita()));
        }
    }

    @Override
    public void alzaVolume(int volume) {
        if(volume > 100 - this.volume){
            this.volume = 100;
        }else{
            this.volume += volume;
        }
        System.out.println("Il volume del video è adesso del: " + this.getVolume() + " %");
    }

    @Override
    public void abbassaVolume(int volume) {
        if(volume > this.volume){
            this.volume = 0;
        }else{
            this.volume -= volume;
        }
        System.out.println("Il volume del video è adesso del: " + this.getVolume() + " %");
    }
}
