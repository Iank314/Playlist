/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 1
 * CSE214.R30
 */

/**
 * Playlist class to manage a list of song records.
 */
public class Playlist implements Cloneable
{
    private SongRecord[] playlist;
    private final int CAPACITY = 50;
    private int songCount;

    /**
     * Default constructor for Playlist.
     * @precondition None.
     * @postcondition A Playlist object is created with a capacity of 50 songs.
     */
    public Playlist()
    {
        playlist = new SongRecord[CAPACITY];
        songCount = 0;
    }

    /**
     * Gets the number of songs in the playlist.
     * @return The number of songs in the playlist.
     * @precondition None.
     * @postcondition The number of songs in the playlist is returned.
     */
    public int size()
    {
        return songCount;
    }

    /**
     * Clones the playlist.
     * @return A clone of the playlist.
     * @precondition None.
     * @postcondition A clone of this playlist is returned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        Playlist cloned = (Playlist) super.clone();
        cloned.playlist = new SongRecord[CAPACITY];
        for (int i = 0; i < songCount; i++)
        {
            cloned.playlist[i] = (SongRecord) playlist[i].clone();
        }
        return cloned;
    }

    /**
     * Checks if this playlist is equal to another object.
     * @param obj The object to compare with.
     * @return true if the playlists are equal, false otherwise.
     * @precondition None.
     * @postcondition Returns true if the playlist is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        Playlist otherPlaylist = (Playlist) obj;
        if (this.songCount != otherPlaylist.songCount)
        {
            return false;
        }
        for (int i = 0; i < songCount; i++)
        {
            if (!this.playlist[i].equals(otherPlaylist.playlist[i]))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a song to the playlist at a specified position.
     * @param song The song to add.
     * @param position The position to add the song.
     * @throws Exceptions.IncorrectTimeException if the song's time is invalid.
     * @throws Exceptions.FullPlaylistException if the playlist is full.
     * @throws Exceptions.InvalidArgumentException if the position is invalid.
     * @precondition song is a valid SongRecord object. position is between 0 and the current number of songs, inclusive.
     * @postcondition The song is added to the playlist at the specified position, and the song count is incremented by 1.
     */
    public void addSong(SongRecord song, int position) throws Exceptions.IncorrectTimeException, Exceptions.FullPlaylistException, Exceptions.InvalidArgumentException
    {
        if (songCount >= CAPACITY)
        {
            throw new Exceptions.FullPlaylistException("The playlist is full");
        }

        if (position < 0 || position > songCount)
        {
            throw new Exceptions.InvalidArgumentException("Invalid position to add a song");
        }
        songCount++;
        if(playlist[position] != null)
        {
            for (int i = songCount; i > position; i--)
            {
                playlist[i] = playlist[i - 1];
            }
        }
        for (int i = songCount; i > position; i--)
        {
            playlist[i] = playlist[i - 1];
        }
        playlist[position] = song;

    }

    /**
     * Removes a song from the playlist at a specified position.
     * @param position The position to remove the song.
     * @throws Exceptions.EmptyListException if the playlist is empty.
     * @throws Exceptions.InvalidArgumentException if the position is invalid.
     * @precondition The playlist is not empty. position is between 0 and the current number of songs minus 1, inclusive.
     * @postcondition The song at the specified position is removed, and the song count is decremented by 1.
     */
    public void removeSong(int position) throws Exceptions.EmptyListException, Exceptions.InvalidArgumentException
    {
        if (songCount == 0)
        {
            throw new Exceptions.EmptyListException("The playlist is empty");
        }
        if (position < 0 || position >= songCount)
        {
            throw new Exceptions.InvalidArgumentException("Invalid song index");
        }
        for (int i = position; i < songCount - 1; i++)
        {
            playlist[i] = playlist[i + 1];
        }
        playlist[--songCount] = null;
    }

    /**
     * Gets a song from the playlist at a specified index.
     * @param index The index of the song.
     * @return The song at the specified index.
     * @throws Exceptions.InvalidArgumentException if the index is invalid.
     * @precondition index is between 0 and the current number of songs minus 1, inclusive.
     * @postcondition The song at the specified index is returned.
     */
    public SongRecord getSong(int index) throws Exceptions.InvalidArgumentException
    {
        if (index < 0 || index >= songCount)
        {
            throw new Exceptions.InvalidArgumentException("Invalid song index");
        }
        return playlist[index];
    }

    /**
     * Prints all the songs in the playlist.
     * @precondition None.
     * @postcondition All songs in the playlist are printed to the console.
     */
    public void printAllSongs()
    {
        System.out.println("No. | Title               | Artist              | Minutes:Seconds");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < songCount; i++)
        {
            System.out.printf("%-3d | %s%n", i + 1, playlist[i].toString());
        }
    }

    /**
     * Gets all songs by a specific artist.
     * @param originalList The original playlist.
     * @param artist The artist name to filter by.
     * @return A new playlist containing songs by the specified artist.
     * @throws Exceptions.InvalidArgumentException if arguments are invalid.
     * @throws Exceptions.IncorrectTimeException if the song's time is invalid.
     * @throws Exceptions.FullPlaylistException if the playlist is full.
     * @precondition originalList is a valid Playlist object. artist is a non-null string.
     * @postcondition A new playlist containing songs by the specified artist is returned.
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist) throws Exceptions.InvalidArgumentException, Exceptions.IncorrectTimeException, Exceptions.FullPlaylistException
    {
        Playlist artistPlaylist = new Playlist();
        int count = 0;
        for (int i = 0; i < originalList.size(); i++)
        {
            SongRecord song = originalList.getSong(i);
            if (song != null && song.getArtist().equals(artist))
            {
                artistPlaylist.addSong(song, count);
                count++;
            }
        }
        return artistPlaylist;
    }
}