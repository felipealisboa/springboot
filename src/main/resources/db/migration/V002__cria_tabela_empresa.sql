CREATE TABLE empresa (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_plataforma BIGINT(20) NOT NULL,
    nome_empresa VARCHAR(200) NOT NULL UNIQUE,
    nome_fantasia VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    endereco VARCHAR(200) NOT NULL,
    telefone VARCHAR(25) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(10) NOT NULL,
    cep VARCHAR(30) NOT NULL,
    email VARCHAR(200) NOT NULL,
    complemento VARCHAR(100),
    status BOOLEAN DEFAULT true NOT NULL,
    data_hora DATETIME,
    FOREIGN KEY (id_plataforma) REFERENCES plataforma(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;