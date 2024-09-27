package Multimedia;

public class RegistrazioneAudio extends ElementoMultimediale implements hasVolume{
    private int volume = 50;
    public RegistrazioneAudio(String titolo, int durata) {
        super(titolo, durata);
    }

    private StringBuilder esclamation(){
        StringBuilder esclamations = new StringBuilder();
        esclamations.append("!".repeat(Math.max(0, this.getVolume())));
        return esclamations;
    }

    @Override
    public void play() {
        for(int i = 0; i < this.getDurata(); i++){
            System.out.println(this.getTitolo() + super.esclamation(this.getVolume()));
        }
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public void abbassaVolume(int volume) {
        if(volume > this.volume){
            this.volume = 0;
        }else{
            this.volume -= volume;
        }
        System.out.println("Il volume dell'audio è adesso del: " + this.getVolume() + " %");
    }

    @Override
    public void alzaVolume(int volume) {
        if(volume > 100 - this.volume){
           this.volume = 100;
        }else{
            this.volume += volume;
        }
        System.out.println("Il volume dell'audio è adesso del: " + this.getVolume() + " %");
    }
}
