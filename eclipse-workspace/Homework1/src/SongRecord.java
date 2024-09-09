/*
 * Ian Kaufman
 * 115639955
 * ian.kaufman@stonybrook.edu
 * Homework 1
 * CSE214.R30
 */


/**
 * SongRecord class to represent a song.
 */
public class SongRecord implements Cloneable
{
    private String title;
    private String artist;
    private int seconds;
    private int minutes;
    

    /**
     * Default constructor for SongRecord.
     * @precondition None.
     * @postcondition A SongRecord object is created with default values.
     */
    public SongRecord()
    {
        this.title = "";
        this.artist = "";
        this.seconds = 0;
        this.minutes = 0;
    }

    /**
     * Sets the title of the song.
     * @param title The title of the song.
     * @precondition None.
     * @postcondition The title of the song is set to the specified value.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Sets the artist of the song.
     * @param artist The artist of the song.
     * @precondition None.
     * @postcondition The artist of the song is set to the specified value.
     */
    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    /**
     * Sets the seconds of the song duration.
     * @param seconds The seconds of the song duration.
     * @throws Exceptions.IncorrectTimeException if the seconds are not between 0 and 59.
     * @precondition seconds is between 0 and 59.
     * @postcondition The seconds of the song duration is set to the specified value.
     */
    public void setSeconds(int seconds) throws Exceptions.IncorrectTimeException
    {
        if(seconds < 0 || seconds >= 60)
        {
            throw new Exceptions.IncorrectTimeException("This seconds value is out of bounds");
        }
        else
        {
            this.seconds = seconds;
        }
    }

    /**
     * Sets the minutes of the song duration.
     * @param minutes The minutes of the song duration.
     * @throws Exceptions.IncorrectTimeException if the minutes are negative.
     * @precondition minutes is non-negative.
     * @postcondition The minutes of the song duration is set to the specified value.
     */
    public void setMinutes(int minutes) throws Exceptions.IncorrectTimeException
    {
        if(minutes < 0)
        {
            throw new Exceptions.IncorrectTimeException("This minutes value is not valid");
        }
        else
        {
            this.minutes = minutes;
        }
    }

    /**
     * Gets the title of the song.
     * @return The title of the song.
     * @precondition None.
     * @postcondition The title of the song is returned.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the artist of the song.
     * @return The artist of the song.
     * @precondition None.
     * @postcondition The artist of the song is returned.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Gets the seconds of the song duration.
     * @return The seconds of the song duration.
     * @precondition None.
     * @postcondition The seconds of the song duration is returned.
     */
    public int getSeconds()
    {
        return seconds;
    }
     /**
     * Gets the minutes of the song duration.
     * @return The minutes of the song duration.
     * @precondition None.
     * @postcondition The minutes of the song duration is returned.
     */
    public int getMinutes()
    {
        return minutes;
    }

    /**
     * Returns a string representation of the song.
     * @return A string representation of the song.
     * @precondition None.
     * @postcondition A formatted string representing the song is returned.
     */
    @Override
    public String toString()
    {
        return String.format("%-20s | %-20s | %02d:%02d", title, artist, minutes, seconds);
    }

    /**
     * Checks if this song is equal to another object.
     * @param obj The object to compare with.
     * @return true if the songs are equal, false otherwise.
     * @precondition None.
     * @postcondition Returns true if the song record is equal to the specified object, false otherwise.
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

        SongRecord song = (SongRecord) obj;

        if (seconds != song.seconds)
        {
            return false;
        }
        if (minutes != song.minutes)
        {
            return false;
        }
        if (!title.equals(song.title))
        {
            return false;
        }
        return artist.equals(song.artist);
    }

    /**
     * Clones this song record.
     * @return A clone of this song record.
     * @throws CloneNotSupportedException if cloning is not supported.
     * @precondition None.
     * @postcondition A clone of this song record is returned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}