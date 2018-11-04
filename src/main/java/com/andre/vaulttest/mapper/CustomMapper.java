package com.andre.vaulttest.mapper;

public interface CustomMapper<T, U> {
    T DTOToBusiness(U dto);
    U BusinessToDTO(T business);
    void DTOToBusiness(U dto, T business);
}
