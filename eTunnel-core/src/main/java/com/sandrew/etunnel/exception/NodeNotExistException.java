package com.sandrew.etunnel.exception;


/**
 * @Author summer
 * @Description
 * @Date 10:31 2024/4/3
 * @Param 
 * @return 
 **/
public class NodeNotExistException extends Exception
{
	public NodeNotExistException()
	{
		super();
	}

	public NodeNotExistException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public NodeNotExistException(String message)
	{
		super(message);
	}

	public NodeNotExistException(Throwable cause)
	{
		super(cause);
	}
}
