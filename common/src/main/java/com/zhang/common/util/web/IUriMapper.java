package com.zhang.common.util.web;

import com.zhang.common.interfaces.identity.IWithLongID;

import java.io.Serializable;

public interface IUriMapper {

    <T extends IWithLongID & Serializable> String getUriBase(final Class<T> clazz);

}
