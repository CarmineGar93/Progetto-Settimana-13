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
        System.out.println("Elemento " + el.getTitolo() +" in riproduzione");
        el.play();
        MultimediaPlayer.ultimoinRiproduzione = el;
        System.out.println("Riproduzione terminata");
    }
    private static void gesticiVolLum(ElementoMultimediale el){
        Scanner sc = new Scanner(System.in);
        System.out.println("Prego scegli cosa modificare");
        System.out.println("1 per Volume - 2 per Luminosita - 0 per tornare indietro");
        String scelta = sc.nextLine();
        switch (Integer.parseInt(scelta)){
            case 0 -> {
                System.out.println("Torno indietro");
            }
            case 1 -> {
                if(el instanceof Immagine) {
                    System.out.println("Mi dispiace non è possibile regolare il volume. File non supportato");
                } else {
                    System.out.println("1 per alzare volume, 2 per abbassare");
                    String s = sc.nextLine();
                    if(Integer.parseInt(s) == 1){
                        System.out.println("Di quanto vuoi alzare il volume?");
                        String vol = sc.nextLine();
                        if(el instanceof Video){
                            ((Video) el).alzaVolume(Integer.parseInt(vol));
                        } else {
                            ((RegistrazioneAudio) el).alzaVolume(Integer.parseInt(vol));
                        }
                    } else if(Integer.parseInt(s) == 2) {
                        System.out.println("Di quanto vuoi abbassare il volume?");
                        String vol = sc.nextLine();
                        if(el instanceof Video){
                            ((Video) el).abbassaVolume(Integer.parseInt(vol));
                        } else {
                            ((RegistrazioneAudio) el).abbassaVolume(Integer.parseInt(vol));
                        }
                    } else {
                        System.out.println("Torno indietro");
                    }
                }
            }
            case 2 -> {
                if(el instanceof RegistrazioneAudio) {
                    System.out.println("Mi dispiace non è possibile regolare la luminosità. File non supportato");
                } else {
                    System.out.println("1 per alzare luminosita, 2 per abbassare");
                    String s = sc.nextLine();
                    if(Integer.parseInt(s) == 1){
                        System.out.println("Di quanto vuoi alzare la luminosita?");
                        String lum = sc.nextLine();
                        if(el instanceof Video){
                            ((Video) el).alzaLuminosita(Integer.parseInt(lum));
                        } else {
                            ((Immagine) el).alzaLuminosita(Integer.parseInt(lum));
                        }
                    } else if(Integer.parseInt(s) == 2) {
                        System.out.println("Di quanto vuoi abbassare la luminosita?");
                        String lum = sc.nextLine();
                        if(el instanceof Video){
                            ((Video) el).abbassaLuminosita(Integer.parseInt(lum));
                        } else {
                            ((Immagine) el).abbassaLuminosita(Integer.parseInt(lum));
                        }

                    } else {
                        System.out.println("Torno indietro");
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
        MultimediaPlayer.riproduci(riproduzione.getFirst());
    }

    private static void gestisciSingolo(ElementoMultimediale el){
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Cosa vuoi fare con l'elemento " + el.getTitolo() + " ?");
            System.out.println("1 - Gestisci volume/ luminosità");
            System.out.println("2 - Riprodruci");
            System.out.println("0 - Torna indietro");
            String s = sc.nextLine();
            if(Integer.parseInt(s) == 0) break;
            if(Integer.parseInt(s) == 1) MultimediaPlayer.gesticiVolLum(el);
            else MultimediaPlayer.riproduci(el);

        }

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
            System.out.println("Adesso scegli quale elemento vuoi visualizzare da 1 a 5, 0 per tornare indietro");
            String scelta = sc.nextLine();
            if(Integer.parseInt(scelta) == 0) break;
            else MultimediaPlayer.gestisciSingolo(riproduzione.get(Integer.parseInt(scelta) - 1));
        }

    }

    public static void riproduciUltimo(){
        if(ultimoinRiproduzione == null){
            System.out.println("Nessun elemento in coda");
        } else {
            MultimediaPlayer.riproduci(ultimoinRiproduzione);
        }

    }

    public static void avvia(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Volume e luminosita di default sono impostati al 50 %");
            System.out.println("Che cosa vuoi fare?");
            System.out.println("1 - Riproduzione rapida elemento multimediale");
            System.out.println("2 - Riproduzione rapida ultimo elemento visualizzato");
            System.out.println("3 - Regola volume/luminosità dell'ultimo elemento visualizzato");
            System.out.println("4 - Riproduzione a scelta di 5 elementi in coda");
            System.out.println("5 - Spegni Player");
            String scelta = sc.nextLine();
            switch (Integer.parseInt(scelta)){
                case 1 -> MultimediaPlayer.riproduzioneRapida();
                case 2 -> MultimediaPlayer.riproduciUltimo();
                case 3 -> MultimediaPlayer.gesticiVolLum(ultimoinRiproduzione);
                case 4 -> MultimediaPlayer.riproduzioneCoda();
                default -> {
                    System.out.println("A presto");
                    return;
                }
            }
        }
    }



}

