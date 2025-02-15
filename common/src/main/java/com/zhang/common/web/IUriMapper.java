package com.zhang.common.web;

import com.zhang.common.interfaces.IWithLongID;

import java.io.Serializable;

public interface IUriMapper {

    <T extends IWithLongID & Serializable> String getUriBase(final Class<T> clazz);

}
