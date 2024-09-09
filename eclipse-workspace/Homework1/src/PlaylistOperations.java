/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 1
 * CSE214.R30
 */

import java.util.Scanner;

/**
 * PlaylistOperations class to manage the playlist and provide options for the user.
 */
public class PlaylistOperations 
{
    private static Playlist[] playlists = new Playlist[50];
    private static String[] playlistNames = new String[50];
    private static int playlistCount = 0;
    private static Playlist currentPlaylist;
    private static String currentPlaylistName;

    /**
     * Method to initialize the default playlist.
     */
    private static void initializeDefaultPlaylist() 
    {
        Playlist defaultPlaylist = new Playlist();
        playlists[playlistCount] = defaultPlaylist;
        playlistNames[playlistCount] = "Default Playlist";
        currentPlaylist = defaultPlaylist;
        currentPlaylistName = "Default Playlist";
        playlistCount++;
    }

    /**
     * Method to find the index of a playlist by name.
     * @param name The name of the playlist.
     * @return The index of the playlist if found, otherwise -1.
     */
    private static int findPlaylistIndexByName(String name) 
    {
        for (int i = 0; i < playlistCount; i++) 
        {
            if (playlistNames[i].equals(name)) 
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Main method to run the playlist operations.
     * @param args command line arguments
     * @throws Exceptions.IncorrectTimeException if the time of the song is incorrect
     * @throws Exceptions.InvalidArgumentException if the position or other arguments are invalid
     * @throws Exceptions.FullPlaylistException if the playlist is full
     * @throws Exceptions.EmptyListException if the playlist is empty
     * @precondition None.
     * @postcondition The playlist operations are executed based on user input.
     */
    public static void main(String[] args) throws Exceptions.IncorrectTimeException, Exceptions.InvalidArgumentException, Exceptions.FullPlaylistException, Exceptions.EmptyListException 
    {
        initializeDefaultPlaylist();
        Scanner input = new Scanner(System.in);
        System.out.println("Make your Playlist!");
        System.out.println("Menu:\r\n"
                + "\r\n"
                + "     A) Add a song\r\n"
                + "\r\n"
                + "     G) Get a song\r\n"
                + "\r\n"
                + "     R) Remove a song\r\n"
                + "\r\n"
                + "     P) Print your playlist\r\n"
                + "\r\n"
                + "     B) Get an artist's songs\r\n"
                + "\r\n"
                + "     S) Get the size of the playlist\r\n"
                + "\r\n"
                + "     N) Create a new playlist and set as current\r\n"
                + "\r\n"
                + "     V) Change current playlist\r\n"
                + "\r\n"
                + "     C) Copy the current playlist's songs into a new playlist\r\n"
                + "\r\n"
                + "     E) Compare the songs in the current playlist with the given playlist\r\n"
                + "\r\n"
                + "     D) Display all playlist names\r\n"
                + "\r\n"
                + "     Q) Leave the playlist\r\n"
                + "\r\n"
                + "Please select an option");

        String option;
        boolean running = true;
        while (running) 
        {
            option = input.nextLine().toUpperCase();
            try 
            {
                switch (option) 
                {
                    case "A": 
                    {
                        System.out.println("Whats the name of the song?");
                        String title = input.nextLine();
                        System.out.println("What's the name of the artist?");
                        String artist = input.nextLine();
                        System.out.println("How many minutes are in the song?");
                        int minutes = input.nextInt();
                        System.out.println("How many seconds are in the song?");
                        int seconds = input.nextInt();
                        input.nextLine();
                        SongRecord song = new SongRecord();
                        song.setTitle(title);
                        song.setArtist(artist);
                        song.setSeconds(seconds);
                        song.setMinutes(minutes);
                        System.out.println("What position do you want the song to go to?");
                        int position = input.nextInt() - 1;
                        input.nextLine();
                        currentPlaylist.addSong(song, position);
                        System.out.println(currentPlaylist.getSong(position).getTitle() + " by " + currentPlaylist.getSong(position).getArtist());
                        break;
                    }
                    case "G": 
                    {
                        System.out.println("What position is the song you want to get?");
                        int position = input.nextInt() - 1;
                        input.nextLine();
                        SongRecord song = currentPlaylist.getSong(position);
                        System.out.println(song);
                        break;
                    }
                    case "R": 
                    {
                        System.out.println("What position do you want to remove?");
                        int position = input.nextInt() - 1;
                        input.nextLine();
                        System.out.println(currentPlaylist.getSong(position).getTitle() + " by " + currentPlaylist.getSong(position).getArtist() + " is removed");
                        currentPlaylist.removeSong(position);
                        break;
                    }
                    case "P": 
                    {
                        currentPlaylist.printAllSongs();
                        break;
                    }
                    case "B": 
                    {
                        System.out.println("Whats is the name of the artist?");
                        String artistName = input.nextLine();
                        Playlist artistPlaylist = Playlist.getSongsByArtist(currentPlaylist, artistName);
                        artistPlaylist.printAllSongs();
                        break;
                    }
                    case "S": 
                    {
                        System.out.println("The size of the playlist is " + currentPlaylist.size());
                        break;
                    }
                    case "N": 
                    {
                        System.out.println("Enter the name of the new playlist:");
                        String name = input.nextLine();
                        if (findPlaylistIndexByName(name) != -1) 
                        {
                            System.out.println("A playlist with this name already exists.");
                            break;
                        }
                        if (playlistCount >= playlists.length) 
                        {
                            System.out.println("Canot create more playlists.");
                            break;
                        }
                        Playlist newPlaylist = new Playlist();
                        playlists[playlistCount] = newPlaylist;
                        playlistNames[playlistCount] = name;
                        currentPlaylist = newPlaylist;
                        currentPlaylistName = name;
                        playlistCount++;
                        System.out.println("New playlist created and set as current: " + name);
                        break;
                    }
                    case "V": 
                    {
                        System.out.println("Enter the name of the playlist to set as current:");
                        String name = input.nextLine();
                        int index = findPlaylistIndexByName(name);
                        if (index == -1) 
                        {
                            System.out.println("No playlist found with this name.");
                            break;
                        }
                        currentPlaylist = playlists[index];
                        currentPlaylistName = name;
                        System.out.println("Current playlist changed to: " + name);
                        break;
                    }
                    case "C": 
                    {
                        System.out.println("Enter the name of the new playlist:");
                        String name = input.nextLine();
                        if (findPlaylistIndexByName(name) != -1) 
                        {
                            System.out.println("A playlist with this name already exists.");
                            break;
                        }
                        if (playlistCount >= playlists.length) 
                        {
                            System.out.println("Cannot create more playlists.");
                            break;
                        }
                        Playlist newPlaylist = (Playlist) currentPlaylist.clone();
                        playlists[playlistCount] = newPlaylist;
                        playlistNames[playlistCount] = name;
                        playlistCount++;
                        System.out.println("Current playlist copied to new playlist: " + name);
                        break;
                    }
                    case "E": 
                    {
                        System.out.println("Enter the name of the playlist to compare with:");
                        String name = input.nextLine();
                        int index = findPlaylistIndexByName(name);
                        if (index == -1) 
                        {
                            System.out.println("No playlist found with this name.");
                            break;
                        }
                        Playlist otherPlaylist = playlists[index];
                        if (currentPlaylist.equals(otherPlaylist)) 
                        {
                            System.out.println("The playlists are identical.");
                        } 
                        else 
                        {
                            System.out.println("The playlists are not identical.");
                        }
                        break;
                    }
                    case "D": 
                    {
                        System.out.println("All playlist names:");
                        for (int i = 0; i < playlistCount; i++) 
                        {
                            System.out.println(playlistNames[i]);
                        }
                        break;
                    }
                    case "Q":
                    {
                        running = false;
                        break;
                    }
                    default: 
                    {
                        System.out.println("Invalid option. Please select again.");
                    }
                }
            } 
            catch (Exception e) 
            {
                System.out.println("An error occurred: " + e.getMessage());
            }

            if (running) 
            {
                System.out.println("Please select an option");
            }
        }
        input.close();
    }
}