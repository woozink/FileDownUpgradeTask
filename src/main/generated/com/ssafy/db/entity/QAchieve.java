package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAchieve is a Querydsl query type for Achieve
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAchieve extends EntityPathBase<Achieve> {

    private static final long serialVersionUID = -1726697393L;

    public static final QAchieve achieve = new QAchieve("achieve");

    public final TimePath<java.time.LocalTime> BestTime = createTime("BestTime", java.time.LocalTime.class);

    public final NumberPath<Long> CoinCount = createNumber("CoinCount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> mmr = createNumber("mmr", Long.class);

    public final TimePath<java.time.LocalTime> playTime = createTime("playTime", java.time.LocalTime.class);

    public final StringPath tier = createString("tier");

    public final StringPath userId = createString("userId");

    public final NumberPath<Long> winCount = createNumber("winCount", Long.class);

    public QAchieve(String variable) {
        super(Achieve.class, forVariable(variable));
    }

    public QAchieve(Path<? extends Achieve> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAchieve(PathMetadata metadata) {
        super(Achieve.class, metadata);
    }

}

