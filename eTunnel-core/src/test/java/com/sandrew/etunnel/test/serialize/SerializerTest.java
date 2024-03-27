package com.sandrew.etunnel.test.serialize;

import com.sandrew.etunnel.protpcol.serializer.HessianSerializer;
import com.sandrew.etunnel.protpcol.serializer.JSONSerializer;
import com.sandrew.etunnel.protpcol.serializer.JavaSerializer;
import com.sandrew.etunnel.protpcol.serializer.Serializer;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @ClassName SerializerTest
 * @Description
 * @Author summer
 * @Date 2024/3/20 14:05
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SerializerTest
{
    private static Logger log = LoggerFactory.getLogger(SerializerTest.class);

    private BodyForSerialize body;

    @Test
    @DisplayName("Java serializer test")
    public void JavaSerializerTest()
    {
        System.out.println("Start java serializer test");
        Serializer serializer = new JavaSerializer();
        byte[] bytes = serializer.serialize(this.body);
        BodyForSerialize recover = serializer.deserialize(bytes, BodyForSerialize.class);
        System.out.println(recover);
        assertTrue(recover.equals(this.body));
    }

    @Test
    @DisplayName("JSON serializer test")
    public void JSONSerializerTest()
    {
        System.out.println("Start json serializer test");
        Serializer serializer = new JSONSerializer();
        byte[] bytes = serializer.serialize(this.body);
        BodyForSerialize recover = serializer.deserialize(bytes, BodyForSerialize.class);
        System.out.println(recover);
        assertTrue(recover.equals(this.body));
    }

    @Test
    @DisplayName("Hessian serializer test")
    public void HessianSerializerTest()
    {
        System.out.println("Start hessian serializer test");
        Serializer serializer = new HessianSerializer();
        byte[] bytes = serializer.serialize(this.body);
        BodyForSerialize recover = serializer.deserialize(bytes, BodyForSerialize.class);
        System.out.println(recover);
        assertTrue(recover.equals(this.body));
        assertEquals(recover, this.body);
    }


    @BeforeAll
    @DisplayName("Initialize data")
    public void assambleBody()
    {

        this.body = new BodyForSerialize();
        body.setStr1("str1");
        body.setStr2("str2");
        body.setInt1(1);
        body.setInt2(2);
        body.setDouble1(1.1);
        body.setDouble2(1.2);
        body.addList1("listStr1");
        body.addList1("listStr2");
        body.addList1("listStr3");
        body.addList1("listStr4");
        body.addList2(new BodyChild("list1Name", 11));
        body.addList2(new BodyChild("list2Name", 21));
        body.addList2(new BodyChild("list3Name", 31));
        body.addList2(new BodyChild("list4Name", 41));

        Map<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        map.put("key3", "val3");
        map.put("key4", "val4");
        body.setMap(map);
        BodyChild bodyChild = new BodyChild("zhangsan", 86);
        body.setBodyChild(bodyChild);
        body.setFile(new File("/Users/summer/Desktop/东北证券EtoC电子合同接口文档-信创版.pdf"));
        System.out.println("Data initialized ...");
    }
}
