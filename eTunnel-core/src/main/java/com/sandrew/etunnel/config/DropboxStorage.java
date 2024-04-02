package com.sandrew.etunnel.config;

/**
 * @ClassName DropboxStorage
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:56
 **/
public class DropboxStorage
{
    private String account;
    private String password;
    private String token;

    private String root;


    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getRoot()
    {
        return root;
    }

    public void setRoot(String root)
    {
        this.root = root;
    }

    @Override
    public String toString()
    {
        return "DropboxStorage{" + "account='" + account + '\'' + ", password='" + password + '\'' + ", token='" + token + '\'' + ", root='" + root + '\'' + '}';
    }
}
