package com.sandrew.etunnel.serialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName BodyForSerialize
 * @Description
 * @Author summer
 * @Date 2024/3/20 14:14
 **/
public class BodyForSerialize implements Serializable
{
    private static final byte COMMAND = 1;

    private String str1;

    private String str2;

    private int int1;

    private Integer int2;

    private double double1;

    private Double double2;

    private List<String> list1;

    private List<BodyChild> list2;

    private Map map;

    private BodyChild bodyChild;

    public String getStr1()
    {
        return str1;
    }

    public void setStr1(String str1)
    {
        this.str1 = str1;
    }

    public String getStr2()
    {
        return str2;
    }

    public void setStr2(String str2)
    {
        this.str2 = str2;
    }

    public int getInt1()
    {
        return int1;
    }

    public void setInt1(int int1)
    {
        this.int1 = int1;
    }

    public Integer getInt2()
    {
        return int2;
    }

    public void setInt2(Integer int2)
    {
        this.int2 = int2;
    }

    public double getDouble1()
    {
        return double1;
    }

    public void setDouble1(double double1)
    {
        this.double1 = double1;
    }

    public Double getDouble2()
    {
        return double2;
    }

    public void setDouble2(Double double2)
    {
        this.double2 = double2;
    }

    public List<String> getList1()
    {
        return list1;
    }

    public void setList1(List<String> list1)
    {
        this.list1 = list1;
    }

    public List<BodyChild> getList2()
    {
        return list2;
    }

    public void setList2(List<BodyChild> list2)
    {
        this.list2 = list2;
    }

    public Map getMap()
    {
        return map;
    }

    public void setMap(Map map)
    {
        this.map = map;
    }

    public BodyChild getBodyChild()
    {
        return bodyChild;
    }

    public void setBodyChild(BodyChild bodyChild)
    {
        this.bodyChild = bodyChild;
    }

    public void addList1(String str)
    {
        if (null == this.list1)
        {
            this.list1 = new ArrayList<>();
        }
        this.list1.add(str);
    }

    public void addList2(BodyChild bodyChild)
    {
        if (null == this.list2)
        {
            this.list2 = new ArrayList<>();
        }
        this.list2.add(bodyChild);
    }

    @Override
    public String toString()
    {
        return String.format("""
                BodyForSerialize{str1 = %s,
                                 str2 = %s,
                                 int1 = %d,
                                 int2 = %d,
                                 double1 = %f,
                                 double2 = %f,
                                 list1 = %s,
                                 list2 = %s,
                                 map = %s,
                                 bodyChild = %s}                                
                """, str1, str2, int1, int2, double1, double2, list1.toString(), list2.toString(), map.toString(), bodyChild.toString().stripLeading()
        );
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BodyForSerialize that = (BodyForSerialize) o;
        return int1 == that.int1 && Double.compare(that.double1, double1) == 0 && str1.equals(that.str1) && str2.equals(that.str2) && int2.equals(that.int2) && double2.equals(that.double2) && list1.equals(that.list1) && list2.equals(that.list2) && map.equals(that.map) && bodyChild.equals(that.bodyChild);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(str1, str2, int1, int2, double1, double2, list1, list2, map, bodyChild);
    }
}
