//Imports
import java.util.ArrayList;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;

//Javadoc
/**
 * Challenge 8 -- MusicRecommender
 *
 * Read and write updates to input music
 * file, and allow user to search available
 * songs for specified parameters. 
 * 
 * @author Brady Williams, L23
 *
 * @version Mar 4, 2024
 *
 */
public class MusicRecommender {

    private String musicListFileName;
    private ArrayList<Music> music;

    
    public MusicRecommender(String musicListFileName) throws FileNotFoundException, MusicFileFormatException {

        this.musicListFileName = musicListFileName;
        music = new ArrayList<Music>();

        File f = new File(musicListFileName);
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        String line;
        try {
            line = bfr.readLine();
            while (line != null) {
                music.add(parseMusic(line));
                line = bfr.readLine();
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }


    private static Music parseMusic(String musicInfoLine) throws MusicFileFormatException {

        int spaces = 0;
        String trackName = "";
        String artistName = ""; 
        String genre = "";
        String bpm = "";
        String popularity = "";


        for (int i = 0; i < musicInfoLine.length(); i++) {

            String currentStr = musicInfoLine.substring(i, i + 1);

            if (currentStr.equals(" ")) {
                spaces++;
                continue;
            }

            switch (spaces) {
                case 0: 
                    if (currentStr.equals("_")) {
                        trackName = trackName + " ";
                    } else {
                        trackName = trackName + currentStr;
                    }
                    
                    break;
                case 1: 
                    if (currentStr.equals("_")) {
                        artistName = artistName + " ";
                    } else {
                        artistName = artistName + currentStr;
                    }
                    break;
                case 2: 
                    if (currentStr.equals("_")) {
                        genre = genre + " ";
                    } else {
                        genre = genre + currentStr;
                    }
                    break;
                case 3: 
                    bpm = bpm + currentStr;
                    break;
                case 4:
                    popularity = popularity + currentStr;
                    break;
            }

        }

        if (spaces != 4 || 
            trackName.length() == 0 || 
            artistName.length() == 0 || 
            genre.length() == 0 || 
            !getInteger(bpm) || 
            !getInteger(popularity)) {
            throw new MusicFileFormatException("One of the lines of the music list file is malformed!");
        } else {
            return new Music(trackName, artistName, genre, Integer.parseInt(bpm), Integer.parseInt(popularity));
        }
    }

    public Music BPMBasedRecommendation(MusicProfile musicProfile) throws NoRecommendationException {
        int index = 0;
        int difference = Integer.MAX_VALUE;

        for (int i = 0; i < music.size(); i++) {
            if (Math.abs(musicProfile.getPreferredBPM() - music.get(i).getBPM()) < difference) {
                difference = Math.abs(musicProfile.getPreferredBPM() - music.get(i).getBPM());
                index = i;
            } else if (Math.abs(musicProfile.getPreferredBPM() - music.get(i).getBPM()) == difference) {
                if (musicProfile.isLikePopular()) {
                    if (music.get(index).getPopularity() > music.get(i).getPopularity()) {
                        continue;
                    } else {
                        index = i;
                    }


                    index = i;
                } else {
                    if (music.get(index).getPopularity() > music.get(i).getPopularity()) {
                        index = i;
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
        if (difference > 20) {
            throw new NoRecommendationException("There was no music with your preferred BPM!");
        } else {
            music.get(index).setPopularity(music.get(index).getPopularity() + 1);
            return music.get(index);
        }

    }

    public Music genreBasedRecommendation(MusicProfile musicProfile) throws NoRecommendationException {
        int index = -1;
        int rating = 0;
        String songRec = musicProfile.getPreferredGenre().toLowerCase();

        for (int i = 0; i < music.size(); i++) {
            if (music.get(i).getGenre().toLowerCase().contains(songRec)) {
                if (musicProfile.isLikePopular()) {
                    if (music.get(i).getPopularity() > rating) {
                        index = i;
                        rating = music.get(i).getPopularity();
                    } else {
                        continue;
                    }
                } else {
                    if (music.get(i).getPopularity() > rating) {
                        continue;
                    } else {
                        index = i;
                        rating = music.get(i).getPopularity();
                    }
                }
            }
        }

        if (index == -1) {
            throw new NoRecommendationException("There was no music with your preferred genre!");
        } else {
            music.get(index).setPopularity(music.get(index).getPopularity() + 1);
            return music.get(index);
        }
    }

    public Music getMostPopularMusic() {
        int index = -1;
        int rating = 0;

        for (int i = 0; i < music.size(); i++) {
            if (music.get(i).getPopularity() > rating) {
                index = i;
                rating = music.get(i).getPopularity();
            }
        }

        music.get(index).setPopularity(music.get(index).getPopularity() + 1);
        return music.get(index);
    }

    public void saveMusicList() {

        File f = new File(musicListFileName);
        f.delete();
        try {
            if (f.createNewFile()) {
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < music.size(); i++) {
                    String trackName = music.get(i).getTrack();
                    trackName = trackName.replaceAll(" ", "_");
        
                    String artistName = music.get(i).getArtist();
                    artistName = artistName.replaceAll(" ", "_");
        
                    String genre = music.get(i).getGenre();
                    genre = genre.replaceAll(" ", "_");

                    pw.printf("%s %s %s %d %d\n", 
                        trackName, 
                        artistName, 
                        genre, 
                        music.get(i).getBPM(), 
                        music.get(i).getPopularity()
                    );
        
                }
                pw.close();
            } else {
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                for (int i = 0; i < music.size(); i++) {
                    String trackName = music.get(i).getTrack();
                    trackName = trackName.replaceAll(" ", "_");
        
                    String artistName = music.get(i).getArtist();
                    artistName = artistName.replaceAll(" ", "_");
        
                    String genre = music.get(i).getGenre();
                    genre = genre.replaceAll(" ", "_");

                    pw.printf("%s %s %s %d %d\n", 
                        trackName, 
                        artistName, 
                        genre, 
                        music.get(i).getBPM(), 
                        music.get(i).getPopularity()
                    );
                }
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        
    }

    public ArrayList<Music> searchArtists(MusicProfile musicProfile) throws NoRecommendationException {
        ArrayList<Music> artistMusic = new ArrayList<Music>();
        String search = musicProfile.getPreferredArtist().toLowerCase();

        for (int i = 0; i < music.size(); i++) {
            if (music.get(i).getArtist().toLowerCase().contains(search)) {
                music.get(i).setPopularity(music.get(i).getPopularity() + 1);
                artistMusic.add(music.get(i));
            }
        }

        if (artistMusic.size() == 0) {
            throw new NoRecommendationException("There was no music with your preferred genre!");
        } else {
            return artistMusic;
        }
    }


    public static boolean getInteger(String input) {
        try {
            int val = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
