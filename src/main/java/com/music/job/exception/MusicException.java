package com.music.job.exception;

public class MusicException extends Exception {
	public MusicException(String message)
	{
		super("MusicException-"+message);
	}
	
	public MusicException(String message, Throwable cause)
	{
		super("MusicException-"+message,cause);
	}
}
