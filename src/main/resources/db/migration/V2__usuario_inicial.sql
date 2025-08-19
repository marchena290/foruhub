CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Insertamos un usuario inicial
INSERT INTO usuarios (nombre, email, password)
VALUES ('Usuario Foro', 'usuario@foro.com', '$2a$12$UVBEoVo.5nvLNLVUfQmAbeR1yBoTDw/AkSvbuH2YEHu1U.y8g3Q2W');