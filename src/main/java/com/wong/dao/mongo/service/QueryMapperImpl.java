package com.wong.dao.mongo.service;

import com.wong.dao.mongo.mapper.QueryMapper;
import com.wong.dao.mongo.pojo.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryMapperImpl implements QueryMapper {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 该方法返回指定数量的单词（后期升级，传递id数组，返回不包含在id数组的随机单词。）
     * @param numbers
     * @return
     */
    @Override
    public List<Word> randomWords(int numbers,Class<Word> cls) {
            Aggregation aggregation = Aggregation.newAggregation(
//                            Aggregation.match(Criteria.where("status").is(1)),
                            Aggregation.sample(numbers));
            AggregationResults<Word> outputTypeCount = mongoTemplate.aggregate(aggregation, cls, cls);
            return outputTypeCount.getMappedResults();
    }
}
