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
        System.out.println("Il volume è adesso del: " + MultimediaPlayer.getVolume() + " %");
    }
    public static void abbassaVolume(int volume) {
        if(volume > MultimediaPlayer.volume){
            MultimediaPlayer.volume = 0;
        }else{
            MultimediaPlayer.volume -= volume;
        }
        System.out.println("Il volume è adesso del: " + MultimediaPlayer.getVolume() + " %");
    }

    public static void alzaLuminosita(int luminosita) {
        if(luminosita > 100 - MultimediaPlayer.luminosita){
            MultimediaPlayer.luminosita = 100;
        }else{
            MultimediaPlayer.luminosita += luminosita;
        }
        System.out.println("La luminosità è adesso del: " + MultimediaPlayer.getLuminosita() + " %");
    }
    public static void abbassaLuminosita(int luminosita) {
        if(luminosita > MultimediaPlayer.luminosita){
            MultimediaPlayer.luminosita = 0;
        }else{
            MultimediaPlayer.luminosita -= luminosita;
        }
        System.out.println("La luminosità è adesso del: " + MultimediaPlayer.getLuminosita() + " %");
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
    private static void gesticiVolLum(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prego scegli cosa modificare");
        System.out.println("1 per Volume - 2 per Luminosita - 0 per tornare indietro");
        String scelta = sc.nextLine();
        switch (Integer.parseInt(scelta)){
            case 0 -> {
                System.out.println("Torno indietro");
            }
            case 1 -> {
                System.out.println("1 per alzare volume, 2 per abbassare");
                String s = sc.nextLine();
                if(Integer.parseInt(s) == 1){
                    System.out.println("Di quanto vuoi alzare il volume?");
                    String vol = sc.nextLine();
                    MultimediaPlayer.alzaVolume(Integer.parseInt(vol));
                } else {
                    System.out.println("Di quanto vuoi abbassare il volume?");
                    String vol = sc.nextLine();
                    MultimediaPlayer.abbassaVolume(Integer.parseInt(vol));
                }
            }
            case 2 -> {
                System.out.println("1 per alzare luminosita, 2 per abbassare");
                String s = sc.nextLine();
                if(Integer.parseInt(s) == 1){
                    System.out.println("Di quanto vuoi alzare la luminosita?");
                    String lum = sc.nextLine();
                    MultimediaPlayer.alzaLuminosita(Integer.parseInt(lum));
                } else {
                    System.out.println("Di quanto vuoi abbassare la luminosita?");
                    String volume = sc.nextLine();
                    MultimediaPlayer.abbassaLuminosita(Integer.parseInt(volume));

                }
            }
        }

    }

    private static void insert(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Che cosa vuoi inserire");
        System.out.println("1 per Audio - 2 per Video - 3 per Immagine");
        String s = sc.nextLine();
        switch (Integer.parseInt(s)){
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

    private static void riproduzioneRapida(){
        riproduzione.clear();
        insert();
        System.out.println("Elemento in riproduzione");
        MultimediaPlayer.riproduci(riproduzione.getFirst());
        System.out.println("Riproduzione terminata");
    }

    private static void riproduzioneCoda(){
        riproduzione.clear();
        Scanner sc = new Scanner(System.in);
        System.out.println("Per favore inserisci 5 elementi multimediali");
        for(int i = 0; i < 5; i++){
            insert();
        }
        System.out.println("Bene tutto settato");
        while (true){
            System.out.println("Adesso scegli quale elemento riprodurre da 1 a 5, 0 per tornare indietro");
            String scelta = sc.nextLine();
            switch (Integer.parseInt(scelta)){
                case 0 -> {
                    System.out.println("Torno indietro");
                }
                case 1 -> MultimediaPlayer.riproduci(riproduzione.get(0));
                case 2 -> MultimediaPlayer.riproduci(riproduzione.get(1));
                case 3 -> MultimediaPlayer.riproduci(riproduzione.get(2));
                case 4 -> MultimediaPlayer.riproduci(riproduzione.get(3));
                case 5 -> MultimediaPlayer.riproduci(riproduzione.get(4));
            }
        }

    }

    public static void avvia(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Che cosa vuoi fare?");
            System.out.println("1 - Riproduzione rapida elemento multimediale");
            System.out.println("2 - Regola volume/luminosità");
            System.out.println("3 - Riproduzione a scelta di 5 elementi in coda");
            System.out.println("4 - Spegni Player");
            String scelta = sc.nextLine();
            if(Integer.parseInt(scelta) == 4) return;
            switch (Integer.parseInt(scelta)){
                case 1 -> MultimediaPlayer.riproduzioneRapida();
                case 2 -> MultimediaPlayer.gesticiVolLum();
                case 3 -> MultimediaPlayer.riproduzioneCoda();
                default -> {
                    System.out.println("A presto");
                }
            }
        }
    }



}

