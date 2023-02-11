package com.cleancode.adapter.out.mapper;

import java.util.List;

public interface ListMapper<Domain, Entity> {

    List<Domain> toDomain(List<Entity> entities);

    List<Entity> toEntity(List<Domain> domains);

}
