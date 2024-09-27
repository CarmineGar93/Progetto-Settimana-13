package Player;

import Multimedia.ElementoMultimediale;
import Multimedia.Immagine;
import Multimedia.RegistrazioneAudio;
import Multimedia.Video;

import java.util.ArrayList;
import java.util.Scanner;

public class MultimediaPlayer {
    public static int volume = 50;
    public static int luminosita = 50;
    public static ArrayList<ElementoMultimediale> riproduzione = new ArrayList<>();

    public static int getVolume() {
        return volume;
    }

    public static void setVolume(int volume) {
        MultimediaPlayer.volume = volume;
    }

    public static int getLuminosita() {
        return luminosita;
    }

    public static void alzaVolume(int volume) {
        if(volume > 100 - MultimediaPlayer.volume){
            MultimediaPlayer.volume = 100;
        }else{
            MultimediaPlayer.volume += volume;
        }
        System.out.println("Il volume è adesso del: " + MultimediaPlayer.getVolume());
    }
    public static void abbassaVolume(int volume) {
        if(volume > MultimediaPlayer.volume){
            MultimediaPlayer.volume = 0;
        }else{
            MultimediaPlayer.volume -= volume;
        }
        System.out.println("Il volume è adesso del: " + MultimediaPlayer.getVolume());
    }

    public static void alzaLuminosita(int luminosita) {
        if(luminosita > 100 - MultimediaPlayer.luminosita){
            MultimediaPlayer.luminosita = 100;
        }else{
            MultimediaPlayer.luminosita += luminosita;
        }
        System.out.println("La luminosità è adesso del: " + MultimediaPlayer.getLuminosita());
    }
    public static void abbassaLuminosita(int luminosita) {
        if(luminosita > MultimediaPlayer.luminosita){
            MultimediaPlayer.luminosita = 0;
        }else{
            MultimediaPlayer.luminosita -= luminosita;
        }
        System.out.println("La luminosità è adesso del: " + MultimediaPlayer.getLuminosita());
    }

    private static StringBuilder esclamation(){
        StringBuilder esclamations = new StringBuilder();
        for (int i = 0; i < MultimediaPlayer.getVolume(); i++){
            esclamations.append("!");
        }
        return esclamations;
    }

    private static StringBuilder asterisk(){
        StringBuilder asterisks = new StringBuilder();
        for (int i = 0; i < MultimediaPlayer.getLuminosita(); i++){
            asterisks.append("*");
        }
        return asterisks;
    }

    public static void riproduci(ElementoMultimediale el){
        if(el.riproducibile){
            for(int i = 0; i < el.getDurata(); i++){
                System.out.println(el.getTitolo() + (el instanceof RegistrazioneAudio ? esclamation() : esclamation().append(asterisk())));
            }
        } else {
            System.out.println(el.getTitolo() + asterisk());
        }
    }

    public static void avvia(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Benvenuto nel Multiplayer numero uno al mondo");
        System.out.println("Per favore inserisci 5 elementi multimediali");
        for(int i = 0; i < 5; i++){
            System.out.println("Per favore indica che cosa vuoi inserire");
            System.out.println("1 per Audio - 2 per Video - 3 per Immagine");
            int scelta = sc.nextInt();
            sc.nextLine();
                switch (scelta){
                    case 1 -> {
                        System.out.println("Prego inserire titolo audio");
                        String titolo = sc.nextLine();
                        System.out.println("Prego inserire durata audio");
                        String dur = sc.nextLine();
                        RegistrazioneAudio audio = new RegistrazioneAudio(titolo, Integer.parseInt(dur));
                        riproduzione.add(audio);
                    }
                    case 2 -> {
                        System.out.println("Prego inserire titolo video");
                        String titolo = sc.next();
                        System.out.println("Prego inserire durata video");
                        String dur = sc.next();
                        sc.nextLine();
                        Video video = new Video(titolo, Integer.parseInt(dur));
                        riproduzione.add(video);
                    }
                    case 3 -> {
                        System.out.println("Prego inserire titolo immagine");
                        String titolo = sc.next();
                        sc.nextLine();
                        Immagine immagine = new Immagine(titolo);
                        riproduzione.add(immagine);
                    }
                }

        }
        System.out.println("Bene tutto settato");

        while (true){
            System.out.println("Adesso scegli quale elemento riprodurre da 1 a 5, 0 per terminare");
            int scelta = sc.nextInt();
            switch (scelta){
                case 0 -> {
                    System.out.println("A presto");
                    return;
                }
                case 1 -> MultimediaPlayer.riproduci(riproduzione.get(0));
                case 2 -> MultimediaPlayer.riproduci(riproduzione.get(1));
                case 3 -> MultimediaPlayer.riproduci(riproduzione.get(2));
                case 4 -> MultimediaPlayer.riproduci(riproduzione.get(3));
                case 5 -> MultimediaPlayer.riproduci(riproduzione.get(4));
            }
        }

    }
}
