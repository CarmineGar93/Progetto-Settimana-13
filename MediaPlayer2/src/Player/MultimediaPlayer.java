package Player;

import Multimedia.ElementoMultimediale;
import Multimedia.Immagine;
import Multimedia.RegistrazioneAudio;
import Multimedia.Video;

import java.util.ArrayList;
import java.util.Scanner;

public class MultimediaPlayer {
    public static ArrayList<ElementoMultimediale> riproduzione = new ArrayList<>();
    public static ElementoMultimediale ultimoinRiproduzione;
    public static void riproduci(ElementoMultimediale el){
        el.play();
        MultimediaPlayer.ultimoinRiproduzione = el;
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
                if(ultimoinRiproduzione instanceof Immagine) {
                    System.out.println("Mi dispiace non è possibile regolare il volume. File non supportato");
                } else {
                    System.out.println("1 per alzare volume, 2 per abbassare");
                    String s = sc.nextLine();
                    if(Integer.parseInt(s) == 1){
                        System.out.println("Di quanto vuoi alzare il volume?");
                        String vol = sc.nextLine();
                        if(ultimoinRiproduzione instanceof Video){
                            ((Video) ultimoinRiproduzione).alzaVolume(Integer.parseInt(vol));
                        } else {
                            ((RegistrazioneAudio) ultimoinRiproduzione).alzaVolume(Integer.parseInt(vol));
                        }
                    } else {
                        String vol = sc.nextLine();
                        if(ultimoinRiproduzione instanceof Video){
                            ((Video) ultimoinRiproduzione).abbassaVolume(Integer.parseInt(vol));
                        } else {
                            ((RegistrazioneAudio) ultimoinRiproduzione).abbassaVolume(Integer.parseInt(vol));
                        }
                    }
                }
            }
            case 2 -> {
                if(ultimoinRiproduzione instanceof RegistrazioneAudio) {
                    System.out.println("Mi dispiace non è possibile regolare il volume. File non supportato");
                } else {
                    System.out.println("1 per alzare luminosita, 2 per abbassare");
                    String s = sc.nextLine();
                    if(Integer.parseInt(s) == 1){
                        System.out.println("Di quanto vuoi alzare la luminosita?");
                        String lum = sc.nextLine();
                        if(ultimoinRiproduzione instanceof Video){
                            ((Video) ultimoinRiproduzione).alzaLuminosita(Integer.parseInt(lum));
                        } else {
                            ((Immagine) ultimoinRiproduzione).alzaLuminosita(Integer.parseInt(lum));
                        }
                    } else {
                        System.out.println("Di quanto vuoi abbassare la luminosita?");
                        String lum = sc.nextLine();
                        if(ultimoinRiproduzione instanceof Video){
                            ((Video) ultimoinRiproduzione).abbassaLuminosita(Integer.parseInt(lum));
                        } else {
                            ((Immagine) ultimoinRiproduzione).abbassaLuminosita(Integer.parseInt(lum));
                        }

                    }
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
        riproduzione.getFirst().play();
        ultimoinRiproduzione = riproduzione.getFirst();
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
                    return;
                }
                case 1 -> riproduzione.get(0).play();
                case 2 -> riproduzione.get(1).play();
                case 3 -> riproduzione.get(2).play();
                case 4 -> riproduzione.get(3).play();
                case 5 -> riproduzione.get(4).play();

            }
        }

    }

    public static void riproduciUltimo(){
        System.out.println("Ultimo elemento in riproduzione");
        ultimoinRiproduzione.play();
        System.out.println("Riproduzione terminata");
    }

    public static void avvia(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Che cosa vuoi fare?");
            System.out.println("1 - Riproduzione rapida elemento multimediale");
            System.out.println("2 - Riproduzione rapida ultimo elemento visualizzato");
            System.out.println("3 - Regola volume/luminosità dell'ultimo elemento visualizzato");
            System.out.println("4 - Riproduzione a scelta di 5 elementi in coda");
            System.out.println("5 - Spegni Player");
            String scelta = sc.nextLine();
            if(Integer.parseInt(scelta) == 5) {
                System.out.println("A presto");
                return;
            }
            switch (Integer.parseInt(scelta)){
                case 1 -> MultimediaPlayer.riproduzioneRapida();
                case 2 -> MultimediaPlayer.riproduciUltimo();
                case 3 -> MultimediaPlayer.gesticiVolLum();
                case 4 -> MultimediaPlayer.riproduzioneCoda();
                default -> {
                    System.out.println("A presto");
                }
            }
        }
    }



}

