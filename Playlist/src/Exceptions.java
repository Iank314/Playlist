

/**
 * Exceptions class to handle custom exceptions.
 */
public class Exceptions 
{
    /**
     * Exception thrown when the time of a song is incorrect.
     * @precondition None.
     * @postcondition An exception with a specified message is created.
     */
    public static class IncorrectTimeException extends Exception 
    {
        /**
         * Constructs an IncorrectTimeException with a specified message.
         * @param message The detail message.
         */
        public IncorrectTimeException(String message) 
        {
            super(message);
        }
    }

    /**
     * Exception thrown when the playlist is full.
     * @precondition None.
     * @postcondition An exception with a specified message is created.
     */
    public static class FullPlaylistException extends Exception 
    {
        /**
         * Constructs a FullPlaylistException with a specified message.
         * @param message The detail message.
         */
        public FullPlaylistException(String message) 
        {
            super(message);
        }
    }

    /**
     * Exception thrown when an argument is invalid.
     * @precondition None.
     * @postcondition An exception with a specified message is created.
     */
    public static class InvalidArgumentException extends Exception 
    {
        /**
         * Constructs an InvalidArgumentException with a specified message.
         * @param message The detail message.
         */
        public InvalidArgumentException(String message) 
        {
            super(message);
        }
    }
    /**
     * Exception thrown when the playlist is empty.
     * @precondition None.
     * @postcondition An exception with a specified message is created.
     */
    public static class EmptyListException extends Exception 
    {
        /**
         * Constructs an EmptyListException with a specified message.
         * @param message The detail message.
         */
        public EmptyListException(String message) 
        {
            super(message);
        }
    }
}
