package com.sandrew.etunnel.exception;


/**
 * @Author summer
 * @Description
 * @Date 10:31 2024/4/3
 * @Param 
 * @return 
 **/
public class DropboxException extends Exception
{
	public DropboxException()
	{
		super();
	}

	public DropboxException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public DropboxException(String message)
	{
		super(message);
	}

	public DropboxException(Throwable cause)
	{
		super(cause);
	}
}
