package jjgg.academysystem.mappers;

import jjgg.academysystem.DTO.UserCreateDTO;
import jjgg.academysystem.DTO.UserResponseDTO;
import jjgg.academysystem.DTO.UserUpdateDTO;
import jjgg.academysystem.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Convierte Entidad a DTO de respuesta (ignora el password)
    UserResponseDTO toUserResponseDTO(User user);

    // Convierte DTO de creación a Entidad
    User toUser(UserCreateDTO userCreateDTO);

    // Actualiza una entidad existente a partir de un DTO de actualización
    // @MappingTarget asegura que los campos del DTO se copien sobre la entidad existente
    void updateUserFromDto(UserUpdateDTO dto, @MappingTarget User user);
}
