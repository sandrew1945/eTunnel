package com.sandrew.etunnel.protpcol;

/**
 * @ClassName Command
 * @Description
 * @Author summer
 * @Date 2024/3/21 10:35
 **/
public enum Command
{
    UPLOAD_REQ((byte) 0),           // file upload request
    UPLOAD_RES((byte) 1),           // file upload response
    BREAK_POINT_REQ((byte) 2),      // file break point upload request
    BREAK_POINT_RES((byte) 3),      // file break point upload response
    STREAM_UPLOAD_REQ((byte) 4),    // file stream upload request
    STREAM_UPLOAD_RES((byte) 5),    // file stream upload response
    DOWNLOAD_REQ((byte) 6),         // file download request
    DOWNLOAD_RES((byte) 7),         // file download response
    STREAM_DOWNLOAD_REQ((byte) 8),  // file stream download request
    STREAM_DOWNLOAD_RES((byte) 9);  // file stream download response

    private byte command;

    Command(byte command)
    {
        this.command = command;
    }

    public byte getCommand()
    {
        return command;
    }
}
