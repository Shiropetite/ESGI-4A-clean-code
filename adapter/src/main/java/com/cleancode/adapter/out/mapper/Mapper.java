package com.cleancode.adapter.out.mapper;

public interface Mapper<Domain, Entity> {

    Domain toDomain(Entity entity);

    Entity toEntity(Domain domain);

}
