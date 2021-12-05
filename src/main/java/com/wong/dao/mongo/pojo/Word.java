package com.wong.dao.mongo.pojo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Document(collection = "words")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word implements Serializable {

    @Id
    private String _id;

    @Field("voices")
    private List<String> voices;

    @Field("names")
    private String names;

    @Field("means")
    private List<String> means;

    @Field("derive")
    private List<String> derive;

    @Field("examples")
    private List<Exam_Object> examples;
}
