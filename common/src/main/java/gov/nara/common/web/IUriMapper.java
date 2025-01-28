package gov.nara.common.web;

import gov.nara.common.interfaces.IWithLongID;

import java.io.Serializable;

public interface IUriMapper {

    <T extends IWithLongID & Serializable> String getUriBase(final Class<T> clazz);

}
