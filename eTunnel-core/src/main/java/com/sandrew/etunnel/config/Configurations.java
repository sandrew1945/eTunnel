package com.sandrew.etunnel.config;

import com.sandrew.etunnel.exception.NodeNotExistException;

import java.util.List;

/**
 * @ClassName Configurations
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:48
 **/
public class Configurations
{
    private ServerConfiguration server;

    private LocalConfiguration local;

    private RemoteConfiguration remote;

    public ServerConfiguration getServer()
    {
        return server;
    }

    public void setServer(ServerConfiguration server)
    {
        this.server = server;
    }

    public LocalConfiguration getLocal()
    {
        return local;
    }

    public void setLocal(LocalConfiguration local)
    {
        this.local = local;
    }

    public RemoteConfiguration getRemote()
    {
        return remote;
    }

    public void setRemote(RemoteConfiguration remote)
    {
        this.remote = remote;
    }

    /**
     * @Author summer
     * @Description Get server startup port
     * @Date 10:26 2024/4/3
     * @Param []
     * @return int
     **/
    public int getServerPort()
    {
        return this.server.getPort();
    }

    /**
     * @Author summer
     * @Description Get local nodes
     * @Date 10:28 2024/4/3
     * @Param []
     * @return java.util.List<com.sandrew.etunnel.config.DiskStorage>
     **/
    public List<DiskStorage> getLocalNodes() throws NodeNotExistException
    {
        if (this.local.getDisk().size() == 0)
        {
            throw new NodeNotExistException("No defined local nodes");
        }
        return this.local.getDisk().values().stream().toList();
    }

    /**
     * @Author summer
     * @Description Get local node by name
     * @Date 10:35 2024/4/3
     * @Param [nodeName]
     * @return com.sandrew.etunnel.config.DiskStorage
     **/
    public DiskStorage getLocalNodeByName(String nodeName) throws NodeNotExistException
    {
        if (null == this.local.getDisk().get(nodeName))
        {
            throw new NodeNotExistException("Can't find the specify local node : " + nodeName);
        }
        return this.local.getDisk().get(nodeName);
    }

    @Override
    public String toString()
    {
        return "Configurations{" + "server=" + server + ", local=" + local + ", remote=" + remote + '}';
    }
}
