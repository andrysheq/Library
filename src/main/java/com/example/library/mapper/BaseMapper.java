package com.example.library.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class BaseMapper extends ModelMapper {

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        if (source == null || destinationType == null) {
            return null;
        }

        return super.map(source, destinationType);
    }
    public <S, D> List<D> convertList(Collection<S> inList, Class<D> destClass) {
        List<D> result = new ArrayList<>();
        if (inList != null && destClass != null) {
            for (S e : inList) result.add(super.map(e, destClass));
        }

        return result;
    }
}
