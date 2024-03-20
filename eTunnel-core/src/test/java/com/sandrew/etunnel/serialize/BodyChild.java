package com.sandrew.etunnel.serialize;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName BodyChild
 * @Description
 * @Author summer
 * @Date 2024/3/20 14:17
 **/
public class BodyChild implements Serializable
{
    private String name;

    private int age;

    public BodyChild()
    {
    }

    public BodyChild(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "BodyChild{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BodyChild bodyChild = (BodyChild) o;
        return age == bodyChild.age && name.equals(bodyChild.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, age);
    }

    //    @Override
//    public boolean equals(Object obj)
//    {
//        if (!(obj instanceof BodyChild))
//        {
//            return false;
//        }
//        else
//        {
//            BodyChild bodyChild = BodyChild.class.cast(obj);
//            if (bodyChild.name.equals(this.name) && bodyChild.age == this.age)
//            {
//                return true;
//            }
//            return false;
//        }
//    }
}
